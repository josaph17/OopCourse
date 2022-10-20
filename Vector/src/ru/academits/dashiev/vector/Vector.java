package ru.academits.dashiev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components; // массив

    public Vector(int size) { //Конструктор, размерность n, количество компонентов
        if (size <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be > 0. Current value: " + size); // бросил исключение
        }

        components = new double[size]; // инициализировали vector, все компоненты равны 0
    }

    public Vector(Vector vector) { //конструктор копирования
        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        components = Arrays.copyOf(vector.components, vector.components.length); // если не делать копию,
        // то 2 разных вектора ссылаются на один массив, именения в одном привед к изменения к другому
    }

    public Vector(double... array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException(
                    "array length must be > 0. Current value: " + array.length); // бросил исключение
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double... array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException(
                    "array length must be > 0. Current value: " + array.length); // бросил исключение
        }

        if (size < 0) {
            throw new IllegalArgumentException(
                    "size must be => 0. Current value: " + size); // бросил исключение
        }

        components = Arrays.copyOf(array, size);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static Vector multiplyByScalar(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector result; // так можно делать !!!
        int multiplyByScalarIndex;

        if (vector1.components.length <= vector2.components.length) {
            result = new Vector(vector2.components.length);

            multiplyByScalarIndex = vector1.components.length;
        } else {
            result = new Vector(vector1.components.length);

            multiplyByScalarIndex = vector2.components.length;
        }

        for (int i = 0; i < multiplyByScalarIndex; i++) {
            result.setComponent(i, vector1.components[i] * vector2.components[i]);
        }

        return result;
    }

    public int size() {
        return components.length;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        StringBuilder sb = new StringBuilder();

        sb.append(Arrays.toString(components)).setCharAt(0,'{');
        sb.setCharAt(sb.length()-1, '}');

        return sb.toString();
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        if (this.components.length <= vector.components.length) {
            this.components = Arrays.copyOf(this.components,
                                            vector.components.length); // создаем новый массив
        }

        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        double[] vectorComponentsCopy = null;

        if (this.components.length <= vector.components.length) {
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] -= vector.components[i]; // здесь this осдавили, чтобы указать, что это поле
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

        for (double e : this.components) {
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
                    "index = " + index + " out of bounds. Valid index value from 0 to " + (components.length - 1));
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException(
                    "index = " + index + " out of bounds. Valid index value from 0 to " + (components.length - 1));
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