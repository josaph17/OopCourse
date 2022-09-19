package ru.academits.dashiev.list;

public class List <T>{
    private T[] items;
    private int length; // вместимость списка

    public  List(){
        items = (T[]) new Object[10];
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

}
