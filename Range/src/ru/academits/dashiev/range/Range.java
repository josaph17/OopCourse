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

    public Range intersect(Range newRange) {
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
}

