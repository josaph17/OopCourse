package Triangle;

import shapes.Shape;

public class Triangle implements Shape {
    double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return max(x1, x2, x3) - min(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return max(y1, y2, y3) - min(y1, y2, y3);
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3));
    }

    @Override
    public double getPerimeter() {
        double side1 = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double side2 = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
        double side3 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));

        return side1 + side2 + side3;
    }

    public double max(double number1, double number2, double number3) {
        return Math.max(Math.max(number1, number2), number3);
    }

    public double min(double number1, double number2, double number3) {
        return Math.min(Math.min(number1, number2), number3);
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        return "Triangle. With coordinates (" + x1 + ";" + y1 + "), (" + x2 + ";" + y2 + "), (" + y3 + ";" + y3 + ")" +
                "\nArea = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this || o == null && this == null) {
            return true;
        }

        if (o == null || this == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle anotherSquare = (Triangle) o;

        if (this.x1 != anotherSquare.x1 || this.y1 != anotherSquare.y1 || this.x2 != anotherSquare.x2 ||
                this.y2 != anotherSquare.y2 || this.x3 != anotherSquare.x3 || this.y3 != anotherSquare.y3) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 94; // с помощью этого числа (константное число) вычисляют хэш-код
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
