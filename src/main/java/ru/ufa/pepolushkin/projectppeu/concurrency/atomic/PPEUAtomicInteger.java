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
     *  Конструктор с параметром
     *  @param устанавливает значение для работы
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
     * Устанавливает новое значение и возвращает старое атомарно
     * @param value новое значение
     * @return старое значение
     */
    public synchronized int getAndSet(int value) {
        int temp = this.value;
        this.value = value;
        return temp;
    }
    
}
