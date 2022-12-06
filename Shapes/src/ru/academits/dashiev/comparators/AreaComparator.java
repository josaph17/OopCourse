package ru.academits.dashiev.comparators;

import ru.academits.dashiev.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    private static AreaComparator instance;

    public AreaComparator() {
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        // use instead of if
        return Double.compare(shape1.getArea(), shape2.getArea());
    }

}