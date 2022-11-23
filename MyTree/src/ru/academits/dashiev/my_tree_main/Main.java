package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {//
        //fixme не все случаи удаления корня рассмотрены
        //        deleteAndPrint(new MyTree<>(2, 6, 1, 4 ,3), 2); //1
        //        deleteAndPrint(new MyTree<>(5, 10, 0, 9 ,8, 7, 6), 5); //2
        //        deleteAndPrint(new MyTree<>(5, 10, 0, 9 ,8, 6, 7), 5); //3
        //        deleteAndPrint(new MyTree<>(5, 10, 0, 8 ,7, 6), 5); //4
        //        deleteAndPrint(new MyTree<>(5, 10, 0, 50 ,100), 5); //5
        //        deleteAndPrint(new MyTree<>(10, 5, 3, 8, 1, 4, 9, 7), 5); //6
        //        deleteAndPrint(new MyTree<>(10, 100, 93, 108, 91, 94, 109), 93); //6
        deleteAndPrint(new MyTree<>(100, 50, 200, 110, 205, 40, 45, 1), 50);
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