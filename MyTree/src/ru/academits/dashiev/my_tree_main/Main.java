package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.add(18);
        tree.add(10);
        tree.add(20);
        tree.add(16);
        tree.add(9);
        tree.add(7);
        tree.add(7);

        System.out.println("Hello");
    }
}
