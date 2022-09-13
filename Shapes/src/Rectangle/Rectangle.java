package Rectangle;

import shapes.Shape;

public class Rectangle implements Shape {
    double width;
    double length;

    public Rectangle(double side1, double side2) {
        if (side1 > side2) {
            length = side1;
            width = side2;
        } else {
            length = side2;
            width = side1;
        }
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public double getPerimeter() {
        return 2 * width + 2 * length;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Rectangle. Width = " + width + ", length = " + length + "\nArea = " + getArea() +
                ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o == null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Rectangle anotherSquare = (Rectangle) o;

        if (this.width != anotherSquare.width || this.length != anotherSquare.length) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 94; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(length);

        return hash;
    }
}
