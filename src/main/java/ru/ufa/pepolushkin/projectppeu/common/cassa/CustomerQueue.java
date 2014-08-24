/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.common.cassa;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Pavel Polushkin
 */
public class CustomerQueue extends ArrayBlockingQueue<Customer> {

    public CustomerQueue(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if(this.size() == 0) {
            return "[Пусто]";
        }
        StringBuilder result = new StringBuilder();
        for(Customer c : this) {
            result.append(c);
        }
        return result.toString();
    }

}
