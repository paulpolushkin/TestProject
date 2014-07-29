/*
 * Класс реализует взаимную блокировку потоков
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author Pavel Polushkin
 * @since 29.07.2014 (1.0)
 * @version 29.07.2014
 */
public class DeadLocker {

    static Thread t1;
    static Thread t2;

    /**
     * Метод создает дэдлок
     */
    public static void callDeadLock() {

        Thread.setDefaultUncaughtExceptionHandler(new SimpleMessageUncaughtExceptionHandler());

        Bar b1 = new Bar();
        Bar b2 = new Bar();

        t1 = new Foo(b1, b2);
        t2 = new Foo(b2, b1);

        t1.start();
        t2.start();
    }

    /**
     * Возвращает поток 1
     * Нужен для тестирования
     * @return поток 1
     */
    public static Thread getT1() {
        return t1;
    }

    /**
     * Возвращает поток 2
     * Нужен для тестирования
     * @return поток 2
     */
    public static Thread getT2() {
        return t2;
    }

    /**
     * Класс-наследник Thread. Используется два его объекта,
     * работающие с двумя (одними и теми же) объектами класса Bar
     */
    static class Foo extends Thread {

        final Bar b1;
        final Bar b2;

        Foo(Bar b1, Bar b2) {
            this.b1 = b1;
            this.b2 = b2;
        }

        @Override
        public void run() {
            try {
                b1.get(b2);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /**
     * Класс, объекты которого будут использоваться в разных потоках
     */
    static class Bar {

        int a;

        synchronized void get(Bar b) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
            b.set();
        }

        synchronized void set() {}
    }

}
