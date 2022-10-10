package ru.academits.dashiev.list_main;

import ru.academits.dashiev.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        System.out.println(list.getSize());
        System.out.println(list.getFirstElement());
        System.out.println("Конец.");
        System.out.println(list.get(2)); // до сюда правильно
        System.out.println("List set(1,25)");
        System.out.println(list.get(1));
        list.set(1, 25);
        System.out.println(list.get(1));
        System.out.println(".removeFirst()");
        list.removeFirst();
        System.out.println(list.get(0));
    }
}
