package ru.academits.dashiev.matrix_main;

import ru.academits.dashiev.matrix.Matrix;
import ru.academits.dashiev.vector.Vector;

public class Main {
    public static void checkConstructors() {
        System.out.println("-- Конструкторы --");
        System.out.println();

        int rowsCount = 3;
        int columnsCount = 5;

        Matrix sizeMatrix = new Matrix(rowsCount, columnsCount);

        System.out.println("a.Конструктор матрица нулей размера nxm:");
        System.out.println("Rows count = " + sizeMatrix.getRowsCount() + " , columns count = " + sizeMatrix.getColumnsCount());

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

    public static void checkNonStaticMethods() {
        System.out.println();
        System.out.println("-- Нестатические методы --");
        System.out.println();

        int rowIndex = 1;

        System.out.println("b.Получение вектора-строки по индексу = " + rowIndex + ":");

        double[][] array1 = {{6, 5, 7.01, 22, 5.2}, {32.1, 98, 5.5}, {5.87, 3, 21.2, 90, 11.37}, {1.6, 81.33, 1.2, 278, 9.4}};

        Matrix matrix1 = new Matrix(array1);
        System.out.println(matrix1.getRow(rowIndex));

        System.out.println("b.Задание вектора-строки по индексу = " + rowIndex + ":");

        double[] array2 = {4, 74, 1.23, 8.09, 11};

        Vector replacedVector = new Vector(array2);

        matrix1.setRow(rowIndex, replacedVector);
        System.out.println(matrix1);

        int columnIndex = 4;

        System.out.println("c.Получение вектора-столбца по индексу = " + columnIndex + ":");
        System.out.println(matrix1.getColumn(columnIndex));

        System.out.println("d.Транспонирование матрицы:");
        matrix1.transpose();
        System.out.println(matrix1);

        double scalar = 2.2;

        System.out.println("e.Умножение на скаляр = " + scalar + ":");
        matrix1.multiplyByScalar(scalar);
        System.out.println(matrix1);

        System.out.println("f.Вычисление определителя матрицы");

        double[][] array3 = {{6, 5, 7.01, 22}, {4, 74, 1.23, 8.09}, {5.87, 3, 21.2, 90}, {1.6, 81.33, 1.2, 278}};

        Matrix matrix2 = new Matrix(array3);

        System.out.println("Determinant = " + matrix2.getDeterminant());

        System.out.println("h.умножение матрицы на вектор:");

        double[] array4 = {2, 2, 2, 2};

        Vector vector = new Vector(array4);

        Vector ptoductVector = matrix1.multiplyByVector(vector);

        System.out.println(ptoductVector);

        System.out.println("i.Сложение матриц:");
        double[][] array5 = {{6, 5, 7.01, 22}, {4, 74, 1.23, 8.09}, {5.87, 3, 21.2, 90}, {1.6, 81.33, 1.2, 278}};

        Matrix matrix3 = new Matrix(array5);

        matrix2.add(matrix3);
        System.out.println(matrix2);

        System.out.println("j.Вычитание матриц:");
        matrix2.subtract(matrix3);
        System.out.println(matrix2);
    }

    public static void checkStaticMethods() {
        System.out.println();
        System.out.println("-- Статические методы --");
        System.out.println();

        double[][] array1 = {{1.3, 5, 2.3}, {3, 4, 7.04}};

        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(matrix1);

        Matrix matrix3 = Matrix.getSum(matrix1, matrix2);

        System.out.println("a.Сложение матриц:");
        System.out.println(matrix3);

        Matrix matrix4 = Matrix.getDifference(matrix3, matrix2);

        System.out.println("b.Вычитание матриц:");
        System.out.println(matrix4);

        System.out.println("c.Умножение матриц:");

        double[][] array3 = {{2, 1}, {-3, 0}, {4, -1}};

        double[][] array4 = {{5, -1, 6}, {-3, 0, 7}};

        Matrix matrix5;
        matrix5 = new Matrix(array3);

        Matrix matrix6 = new Matrix(array4);

        Matrix productMatrix = Matrix.getProduct(matrix5, matrix6);

        System.out.println(productMatrix);
    }

    public static void main(String[] args) {
        checkConstructors();
        checkNonStaticMethods();
        checkStaticMethods();
    }
}