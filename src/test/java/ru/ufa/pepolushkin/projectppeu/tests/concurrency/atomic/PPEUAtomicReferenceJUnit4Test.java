/*
 * Тесты для PPEUAtomicReference (JUnit 4)
 */

package ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic;

import org.junit.Before;
import org.junit.Test;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicReference;
import static org.junit.Assert.*;

/**
 * @author Pavel Polushkin
 * @since 28.07.2014 (1.0)
 * @version 29.07.2014
 */
public class PPEUAtomicReferenceJUnit4Test {

    private PPEUAtomicReference<String> ref1;
    private PPEUAtomicReference<String> ref2;
    private PPEUAtomicReference<String> ref3;

    public PPEUAtomicReferenceJUnit4Test() {
    }

    @Before
    public void setUp() {
        ref1 = new PPEUAtomicReference<>();
        ref2 = new PPEUAtomicReference<>("String");
        ref3 = new PPEUAtomicReference<>(new String("String"));
    }

    @Test
    public void get() {
        String str = "String";
        assertNull(ref1.get());
        assertNotNull(ref2.get());
        assertNotNull(ref3.get());
        assertSame(str, ref2.get());
        assertNotSame(str, ref3.get());
        assertNotSame(ref2.get(), ref3.get());
        assertEquals(str, ref2.get());
        assertEquals(str, ref3.get());
        assertEquals(ref2.get(), ref3.get());
    }

    @Test
    public void set() {
        ref1.set("1");
        assertEquals(ref1.get(), "1");
        assertEquals(ref1.get(), new String("1"));
        assertSame(ref1.get(), "1");
        assertNotSame(ref1.get(), new String("1"));
    }

    @Test
    public void getAndSet() {
        String s1 = ref1.getAndSet("1");
        assertNull(s1);
        assertNotSame(s1, ref1.get());
        assertEquals("1", ref1.get());
        String s2 = ref2.getAndSet("2");
        assertNotNull(s2);
        assertNotSame(s2, ref2.get());
        assertEquals("2", ref2.get());
    }

}
