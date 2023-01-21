package ru.academits.dashiev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components; // массив

    public Vector(int size) { // Конструктор, размерность n, количество компонентов
        if (size <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0. Current value: " + size);
        }

        components = new double[size]; // инициализировали vector, все компоненты равны 0
    }

    public Vector(Vector vector) { //конструктор копирования
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        /* если не делать копию, то 2 разных вектора ссылаются на один массив, именения в одном
        приведут к изменения в другом */
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double... array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException(
                    "Array length must be > 0. Current value: " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double... array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be > 0. Current value: " + size);
        }

        if (array.length <= 0) {
            throw new IllegalArgumentException(
                    "Array length must be > 0. Current value: " + array.length); // бросил исключение
        }

        components = Arrays.copyOf(array, size);
    }

    public static Vector getAddSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new NullPointerException("2nd vector is null!");
        }

        Vector result = new Vector(vector1);

        result.add(vector2);

        return result;
    }

    public static Vector getSubtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new NullPointerException("2nd vector is null!");
        }

        Vector result = new Vector(vector1);

        result.subtract(vector2);

        return result;
    }

    // todo Прошу проверить
    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new NullPointerException("2nd vector is null!");
        }

        if (vector1.components.length < vector2.components.length) {
            vector1.components = Arrays.copyOf(vector1.components, vector2.components.length);
        } else if (vector1.components.length > vector2.components.length) {
            vector2.components = Arrays.copyOf(vector2.components, vector1.components.length);
        }

        double result = 0;

        for (int i = 0; i < vector1.components.length; i++) {
            result += (vector1.components[i] * vector2.components[i]);
        }

        return result;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Arrays.toString(components)).setCharAt(0, '{');
        sb.setCharAt(sb.length() - 1, '}');

        return sb.toString();
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (components.length < vector.components.length) {
            // создаем новый массив
            this.components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null!");
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i]; // здесь this оставили, чтобы указать, что это поле
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1); // через this.multiplyByScalar(-1) не надо обращаться
    }

    public double getLength() { // e.Получение длины вектора
        double vectorComponentsSum = 0;

        for (double e : components) {
            vectorComponentsSum += e * e; // умножение т.к. это быстрее Math.pow
        }

        return Math.sqrt(vectorComponentsSum);
    }

    @Override
    public int hashCode() {
        final int prime = 7; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException(
                    "Index = " + index + " out of bounds. Valid index value from 0 to " + (components.length - 1));
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException(
                    "Index = " + index + " out of bounds. Valid index value from 0 to " + (components.length - 1));
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }
}