package Square;

import shapes.Shape;

public class Square implements Shape {
    double side;

    public Square(double sideLength) {
        this.side = sideLength;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Square. side = " + side + "\nArea = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o == null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Square anotherSquare = (Square) o;

        if (this.side != anotherSquare.side) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 94; // с помощью этого числа (константное число) вычисляют хэш-код
        int hash = 1;  // начальное значение хэш-кода

        hash = prime * hash + Double.hashCode(side);

        return hash;
    }
}
