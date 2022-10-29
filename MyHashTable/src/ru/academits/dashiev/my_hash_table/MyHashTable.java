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
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("0 is null.");
        }

        V element = (V) o;

        int oIndex = Math.abs((element != null ? element.hashCode() : 0) % items.length);

        if (items[oIndex] == null) {
            return false;
        }

        return items[oIndex].contains(o);
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
                    while (items[currentIndex] == null) {
                        currentIndex++;
                    }
                    return true;
                }

                return false;
            }

            @Override
            public V next() {
                if (currentIndex >= items.length) {
                    throw new NoSuchElementException("Коллекция закончилась!"); // лекция 13 стр.15
                }

                return items[currentIndex++].getFirst();
            }
        };

        return instance;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];

        int i = 0;
        Iterator<V> iterator = iterator();

        while(iterator.hasNext()){
            result[i++] = iterator.next();
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(V value) {
        int oldSize = size();

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

        return size() != oldSize;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("o is null!");
        }

        V element = (V) o;

        int oIndex = Math.abs((element != null ? element.hashCode() : 0) % items.length);

        return items[oIndex].remove(o); // чтобы не делать двойного приведения
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        for (Object o : c) {
            if (contains(o) == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        int oldSize = size();

        for (V v : c) {
            add(v);
        }

        return size() != oldSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        int oldSize = size();

        for (Object o : c) {
            remove(o);
        }

        return size() != oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = size();

        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        //TODO здесь надо идти по нашему итератору верно?

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                items[i].retainAll(c);
            }
        }

        return size() != oldSize;
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                items[i] = null;
            }
        }
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
