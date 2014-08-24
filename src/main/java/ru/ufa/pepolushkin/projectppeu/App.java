package ru.ufa.pepolushkin.projectppeu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import ru.ufa.pepolushkin.projectppeu.common.cassa.CustomerGenerator;
import ru.ufa.pepolushkin.projectppeu.common.cassa.CustomerQueue;
import ru.ufa.pepolushkin.projectppeu.common.cassa.TellerManager;
import ru.ufa.pepolushkin.projectppeu.concurrency.AirportExtended;
import ru.ufa.pepolushkin.projectppeu.concurrency.AirportSimple;
import ru.ufa.pepolushkin.projectppeu.concurrency.Plane;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        // Аэропорт
        //AirportSimple a = new AirportSimple();
        /*AirportExtended a = new AirportExtended();
        ExecutorService e = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++) {
            e.execute(new Thread(new Plane(a)));
        }
        e.shutdown();*/


        // Кассир
        final int adPeriod = 1000;
        final int maxQueueSize = 50;
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerQueue queue = new CustomerQueue(maxQueueSize);
        exec.execute(new CustomerGenerator(queue));
        exec.execute(new TellerManager(exec, queue, adPeriod));

        TimeUnit.SECONDS.sleep(20);

        exec.shutdownNow();
    }
}
