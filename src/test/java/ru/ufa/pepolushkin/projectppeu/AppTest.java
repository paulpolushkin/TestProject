package ru.ufa.pepolushkin.projectppeu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.ufa.pepolushkin.projectppeu.common.CloneJUnit4Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.DeadLockerJUnit4Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic.ArrayModificationJUnit3Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic.ArrayModificationJUnit4Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic.PPEUAtomicIntegerArrayJUnit3Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic.PPEUAtomicIntegerArrayJUnit4Test;
import ru.ufa.pepolushkin.projectppeu.tests.concurrency.atomic.PPEUAtomicReferenceJUnit4Test;

/**
 * @author Pavel Polushkin
 * @since 23.07.2014 (1.0)
 * @version 04.08.2014
 */

@Suite.SuiteClasses( {  PPEUAtomicIntegerArrayJUnit3Test.class,
                        PPEUAtomicIntegerArrayJUnit4Test.class,
                        PPEUAtomicReferenceJUnit4Test.class,
                        ArrayModificationJUnit3Test.class,
                        ArrayModificationJUnit4Test.class,

                        DeadLockerJUnit4Test.class,

                        CloneJUnit4Test.class
                    } )
@RunWith(Suite.class)
public class AppTest
{
}
