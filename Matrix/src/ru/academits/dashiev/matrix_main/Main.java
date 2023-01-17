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
        double[] array1 = {21.3, 43.4, 3.2, 6, 1};
        double[] array2 = {9, 0, 1.62, 10};
        double[] array3 = {5.9, 7.05, 8};
        double[] array4 = {0, 1.9, 0, 2.28};
        double[] array5 = {0, 3.72, 4.9, 21, 38};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);
        Vector vector5 = new Vector(array5);

        Vector[] vectorsArray = {vector1, vector2, vector3, vector4, vector5};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        //double[] inArray = {3.4, 1111.1, 21.3, 342.4, 565};
        //vectorsMatrix.setVectorRow(0, inArray);
        // System.out.println(vectorsMatrix.getVectorRow(0));

        System.out.println("Before transpose");

        System.out.println(vectorsMatrix);

        //vectorsMatrix.transpose();
        //System.out.println("After transpose");
        //System.out.println(vectorsMatrix);

        double det = vectorsMatrix.calculateDet();

        System.out.println(det);
    }

    public  static  void check(){
        double[] array1 = {1};
        double[] array2 = {2};
        double[] array3 = {3};
        double[] array4 = {4};
        double[] array5 = {5};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);
        Vector vector5 = new Vector(array5);

        Vector[] vectorsArray = {vector1, vector2, vector3, vector4, vector5};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        double[] halo = {2, 1.1, 3, 2.07, 1.5};

        Vector multiplyVector = new Vector(halo);

        vectorsMatrix.multiplyByVector(multiplyVector);

        vectorsMatrix.printMatrix();
    }

    public static void main(String[] args) {
        double[] array1 = {21.3, 43.4, 3.2};
        double[] array2 = {9, 0, 1.62, 10};
        double[] array3 = {5.9, 7.05, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);


        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix vectorsMatrix = new Matrix(vectorsArray);

        double[] array4 = {21.3, 43.4, 3.2};
        double[] array5 = {9, 0, 1.62, 10};
        double[] array6 = {5.9, 7.05, 92};

        Vector vector4 = new Vector(array4);
        Vector vector5 = new Vector(array5);
        Vector vector6 = new Vector(array6);

        Vector[] vectorsArray2 = {vector4, vector5, vector6};

        Matrix vectorsMatrix2 = new Matrix(vectorsArray2);

        Matrix result = Matrix.getSubtract(vectorsMatrix,vectorsMatrix2);

        result.printMatrix();
    }
}