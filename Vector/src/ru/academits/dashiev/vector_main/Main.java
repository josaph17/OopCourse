package ru.academits.dashiev.vector_main;

import ru.academits.dashiev.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void checkConstructors() {
        System.out.println("-- Check constructors --");

        System.out.println();

        int size = 5;

        System.out.println("Vector(int size), size = " + size);
        Vector test1 = new Vector(5);
        System.out.println(test1);
        System.out.println();

        double[] array = {6.5, 5.5, 0, 11111.9, 5};
        Vector test2 = new Vector(array);
        System.out.println("Vector(double... array), array[] = " + Arrays.toString(array));
        System.out.println(test2);
        System.out.println();

        Vector test3 = new Vector(test2);
        System.out.println("Vector(Vector vector), vector = " + test3);
        System.out.println(test3);
        System.out.println();

        size = 3;
        Vector test4 = new Vector(size, array);
        System.out.println(
                "Vector(int size, double... array), size = " + size + ", array[] = " + Arrays.toString(
                        array));
        System.out.println(test4);
        System.out.println();
    }

    public static void checkNonStaticOperations() {
        System.out.println("-- Check non static operations --");

        double[] array1 = {10.5, 4.3, 0};
        Vector vector1 = new Vector(array1);

        double[] array2 = {1, 1, 1, 4.4, 5};
        Vector vector2 = new Vector(array2);

        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);

        System.out.println();

        System.out.println("vector1.add(vector2)");
        vector1.add(vector2);
        System.out.println(vector1);

        System.out.println();

        System.out.println("vector1.subtract(vector2)");
        vector1.subtract(vector2);
        System.out.println(vector1);

        System.out.println();

        System.out.println("vector1.multiplyByScalar(double scalar)");
        int scalar = 5;
        vector1.multiplyByScalar(scalar);
        System.out.println(vector1);

        System.out.println();

        System.out.println("vector1.reverse()");
        vector1.reverse();
        System.out.println(vector1);

        System.out.println();

        System.out.println("vector2.getSum()");
        System.out.println(vector2.getLength());

        System.out.println();

        int index = 2;
        System.out.println("vector2 = " + vector2 + ", index = " + index);
        System.out.println("vector2.getVectorComponent(index)");
        System.out.println(vector2.getComponent(index));

        System.out.println();

        index = 3;
        double value = -21.4;
        System.out.println("vector2 = " + vector2 + ", index = " + index + ", value = " + value);
        System.out.println("vector2.setVectorComponent(index, value)");
        vector2.setComponent(index, value);
        System.out.println(vector2);

        System.out.println();
    }

    public static void checkStaticOperations(Vector vector1, Vector vector2) {
        System.out.println("--Check vector static operations--");

        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);

        System.out.println();

        System.out.print("Vectors Sum: ");
        Vector vectorsSum = Vector.getAddSum(vector2, vector1);
        System.out.println(vectorsSum);

        System.out.print("Vectors Subtraction: ");
        Vector vectorsSubtraction = Vector.getSubtract(vector1, vector2);
        System.out.println(vectorsSubtraction);

        System.out.print("Vectors MultiplyByScalar: ");
        double vectorsScalarMultiply = Vector.getVectorsScalarMultiply(vector2, vector1);
        System.out.println(vectorsScalarMultiply);

        System.out.println();
    }

    public static void main(String[] args) {
        checkConstructors();
        checkNonStaticOperations();

        double[] array1 = {1, 1, 1, 2, 5};
        Vector vector1 = new Vector(array1);

        double[] array2 = {9, 9, 9, 8, 5, 5, 5};
        Vector vector2 = new Vector(array2);

        checkStaticOperations(vector1, vector2);

        System.out.println("--Check equals--");
        System.out.println();
        System.out.println("vector1.equals(vector2)");
        System.out.println(vector1.equals(vector2));
    }
}