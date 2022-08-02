package ru.academits.dashiev.range_main;

import ru.academits.dashiev.range.Range;

public class Main {
    public static void printRangesArray(Range[] array) {
        if (array.length == 1) {
            System.out.print("[");
            for (Range range : array) {
                System.out.print(range.toString());
            }
            System.out.print("]");

            return;
        }

        if (array.length == 0) {
            System.out.println("[]");

            return;
        }

        if (array.length == 2) {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            for (Range i : array) {
                sb.append(i)
                        .append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());

            sb.append("]");

            String result = sb.toString();

            System.out.print(sb);
        }
    }

    public static void checkRangesOperationsFunction(double from1, double to1, double from2, double to2) {
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.print("Даны интервалы: [" + range1.toString() + "] и [" + range2.toString() + "]");
        System.out.println();

        if (range1.getIntersection(range2) != null) {
            System.out.print("Пересечение интервалов: ");
            printRangesArray(range1.getIntersection(range2));
        } else {
            System.out.print("Пересечение интервалов: null");
        }

        System.out.println();

        if (range1.getUnion(range2) != null) {
            System.out.print("Объединение интервалов: ");
            printRangesArray(range1.getUnion(range2));
        } else {
            System.out.print("Объединение интервалов: null");
        }

        System.out.println();

        if (range1.getDifference(range2) != null) {
            System.out.print("Разность интервалов: ");
            printRangesArray(range1.getDifference(range2));
        }

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        checkRangesOperationsFunction(4.4, 5.5, 6.6, 7.7); // не перес
        checkRangesOperationsFunction(6.6, 7.7, 4.4, 5.5); // не перес
        checkRangesOperationsFunction(6, 8.9, 4.4, 6.3); // перес в двух точках
        checkRangesOperationsFunction(5.2, 8.3, 7.8, 10); // перес в конце this
        checkRangesOperationsFunction(6.7, 10, 7.2, 8.8); // второй отрезок внутри this
        checkRangesOperationsFunction(10, 10.5, 9.9, 11.9); // this внутри второго отрещка
        checkRangesOperationsFunction(5, 6.6, 6.6, 7.8); // this внутри второго отрещка
        checkRangesOperationsFunction(5.5, 10.2, 4.4, 20); // this внутри второго отрещка
    }
}
