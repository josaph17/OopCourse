package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int size; // кол-во эл-в
    private int modCount; // кол-во изменений

    static int defaultCapacity = 5; // константа для размера массива по умолчанию

    public MyHashTable() {
        this(defaultCapacity);
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity <= 0. Capacity = " + capacity);
        }

        defaultCapacity = capacity;

        //noinspection unchecked
        lists = new LinkedList[defaultCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) {
            return false;
        }

        int index = (o == null) ? 0 : Math.abs(o.hashCode() % defaultCapacity);

        return lists[index].contains(o);
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

            index++;
        }

        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {  // // T- т.к. этот нек-й класс может отличаться от V
        if (a == null) {
            throw new NullPointerException("a is null!");
        }

        int hashTableSize = size();

        E[] hashTableArray = (E[]) toArray();

        if (hashTableSize > a.length) {
            E[] newArray = (E[]) Arrays.copyOf(a, hashTableSize, a.getClass());

            int i = 0;

            for (E t : hashTableArray) {
                newArray[i] = t;

                i++;
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
        int index = (value == null) ? 0 : Math.abs(value.hashCode() % defaultCapacity);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        size++;

        modCount++;

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

        int primeSize = size;

        for (E v : c) {
            add(v);
        }

        return primeSize != size;
    }

    @Override
    public boolean remove(Object o) {
        int index = (o == null) ? 0 : Math.abs(o.hashCode() % defaultCapacity);

        if (lists[index] != null) {
            return lists[index].remove(o);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        System.out.println(this);

        int primeSize = size;

        for (Object item : c) {
            boolean isRemoved = true;

            while (isRemoved) {
                isRemoved = remove(item);
            }
        }

        return primeSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        if (size == 0) { // HashTable is empty
            return false;
        }

        int oldHashTableSize = size;

        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            E value = iterator.next();

            if (!c.contains(value)) {
                iterator.remove();
            }
        }

        return oldHashTableSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return; // ничего не делать если таблица уже пустая
        }

        Arrays.fill(lists, null);

        modCount++;

        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        Iterator<E> iterator = new MyIterator();

        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        final int iteratorModCount = modCount;

        int indexOfElementInTable = -1; // индекс элемента во всей таблице
        int indexOfListInArray = 0; // индекс списка в массиве
        int indexOfElementInList = -1; // индекс элемента в списке

        boolean isNextCalled; // false по умолчанию

        @Override
        public boolean hasNext() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("Hashtable changed!");
            }

            return indexOfElementInTable + 1 < size;
        }

        @Override
        public E next() {
            isNextCalled = true;

            if (indexOfElementInTable >= size) {
                // лекция 13 стр.15
                throw new NoSuchElementException("Нет больше элементов в HashTable");
            }

            indexOfElementInList = indexOfElementInList + 1;

            for (int i = indexOfListInArray; indexOfListInArray < size; ) {
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
            if (isNextCalled) {
                // int listSize = lists[indexOfListInArray].size();

                System.out.println(
                        "indexOfElementInTable = " + indexOfElementInTable + " , deleted in list[" + indexOfListInArray + "] item index = " + indexOfElementInList //
                                + " value = " + lists[indexOfListInArray].get(indexOfElementInList));

                lists[indexOfListInArray].remove(indexOfElementInList);

                indexOfElementInTable--;
                indexOfElementInList--;

                size--;

                isNextCalled = false;
            } else {
                // Чтобы remove не вызвался 2 раза
                throw new IllegalStateException("Operation removeAll() call second time!");
            }
        }
    }

    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>(3);

        hashTable1.add(88);
        hashTable1.add(432);
        //        hashTable1.add(32);
        hashTable1.add(null);
        hashTable1.add(32);
        hashTable1.add(54);
        hashTable1.add(null);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(432);
        arrayList.add(null);

        System.out.println(hashTable1.retainAll(arrayList));

        System.out.println(hashTable1);

        MyHashTable<Integer> hashTable2 = new MyHashTable<>(3);
        hashTable2.add(1);
        hashTable2.add(2);
        hashTable2.add(3);
        hashTable2.add(4);
        hashTable2.add(5);
        System.out.println(hashTable2);
        Iterator<Integer> iterator = hashTable2.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        System.out.println(hashTable2);
    }
}