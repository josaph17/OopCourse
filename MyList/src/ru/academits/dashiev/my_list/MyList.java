package ru.academits.dashiev.my_list;

import java.util.NoSuchElementException;

public class MyList<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head; // переменная, которая указывает на начало списка
    private int size; // здесь храним длину списка

    public MyList() {
        // size = 0; head = null; будет по умолчанию
        size = 0;
        head = null;
    }

    //п.7 7. List(Node<T> head) - конструктор убрал поскольку он нигде не используется

    public MyList(MyList<T> list) {
        this(); // вызвал конструктор по умолчанию

        Node<T> current = head;

        int i = 0;

        for (Node<T> p = list.head; p != null; p = p.getNext()) {
            if (current == null) {
                current = new Node<>(p.getData(), null);

                head = current; // чтобы не терять указатель head
            } else {
                current.setNext(new Node<>(p.getData(),null));
                current = current.getNext();
            }

            size++;
        }
    }

    public int getSize() { // получение размера списка
        return size; // размер списка называют вместимостью, capacity
    }

    public T getFirst() { // получение значения первого элемента
        if (head == null) {
            throw new NoSuchElementException("List is empty."); // выход за sizes
        }

        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (getSize() - 1) + ".Current index = " + index);
        }

        Node<T> current = head;

        for (int i = 0; i != index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (getSize() - 1) + ".Current index = " + index);
        }

        Node<T> current = head;

        for (int i = 0; i != index; i++, current = current.getNext()) {
        }

        T oldData = current.getData();

        current.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, index = " + (getSize() - 1) + ".Current index = " + index);
        }

        if (index == 0) {
            return removeFirst();
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        T oldData = current.getNext().getData(); // удаляемое значение

        current.setNext(current.getNext().getNext()); // более общий случай

        size--;

        return oldData;
    }

    public void addFirst(T data) { // вставка элемента в начало
        head = new Node<>(data, head); // п.28 более общий случай

        size++;
    }

    public void addByIndex(int index, T data) { // вставка элемента по индексу
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (getSize() - 1) + ".Current index = " + index);
        }

        if (index == 0) { // если нужно вставить в самое начало
            addFirst(data);
            return;
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        //        Node<T> newNode = new Node<>(data);
        //        newNode.setNext(current.getNext());
        //        current.setNext(newNode); // присваивание current = newMode не работает

        current.setNext(new Node<>(data, current.getNext())); // п.29 от ответа от 19.10.22

        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        int i = 0;

        for (Node<T> previous = head; previous != null; previous = previous.getNext()) {
            if (previous.getData().equals(data)) {
                removeByIndex(i);

                return true;
            }

            i++;
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty."); // выход за sizes
        }

        T oldData = head.getData(); // страын данные

        head = head.getNext();

        size--;
        // или head = head.getNext();

        return oldData;
    }

    public void reverse() { // разворот за линейное время
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.getNext();

            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous; // т.к. к этому моменту current = null
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        if (head == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[ ");

        for (Node<T> previous = head; previous != null; previous = previous.getNext()) {
            sb.append(previous.getData()).append(", ");
        }

        sb.deleteCharAt(sb.length() - 2);

        sb.append("]");

        return sb.toString();
    }
}