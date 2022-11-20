package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        //        tree.add(100);
        //        tree.add(200);
        //        tree.add(50);
        //        tree.add(150);
        //        tree.add(250);
        //        tree.add(225);
        //        tree.add(275);
        //        tree.add(215);
        //        tree.add(220); //9

        tree.add(200);
        tree.add(100);
        tree.add(250);
        tree.add(225);
        tree.add(210);
        tree.add(205);
        tree.add(206);
        tree.add(270);

        tree.printTree();

        System.out.println("Delete " + tree.deleteNode(250));
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
        System.out.println("Tree 3");

        //fixme не все случаи удаления корня рассмотрены
        deleteAndPrint(new MyTree<>(2, 6, 1, 4 ,3), 2);
        deleteAndPrint(new MyTree<>(5, 10, 0, 9 ,8, 7, 6), 5);
        deleteAndPrint(new MyTree<>(5, 10, 0, 9 ,8, 6, 7), 5);
        deleteAndPrint(new MyTree<>(5, 10, 0, 8 ,7, 6), 5);
        deleteAndPrint(new MyTree<>(5, 10, 0, 50 ,100), 5);
        deleteAndPrint(new MyTree<>(10, 5, 3, 8, 1, 4), 5);

//        deleteAndPrint(new MyTree<>(10.0), 5.0);


    }

    private static <C extends Comparable<C>> void deleteAndPrint(MyTree<C> tree, C deletable) {
        System.out.println("New tree");
        tree.printTree();
        try {
            System.out.println("Delete " + tree.deleteNode(deletable));
            tree.printTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
