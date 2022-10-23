package array_list_home_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //создаем сканер от FileInputStream
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"), "UTF-8")) {
            // дальше работаем сосканером как обычно
            while(scanner.hasNextLine()){
                int count = scanner.nextInt();
                ArrayList<Integer> a = new ArrayList<>(count);

                for (int i = 0; i < count; i++) {
                    a.add(scanner.nextInt());
                }
                // теперь close Не нужен о вызовется сам2
                System.out.println("Привет, как дела");
            }
        }
    }
}
