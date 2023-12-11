package ru.academits.dashiev.tree;

class TreeNode<T>  { // Wildcard с ? , Надо указать что-то расш, T - т.к. этот тип надо переиспользовать,
    // теперь все поля extends Comparable
    private TreeNode<T> left;
    private TreeNode<T> right;
    private final T data;

    public TreeNode(T data) { // узел без детей
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}