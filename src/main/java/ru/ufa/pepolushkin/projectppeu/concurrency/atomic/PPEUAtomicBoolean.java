/*
 * Класс реализует атомарные операции с типом boolean
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 24.07.2014 (1.0)
 * @version 24.07.2014
 */
public class PPEUAtomicBoolean {

    /**
     * Значение типа boolean, с которым необходимо проводить атомарные операции
     * default {@code value = false}
     */
    private volatile boolean value = false;

    /**
     *  Конструктор по умолчанию
     *  default {@code value = false}
     */
    public PPEUAtomicBoolean() {}

    /**
     *  Конструктор устанавливает значение для работы
     *  @param value значение
     */
    public PPEUAtomicBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Возвращает текущее значение атомарно
     * @return текущее значение
     */
    public boolean get() {
        return value;
    }

    /**
     * Устанавливает новое значение атомарно
     * @param value новое значение
     */
    public void set(boolean value) {
        this.value = value;
    }

    /**
     * Устанавливает новое значение и возвращает старое атомарно
     * @param value новое значение
     * @return старое значение
     */
    public synchronized boolean getAndSet(boolean value) {
        boolean temp = this.value;
        this.value = value;
        return temp;
    }

}
