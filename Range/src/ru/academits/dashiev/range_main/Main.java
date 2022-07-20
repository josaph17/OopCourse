package ru.academits.dashiev.range_main;

import ru.academits.dashiev.range.Range;

public class Main {
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
        Range range1 = new Range(6.7, 33.8);
        Range range2 = new Range(8, 34.1);

        Range intersectRange = range1.intersect(range2);
        if (intersectRange != null) {
            System.out.println("Интервал пересечения двух интервалов: " + intersectRange.toString());
        } else {
            System.out.println("Интервал пересечения двух интервалов: null");
        }
    }
}
