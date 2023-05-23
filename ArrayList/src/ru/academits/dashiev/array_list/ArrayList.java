package ru.academits.dashiev.array_list;
// Коммент на будущее ! null - это нормальные данные для любой коллекции, для них все должно работать

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10; // Вместимость по умолчанию, т.е. items.length

    private E[] items;
    private int size; /* Длина списка(кол-во эл-в в списке) = 0, вместимость списка , длина списка
    и длина массива могут отличаться */
    private int modCount; // п.7 счетчик изменений

    public ArrayList() {
        // noinspection unchecked, заглушил
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >= 0. Capacity = " + capacity);
        }

        // noinspection unchecked
        items = (E[]) new Object[capacity]; // Заглушил
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + (size - 1) + ". Current index = " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        // Верхняя граница не должна зависеть от длины массива(items.length), а должна зависеть от длины списка
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + size + ". Current index = " + index);
        }
    }

    public void trimToSize() {
        if (size < items.length) { // Если нет необходимости не будем пересоздавать массив
            items = Arrays.copyOf(items, size);
        }
    }

    public int size() { // получение размера списка
        return size; // Кол-во эл-в в списке
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1; // Вернуть результат
    }

    @Override
    public boolean add(E item) {
        // Здесь не нужна проверка вместимости, т.к. она есть в add с индексом
        add(size, item); // Чтобы не дублировать код !, size сам увеличится

        return true;
    }

    // Метод должен удалять null-данные так же, как любые другие данные.
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
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c); // п.26 чтобы не дублировать код
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        checkIndexForAdd(index);

        if (c.isEmpty()) { // Если коллекция пустая
            return false;
        }

        ensureCapacity(size + c.size()); // Обеспечить длину массива

        System.arraycopy(items, index, items, index + c.size(), size - index);

        modCount++; // Если программа дойдет до этого места, то элементы добавятся
        size += c.size();

        int i = index;

        for (E item : c) {
            items[i] = item;
            i++;
        }

        return true;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        /* Нужно занулить ссылки на объекты, хранящиеся в массиве, чтобы сборщик
             мусора мог очистить эти объекты если они больше нигде не используются */
        Arrays.fill(items, 0, size, null);

        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];

        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndexForAdd(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;

        modCount++;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);

        modCount++;
        size--;

        items[size] = null;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection ConstantConditions // Заглушил
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) { // Реализация не нужна
        //noinspection ConstantConditions // Заглушил
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) { // Реализация не нужна
        //noinspection ConstantConditions // Заглушил
        return null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int initialSize = size;

        int indexAfterRetain = 0;

        for (int i = 0; i < initialSize; i++) {
            if (c.contains(items[i])) {
                items[indexAfterRetain] = items[i];
                indexAfterRetain++; // Передвигаемся по массиву
            }
        }

        size = indexAfterRetain;

        if (size != initialSize) {
            Arrays.fill(items, size, initialSize, null); // Второй индекс является не включительным

            modCount++; // Достаточно увеличить счетчик на 1 если список изменился

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) { // Удаляет все вхождения
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        int initialSize = size;

        int indexAfterRemove = 0;

        for (int i = 0; i < initialSize; i++ ) {
            if (!c.contains(items[i])) {
                items[indexAfterRemove] = items[i];
                indexAfterRemove++; // Передвигаемся по массиву
            }
        }

        size = indexAfterRemove;

        if (size != initialSize) {
            Arrays.fill(items, size, initialSize, null); // Второй индекс является не включительным

            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection is null!!!");
        }

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        /* Нужно создать копию и ее возвратить , т.к. если вернуть
        оригинальный массив, то его могут поменять извне, если возвр. ориг. - то я предоставлю прямой
        доступ к данным, инкапсуляции не будет */
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[]  toArray(T[] array) { // T, т.к. этот нек-й класс, который может отличаться от E
        if (array == null) {
            throw new NullPointerException("Array is null");
        }

        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass()); // Возвр. новый массив того
            // же типа, что и переданный, но с данными из списка
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size); /* Возвр. переданный массив,
        заполненный элементами из списка */

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    private void increaseCapacity() {
        // Задаем вместимость по умолчанию, как в конструкторе без аргументов
        if (items.length == 0){
            //noinspection unchecked
            items = (E[]) new Object[DEFAULT_CAPACITY];
        } else{
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31; // Простое число

        int hash = 1;

        /* 11. hashCode - здесь эффективнее использовать цикл for вместо foreach по this.
        Этот пункт можно не исправлять. Прошу не считать за ошибку этот комментарий от Вас */
        for (Object item : this) {
            hash = prime * hash + ((item == null) ? 0 : item.hashCode());
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // Чтобы акцент был на аргументе
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) { // Если я прохожу, то что это О. этого класса
            return false;
        }

        ArrayList<?> list = (ArrayList<?>) obj;

        if (size != list.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], list.items[i])) { // Сравниваем только по equals!
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

        sb.append('[');

        for (int i = 0; i < size; i++) {
            sb.append(items[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        private final int initialModCount = modCount;
        private int currentIndex = -1; // Обязательно должен быть модификатор доступа

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size; /* && items[currentIndex] != null - на null нельзя
                ориентироваться т.к. это нормально значение данных, size-1 чтобы не убежали! */
        }

        @Override
        public E next() { // Возвр. текущий элемент и переходит к следующему
            if (!hasNext()){
                throw new NoSuchElementException("Not find element, currentIndex (" + currentIndex + ") >= size ("+ size + ")");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("ArrayList is changed!");
            }

            ++currentIndex; // Сначало увеличиваем индекс

            return items[currentIndex];
        }
    }

    public Iterator<E> iterator() {
        return new MyIterator();
    }
}