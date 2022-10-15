package ru.academits.dashiev.arrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java.util.Arrays;

public class ArrayList<T> implements List {
    private T[] items;
    private int size; // 0, по умолчанию , вместимость списка , длина массива и вместимость списка могут отличаться

    public ArrayList() {
        items = (T[]) new Object[10];
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

    public int size() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
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

        T oldValue = items[index];

        items[index] = listItem;

        return oldValue;
    }
}
