package ru.academits.dashiev.my_list;

import java.util.NoSuchElementException;

public class MyList<T> { // класс List  должен быть generic, чтобы тоже жестко не привязываться к типу
    private Node<T> head; // переменная, которая указывает на начало списка
    private int size;

    public MyList() {
    }

    //List(Node<T> head) - конструктор убрал поскольку он нигде не используется

    public MyList(MyList<T> list) {
        for (int i = 0; i < list.size; i++) {
            addByIndex(i, list.get(i));
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();

        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(null);

        System.out.println(list);

        //System.out.println(list.remove(null));
        list.remove(null);

        System.out.println(list);
    }

    public int getSize() { // получение размера списка
        return size;
    }

    public T getFirst() { // получение значения первого элемента
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
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (getSize()) + ". Current index = " + index);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(
                    "List min index = 0, max index = " + (getSize() - 1) + ". Current index = " + index);
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

        Node<T> deletedNode = getNodeByIndex(index);
        Node<T> previousNode = head;

        while (previousNode.getNext() != deletedNode) {
            previousNode = previousNode.getNext();
        }

        previousNode.setNext(deletedNode.getNext()); // более общий случай

        size--;

        return deletedNode.getData();
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

        Node<T> currentNode = head;
        Node<T> nextNode = getNodeByIndex(index);

        while (currentNode.getNext() != nextNode) {
            currentNode = currentNode.getNext();
        }

        currentNode.setNext(new Node<>(data, nextNode));

        size++;
    }

    public boolean remove(T data) {
        // если лист пуст
        if (head == null) {
            return false;
        }

        if (data == null) { // Если элемент null
            Node<T> removedNode = null;

            Node<T> previousNode = head;

            if(previousNode.getData() == null){ // Если этот элемент первый
                head = previousNode.getNext();

                size --;

                return true;
            }

            for (int i = 0; previousNode.getNext().getData() != null && i < size; i++) {
                System.out.println(previousNode.getNext().getData());

                previousNode = previousNode.getNext();
            }

            if (previousNode.getNext().getData() == null) {
                removedNode = previousNode.getNext();
                previousNode.setNext(removedNode.getNext());

                size = size - 1;

                return true;
            } else {
                return false;
            }

        }

        if (head.getData().equals(data)) { // Если элемент в начале списка
            head.setNext(head.getNext());

            size--;

            return true;
        }

        int i = 0;

        for (Node<T> previousNode = head; previousNode != null; previousNode = previousNode.getNext()) {
            if (i == size - 1) { // Нет элемента в списке
                return false;
            }

            if (previousNode.getNext().getData().equals(data)) {
                previousNode.setNext(previousNode.getNext().getNext());

                size--;

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

        head = previousNode; // т.к. к этому моменту currentNode = null
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