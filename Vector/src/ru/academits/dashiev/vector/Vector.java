package ru.academits.dashiev.vector;

public class Vector {
    private double value;

    public Vector(int n) { // размерность n, все компоненты равны 0
        double[] vector = new double[n];

        for (double e : vector) {
            e = 0;
        }
    }

    public Vector(Vector vector) { //конструктор копирования
        this.value = vector.value;
    }

    public Vector(double... array) { // заполнение вектора значениями из массива
        for (int i = 0; i < this.length; i++) {
            this
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

    public int getSize() {  // для получения размерности вектора
        return this.length;
    }

    public String toString() {
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print("{ ");
            for (int j = 0; j < this.getSize(); j++) {
                System.out.print(this[j] + ", ");
            }
        }
        System.out.print(" }");

        return 1;
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

    public int getVectorLength() { // f.Получение и установка компоненты вектора по индексу
        return 1;
    }

    public boolean equals() { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return true;
    }

    public int hashCode() { /*g.Переопределить метод equals, чтобы был true  векторы
    имеют одинаковую размерность и соответствующие компоненты равны. */
        return 1;
    }
}
