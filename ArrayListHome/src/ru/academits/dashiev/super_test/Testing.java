package ru.academits.dashiev.super_test;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Testing {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 5));
        //        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 3, 3, 3));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3));


//        boolean hallo = list1.retainAll(list2);
//        System.out.println(list1);
//        System.out.println(hallo);
        list1.retainAll(list2);
        System.out.println(list1);

        MyArrayList<Integer> list3 = new MyArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
        list3.add(3);
        MyArrayList<Integer> list4 = new MyArrayList<>();
        list4.add(3);
        list4.add(3);
        list4.add(3);
        list4.add(3);

        list3.retainAll(list4);
        System.out.println(list3);
    }

}
