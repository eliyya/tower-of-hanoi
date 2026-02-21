package com.eli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

/**
 * Punto de entrada de la aplicación que resuelve el problema de las torres de Hanoi usando búsqueda en profundidad (DFS).
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
        var startState = getState(true);
        var finalState = getState(false);
        var deepth = Validations.validateDisc(IO.readln("Cuántos niveles máximos quieres que se genere?: "), Integer.MAX_VALUE);

        var tree = new Node(startState, deepth);
        
        var finded = tree.solve(finalState);
        if (finded == null) {
            IO.println("No se encontró solución");
            return;
        }
        IO.println("Encontrado");

        var path = new ArrayList<Node>();
        do {
            path.add(finded);
            finded = finded.parent();
        } while (finded != null);
        path.reversed().forEach(IO::println);

        Files.writeString(
                Paths.get("tree.json"),
                new GsonBuilder()
                        .setPrettyPrinting()
                        .create().toJson(tree.toMap()));

        IO.println("Se generó un archivo tree.json para visualizar el tree generado en jsoncrack.eliyya.dev/editor");

    }

    static State<State<Integer>> getState(boolean init) {
        var state = new State<State<Integer>>();
        var disks = new State<Integer>();
        for (int t = 0; t < 3; t++) {
            var tower = new State<Integer>();
            state.add(tower);
            if (disks.size() == 3 || (!tower.isEmpty() && !Validations.canContinue(tower.getLast(), disks, 3))) {
                continue;
            }
            IO.println(String.format("Estado %s de la torre %d...", init ? "inicial" : "final", t+1));
            for (int i = 0; i < 3; i++) {
                // IO.println(!tower.isEmpty());
                // IO.println(!tower.isEmpty() && !Validations.canContinue(tower.getLast(), disks, 3));
                if (disks.size() == 3 || (!tower.isEmpty() && !Validations.canContinue(tower.getLast(), disks, 3))) {
                    break;
                }
                var s = Validations.validateDisc(IO.readln("ingrese de 1 a 3 (deje en blanco para la siguiente torre): "), 3);
                if (s == -1) {
                    i--;
                    continue;
                } else if (s == 0) {
                    break;
                } else {
                    if (disks.contains(s)) {
                        IO.println("No se puede repetir discos");
                        i--;
                        continue;
                    }
                    if (tower.isEmpty()) {
                        disks.add(s);
                        tower.add(s);
                    } else {
                        var last = tower.getLast();
                        if (last > s) {
                            tower.add(s);
                            disks.add(s);
                            continue;
                        } 
                        IO.println("No se puede colocar un disco más grande sobre uno más pequeño");
                        i--;
                    }
                }
            }
        }
        return state;   
    }
}
