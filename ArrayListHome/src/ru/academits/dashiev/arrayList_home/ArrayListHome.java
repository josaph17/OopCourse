package ru.academits.dashiev.arrayList_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListHome {
    public static void main(String[] args) {
        System.out.println("-- Task 1 --");

        // создаем сканер от FileInputStream
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            // дальше работаем со сканером как обычно
            ArrayList<String> fileStringsList = new ArrayList<>();

            while (!fileStringsList.add(reader.readLine())) {
                // теперь close Не нужен он вызовется сам
            }

            System.out.println("fileStringsList: " + fileStringsList);
        } catch (FileNotFoundException e) {
            System.out.println("Нету файла.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("The program continues to work.");
            System.out.println();
        }

        System.out.println("-- Task 2 --");

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 74, 23, 53, 44, 11, 11, 80));

        System.out.println("list: " + list);

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }

        System.out.println("list после удаления четных чисел: " + list);
        System.out.println();

        System.out.println("-- Task 3 --");

        ArrayList<Integer> repeatingNumbersList = new ArrayList<>(
                Arrays.asList(1, 5, 2, 1, 3, 5, 3, 3));
        System.out.println("repeatingNumbersList: " + repeatingNumbersList);

        ArrayList<Integer> uniqueNumbersList = new ArrayList<>(repeatingNumbersList.size());

        for (Integer item : repeatingNumbersList) {
            if (!uniqueNumbersList.contains(item)) {
                uniqueNumbersList.add(item);
            }
        }

        uniqueNumbersList.trimToSize(); // Лучше не делать без необходимости

        System.out.println("uniqueNumbersList: " + uniqueNumbersList);
    }
}