/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.common.cassa;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pavel Polushkin
 */
public class TellerManager implements Runnable {

    private ExecutorService es;
    private volatile CustomerQueue queue;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
    private int adjustmentPeriod;
    private Random rand = new Random();

    public TellerManager(ExecutorService es, CustomerQueue queue, int adjustmentPeriod) {
        this.es = es;
        this.queue = queue;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller t = new Teller(queue);
        es.execute(t);
        workingTellers.add(t);
    }

    private void adjustTellerNumber() {
        if(queue.size() / workingTellers.size() > 2) {
            if(tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomers();
                workingTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(queue);
            es.execute(teller);
            workingTellers.add(teller);
            return;
        }
        if(workingTellers.size() > 1
                && queue.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        if(queue.size() == 0) {
            while(workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(queue + " {");
                for(Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch(InterruptedException e) {
            System.out.println(this + " interrupted!");
        }
        System.out.println(this + " terminated!");
    }

    @Override
    public String toString() {
        return "TellerManager";
    }

}
