package ru.academits.dashiev.shapes_main;

import ru.academits.dashiev.areaComparator.AreaComparator;
import ru.academits.dashiev.perimeterComparator.PerimeterComparator;
import ru.academits.dashiev.shapes.circle.Circle;
import ru.academits.dashiev.shapes.rectangle.Rectangle;
import ru.academits.dashiev.shapes.shape.Shape;
import ru.academits.dashiev.shapes.square.Square;
import ru.academits.dashiev.shapes.triangle.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Square(5), //
                new Triangle(2, 3, 1, 1, 7, 4),//
                new Triangle(10, 6, 8, 10, 5, 2), //
                new Rectangle(4, 2), new Rectangle(1, 9),//
                new Circle(5), new Circle(6)};

        System.out.println("First maxArea element of array[]:");
        Arrays.sort(shapes, AreaComparator.getInstance());
        System.out.println(shapes[shapes.length - 1]);
        System.out.println();

        System.out.println("The second larges maxPerimeter element of array[]:");
        Arrays.sort(shapes, PerimeterComparator.getInstance());
        System.out.println(shapes[shapes.length - 2]);
    }
}
