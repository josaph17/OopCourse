package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;
import ru.academits.dashiev.shapes.Circle;
import ru.academits.dashiev.shapes.Square;
import ru.academits.dashiev.shapes.Triangle;
import ru.academits.dashiev.shapes_comparators.AreaComparator;

public class Main {
    private static <C> void deleteAndPrint(MyTree<C> tree, C deletable) {
        System.out.println("New tree before delete = " + deletable + ", size = " + tree.getSize() + ":");
        tree.printTree();

        try {
            System.out.println("After tree.delete(" + deletable + "), result: " + tree.delete(deletable) + ", size = " + tree.getSize());
            tree.printTree();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void deleteNodeCheck() {
        deleteAndPrint(new MyTree<>(null, 2, 6, 1, 4, 3), 2); // 1
        deleteAndPrint(new MyTree<>(null, 5, 10, 0, 9, 8, 7, 6), 5); // 2
        deleteAndPrint(new MyTree<>(null, 5, 10, 0, 9, 8, 6, 7), 5); // 3
        deleteAndPrint(new MyTree<>(null, 5, 10, 0, 8, 7, 6), 5); // 4
        deleteAndPrint(new MyTree<>(null, 5, 10, 0, 50, 100), 5); // 5
        deleteAndPrint(new MyTree<>(null, 10, 5, 3, 8, 1, 4, 9, 7), 5); // 6
        deleteAndPrint(new MyTree<>(null, 10, 100, 93, 108, 91, 94, 109), 93); // 7
        deleteAndPrint(new MyTree<>(null, 100, 50, 200, 110, 205, 40, 45, 1), 50); // 8
        deleteAndPrint(new MyTree<>(null, 10.0), 5.0); // 9
        deleteAndPrint(new MyTree<>(null, 40.0, 30.0, null, null, 17.0, 15.0, 30.0, 13.0, 14.0), null); // 10
        deleteAndPrint(new MyTree<>(null, null, null, 17.0, 15.0, 30.0, 13.0), null); // 11
        deleteAndPrint(new MyTree<>(new AreaComparator(), new Square(5), null, null, new Square(4), new Triangle(10, 6, 8, 10, 5, 2), new Triangle(2, 3, 1, 1, 7, 4), new Triangle(2, 3, 1, 1, 7, 4), new Circle(5), new Circle(6), null), new Square(4));

    }
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.add(10);
        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(110);
        tree.add(205);
        tree.add(40);
        tree.add(45);
        tree.add(1);

        Integer findingValue = 40;

        tree.printTree();

        boolean isNodeExist = tree.contains(findingValue);

        System.out.println("Is node with value = " + findingValue + " is exist: " + isNodeExist);

        System.out.println("Tree items count: " + tree.getSize());

        System.out.println();

        System.out.println("widthBypass:");
        tree.bypassInWidth(System.out::println);

        System.out.println();

        System.out.println("deepBypass:");
        tree.bypassInDeep(System.out::println);

        System.out.println();

        System.out.println("recursionDeepVisit: ");
        tree.visitInDeepRecursively(System.out::println);
        System.out.println();

        System.out.println("Check deleteNode function:");
        deleteNodeCheck();
    }
}