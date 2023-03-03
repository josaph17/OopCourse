package ru.academits.dashiev.my_tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/* Comparable обязан быть с generic!!! чтобы указ с
    каким типом он сравнивается. Если Comparable указ без типа то будет выдаваться ошибка
    непроверяемого типа. Компилятор не может проверить тип */
public class MyTree<T extends Comparable<T>> {
    private MyTreeNode<T> root;
    private int size;

    public MyTree() { // создаем пустое деревофф
    }

    public MyTree(T... elements) {
        this();

        for (T node : elements) {
            add(node);
        }
    }

    public void add(T data) {
        if (data == null) { // в своем дереве не допускаю null
            throw new IllegalArgumentException("data не должна быть null!");
        }

        if (root == null) {
            root = new MyTreeNode<>(data);

            size++;

            return; // обязательно, чтобы код ниже не выполнялся
        }

        MyTreeNode<T> current = root;

        while (true) { // если значения будут повторяться то ветка перейдет вправо
            if (current.getData().compareTo(data) > 0) { // из лекции если x<узла 18 стр., equals можно заменить на compare,
                // если у Объекта Comparable, equals они равны или не равны
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(new MyTreeNode<>(data)); // вставляем data как левого сына

                    size++;

                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(new MyTreeNode<>(data)); // вставляем data как правого сына

                    size++;

                    return;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void bypassInWidth() {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Queue<MyTreeNode<T>> queue = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> node = queue.poll(); // достаем 1-ый элемент из очереди и удаляем его

            System.out.print(node.getData() + " "); //  выводим его

            if (node.getLeft() != null) {
                queue.offer(node.getLeft()); //добавить эл-т
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }

        System.out.println();
    }

    public void bypassInDeep() {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Deque<MyTreeNode<T>> stack = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> node = stack.removeLast(); // достаем последний элемент из стэка и удаляем его

            System.out.print(node.getData() + " "); //  выводим его

            if (node.getRight() != null) { // ложим детей в обратном порядке
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }

        System.out.println();
    }

    public void visitInDeepRecursion() {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        bypassInDeepRecursion(root);
    }

    private void bypassInDeepRecursion(MyTreeNode<T> node) { // private, чтобы не вызвать извне
        System.out.print(node.getData() + " ");

        if (node.getLeft() != null) {
            bypassInDeepRecursion(node.getLeft());
        }

        if (node.getRight() != null) {
            bypassInDeepRecursion(node.getRight());
        }
    }

    public MyTreeNode<T> contains(T data) {
        if (Objects.equals(root.getData(), data)) {
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

        while (true) { // Находим deletedNode и deletedNodeParent
            if (nodeToDeleteParent.getLeft() != null) {
                if (nodeToDeleteParent.getLeft().getData().compareTo(data) == 0) {
                    break;
                    //                    return nodeToDeleteParent;
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
        if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(data) == 0) {
            return nodeToDeleteParent.getRight();
        }

        return nodeToDeleteParent.getLeft();
    }

    private MyTreeNode<T> findMinLeftNodeParent(MyTreeNode<T> nodeToDelete, MyTreeNode<T> rootToDeleteSubTree, T data) {
        if (root.getData().compareTo(data) > 0) {
            // идем влево от корня
            if (rootToDeleteSubTree.getRight() == null) {
                return rootToDeleteSubTree;
            }
        } else {
            if (rootToDeleteSubTree.getLeft() == null) {
                return rootToDeleteSubTree;
            }
        }

        MyTreeNode<T> minLeftNodeParent = null;
        MyTreeNode<T> minRightNodeParent = null;
        MyTreeNode<T> stop = null;

        if (root.getData().compareTo(data) > 0) {
            // Идем влево от корня. У левого потомка самый правый ребенок
            minRightNodeParent = nodeToDelete.getLeft();

            stop = minLeftNodeParent.getRight();

            // Посмотреть stop
            while (stop.getRight() != null) {
                minRightNodeParent = minRightNodeParent.getRight();
                stop = minRightNodeParent.getRight();
            }

            return minRightNodeParent;
        } else {
            // Идем вправо от корня. У правого потомка самый левый ребенок
            minLeftNodeParent = nodeToDelete.getRight(); // ВНимание! правый потомок

            stop = minLeftNodeParent.getLeft();

            while (stop.getLeft() != null) {
                minLeftNodeParent = minLeftNodeParent.getLeft();
                stop = minLeftNodeParent.getLeft();
            }

            return minLeftNodeParent;
        }
    }

    private MyTreeNode<T> findMinLastNode(MyTreeNode<T> minLastNodeParent, T data) {
        MyTreeNode<T> minLeftNode = null;
        MyTreeNode<T> minRightNode = null;

        if (root.getData().compareTo(data) > 0) {
            // Идем влево от корня .
            if (minLastNodeParent.getRight() == null) {
                return minLastNodeParent;
            }

            minRightNode = minLastNodeParent.getRight();

            // Если у крайнего правого ребенка есть левый ребенок
            if (minRightNode.getLeft() != null) {
                minLastNodeParent.setRight(minRightNode.getLeft());
            }

            return minRightNode;

        } else {
            // Идем вправо от корня.
            if (minLastNodeParent.getLeft() == null) {
                return minLastNodeParent;
            }

            minLeftNode = minLastNodeParent.getLeft();

            // Если у крайнего левого ребенка есть правый ребенок
            if (minLeftNode.getRight() != null) {
                minLastNodeParent.setLeft(minLeftNode.getRight());
            }

            return minLeftNode;
        }
    }

    public T deleteNode(T data) {
        MyTreeNode<T> minLastNodeParent = null;
        MyTreeNode<T> minLastNode = null;
        MyTreeNode<T> rootToDeleteSubTree = null;
        MyTreeNode<T> nodeToDeleteParent = null;
        MyTreeNode<T> nodeToDelete = null;

        if (Objects.equals(root.getData(), data)) {
            /* удаляемый элемент - корень дерева надо сравнивать через Objects.equals, а то сравнятся сылки */
            nodeToDelete = root;

            if (root.getLeft() == null && root.getRight() == null) { // у корня нет детей
                root = null;

                size--;

                return nodeToDelete.getData();
            }

            if (root.getLeft() == null) { // у корня есть 1 правый ребенок
                root = root.getRight();

                size--;

                return nodeToDelete.getData();
            }

            if (root.getRight() == null) { // у корня есть 1 левый ребенок
                root = root.getLeft();

                size--;

                return nodeToDelete.getData();
            }

            minLastNodeParent = nodeToDelete.getRight(); // находим minLastNodeParent

            if (minLastNodeParent.getLeft() == null) { // когда minLastNode == minLastNodeParent;
                minLastNode = minLastNodeParent;

                minLastNode.setLeft(nodeToDelete.getLeft());

                root = minLastNode;

                size--;

                return nodeToDelete.getData();
            }

            minLastNode = minLastNodeParent.getLeft();

            while (minLastNode.getLeft() != null) { // перенес на частные случаи
                minLastNodeParent = minLastNodeParent.getLeft(); // -- нашли minLastNodeParent
                minLastNode = minLastNodeParent.getLeft(); // -- нашли minLastNode
            }

            if (minLastNode.getRight() != null) { // отвязываем Last Node, если у LastNode нет ребенка
                minLastNodeParent.setLeft(minLastNode.getRight());
            } else {
                minLastNodeParent.setLeft(null);
            }

            root = minLastNode; // корень и есть minLAstNode

            root.setLeft(nodeToDelete.getLeft()); // привязываем левого ребенка к root
            root.setRight(nodeToDelete.getRight()); // привязываем левого ребенка к root

            size--;

            return nodeToDelete.getData();

        } // Удаление корня = root;

        nodeToDeleteParent = findNodeToDeleteParent(data);
        if (nodeToDeleteParent == null) {
            return null; // 0. Нет элемента в дереве
        }
        // находим deletedNodeParent

        nodeToDelete = findNodeToDelete(nodeToDeleteParent, data); // находим deletedNode

        // 1. Удаляемый узел - лист
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(nodeToDelete.getData()) == 0) {
                nodeToDeleteParent.setRight(null);
            } else {
                nodeToDeleteParent.setLeft(null);

            }

            size--;

            return nodeToDelete.getData(); // 1. Удаляемый узел - лист
        }

        // 2. Удаление узла - c одним ребенком
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) { // 2. Удаление узла - c одним ребенком
            if ((nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                    nodeToDelete.getData()) == 0)) { // удаляемый элемент не null и он точно справа
                nodeToDeleteParent.setRight(
                        nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            } else { // удаляемый элемент не null и он точно слева
                nodeToDeleteParent.setLeft(
                        nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            }

            size--;

            return nodeToDelete.getData();
        }

        // 3. Удаление узла - c двумя детьми
        rootToDeleteSubTree = nodeToDelete.getRight();

        if (rootToDeleteSubTree.getLeft() == null) { // когда minLastNode == rootToDeleteSubTree;
            minLastNode = rootToDeleteSubTree;

            if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                    data) == 0) { // nodeToDeletePare.getRight = nodeToDelete
                if (minLastNode.getLeft() != null) {
                    nodeToDelete.setLeft((minLastNode.getLeft()));
                }

                nodeToDeleteParent.setRight(minLastNode);

                minLastNode.setRight(nodeToDelete.getRight());
            } else {
                if (minLastNode.getRight() != null) {
                    nodeToDelete.setRight((minLastNode.getRight()));
                }

                nodeToDeleteParent.setLeft(minLastNode);

                minLastNode.setLeft(nodeToDelete.getLeft());
            }

            size--;

            return nodeToDelete.getData();
        }

        minLastNodeParent = rootToDeleteSubTree;
        minLastNode = minLastNodeParent.getLeft();

        while (minLastNode.getLeft() != null) { // частный случай
            minLastNodeParent = minLastNodeParent.getLeft(); // -- нашли minLastNodeParent
            minLastNode = minLastNodeParent.getLeft(); // -- нашли minLastNode
        }

        // отвязываем minLastNode от minLastNodeParent
        if (minLastNode.getRight() != null) { // если у minLastNode есть ребенок
            minLastNodeParent.setLeft(minLastNode.getRight());
            minLastNode.setRight(null);
        } else {
            minLastNodeParent.setLeft(null);
        }

        if (minLastNodeParent.getRight() != null && minLastNodeParent.getRight().getData().compareTo(
                nodeToDelete.getData()) == 0) {
            nodeToDeleteParent.setRight(minLastNode);
        } else {
            nodeToDeleteParent.setLeft(minLastNode);
        }

        minLastNode.setRight(
                nodeToDelete.getRight()); // привязываем детей nodeToDelete к minLastNode
        minLastNode.setLeft(nodeToDelete.getLeft());

        size--;
        return nodeToDelete.getData();
    }

    public void printTree() {
        Deque<MyTreeNode<T>> globalStack = new LinkedList<>();

        globalStack.addLast(root);

        int gaps = 32; // Начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator); // Черта для указания начала нового дерева

        while (isRowEmpty == false) {
            Deque<MyTreeNode<T>> localStack = new LinkedList<>();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++){
                System.out.print(' ');
            }

            while (globalStack.isEmpty() == false) { // Пока в общем стеке есть элементы
                MyTreeNode<T> temp = (MyTreeNode<T>) globalStack.pop(); // Берем следующий, при этом удаляя его из стека

                if (temp != null) {
                    System.out.print(temp.getData()); // Выводим его значение в консоли
                    localStack.addLast(temp.getLeft()); // Сохраняем в локальный стек, наследники текущего элемента
                    localStack.addLast(temp.getRight());

                    if (temp.getLeft() != null || temp.getRight() != null){
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("__");// Если элемент пустой
                    localStack.addLast(null);
                    localStack.addLast(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++){
                    System.out.print(' ');
                }
            }
            System.out.println();

            gaps /= 2; // При переходе на следующий уровень расстояние между элементами каждый раз уменьшается

            while (localStack.isEmpty() == false)
                globalStack.push(localStack.removeLast()); // Перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator); // Подводим черту
    }

    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();

        tree.bypassInWidth();
    }
}