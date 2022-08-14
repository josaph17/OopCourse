package ru.academits.dashiev.vector;

public class Vector {
    private double vectorComponents[]; // массив

    public Vector(int capacity) { //Конструктор, размерность n, количество компонетов
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be >0. Current value: " + capacity); // бросил исключение
        }

        vectorComponents = new double[capacity]; // инициализировали vector, все компоненты равны 0
    }

    public Vector(Vector anotherVector) { //конструктор копирования
        if (anotherVector == null) {
            throw new IllegalArgumentException("Constructor Argument is null!");
        }

        vectorComponents = new double[anotherVector.getSize()];

        for (int i = 0; i < anotherVector.getSize(); i++) {
            vectorComponents[i] = anotherVector.getVectorComponents()[i];
        }
    }

    public Vector(double... array) { // c.заполнение вектора значениями из массива , передать new double{3,4,5}
        for (int i = 0; i < array.length; i++) { // foreach Не подойдет т.к. хотим внести изменения
            this.vectorComponents[i] = array[i];
        }
    }

    public Vector(int capacity, double... array) {
        /* заполнение вектора значениями из массива. Если длина массива array,length
     меньше n, то считать что в остальных компонентах 0*/
        this(capacity); // вызвали конструктор, размерность capacity, количество компонетов

        for (int i = 0; i < array.length; i++) {
            vectorComponents[i] = array[i];
        }
    }

    public double[] getVectorComponents() {
        double[] copyVectorComponents = new double[vectorComponents.length];

        System.arraycopy(copyVectorComponents, 0, vectorComponents, 0, vectorComponents.length);

        return copyVectorComponents;
    }

    public void setVectorComponents(double[] vectorComponents) {
        this.vectorComponents = new double[vectorComponents.length]; /* чтобы переменная this.vectorComponents
        больше не ссылалась на объект, что и vectorComponents*/

        System.arraycopy(vectorComponents, 0, this.vectorComponents, 0, vectorComponents.length);
    }

    public int getSize() {  // для получения размерности вектора, зн-я n-мерного пространства
        return vectorComponents.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (double vC : vectorComponents) {
            sb.append(vC);
        }

        return sb.toString(); // получение резкльтирующей строчки
    }

    public double add() { // a.Прибавление к вектору другого вектора
        return 1;
    } // a. Прибавление к вектору другого вектора

    public double subtractVector() { // b.Прибавление к вектору другого вектора
        return 1;
    } // b. Вычитание из вектора другого вектора

    public double[] multiplicationVectorScalar(double scalar) { // c.Умножение вектора на скаляр
        for (int i = 0; i < vectorComponents.length; i++) { // копию не обяз создавать т.к. scalar не ссылочный тип
            vectorComponents[i] *= scalar;
        }

        return vectorComponents;
    }

    public void reverse() { // d.Разворот вектора
        for (int i = 0; i < vectorComponents.length; i++) { // копию не обяз создавать т.к. scalar не ссылочный тип
            vectorComponents[i] *= -1;
        }
    }

    public double getSum() { // e.Получение длины вектора

        double vectorLength = 0;

        for (double e : vectorComponents) {
            vectorLength += e * e; // умножение т.к. это быстрее Math.pow
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
        return 1; /// !!!!!!!!!!!!!
    }

    static double sumVectors(Vector vector1, Vector vector2) {
        return 1;
    }

    static double subtractionVectors(Vector vector1, Vector vector2) {
        return 1;
    }

    static double scalarProduct(Vector vector1, Vector vector2) {
        return 1;
    }
}
