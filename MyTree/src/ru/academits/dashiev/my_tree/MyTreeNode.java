package ru.academits.dashiev.my_tree_node;

public class MyTreeNode<T extends Comparable<T>>  { // wildcard с ? , Надо указать что-то расш , T - т.к. этот тип надо переиспользовать,
    // теперь все поля extends Comparable
    private MyTreeNode<T> left;
    private MyTreeNode<T> right;
    private T data;

    public MyTreeNode(T data) { // узел без детей
        this.data = data;
        left = null;
        right = null;
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

    @Override
    public int hashCode() {
        final int prime = 113;

        return data.hashCode() * prime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        MyTreeNode<T> temp = (MyTreeNode<T>) obj;

        return (this.data == temp.data);
    }
}
