package ru.academits.dashiev.list;

import ru.academits.dashiev.listItem.Node;

public class List<T> { // ����� List  ������ ���� generic, ����� ���� ������ �� ������������� � ����
    private Node<T> head;
    private int count; // ����� ������ ����� ������

    public List() {
        count = 0;
        head = null;
    }

    public List(Node<T> head) {
        this.head = head;
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

    public T getValue(int index) {
        if (index >= getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "IndexOutOfBoundsException. List capacity = " + getSize() + ".Current value = " + index);
        }

        if (head == null) {
            throw new NullPointerException("1st Node is null!!!"); // ����� �� sizes
        }

        Node<T> current = head;

        for (int i = getSize(); i != index; i--) {
            current = current.getNext();
        }

        return current.getData();
    }


    //    public T getItem(int index) { // ��������� �������� �� ���������� �������
    //
    //    }
    //
    //    public T setItem(int index, T listItem) {
    //        // ��������� �������� �� ���������� �������, ��������� �������� �� ������� ����� ������ ������ ��������.
    //    }
    //
    //    public void remove(int index) { // �������� �������� �� �������, ����� ������ �������� ��������
    //    }

    public void add(T data) { // ������� �������� � ������
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> current = new Node<>(data, head);
            head = current;
        }

        count++;
    }

    //    public void pushBack(T data) { // ������� �������� � ������
    //        if (head == null) {
    //            head = new Node<T>(data);
    //        } else {
    //            Node<T> current = head;
    //
    //            while (current.getNext() != null) {
    //                current = current.getNext(); // ��������� �� ���� ������
    //            }
    //
    //            current = new Node<T>(
    //                    data); // ���� �������� ��-� ���� �� null, �� ������� ����� ���� � �������
    //        }
    //
    //        count++;
    //    }

    //    public void addIndexItem(int index, T item) { //������� �������� �� �������
    //    }
    //
    //    public boolean remove(T item) {
    //        // �������� ���� �� ��������, ����� ������ true, ���� ������� ��� ������
    //        return true;
    //    }
    //
    //    public T removeStart() { // �������� ������� ��������, ����� ������ �������� ��������
    //    }
    //
    //    public void reverse() { // �������� ������ �� �������� �����
    //
    //    }
    //
    //    public void copyList() {
}
