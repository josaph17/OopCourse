package ru.academits.dashiev.my_array_list_main;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> it = new MyArrayList<>();
//
//        it.add("Java");
//        it.add("SQL");
//        it.add("GIT");
//
//        Iterator<String> itIterator = it.iterator();
//
//        while(itIterator.hasNext()){
//            System.out.println(itIterator.next());
//        }

        //it.add(3, "Kafka");

        List<Integer> list = new MyArrayList<>();

        list.add(4);
        list.add(10);
        list.add(18);
        list.add(45);
        list.add(77);
        list.add(4,7);

        Iterator listIterator = list.iterator();

        while(listIterator.hasNext()){
            System.out.print(listIterator.next() + " ");
        }

        System.out.println();

        System.out.println(list.size());
    }
}
