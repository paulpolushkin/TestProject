/*
 * Класс Аэропорт для решения задачи с самолетами
 * Пусть у нас есть небольшой сельский аэропорт с одной полосой,
 * одним ангаром и одним рукавом для посадки/высадки пассажиров.
 * Первоначально самолеты находятся где-то на резервной полосе.
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

/**
 * @author Pavel Polushkin
 * @since 09.08.2014 (1.0)
 * @version 10.08.2014
 */
public class AirportSimple implements Airport {

    // Взлетная полоса
    final private Object runway = new Object();
    // Ангар где самолеты проходят обслуживание
    final private Object hangar = new Object();
    // Посадочный шлюз
    final private Object gate = new Object();

    // Флаг показывает занятость ангара
    private boolean hangarIsBusy = false;
    // Флаг показывает занятость шлюза
    private boolean gateIsBusy = false;
    // Флаг показывает занятость полосы
    private boolean runwayIsBusy = false;

    @Override
    public void takeHangar(Plane p) throws InterruptedException {
        synchronized(hangar) {
            while(hangarIsBusy) {
                hangar.wait();
            }
            hangarIsBusy = true;
            System.out.println("Ангар занят самолетом #" + p.getId());
        }
    }

    @Override
    public void releaseHangar(Plane p) {
        synchronized(hangar) {
            while(hangarIsBusy) {
                hangarIsBusy = false;
                hangar.notify();
                System.out.println("Ангар освобожден самолетом #" + p.getId());
            }
        }
    }

    @Override
    public void takeGate(Plane p) throws InterruptedException {
        synchronized(gate) {
            while(gateIsBusy) {
                gate.wait();
            }
            gateIsBusy = true;
            System.out.println("Шлюз занят самолетом #" + p.getId());
        }
    }

    @Override
    public void releaseGate(Plane p) {
        synchronized(gate) {
            while(gateIsBusy) {
                gateIsBusy = false;
                gate.notify();
                System.out.println("Шлюз освобожден самолетом #" + p.getId());
            }
        }
    }

    @Override
    public void takeRunway(Plane p) throws InterruptedException {
        synchronized(runway) {
            while(runwayIsBusy) {
                runway.wait();
            }
            runwayIsBusy = true;
            System.out.println("Полоса занята самолетом #" + p.getId());
        }
    }

    @Override
    public void releaseRunways(Plane p) {
        synchronized(runway) {
            while(runwayIsBusy) {
                runwayIsBusy = false;
                runway.notify();
                System.out.println("Полоса освобождена самолетом #" + p.getId());
            }
        }
    }

}
