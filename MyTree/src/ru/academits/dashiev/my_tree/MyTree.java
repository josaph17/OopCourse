package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

public class MyTree<T extends Comparable> {
    private MyTreeNode<T> root;

    public MyTree() { // создаем пустое деревофф
        root = null;
    }

    public MyTree(MyTreeNode<T> root) {
        this.root = root;
    }

    public void add(T data) {
        // сделать current
        MyTreeNode<T> current = new MyTreeNode<>(data);

        current = root;

        if (root == null) {
            root = new MyTreeNode<>(data);

            return; // обязательно, чтобы код ниже не выполнялся
        }

        while (true) { // если значения будут повторяться то ветка перейдет вправо
            if (current.getData().compareTo(data) > 0) { // из лекции если x<узла 18 стр.
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(new MyTreeNode<>(data)); // вставляем data как левого сына
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(new MyTreeNode<>(data)); // вставляем data как левого сына
                    return;
                }
            }
        }
    }

    public boolean isExist (T data) {
        // сделать current
        MyTreeNode<T> current = new MyTreeNode<>(data);

        current = root;

        if (root == data) {
            return true; // узел существует
        }

        while (true) {
            if (current.getData().compareTo(data) == 0){
                return true;
            }

            if (current.getData().compareTo(data) > 0) { // из лекции если x < узла 17 стр.
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return false;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }
        }
    }
}
