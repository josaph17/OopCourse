package ru.academits.dashiev.my_array_list_main;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new MyArrayList<>();

        list.add(4);
        list.add(10);
        list.add(18);

        Iterator listIterator = list.iterator();

        while(listIterator.hasNext()){
            System.out.print(listIterator.next() + " ");
        }

        System.out.println();

        System.out.println("list size = " + list.size());

        List<Integer> newList = new MyArrayList<>();

        newList.add(301);
        newList.add(302);

        System.out.println("function addAll(index, collection)");

        list.addAll(0, newList);

        Iterator newListIterator = list.iterator();

        while(newListIterator.hasNext()){
            System.out.print(newListIterator.next() + " ");
        }

        System.out.println();

        //list.clear();

        System.out.println("after clear()");

        Iterator delListIterator = list.iterator();

        while(delListIterator.hasNext()){
            System.out.print(delListIterator.next() + " ");
        }

        System.out.println();

        list.add(301);

        System.out.println(list);
        System.out.println(list.size());

        list.remove(5);
        System.out.println(list);
        System.out.println(list.size());
    }
}