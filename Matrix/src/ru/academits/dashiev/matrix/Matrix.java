package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int rows;
    private int cols; // размерность вектора

    private Vector[] vectors;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Matrix rows must be > 0. Current rows = " + n);
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Matrix cols must be > 0. Current cols = " + m);
        }

        rows = n;
        cols = m;

        vectors = new Vector[n]; // обязательно проинициализируем vectors

        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }

        rows = matrix.rows;
        cols = matrix.cols;

        vectors = new Vector[rows]; // создаем массив векторов

        for (int i = 0; i < rows; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        rows = array.length;

        vectors = new Vector[rows];

        // Находим кол-во столбцов матрицы
        for (int i = 0; i < rows; i++) {
            int currentCapacity = array[i].length;

            if (currentCapacity > cols) {
                cols = currentCapacity;
            }
        }

        for (int i = 0; i < rows; i++) {
            vectors[i] = new Vector(Arrays.copyOf(array[i], cols));
        }
    }

    public Matrix(Vector[] vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null");
        }

        rows = vector.length;

        vectors = new Vector[vector.length];

        // Находим максимальную длину подВектора
        for (int i = 0; i < rows; i++) {
            int currentCapacity = vector[i].getSize();

            if (currentCapacity > cols) {
                cols = currentCapacity;
            }
        }

        for (int i = 0; i < rows; i++) {
            vectors[i] = new Vector(cols);

            for (int j = 0; j < vector[i].getSize(); j++) {
                vectors[i].setComponent(j, vector[i].getComponent(j));
            }
        }
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= rows) {
            throw new IndexOutOfBoundsException(
                    "Matrix min rowIndex = 0, max index = " + (rows - 1) + ". Current rowIndex = " + index);
        }
    }

    // c.Получение вектора - столбца по индексу
    // d.Транспонирование матрицы
    // e.Умножение на скаляр
    // f.Вычисление определителя матрицы
    // g.toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
    // h.Умножение матрицы на вектор
    // i.Сложение матриц
    // j.Вычитание матриц
    // Статические методы. a.Сложение матриц
    // Статические методы. b.Вычитание матриц
    // Статические методы. c.Умножение матриц

    // a.Получение размеров матрицы
    public int size() {
        return rows * cols;
    }

    // b.Получение и задание вектора-строки по индексу
    public Vector getRow(int index) {
        return null;
    }
}
