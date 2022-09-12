package Circle;

import shapes.Shape;

public class Circle implements Shape {
    double radius;

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
        return 3.14 * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * 3.14 * radius;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Радиус окружности равен " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o == null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Circle anotherSquare = (Circle) o;

        if (this.radius != anotherSquare.radius) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 94; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }

}
