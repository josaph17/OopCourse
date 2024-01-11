package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Matrix rows count must be > 0. Current rows = " + rowsCount);
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
        /* 7. Matrix(double[][] array). Нужно проверять, что получившееся количество столбцов не равно 0.
        * Прошу объяснить зачем это проверять, Поскольку мы не сможем создать матрицу через конструктор, где
        * Количество столбцов равно 0*/
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Array length = 0 !");
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
            throw new IllegalArgumentException("VectorsArray size = 0 !");
        }

        rows = new Vector[vectors.length];

        int maxLength = 0;

        for (Vector vector : vectors) {
            if (maxLength < vector.getSize()) {
                maxLength = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxLength);

            rows[i].add(vectors[i]);
        }
    }

    private static void checkMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.rows[0].getSize() != matrix2.rows[0].getSize()) {
            throw new IllegalArgumentException(
                    "Matrices sizes not match! Matrix1 rows count = " + matrix1.rows.length +
                            ", matrix2 rows count = " + matrix2.rows.length +
                            ", matrix1 columns count = " + matrix1.rows[0].getSize() +
                            "matrix2 columns count = " + matrix2.rows[0].getSize());
        }
    }

    /**
     * Статические методы. a. Сложение матриц
     */
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    /**
     * Статические методы. b. Вычитание матриц
     */
    public static Matrix getSubtractionResult(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    /**
     * Статические методы. c. Умножение матриц
     */
    public static Matrix getMultiplicationResult(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesForMultiplication(matrix1, matrix2);

        Vector[] multiplicationMatrix = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            multiplicationMatrix[i] = new Vector(matrix2.rows[0].getSize());

            for (int j = 0; j < matrix2.rows[i].getSize(); j++) {
                multiplicationMatrix[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return new Matrix(multiplicationMatrix);
    }

    private static void checkMatricesSizesForMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException(
                    "Matrices cannot be multiplied! Matrix1 columns count = " + matrix1.rows[0].getSize() +
                            " not match to Matrix2 rows count = " + matrix2.rows.length);
        }
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Matrix min row index = 0, max index = " + (rows.length - 1) + ". Current row index = " + index);
        }
    }

    private void checkColumnIndex(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException(
                    "Matrix min column index = 0, max index = " + (rows[0].getSize() - 1) + ". Current column index = " + index);
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

        if (rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize() + "  is wrong, because columns count = " + rows[0].getSize());
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
        Vector[] newRows = new Vector[rows[0].getSize()];

        for (int i = 0; i < rows[0].getSize(); i++) {
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
        Vector[] minorRows = new Vector[rows.length - 1];
        int m = 0; // Счетчик циклов для минора

        for (int i = 1; i < rows.length; i++) { // i - номер строки, всегда берем с 1 строки, т.к. 0 не участвует
            // Создаем подмассив для инициализации минора
            double[] arrayRow = new double[rows.length - 1]; // После прохода каждой строчки создаю подмассив

            // column - номер столбца, k - счетчик циклов для подмассива
            for (int j = 0, k = 0; j < rows.length; j++) {
                if (j != removedColumnIndex) {
                    arrayRow[k] = rows[i].getComponent(j);
                    k++;
                }
            }

            minorRows[m] = new Vector(arrayRow);
            m++;
        }

        return new Matrix(minorRows);
    }

    public double getDeterminant() {
        // Делаем разложение определителя по первой строке
        if (rows.length != rows[0].getSize()) {
            throw new UnsupportedOperationException("Matrix is not square! Rows count = " + rows.length + ", columns count =  " + rows[0].getSize());
        }

        int dimension = rows[0].getSize();

        // Когда размерность матрицы = 1
        if (dimension == 1) {
            return rows[0].getComponent(0);
        }

        if (dimension == 2) { // метод вычисления определителей второго порядка
            return rows[0].getComponent(0) * rows[1].getComponent(1) - //
                    rows[0].getComponent(1) * rows[1].getComponent(0);
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

        sb.append("{");

        for (Vector row : rows) {
            sb.append(row).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }

    // h. Умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException(
                    "Matrix columns count = " + rows[0].getSize() + " not match to vector elements count = " + vector.getSize());
        }

        double[] multiplicationByVectorMatrix = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            multiplicationByVectorMatrix[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(multiplicationByVectorMatrix);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }
}