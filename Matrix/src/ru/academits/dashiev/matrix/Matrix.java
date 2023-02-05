package ru.academits.dashiev.matrix;

import ru.academits.dashiev.vector.Vector;

public class Matrix {
    private int cols_count;

    private Vector[] matrixRow;

    public Matrix(int rowsCount, int colsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Matrix rows must be > 0. Current rows = " + rowsCount);
        }

        if (colsCount <= 0) {
            throw new IllegalArgumentException("Matrix cols must be > 0. Current cols = " + colsCount);
        }

        matrixRow = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            matrixRow[i] = new Vector(colsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }

        matrixRow = new Vector[matrix.matrixRow.length]; // создаем массив векторов

        for (int i = 0; i < matrixRow.length; i++) {
            matrixRow[i] = new Vector(matrix.matrixRow[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Array length = 0 !");
        }

        matrixRow = new Vector[array.length];

        int maxLength = 0;

        for (double[] items : array) {
            if (maxLength < items.length) {
                maxLength = items.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            matrixRow[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector... vectorsArray) {
        if (vectorsArray == null) {
            throw new NullPointerException("VectorsArray is null");
        }

        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("VectorsArray length = 0 !");
        }

        matrixRow = new Vector[vectorsArray.length];

        int maxLength = 0;

        for (Vector vector : vectorsArray) {
            if (maxLength < vector.getSize()) {
                maxLength = vector.getSize();
            }
        }

        for (int i = 0; i < vectorsArray.length; i++) {
            matrixRow[i] = new Vector(maxLength);

            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                matrixRow[i].setComponent(j, vectorsArray[i].getComponent(j));
            }
        }
    }

    private static void checkMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixRow.length != matrix2.matrixRow.length || matrix1.matrixRow[0].getSize() != matrix2.matrixRow[0].getSize()) {
            throw new IllegalArgumentException(
                    "Matrices sizes not match! Matrix1 rows count = " + matrix1.matrixRow.length + //
                            ", matrix2 rows count = " + matrix2.matrixRow.length + //
                            ", matrix1 columns count = " + matrix1.matrixRow[0].getSize() + //
                            "matrix2 columns count = " + matrix2.matrixRow[0].getSize());
        }
    }

    /**
     * Статические методы. a.Сложение матриц
     */
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    /**
     * Статические методы. b.Вычитание матриц
     */
    public static Matrix getSubtract(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    /**
     * Статические методы. c.Умножение матриц
     */
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixRow[0].getSize() != matrix2.matrixRow.length) {
            throw new IllegalArgumentException(
                    "Matrices sizes not equals! Matrix1 cols count = " + matrix1.matrixRow[0].getSize() //
                            + ", matrix2 rows count = " + matrix2.matrixRow.length);
        }

        Vector[] result = new Vector[matrix1.matrixRow.length];

        for (int i = 0; i < matrix1.matrixRow.length; i++) {
            result[i] = new Vector(matrix2.matrixRow[0].getSize());

            for (int j = 0; j < matrix2.matrixRow[i].getSize(); j++) {
                result[i].setComponent(j, Vector.getScalarMultiply(matrix1.matrixRow[i],
                                                                   matrix2.getColumn(j)));
            }
        }

