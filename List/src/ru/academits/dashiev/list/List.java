package ru.academits.dashiev.list;

import ru.academits.dashiev.listItem.Node;

public class List<T> { // ����� List  ������ ���� generic, ����� ���� ������ �� ������������� � ����
    private Node<T> head; // ����������, ������� ��������� �� ������ ������
    private int count; // ����� ������ ����� ������

    public List() {
        count = 0;
        head = null;
    }

    public List(Node<T> head) {
        this.head = head;
    }

    public List(List<T> list) {
        for (Node<T> p = list.head; p != null; p = p.getNext()) {
            this.add(p.getData());
        }

        this.reverse();
    }

    public int getSize() { // ��������� ������� ������
        return count; // ������ ������ �������� ������������, capacity
    }

    public T getFirstElement() { // ��������� �������� ������� ��������
        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // ����� �� sizes
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
            throw new NullPointerException("1st Node is null!!!"); // ����� �� sizes
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
            throw new NullPointerException("Head is null!!!"); // ����� �� sizes
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
            throw new NullPointerException("Head is null!!!"); // ����� �� sizes
        }

        if (index == 0) {
            return removeFirst();
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        T deletedValue = current.getNext().getData(); // ��������� ��������

        if (current.getNext().getNext() == null) {
            current.setNext(null);
        } else {
            current.setNext(current.getNext().getNext());
        }

        count--;

        return deletedValue;
    }

    public void add(T data) { // ������� �������� � ������
        if (head == null) {
            head = new Node<>(data); // head ���� �� ���� ����
        } else {
            head = new Node<>(data, head); // head ��������� �� �������� ����
        }

        count++;
    }

    public void addIndexElement(int index, T data) { // ������� �������� �� �������
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List max index = " + (getSize() - 1) + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // ����� �� sizes
        }

        if (index == 0) { // ���� ����� �������� � ����� ������
            add(data);
            return;
        }

        Node<T> current = head;

        for (int i = 0; i != index - 1; i++, current = current.getNext()) {
            // System.out.println(current.getData());
        }

        Node<T> newNode = new Node<>(data);

        newNode.setNext(current.getNext());
        current.setNext(newNode); // ������������ current = newMode �� ��������

        count++;
    }

    public boolean removeValue(T data) {
        if (head == null) {
            throw new NullPointerException("Head is null!!!"); // ����� �� sizes
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
            throw new NullPointerException("1st Node is null!!!"); // ����� �� sizes
        }

        T deletedValue = head.getData();

        head = head.getNext();

        count--;
        // ��� head = head.getNext();

        return deletedValue;
    }

    public void reverse() { // �������� �� �������� �����
        Node<T> prev = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.getNext();

            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev; // �.�. � ����� ������� current = null

    }

    @Override
    public String toString() { // �������������� toString ��� ������ ������������ ������
        StringBuilder sb = new StringBuilder();

        for (Node<T> p = head; p != null; p = p.getNext()) {
            sb.append(p).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
