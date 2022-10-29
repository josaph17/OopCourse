package ru.academits.dashiev.my_hash_table;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashTable<V> implements Collection<V> {
    private LinkedList<V>[] items; // это точно правильно

    public MyHashTable() {
        items = (LinkedList<V>[]) new Object[20];
    }

    public MyHashTable(int arrayCapacity) {
        items = new LinkedList[arrayCapacity];
    }


    @Override
    public int size() {
        int size = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                size += items[i].size();
            }
        }
        return size;
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
        // объект кот-й позволяет обойти эл-ы коллекции в определннном порядке
        Iterator<V> instance = new Iterator<V>() { //тело анонимного класса
            // Это анонимный класс, та часть, кот-я идет в правой части от присваивания.
            //Созд новый класс кот-й реал интерфейс Iterator<V>, созд О. этого класса и присв перем instance.
            int currentIndex = findFirstElIndex();

            int lastNotNullElementIndex = findLastElIndex();

            @Override
            public boolean hasNext() {
                if (size() != 0 && currentIndex <= lastNotNullElementIndex) {
                    while (items[currentIndex]==null){
                        currentIndex++;
                    }
                    return true;
                }

                return false;
            }

            @Override
            public V next() {
                if(currentIndex >= items.length){
                    throw new NoSuchElementException("Коллекция закончилась!"); // лекция 13 стр.15
                }

                return items[currentIndex++].getFirst();
            }
        };

        return instance;
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

        int index = Math.abs((value != null ? value.hashCode() : 0) % items.length);

        try {
            if (items[index] == null) {
                items[index] = new LinkedList<>();
                items[index].add(value);
            } else {
                items[index].add(value);
            }
        } catch (ArrayStoreException e) {
             e.printStackTrace();
        }

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

    private int findLastElIndex() {
        /*TODO т.к. коллизию обрабатывать не нужно, то логика нахождения пслденего эл-а упрощается не
         *TODO не нужно будет пробегать по эл-м linkedList */
        int findIndex = 0;

        for (int i = items.length - 1; size() != 0; ) {
            if (items[i] == null) { // точно есть элемент, Нно надо откатиться назад
                i--; // индекс последнего элемента
            }

            if (items[i] != null) {
                return i;
            }
        }

        return -1; // нет эл-в в hashTable
    }

    private int findFirstElIndex() {
        for (int i = 0; size() != 0; ) {
            if (items[i] != null) {
                return i;
            }

            i++;
        }

        return -1; // нет эл-в в hashTable
    }
}
