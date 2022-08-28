package ru.academits.dashiev.range;

import java.util.Arrays;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range(Range range) { // конструктор копирования
        from = range.from;
        to = range.to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    @Override
    public boolean equals(Object object) { // неявноее приведение к базовому классу
        if (object == this) { // в java Проверяем через This
            return true;
        }

        if (object == null || object.getClass() != getClass()) { // возвр тип объекта
            return false;
        }

        Range range = (Range) object; // явно приводим к наследнику

        return from == range.from && to == range.to; // сравниваем поля
    }

    public boolean isInside(double point) {
        return point >= from && to >= point;
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }


    public Range getIntersection(Range range) {
        if (to <= range.from || range.to <= from) {
            return null; // интервалы не пересекаются
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to)); // интервалы пересек
    }

    public Range[] getUnion(Range range) {
        if (to < range.from || range.to < from) { // если интервалы не пересек
            return new Range[]{
                    new Range(this),
                    new Range(range)
            };
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        // разность
        if (from >= range.from && to <= range.to) { // могу ли я убрать equals(range) ? или он нужен
            return new Range[0]; // если интервалы одинаковы или this находится внутри второго интервала, то пустой массив
        }

        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(new Range(from, range.from)),
                    new Range(new Range(range.to, to))
            }; // 2 интервала
        }

        if (to <= range.from || range.to <= from) {
            return new Range[]{new Range(this)}; // интервалы не пересекаются
        }

        if (to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{new Range(from, range.from)};
    }
}