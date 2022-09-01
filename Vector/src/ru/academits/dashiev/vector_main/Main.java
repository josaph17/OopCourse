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
        double[] array = {21, 23421, 77, 65, 56};
        //Vector newVector = new Vector(6, array);

        Vector newVector = new Vector(array);

        System.out.println(newVector);
    }
}
