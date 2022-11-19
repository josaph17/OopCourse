package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MyTree<T extends Comparable<T>> { /* Comparable обязан быть с generic!!! чтобы указ с
    каким типом он сравнивается. Если Comparable указ без типа то будет выдаваться ошибка
    непроверяемого типа. Компилятор не может проверить тип */
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
        if (data == null) { // в своем дереве не допускаю null
            throw new IllegalArgumentException("data не должна быть null!");
        }

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

    public int getSize() {
        return treeSize;
    }

    public void widthBypass() {
        Queue<MyTreeNode<T>> queue = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> element = queue.poll(); // достаем 1-ый элемент из очереди и удаляем его

            System.out.print(element.getData() + " "); //  выводим его

            if (element.getLeft() != null) {
                queue.offer(element.getLeft()); //добавить эл-т
            }

            if (element.getRight() != null) {
                queue.offer(element.getRight());
            }
        }
    }

    public void deepBypass() {
        Deque<MyTreeNode<T>> stack = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> element = stack.removeLast(); // достаем последний элемент из стэка и удаляем его

            System.out.print(element.getData() + " "); //  выводим его

            if (element.getRight() != null) { // ложим детей в обратном порядке
                stack.addLast(element.getRight());
            }

            if (element.getLeft() != null) {
                stack.addLast(element.getLeft());
            }
        }
    }

    public void recursionDeepVisit() {
        recursionDeepBypass(root);
    }

    private void recursionDeepBypass(MyTreeNode<T> node) { // private, чтобы не вызвать извне
        System.out.print(node.getData() + " ");

        if (node.getLeft() != null) {
            recursionDeepBypass(node.getLeft());
        }

        if (node.getRight() != null) {
            recursionDeepBypass(node.getRight());
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

    private MyTreeNode<T> findNodeToDeleteParent(T data) {
        MyTreeNode<T> nodeToDeleteParent = root;

        while (true) { // TODO находим deletedNode и deletedNodeParent
            if (nodeToDeleteParent.getLeft() != null) {
                if (nodeToDeleteParent.getLeft().getData().compareTo(data) == 0) {
                    break;
                }
            }

            if (nodeToDeleteParent.getRight() != null) {
                if (nodeToDeleteParent.getRight().getData().compareTo(data) == 0) {
                    break;
                }
            }

            if (nodeToDeleteParent.getData().compareTo(data) > 0) { // идем влево
                if (nodeToDeleteParent.getLeft() != null) {
                    nodeToDeleteParent = nodeToDeleteParent.getLeft();
                } else {
                    return null;
                }
            } else { // идем вправо
                if (nodeToDeleteParent.getRight() != null) {
                    nodeToDeleteParent = nodeToDeleteParent.getRight();
                } else {
                    return null;
                }
            }
        }

        return nodeToDeleteParent;
    }

    private MyTreeNode<T> findNodeToDelete(MyTreeNode<T> nodeToDeleteParent, T data) {
        if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                data) == 0) {
            return nodeToDeleteParent.getRight();
        } else {
            return nodeToDeleteParent.getLeft();
        }
    }

    private MyTreeNode<T> findMinLastNodeParent(MyTreeNode<T> nodeToDelete, T data) {
        MyTreeNode<T> minLeftNodeParent = null;
        MyTreeNode<T> minRightNodeParent = null;

        if (root.getData().compareTo(data) > 0) {
            // TODO идем влево от корня. У левого потомка самый правый ребенок
            if (nodeToDelete.getLeft() == null) {
                return null; // нету родитетя самого левого потомка
            }

            minRightNodeParent = nodeToDelete.getLeft();

            while (minRightNodeParent.getRight().getRight() != null) {
                minRightNodeParent = minRightNodeParent.getRight();
            }

            return minRightNodeParent;

        } else {
            // TODO идем вправо от корня. У правого потомка самый левый ребенок
            if (nodeToDelete.getRight() == null) {
                return null; // нету родитетя самого левого потомка
            }

            minLeftNodeParent = nodeToDelete.getRight(); // правый потомок

            while (minLeftNodeParent.getLeft().getLeft() != null) {
                minLeftNodeParent = minLeftNodeParent.getLeft();
            }

            return minLeftNodeParent;
        }
    }

    private MyTreeNode<T> findMinLastNode(MyTreeNode<T> minLastNodeParent, T data) {
        if (minLastNodeParent == null) {
            return null;
        }

        MyTreeNode<T> minLeftNode = null;
        MyTreeNode<T> minRightNode = null;

        if (root.getData().compareTo(data) > 0) {
            // TODO идем влево от корня .
            minRightNode = minLastNodeParent.getRight();

            // TODO Если у крайнего правого ребенка есть левый ребенок
            if (minRightNode.getLeft() != null) {
                minLastNodeParent.setRight(minRightNode.getLeft());
            }

            return minRightNode;

        } else {
            // TODO идем вправо от корня.
            minLeftNode = minLastNodeParent.getLeft();

            // TODO Если у крайнего левого ребенка есть правый ребенок
            if (minLeftNode.getRight() != null) {
                minLastNodeParent.setLeft(minRightNode.getRight());
            }

            return minLeftNode;
        }
    }

    public T deleteNode(T data) {
        MyTreeNode<T> minLastNodeParent = null;
        MyTreeNode<T> minLastNode = null;


        if (root.getData().equals(data)) { // надо сравнивать через equals, а то сравнятся
            //            deletedNode = root; //TODO удаляемый элемент -корень дерева
            return null;

        } // TODO - удвление корня = root;


        MyTreeNode<T> deletedNodeParent = findNodeToDeleteParent(data); // находим deletedNodeParent
        MyTreeNode<T> deletedNode = findNodeToDelete(deletedNodeParent,
                                                     data); // находим deletedNode
        minLastNodeParent = findMinLastNodeParent(deletedNode, data);
        minLastNode = findMinLastNode(minLastNodeParent, data);

        if (deletedNodeParent.getRight().getData().compareTo(
                data) == 0) { // ролдительскую ссылку указна самые левый эл-т
            deletedNodeParent.setRight(minLastNode);
        } else {
            deletedNodeParent.setLeft(minLastNode);
        }

        if (deletedNode.getRight() != null) { // передаем детей удаляемого узла
            minLastNode.setRight(deletedNode.getRight());
        }

        if ((deletedNode.getLeft() != null)) { // передаем детей удаляемого узла
            minLastNode.setLeft(deletedNode.getLeft());
        }

        treeSize--;


        return deletedNode.getData();
    }
}