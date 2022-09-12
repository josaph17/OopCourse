package shapes_main;

import Circle.Circle;
import Rectangle.Rectangle;
import Square.Square;
import Triangle.Triangle;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(5);
        Triangle triangle1 = new Triangle(2, 3, 1, 1, 7, 4);
        Triangle triangle2 = new Triangle(10, 6, 8, 10, 5, 2);
        Rectangle rectangle1 = new Rectangle(4, 2);
        Rectangle rectangle2 = new Rectangle(1, 9);
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(6);

        System.out.println(square);
        System.out.println(triangle1);
        System.out.println(rectangle1);
        System.out.println(circle2);
    }
}
