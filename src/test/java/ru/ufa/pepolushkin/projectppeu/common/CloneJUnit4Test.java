/*
 * Тесты клонирования объектов (JUnit 4)
 */

package ru.ufa.pepolushkin.projectppeu.common;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Pavel Polushkin
 * @since 04.08.2014 (1.0)
 * @version 04.08.2014
 */
public class CloneJUnit4Test {

    private StandartClone c;
    private ExtendedClone e;

    int value = 1;
    Integer[] array = new Integer[]{1,2,3};

    public CloneJUnit4Test() {
    }

    @Before
    public void setUp() {
        c = new StandartClone(value, array);
        e = new ExtendedClone(value, array);
    }

    @Test
    public void testStandartClone() {
        try {
            StandartClone sc = (StandartClone) c.clone();
            assertNotSame(sc, c);
            assertTrue(sc.equals(c));
            int i = c.getValue();
            int j = sc.getValue();
            assertSame(i, j);
            assertTrue(i == j);
            Integer ii[] = c.getArray();
            Integer jj[] = sc.getArray();
            assertTrue(Arrays.deepEquals(jj, jj));
            assertSame(ii, jj);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CloneJUnit4Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testExtendedClone() {
        try {
            ExtendedClone sc = (ExtendedClone) e.clone();
            assertNotSame(sc, e);
            assertTrue(sc.equals(e));
            int i = e.getValue();
            int j = sc.getValue();
            assertSame(i, j);
            assertTrue(i == j);
            Integer ii[] = e.getArray();
            Integer jj[] = sc.getArray();
            assertTrue(Arrays.deepEquals(jj, jj));
            assertNotSame(ii, jj);
            List<Integer> li = e.getList();
            List<Integer> ji = sc.getList();
            assertTrue(li.equals(ji));
            assertNotSame(li, ji);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CloneJUnit4Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
