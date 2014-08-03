/**
  * Thread-safe класс для модификации массива
  */

package ru.ufa.pepolushkin.projectppeu.concurrency;

import java.util.Arrays;
import ru.ufa.pepolushkin.projectppeu.concurrency.atomic.PPEUAtomicIntegerArray;

/**
 * @author Pavel Polushkin
 * @since 03.08.2014 (1.0)
 * @version 03.08.2014
 */
public class ArrayModification {

    /**
     * Массив типа int, с которым необходимо проводить атомарные операции
     */
    private volatile int[] array;

    /**
     *  Конструктор принимает готовый массив
     *  @param array массив
     */
    public ArrayModification(int[] array) {
        this.array = array.clone();
    }

    /**
    * Добавляет элемент в конец массива
    * @param value элемент для добавления
    */
    public synchronized void add(int value) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = value;
    }

    /**
     * Добавляет элементы в конец массива
     * @param values элементы для добавления
     */
    public synchronized void addAll(int[] values) {
        int oldLenght = array.length;
        array = Arrays.copyOf(array, array.length + values.length);
        System.arraycopy(values, 0, array, oldLenght, values.length);
    }

    /**
     * Добавляет элемент в определенное место массива
     * @param elem номер нового элемента массива
     * @param value элемент для добавления
     */
    public void add(int elem, int value) {
        addAll(elem, new int[]{value});
    }

    /**
     * Добавляет элементы в определенное место массива
     * @param elem номер нового элемента массива
     * @param values элементы для добавления
     */
    public synchronized void addAll(int elem, int[] values) {
        int oldLenght = array.length;
        array = Arrays.copyOf(array, array.length + values.length);
        System.arraycopy(array, elem, array, elem + values.length, oldLenght - elem);
        System.arraycopy(values, 0, array, elem, values.length);
    }

    /**
     * Удаляет элемент из конца массива
     */
    public void remove() {
        removeAll(array.length - 1);
    }

    /**
     * Удаляет элемент из указанной позиции массива
     * @param i позиция элемента для удаления
     */
    public void remove(int i) {
        removeAll(i, 1);
    }

    /**
     * Удаляет все элементы до конца массива начиная с указанного
     * @param begin номер элемента с которого следует начать удаление
     */
    public void removeAll(int begin) {
        array = Arrays.copyOf(array, begin);
    }

    /**
     * Удаляет некоторое количество элеменов массива начиная с указанного
     * @param begin номер элемента с которого следует начать удаление
     * @param count количество элементов для удаления
     */
    public synchronized void removeAll(int begin, int count) {
        System.arraycopy(array, begin + count, array, begin, array.length - begin - count);
        array = Arrays.copyOf(array, array.length - count);
    }

    /**
     * Возвращает копию массива
     * @return копия массива
     */
    public int[] get() {
        return array.clone();
    }

    /**
     * Возвращает длину массива
     * @return длина массива
     */
    public int length() {
        return array.length;
    }

    /**
     * Возвращает объект PPEUAtomicIntegerArray на основе текущего массива
     * @return объект PPEUAtomicIntegerArray
     */
    public PPEUAtomicIntegerArray toPPEUAtomicIntegerArray() {
        int[] temp = get();
        return new PPEUAtomicIntegerArray(temp);
    }

}
