package ru.ufa.pepolushkin.projectppeu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicIntegerArrayJUnitTest3;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicIntegerArrayJUnitTest4;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicReferenceJUnitTest4;

/**
 * @author Pavel Polushkin
 * @since 23.07.2014 (1.0)
 * @version 28.07.2014
 */

@Suite.SuiteClasses( {  PPEUAtomicIntegerArrayJUnitTest3.class,
                        PPEUAtomicIntegerArrayJUnitTest4.class,
                        PPEUAtomicReferenceJUnitTest4.class
                    } )
@RunWith(Suite.class)
public class AppTest
{
}
