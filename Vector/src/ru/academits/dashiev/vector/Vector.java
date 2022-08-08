package ru.academits.dashiev.vector;

public class Vector {
    private int n; // размерность вектора, количество компонетов
    private double vector[]; // массив

    public Vector() {
        vector = new double[5]; // конструктор по умолчанию размерности в 3 эл-а
    }

    public Vector(int n) { //Конструктор, размерность n, количество компонетов
        if (n <= 0) {
            throw new IllegalArgumentException("n must be >0. Current value: " + n); // бросил исключение
        }

        vector = new double[n]; // инициализировали vector, все компоненты равны 0
    }

    public Vector(Vector vector) { //конструктор копирования
        if (vector != null && vector.getSize() > 0 && (vector instanceof Vector)) {
            n = vector.getSize();

            this.vector = new double[vector.getSize()];

            for (int i = 0; i < n; i++) {
                this.vector[i] = vector[i];
            }
        }
    }

    public Vector(double[] array) { // c.заполнение вектора значениями из массива , передать new double{3,4,5}
        for (int i = 0; i < array.length; i++) { // foreach Не подойдет т.к. хотим внести изменения
            this.vector[i] = array[i];
        }
    }

    public Vector(int n, double... array) { /* заполнение вектора значениями из массива. Если длина массива array,length
     меньше n, то считать что в остальных компонентах 0*/
        this(n);

        vector = new double[n];

        for (int i = 0; i < array.length; i++) {
            vector[i] = array[i];
        }
    }

    public int getSize() {  // для получения размерности вектора, зн-я n-мерного пространства
        return n;
    }

    public String toString() {
        for (int i = 0; i < n; i++) {
            System.out.print("{ ");
            for (int j = 0; j < n; j++) {
                System.out.print(vector[j] + ", ");
            }
        }
        System.out.print(" }");

        return "";
    }

    public double add() { // a.Прибавление к вектору другого вектора
        return 1;
    } // a. Прибавление к вектору другого вектора

    public double subtractVector() { // b.Прибавление к вектору другого вектора
        return 1;
    } // b. Вычитание из вектора другого вектора

    public double[] multiplicationVectorScalar(double scalar) { // c.Умножение вектора на скаляр
        Vector newVector = new Vector(vector);

        for (int i = 0; i < n; i++) {
            vector[i] *= scalar;
        }

        return vector;
    }

    public double reverseVector() { // d.Разворот вектора
        return 1;
    }

    public double getVectorLength() { // e.Получение длины вектора

        double vectorLength = 0;

        for (double e : vector) {
            vectorLength += Math.pow(e, 2);
        }

        return Math.sqrt(vectorLength);
    }

    @Override
    public boolean equals(Object obj) { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return true;
    }

    public int hashCode() { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return this.n; /// !!!!!!!!!!!!!
    }

    static double addVector(Vector vector) {
        return 1;
    }

    static double scalarProduct(Vector vector) {
        return 1;
    }
}
