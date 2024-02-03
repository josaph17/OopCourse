package ru.academits.dashiev.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10; // константа для размера массива по умолчанию

    private final ArrayList<E>[] lists;
    private int size; // кол-во эл-в
    private int modCount; // кол-во изменений

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal capacity value. Capacity should be > 0. Capacity = " + capacity);
        }

        //noinspection unchecked
        lists = new ArrayList[capacity];
    }

    private int getIndex(Object o) {
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

        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int arrayIndex = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E item : list) {
                    array[arrayIndex] = item;

                    arrayIndex++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {  // // T- т.к. этот некоторый класс может отличаться от E
        if (a == null) {
            throw new NullPointerException("Array is null!");
        }

        // hashTableArray - лучше сделать типа Object[], т.к. это по факту пока не T[], прошу не считать за ошибку
        // важный комментарий
        Object[] hashTableArray = toArray();

        if (size > a.length) {
            // возвращается новый массив, но длины хэш Таблицы
            //noinspection unchecked
            return (T[]) Arrays.copyOf(hashTableArray, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(hashTableArray, 0, a, 0, size);

        if (size < a.length) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        modCount++;
        size++;

        // True т.к. у односвязного списка нет случаев когда он может не добавить
        return lists[index].add(item);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!");
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
            throw new NullPointerException("Collection is null!");
        }

        // этот метод может вернуть false только если переданная коллекция пустая
        if (c.isEmpty()) {
            return false;
        }

        for (E item : c) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Parameter collection is null!");
        }

        if (size == 0) { // HashTable is empty
            return false;
        }

        if (c.isEmpty()) {
            return false;
        }

        int oldSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int initialListSize = list.size();

                if (list.removeAll(c)) {
                    size -= initialListSize - list.size();
                }
            }
        }

        boolean isModified = oldSize != size;

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    // Удаляет элементы, не принадлежащие переданной коллекции
    //Этот пункт можно не исправлять
    @Override
    public boolean retainAll(Collection<?> c) {
        // todo прошу не считать за замечание, хочу запомнить данный способ реализации функции

        if (c == null) {
            throw new NullPointerException("Parameter collection is null!");
        }

        if (size == 0) { // HashTable is empty
            return false;
        }

        int oldSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int initialListSize = list.size();

                if (list.retainAll(c)) {
                    size -= initialListSize - list.size();
                }
            }
        }

        boolean isModified = oldSize != size;

        if (isModified) {
            modCount++;
        }

        return oldSize != size;
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

        sb.append('[');

        for (E item : this) {
            sb.append(item).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }

    private class HashTableIterator implements java.util.Iterator<E> {
        private int initialModCount = modCount;

        private int hashTableItemIndex = -1; // индекс элемента во всей таблице
        private int listIndex; // индекс списка в массиве
        private int itemIndex = -1; // индекс элемента в списке

        // Для remove() в Итераторе
        private boolean isRemoveCalled; // false по умолчанию

        @Override
        public boolean hasNext() {
            return hashTableItemIndex + 1 < size;
        }

        @Override
        public E next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Hashtable changed!");
            }

            // Внизу было условие if (tableItemIndex == size)
            if (!hasNext()) {
                // лекция 13 стр.15
                throw new NoSuchElementException("No more items in HashTable");
            }

            isRemoveCalled = false;

            itemIndex++;

            while (lists[listIndex] == null || itemIndex >= lists[listIndex].size()) {
                listIndex++;
                itemIndex = 0;
            }

            hashTableItemIndex++;

            return lists[listIndex].get(itemIndex);
        }

        @Override
        public void remove() {
            if (isRemoveCalled) {
                // Чтобы remove не вызвался 2 раза
                throw new IllegalStateException("Operation iterator.remove() call second time!");
            }

            isRemoveCalled = true;

            lists[listIndex].remove(itemIndex);

            hashTableItemIndex--;
            itemIndex--;

            // initialModCount++ чтобы код не падал поскольку итератор сам меняет коллекцию
            initialModCount++;
            modCount++;
            size--;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }
}