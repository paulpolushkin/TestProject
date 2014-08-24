/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.common.cassa;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pavel Polushkin
 */
public class Teller implements Runnable, Comparable<Teller> {

    private static int counter = 0;
    private final int id = counter++;
    private int customersServed = 0;
    private volatile CustomerQueue queue;
    private boolean servingCustomerQueue = true;

    public Teller(CustomerQueue q) {
        queue = q;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Customer cust = queue.take();
                TimeUnit.MILLISECONDS.sleep(cust.getServiceTime());
                synchronized(this) {
                    customersServed++;
                    while(!servingCustomerQueue) {
                        wait();
                    }
                }
            }
        } catch(InterruptedException e) {
            System.out.println(this + " interrupted!");
        }
        System.out.println(this + " terminated!");
    }

    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerQueue = false;
    }

    public synchronized void serveCustomers() {
        assert !servingCustomerQueue : "already serving: " + this;
        servingCustomerQueue = true;
        notifyAll();
    }

    @Override
    public synchronized int compareTo(Teller o) {
        return -(customersServed - o.customersServed);
    }

    @Override
    public String toString() {
        return "Кассир " + id + " ";
    }

    public String shortString() {
        return "К" + id + "-" + customersServed;
    }

}
