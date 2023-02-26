package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int size; // кол-во эл-в
    private int modCount; // кол-во изменений

    static int defaultCapacity = 5; // константа для размера массива по умолчанию
    // поле, которое не static поле у каждого свое - может быть разное

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

    private int getArrayIndex(Object o){
        return (o == null) ? 0 : Math.abs(o.hashCode() % lists.length);
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

        int index = getArrayIndex(o);

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
    public <T> T[] toArray(T[] a) {  // // T- т.к. этот нек-й класс может отличаться от V
        if (a == null) {
            throw new NullPointerException("a is null!");
        }

        int hashTableSize = size();

        @SuppressWarnings("unchecked")
        T[] hashTableArray = (T[]) toArray();

        if (hashTableSize > a.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) Arrays.copyOf(a, hashTableSize, a.getClass());

            int i = 0;

            for (T t : hashTableArray) {
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
        int index = (value == null) ? 0 : Math.abs(value.hashCode() % lists.length);

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
        int index = getArrayIndex(o);

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
        private int iteratorModCount = modCount;

        private int tableElementIndex = -1; // индекс элемента во всей таблице
        private int listIndex; // индекс списка в массиве
        private int listElementIndex = -1; // индекс элемента в списке

        private boolean isNextCalled; // false по умолчанию

        @Override
        public boolean hasNext() {
            return tableElementIndex + 1 < size;
        }

        @Override
        public E next() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("Hashtable changed!");
            }

            if (tableElementIndex >= size) {
                // лекция 13 стр.15
                throw new NoSuchElementException("Нет больше элементов в HashTable");
            }

            if (hasNext()){
                isNextCalled = true;

                listElementIndex = listElementIndex + 1;

                while (listIndex < size) {
                    if (!lists[listIndex].isEmpty() && listElementIndex < lists[listIndex].size()) {
                        tableElementIndex++;

                        return lists[listIndex].get(listElementIndex);
                    }

                    listElementIndex = 0;

                    listIndex++;
                }
            }

            return null;
        }

        @Override
        public void remove() {
            if (isNextCalled) {
                lists[listIndex].remove(listElementIndex);

                tableElementIndex--;
                listElementIndex--;

                size--;

                isNextCalled = false;
            } else {
                // Чтобы remove не вызвался 2 раза
                throw new IllegalStateException("Operation removeAll() call second time!");
            }
        }
    }
}