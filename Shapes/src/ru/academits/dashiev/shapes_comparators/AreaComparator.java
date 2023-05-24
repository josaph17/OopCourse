package ru.academits.dashiev.shapes_comparators;

import ru.academits.dashiev.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        if (shape1 == null && shape2 == null) {
            return 0;
        }

        if (shape1 == null){
            return -1;
        }

        if (shape2 == null){
            return 1;
        }

        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}