        return new Matrix(result);
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= matrixRow.length) {
            throw new IndexOutOfBoundsException("Matrix min row index = 0, max index = " + (matrixRow.length - 1) + ". Current row index = " + index);
        }
    }

    private void checkColumnIndex(int index) {
        if (index < 0 || index >= matrixRow[0].getSize()) {
            throw new IndexOutOfBoundsException(
                    "Matrix min column index = 0, max index = " + (matrixRow[0].getSize() - 1) + ". Current column index = " + index);
        }
    }

    // a.Получение размеров матрицы
    public int getRows() {
        return matrixRow.length;
    }

    // a.Получение размеров матрицы
    public int getColumns() {
        return matrixRow[0].getSize();
    }

    // b.Получение и задание вектора-строки по индексу
    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(matrixRow[index]);
    }

    // b.Получение и задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (matrixRow[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize() + "  is wrong, because columns count = " + matrixRow[0].getSize());
        }

        matrixRow[index] = new Vector(vector);
    }

    // c.Получение вектора - столбца по индексу
    public Vector getColumn(int index) {
        checkColumnIndex(index);

        double[] columnArray = new double[matrixRow.length];

        for (int i = 0; i < matrixRow.length; i++) {
            columnArray[i] = matrixRow[i].getComponent(index);
        }

        return new Vector(columnArray);
    }

    // d.Транспонирование матрицы
    public void transpose() {
        Vector[] newMatrixRow = new Vector[matrixRow[0].getSize()];

        for (int i = 0; i < matrixRow[0].getSize(); i++) {
            newMatrixRow[i] = getColumn(i);
        }

        matrixRow = newMatrixRow;
    }

    // e.Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < matrixRow.length; i++) {
            matrixRow[i].multiplyByScalar(scalar);
        }
    }

    // f.Вычисление определителя матрицы
    private Matrix getMinor(int removesColumn) {
        Vector[] array = new Vector[matrixRow.length - 1];
        int m = 0; // Счетчик циклов для минора

        for (int i = 1; i < matrixRow.length; i++) { // i - номер строки, всегда берем с 1 строки, т.к. 0 не участвует
            // Создаем подмассив для инициализации минора
            double[] lowerOrderArray = new double[matrixRow.length - 1]; // После прохода каждой строчки создаю подмассив

            // column - номер столбца, k - счетчик циклов для подмассива
            for (int j = 0, k = 0; j < matrixRow.length; j++) {
                if (j != removesColumn) {
                    lowerOrderArray[k] = matrixRow[i].getComponent(j);
                    k++;
                }
            }

            array[m] = new Vector(lowerOrderArray);
            m++;
        }

        return new Matrix(array);
    }

    public double getDeterminant() {
        // Делаем разложение определителя по первой строке
        if (matrixRow.length != matrixRow[0].getSize()) {
            throw new UnsupportedOperationException("Matrix is not square! Rows count = " + matrixRow.length + ", columns count =  " + matrixRow[0].getSize());
        }

        int dimension = matrixRow[0].getSize();

        // Когда размерность матрицы = 1
        if (dimension == 1) {
            return matrixRow[0].getComponent(0);
        }

        if (dimension == 2) { // метод вычислнения определителей второго порядка
            return matrixRow[0].getComponent(0) * matrixRow[1].getComponent(1) - //
                    matrixRow[0].getComponent(1) * matrixRow[1].getComponent(0);
        }

        double determinant = 0; // Определитель, детерминант

        for (int i = 0, sign = 1; i < dimension; i++, sign = -sign) { // Пробегаемся по всем эл-м первой строки, dimension-столбцов в i-й строке
            // TODO "Уменьшил" шаг для рекурсии, когда созд матр меньшего размера
            Matrix minor = getMinor(i);

            // здесь рекурсивный вызов
            determinant += sign * matrixRow[0].getComponent(i) * minor.getDeterminant();
        }

        return determinant;
    }

    // g.toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
    @Override
    public String toString() {
        if (matrixRow == null) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (int i = 0; i < matrixRow.length; i++) {
            sb.append(matrixRow[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }

    // h.Умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (matrixRow[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException(
                    "Matrix columns count = " + matrixRow[0].getSize() + " not match to vector elements count = " + vector.getSize());
        }

        double[] result = new double[matrixRow.length];

        for (int i = 0; i < matrixRow.length; i++) {
            result[i] = Vector.getScalarMultiply(matrixRow[i], vector);
        }

        return new Vector(result);
    }

    // i.Сложение матриц
    public void add(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < matrixRow.length; i++) {
            matrixRow[i].add(matrix.matrixRow[i]);
        }
    }

    // j.Вычитание матриц
    public void subtract(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < matrixRow.length; i++) {
            matrixRow[i].subtract(matrix.matrixRow[i]);
        }
    }
}