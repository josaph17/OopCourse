package ru.academits.dashiev.list_main;

import ru.academits.dashiev.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

//        System.out.println(list.getSize());
//        System.out.println(list.getFirstElement());
//        System.out.println("Конец.");
//        System.out.println(list.get(3));
//        list.set(3, 25);
//        System.out.println(list.get(3));
        System.out.println(".removeFirst()");
        System.out.println(list.get(0));
        list.removeFirst();
        System.out.println(list.get(0));
    }
}
