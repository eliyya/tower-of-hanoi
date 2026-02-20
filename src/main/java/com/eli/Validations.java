package com.eli;

import java.util.ArrayList;

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
    public static int validateBote(Object read, int max) {
        int state = 0;
        if (read instanceof String str) {
            try {
                state = Integer.parseInt(str);
            } catch (Exception e) {
                IO.print("Tiene que ser un numero entero");
                System.exit(1);
            }
        } else {
            IO.print("Tiene que ser un numero entero");
            System.exit(1);
        }
        if (state < 0) {
            IO.print("No puede ser menor que 0");
            System.exit(1);
        }
        if (state > max) {
            IO.print("No puede ser mayor que "+max);
            System.exit(1);
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
    static Node find(ArrayList<Node> list, State state) {
        for (Node node : list) {
            if (node.state().equals(state))
                return node;
        }
        return null;
    }
}
