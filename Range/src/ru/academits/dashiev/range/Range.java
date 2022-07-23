package ru.academits.dashiev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
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
        return point - from >= 0 && to - point >= 0;
    }

    public String toString() {
        return "(" + from + ", " + to + ")";
    }

    public Range getIntersect(Range newRange) { // ф-я пересечения
        //if (this.from <= newRange.getTo() && this.to >= newRange.getFrom())
        if (newRange.isInside(this.from)) { // начало промежутка this приндлежит промежутку newRange
            if (newRange.isInside(this.to)) {
                return (new Range(this.from, this.to));
            }
            if (this.isInside(newRange.getTo())) {
                return (new Range(this.from, newRange.getTo()));
            }
        }

        if (this.isInside(newRange.getFrom())) { // начало промежутка newRange приндлежит промежутку this
            if (newRange.isInside(this.to)) {
                return (new Range(newRange.getFrom(), this.to));
            }

            if (this.isInside(newRange.getTo())) {
                return (new Range(newRange.getFrom(), newRange.getTo()));
            }
        }

        return null;
    }

    public Range[] getUnion(Range newRange) { // объединение
        if (this.getIntersect(newRange) == null) { // если нет пересечений, то объединение из 2-х отрезков

            Range[] unionRange = new Range[2];
            unionRange[0] = this;
            unionRange[1] = newRange;

            return unionRange;
        }

        Range[] unionRange = new Range[1];
        unionRange[0] = new Range(Math.min(this.from, newRange.getFrom()), Math.max(this.to, newRange.getTo()));
        return unionRange;
    }
}

