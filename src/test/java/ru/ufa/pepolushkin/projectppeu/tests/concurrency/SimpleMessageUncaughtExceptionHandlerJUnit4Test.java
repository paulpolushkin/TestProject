/*
 * Тесты для SimpleMessageUncaughtExceptionHandler (JUnit 4)
 */

package ru.ufa.pepolushkin.projectppeu.tests.concurrency;

import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import ru.ufa.pepolushkin.projectppeu.concurrency.SimpleMessageUncaughtExceptionHandler;

/**
 * @author Pavel Polushkin
 * @since 04.08.2014 (1.0)
 * @version 04.08.2014
 */
public class SimpleMessageUncaughtExceptionHandlerJUnit4Test {
    
    Thread t1;
    Thread t2;
    Thread t3;
    Thread t4;
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    
    public SimpleMessageUncaughtExceptionHandlerJUnit4Test() {
    }
    
    @Before
    public void setUp() {
        t1 = new Thread() {
            @Override
            public void run() {
                throw new NullPointerException();
            }
        };
        
        t2 = new Thread() {
            @Override
            public void run() {
                throw new IllegalStateException();
            }
        };
        
        t3 = new Thread() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        };
        
        t4 = new Thread() {
            @Override
            public void run() {
                throw new IllegalArgumentException();
            }
        };
    }
    
    @Test
    public void test() throws InterruptedException {
        //Thread.setDefaultUncaughtExceptionHandler(new SimpleMessageUncaughtExceptionHandler());
        t1.setUncaughtExceptionHandler(new SimpleMessageUncaughtExceptionHandler());
        TimeUnit.SECONDS.sleep(1);
        t1.start();
        t2.start();
        t3.getThreadGroup().uncaughtException(t3, new RuntimeException());
        Thread.setDefaultUncaughtExceptionHandler(new SimpleMessageUncaughtExceptionHandler());
        t4.getThreadGroup().uncaughtException(t4, new IllegalArgumentException());
        //t2.getThreadGroup().uncaughtException(t2, new ThreadDeath());
        
    }
    
}
