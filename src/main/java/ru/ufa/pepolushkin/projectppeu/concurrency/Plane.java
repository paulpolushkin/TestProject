/*
 * Класс Самолет для решения задачи с самолетами
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Pavel Polushkin
 * @since 09.08.2014 (1.0)
 * @version 10.08.2014
 */
public class Plane implements Runnable {

    private static int seq = 0;
    private final int id = seq++;
    private final Airport port;

    private final Random r = new Random();

    public Plane(Airport a) {
        port = a;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println("Самолет #" + id + " выкатился с резервной полосы");
        System.out.println("Самолет #" + id + " пытается захватить ангар");
        try {
            port.takeHangar(this);
            System.out.println("Самолет #" + id + " захватил ангар");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            port.releaseHangar(this);
            System.out.println("Самолет #" + id + " освободил ангар");
            System.out.println("Самолет #" + id + " пытается начать посадку пассажиров");
            port.takeGate(this);
            System.out.println("Самолет #" + id + " производит посадку пассажиров");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            port.releaseGate(this);
            System.out.println("Посадка пассажиров на самолет #" + id + " завершена");
            System.out.println("Самолет #" + id + " пытается начать взлет");
            port.takeRunway(this);
            System.out.println("Самолет #" + id + " производит взлет");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            port.releaseRunways(this);
            System.out.println("Самолет #" + id + " освободил полосу");
            System.out.println("Самолет #" + id + " находится в полете");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            System.out.println("Самолет #" + id + " пытается начать посадку");
            port.takeRunway(this);
            System.out.println("Самолет #" + id + " производит посадку");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            port.releaseRunways(this);
            System.out.println("Самолет #" + id + " освободил полосу");
            System.out.println("Самолет #" + id + " пытается начать высадку пассажиров");
            port.takeGate(this);
            System.out.println("Самолет #" + id + " производит высадку пассажиров");
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            port.releaseGate(this);
            System.out.println("Высадка пассажиров с самолета #" + id + " завершена");
            System.out.println("Самолет #" + id + " отправлен на резервную полосу до следующего полета");
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plane other = (Plane) obj;
        return this.id == other.id;
    }

}
