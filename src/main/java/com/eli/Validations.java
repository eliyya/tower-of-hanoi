package com.eli;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Utilidades de validación y búsqueda auxiliares para la aplicación.
 */
public class Validations {
    /**
     * Valida que la entrada sea un número entero entre 0 y `max`.
     *
     * @param read valor leído (se espera `String`)
     * @param max  valor máximo permitido (inclusive)
     * @return el entero parseado si es válido
     */
    public static int validateTower(Object read, int max) {
        int state = 1;
        if (read instanceof String str) {
            try {
                state = Integer.parseInt(str);
            } catch (Exception e) {
                IO.println("Tiene que ser un numero entero");
                System.exit(1);
            }
        } else {
            IO.println("Tiene que ser un numero entero");
            return -1;
        }
        if (state < 1) {
            IO.println("No puede ser menor que 1");
            return -1;
        }
        if (state > max) {
            IO.println("No puede ser mayor que "+max);
            return -1;
        }
        return state;
    }

    /**
     * Busca en una lista de nodos un nodo cuyo estado sea igual al indicado.
     *
     * @param list  lista de nodos a buscar
     * @param state estado objetivo
     * @return el nodo encontrado o `null` si no existe
     */
    static Node find(ArrayList<Node> list, State<?> state) {
        for (Node node : list) {
            if (node.state().equals(state))
                return node;
        }
        return null;
    }

    static boolean canContinue(int last, List<Integer> used, int max) {
        for (var i : IntStream.range(1, max +1).toArray()) {
            if (used.contains(i)) continue;
            if (last > i) return true;
        }
        return false;
    }
}
