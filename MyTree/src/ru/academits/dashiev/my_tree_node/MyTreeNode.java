package ru.academits.dashiev.my_tree_node;

public class MyTreeNode<T> {
    private MyTreeNode<T> left;
    private MyTreeNode<T> right;
    private T data;

    public MyTreeNode() {  // пустой конструктор
    }

    public MyTreeNode(T data) { // узел без детей
        this.data = data;
        left = null;
        right = null;
    }

    public T getData() {
        return data;
    }
}
