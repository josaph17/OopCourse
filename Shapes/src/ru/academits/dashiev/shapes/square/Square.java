package ru.academits.dashiev.shapes.square;

import ru.academits.dashiev.shapes.shape.Shape;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Square. side = " + sideLength + "\nArea = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Square square = (Square) o;

        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 17; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
