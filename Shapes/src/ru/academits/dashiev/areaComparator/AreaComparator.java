package ru.academits.dashiev.areaComparator;

import ru.academits.dashiev.shapes.shape.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    private static AreaComparator instance;

    private AreaComparator() {
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        double area1 = shape1.getArea();
        double area2 = shape2.getArea();

        if (area1 != area2) {
            return area1 > area2 ? 1 : -1;
        }

        return 0;
    }

    public static AreaComparator getInstance() {
        // вернуть инстанцию объект, паттерн Синглтон,
        // всегда будет существовать один объектб и чтобы никто не сделал new
        // AreaComparator нужно в этом классе сделать приватный конструктор
        if (instance == null) {
            instance = new AreaComparator();
        }

        return instance;
    }
}