package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int rows_count;
    private int cols_cunt;

    private Vector[] data;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Matrix rows must be > 0. Current rows = " + n);
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Matrix cols must be > 0. Current cols = " + m);
        }

        rows_count = n;
        cols_cunt = m;

        data = new Vector[n]; // обязательно проинициализируем vectors

        for (int i = 0; i < n; i++) {
            data[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }

        rows_count = matrix.rows_count;
        cols_cunt = matrix.cols_cunt;

        data = new Vector[rows_count]; // создаем массив векторов

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(matrix.data[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        rows_count = array.length;

        data = new Vector[rows_count];

        // Находим кол-во столбцов матрицы
        for (int i = 0; i < rows_count; i++) {
            int currentCapacity = array[i].length;

            if (currentCapacity > cols_cunt) {
                cols_cunt = currentCapacity;
            }
        }

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(Arrays.copyOf(array[i], cols_cunt));
        }
    }

    public Matrix(Vector[] vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null");
        }

        rows_count = vector.length;

        data = new Vector[vector.length];

        // Находим максимальную длину подВектора
        for (int i = 0; i < rows_count; i++) {
            int currentCapacity = vector[i].getSize();

            if (currentCapacity > cols_cunt) {
                cols_cunt = currentCapacity;
            }
        }

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(cols_cunt);

            for (int j = 0; j < vector[i].getSize(); j++) {
                data[i].setComponent(j, vector[i].getComponent(j));
            }
        }
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows_count) {
            throw new IndexOutOfBoundsException(
                    "Matrix min rowIndex = 0, max index = " + (rows_count - 1) + ". Current rowIndex = " + index);
        }
    }

    private void checkColIndex(int index) {
        if (index < 0 || index >= cols_cunt) {
            throw new IndexOutOfBoundsException(
                    "Matrix min colIndex = 0, max index = " + (cols_cunt - 1) + ". Current colIndex = " + index);
        }
    }

    // a.Получение размеров матрицы
    public int rows() {
        return rows_count;
    }

    // a.Получение размеров матрицы
    public int cols() {
        return cols_cunt;
    }

    // b.Получение и задание вектора-строки по индексу
    public Vector getVectorRow(int index) {
        checkRowIndex(index);

        return new Vector(data[index]);
    }

    // b.Получение и задание вектора-строки по индексу
    public void setVectorRow(int index, double... array) {
        checkRowIndex(index);

        if (array.length < cols_cunt || array.length > cols_cunt) {
            throw new IllegalArgumentException(
                    "Array length = " + array.length + "  is wrong, because cols = " + cols_cunt + ".");
        }

        data[index] = new Vector(array);
    }

    // c.Получение вектора - столбца по индексу
    public Vector getVectorCol(int index) {
        checkColIndex(index);

        double[] copy = new double[rows_count];
        for (int i = 0; i < rows_count; i++) {
            copy[i] = data[i].getComponent(index);
        }

        return new Vector(copy);
    }

    // d.Транспонирование матрицы
    public void transpose() {
        int transposeRows = cols_cunt;
        int transposeCols = rows_count;

        Matrix copyMatrix = new Matrix(transposeRows, transposeCols);

        for (int i = 0; i < transposeRows; i++) {
            copyMatrix.data[i] = new Vector(getVectorCol(i));
        }

        rows_count = transposeRows;
        cols_cunt = transposeCols;

        data = new Vector[rows_count];

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(copyMatrix.data[i]);
        }
    }

    // e.Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < rows_count; i++) {
            data[i].multiplyByScalar(scalar);
        }
    }

    // f.Вычисление определителя матрицы
    public double calculateDet(){
        return determinant(cols_cunt);
    }

    private double determinant(int n) {
        // Делаем разложение определителя по первой строке
        if (cols_cunt != rows_count) {
            throw new IllegalArgumentException("Matrix is not square!");
        }

        if (n == 1) {
            return data[0].getComponent(0);
        }

        if (n == 2) { // метод вычислнения определителей второго порядка
            return data[0].getComponent(0) * data[1].getComponent(1) - //
                    data[0].getComponent(1) * data[1].getComponent(0);
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
                        subArray[l] = data[j].getComponent(k);
                        l = l + 1;
                    }
                }

                minor[m] = new Vector(subArray);
                m = m + 1;
            }

            Matrix subMatrix = new Matrix(minor);// подмассив, "состоящий из n-1 миноров"

            det += data[0].getComponent(i) * Math.pow(-1, i) * subMatrix.determinant(n - 1);
        }

        return det;
    }

    // g.toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
    @Override
    public String toString() {
        if (data == null) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{ ");

        for (int i = 0; i < rows_count; i++) {
            sb.append(data[i]).append(", ");
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
        for (int i = 0; i < rows_count; i++) {
            for (int j = 0; j < cols_cunt; j++) {
                System.out.printf("%9.2f", data[i].getComponent(j));
            }
            System.out.println("\n");
        }
    }
}
