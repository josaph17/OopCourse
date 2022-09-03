package ru.academits.dashiev.vector_main;

import ru.academits.dashiev.vector.Vector;

public class Main {
    public static void main(String[] args) {
//        Vector vector = new Vector(5);
//
//        Vector vector2 = new Vector(3);
//        vector2.setVectorComponent(0, 1);
//        vector2.setVectorComponent(1, 2);
//        vector2.setVectorComponent(2, 3);
//
//        System.out.println(vector2);
//
//        Vector vector3  = new Vector(vector2);
//        System.out.println(vector3);

        //double[] array = {21, 23421, 77, 65, 56};
//        double[] array = {21, 23421, 77, 65, 56};
//        //Vector newVector = new Vector(6, array);
//
//        Vector newVector = new Vector(array);
//        newVector.multiplyByScalar(2);
//
//        System.out.println(newVector);
//
//        newVector.reverse();
//        System.out.println(newVector);
//
//        newVector.setVectorComponent(3, 777);
//        System.out.println(newVector);


//        double[] array1 = {21, 23421, 77, 65, 56};
//        Vector vector1 = new Vector(array1);
//        double[] array2 = {21, 23421, 77, 65, 56, 32, 7};
//        Vector vector2 = new Vector(array2);

//        double[] array1 = {21, 23421, 77, 65, 56, 32, 7};
//        Vector vector1 = new Vector(array1);
//        double[] array2 = {21, 23421, 77, 65, 56};
//        Vector vector2 = new Vector(array2);

        double[] array1 = {1, 1, 1, 2, 5};
        Vector vector1 = new Vector(array1);
        double[] array2 = {9, 9, 9, 8, 5, 5, 5};
        Vector vector2 = new Vector(array2);

//        System.out.println("----------");
//        System.out.println("Subtract Vector.subtraction(another Vector)");
//        vector1.subtract(vector2);
//        System.out.println(vector1);

        System.out.println("----------");
        System.out.println("Add Vector.subtraction(another Vector)");
        vector1.add(vector2);
        System.out.println(vector1);
    }
}
