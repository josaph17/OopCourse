package ru.academits.dashiev.range_main;

import ru.academits.dashiev.range.Range;

import java.util.Arrays;

public class Main {
    public static void printRangesArray(Range range) {
        if (range.getLength() == 0) {
            System.out.println("[]");

            return;
        }

        System.out.println("[" + range + "]");
    }

    public static void checkRangesOperations(double from1, double to1, double from2, double to2) {
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.print("Даны интервалы: " + range1 + " и " + range2);
        System.out.println();

        if (range1.getIntersection(range2) == null) {
            System.out.println("Пересечение интервалов: null");
        } else {
            System.out.print("Пересечение интервалов: ");
            printRangesArray(range1.getIntersection(range2));
        }

        System.out.print("Объединение интервалов: ");
        System.out.println(Arrays.toString(range1.getUnion(range2)));

        if (range1.getDifference(range2).length != 0) { // если рез-т пустой массив, т.е.масс массив длины 0
            System.out.print("Разность интервалов: ");
            System.out.println(Arrays.toString(range1.getDifference(range2)));
        } else {
            System.out.print("Разность интервалов: ");
            System.out.println(Arrays.toString(range1.getDifference(range2)));
        }

        System.out.println();
    }

    public static void main(String[] args) {
        checkRangesOperations(8.8, 9.9, 8.8, 9.9); // не перес
        checkRangesOperations(4.4, 5.5, 6.6, 7.7); // не перес
        checkRangesOperations(6.6, 7.7, 4.4, 5.5); // не перес
        checkRangesOperations(6, 8.9, 4.4, 6.3); // перес в двух точках
        checkRangesOperations(5.2, 8.3, 7.8, 10); // перес в конце this
        checkRangesOperations(6.7, 10, 7.2, 8.8); // второй отрезок внутри this
        checkRangesOperations(10, 10.5, 9.9, 11.9); // this внутри второго отрещка
        checkRangesOperations(5, 6.6, 6.6, 7.8); // this внутри второго отрещка
        checkRangesOperations(5.5, 10.2, 4.4, 20); // this внутри второго отрещка
    }
}
