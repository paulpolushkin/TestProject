/*
 * Класс реализует атомарные операции с массивом типа int
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 25.07.2014 (1.0)
 * @version 25.07.2014
 */
public class PPEUAtomicIntegerArray {

    /**
     * Массив типа int, с которым необходимо проводить атомарные операции
     */
    private volatile int[] array;

    private ArrayModification mod;

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
        this.array = array;
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
     * Внутренний класс для модификации самого массива,
     * а не только его данных
     */
    class ArrayModification {

    }

    /**
     * Лениво возвращает объект {@link ArrayModification}
     * для модификации массива
     * Сделаем двойную проверку на null чтобы сборщику мусора не собирать лишние объекты)))
     * @return объект {@link ArrayModification}
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
