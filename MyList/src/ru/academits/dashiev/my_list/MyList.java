package ru.academits.dashiev.my_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyList<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head; // переменная, которая указывает на начало списка
    private int size;

    public MyList() {
    }

    public MyList(MyList<T> list) {
        if (list == null){
            throw new NullPointerException("List is null!");
        }

        size = list.size;

        Node<T> listCurrent = list.head;

        head = new Node<>(listCurrent.getData());

        Node<T> current = head;

        for (int i = 0; i < list.size - 1; i++) {
            listCurrent = listCurrent.getNext();

            Node<T> newNode = new Node<>(listCurrent.getData());

            current.setNext(newNode);

            current = current.getNext();
        }
    }

    public int getSize() { // получение размера списка
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty."); // выход за sizes
        }

        return head.getData();
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> currentNode = head;

        for (int i = 0; i != index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + (getSize()) + ". Current index = " + index);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + (getSize() - 1) + ". Current index = " + index);
        }
    }

    public T get(int index) {
        checkIndex(index);

        return getNodeByIndex(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        Node<T> currentNode = getNodeByIndex(index);

        T oldData = currentNode.getData();

        currentNode.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) { // удаляемое значение 1-й элемент
            return removeFirst();
        }

        T deletedNodeData = getNodeByIndex(index).getData();

        getNodeByIndex(index - 1).setNext(getNodeByIndex(index).getNext()); // общий случай

        size--;

        return deletedNodeData;
    }

    public void addFirst(T data) {
        head = new Node<>(data, head);

        size++;
    }

    public void addByIndex(int index, T data) {
        checkAddIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> previousNode = getNodeByIndex(index - 1);
        Node<T> nextNode = getNodeByIndex(index);

        previousNode.setNext(new Node<>(data, nextNode));

        size++;
    }

    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        // Если элмент в начале списка
        if (Objects.equals(head.getData(), data)) {

            head = head.getNext();

            size--;

            return true;
        }

        Node<T> removedNode;
        Node<T> previousNode = head;

        for (int i = 1; !Objects.equals(previousNode.getNext().getData(), data); i++) {
            previousNode = previousNode.getNext();

            if (i == size - 1) {
                return false; // нет элемента в списке
            }
        }

        removedNode = previousNode.getNext();
        previousNode.setNext(removedNode.getNext());

        size--;

        return true;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }

        T oldData = head.getData();

        head = head.getNext();

        size--;

        return oldData;
    }

    public void reverse() { // разворот за линейное время
        Node<T> previousNode = null;
        Node<T> currentNode = head;

        while (currentNode != null) {
            Node<T> nextNode = currentNode.getNext();

            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode; // к этому моменту currentNode = null
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Node<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            sb.append(currentNode.getData()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append("]");

        return sb.toString();
    }
}