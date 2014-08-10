/*
 * Класс Аэропорт для решения задачи с самолетами
 * Данный аэропорт может поддерживать любое количество
 * полос, ангаров и посадочных шлюзов
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Pavel Polushkin
 * @since 10.08.2014 (1.0)
 * @version 10.08.2014
 */
public class AirportExtended implements Airport {

    // Взлетные полосы
    private final Plane[] runways;
    // Ангары где самолеты проходят обслуживание
    private final Plane[] hangars;
    // Посадочные шлюзы
    private final Plane[] gates;

    // Флаг показывает занятость ангара
    private Lock allHangarsLock = new ReentrantLock();
    private Condition allHangarsCond = allHangarsLock.newCondition();
    // Флаг показывает занятость шлюза
    private Lock allGatesLock = new ReentrantLock();
    private Condition allGatesCond = allGatesLock.newCondition();
    // Флаг показывает занятость полосы
    private Lock allRunwaysLock = new ReentrantLock();
    private Condition allRunwaysCond = allRunwaysLock.newCondition();

    public AirportExtended(int r, int h, int g) {
        if(r < 1 || h < 1 || g < 1)
            throw new IllegalArgumentException("Всех служб аэропорта должно быть не меньше одной");

        runways = new Plane[r];
        hangars = new Plane[h];
        gates = new Plane[g];
    }

    public AirportExtended() {
        this(2, 2, 2);
    }

    private int isAllBusy(Plane[] p) {
        synchronized(p) {
            for(int i = 0; i < p.length; i++) {
                if(p[i] == null) {
                    return i;
                }
            }
            return -1;
        }
    }

    private int findPlane(Plane[] ps, Plane p) {
        if(p == null) {
            throw new NullPointerException("Самолет не может быть null");
        }
        synchronized(ps) {
            for(int i = 0; i < ps.length; i++) {
                if(p.equals(ps[i])) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public void takeHangar(Plane p) throws InterruptedException {
        allHangarsLock.lock();
        try {
            int index;
            while((index = isAllBusy(hangars)) == -1) {
                allHangarsCond.await();
            }
            hangars[index] = p;
            System.out.println("Ангар " + index + " занят самолетом #" + p.getId());
        } finally {
            allHangarsLock.unlock();
        }
    }

    @Override
    public void releaseHangar(Plane p) {
        allHangarsLock.lock();
        try {
            int index = findPlane(hangars, p);
            if(index == -1) {
                throw new IllegalStateException("Покидающий ангар самолет в начале операции должен быть еще в ангаре");
            }
            allHangarsCond.signal();
            hangars[index] = null;
            System.out.println("Ангар " + index + " освобожден самолетом #" + p.getId());
        } finally {
            allHangarsLock.unlock();
        }
    }

    @Override
    public void takeGate(Plane p) throws InterruptedException {
        allGatesLock.lock();
        try {
            int index;
            while((index = isAllBusy(gates)) == -1) {
                allGatesCond.await();
            }
            gates[index] = p;
            System.out.println("Шлюз " + index + " занят самолетом #" + p.getId());
        } finally {
            allGatesLock.unlock();
        }
    }

    @Override
    public void releaseGate(Plane p) {
        allGatesLock.lock();
        try {
            int index = findPlane(gates, p);
            if(index == -1) {
                throw new IllegalStateException("Покидающий шлюз самолет в начале операции должен быть еще у шлюза");
            }
            allGatesCond.signal();
            gates[index] = null;
            System.out.println("Шлюз " + index + " освобожден самолетом #" + p.getId());
        } finally {
            allGatesLock.unlock();
        }
    }

    @Override
    public void takeRunway(Plane p) throws InterruptedException {
        allRunwaysLock.lock();
        try {
            int index;
            while((index = isAllBusy(runways)) == -1) {
                allRunwaysCond.await();
            }
            runways[index] = p;
            System.out.println("Полоса " + index + " занята самолетом #" + p.getId());
        } finally {
            allRunwaysLock.unlock();
        }
    }

    @Override
    public void releaseRunways(Plane p) {
        allRunwaysLock.lock();
        try {
            int index = findPlane(runways, p);
            if(index == -1) {
                throw new IllegalStateException("Покидающий полосу самолет в начале операции должен быть еще на полосе");
            }
            allRunwaysCond.signal();
            runways[index] = null;
            System.out.println("Полоса " + index + " освобождена самолетом #" + p.getId());
        } finally {
            allRunwaysLock.unlock();
        }
    }

}
