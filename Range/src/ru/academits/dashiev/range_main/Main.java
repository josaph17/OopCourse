package ru.academits.dashiev.range_main;

import ru.academits.dashiev.range.Range;

public class Main {
    public static void printRangeArray(Range[] array) {
        for (Range i : array) {
            System.out.print(i.toString() + " ");
        }
    }

    public static void checkFunction(double from1, double to1, double from2, double to2) {
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.print("Дано два интервала: " + range1.toString() + " и " + range2.toString());
        System.out.println();

        Range intersectRange = range1.getIntersect(range2);
        if (intersectRange != null) {
            System.out.println("Интервал пересечения двух интервалов: " + intersectRange.toString());
        } else {
            System.out.println("Интервал пересечения двух интервалов: null");
        }

        if (range1.getUnion(range2) != null) {
            System.out.print("Объединение двух отрезков: ");
            printRangeArray(range1.getUnion(range2));
        } else {
            System.out.print("Объедиение двух интервалов: null");
        }

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        // Range range1 = new Range(11.3, 32.4);
//        double point = 32.6;
//        double length = range.getLength();
//
//        System.out.printf("Длина диапазона от %.1f до %.1f равна %.1f%n", range.getFrom(), range.getTo(), length);
//
//        if (range.isInside(point)) {
//            System.out.printf("Число %.1f принадлежит диапазону ", point);
//        } else {
//            System.out.printf("Число %.1f не принадлежит диапазону от %.1f до %.1f%n", point, range.getFrom(), range.getTo());
//        }
        // Range range2 = new Range(12, 30);
//        length = range.getLength();
//        System.out.printf("Длина диапазона от %.1f до %.1f равна %.1f%n", range.getFrom(), range.getTo(), length);
//
//        if (range.isInside(point)) {
//            System.out.printf("Число %.1f принадлежит диапазону от %.1f до %.1f%n", point, range.getFrom(), range.getTo());
//        } else {
//            System.out.printf("Число %.1f не принадлежит диапазону от %.1f до %.1f%n", point, range.getFrom(), range.getTo());
//        }
        checkFunction(6.7, 33.8, 8, 34.1); // Интервалы пересек в 2-х точках, объед 1 отр

        checkFunction(5, 7, 5, 7); // И. равны. Интервалы О и перес равны

        checkFunction(3.3, 4.4, 5.5, 6.6); // И. равны. Интервалы О и перес равны

    }
}
