/*
 * Класс реализует атомарные операции с типом int
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 25.07.2014 (1.0)
 * @version 25.07.2014
 */
public class PPEUAtomicInteger {

    /**
     * Значение типа int, с которым необходимо проводить атомарные операции
     * default {@code value = 0}
     */
    private volatile int value = 0;

    /**
     *  Конструктор по умолчанию
     *  default {@code value = 0}
     */
    public PPEUAtomicInteger() {}

    /**
     *  Конструктор устанавливает значение для работы
     *  @param value значение
     */
    public PPEUAtomicInteger(int value) {
        this.value = value;
    }

    /**
     * Возвращает текущее значение атомарно
     * @return текущее значение
     */
    public int get() {
        return value;
    }

    /**
     * Устанавливает новое значение атомарно
     * @param value новое значение
     */
    public void set(int value) {
        this.value = value;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * @param value число, на которое нужно увеличить текущее значение
     */
    public synchronized void add(int value) {
        this.value += value;
    }

    /**
     * Увеличивает на единицу
     */
    public void increment() {
        add(1);
    }

    /**
     * Уменьшает на единицу
     */
    public void decrement() {
        add(-1);
    }

    /**
     * Устанавливает новое значение и возвращает старое атомарно
     * @param value новое значение
     * @return старое значение
     */
    public synchronized int getAndSet(int value) {
        int temp = this.value;
        this.value = value;
        return temp;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * и возвращает новое значение
     * @param value число, на которое нужно увеличить текущее значение
     * @return новое значение
     */
    public synchronized int addAndGet(int value) {
        this.value += value;
        return this.value;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * и возвращает сумму
     * @param value число, на которое нужно увеличить текущее значение
     * @return старое значение
     */
    public synchronized int getAndAdd(int value) {
        int temp = this.value;
        this.value += value;
        return temp;
    }

    /**
     * Увеличивает на единицу и возвращает то, что получилось
     * @return результат увеличения на единицу
     */
    public int incrementAndGet() {
        return addAndGet(1);
    }

    /**
     * Увеличивает на единицу и возвращает старое значение
     * @return Старое значение
     */
    public int getAndIncrement() {
        return getAndAdd(1);
    }

    /**
     * Уменьшает на единицу и возвращает то, что получилось
     * @return результат уменьшения на единицу
     */
    public int decrementAndGet() {
        return addAndGet(-1);
    }

    /**
     * Уменьшает на единицу и возвращает старое значение
     * @return Старое значение
     */
    public int addAndDecrement() {
        return getAndAdd(-1);
    }

}
