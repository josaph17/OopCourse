package ru.academits.dashiev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        System.out.println("-- Task 1 --");

        // создаем сканер от FileInputStream
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"), "UTF-8")) {
            // дальше работаем со сканером как обычно
            ArrayList<String> scannerList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                scannerList.add(scanner.nextLine());
                // теперь close Не нужен он вызовется сам
            }

            System.out.println("scannerList: " + scannerList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("The program continues to work.");
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

        System.out.println("-- Task 3 --");

        ArrayList<Integer> source = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 3, 3));
        System.out.println("source: " + source);

        ArrayList<Integer> result = new ArrayList<>(source.size());

        for (Integer item : source) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }

        result.trimToSize();

        System.out.println("result: " + result);
    }
}
