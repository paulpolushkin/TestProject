/*
 * Класс реализует атомарные операции с типом long
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 25.07.2014 (1.0)
 * @version 25.07.2014
 */
public class PPEUAtomicLong {

    /**
     * Значение типа long, с которым необходимо проводить атомарные операции
     * default {@code value = 0}
     */
    private volatile long value = 0;

    /**
     *  Конструктор по умолчанию
     *  default {@code value = 0}
     */
    public PPEUAtomicLong() {}

    /**
     *  Конструктор с параметром
     *  @param устанавливает значение для работы
     */
    public PPEUAtomicLong(long value) {
        this.value = value;
    }

    /**
     * Возвращает текущее значение атомарно
     * @return текущее значение
     */
    public long get() {
        return value;
    }

    /**
     * Устанавливает новое значение атомарно
     * @param value новое значение
     */
    public void set(long value) {
        this.value = value;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * @param value число, на которое нужно увеличить текущее значение
     */
    public synchronized void add(long value) {
        this.value += value;
    }

    /**
     * Увеличивает на единицу
     */
    public void increment() {
        add(1L);
    }

    /**
     * Уменьшает на единицу
     */
    public void decrement() {
        add(-1L);
    }

    /**
     * Устанавливает новое значение и возвращает старое атомарно
     * @param value новое значение
     * @return старое значение
     */
    public synchronized long getAndSet(long value) {
        long temp = this.value;
        this.value = value;
        return temp;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * и возвращает новое значение
     * @param value число, на которое нужно увеличить текущее значение
     * @return новое значение
     */
    public synchronized long addAndGet(long value) {
        this.value += value;
        return this.value;
    }

    /**
     * Добавляет к текущему значению переданное в параметре число
     * и возвращает сумму
     * @param value число, на которое нужно увеличить текущее значение
     * @return старое значение
     */
    public synchronized long getAndAdd(long value) {
        long temp = this.value;
        this.value += value;
        return temp;
    }

    /**
     * Увеличивает на единицу и возвращает то, что получилось
     * @return результат увеличения на единицу
     */
    public long incrementAndGet() {
        return addAndGet(1);
    }

    /**
     * Увеличивает на единицу и возвращает старое значение
     * @return Старое значение
     */
    public long getAndIncrement() {
        return getAndAdd(1);
    }

    /**
     * Уменьшает на единицу и возвращает то, что получилось
     * @return результат уменьшения на единицу
     */
    public long decrementAndGet() {
        return addAndGet(-1);
    }

    /**
     * Уменьшает на единицу и возвращает старое значение
     * @return Старое значение
     */
    public long addAndDecrement() {
        return getAndAdd(-1);
    }

}
