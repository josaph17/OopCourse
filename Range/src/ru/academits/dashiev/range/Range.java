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
        double epsilon = 1.0e-10;

        return point - from >= -epsilon && to - point >= -epsilon;
    }

    public String crossInterval(double from, double to, double from2, double to2) {
        if (this.from <= to2 && this.to >= from2) {
            System.out.printf("Интервал пересечения двух интервалов {%.2f , %.2f}", from2, to2);
            return " ";
        }

        return null;
    }

}

