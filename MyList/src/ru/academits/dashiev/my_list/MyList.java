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

        Node<T> currentListNode = list.head;

        head = new Node<>(currentListNode.getData());

        currentListNode = currentListNode.getNext();

        Node<T> currentNode = head;

        while(currentListNode != null){
            currentNode.setNext(new Node<>(currentListNode.getData()));
            currentListNode = currentListNode.getNext();
            currentNode = currentNode.getNext();
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

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + size + ". Current index = " + index);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("List min index = 0, max index = " + (size - 1) + ". Current index = " + index);
        }
    }

    public T get(int index) {
        checkIndex(index);

        return getNodeByIndex(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        Node<T> currentNode = getNodeByIndex(index);

        T deletedData = currentNode.getData();

        currentNode.setData(data);

        return deletedData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) { // удаляемое значение 1-й элемент
            return removeFirst();
        }

        Node<T> beforeRemovedNode = getNodeByIndex(index-1);

        T removedData = beforeRemovedNode.getNext().getData();

        beforeRemovedNode.setNext(beforeRemovedNode.getNext().getNext());

        size--;

        return removedData;
    }

    public void addFirst(T data) {
        head = new Node<>(data, head);

        size++;
    }

    public void addByIndex(int index, T data) {
        checkIndexForAdd(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> previousNode = getNodeByIndex(index - 1);

        if (index == size){
            previousNode.setNext(new Node<>(data));

            size++;

            return;
        }

        Node<T> nextNode = previousNode.getNext();
        Node<T> newNode = new Node<>(data, nextNode);

        previousNode.setNext(newNode);

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


        for (Node<T> previousNode = head; previousNode.getNext()!= null; previousNode = previousNode.getNext()) {
            if (Objects.equals(previousNode.getNext().getData(), data)){
                Node<T> removedNode = previousNode.getNext();
                
                previousNode.setNext(removedNode.getNext());

                size--;

                return true;
            }
        }

        return false;
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