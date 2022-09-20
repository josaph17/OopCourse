package ru.academits.dashiev.list;

import ru.academits.dashiev.list_item.ListItem;

public class List <T>{
    private T[] items;
    private ListItem<T> Head;
    private int length; // вместимость списка

    public  List(){
        items = (T[]) new Object[10];
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

}
