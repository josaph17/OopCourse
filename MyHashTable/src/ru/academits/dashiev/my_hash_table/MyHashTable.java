package ru.academits.dashiev.my_hash_table;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<V> implements Collection<V> {
    private LinkedList<V>[] items; // это точно правильно
    private int arraySize;

    public MyHashTable() {
        arraySize = 20;

        items = (LinkedList<V>[]) new Object[arraySize];
    }

    public MyHashTable(int arraySize) {
        this.arraySize = arraySize;

        items = new LinkedList[arraySize];
    }


    @Override
    public int size() {
        return arraySize;
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
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(V value) {

        int index = Math.abs((value != null ? value.hashCode() : 0) % arraySize);

        try {
            System.out.println(items.getClass());
            System.out.println(value.getClass());
            items[index].add(value);
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        }
        System.out.println("Continuing execution");


        return items[index] != null;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
