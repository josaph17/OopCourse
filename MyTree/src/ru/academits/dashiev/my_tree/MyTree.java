package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

public class MyTree<T extends Comparable<T>> { // Comparable обяз generic!!! чтобы указ с каким типом он сравнивается
    // Если Comparable указ без типа то будет выдаваться ошибка непрверяемого типа. Компилятор не может проверить тип
    private MyTreeNode<T> root;

    public MyTree() { // создаем пустое деревофф
        root = null;
    }

    public MyTree(MyTreeNode<T> root) {
        this.root = root;
    }

    public void add(T data) {
        if (root == null) {
            root = new MyTreeNode<>(data);

            return; // обязательно, чтобы код ниже не выполнялся
        }

        MyTreeNode<T> current = root;

        while (true) { // если значения будут повторяться то ветка перейдет вправо
            if (current.getData().compareTo(
                    data) > 0) { // из лекции если x<узла 18 стр., equals можно заменить на compare,
                // если у Объекта Comparable, equals они равны или не равны
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

    public MyTreeNode<T> findNode(T data) {
        if (root.getData().equals(data)) { // надо сравнивать через equals, а то сравнятся ссылки
            return root; // узел существует
        }

        MyTreeNode<T> current = root;

        while (true) {
            if (current.getData().compareTo(data) == 0) {
                return current;
            }

            if (current.getData().compareTo(data) > 0) { // из лекции если x < узла 17 стр.
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return null;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public boolean deleteNode(T data) {
        if(root.getData().equals(data)){
            // TODO случай когда надо удалить корень дерева
        }

        MyTreeNode<T> nodeToDelete = findNode(data);
        MyTreeNode<T> nodeToDeleteParent = findNode(data);
        MyTreeNode<T> minLeftNode = findNode(data);
        MyTreeNode<T> minLeftNodeParent = findNode(data);


        minLeftNode = nodeToDelete.getRight();

        while (true) {
            if (minLeftNodeParent.getRight().getData().compareTo(data) == 0) {
                // нашли родителя и элемент который надо удалить
                nodeToDelete = minLeftNodeParent.getRight();
            }

            // TODO сначало найдем родителя и элемент, который надо удалить
            if (minLeftNodeParent.getRight().getData().compareTo(data) > 0) { // из лекции если x < узла 17 стр.б, идем влево
                if (minLeftNodeParent.getRight().getLeft() != null) {
                    minLeftNodeParent = minLeftNodeParent.getRight().getLeft();
                } else {
                    return null;
                }
            } else {
                if (minLeftNodeParent.getRight().getRight() != null) {
                    minLeftNodeParent.getRight() = minLeftNodeParent.getRight().getRight();
                } else {
                    return null;
                }
            }
        }
    }
}
