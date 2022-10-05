package ru.academits.dashiev.perimeterComparator;

import ru.academits.dashiev.shapes.shape.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    private static PerimeterComparator instance;

    private PerimeterComparator() {
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        double perimeter1 = shape1.getPerimeter();
        double perimeter2 = shape2.getPerimeter();

        if (perimeter1 != perimeter2) {
            return perimeter1 > perimeter2 ? 1 : -1;
        }

        return 0;
    }

    public static PerimeterComparator getInstance() {
        if (instance == null)
            instance = new PerimeterComparator();
        return instance;
    }
}
