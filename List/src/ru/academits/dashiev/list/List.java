package ru.academits.dashiev.list;

import ru.academits.dashiev.listItem.Node;

public class List<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head; // переменная, которая указывает на начало списка
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

    public void set(int index, T value) {
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

        current.setData(value);
    }

    public void remove(int index) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // выход за sizes
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            System.out.println(current.getData());
        }

        if (current.getNext().getNext() == null) {
            current.setNext(null);
        } else {
            current.setNext(current.getNext().getNext());
        }

        count--;
    }

    public void add(T data) { // вставка элемента в начало
        if (head == null) {
            head = new Node<T>(data); // head указ на ноую ноду
        } else {
            Node<T> current = new Node<>(data, head); // head здесь указывает на предыдующую ноду
            head = current; // head указывает на текующую ноду
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
            System.out.println(current.getData());
        }

        Node<T> newNode = new Node<T>(data);

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
            ;

            i++;
        }

        return false;
    }

    public void removeFirst() {
        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // выход за sizes
        }

        head = head.getNext();

        count--;
        // или head = head.getNext();
    }

    public void reverse() { // разворот за линейное время
        Node<T> current = head;

        T temp;

        for (int i = 0; i != (getSize() - 1)/2; i++) {
            temp = get(i);
            set(i, get(getSize()-1-i));
            set(getSize()-1-i, temp);
        }
    }

    public void show() {
        for (Node<T> p = head; p != null; p = p.getNext()) {
            System.out.print(p.getData() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() { // переопределили toString для нашего собственного класса
        StringBuilder sb = new StringBuilder();

        for (Node p = head; p != null; p = p.getNext()) {
            sb.append(p).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
