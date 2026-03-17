package com.eli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

import com.google.gson.GsonBuilder;

/**
 * Punto de entrada de la aplicación que resuelve el problema de las torres de
 * Hanoi usando búsqueda en profundidad (DFS).
 * <p>
 * El código construye un árbol de estados a partir del estado inicial y
 * expande niveles sucesivos hasta encontrar el estado objetivo. Al
 * finalizar, escribe una representación JSON del árbol en `tree.json`.
 */
public class App {
    /**
     * Ejecuta la búsqueda en profundidad para encontrar el estado objetivo y
     * guarda el árbol generado en `tree.json`.
     *
     * @param args argumentos de línea de comandos (no usados)
     * @throws IOException si ocurre un error al escribir el archivo JSON
     */
    public static void main(String[] args) throws IOException {
        IO.println("Estado incial: ");
        var initialState = getState(3);
        IO.println("Estado final: ");
        var finalState = getState(3);
        var deepth = Validations.validateTower(IO.readln("Cuántos niveles máximos quieres que se genere?: "),
                Integer.MAX_VALUE);

        var tree = new Node(initialState, deepth);

        var finded = tree.solve(finalState, (n) -> {
            // IO.print(String.format("Exploring leve %s", n.deepth()));
        });
        
        if (finded == null) {
            IO.println("No se encontró solución");
        } else {
            var last = finded;
            var solution = new ArrayList<Node>();
            solution.add(last);
            while (last.parent() != null) {
                last = last.parent();
                solution.add(last);
            }
            for (var node : solution.reversed()) {
                IO.println(node);
            }
        }

        Files.writeString(
                Paths.get("tree.json"),
                new GsonBuilder()
                        .setPrettyPrinting()
                        .create().toJson(tree.toMap()));

        IO.println(
                "Se generó un archivo tree.json para visualizar el tree generado en https://jsoncrack.eliyya.dev/editor");

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
