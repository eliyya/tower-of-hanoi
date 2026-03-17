package com.eli;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Punto de entrada de la aplicación que resuelve el problema de las torres de
 * Hanoi usando búsqueda en profundidad (DFS).
 */
public class App {
    public static void main(String[] args) throws IOException {
        IO.println("Estado incial: ");
        var initialState = getState(3);

        var tree = new Node(initialState);

        IO.println(tree);

        for (var child : tree.generateChildrens()) {
            IO.println(child);
        }
    }

    static State<State<Integer>> getState(int range) {
        var state = new State<State<Integer>>(IntStream.range(0, range).mapToObj(i -> new State<Integer>()).toList());
        for (var i : new int[] { 3, 2, 1 }) {
            while (true) {
                var nt = Validations
                        .validateTower(IO.readln(String.format("Donde quieres colocar el disco %d (1-3): ", i)), 3);
                if (nt == -1) {
                    continue;
                }
                state.get(nt - 1).add(i);
                break;
            }
        }
        return state;
    }
}
