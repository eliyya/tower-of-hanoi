package com.eli;

import java.util.ArrayList;

public class Node {
    private int[][] state;
    private Node parent;
    private ArrayList<Node> childrens = new ArrayList<Node>();

    public Node(int[][] state, Node parent) {
        this.state = state;
        this.parent = parent;
    }

    public int[][] state() {
        return state;
    }

    public Node parent() {
        return parent;
    }
    
    public ArrayList<Node> generateChildrens() {
        try {
            childrens.add(mover(-1, 0));
        } catch (Exception e) {}

        try {
            childrens.add(mover(1, 0));
        } catch (Exception e) {}

        try {
            childrens.add(mover(0, -1));
        } catch (Exception e) {}

        try {
            childrens.add(mover(0, 1));
        } catch (Exception e) {}
        
        return childrens;
    }
    
    private Node mover(int y, int x) {
        var clon = State.clone(state);
        var cero = State.findPosition(clon, 0);
        var numero = clon[y + cero[0]][x + cero[1]];
        clon[y + cero[0]][x + cero[1]] = 0;
        clon[cero[0]][cero[1]] = numero;
        return new Node(clon, this);
    }

}
