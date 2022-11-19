package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        //        tree.add(100);
        //        tree.add(200);
        //        tree.add(150);
        //        tree.add(50);
        //        tree.add(56);
        //        tree.add(40);
        //        tree.add(5);
        //        tree.add(30);
        //        tree.add(250);
        //        tree.add(225);
        //        tree.add(215);
        //        tree.add(230);
        //        tree.add(275);
        //        tree.add(220);

        //        tree.add(100);
        //        tree.add(200);
        //        tree.add(50);
        //        tree.add(150);
        //        tree.add(250);
        //        tree.add(225);
        //        tree.add(275);
        //        tree.add(215);
        //        tree.add(220); //9
        tree.add(5);
        tree.add(2);
        tree.add(1);
        tree.add(10);
        tree.add(7);
        tree.add(15);
        tree.add(20);
        tree.add(14);
        tree.add(6);
        tree.add(9);
        //tree.widthBypass();
        //tree.deepBypass();
        //tree.recursionDeepVisit();

        //tree.printTree();
        // tree.printTree();

        //         System.out.println("Delete"+tree.deleteNode(7));
        //          TODO NPE
        //        tree.printTree();

        //System.out.println("Конец !!!");

        MyTree<Integer> tree2 = new MyTree<>();
        tree2.add(3);
        tree2.add(4);
        tree2.add(5);
        tree2.printTree();
        System.out.println("Delete " + tree2.deleteNode(4));
        tree2.printTree();
    }
}
