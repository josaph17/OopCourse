package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private int capacity;
    private LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int hashTableSize; // кол-во эл-в
    private int modCount; // кол-во изменений

    public MyHashTable() {
        capacity = 5;

        //noinspection unchecked
        lists = new LinkedList[capacity]; // создание массива списков без указания типа
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;

        //noinspection unchecked
        lists = new LinkedList[capacity];
    }

    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>(3);

        hashTable1.add(88);
        hashTable1.add(432);
        hashTable1.add(32);
        hashTable1.add(null);
        hashTable1.add(32);
        hashTable1.add(54);
        hashTable1.add(null);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(432);
        arrayList.add(null);

        System.out.println(hashTable1.retainAll(arrayList));

        System.out.println(hashTable1);
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
        if (hashTableSize == 0) {
            return false;
        }

        int index = (o == null) ? 0 : Math.abs(o.hashCode() % capacity);

        if (lists[index] != null) {
            return lists[index].contains(o);
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];

        int index = 0;

        for (E item : this) {
            array[index] = item;

            index = index + 1;
        }

        return array;
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
    public boolean remove(Object o) {
        if (hashTableSize == 0) {
            return false; // когда список пуст
        }

        int index = (o == null) ? 0 : Math.abs(o.hashCode() % capacity);

        boolean result = lists[index].remove(o);

        if (result) {
            hashTableSize = hashTableSize - 1;

            modCount = modCount + modCount;
        }

        return result; // чтобы не делать двойного приведения
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        System.out.println(this);

        int oldModCount = modCount;

        for (Object item : c) {
            boolean isRemoved = true;

            while (isRemoved) {
                isRemoved = remove(item);
            }
        }

        return oldModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldHashTableSize = hashTableSize;

        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        if (hashTableSize == 0) { // HashTable is empty
            return false;
        }

        Iterator<E> iterator = iterator();

        for (Object item : this) {
            while (iterator.hasNext()) {
                E value = iterator.next();

                if (!c.contains(value)) {
                    iterator.remove();
                }
            }
        }


        return oldHashTableSize != hashTableSize;
    }

    @Override
    public void clear() {
        if (hashTableSize == 0) {
            return; // ничего не делать если таблица уже пустая
        }

        Arrays.fill(lists, null);

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
        int indexOfElementInList = -1; // индекс элемента в списке

        @Override
        public boolean hasNext() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("Hashtable changed!");
            }

            return indexOfElementInTable + 1 < hashTableSize;
        }

        @Override
        public E next() {
            if (indexOfElementInTable >= hashTableSize) {
                // лекция 13 стр.15
                throw new NoSuchElementException("Нет больше элементов в HashTable");
            }

            indexOfElementInList = indexOfElementInList + 1;

            for (int i = indexOfListInArray; indexOfListInArray < capacity; ) {
                if (!lists[indexOfListInArray].isEmpty() && indexOfElementInList < lists[indexOfListInArray].size()) {
                    indexOfElementInTable = indexOfElementInTable + 1;

                    return lists[indexOfListInArray].get(indexOfElementInList);
                } else {
                    indexOfElementInList = 0;
                    indexOfListInArray = indexOfListInArray + 1;
                }
            }

            return null;
        }

        @Override
        public void remove() {
            int listSize = lists[indexOfListInArray].size();

            System.out.println(
                    "indexOfElementInTable = " + indexOfElementInTable + " , deleted in list[" + indexOfListInArray + "] item index = " + indexOfElementInList //
                            + " value = " + lists[indexOfListInArray].get(indexOfElementInList));


            lists[indexOfListInArray].remove(lists[indexOfListInArray].get(indexOfElementInList));

            indexOfElementInTable = indexOfElementInTable -1;
            indexOfElementInList = indexOfElementInList - 1;

            hashTableSize = hashTableSize - 1;

            return;
        }
    }
}