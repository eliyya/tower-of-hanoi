package com.eli;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo del árbol de estados usado por la búsqueda en anchura. Cada nodo
 * contiene un `State` (estado de los dos jarros), una referencia al
 * nodo padre (para reconstruir el camino) y la acción que generó este
 * estado desde su padre.
 */
public class Node {
    private State<State<Integer>> state = new State<>();
    private ArrayList<Node> childrens = new ArrayList<Node>();
    private Node parent;
    private String action = "Inicio";

    public State<State<Integer>> state() {
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

    public Node() {
        this.state = new State<>();
        this.state.add(new State<>());
        this.state.add(new State<>());
        this.state.add(new State<>());
    }

    public Node(State<State<Integer>> state) {
        this.state = state;
    }

    public Node pasar(int x, int y) {
        var t1 = this.state.get(x);
        var t2 = this.state.get(y);
        var d1 = t1.getLast();
        if (t2.isEmpty() || d1 < t2.getLast()) {
            var newState = this.state.clone();
            newState.get(y).add(newState.get(x).removeLast());
            return new Node(newState).setParent(this).setAction(String.format("pasar %d a %d", x+1, y+1));
        }
        throw new IllegalStateException();
    }

    public List<Node> generateChildrens() {
        /**
         * Genera todos los hijos válidos de este nodo aplicando las
         * operaciones posibles (vaciar, llenar, verter) y devuelve la lista
         * con los nodos hijos.
         *
         * @return lista de hijos generados
         */
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                try {
                    var child = pasar(x, y);
                    childrens.add(child);
                } catch (Throwable e) {
                    // ignore
                }
            }
        }
        return childrens;
    }

    @Override
    public String toString() {
        return String.format("%s  \t -> %s", this.action, state.toString());
    }
}
