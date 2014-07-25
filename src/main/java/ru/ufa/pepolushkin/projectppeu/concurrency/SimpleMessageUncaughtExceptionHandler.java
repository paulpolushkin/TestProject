/**
 * Простой обработчик необрабатываемых исключений потока
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.io.PrintStream;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Pavel Polushkin
 * @since 23.07.2014 (1.0)
 * @version 23.07.2014
 */
public class SimpleMessageUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * Количество элементов стека, которые следует выводить
     * пользователю @see StackTraceElement
     * default {@code level = 5}
     */
    protected int level = 5;

    /**
     * Стрим вывода сообщений
     */
    protected PrintStream ps = System.out;

    /**
     * Конструктор по умолчанию
     */
    public SimpleMessageUncaughtExceptionHandler() {}

    /**
     * Конструктор устанавливает сколько элементов стека выводить
     * при выборке сообщений из {@link StackTraceElement}
     * @param level количество элементов стека
     */
    public SimpleMessageUncaughtExceptionHandler(int level) {
        this.level = level;
    }

    /**
     * Конструктор устанавливает {@link PrintStream} для вывода сообщений пользователю
     * @param ps объект {@link PrintStream}
     */
    public SimpleMessageUncaughtExceptionHandler(PrintStream ps) {
        this.ps = ps;
    }

    /**
     * Конструктор устанавливает сколько элементов стека выводить
     * при выборке сообщений из {@code StackTraceElement}
     * также устанавливает {@link PrintStream} для вывода сообщений пользователю
     * @param level количество элементов стека
     * @param ps - объект {@link PrintStream}
     */
    public SimpleMessageUncaughtExceptionHandler(int level, PrintStream ps) {
        this.level = level;
        this.ps = ps;
    }

    /**
     * Действие при выбросе потоком исключения
     * @param t поток выбросивший исключение
     * @param e исключение выброшенное потоком, которое необходимо обработать
     */
    public void uncaughtException(Thread t, Throwable e) {
        ps.println(t.getName() + " - " + e.getClass());
        getMessage(e);
    }

    /**
     * Выводит сообщение об ошибке
     * Рекурсивный метод
     * @param e исключение по которому необходимо вывести сообщение
     */
    private void getMessage(Throwable e) {
        if(ArrayUtils.isEmpty(e.getStackTrace())) {
            /*
            Если стектрайс пуст, то выводим стектрейс исключения-причины
            */
            getCause(e);
            return;
        }
        /*
        Выводим ровно столько элементов стектрейса, сколько указано в #level
        */
        StackTraceElement ste;
        for(int i = 0; i < level; i++) {
            ste = e.getStackTrace()[i];
            if(ste == null)
                break;
            ps.println("Class: " + ste.getFileName() + ", Method: " + ste.getMethodName() + ", Line: " + ste.getLineNumber());
        }
        /*
        Выводим данные исключения, которое стало причиной возникновения текущего (если есть)
        Метод #getCause(Throwable) может снова вызвать #getMessage(Throwable)
        */
        getCause(e);
    }

    /**
     * Вывести сообщение по исключению-причине текущего исключения
     * @param e текущее исключение
     */
    private void getCause(Throwable e) {
        if(e.getCause() != null) {
            ps.print("Cause: ");
            getMessage(e.getCause());
        }
    }

}
