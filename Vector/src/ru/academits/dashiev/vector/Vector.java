package ru.academits.dashiev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components; // массив

    public Vector(int size) { //Конструктор, размерность n, количество компонентов
        if (size <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0. Current value: " + size); // бросил исключение
        }

        components = new double[size]; // инициализировали vector, все компоненты равны 0
    }

    public Vector(Vector vector) { //конструктор копирования
        if (vector.components.length <= 0) {
            throw new IllegalArgumentException("vector length must be > 0. Current value: " +
                    vector.components.length); // бросил исключение
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        components = new double[vector.components.length];

        for (int i = 0; i < vector.components.length; i++) {
            components[i] = vector.components[i];
        }
    }

    public Vector(double... array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("array length must be > 0. Current value: " + array.length); // бросил исключение
        }

        components = new double[array.length];

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int capacity, double... array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("array length must be > 0. Current value: " + array.length); // бросил исключение
        }

        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be > 0. Current value: " + capacity); // бросил исключение
        }

        if (array == null) {
            throw new IllegalArgumentException("array is null!");
        }

        components = new double[capacity];
        components = Arrays.copyOf(array, capacity);

        if (capacity > array.length) {
            components = Arrays.copyOf(array, capacity);

            for (int i = array.length; i < capacity; i++) {
                components[i] = 0;
            }
        }
    }

    public int size() {
        return components.length;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return Arrays.toString(components);
    }

    public void add(Vector vector) {
        if (components == null) {
            throw new IllegalArgumentException("components is null!");
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        double[] vectorComponentsCopy = null;

        if (components.length <= vector.components.length) {
            vectorComponentsCopy = new double[vector.components.length];

            vectorComponentsCopy = Arrays.copyOf(components, vector.components.length);
        } else {
            vectorComponentsCopy = new double[components.length];
            vectorComponentsCopy = Arrays.copyOf(components, components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            vectorComponentsCopy[i] += vector.components[i];
        }

        components = vectorComponentsCopy;
    }

    public void subtract(Vector vector) {
        if (components == null) {
            throw new IllegalArgumentException("components is null!");
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector is null!");
        }

        double[] vectorComponentsCopy = null;

        if (components.length <= vector.components.length) {
            vectorComponentsCopy = new double[vector.components.length];

            vectorComponentsCopy = Arrays.copyOf(components, vector.components.length);
        } else {
            vectorComponentsCopy = new double[components.length];
            vectorComponentsCopy = Arrays.copyOf(components, components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            vectorComponentsCopy[i] -= vector.components[i];
        }

        components = vectorComponentsCopy;
    }

    public void multiplyByScalar(double scalar) {
        if (components == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        if (components == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        for (int i = 0; i < components.length; i++) { // копию не обяз создавать т.к. scalar не ссылочный тип
            components[i] *= -1;
        }

        /* ф-я void т.к. она меняет текущий вектор,
         метод не должен возвращать ссылку на массив компонент класса - это нарушение инкапсуляции */
    }

    public double getLength() { // e.Получение длины вектора
        double vectorLength = 0;

        for (double e : components) {
            vectorLength += e * e; // умножение т.к. это быстрее Math.pow
        }

        return Math.sqrt(vectorLength);
    }

    public double getVectorComponent(int index) {
        if (components == null) {
            throw new IllegalArgumentException("Vector is null!");
        }

        double[] copyVectorComponents = new double[components.length];
        copyVectorComponents = Arrays.copyOf(components, components.length);

        return copyVectorComponents[index];
    }

    public void setVectorComponent(int index, double value) {
        if (components == null) {
            throw new IllegalArgumentException("Vector is null!");
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Index = " + index + " out of bounds, because length = " + components.length);
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o == null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (components.length != vector.components.length) {
            return false;
        }

        for (int i = 0; i < components.length; ) {
            if (components[i] == vector.components[i]) {
                i++;
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 94; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector vector1Copy = new Vector(vector1);
        Vector vector2Copy = new Vector(vector2);
        Vector result = null;

        if (vector1.components.length <= vector2.components.length) { // Длина vector2 больше или раввно
            result = new Vector(vector2.components.length);
        } else {
            result = new Vector(vector1.components.length);
        }

        vector1Copy.add(vector2Copy);

        result = vector1Copy;

        return result;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector vector1Copy = new Vector(vector1);
        Vector vector2Copy = new Vector(vector2);
        Vector result = null;

        if (vector1.components.length <= vector2.components.length) { // Длина vector2 больше или раввно
            result = new Vector(vector2.components.length);
        } else {
            result = new Vector(vector1.components.length);
        }

        vector1Copy.subtract(vector2Copy);

        result = vector1Copy;

        return result;
    }

    public static Vector multiplyByScalar(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("1st vector is null!");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("2nd vector is null!");
        }

        Vector result = null;
        int multiplyByScalarIndex = 0;

        if (vector1.components.length <= vector2.components.length) {
            result = new Vector(vector2.components.length);

            multiplyByScalarIndex = vector1.components.length;
        } else {
            result = new Vector(vector1.components.length);

            multiplyByScalarIndex = vector2.components.length;
        }

        for (int i = 0; i < multiplyByScalarIndex; i++) {
            result.setVectorComponent(i, vector1.components[i] * vector2.components[i]);
        }

        return result;
    }
}