package ru.academits.dashiev.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private static Iterator instance;
    private T[] items; // внутренний массив
    private int size; // длина списка(кол-ва эл-в в списке) = 0, по умолчанию ,
    // вместимость списка , длина списка и длина массива могут

    public MyArrayList() {
        items = (T[]) new Object[4]; // 5м - начальный размер (вместимость, capacity)
        /*Чтобы не было ошибки компиляции, массив типа T приводится к Object*/
    }

    public MyArrayList(int length) {
        items = (T[]) new Object[length];
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            T[] copy = (T[]) new Object[capacity];

            System.arraycopy(items,0,copy,0,size);
            items = copy;
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    public int size() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1) {
            return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> instance = new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (currentIndex < size && items[currentIndex] != null)
                    return true;

                return false;
            }

            @Override
            public T next() {
                return items[currentIndex++]; // возвр. текущий элемент и переходит к след.
            }
        };

        return instance;
    }

    @Override
    public Object[] toArray() {
        // нужно создать копию и возвратить копию, т.к. если вернуть оригинальный массив, то его могут поменять
        //извне, если возвр ориг - я предоставля прямой доступ к данным, инкапсуляции не будет

        T[] copyItems = Arrays.copyOf(items, size);

        return copyItems;
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException("Add element is null!!!");
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = (T) o;

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int objIndex = indexOf(o); // находим индекс элемента

        if (objIndex == -1) {
            return false;
        }

        remove(objIndex);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (size >= items.length || items.length >= c.size()) { // т.к. size увеличивается после add
            increaseCapacity();
        }

        int oldSize = size;

        int i = 0;

        for (T t : c) {
            add(t);
        }

        return oldSize != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("IndexOutOfBoundsException. index < 0");
        }

        if (size + items.length >= items.length) {
            increaseCapacity();
        }

        int oldSize = size;

        for (T t : c) {
            this.add(index, t);
            ++ index;
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        items = (T[]) new Object[3]; // items = null не обязвтельно делать

        size = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }
        // этот get возвращает просто конкретный элемент, это не геттер, т.к. он не возвращает сам массив
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        T oldValue = items[index];

        items[index] = (T) element;

        return oldValue;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        if (element == null) {
            throw new NullPointerException("Element is null!!!");
        }

        T objectElement = (T) element;

        if (size + 1 >= items.length) {
            increaseCapacity();
        }

        for (int i = size - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }

        items[index] = (T) element;

        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (size - 1) + ".Current value = " + index);
        }

        T deletedElement = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);

        items[size - 1] = null;
        --size;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        T element = (T) o;

        if (element == null) { // если это данные ссылочного типа
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (items[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        T element = (T) o;

        if (element == null) { // если это данные ссылочного типа
            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) { // реализация не нужна
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) { // реализация не нужна
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        int oldSize = size;

        clear();
        addAll(c);

        return size != oldSize;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int oldSize = size;

        for (Object t : c) {
            remove(t);
        }

        return size != oldSize;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object t : c) {
            if (contains(t) == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <E> E[] toArray(E[] array) { // E- т.к. этот нек-й класс может отличаться от T
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length < size) {
            return Arrays.copyOf(array, size);
            // возвращаем новый массив того же типа< что и переданный
        }

        System.arraycopy(items, 0, array, 0, size); // возвр переданный массив,
        //заполненный элементами из списка

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }


    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public T setItem(int index, T listItem) {
        // изменение значения по указанному индексу, Изменение значения по индексу пусть выдает старое значение.
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + size + ".Current value = " + index); // выход за sizes
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        T oldValue = items[index];

        items[index] = listItem;

        return oldValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31; // просто число
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(items);
        hash = prime * hash + size;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass() || obj == null) {
            return false;
        }

        MyArrayList o = (MyArrayList) obj;

        if (size != o.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (items[i] != o.items[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if(size == 0){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(items[i] + " ");
        }

        sb.deleteCharAt(sb.length() - 1);

        sb.append("]");

        return sb.toString();
    }
}