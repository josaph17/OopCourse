package ru.academits.dashiev.listItem;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
        // пустой конструктор
    }

    public Node(T data) {
        this.data = data;
        next = null; // указатель указывает на null
    }// добавление последнего эл-а в список

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
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
}
