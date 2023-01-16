package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int rows;
    private int cols;

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

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows) {
            throw new IndexOutOfBoundsException(
                    "Matrix min rowIndex = 0, max index = " + (rows - 1) + ". Current rowIndex = " + index);
        }
    }

    private void checkColIndex(int index) {
        if (index < 0 || index >= cols) {
            throw new IndexOutOfBoundsException(
                    "Matrix min colIndex = 0, max index = " + (cols - 1) + ". Current colIndex = " + index);
        }
    }

    // a.Получение размеров матрицы
    public int rows() {
        return rows;
    }

    // a.Получение размеров матрицы
    public int cols() {
        return cols;
    }

    // b.Получение и задание вектора-строки по индексу
    public Vector getVectorRow(int index) {
        checkRowIndex(index);

        return new Vector(vectors[index]);
    }

    // b.Получение и задание вектора-строки по индексу
    public void setVectorRow(int index, double... array) {
        checkRowIndex(index);

        if (array.length < cols || array.length > cols) {
            throw new IllegalArgumentException(
                    "Array length = " + array.length + "  is wrong, because cols = " + cols + ".");
        }

        vectors[index] = new Vector(array);
    }

    // c.Получение вектора - столбца по индексу
    public Vector getVectorCol(int index) {
        checkColIndex(index);

        double[] copy = new double[rows];
        for (int i = 0; i < rows; i++) {
            copy[i] = vectors[i].getComponent(index);
        }

        return new Vector(copy);
    }

    // d.Транспонирование матрицы
    public void transpose() {
        int transposeRows = cols;
        int transposeCols = rows;

        Matrix copyMatrix = new Matrix(transposeRows, transposeCols);

        for (int i = 0; i < transposeRows; i++) {
            copyMatrix.vectors[i] = new Vector(getVectorCol(i));
        }

        rows = transposeRows;
        cols = transposeCols;

        vectors = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            vectors[i] = new Vector(copyMatrix.vectors[i]);
        }
    }

    // e.Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < rows; i++) {
            vectors[i].multiplyByScalar(scalar);
        }
    }

    // f.Вычисление определителя матрицы
    public double determinant(int n) {
        // Делаем разложение определителя по первой строке
        if (cols != rows) {
            throw new IllegalArgumentException("Matrix is not square!");
        }

        if (n == 1) {
            return vectors[0].getComponent(0);
        }

        if (n == 2) { // метод вычислнения определителей второго порядка
            return vectors[0].getComponent(0) * vectors[1].getComponent(1) - //
                    vectors[0].getComponent(1) * vectors[1].getComponent(0);
        }

        double det = 0; // Определитель, детерминант

        for (int i = 0; i < n; i++) { // Пробегаемся по всем эл-м первой строки, n-столбцов в i-й строке
            Vector[] minor = new Vector[n - 1];
            int m = 0; // Итератор для минора

            for (int j = 1; j < n; j++) { // j - номер строки, всегда берем с 1 строки, т.к. 0 не участвует
                // Создаем подмассив для инициализации подматрицы
                double[] subArray = new double[n - 1]; // После прохода каждой строчки создаю подмассив

                int l = 0; // Итератор для подмассива

                for (int k = 0; k < n; k++) { // l - Номер столбца
                    if (k != i) {
                        subArray[l] = vectors[j].getComponent(k);
                        l = l + 1;
                    }
                }

                minor[m] = new Vector(subArray);
                m = m + 1;
            }

            Matrix subMatrix = new Matrix(minor);// подмассив, "состоящий из n-1 миноров"

            det += vectors[0].getComponent(i) * Math.pow(-1, i) * subMatrix.determinant(n - 1);
        }

        return det;
    }

    // g.toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
    @Override
    public String toString() {
        if (vectors == null) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{ ");

        for (int i = 0; i < rows; i++) {
            sb.append(vectors[i]).append(", ");
        }

        sb.deleteCharAt(sb.length() - 2);
        sb.append("}");

        return sb.toString();
    }
    // h.Умножение матрицы на вектор
    // i.Сложение матриц
    // j.Вычитание матриц
    // Статические методы. a.Сложение матриц
    // Статические методы. b.Вычитание матриц
    // Статические методы. c.Умножение матриц

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%9.2f", vectors[i].getComponent(j));
            }
            System.out.println("\n");
        }
    }
}
