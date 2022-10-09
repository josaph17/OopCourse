package ru.academits.dashiev.list;

import ru.academits.dashiev.listItem.Node;

public class List<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head;
    private int count; // здесь храним длину списка

    public List() {
        count = 0;
        head = null;
    }

    public List(Node<T> head) {
        this.head = head;
    }

    public int getSize() { // получение размера списка
        return count; // размер списка называют вместимостью, capacity
    }

    public T getFirstElement() { // получение значения первого элемента
        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // выход за sizes
        } else {
            return head.getData();
        }
    }

    public T getValue(int index) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + getSize() + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // выход за sizes
        }

        Node<T> current = head;

        for (int i = getSize(); i != index; i--) {
            current = current.getNext();
        }

        return current.getData();
    }


    //    public T getItem(int index) { // получение значения по указанному индексу
    //
    //    }
    //
    //    public T setItem(int index, T listItem) {
    //        // изменение значения по указанному индексу, Изменение значения по индексу пусть выдает старое значение.
    //    }
    //
    //    public void remove(int index) { // удаление элемента по индексу, пусть выдает значение элемента
    //    }

    public void add(T data) { // вставка элемента в начало
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> current = new Node<>(data, head);
            head = current;
        }

        count++;
    }

    //    public void pushBack(T data) { // вставка элемента в начало
    //        if (head == null) {
    //            head = new Node<T>(data);
    //        } else {
    //            Node<T> current = head;
    //
    //            while (current.getNext() != null) {
    //                current = current.getNext(); // переходим на след ссылку
    //            }
    //
    //            current = new Node<T>(
    //                    data); // если нынешний эл-т указ на null, то создаем новый узел с данными
    //        }
    //
    //        count++;
    //    }

    //    public void addIndexItem(int index, T item) { //вставка элемента по индексу
    //    }
    //
    //    public boolean remove(T item) {
    //        // удаление узла по значению, пусть выдает true, если элемент был удален
    //        return true;
    //    }
    //
    //    public T removeStart() { // удаление первого элемента, пусть выдает значение элемента
    //    }
    //
    //    public void reverse() { // разворот списка за линейное время
    //
    //    }
    //
    //    public void copyList() {
}
