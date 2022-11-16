package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MyTree<T extends Comparable<T>> { // Comparable обяз generic!!! чтобы указ с каким типом он сравнивается
    // Если Comparable указ без типа то будет выдаваться ошибка непрверяемого типа. Компилятор не может проверить тип
    private MyTreeNode<T> root;
    private int treeSize;

    public MyTree() { // создаем пустое деревофф
        treeSize = 0;
        root = null;
    }

    public MyTree(MyTreeNode<T> root) {
        this.root = root;
    }

    public void add(T data) {
        if (root == null) {
            root = new MyTreeNode<>(data);

            treeSize++;

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

                    treeSize++;

                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(new MyTreeNode<>(data)); // вставляем data как правого сына

                    treeSize++;

                    return;
                }
            }
        }
    }

    public MyTreeNode<T> findNode(T data) {
        if (root.getData().equals(data)) { // надо сравнивать через equals, а то сравнятся
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

    public int getSize() {
        return treeSize;
    }

    public void widthTraversal() {
        Queue<T> queue = new LinkedList<>();
        int queueSize = treeSize;

        MyTreeNode<T> current = root;

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(current.getData());

        while (queueSize > 0) { // пока очередь не пуста
            current = findNode(
                    queue.element()); // удаляем из очереди, null если не получится удалить

            T element = queue.peek(); // достаем 1-ый элемент из очереди

            System.out.print(element + " "); //  выводим его

            if (current.getLeft() != null) {
                queue.offer(current.getLeft().getData());
            }

            if (current.getRight() != null) {
                queue.offer(current.getRight().getData());

            }

            queue.remove();
            queueSize--;
        }
    }
}
