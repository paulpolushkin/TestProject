/*
 * Класс реализует атомарные операции с массивом типа int
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 25.07.2014 (1.0)
 * @version 26.07.2014
 */
public class PPEUAtomicIntegerArray {

    /**
     * Массив типа int, с которым необходимо проводить атомарные операции
     */
    private volatile int[] array;

    private volatile ArrayModification mod;

    /**
     *  Конструктор принимает значение размера массива
     *  и создает на его основе новый массив
     *  @param size размер массива
     */
    public PPEUAtomicIntegerArray(int size) {
        array = new int[size];
    }

    /**
     *  Конструктор принимает готовый массив
     *  @param array массив
     */
    public PPEUAtomicIntegerArray(int[] array) {
        this.array = array.clone();
    }

    /**
     * Проверяет не выходит ли номер элемента массива за пределы последнего
     * @param i номер элемента массива
     */
    private void checkBounds(int i) {
        if(i < 0 || i >= array.length)
            throw new IndexOutOfBoundsException("index " + i
                    + " out of range 0..array.length(" + (array.length - 1) + ")");
    }

    /**
     * Возвращает текущий массив
     * @return текущий массив
     */
    public int[] get() {
        return array;
    }

    /**
     * Возвращает длину массива
     * @return длина массива
     */
    public int length() {
        return array.length;
    }

    /**
     * Возвращает текущее значение элемента массива атомарно
     * @param i номер элемента массива
     * @return текущее значение элемента массива
     */
    public synchronized int get(int i) {
        checkBounds(i);
        return array[i];
    }

    /**
     * Устанавливает новое значение элемента массива атомарно
     * @param i номер элемента массива
     * @param value новое значение
     */
    public synchronized void set(int i, int value) {
        checkBounds(i);
        array[i] = value;
    }

    /**
     * Добавляет к текущему значению элемента массива переданное число
     * @param i номер элемента массива
     * @param value число, на которое нужно увеличить текущее значение
     */
    public synchronized void add(int i, int value) {
        checkBounds(i);
        array[i] += value;
    }

    /**
     * Увеличивает значение элемента массива на единицу
     * @param i номер элемента массива
     */
    public void increment(int i) {
        add(i, 1);
    }

    /**
     * Уменьшает значение элемента массива на единицу
     * @param i номер элемента массива
     */
    public void decrement(int i) {
        add(i, -1);
    }

    /**
     * Устанавливает новое значение элемента массива и возвращает старое атомарно
     * @param i номер элемента массива
     * @param value новое значение
     * @return старое значение
     */
    public synchronized int getAndSet(int i, int value) {
        checkBounds(i);
        int temp = array[i];
        array[i] = value;
        return temp;
    }

    /**
     * Добавляет к текущему значению элемента массива переданное в параметре число
     * и возвращает новое значение
     * @param i номер элемента массива
     * @param value число, на которое нужно увеличить текущее значение
     * @return новое значение
     */
    public synchronized int addAndGet(int i, int value) {
        checkBounds(i);
        array[i] += value;
        return array[i];
    }

    /**
     * Добавляет к текущему значению элемента массива переданное в параметре число
     * и возвращает сумму
     * @param i номер элемента массива
     * @param value число, на которое нужно увеличить текущее значение
     * @return старое значение
     */
    public synchronized int getAndAdd(int i, int value) {
        checkBounds(i);
        int temp = array[i];
        array[i] += value;
        return temp;
    }

    /**
     * Увеличивает значение элемента массива на единицу и возвращает то, что получилось
     * @param i номер элемента массива
     * @return результат увеличения на единицу
     */
    public int incrementAndGet(int i) {
        return addAndGet(i,1);
    }

    /**
     * Увеличивает значение элемента массива на единицу и возвращает старое значение
     * @param i номер элемента массива
     * @return Старое значение
     */
    public int getAndIncrement(int i) {
        return getAndAdd(i,1);
    }

    /**
     * Уменьшает значение элемента массива на единицу и возвращает то, что получилось
     * @param i номер элемента массива
     * @return результат уменьшения на единицу
     */
    public int decrementAndGet(int i) {
        return addAndGet(i,-1);
    }

    /**
     * Уменьшает значение элемента массива на единицу и возвращает старое значение
     * @param i номер элемента массива
     * @return Старое значение
     */
    public int addAndDecrement(int i) {
        return getAndAdd(i,-1);
    }

    /**
     * Внутренний класс для модификации самого массива,
     * а не только его данных
     */
    public class ArrayModification {

    }

    /**
     * Лениво возвращает объект {@linkplain PPEUAtomicIntegerArray.ArrayModification}
     * для модификации массива
     * @return объект {@linkplain PPEUAtomicIntegerArray.ArrayModification}
     */
    public ArrayModification getArrayModification() {
        if(mod == null) {
            synchronized(this) {
                if(mod == null) {
                    mod = new ArrayModification();
                }
            }
        }
        return mod;
    }

}
