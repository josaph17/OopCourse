package ru.academits.dashiev.test1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) throws FileNotFoundException {
        // ��������� � arrayLIst ������, ���� ����� ������� �� int��
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {


            ArrayList<Integer> list1 = new ArrayList<>();

            while (scanner.hasNext()) {
                // scanner.hasNext() - ���� �� � ������ ��� ���-������
                if(scanner.hasNext() == scanner.hasNextInt()){
                    Integer x = scanner.nextInt();

                    list1.add(x);
                }
            }

            Iterator listIterator = list1.iterator();

            while (listIterator.hasNext()) {
                System.out.println(listIterator.next() + " ");
            }

        }
    }
}
