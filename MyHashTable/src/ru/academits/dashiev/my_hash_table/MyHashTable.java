package ru.academits.dashiev.my_hash_table;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

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
            throw new NullPointerException("o is null.");
        }

        V element = (V) o;

        int oIndex = Math.abs(element.hashCode() % items.length);

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

            boolean isHashTableEmpty = (size() == 0); // будет созд при созд итератора только раз

            @Override
            public boolean hasNext() {
                if (!isHashTableEmpty && currentIndex <= lastNotNullElementIndex) {
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
                    throw new IndexOutOfBoundsException(
                            "Коллекция закончилась!"); // лекция 13 стр.15
                }

                return items[currentIndex++].getFirst(); //т.к. Колллизии не рассматриваем
            }
        };

        return instance;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];

        int resultIndex = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                for (V v : items[i]) {
                    result[resultIndex++] = v;
                }
            }
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {  // // T- т.к. этот нек-й класс может отличаться от V
        if (a == null) {
            throw new NullPointerException("a is null!");
        }

        int hashTableSize = size();
        T[] hashTableArray = (T[]) toArray();

        if (hashTableSize > a.length) {
            T[] newArray = Arrays.copyOf(a, hashTableSize);

            int i = 0;

            for (T t : hashTableArray) {
                newArray[i++] = t;
            }

            return newArray; // возвр новый ф, но длины хэш Таблицы
        }

        System.arraycopy(hashTableArray, 0, a, 0, hashTableSize); // если равны , на этом закончится

        if (hashTableSize < a.length) {
            a[hashTableSize] = null;
        }

        return a;
    }

    @Override
    public boolean add(V value) {
        if (value == null) {
            throw new NullPointerException("value is null!");
        }

        int index = Math.abs(value.hashCode() % items.length);

        if (items[index] == null) {
            items[index] = new LinkedList<>();
        }

        return items[index].add(
                value); // true т.к. у односвязного списка нет случаев когда он может не добавить
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("o is null!");
        }

        V element = (V) o;

        int oIndex = Math.abs(element.hashCode() % items.length);

        return items[oIndex].remove(o); // чтобы не делать двойного приведения
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        for (Object o : c) {
            if (!contains(o)) { // вместо contains(o) == false
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

        boolean isHashTableChanged = false;

        for (V v : c) {
            add(v);
            isHashTableChanged = true;
        }

        return isHashTableChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        boolean isHashTableChanged = false;

        for (Object o : c) {
            if (remove(o) == true) { // remove(o) выполнится и сразу даст ответ
                isHashTableChanged = true;
            }
        }

        return isHashTableChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        boolean isHashTableChanged = false;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].retainAll(c)) { // если retainAll вернет true
                    isHashTableChanged = true;
                }
            }
        }

        return isHashTableChanged;
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
