package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10; // константа для размера массива по умолчанию

    private final LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int size; // кол-во эл-в
    private int modCount; // кол-во изменений

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be >= 0. Capacity = " + capacity);
        }

        //noinspection unchecked
        lists = new LinkedList[capacity];
    }

    private int getIndex(Object o){
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

        for(int i = 0; i < lists.length; i ++){
            if (lists[i] != null){
                for (E item : lists[i]) {
                    array[arrayIndex] = item;

                    arrayIndex++;
                }
            }

            i ++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {  // // T- т.к. этот некоторый класс может отличаться от E
        if (a == null) {
            throw new NullPointerException("a is null!");
        }

        // hashTableArray - лучше сделать типа Object[], т.к. это по факту пока не T[], Прошу не считать за ошибку
        // важный комментарий
        Object[] hashTableArray =  toArray();

        if (size > a.length) {
            // возвр новый массив, но длины хэш Таблицы
            //noinspection unchecked
            return (T[]) Arrays.copyOf(hashTableArray, size, a.getClass());
        }

        System.arraycopy(hashTableArray, 0, a, 0, size);

        if (size < a.length) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E value) {
        int index = getIndex(value);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        modCount++;
        size++;

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

        int initialSize = size;

        for (E v : c) {
            add(v);
        }

        return initialSize != size;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if(lists[index] != null && lists[index].remove(o)){
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        if (c.size() == 0) {
            return false;
        }

        if (size == 0) { // HashTable is empty
            return false;
        }

        int oldHashTableSize = size;

        // Не менял на forEach поскольку код становится для меня не читаемым
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                int initialCurrentListSize = lists[i].size();

                lists[i].removeAll(c);

                int initialCurrentListSizeAfterAll = lists[i].size();

                if (initialCurrentListSize != initialCurrentListSizeAfterAll) {
                    modCount++;
                    size = size - (initialCurrentListSize - initialCurrentListSizeAfterAll);
                }
            }
        }

        return oldHashTableSize != size;
    }

    // Удаляет элементы, не принадлежащие переданной коллекции
    // 14. Можно не реализовывать remove итератора и реализовать retainAll без него.
    //Этот пункт можно не исправлять
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

        sb.append('[');

        for (E element: this) {
            sb.append(element).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        private int initialModCount = modCount;

        private int hashTableElementIndex = -1; // индекс элемента во всей таблице
        private int listIndex; // индекс списка в массиве
        private int listElementIndex = -1; // индекс элемента в списке

        // Для remove() в Итераторе
        private boolean isRemoveCalledTwice; // false по умолчанию
        private boolean isNextFind;

        @Override
        public boolean hasNext() {
            return hashTableElementIndex + 1 < size;
        }

        @Override
        public E next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Hashtable changed!");
            }

            // Внизу было условие if (tableElementIndex == size)
            if (!hasNext()) {
                // лекция 13 стр.15
                throw new NoSuchElementException("Нет больше элементов в HashTable");
            }

            listElementIndex ++;

            while (!isNextFind){
                while (lists[listIndex] == null){
                    // Чтобы выйти из while, но сюда можно упасть если мы удалили из list зн-я и
                    // лист .size() теперь равен 0
                    listIndex++;
                }

                if (lists[listIndex].isEmpty() || listElementIndex >= lists[listIndex].size()){
                    listElementIndex = 0;
                    listIndex++;
                    // Чтобы не выйти из while, видимо сборщик мусора не успевает присвоить null листу если он пуст
                    isNextFind = false;
                } else {
                    isNextFind = true;
                }
            }

            isRemoveCalledTwice = true;

            hashTableElementIndex++;

            isNextFind = false;

            return lists[listIndex].get(listElementIndex);
        }

        @Override
        public void remove() {
            if (!isRemoveCalledTwice) {
                // Чтобы remove не вызвался 2 раза
                throw new IllegalStateException("Operation remove() in Iterator call second time on" +
                                                        "that element!");
            }

            lists[listIndex].remove(listElementIndex);

            hashTableElementIndex--;
            listElementIndex--;

            // initialModCount++ чтобы итератор не падал из-за того что сам меняет колекцию
            initialModCount++;
            modCount++;
            size--;

            isRemoveCalledTwice = false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }
}