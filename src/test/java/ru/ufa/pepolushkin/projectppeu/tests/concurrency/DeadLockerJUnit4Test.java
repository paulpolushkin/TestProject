/*
 * Тесты для DeadLocker (JUnit 4)
 */

package ru.ufa.pepolushkin.projectppeu.tests.concurrency;

import org.junit.Before;
import org.junit.Test;
import ru.ufa.pepolushkin.projectppeu.concurrency.DeadLocker;
import static org.junit.Assert.*;

/**
 * @author Pavel Polushkin
 * @since 29.07.2014 (1.0)
 * @version 29.07.2014
 */
public class DeadLockerJUnit4Test {

    public DeadLockerJUnit4Test() {
    }

    @Before
    public void setUp() {
        DeadLocker.callDeadLock();
    }

    /**
     * Ставим задержку 10 секунда,
     * чтобы потоки успели войти во взаимную блокировку
     * и посидеть там некоторое время к моменту запуска теста
     */
    @Test(timeout = 10000)
    public void deadLock() {
        assertNotNull(DeadLocker.getT1());
        assertNotNull(DeadLocker.getT1());
        assertFalse(DeadLocker.getT1().isInterrupted());
        assertFalse(DeadLocker.getT2().isInterrupted());
        assertFalse(DeadLocker.getT1().getState() == Thread.State.TERMINATED);
        assertFalse(DeadLocker.getT2().getState() == Thread.State.TERMINATED);
        assertTrue(DeadLocker.getT1().isAlive());
        assertTrue(DeadLocker.getT2().isAlive());
    }

}
