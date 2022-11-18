package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.add(100);
        tree.add(200);
        tree.add(150);
        tree.add(50);
        tree.add(56);
        tree.add(40);
        tree.add(5);
        tree.add(30);
        tree.add(250);
        tree.add(225);
        tree.add(215);
        tree.add(230);
        tree.add(275);
        tree.add(220);

//        tree.add("Тигр");
//        tree.add("Слон");
//        tree.add("Лев");
//        tree.add("Чау-Чау");
//        tree.add("Орел");
//        tree.add("Анаконда");
//        tree.add("Дельфин");
//        tree.add("Кит");
//        tree.add("Чихуа-Хуа");

        //tree.widthBypass();
        //tree.deepBypass();
        //tree.recursionDeepVisit();

        tree.deleteNode(534);

        System.out.println();
        System.out.println("Hello");


    }
}
