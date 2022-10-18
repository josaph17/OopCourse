package ru.academits.dashiev.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private static Iterator instance;
    private T[] items; // внутренний массив
    private int size; // длина списка(кол-ва эл-в в списке) = 0, по умолчанию ,
    // вместимость списка , длина списка и длина массива могут

    public MyArrayList() {
        items = (T[]) new Object[5]; // 5м - начальный размер (вместимость, capacity)
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

    public MyArrayList(int length) {
        items = (T[]) new Object[length];
    }

    public void ensureCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public int size() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> instance = new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size || items[currentIndex] != null;
            }

            @Override
            public T next() {
                return items[currentIndex++]; // возвр. текущий элемент и переходит к след.
            }
        };

        return instance;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = (T) o;

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {


        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (size >= items.length) {
            increaseCapacity();
        }

        if (items.length >= c.size()) {
            increaseCapacity();
        }

        int oldSize = size;

        int i = 0;

        for (T t : c) {
            this.add(t);
        }

        return oldSize == size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        T oldValue = items[index];

        items[index] = (T) element;

        return oldValue;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        if (element == null) {
            throw new NullPointerException("Element is null!!!");
        }

        T objectElement = (T) element;

        if (size + 1 >= items.length) {
            increaseCapacity();
        }

        for (int i = size - 1; i >= 0; i--) {
            items[i+1] = items[i];
        }

        items[index] = (T) element;

        size++;
    }

    @Override
    public T remove(int index) {
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
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) { // реализация не нужна
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) { // реализация не нужна
        return null;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass() || obj == null) {
            return false;
        }
        MyArrayList o = (MyArrayList) obj;

        if (size != o.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (items[i] != o.items[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
