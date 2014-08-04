/*
 * Клонирование вызовом суперметода {@link #Object.clone()}
 */

package ru.ufa.pepolushkin.projectppeu.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Pavel Polushkin
 * @since 03.08.2014 (1.0)
 * @version 04.08.2014
 */
public class StandartClone implements Cloneable {

    private int value;
    private Integer[] array;
    private List<Integer> list;

    public StandartClone(int value, Integer[] array) {
        this.value = value;
        Integer[] arr = array.clone();
        this.array = arr;
        list = new ArrayList<>(Arrays.asList(arr));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getValue() {
        return value;
    }

    public Integer[] getArray() {
        return array;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StandartClone other = (StandartClone) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Arrays.deepEquals(this.array, other.array)) {
            return false;
        }
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }



}
