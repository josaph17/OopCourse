package ru.academits.dashiev.list;

import java.util.Arrays;

public class List<T> {
    private T[] items;
    private int size; // 0, по умолчанию , вместимость списка , длина массива и вместимость списка могут отличаться

    public List() {
        items = (T[]) new Object[10];
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

    public int size() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
    }

    public T getHead() { // получение значения первого элемента
        return  items[0];
    }

    public T getItem(int index) { // получение значения по указанному индексу
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + size + ".Current value = " + index); // выход за sizes
        }

        return items[index];
    }

    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public T setItem(int index, T listItem) {
        // изменение значения по указанному индексу, Изменение значения по индексу пусть выдает старое значение.
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + size + ".Current value = " + index); // выход за sizes
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        T oldValue  = items[index];

        items[index] = listItem;

        return oldValue;
    }

    public void remove(int index) { // удаление элемента по индексу, пусть выдает значение элемента
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + size + ".Current value = " + index); // выход за sizes
        }


    }

    public void addStart(T item) { // вставка элемента в начало

    }

    public void addIndexItem(int index, T item) { //вставка элемента по индексу
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + size + ".Current value = " + index); // выход за sizes
        }

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
