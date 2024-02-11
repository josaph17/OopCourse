package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Matrix rows count must be > 0. Current rows count = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Matrix columns must be > 0. Current columns count = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }

        rows = new Vector[matrix.rows.length]; // создаем массив векторов

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Array columns count shouldn't be = 0!");
        }

        rows = new Vector[array.length];

        int maxSize = 0;

        for (double[] row : array) {
            if (maxSize < row.length) {
                maxSize = row.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector... vectors) {
        if (vectors == null) {
            throw new NullPointerException("VectorsArray is null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Vectors array size = 0!");
        }

        rows = new Vector[vectors.length];

        int maxSize = 0;

        for (Vector vector : vectors) {
            if (maxSize < vector.getSize()) {
                maxSize = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxSize);

            rows[i].add(vectors[i]);
        }
    }

    private static void checkSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices sizes not match! Matrix1 rows count = " + matrix1.rows.length
                    + ", matrix2 rows count = " + matrix2.rows.length + ", matrix1 columns count = "
                    + matrix1.getColumnsCount() + "matrix2 columns count = " + matrix2.getColumnsCount());
        }
    }

    /**
     * Статические методы. a. Сложение матриц
     */
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    /**
     * Статические методы. b. Вычитание матриц
     */
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    /**
     * Статические методы. c. Умножение матриц
     */
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied! Matrix1 columns count = "
                    + matrix1.getColumnsCount() + " not match to Matrix2 rows count = " + matrix2.rows.length);
        }

        Vector[] productVectors = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            productVectors[i] = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                productVectors[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return new Matrix(productVectors);
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Matrix min row index = 0, max index = " + (rows.length - 1) + ". Current row index = " + index);
        }
    }

    private void checkColumnIndex(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Matrix min column index = 0, max index = " + (getColumnsCount() - 1) + ". Current column index = " + index);
        }
    }

    // a. Получение размеров матрицы
    public int getRowsCount() {
        return rows.length;
    }

    // a. Получение размеров матрицы
    public int getColumnsCount() {
        return rows[0].getSize();
    }

    // b. Получение и задание вектора-строки по индексу
    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    // b. Получение и задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize()
                    + "  is wrong, because columns count = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    // c. Получение вектора - столбца по индексу
    public Vector getColumn(int index) {
        checkColumnIndex(index);

        double[] columnArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            columnArray[i] = rows[i].getComponent(index);
        }

        return new Vector(columnArray);
    }

    // d. Транспонирование матрицы
    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    // e.Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    // f. Вычисление определителя матрицы
    private Matrix getMinor(int removedColumnIndex) {
        double[][] minorRows = new double[rows.length - 1][rows.length - 1];

        // m - Счетчик циклов для минора
        for (int i = 1, m = 0; i < rows.length; i++) { // i - номер строки в rows, всегда берем с 1 строки, т.к. 0 не участвует
            for (int j = 0, k = 0; j < rows.length; j++) {
                if (j != removedColumnIndex) {
                    minorRows[m][k] = rows[i].getComponent(j);
                    k++;
                }
            }

            m++;
        }

        return new Matrix(minorRows);
    }

    public double getDeterminant() {
        // Делаем разложение определителя по первой строке
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Matrix is not square! Rows count = " + rows.length
                    + ", columns count =  " + getColumnsCount());
        }

        int dimension = getColumnsCount();

        // Когда размерность матрицы = 1
        if (dimension == 1) {
            return rows[0].getComponent(0);
        }

        if (dimension == 2) { // метод вычисления определителей второго порядка
            return rows[0].getComponent(0) * rows[1].getComponent(1) //
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0; // Определитель, детерминант

        for (int i = 0, sign = 1; i < dimension; i++, sign = -sign) { // Пробегаемся по всем эл-м первой строки, dimension-столбцов в i-й строке
            // здесь рекурсивный вызов
            determinant += sign * rows[0].getComponent(i) * getMinor(i).getDeterminant();
        }

        return determinant;
    }

    // g.toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (Vector row : rows) {
            sb.append(row).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append('}');

        return sb.toString();
    }

    // h. Умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Matrix columns count = " + getColumnsCount()
                    + " not match to vector elements count = " + vector.getSize());
        }

        double[] multiplicationArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            multiplicationArray[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(multiplicationArray);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }
}