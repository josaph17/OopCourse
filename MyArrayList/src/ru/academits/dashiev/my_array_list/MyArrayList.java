package ru.academits.dashiev.my_array_list;
// Коммент на будущее ! null - это нормальные данные для любой коллекции, для них все должно работать

import java.sql.Array;
import java.util.*;

import static java.util.Arrays.asList;

public class MyArrayList<T> implements List<T> {
    final int defaultCapacity = 4; // константа-поле класса для вместимости по умолчанию
    private T[] items; // внутренний массив
    private int size; /* длина списка(кол-во эл-в в списке) = 0, вместимость списка , длина списка
    и длина массива могут отличаться*/
    private int modCount; // п.7 счетчик изменений

    public MyArrayList() {
        // noinspection unchecked, заглушил
        // Чтобы не было ошибки компиляции, массив типа T приводится к Object
        items = (T[]) new Object[defaultCapacity];
    }

    public MyArrayList(int capacity) {
        // noinspection unchecked
        items = (T[]) new Object[capacity]; // заглушил
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (size - 1) + ". Current index = " + index);
        }
    }

    private void checkIndexToAdd(int index) {
        // Верхняя граница не должна зависеть от длины массива(items.length), а должна зависеть от длины списка
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + size + ". Current index = " + index);
        }
    }

    public void trimToSize() {
        if (size < items.length) { // если нет необходимости не будем пересоздавать массив
            items = Arrays.copyOf(items, size);
        }
    }

    public int size() { // получение размера списка
        return size; // кол-во эл-в в списке
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1; // вернуть резутат
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> instance = new Iterator<T>() {
            private int currentIndex = -1; // обязательно должен быть модификатор доступа

            @Override
            public boolean hasNext() {
                return currentIndex < size - 1; /* && items[currentIndex] != null - на null нельзя
                ориентироваться т.к. это нормально значение данных, size-1 чтобы не убежали! */
            }

            @Override
            public T next() { // возвр. текущий элемент и переходит к следующему
                if (currentIndex >= items.length) {
                    throw new NoSuchElementException("Не больше элементов в ArrayList");
                }

                ++currentIndex; // сначало увеличиваем индекс

                return items[currentIndex];
            }
        };

        return instance;
    }

    @Override
    public boolean add(T item) {
        //        здесь не нужна проверка вместимости, т.к. она есть в add с индексом
        add(size, item); // чтобы не дублировать код !, size сам увеличится

        return true;
    }

    // метод должен удалять null-данные так же, как любые другие данные.
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c); // п.26 чтобы не дублировать код
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexToAdd(index);

        if (c.isEmpty()) { // если коллекция пустая
            return false;
        }

        int oldSize = size;

        // п.27 Массив может пересоздаваться несколько раз, и много раз будут двигаться элементы
        int requiredCapacity = size + c.size();

        ensureCapacity(requiredCapacity); // обеспечить длину массива

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (T t : c) {
            items[i] = t;
            i = i + 1;
            size = size + 1;
            modCount = modCount +1;
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            /* нужно занулить ссылки на объекты, хранящиеся в массиве, чтобы сборщик
             мусора мог очистить эти объекты если они больше нигде не используются */
            items[i] = null;
        }

        modCount++; // увеличиваем счетчик изменений

        size = 0;
    }

    /* возвращает просто конкретный элемент, это геттер, он выдает эл-т по индексу*/
    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T oldItem = items[index];

        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        checkIndexToAdd(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;

        size++;

        modCount++; // увеличиваем счетчик изменений
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);

        items[size - 1] = null;
        --size;

        modCount++; // увеличиваем счетчик изменений

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        // TODO прошу проверить правильно ли работает на null
        if (o == null){ // если o = null
            for (int i = 0; i < size; i++) {
                if (items[i] == null) { // equals принмиает Object, Не нужно приведение
                    return i;
                }
            }

            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) { // equals принмиает Object, Не нужно приведение
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO прошу проверить правильно ли работает на null
        if (o == null){ // если o = null
            for (int i = 0; i < size; i++) {
                if (items[i] == null) { // equals принмиает Object, Не нужно приведение
                    return i;
                }
            }

            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
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

    //TODO прошу проверить функцию retainAll(Collection c), были затруднения
    @Override
    public boolean retainAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int oldSize = size;

        int i = 0;

        for (T t : this) {
            if (c.contains(t)) {
                items[i] = t;
                i++; // передвигаемся по массиву
            }
        }

        size = i;

        for (int j = size; j < oldSize; j++) {
            items[i] = null;
        }

        modCount += (oldSize - size); // правильно ли я увеличил счетчик изменений ?

        return size != oldSize;
    }

    @Override
    public boolean removeAll(Collection c) { // удаляет все вхожде
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int oldSize = size;

        for (Object t : c) {
            for (int i = size - 1; i >= 0; i--) {
                if (items[i].equals(t)) {
                    remove(i);
                }
            }
        }

        modCount += (oldSize - size); // правильно ли я увеличил счетчик изменений

        return size != oldSize;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (this == null) {
            System.out.println("this is null");
        }

        for (Object t : c) {
            boolean result = this.contains(t);

            if (result == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        /* нужно создать копию и ее возвратить , т.к. если вернуть
    оригинальный массив, то его могут поменять извне, если возвр. ориг. - то я предоставлю прямой
    доступ к данным, инкапсуляции не будет */
        return Arrays.copyOf(items, size);
    }

    @Override
    public <E> E[] toArray(E[] array) { // E, т.к. этот нек-й класс, который может отличаться от T
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length < size) {
            return Arrays.copyOf(array, size); // возвр. новый массив того же типа, что и переданный
        }

        System.arraycopy(items, 0, array, 0, size); /* возвр. переданный массив,
        заполненный элементами из списка */

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, defaultCapacity);

            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public int hashCode() {
        final int prime = 31; // просто число

        int hash = prime + Arrays.hashCode(items);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) { // если я прохожу, то что это О. этого класса
            return false;
        }

        MyArrayList<?> o = (MyArrayList<?>) obj; // TODO поставил wildcard т.к. точно не знаю тип Obj

        if (size != o.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!items[i].equals(o.items[i])) { // TODO сравниваем только по equals!
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(items[i]).append(", ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> stringList = new MyArrayList<>();
        stringList.add("Hello");
        stringList.add("Mister");
        stringList.add(null);
        stringList.add("!");

        int hollINd = stringList.indexOf(null);
        System.out.println(hollINd);
    }
}