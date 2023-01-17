package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int rows_count;
    private int cols_count;

    private Vector[] data;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Matrix rows must be > 0. Current rows = " + n);
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Matrix cols must be > 0. Current cols = " + m);
        }

        rows_count = n;
        cols_count = m;

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
        cols_count = matrix.cols_count;

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

            if (currentCapacity > cols_count) {
                cols_count = currentCapacity;
            }
        }

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(Arrays.copyOf(array[i], cols_count));
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

            if (currentCapacity > cols_count) {
                cols_count = currentCapacity;
            }
        }

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(cols_count);

            for (int j = 0; j < vector[i].getSize(); j++) {
                data[i].setComponent(j, vector[i].getComponent(j));
            }
        }
    }

    // Статические методы. a.Сложение матриц
    public static Matrix getAddSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows_count != matrix2.rows_count || matrix1.cols_count != matrix2.cols_count) {
            throw new IllegalArgumentException("Matrices sizes not match!");
        }

        Matrix result = new Matrix(matrix1);

        result.add(matrix2);

        return result;
    }

    public static Matrix getSubtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows_count != matrix2.rows_count || matrix1.cols_count != matrix2.cols_count) {
            throw new IllegalArgumentException("Matrices sizes not match!");
        }

        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }


    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows_count) {
            throw new IndexOutOfBoundsException(
                    "Matrix min rowIndex = 0, max index = " + (rows_count - 1) + ". Current rowIndex = " + index);
        }
    }

    private void checkColIndex(int index) {
        if (index < 0 || index >= cols_count) {
            throw new IndexOutOfBoundsException(
                    "Matrix min colIndex = 0, max index = " + (cols_count - 1) + ". Current colIndex = " + index);
        }
    }

    // a.Получение размеров матрицы
    public int rows() {
        return rows_count;
    }

    // a.Получение размеров матрицы
    public int cols() {
        return cols_count;
    }

    // b.Получение и задание вектора-строки по индексу
    public Vector getVectorRow(int index) {
        checkRowIndex(index);

        return new Vector(data[index]);
    }

    // b.Получение и задание вектора-строки по индексу
    public void setVectorRow(int index, double... array) {
        checkRowIndex(index);

        if (array.length < cols_count || array.length > cols_count) {
            throw new IllegalArgumentException(
                    "Array length = " + array.length + "  is wrong, because cols = " + cols_count + ".");
        }

        data[index] = new Vector(array);
    }

    // c.Получение вектора - столбца по индексу
    public Vector getVectorColumn(int index) {
        checkColIndex(index);

        double[] copy = new double[rows_count];
        for (int i = 0; i < rows_count; i++) {
            copy[i] = data[i].getComponent(index);
        }

        return new Vector(copy);
    }

    // d.Транспонирование матрицы
    public void transpose() {
        int transposeRows = cols_count;
        int transposeCols = rows_count;

        Matrix copyMatrix = new Matrix(transposeRows, transposeCols);

        for (int i = 0; i < transposeRows; i++) {
            copyMatrix.data[i] = new Vector(getVectorColumn(i));
        }

        rows_count = transposeRows;
        cols_count = transposeCols;

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
    public double calculateDet() {
        return determinant(cols_count);
    }

    private Matrix getMinor(int removes_j) {
        Vector[] minor = new Vector[rows_count - 1];
        int m = 0; // Итератор для минора

        for (int i = 1; i < rows_count; i++) { // j - номер строки, всегда берем с 1 строки, т.к. 0 не участвует
            // Создаем подмассив для инициализации минора
            double[] subArray = new double[rows_count - 1]; // После прохода каждой строчки создаю подмассив

            // j - номер столбца, k - итератор для подмассива
            for (int j = 0, k = 0; j < rows_count; j++) {
                if (j != removes_j) {
                    subArray[k] = data[i].getComponent(j);
                    k = k + 1;
                }
            }

            minor[m] = new Vector(subArray);
            m = m + 1;
        }

        return new Matrix(minor);
    }

    private double determinant(int dimension) {
        // Делаем разложение определителя по первой строке
        if (cols_count != rows_count) {
            throw new IllegalArgumentException("Matrix is not square!");
        }

        // Когда размерность матрицы = 1
        if (dimension == 1) {
            return data[0].getComponent(0);
        }

        if (dimension == 2) { // метод вычислнения определителей второго порядка
            return data[0].getComponent(0) * data[1].getComponent(1) - //
                    data[0].getComponent(1) * data[1].getComponent(0);
        }

        double det = 0; // Определитель, детерминант

        for (int i = 0; i < dimension; i++) { // Пробегаемся по всем эл-м первой строки, dimension-столбцов в i-й строке
            Matrix subMatrix = getMinor(i);

            det += data[0].getComponent(i) * Math.pow(-1, i) * subMatrix.determinant(dimension - 1);
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
    public void multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (cols_count > 1) {
            throw new IllegalArgumentException("Matrix columns count > 1!");
        }

        if (rows_count != vector.getSize()) {
            throw new IllegalArgumentException("Matrix rows count not match to vector size!");
        }

        Vector multiplier = getVectorColumn(0);

        for (int i = 0; i < rows_count; i++) {
            data[i] = new Vector(vector);
        }

        cols_count = rows_count; // Важное изменение

        for (int i = 0; i < rows_count; i++) {
            data[i].multiplyByScalar(multiplier.getComponent(i));
        }
    }

    // i.Сложение матриц
    private void checkMatricesSize(int rows1_count, int cols1_count, int rows2_count,
                                   int cols2_count) {
        if (rows1_count != rows2_count || cols1_count != cols2_count) {
            throw new IllegalArgumentException("Matrices sizes not match!");
        }
    }

    public void add(Matrix matrix) {
        checkMatricesSize(rows_count, cols_count, matrix.rows_count, matrix.cols_count);

        for (int i = 0; i < rows_count; i++) {
            data[i].add(matrix.data[i]);
        }
    }

    // j.Вычитание матриц
    public void subtract(Matrix matrix) {
        checkMatricesSize(rows_count, cols_count, matrix.rows_count, matrix.cols_count);

        for (int i = 0; i < rows_count; i++) {
            data[i].subtract(matrix.data[i]);
        }
    }

    // Статические методы. b.Вычитание матриц
    // Статические методы. c.Умножение матриц

    public void printMatrix() {
        for (int i = 0; i < rows_count; i++) {
            for (int j = 0; j < cols_count; j++) {
                System.out.printf("%9.2f", data[i].getComponent(j));
            }
            System.out.println("\n");
        }
    }
}
