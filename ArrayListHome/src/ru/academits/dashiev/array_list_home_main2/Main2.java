package ru.academits.dashiev.array_list_home_main2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        //создаем сканер от FileInputStream
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"), "UTF-8")) {
            // дальше работаем сосканером как обычно
            ArrayList<String> a = new ArrayList<>();

            while (scanner.hasNextLine()) {
                a.add(scanner.nextLine());
                // теперь close Не нужен о вызовется сам2
            }

            System.out.println("Привет, как дела");
        }
    }
}
