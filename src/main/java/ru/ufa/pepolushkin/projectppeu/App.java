package ru.ufa.pepolushkin.projectppeu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.ufa.pepolushkin.projectppeu.concurrency.AirportExtended;
import ru.ufa.pepolushkin.projectppeu.concurrency.AirportSimple;
import ru.ufa.pepolushkin.projectppeu.concurrency.Plane;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //AirportSimple a = new AirportSimple();
        AirportExtended a = new AirportExtended();
        ExecutorService e = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++) {
            e.execute(new Thread(new Plane(a)));
        }
        e.shutdown();
    }
}
