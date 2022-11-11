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

        boolean isFindPlace = false;

        while (!isFindPlace) {
            if (current.getData().compareTo(data) > 0) { // из лекции если x<0 18 стр.
                if (current.getLeft() != null) {
                    current = current.getLeft();

                    // add(data);
                } else {
                    current.setLeft(new MyTreeNode<>(data)); // вставляем data как левого сына
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();

                    // add(data);
                } else {
                    current.setRight(new MyTreeNode<>(data)); // вставляем data как левого сына
                    return;
                }
            }
        }
    }

}
