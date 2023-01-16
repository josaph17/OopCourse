package ru.academits.dashiev.matrix_main;

import ru.academits.dashiev.matrix.Matrix;
import ru.academits.dashiev.vector.Vector;

public class Main {
    public static void checkConstructors() {
        //        double[][] test = {{0.9, 2.1, 5.2, 4.2}, {3, 42.7, 6.5}, {321, 7.1, 43.9, 111.3}};
        //
        //        Matrix matrix = new Matrix(test);
        //
        //        System.out.println(test.length);
        //        System.out.println(matrix.cols);

        double[] array1 = {21.3, 43.4};
        double[] array2 = {9};
        double[] array3 = {342.9, 7.05, 111, 32132.43, 27};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix vectorsMatrix = new Matrix(vectorsArray);
    }

    public static void checkMethods() {
        double[] array1 = {21.3, 43.4};
        double[] array2 = {9};
        double[] array3 = {342.9, 7.05, 111, 9.43, 27};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        //System.out.println(vectorsMatrix.getRow(3));

        double[] inArray = {3.4, 1111.1, 21.3, 342.4, 565};
        vectorsMatrix.setVectorRow(0, inArray);
        // System.out.println(vectorsMatrix.getVectorRow(0));

        System.out.println("Before transpose");

        System.out.println(vectorsMatrix);

        vectorsMatrix.transpose();

        System.out.println("After transpose");

        System.out.println(vectorsMatrix);
    }

    public static void main(String[] args) {
        // checkMethods();
        double[] array1 = {21.3, 43.4};
        double[] array2 = {9, 9.17, 1.62};
        double[] array3 = {5.9, 7.05, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        double det = vectorsMatrix.determinant(vectorsMatrix.cols());

        System.out.println(det);
    }
}
