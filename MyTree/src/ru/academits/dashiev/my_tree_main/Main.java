package ru.academits.dashiev.my_tree_main;

import ru.academits.dashiev.my_tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<String> tree = new MyTree<>();

//        tree.add(8);
//        tree.add(3);
//        tree.add(10);
//        tree.add(1);
//        tree.add(6);
//        tree.add(14);
//        tree.add(4);
//        tree.add(7);
//        tree.add(13);

        tree.add("Тигр");
        tree.add("Слон");
        tree.add("Лев");
        tree.add("Чау-Чау");
        tree.add("Орел");
        tree.add("Анаконда");
        tree.add("Дельфин");
        tree.add(null);
        tree.add("Кит");
        tree.add("Чихуа-Хуа");

        //tree.widthBypass();
        //tree.deepBypass();
        tree.recursionDeepVisit();

        System.out.println();
        System.out.println("Hello");
    }
}
