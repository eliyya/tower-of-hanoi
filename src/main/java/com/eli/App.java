package com.eli;

public class App {
    public static void main(String[] args) {
        var state = State.getState();
        State.print(state);
        var root = new Node(state, null);
        var hijos = root.generateChildrens();
        for (var nodo : hijos) {
            IO.println();   
            State.print(nodo.state());
        }
    }
}
