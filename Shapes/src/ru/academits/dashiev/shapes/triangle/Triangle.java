package ru.academits.dashiev.shapes.triangle;

import ru.academits.dashiev.shapes.shape.Shape;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getMax(double a, double b,
                                 double c) { // private, т.к. я использую ф-ю внутри этого класса, она вспомогательная
        return Math.max(Math.max(a, b), c);
    }

    private static double getMin(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    private static double getLength(double a1, double b1, double a2, double b2) {
        return Math.sqrt((a2 - a1) * (a2 - a1) + (b2 - b1) * (b2 - b1));
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3));
    }

    @Override
    public double getPerimeter() {
        return getLength(x1, y1, x2, y2) + getLength(x1, y1, x3, y3) + getLength(x2, y2, x3, y3);
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Triangle. With coordinates (" + x1 + ";" + y1 + "), (" + x2 + ";" + y2 + "), (" + y3 + ";" + y3 + ")" + System.lineSeparator() + "Area = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3 //
                && y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 23; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
