package com.eli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * Punto de entrada de la aplicación que resuelve el problema de los jarros
 * (jarras) de agua usando búsqueda en anchura (BFS).
 * <p>
 * El código construye un árbol de estados a partir del estado inicial y
 * expande niveles sucesivos hasta encontrar el estado objetivo. Al
 * finalizar, escribe una representación JSON del árbol en `tree.json`.
 */
public class App {
    /**
     * Ejecuta la búsqueda en anchura para encontrar el estado objetivo y
     * guarda el árbol generado en `tree.json`.
     *
     * @param args argumentos de línea de comandos (no usados)
     * @throws IOException si ocurre un error al escribir el archivo JSON
     */
    public static void main(String[] args) throws IOException {
        IO.println("Ingresa el estado inicial de...");
        var sx = Validations.validateBote(IO.readln("Bote x (0-3):"), 3);
        var sy = Validations.validateBote(IO.readln("Bote y (0-4):"), 4);
        var tree = new Node(sx, sy);

        IO.println("Ingresa el estado final de...");
        var fx = Validations.validateBote(IO.readln("Bote x (0-3):"), 3);
        var fy = Validations.validateBote(IO.readln("Bote y (0-4):"), 4);
        var finalState = new State(fx, fy);

        var level = new ArrayList<Node>(List.of(tree));

        Node finded;
        while ((finded = Validations.find(level, finalState)) == null) {
            var childrens = new ArrayList<Node>(level);
            level.clear();
            for (var node : childrens) {
                level.addAll(node.generateChildrens());
            }
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
}
