package ru.academits.dashiev.comparators;

import ru.academits.dashiev.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    private static PerimeterComparator instance;

    public PerimeterComparator() {
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }

}
