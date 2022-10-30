package ru.academits.dashiev.my_array_list;
// Коммент на будущее ! null - это нормальные данные для любой коллекции, для них все должно работать

import java.util.*;

public class MyArrayList<T> implements List<T> {
    int changesCount = 0; // п.7 счетчик изменений
    private T[] items; // внутренний массив
    private int size; /* длина списка(кол-во эл-в в списке) = 0, вместимость списка , длина списка
    и длина массива могут отличаться*/

    public MyArrayList() {
        final int defaultCapacity = 4; // константа для вместимости по умолчанию

        //noinspection unchecked, заглушил
        items = (T[]) new Object[defaultCapacity]; /* Чтобы не было ошибки компиляции, массив типа T приводится к Object */
    }

    public MyArrayList(int capacity) {        //noinspection unchecked
        items = (T[]) new Object[capacity]; // заглушил
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + size + ". Current value = " + index);
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + size + ". Current value = " + index);
        }
    }

    public void trimToSize() {
        if (size < items.length) { // если нет необходимости не будем пересоздавать массив
            items = Arrays.copyOf(items, size);
        }
    }

    public int size() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
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
    public boolean add(T element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        add(size, element); // чтобы не дублировать код !, size сам увеличится

        return true;
    }

    @Override
    public boolean remove(
            Object o) { // метод должен удалять null-данные так же, как любые другие данные.
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        changesCount++; // увеличиваем счетчик изменений

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c); // п.26 чтобы не дублировать код
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkAddIndex(index);

        if (c.isEmpty()) { // если массив пустой
            return false;
        }

        int oldSize = size;

        int requiredCapacity = size + c.size(); //п.27 Массив может пересоздаваться несколько раз, и много раз будут двигаться элементы

        ensureCapacity(requiredCapacity);

        int i = index;

        System.out.println(items.length);

        for (T t : c) {
            set(i++, t); // вместо add п.27 чтобы не пересоздавать массив
        }

        changesCount += (oldSize - size); // правильно ли я увеличил счетчик изменений?

        return oldSize != size;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            set(i, null); /* нужно занулить ссылки на объекты, хранящиеся в массиве, чтобы сборщик
             мусора мог очистить эти объекты если они больше нигде не используются */
        }

        changesCount++; // увеличиваем счетчик изменений

        size = 0;
    }

    @Override
    public T get(
            int index) { /* возвращает просто конкретный элемент, это геттер, он выдает эл-т по индексу*/
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkAddIndex(index);

        T oldValue = items[index];

        items[index] = element;

        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        checkAddIndex(index);

        if (size + 1 >= items.length) {
            increaseCapacity();
        }

        for (int i = size - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }

        items[index] = element;

        size++;

        changesCount++; // увеличиваем счетчик изменений
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            return null; // TODO не знаю что вернуть если напечатали индекс >= size
        }

        checkIndex(index);

        T deletedElement = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);

        items[size - 1] = null;
        --size;

        changesCount++; // увеличиваем счетчик изменений

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

    //TODO прошу проверить функцию retainAll(Collection c), были затруднения
    @Override
    public boolean retainAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int oldSize = size;

        Set<T> set = new HashSet<>(c);

        int i = 0;

        for (T t : this) {
            if (set.contains(t)) {
                items[i] = t;
                i++; // передвигаемся по массиву
            }
        }

        size = i;

        changesCount += (oldSize - size); // правильно ли я увеличил счетчик изменений ?

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

        changesCount += (oldSize - size); // правильно ли я увеличил счетчик изменений

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
    public Object[] toArray() {    /* нужно создать копию и ее возвратить , т.к. если вернуть
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
        items = Arrays.copyOf(items, items.length * 2);
    }

    public T setItem(int index, T listItem) { // выдает старое значение элемена
        checkAddIndex(index);

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
        if (size == 0) {
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