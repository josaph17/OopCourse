package ru.academits.dashiev.vector;

public class Vector {
    private double vectorValue; // значение вектора
    private int n; // размерность вектора, количество компонетов
    private double vector[]; // массив

    public Vector(int n) { // размерность n, количество компонетов
        this.n = n;

        double[] vector = new double[this.n ];

        for (int i = 0; i < this.n ; i++) {
            vector[i] = 0;
        }
    }

    public Vector(Vector newVector) { //конструктор копирования

        this.vectorValue = newVector.vectorValue;
    }

    public Vector(double... array) { // c.заполнение вектора значениями из массива , передать new double{3,4,5}
        for (int i = 0; i < this.n; i++) {
            vector[i] = array[i];
        }
    }

    public Vector(int n, double... array) { /* заполнение вектора значениями из массива. Если длина массива меньше n, то считать
     что в остальных компонентах 0*/
        for (double e : this) {
            e = 0;
        }

        for (int i = 0; i < this.length; i++) {
            this[i] = array[i];
        }
    }

    public int getSize() {  // для получения размерности вектора, зн-я n-мерного пространства

        return this.n;
    }

    public String toString() {
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print("{ ");
            for (int j = 0; j < this.getSize(); j++) {
                System.out.print(this[j] + ", ");
            }
        }
        System.out.print(" }");

        return "";
    }

    public double addVector() { // a.Прибавление к вектору другого вектора
        return 1;
    }

    public double deleteVector() { // b.Прибавление к вектору другого вектора
        return 1;
    }

    public double multiplicationVectorScalar() { // c.Умножение вектора на скаляр
        return 1;
    }

    public double reverseVector() { // d.Разворот вектора
        return 1;
    }

    public int getVectorLength() { // e.Получение длины вектора

        return 1;
    }


    public boolean equals() { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return true;
    }

    public int hashCode() { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return this.n; /// !!!!!!!!!!!!!
    }
}
