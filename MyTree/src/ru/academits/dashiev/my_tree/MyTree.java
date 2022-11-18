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

    public T deleteNode(T data) {
        MyTreeNode<T> deletedNodeParent = null;
        MyTreeNode<T> deletedNode = null;
        MyTreeNode<T> minLeftNodeParent = null;
        MyTreeNode<T> minLeftNode = null;

        boolean isParentFind = false;
        boolean isLeftNodeParentNotFind = true;

        if (root.getData().equals(data)) { // надо сравнивать через equals, а то сравнятся
//            deletedNode = root; //TODO удаляемый элемент -корень дерева
//
//            MyTreeNode<T> maxRightNodeParent = null;
//            MyTreeNode<T> maxRightNode = null;
//
//            maxRightNodeParent = deletedNode.getLeft();
//
//            if (maxRightNodeParent.getRight() == null) { // если справа от родителя нет ничего
//                root = maxRightNodeParent;
//            }
//
//            boolean isLRightNodeParentNotFind = true;
//
//            while ( isLRightNodeParentNotFind){
//                //TODO находим правого родителя
//                while (maxRightNodeParent.getRight().getRight()!=null){
//                    maxRightNodeParent = maxRightNodeParent.getRight();
//                }
//
//                maxRightNode = maxRightNodeParent.getRight();
//
//                if (maxRightNode.getLeft()!=null){ // работаем с maxRightNode
//                    maxRightNodeParent.setRight(maxRightNode.getLeft());
//                }
//
//                maxRightNode.setRight(root.getRight());
//                maxRightNode.setLeft(root.getLeft());
//
//                root = maxRightNode;
//
//                return deletedNode.getData();
//            }
            return null;

        } // TODO - удвление корня

        deletedNodeParent = root;


        while (!isParentFind) { // пока !isParentFind = true TODO находим deletedNode и deletedNodeParent
            if (deletedNodeParent.getLeft() != null) {
                if (deletedNodeParent.getLeft().getData().compareTo(data) == 0) { // угадали
                    deletedNode = deletedNodeParent.getLeft(); // нашли удаляемый элемент
                    isParentFind = true;
                    break;
                }
            }

            if (deletedNodeParent.getRight() != null) {
                if (deletedNodeParent.getRight().getData().compareTo(data) == 0) {
                    deletedNode = deletedNodeParent.getRight(); // нашли удаляемый элемент
                    isParentFind = true;
                    break;
                }
            }

            if (deletedNodeParent.getData().compareTo(data) > 0) { // идем влево
                if (deletedNodeParent.getLeft() != null) {
                    deletedNodeParent = deletedNodeParent.getLeft();
                } else {
                    return null;
                }
            } else { // идем вправо
                if (deletedNodeParent.getRight() != null) {
                    deletedNodeParent = deletedNodeParent.getRight();
                } else {
                    return null;
                }
            }
        } // -- TODO конец находим deletedNode и deletedNodeParent

        // System.out.println("Parent: " + deletedNodeParent.getData());
        // System.out.println("Deleted: " + deletedNode.getData());

        if (deletedNode.getRight() != null) { // TODO находим родителя самого левого потомка из правого узла
            minLeftNodeParent = deletedNode.getRight();

            if (minLeftNodeParent.getLeft()==null){
                if (deletedNode.getLeft()!=null){
                    minLeftNodeParent.setLeft(deletedNode.getLeft());
                }

                deletedNodeParent.setRight(minLeftNodeParent); // обмен noteToDelete с minLeft Node

                return  deletedNode.getData();
            }

            while (minLeftNodeParent.getLeft().getLeft() != null) {
                minLeftNodeParent = minLeftNodeParent.getLeft();
                minLeftNode = minLeftNodeParent.getLeft(); // здесь инициализация

                if (minLeftNode.getRight() != null) { // если есть правый ребенок
                    minLeftNodeParent.setLeft(
                            minLeftNode.getRight()); // правого ребенка передали левому родителю
                }
            }
        } // TODO -- КОНЕЦ находим родителя самого левого потомка из правого узла

        if (deletedNodeParent != null) { // странное условие
            if (deletedNodeParent.getRight().getData().compareTo(
                    data) == 0) { // ролдительскую ссылку указна самые левый эл-т
                deletedNodeParent.setRight(minLeftNode);
            } else {
                deletedNodeParent.setLeft(minLeftNode);
            }
        }

        if (deletedNode.getRight() != null) { // передаем детей удаляемого узла
            minLeftNode.setRight(deletedNode.getRight());

            treeSize--;
        } else if ((deletedNode.getLeft() != null)) {
            minLeftNode.setLeft(deletedNode.getLeft());

            treeSize--;
        }

        return deletedNode.getData();
    }
}