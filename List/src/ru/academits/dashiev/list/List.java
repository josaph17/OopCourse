package ru.academits.dashiev.list;

public class List<T> {
    private T[] items;
    private int size; // вместимость списка , длина массива и вместимость списка могут отличаться

    public List() {
        items = (T[]) new Object[10];
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

    public int size() { // получение размера списка

    }

    public T getHead() { // получение значения первого элемента

    }

    public T getItem(int index) { // получение значения по указанному индексу
        //TODO бросить исключение если выход за size

    }

    public void setItem(int index, T listItem) {
        // изменение значения по указанному индексу, Изменение значения по индексу пусть выдает старое значение.
        //TODO бросить исключение если выход за size

    }

    public void remove(int index) { // удаление элемента по индексу, пусть выдает значение элемента
        //TODO бросить исключение если выход за size

    }

    public void addStart(T item) { // вставка элемента в начало

    }

    public void addIndexItem(int index, T item) { //вставка элемента по индексу
        //TODO бросить исключение если выход за size

    }

    public boolean remove(
            T item) { // удаление узла по значению, пусть выдает true, если элемент был удален

    }

    public T removeStart() { // удаление первого элемента, пусть выдает значение элемента

    }

    public void reverse() { // разворот списка за линейное время

    }

    public void copyList() {

    }
}
