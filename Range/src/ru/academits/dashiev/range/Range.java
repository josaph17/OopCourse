package ru.academits.dashiev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range(Range b) { // конструктор копирования
        from = b.from;
        to = b.to;
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

    public boolean equals(Range range) {
        return (from == range.from && to == range.to);
    }

    public boolean isInside(double point) {
        return point >= from && to >= point;
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }

    public Range[] getIntersection(Range range) {
        //if (this.from <= newRange.getTo() && this.to >= newRange.getFrom())
        if (this.equals(range)) {
            return new Range[]{(new Range(this))}; // если интервалы одинаковы
        }

        if (to < range.from || range.to < from) {
            return null; // интервалы не пересекаются
        }

        if ((from == range.to && to > range.from) || (to == range.from && from < range.to)) {
            return null; // интервалы пересекаются в одной точке
        }

        if (from == range.from) { // интервалы пересекаются в двух точках , 1-я точка пересечения одинаковая
            return new Range[]{new Range(from, Math.min(to, range.to))};
        }

        if (to == range.to) { // интервалы пересекаются в двух точках , 2-я точка пересечения одинаковая
            return new Range[]{new Range(Math.max(from, range.from), to)};
        }

        return new Range[]{new Range(Math.max(from, range.from), Math.min(to, range.to))}; // интервалы пересек в 2-ч точках
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
        if (from >= range.from && to <= range.to) {
            //левый интервал внутри второго
            Range[] differenceRange = {}; // пустой массив длины 0

            return differenceRange; // нет отрезков, вместо null пустой массив
        }

        if (this.getIntersection(range) == null) {
            // если нет пересечений
            return new Range[]{
                    new Range(this)
            };
        }

        if (from < range.from && to > range.to) {
            // правый интервал внутри левого, 2 отрезка
            return new Range[]{
                    new Range(Math.min(from, range.from), Math.max(from, range.from)),
                    new Range(Math.min(to, range.to), Math.max(to, range.to))
            };
        }

        if (from > range.from) {
            return new Range[]{
                    new Range(range.to, to)
            };
        }

        return new Range[]{
                new Range(from, range.from)
        };
    }
}