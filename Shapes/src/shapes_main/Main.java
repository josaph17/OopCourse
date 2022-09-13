package shapes_main;

import Circle.Circle;
import Rectangle.Rectangle;
import Square.Square;
import Triangle.Triangle;
import shapes.Shape;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(5);
        Triangle triangle1 = new Triangle(2, 3, 1, 1, 7, 4);
        Triangle triangle2 = new Triangle(10, 6, 8, 10, 5, 2);
        Rectangle rectangle1 = new Rectangle(4, 2);
        Rectangle rectangle2 = new Rectangle(1, 9);
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(6);

        Shape[] array = {square, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};

        Comparator<Shape> areaComparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                if (o1.getArea() != o2.getArea()) {
                    return o1.getArea() > o2.getArea() ? 1 : -1;
                } else {
                    return 0;
                }
            }
        };

        Comparator<Shape> perimeterComparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                if (o1.getPerimeter() != o2.getPerimeter()) {
                    return o1.getPerimeter() > o2.getPerimeter() ? 1 : -1;
                } else {
                    return 0;
                }
            }
        };

        System.out.println("Using areaComparator:\n");

        Arrays.sort(array, areaComparator);

        for (Shape e : array) {
            System.out.println(e);
            System.out.println();
        }

        System.out.println("Using areaComparator:\n");

        Arrays.sort(array, perimeterComparator);

        for (Shape e : array) {
            System.out.println(e);
            System.out.println();
        }
    }
}
