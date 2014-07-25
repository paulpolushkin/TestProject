/*
 * Класс реализует атомарные операции с сылочными типами
 */

package ru.ufa.pepolushkin.projectppeu.concurrency.atomic;

/**
 * @author Pavel Polushkin
 * @since 25.07.2014 (1.0)
 * @version 25.07.2014
 */
public class PPEUAtomicReference<P> {

    /**
     * Ссылка, с которой необходимо проводить атомарные операции
     * default {@code value = null}
     */
    private volatile P value = null;

    /**
     *  Конструктор по умолчанию
     *  default {@code value = null}
     */
    public PPEUAtomicReference() {}

    /**
     *  Конструктор устанавливает значение для работы
     *  @param value значение
     */
    public PPEUAtomicReference(P value) {
        this.value = value;
    }

    /**
     * Возвращает текущее значение атомарно
     * @return текущее значение
     */
    public P get() {
        return value;
    }

    /**
     * Устанавливает новое значение атомарно
     * @param value новое значение
     */
    public void set(P value) {
        this.value = value;
    }

    /**
     * Устанавливает новое значение и возвращает старое атомарно
     * @param value новое значение
     * @return старое значение
     */
    public synchronized P getAndSet(P value) {
        P temp = this.value;
        this.value = value;
        return temp;
    }

}
