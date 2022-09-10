package Rectangle;

import shapes.Shape;

public class Rectangle implements Shape {
    int sideLength;

    public Rectangle(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * sideLength * sideLength * sideLength;
    }
}
