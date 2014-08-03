/*
 * Тесты для PPEUAtomicIntegerArray (JUnit 3)
 */

package ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic;

import java.util.Arrays;
import junit.framework.TestCase;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicIntegerArray;

/**
 * @author Pavel Polushkin
 * @since 28.07.2014 (1.0)
 * @version 03.08.2014
 */
public class PPEUAtomicIntegerArrayJUnit3Test extends TestCase {

    int[] array = new int[] {1,2,3,4,5,6,7,8,9,10};

    PPEUAtomicIntegerArray atom1;
    PPEUAtomicIntegerArray atom2;

    public PPEUAtomicIntegerArrayJUnit3Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        atom1 = new PPEUAtomicIntegerArray(3);
        atom2 = new PPEUAtomicIntegerArray(array);
    }

    public void testGetArray() {
        assertNotNull(atom1.get());
        assertNotNull(atom2.get());
        assertEquals(atom1.get().length, 3);
        assertEquals(atom2.get().length, array.length);
        assertFalse(atom2.get() == array);
        assertNotSame(atom2.get(), array);
        assertTrue(Arrays.equals(atom2.get(), array));
    }

    public void testLengthArray() {
        assertEquals(atom1.length(), 3);
        assertEquals(atom2.length(), array.length);
    }

    public void testGetElem() {
        assertSame(atom1.get(0), 0);
        assertSame(atom2.get(0), 1);
    }

    public void testSetElem() {
        atom2.set(0, 10);
        assertSame(atom2.get(0), 10);
    }

    public void testAdd() {
        int i = atom2.get(1);
        atom2.add(1, 1);
        assertSame(atom2.get(1), i+1);
    }

    public void testIncrement() {
        int i = atom2.get(2);
        atom2.increment(2);
        assertSame(atom2.get(2), i+1);
    }

    public void testDecrement() {
        int i = atom2.get(3);
        atom2.decrement(3);
        assertSame(atom2.get(3), i-1);
    }

    public void testGetAndSet() {
        int i = atom2.get(4);
        int res = atom2.getAndSet(4, 20);
        assertSame(res, i);
        assertSame(atom2.get(4), 20);
    }

    public void testAddAndGet() {
        int i = atom2.get(5);
        int res = atom2.addAndGet(5, 11);
        assertSame(res, i+11);
        assertSame(atom2.get(5), res);
    }

    public void testGetAndAdd() {
        int i = atom2.get(6);
        int res = atom2.getAndAdd(6, 3);
        assertSame(res, i);
        assertSame(atom2.get(6), res+3);
    }

    public void testIncrementAndGet() {
        int i = atom2.get(7);
        int res = atom2.incrementAndGet(7);
        assertSame(res, i+1);
        assertSame(atom2.get(7), res);
    }

    public void testGetAndIncrement() {
        int i = atom2.get(8);
        int res = atom2.getAndIncrement(8);
        assertSame(res, i);
        assertSame(atom2.get(8), res+1);
    }

    public void testDecrementAndGet() {
        int i = atom2.get(9);
        int res = atom2.decrementAndGet(9);
        assertSame(res, i-1);
        assertSame(atom2.get(9), res);
    }

    public void testAddAndDecrement() {
        int i = atom2.get(0);
        int res = atom2.addAndDecrement(0);
        assertSame(res, i);
        assertSame(atom2.get(0), res-1);
    }

    public void testGetArrayModification() {
        assertNotSame(atom2.getArrayModification(), atom2.getArrayModification());
    }

}
