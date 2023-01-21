package ru.academits.dashiev.matrix_main;

import ru.academits.dashiev.matrix.Matrix;
import ru.academits.dashiev.vector.Vector;

public class Main {
    public static void checkConstructors() {
        System.out.println("-- Конструкторы --");
        System.out.println();

        int sizeN = 3;
        int sizeM = 5;

        Matrix sizeMatrix = new Matrix(sizeN, sizeM);

        System.out.println("a.Конструктор матрица нулей размера nxm:");
        System.out.println("n = " + sizeN + " , m = " + sizeM);

        double[][] array = {{0.9, 2.1, 5.2, 4.2}, {3, 42.7, 6.5}, {321, 7.1, 43.9, 111.3}};

        Matrix arrayMatrix = new Matrix(array);

        System.out.println("c.Конструктор копирования из двумерного массива:");
        System.out.println(arrayMatrix);

        Matrix copyMatrix = new Matrix(arrayMatrix);

        System.out.println("b.Конструктор копирования:");
        System.out.println(copyMatrix);

        double[] array1 = {21.3, 43.4};
        double[] array2 = {9};
        double[] array3 = {342.9, 7.05, 111, 32132.43, 27};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        System.out.println("d.Конструктор копирования из массива векторов-строк:");
        System.out.println(vectorsMatrix);
    }

    public static void checkNonStaticMethods1() {
        System.out.println();
        System.out.println("-- Нестатические методы --");
        System.out.println();

        double[][] array = { //
                {6, 5, 7.01, 22, 5.2}, //
                {32.1, 98, 5.5}, //
                {5.87, 3, 21.2, 90, 11.37}, //
                {1.6, 81.33, 1.2, 278, 9.4} //
        };

        Matrix matrix2 = new Matrix(array);

        int vectorRow = 1;

        System.out.println("b.Получение вектора-строки по индексу = " + vectorRow + ":");
        System.out.println(matrix2.getVectorRow(vectorRow));

        double[] replaceArray = {4, 74, 1.23, 8.09, 11};

        Vector replaceVector = new Vector(replaceArray);

        matrix2.setVectorRow(vectorRow, replaceVector);

        System.out.println("b.Задание вектора-строки по индексу = " + vectorRow + ":");
        System.out.println(matrix2);

        int vectorCols = 4;

        System.out.println("c.Получение вектора-столбца по индексу = " + vectorCols + ":");
        System.out.println(matrix2.getVectorColumn(vectorCols));

        matrix2.transpose();

        System.out.println("d.Транспонирование матрицы:");
        System.out.println(matrix2);

        double scalar = 2.2;

        matrix2.multiplyByScalar(scalar);

        System.out.println("e.Умножение на скаляр = " + scalar + ":");
        System.out.println(matrix2);
    }

    public static void checkNonStaticMethods2() {
        double[][] array2 = { //
                {6, 5, 7.01, 22}, //
                {4, 74, 1.23, 8.09}, //
                {5.87, 3, 21.2, 90}, //
                {1.6, 81.33, 1.2, 278} //
        };

        Matrix matrix2 = new Matrix(array2);

        double det = matrix2.getDeterminant();

        System.out.println("h.умножение матрицы на вектор:");
        System.out.println(det);

        double[][] array3 = { //
                {6, 5, 7.01, 22}, //
                {4, 74, 1.23, 8.09}, //
                {5.87, 3, 21.2, 90}, //
                {1.6, 81.33, 1.2, 278} //
        };

        Matrix matrix3 = new Matrix(array3);

        matrix2.add(matrix3);

        System.out.println("i.Сложение матриц:");
        System.out.println(matrix2);

        matrix2.subtract(matrix3);

        System.out.println("j.Вычитание матриц:");
        System.out.println(matrix2);
    }

    public static void checkStaticMethods() {
        System.out.println();
        System.out.println("-- Статические методы --");
        System.out.println();

        double[][] array3 = { //
                {1.3, 5, 2.3}, //
                {3, 4, 7.04}, //
        };

        Matrix matrix3 = new Matrix(array3);
        Matrix matrix4 = new Matrix(matrix3);

        Matrix matrix5 = Matrix.add(matrix3, matrix4);

        System.out.println("a.Сложение матриц:");
        System.out.println(matrix5);

        Matrix matrix6 = Matrix.subtract(matrix5, matrix4);

        System.out.println("b.Вычитание матриц:");
        System.out.println(matrix6);

        double[][] array2 = { //
                {6, 5, 7.01, 22}, //
                {32.1, 98, 5.5, 5.2}, //
                {5.87, 3, 21.2, 90} //
        };

        Matrix matrix7 = new Matrix(array2);

        Matrix multiplyMatrix = Matrix.multiply(matrix3, matrix7);

        System.out.println("c.Умножение матриц:");

        System.out.println(multiplyMatrix);
    }

    public static void main(String[] args) {
        checkConstructors();
        checkNonStaticMethods1();
        checkNonStaticMethods2();
        checkStaticMethods();
    }
}