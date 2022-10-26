package ru.academits.dashiev.array_list_home_main3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println("list1: " + list1);

        List<Integer> list2 = new ArrayList<>();
        for (Integer e : list1) {
            if (list2.contains(e) == false) {
                list2.add(e);
            }
        }

        System.out.println("list2: " + list2);
    }
}
