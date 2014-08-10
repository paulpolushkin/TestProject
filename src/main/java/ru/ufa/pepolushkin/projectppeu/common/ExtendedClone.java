/*
 * Клонирование всех полей класса
 */

package ru.ufa.pepolushkin.projectppeu.common;

import java.util.ArrayList;

/**
 * @author Pavel Polushkin
 * @since 04.08.2014 (1.0)
 * @version 10.08.2014
 */
public class ExtendedClone extends StandartClone {

    public ExtendedClone(int value, Integer[] array) {
        super(value, array);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ExtendedClone ec = (ExtendedClone) super.clone();
        ec.setValue(super.getValue());
        ec.setArray(super.getArray().clone());
        ec.setList(new ArrayList<>(super.getList()));
        return ec;
    }

}
