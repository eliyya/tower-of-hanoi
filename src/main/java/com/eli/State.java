package com.eli;

/**
 * Representa el estado de los dos jarros: cantidad de agua en X y en Y.
 */
public class State {
    private int x;
    private int y;

    /** Devuelve la cantidad de agua en el jarro X. */
    public int x() {
        return x;
    }

    /** Devuelve la cantidad de agua en el jarro Y. */
    public int y() {
        return y;
    }

    /** Crea un estado con cantidades específicas en X e Y. */
    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Crea un estado vacío por defecto. */
    public State() {
    }

    /**
     * Devuelve un nuevo objeto State con X cambiado (inmutable respecto al
     * objeto original).
     */
    State setX(int x) {
        return new State(x, y);
    }

    /**
     * Devuelve un nuevo objeto State con Y cambiado (inmutable respecto al
     * objeto original).
     */
    State setY(int y) {
        return new State(x, y);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof State s) {
            return s.x() == this.x() && s.y() == this.y();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
