package com.eli;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Representa el estado de los dos jarros: cantidad de agua en X y en Y.
 */
public class State<T> extends ArrayList<T> {

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof State s) {
            return s.toString().equals(this.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%s)", String.join(",", this.stream().map(i -> i.toString()).toList()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public State<T> clone() {
        var clone = new State<T>();
        for (var item : this) {
            try {
                clone.add((T) item.getClass().getMethod("clone").invoke(item));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                clone.add(item);
                IO.println("No se pudo clonar el item: " + item);
            }
        }
        return clone;
    }
}
