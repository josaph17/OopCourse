package ru.academits.dashiev.vector;

import java.util.Arrays;

public class Vector {
    private double vectorComponents[]; // массив

    public Vector(int capacity) { //Конструктор, размерность n, количество компонетов
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be >0. Current value: " + capacity); // бросил исключение
        }

        vectorComponents = new double[capacity]; // инициализировали vector, все компоненты равны 0
        //System.out.println("Copy constructor (int capacity)");
    }

    public Vector(Vector anotherVector) { //конструктор копирования
        if (anotherVector == null) {
            throw new IllegalArgumentException("Constructor Argument is null!");
        }

        vectorComponents = new double[anotherVector.getSize()];

        for (int i = 0; i < anotherVector.getSize(); i++) {
            vectorComponents[i] = anotherVector.getVectorComponent(i);
        }
    }

    public Vector(double... array) {
        vectorComponents = new double[array.length];

        vectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(int capacity, double... array) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >0. Current value: " + capacity); // бросил исключение
        }

        if (array == null) {
            throw new IllegalArgumentException("Constructor Argument is null!");
        }

        vectorComponents = new double[capacity];
        vectorComponents = Arrays.copyOf(array, capacity);

        if (capacity > array.length) {
            vectorComponents = Arrays.copyOf(array, capacity);

            for (int i = array.length; i < capacity; i++) {
                vectorComponents[i] = 0;
            }
        }
    }

    public int getSize() {
        return vectorComponents.length;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return Arrays.toString(vectorComponents);
    }

    public void add(Vector anotherVector) {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        if (anotherVector == null) {
            throw new IllegalArgumentException("AnotherVector is null!");
        }

        double[] vectorComponentsCopy = null;

        if (vectorComponents.length <= anotherVector.getSize()) {
            vectorComponentsCopy = new double[anotherVector.getSize()];

            vectorComponentsCopy = Arrays.copyOf(vectorComponents, anotherVector.getSize());
        } else {
            vectorComponentsCopy = new double[vectorComponents.length];
            vectorComponentsCopy = Arrays.copyOf(vectorComponents, vectorComponents.length);
        }

        for (int i = 0; i < anotherVector.getSize(); i++) {
            vectorComponentsCopy[i] += anotherVector.getVectorComponent(i);
        }

        vectorComponents = vectorComponentsCopy;
    }

    public void subtract(Vector anotherVector) {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        if (anotherVector == null) {
            throw new IllegalArgumentException("AnotherVector is null!");
        }

        double[] vectorComponentsCopy = null;

        if (vectorComponents.length <= anotherVector.getSize()) {
            vectorComponentsCopy = new double[anotherVector.getSize()];

            vectorComponentsCopy = Arrays.copyOf(vectorComponents, anotherVector.getSize());
        } else {
            vectorComponentsCopy = new double[vectorComponents.length];
            vectorComponentsCopy = Arrays.copyOf(vectorComponents, vectorComponents.length);
        }

        for (int i = 0; i < anotherVector.getSize(); i++) {
            vectorComponentsCopy[i] -= anotherVector.getVectorComponent(i);
        }

        vectorComponents = vectorComponentsCopy;
    }

    public void multiplyByScalar(double scalar) {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] *= scalar;
        }
    }

    public void reverse() {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("VectorComponents is null!");
        }

        for (int i = 0; i < vectorComponents.length; i++) { // копию не обяз создавать т.к. scalar не ссылочный тип
            vectorComponents[i] *= -1;
        }

        /* ф-я void т.к. она меняет текущий вектор,
         метод не должен возвращать ссылку на массив компонент класса - это нарушение инкапсуляции */
    }

    public double getLength() { // e.Получение длины вектора
        double vectorLength = 0;

        for (double e : vectorComponents) {
            vectorLength += e * e; // умножение т.к. это быстрее Math.pow
        }

        return Math.sqrt(vectorLength);
    }

    public double getVectorComponent(int index) {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("Vector is null!");
        }

        double[] copyVectorComponents = new double[vectorComponents.length];
        copyVectorComponents = Arrays.copyOf(vectorComponents, vectorComponents.length);

        return copyVectorComponents[index];
    }

    public void setVectorComponent(int index, double value) {
        if (vectorComponents == null) {
            throw new IllegalArgumentException("Vector is null!");
        }

        if (index >= vectorComponents.length) {
            throw new IndexOutOfBoundsException("Index = " + index + " out of bounds, because length = " + vectorComponents.length);
        }

        vectorComponents[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o ==null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Vector anotherVector = (Vector) o;

        if (vectorComponents.length != anotherVector.getSize()) {
            return false;
        }

        for (int i = 0; i < vectorComponents.length; ) {
            if (vectorComponents[i] == anotherVector.getVectorComponent(i)) {
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

        hash = prime * hash + Arrays.hashCode(vectorComponents);

        return 1;
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

        if (vector1.getSize() <= vector2.getSize()) { // Длина vector2 больше или раввно
            result = new Vector(vector2.getSize());
        } else {
            result = new Vector(vector1.getSize());
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

        if (vector1.getSize() <= vector2.getSize()) { // Длина vector2 больше или раввно
            result = new Vector(vector2.getSize());
        } else {
            result = new Vector(vector1.getSize());
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

        if (vector1.getSize() <= vector2.getSize()) {
            result = new Vector(vector2.getSize());

            multiplyByScalarIndex = vector1.getSize();
        } else {
            result = new Vector(vector1.getSize());

            multiplyByScalarIndex = vector2.getSize();
        }

        for (int i = 0; i < multiplyByScalarIndex; i++) {
            result.setVectorComponent(i, vector1.getVectorComponent(i) * vector2.getVectorComponent(i));
        }

        return result;
    }
}