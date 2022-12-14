package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private int capacity;
    private LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int hashTableSize; // кол-во эл-в
    private int modCount; // кол-во изменений

    public MyHashTable() {
        capacity = 5;

        lists = new LinkedList[capacity];
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;

        //noinspection unchecked
        lists = new LinkedList[capacity];
    }

    @Override
    public int size() {
        return hashTableSize;
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

        E value = (E) o;

        int index = Math.abs(value.hashCode() % lists.length);

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];

        int resultIndex = 0;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                for (E v : lists[i]) {
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
    public boolean add(E value) {
        int index = (value == null) ? 0 : Math.abs(value.hashCode() % capacity);

        // System.out.println("index = " + index);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        hashTableSize = hashTableSize + 1;

        modCount = modCount + 1;

        // true т.к. у односвязного списка нет случаев когда он может не добавить
        return lists[index].add(value);
    }

    @Override
    public boolean remove(Object o) {
        if (hashTableSize == 0) {
            return false; // когда список пуст
        }

        int index = Math.abs(o.hashCode() % lists.length);

        boolean result = lists[index].remove(o);

        if (result) {
            hashTableSize = hashTableSize - 1;

            modCount = modCount + modCount;
        }

        return result; // чтобы не делать двойного приведения
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
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        boolean isHashTableChanged = false;

        for (E v : c) {
            add(v);
            isHashTableChanged = true;
        }

        return isHashTableChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        boolean isHashTableChanged = false;

        for (Object o : c) {
            if (remove(o)) { // remove(o) выполнится и сразу даст ответ
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

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (lists[i].retainAll(c)) { // если retainAll вернет true
                    isHashTableChanged = true;
                }
            }
        }

        return isHashTableChanged;
    }

    @Override
    public void clear() {
        if (hashTableSize == 0) {
            return; // ничего не делать если таблица уже пустая
        }

        for (int i = 0; i < lists.length; i++) {
            lists[i] = null;
        }

        hashTableSize = 0;
    }

    @Override
    public String toString() {
        if (hashTableSize == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        Iterator<E> iterator = new MyIterator();

        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        final int iteratorModCount = modCount;

        int indexOfElementInTable = -1; // индекс элемента во всей таблице
        int indexOfListInArray = 0; // индекс списка в массиве
        int indexOfElementInList = 0; // индекс элемента в списке

        @Override
        public boolean hasNext() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("Collection changed!");
            }

            return indexOfElementInTable + 1 < hashTableSize;
        }

        @Override
        public E next() {
            if (indexOfElementInTable >= hashTableSize) {
                // лекция 13 стр.15
                throw new NoSuchElementException("Нет больше элементов в HashTable");
            }

            for (int i = indexOfListInArray; indexOfListInArray < capacity;) {
                if (!lists[indexOfListInArray].isEmpty() && indexOfElementInList < lists[indexOfListInArray].size()) {
                    indexOfElementInTable++;

                    return lists[indexOfListInArray].get(indexOfElementInList++);
                } else {
                    indexOfElementInList = 0;
                    indexOfListInArray++;
                }
            }

            return null;
        }
    }

    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>(3);

        hashTable1.add(88);
        hashTable1.add(111);
        hashTable1.add(432);
        hashTable1.add(null);
        hashTable1.add(32);

        System.out.println(hashTable1);
    }
}