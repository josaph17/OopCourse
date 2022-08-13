package ru.academits.dashiev.range_main;

import ru.academits.dashiev.range.Range;

public class Main {
    public static void printRangesArray(Range range) {
        if (range.getLength() == 0) {
            System.out.println("[]");

            return;
        }

        System.out.println("[" + range + "]");
    }

    public static void printRangesArray(Range[] rangeArray) { // перегрузка функции
        if (rangeArray.length == 1) {
            System.out.print("[");
            for (Range range : rangeArray) {
                System.out.print(range);
            }
            System.out.print("]");

            return;
        }

        if (rangeArray.length == 0) {
            System.out.println("[]");

            return;
        }

        if (rangeArray.length == 2) {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            for (Range interval : rangeArray) {
                sb.append(interval)
                        .append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());

            sb.append("]");

            System.out.print(sb); // вместо result
        }
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
        } else {
            System.out.print("Разность интервалов: null");
        }

        System.out.println();
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
