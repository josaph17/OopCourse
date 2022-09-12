package shapes_main;

import Circle.Circle;
import Rectangle.Rectangle;
import Square.Square;
import Triangle.Triangle;
import shapes.Shape;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(5);
        Triangle triangle1 = new Triangle(2, 3, 1, 1, 7, 4);
        Triangle triangle2 = new Triangle(10, 6, 8, 10, 5, 2);
        Rectangle rectangle1 = new Rectangle(4, 2);
        Rectangle rectangle2 = new Rectangle(1, 9);
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(6);

        Shape[] array = new Shape[]{square, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};
    }
}
