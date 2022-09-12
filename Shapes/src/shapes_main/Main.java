package shapes_main;

import Circle.Circle;
import Rectangle.Rectangle;
import Square.Square;
import Triangle.Triangle;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(5);
        Triangle triangle = new Triangle(2, 3, 1, 1, 7, 4);
        Rectangle rectangle1 = new Rectangle(4, 2);
        Rectangle rectangle2 = new Rectangle(1, 9);
        Circle circle = new Circle(5);

        System.out.println(triangle);
    }
}
