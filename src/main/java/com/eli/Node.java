package com.eli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nodo del árbol de estados usado por la búsqueda en anchura. Cada nodo
 * contiene un `State` (estado de los dos jarros), una referencia al
 * nodo padre (para reconstruir el camino) y la acción que generó este
 * estado desde su padre.
 */
public class Node {
    private State state = new State();
    private ArrayList<Node> childrens = new ArrayList<Node>();
    private Node parent;
    private String action = "Inicio";

    public State state() {
        return state;
    }

    public Node parent() {
        return parent;
    }

    private Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    private Node setAction(String action) {
        this.action = action;
        return this;
    }

    public Node(int x, int y) {
        this.state = new State(x, y);
    }

    public Node(State state) {
        this.state = state;
    }

    public Node vaciarX() {
        /**
         * Vacía completamente el jarro X y devuelve el nuevo nodo resultante.
         *
         * @return nuevo `Node` con X = 0
         * @throws Error si X ya está vacío
         */
        if (state.x() > 0) {
            return new Node(state.setX(0)).setParent(this).setAction("Vaciar x");
        } else {
            throw new Error("X no contiene algo");
        }
    }

    public Node vaciarY() {
        /**
         * Vacía completamente el jarro Y y devuelve el nuevo nodo resultante.
         *
         * @return nuevo `Node` con Y = 0
         * @throws Error si Y ya está vacío
         */
        if (state.y() > 0) {
            return new Node(state.setY(0)).setParent(this).setAction("Vaciar y");
        } else {
            throw new Error("Y no contiene algo");
        }
    }

    public Node llenarX() {
        /**
         * Llena completamente el jarro X (capacidad 3) y devuelve el nuevo
         * nodo.
         *
         * @return nuevo `Node` con X = 3
         * @throws Error si X ya está lleno
         */
        if (state.x() < 3) {
            return new Node(state.setX(3)).setParent(this).setAction("llenar x");
        } else {
            throw new Error("x está lleno");
        }
    }

    public Node llenarY() {
        /**
         * Llena completamente el jarro Y (capacidad 4) y devuelve el nuevo
         * nodo.
         *
         * @return nuevo `Node` con Y = 4
         * @throws Error si Y ya está lleno
         */
        if (state.y() < 4) {
            return new Node(state.setY(4)).setParent(this).setAction("llenar y");
        } else {
            throw new Error("y está lleno");
        }
    }

    public Node pasarXaY() {
        /**
         * Vierte agua desde X hacia Y hasta que X esté vacío o Y esté lleno.
         *
         * @return nuevo `Node` con los valores ajustados de X e Y
         * @throws Error si X está vacío o Y está lleno
         */
        if (state.x() == 0) {
            throw new Error("X no contiene nada");
        }
        if (state.y() == 4) {
            throw new Error("Y está lleno");
        }
        int ny = state.x() + state.y();
        int nx = 0;
        if (ny > 4) {
            nx = ny % 4;
            ny = 4;
        }
        return new Node(new State(nx, ny)).setParent(this).setAction("pasar x a y");
    }

    public Node pasarYaX() {
        /**
         * Vierte agua desde Y hacia X hasta que Y esté vacío o X esté lleno.
         *
         * @return nuevo `Node` con los valores ajustados de X e Y
         * @throws Error si Y está vacío o X está lleno
         */
        if (state.y() == 0) {
            throw new Error("Y no contiene nada");
        }
        if (state.x() == 3) {
            throw new Error("X está lleno");
        }
        int nx = state.x() + state.y();
        int ny = 0;
        if (nx > 3) {
            ny = nx % 3;
            nx = 3;
        }
        return new Node(new State(nx, ny)).setParent(this).setAction("pasar y a x");
    }

    public List<Node> generateChildrens() {
        /**
         * Genera todos los hijos válidos de este nodo aplicando las
         * operaciones posibles (vaciar, llenar, verter) y devuelve la lista
         * con los nodos hijos.
         *
         * @return lista de hijos generados
         */
        try {
            childrens.add(vaciarX());
        } catch (Throwable t) {
        }
        try {
            childrens.add(vaciarY());
        } catch (Throwable t) {
        }
        try {
            childrens.add(llenarX());
        } catch (Throwable t) {
        }
        try {
            childrens.add(llenarY());
        } catch (Throwable t) {
        }
        try {
            childrens.add(pasarXaY());
        } catch (Throwable t) {
        }
        try {
            childrens.add(pasarYaX());
        } catch (Throwable t) {
        }
        return childrens;
    }

    @Override
    public String toString() {
        return String.format("%s  \t -> %s", this.action, state.toString());
    }

    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("", state.toString());
        this.childrens.forEach(c -> {
            map.put(c.action, c.toMap());
        });
        return map;
    }
}
