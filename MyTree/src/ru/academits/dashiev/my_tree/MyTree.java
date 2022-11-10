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

        if (root == null) {
            root = new MyTreeNode<>(data);

            return; // обяхательно
        }

        if (root.getData().compareTo(data) > 0) { // из лекции если x<0 18 стр.
            if (root.getLeft() != null) {
                root = root.getLeft();

                add(data);
            } else {
                root.setLeft(new MyTreeNode<>(data)); // вставляем data как левого сына
            }
        } else {
            if (root.getRight() != null) {
                root = root.getRight();

                add(data);
            } else {
                root.setRight(new MyTreeNode<>(data)); // вставляем data как левого сына
            }
        }
    }

}
