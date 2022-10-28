package ru.academits.dashiev.my_list;

import ru.academits.dashiev.node.Node;

public class MyList<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head; // переменная, которая указывает на начало списка
    private int count; // здесь храним длину списка

    public MyList() {
        count = 0;
        head = null;
    }

    public MyList(Node<T> head) {
        this.head = head;
    }

    public MyList(MyList<T> list) {
        for (Node<T> p = list.head; p != null; p = p.getNext()) {
            this.add(p.getData());
        }

        this.reverse();
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

    public T get(int index) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }


        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // выход за sizes
        }

        Node<T> current = head;

        for (int i = 0; i != index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public T set(int index, T value) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // выход за sizes
        }

        Node<T> current = head;

        for (int i = 0; i != index; i++, current = current.getNext()) {
        }

        T editedValue = current.getData();

        current.setData(value);

        return editedValue;
    }

    public T remove(int index) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // выход за sizes
        }

        if (index == 0) {
            return removeFirst();
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        T deletedValue = current.getNext().getData(); // удаляемое значение

        if (current.getNext().getNext() == null) {
            current.setNext(null);
        } else {
            current.setNext(current.getNext().getNext());
        }

        count--;

        return deletedValue;
    }

    public void add(T data) { // вставка элемента в начало
        if (head == null) {
            head = new Node<>(data); // head указ на ноую ноду
        } else {
            head = new Node<>(data, head); // head указывает на текующую ноду
        }

        count++;
    }

    public void addIndexElement(int index, T data) { // вставка элемента по индексу
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // выход за sizes
        }

        if (index == 0) { // если нужно вставить в самое начало
            add(data);
            return;
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        Node<T> newNode = new Node<>(data);

        newNode.setNext(current.getNext());
        current.setNext(newNode); // присваивание current = newMode не работает

        count++;
    }

    public boolean removeValue(T data) {
        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // выход за sizes
        }

        int i = 0;

        for (Node<T> p = head; p != null; p = p.getNext()) {
            if (p.getData() == data) {
                remove(i);

                return true;
            }

            i++;
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // выход за sizes
        }

        T deletedValue = head.getData();

        head = head.getNext();

        count--;
        // или head = head.getNext();

        return deletedValue;
    }

    public void reverse() { // разворот за линейное время
        Node<T> prev = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.getNext();

            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev; // т.к. к этому моменту current = null

    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        StringBuilder sb = new StringBuilder();

        for (Node<T> p = head; p != null; p = p.getNext()) {
            sb.append(p).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
