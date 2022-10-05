package ru.academits.dashiev.shapes.circle;

import ru.academits.dashiev.shapes.shape.Shape;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Circle. Radius = " + radius + System.lineSeparator() + "Area = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Circle circle = (Circle) o;

        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 13; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }

}
