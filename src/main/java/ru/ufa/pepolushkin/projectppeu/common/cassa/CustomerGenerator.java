/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.common.cassa;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pavel Polushkin
 */
public class CustomerGenerator implements Runnable {

    private volatile CustomerQueue queue;
    private static Random rand = new Random();

    public CustomerGenerator(CustomerQueue q) {
        queue = q;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
                queue.put(new Customer(rand.nextInt(200)));
            }
        } catch(InterruptedException e) {
            System.out.println("CustomerGenerator interrupted!");
        }
        System.out.println("CustomerGenerator terminated!");
    }

}
