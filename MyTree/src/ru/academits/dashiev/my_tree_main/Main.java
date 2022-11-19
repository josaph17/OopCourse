package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.add(100);
        tree.add(200);
        tree.add(50);
        tree.add(150);
        tree.add(250);
        tree.add(225);
        tree.add(275);
        tree.add(215);
        tree.add(220); //9
        tree.printTree();

        System.out.println("Delete " + tree.deleteNode(200));
        //          TODO NPE
        tree.printTree();

        //System.out.println("Конец !!!");

        //        MyTree<Integer> tree2 = new MyTree<>();
        //        tree2.add(2);
        //        tree2.add(6);
        //        tree2.add(7);
        //        tree2.add(15);
        //        tree2.add(14);
        //        tree2.add(13);
        //        tree2.add(11);
        //        tree2.printTree();
        //        System.out.println("Delete " + tree2.deleteNode(15));
        //        tree2.printTree();
    }
}
