/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.common.cassa;

/**
 *
 * @author Pavel Polushkin
 */
public class Customer {

    private final int serviceTime;
    
    public Customer(int time) {
        serviceTime = time;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }

}
