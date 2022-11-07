package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

public class MyTree<T> {
    private MyTreeNode<T> root;

    public MyTree() { // создаем пустое деревофф
        root = null;
    }

    public MyTree(MyTreeNode<T> root) {
        this.root = root;
    }

    public void add(T data) {
    }
}
