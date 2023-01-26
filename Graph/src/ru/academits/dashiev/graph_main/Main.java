package ru.academits.dashiev.graph_main;

import ru.academits.dashiev.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph path = new Graph(8);

        path.set(1, 2, 1);
        path.set(1, 5, 1);
        path.set(2, 5, 1);
        path.set(2, 8, 1);
        path.set(3, 5, 1);
        path.set(3, 8, 1);
        path.set(4, 5, 1);
        path.set(6, 7, 1);

        System.out.println("Width bypass:");
        path.widthBypass();

        System.out.println("Deep bypass:");
        path.deepBypass();

        System.out.println("Recursion deep bypass:");
        path.recursionDeepBypass();
    }
}