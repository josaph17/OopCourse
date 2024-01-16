package ru.academits.dashiev.graph_main;

import ru.academits.dashiev.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph path = new Graph(8);

        path.set(0, 1, 1);
        path.set(0, 4, 1);
        path.set(1, 4, 1);
        path.set(1, 7, 1);
        path.set(2, 4, 1);
        path.set(2, 7, 1);
        path.set(3, 4, 1);
        path.set(5, 6, 1);

        System.out.println("Width bypass:");
        path.bypassInWidth(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("Deep bypass:");
        path.bypassInDeep(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("Recursion deep bypass:");
        path.bypassInDeepRecursively(i -> System.out.print(i + " "));
    }
}