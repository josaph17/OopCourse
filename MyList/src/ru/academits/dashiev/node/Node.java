package ru.academits.dashiev.node;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
        // ������ �����������
    }

    public Node(T data) {
        this.data = data;
        next = null; // ��������� ��������� �� null
    }// ���������� ���������� ��-� � ������

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() { // �������������� toString ��� ������ ������������ ������
        return data.toString();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        final int prime = 113;
        int hash = 1;

        hash = data.hashCode() * prime;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        if (this == obj) {
            return true;
        }

        Node<T> temp = (Node<T>) obj;

        return (this.data == temp.data);
    }
}
