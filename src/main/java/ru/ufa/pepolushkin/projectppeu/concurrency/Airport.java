/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.projectppeu.concurrency;

/**
 *
 * @author Pavel Polushkin
 */
public interface Airport {

     /**
     * Захватить ангар
     * @param p самолет захвативший ангар
     * @throws InterruptedException
     */
    public void takeHangar(Plane p) throws InterruptedException;

    /**
     * Освободить ангар
     * @param p самолет освободивший ангар
     */
    public void releaseHangar(Plane p);

    /**
     * Захватить шлюз
     * @param p самолет захвативший шлюз
     * @throws InterruptedException
     */
    public void takeGate(Plane p) throws InterruptedException;

    /**
     * Освободить шлюз
     * @param p самолет освободивший шлюз
     */
    public void releaseGate(Plane p);

    /**
     * Захватить полосу
     * @param p самолет захвативший полосу
     * @throws InterruptedException
     */
    public void takeRunway(Plane p) throws InterruptedException;

    /**
     * Освободить полосу
     * @param p самолет освободивший полосу
     */
    public void releaseRunways(Plane p);

}
