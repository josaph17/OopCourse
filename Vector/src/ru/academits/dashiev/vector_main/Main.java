package ru.academits.dashiev.vector_main;
import ru.academits.dashiev.vector.Vector;

public class Main {
    public static void printVector(Vector vector) {
        for (double e: vector) {
            System.out.print(e + " ");
        }
    }
    
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
    }    
}
