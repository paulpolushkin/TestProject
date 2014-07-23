/**
 * Простой обработчик необрабатываемых исключений потока
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.io.PrintStream;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Pavel Polushkin
 * @since 1.0
 * @version 23.07.2014
 */
public class SimpleMessageUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * Глубина стека, на которую следует спускаться
     * при выборке сообщений из {@code StackTraceElement}
     * default {@code level = 5}
     */
    protected int level = 5;
    
    /**
     * Стрим вывода сообщений
     * default {@code ps = System.out)
     */
    protected PrintStream ps = System.out;
    
    /**
     * Конструктор по умолчанию
     */
    public SimpleMessageUncaughtExceptionHandler() {}
    
    /**
     * Конструктор с параметром
     * @param level устанавливает глубину стека, на которую следует спускаться
     * при выборке сообщений из {@code StackTraceElement}
     */
    public SimpleMessageUncaughtExceptionHandler(int level) {
        this.level = level;
    }
    
    /**
     * Конструктор с параметром
     * @param ps устанавливает стрим для вывода сообщений пользователю
     */
    public SimpleMessageUncaughtExceptionHandler(PrintStream ps) {
        this.ps = ps;
    }
    
    /**
     * Конструктор с параметрами
     * @param level устанавливает глубину стека, на которую следует спускаться
     * при выборке сообщений из {@code StackTraceElement}
     * @param ps - устанавливает стрим для вывода сообщений пользователю
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
     * Рекурсивный метод
     * @param e 
     */
    private void getMessage(Throwable e) {
        if(ArrayUtils.isEmpty(e.getStackTrace())) {
            return;
        }
        StackTraceElement ste = e.getStackTrace()[0];
        ps.println("Class: " + ste.getFileName() + ", Method: " + ste.getMethodName() + ", Line: " + ste.getLineNumber());
        if(e.getCause() != null) {
            ps.print("Cause: ");
            getMessage(e.getCause());
        }
   }
    
}
