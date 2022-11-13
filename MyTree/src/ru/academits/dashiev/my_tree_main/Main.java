package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(150);
        tree.add(250);
        tree.add(275);
        tree.add(225);
        tree.add(220);

        int findingElement = 7;
        System.out.println(
                "Element = " + findingElement + " findingElement: " + tree.findNode(findingElement));

        System.out.println("Hello");
    }
}
