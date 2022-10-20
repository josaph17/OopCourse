package ru.academits.dashiev.my_array_list_main;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(4);
        list.add(10);
        list.add(18);
        list.add(301);
        list.add(302);

        Iterator listIterator = list.iterator();

        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }

        System.out.println();

        List<Integer> newList = new MyArrayList<>();

        newList.add(301);
        newList.add(300);

        Iterator newListIterator = newList.iterator();

        while (newListIterator.hasNext()) {
            System.out.print(newListIterator.next() + " ");
        }

        System.out.println();

        System.out.println(Arrays.toString(list.toArray()));
    }
}
