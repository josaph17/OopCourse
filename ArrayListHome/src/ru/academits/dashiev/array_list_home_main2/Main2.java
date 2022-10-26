package ru.academits.dashiev.array_list_home_main2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-- 1-й способ --");

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 74, 23, 53, 44, 11, 11, 80));

        System.out.println("list1: " + list1);

        for (int i = list1.size() - 1; i >= 0; i--) {
            if (list1.get(i) % 2 == 0) {
                list1.remove(i);
            }
        }

        System.out.println("list1 после удаления четных чисел: " + list1);

        System.out.println("-- 2-й способ --");

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 32, 789, 1000, 2048, 7, 78));

        System.out.println("list2: " + list2);

        Iterator iterator = list2.iterator();

        int value;

        while (iterator.hasNext()) { // пока нынешний индекс < size(), и значение !=null
            value = (Integer) iterator.next(); //next выдает значение при индексе и в след итар дает увел зн-е индекса
            if (value % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("list2 после удаления четных чисел: " + list2);

        //TODO прошу описать другие способы удаления четных чисел
    }
}