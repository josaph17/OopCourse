package ru.academits.dashiev.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final LinkedList<E>[] lists; // это точно правильно, Массив односвязных списков
    private int size; // кол-во эл-в
    private int modCount; // кол-во изменений

    static final int defaultCapacity = 10; // константа для размера массива по умолчанию

    public MyHashTable() {
        this(defaultCapacity);
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity <= 0. Capacity = " + capacity);
        }

        //noinspection unchecked
        lists = new LinkedList[capacity];
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

        if (lists[index] == null){
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
        Object[] array = new Object[size];

        int listIndex = 0;
        int arrayIndex = 0;

        for(int i = 0; i < lists.length; i ++){
            if (lists[listIndex] != null){
                for (E item : lists[listIndex]) {
                    array[arrayIndex] = item;

                    arrayIndex++;
                }
            }

            listIndex ++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {  // // T- т.к. этот нек-й класс может отличаться от V
        if (a == null) {
            throw new NullPointerException("a is null!");
        }

        @SuppressWarnings("unchecked")
        T[] hashTableArray = (T[]) toArray();

        if (size > a.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) Arrays.copyOf(hashTableArray, size, a.getClass());

            return newArray; // возвр новый ф, но длины хэш Таблицы
        }

        System.arraycopy(hashTableArray, 0, a, 0, size); // если равны , на этом закончится

        if (size < a.length) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E value) {
        int index = getArrayIndex(value);

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

        int initialSize = size;

        for (E v : c) {
            add(v);
        }

        return initialSize != size;
    }

    @Override
    public boolean remove(Object o) {
        int index = getArrayIndex(o);

        // Код ниже все равно что return lists[index] == null ? false : lists[index].remove(o);
        return lists[index] != null && lists[index].remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("c is null!");
        }

        if (size == 0) { // HashTable is empty
            return false;
        }

        int oldHashTableSize = size;

        for (LinkedList<E> list : lists) {
            if (list != null) {
                for (int j = list.size() - 1; j >= 0; j--) {
                    if (c.contains(list.get(j))) {
                        list.remove(j);

                        size--;
                    }
                }
            }
        }

        return oldHashTableSize != size;
    }

    // Удаляет элементы, не принадлежащие переданной коллекции
    @Override
    public boolean retainAll(Collection<?> c) {
        // Реализация без remove в Итераторе
//        if (c == null) {
//            throw new NullPointerException("c is null!");
//        }
//
//        if (size == 0) { // HashTable is empty
//            return false;
//        }
//
//        int oldHashTableSize = size;
//
//        for (LinkedList<E> list : lists) {
//            if (list != null) {
//                for (int j = list.size() - 1; j >= 0; j--) {
//                    if (!c.contains(list.get(j))) {
//                        list.remove(j);
//
//                        size--;
//                    }
//                }
//            }
//        }

        // return oldHashTableSize != size;

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

        for (E element: this) {
            sb.append(element).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        private final int initialModCount = modCount;

        private int hashTableElementIndex = -1; // индекс элемента во всей таблице
        private int listIndex = 0; // индекс списка в массиве
        private int listElementIndex = -1; // индекс элемента в списке

        // Для remove() в Итераторе
        private boolean isRemoveCalledOnce; // false по умолчанию

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
                    // listElementIndex = 0;
                    listIndex++;

                    // Чтобы выйти из while, но сюда можно упасть если мы удвлили из list зн-я и
                    // лист .size() теперь равен 0
                    isNextFind = true;
                }

                if (listElementIndex >= lists[listIndex].size() || lists[listIndex].size() == 0){
                    listElementIndex = 0;
                    listIndex++;
                    // Чтобы не выйти из while, видимо сборщик мусора не успевает присвоить null листу если он пуст
                    isNextFind = false;
                } else {
                    isNextFind = true;
                }
            }

            isRemoveCalledOnce = true;

            hashTableElementIndex++;

            // Меняем isListIndexFind к следующей итерации
            isNextFind = false;

            return lists[listIndex].get(listElementIndex);
        }

        @Override
        public void remove() {
            if (!isRemoveCalledOnce) {
                // Чтобы remove не вызвался 2 раза
                throw new IllegalStateException("Operation remove() in Iterator call second time on" +
                                                        "that element!");
            } else {
                lists[listIndex].remove(listElementIndex);

                hashTableElementIndex--;
                listElementIndex--;

                size--;

                isRemoveCalledOnce = false;
            }
        }
    }
}