package ru.academits.dashiev.my_tree;

class MyTreeNode<T>  { // wildcard с ? , Надо указать что-то расш , T - т.к. этот тип надо переиспользовать,
    // теперь все поля extends Comparable
    private MyTreeNode<T> left;
    private MyTreeNode<T> right;
    private final T data;

    public MyTreeNode(T data) { // узел без детей
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public MyTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(MyTreeNode<T> left) {
        this.left = left;
    }

    public MyTreeNode<T> getRight() {
        return right;
    }

    public void setRight(MyTreeNode<T> right) {
        this.right = right;
    }
}