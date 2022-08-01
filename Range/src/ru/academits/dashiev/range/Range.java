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

    public boolean isInside(double point) {
        return point >= from && to >= point;
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }

    public Range getIntersection(Range range) {
        //if (this.from <= newRange.getTo() && this.to >= newRange.getFrom())
        if (range.isInside(from)) { // начало промежутка this принадлежит промежутку newRange
            if (range.isInside(to)) {
                return (new Range(from, to));
            }
            if (this.isInside(range.to)) {
                return (new Range(from, range.to));
            }
        }

        if (this.isInside(range.getFrom())) {
            // начало промежутка newRange принадлежит промежутку this
            if (range.isInside(to)) {
                return (new Range(range.from, to));
            }

            if (this.isInside(range.to)) {
                return (new Range(range.from, range.to));
            }
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        // объединение
        if ((from == range.getTo())) {
            return new Range[]{
                    new Range(range.from, to)
            };
        }

        if (to == range.getFrom()) {
            return new Range[]{
                    new Range(from, range.to) // для одного эл-а так делается?
            };

        }

        if (this.getIntersection(range) == null) {
            // если нет пересечений, то объединение из 2-х отрезков
            return new Range[]{
                    new Range(from, to),
                    new Range(range.from, range.to)
            };
        }

        // пересечение в одной точке
        return new Range[]{
                new Range(Math.min(from, range.from), Math.max(from, range.from)),
                new Range(Math.min(to, range.to), Math.max(to, range.to))
        };
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