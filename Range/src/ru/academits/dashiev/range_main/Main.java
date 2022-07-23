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

        System.out.print("Даны интервалы: " + range1.toString() + " и " + range2.toString());
        System.out.println();

        Range intersectRange = range1.getIntersect(range2);
        if (intersectRange != null) {
            System.out.print("Пересечение интервалов: " + intersectRange.toString());
        } else {
            System.out.print("Пересечение интервалов: null");
        }

        System.out.println();

        if (range1.getUnion(range2) != null) {
            System.out.print("Объединение интервалов: ");
            printRangeArray(range1.getUnion(range2));
        } else {
            System.out.print("Объедиение интервалов: null");
        }

        System.out.println();

        if (range1.getDifference(range2)!= null){
            System.out.print("Разность интервалов: ");
            printRangeArray(range1.getDifference(range2));
        } else{
            System.out.print("Разность интервалов: пустой массив");
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
        checkFunction(4.4, 5.5, 6.6, 7.7); // не перес
        checkFunction(6.6, 7.7, 4.4, 5.5); // не перес
        checkFunction(6, 8.9, 4.4, 6.3); // перес в начале this
        checkFunction(5.2, 8.3, 7.8, 10); // перес в конце this
        checkFunction(6.7, 10, 7.2, 8.8); // второй отрезок внутри this
        checkFunction(10, 10.5, 9.9, 11.9); // this внутри второго отрещка
        checkFunction(5, 6.6, 6.6, 7.8); // this внутри второго отрещка
        checkFunction(4.4, 5.5, 5.5, 10.2); // this внутри второго отрещка
    }
}
