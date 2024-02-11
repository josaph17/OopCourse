package ru.academits.dashiev.graph_main;

import ru.academits.dashiev.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(3,4,5,6,7,8,9,10);

        graph.set(3, 4, 1);
        graph.set(3, 7, 1);
        graph.set(4, 7, 1);
        graph.set(4, 10, 1);
        graph.set(5, 7, 1);
        graph.set(5, 10, 1);
        graph.set(6, 7, 1);
        graph.set(8, 9, 1);

        System.out.println("Width bypass:");
        graph.bypassInWidth(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("Deep bypass:");
        graph.bypassInDepth(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("Recursion deep bypass:");
        graph.bypassInDeepRecursive(i -> System.out.print(i + " "));

        System.out.println();
    }
}