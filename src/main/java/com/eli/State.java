package com.eli;

public class State {

    public static int[][] getState() {
        var state = new int[][] {
                {9,9,9},
                {9,9,9},
                {9,9,9},
        };

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                state[y][x] = 10;
                while (true) {
                    print(state);
                    var response = IO.readln("¿Que valor desea agregar aqui? ");
                    var number = Validations.validateNumber(response);
                    if (number == null) {
                        IO.println("Numero debe se un entero");
                        continue;
                    } else if (number < 0 || number > 8) {
                        IO.println("Numero debe estar entre 0 y 8");
                        continue;
                    }
                    var repeat = findPosition(state, number);
                    if (repeat != null) {
                        IO.println("Numero ya esta en el estado");
                        continue;
                    }
                    state[y][x] = number;
                    break;
                }
            }
        }
        return state;
    }

    public static int[][] clone(int[][] state){
        var clon = new int[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                clon[y][x] = state[y][x];
            }
        }
        return clon;
    }

    public static void print(int[][] state) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (state[y][x] == 9) {
                    IO.print("_ ");
                } else if(state[y][x] == 10) {
                    IO.print("# ");
                } else {
                    IO.print(state[y][x]+" ");
                }
            }
            IO.println();
        }
    }

    public static int[] findPosition(int[][] state, int number) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (state[y][x] == number) {
                    return new int[]{y, x};
                }
            }
        }
        return null;
    }
}
