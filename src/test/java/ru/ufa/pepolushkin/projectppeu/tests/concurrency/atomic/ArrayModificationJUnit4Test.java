/*
 * Тесты для ArrayModification (JUnit 4)
 */

package ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.ufa.pepolushkin.projectppeu.concurrency.ArrayModification;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicIntegerArray;

/**
 * @author Pavel Polushkin
 * @since 03.08.2014 (1.0)
 * @version 03.08.2014
 */
public class ArrayModificationJUnit4Test {

    static int[] array = new int[] {1,2,3,4,5,6,7,8,9,10};

    ArrayModification mod;

    public ArrayModificationJUnit4Test() {
    }

    @Before
    public void setUp() {
        mod = new ArrayModification(array);
    }

    @Test
    public void testGet() {
        int[] i = mod.get();
        assertNotSame(i, array);
        assertArrayEquals(i, array);
    }

    @Test
    public void testLength() {
        assertEquals(mod.length(), 10);
        assertEquals(mod.length(), array.length);
    }

    @Test
    public void testToPPEUAtomicIntegerArray() {
        PPEUAtomicIntegerArray p1 = mod.toPPEUAtomicIntegerArray();
        assertNotNull(p1);
        PPEUAtomicIntegerArray p2 = new PPEUAtomicIntegerArray(array);
        assertArrayEquals(p1.get(), p2.get());
    }

    @Test
    public void testAddToEndElem() {
        int[] i = mod.get();
        mod.add(11);
        assertTrue(mod.length() == 11);
        assertSame(mod.length(), i.length + 1);
    }

    @Test
    public void testAddAllToEndElems() {
        int[] i = mod.get();
        int[] j = new int[]{22,33,44};
        mod.addAll(j);
        assertTrue(mod.length() == 13);
        assertSame(mod.length(), i.length + j.length);
        int[] k = new int[3];
        System.arraycopy(mod.get(), i.length, k, 0, j.length);
        assertArrayEquals(j, k);
    }

    @Test
    public void testAddElem() {
        int[] i = mod.get();
        mod.add(1,12);
        assertTrue(mod.length() == 11);
        assertSame(mod.length(), i.length + 1);
    }

    @Test
    public void testAddAllElems() {
        int[] i = mod.get();
        int[] j = new int[]{77,88};
        mod.addAll(2,j);
        assertTrue(mod.length() == 12);
        assertSame(mod.length(), i.length + j.length);
        int[] k = new int[2];
        System.arraycopy(mod.get(), 2, k, 0, j.length);
        assertArrayEquals(j, k);
    }

    @Test
    public void testRemoveFromEndElem() {
        int[] i = mod.get();
        mod.remove();
        assertSame(mod.length(), i.length - 1);
    }

    @Test
    public void testRemoveAllFromEndElems() {
        mod.removeAll(2);
        assertSame(mod.length(), 2);
    }

    @Test
    public void testRemoveElem() {
        int[] i = mod.get();
        mod.remove(0);
        assertSame(mod.length(), i.length - 1);
    }

    @Test
    public void testRemoveAllElems() {
        int[] i = mod.get();
        int[] j = new int[]{77,88};
        mod.removeAll(2, j.length);
        assertSame(mod.length(), i.length - j.length);
    }

}
