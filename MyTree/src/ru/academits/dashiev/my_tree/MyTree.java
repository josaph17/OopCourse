package ru.academits.dashiev.my_tree;

import ru.academits.dashiev.my_tree_node.MyTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/* Comparable обязан быть с generic!!! чтобы указ с
    каким типом он сравнивается. Если Comparable указ без типа то будет выдаваться ошибка
    непроверяемого типа. Компилятор не может проверить тип */
public class MyTree<T extends Comparable<T>> {
    private MyTreeNode<T> root;
    private int treeSize;

    public MyTree() { // создаем пустое деревофф
        treeSize = 0;
        root = null;
    }

    public MyTree(MyTreeNode<T> root) {
        this.root = root;
    }

    public MyTree(T... elements) {
        this();
        for (T element : elements) {
            this.add(element);
        }
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

        System.out.println();
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

        System.out.println();
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
        if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                data) == 0) {
            return nodeToDeleteParent.getRight();
        } else {
            return nodeToDeleteParent.getLeft();
        }
    }

    private MyTreeNode<T> findMinLastNodeParent(MyTreeNode<T> nodeToDelete,
                                                MyTreeNode<T> rootToDeleteSubTree, T data) {
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
            // TODO идем влево от корня. У левого потомка самый правый ребенок
            minRightNodeParent = nodeToDelete.getLeft();

            stop = minLeftNodeParent.getRight();

            //todo посмотреть stop
            while (stop.getRight() != null) {
                minRightNodeParent = minRightNodeParent.getRight();
                stop = minRightNodeParent.getRight();
            }

            return minRightNodeParent;
        } else {
            // TODO идем вправо от корня. У правого потомка самый левый ребенок
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
            // TODO идем влево от корня .
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
            // TODO идем вправо от корня.
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

        if (root.getData().equals(data)) {
            /* удаляемый элемент -корень дерева надо сравнивать через equals, а то сравнятся сылки */
            nodeToDelete = root;

            if (root.getLeft() == null && root.getRight() == null) { // у корня нет детей
                root = null;

                treeSize--;

                return nodeToDelete.getData();
            }

            if (root.getLeft() == null) { // у корня есть 1 правый ребенок
                root = root.getRight();

                treeSize--;

                return nodeToDelete.getData();
            }

            if (root.getRight() == null) { // у корня есть 1 левый ребенок
                root = root.getLeft();

                treeSize--;

                return nodeToDelete.getData();
            }

            minLastNodeParent = nodeToDelete.getRight(); // находим minLastNodeParent

            if (minLastNodeParent.getLeft() == null) { // когда minLastNode == minLastNodeParent;
                minLastNode = minLastNodeParent;

                minLastNode.setLeft(nodeToDelete.getLeft());

                root = minLastNode;

                treeSize--;

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

            treeSize--;

            return nodeToDelete.getData();

        } // TODO - удвление корня = root;

        nodeToDeleteParent = findNodeToDeleteParent(data);
        if (nodeToDeleteParent == null) {
            return null; // 0. Нет элемента в дереве
        }
        // находим deletedNodeParent

        nodeToDelete = findNodeToDelete(nodeToDeleteParent, data); // находим deletedNode

        //  TODO 1. Удаляемый узел - лист
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                    nodeToDelete.getData()) == 0) {
                nodeToDeleteParent.setRight(null);
            } else {
                nodeToDeleteParent.setLeft(null);

            }

            treeSize--;

            return nodeToDelete.getData(); // 1. Удаляемый узел - лист
        }

        // TODO 2. Удаление узла - c одним ребенком
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) { // 2. Удаление узла - c одним ребенком
            if ((nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(
                    nodeToDelete.getData()) == 0)) { // удаляемый элемент не null и он точно справа
                nodeToDeleteParent.setRight(
                        nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            } else { // удаляемый элемент не null и он точно слева
                nodeToDeleteParent.setLeft(
                        nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            }

            treeSize--;

            return nodeToDelete.getData();
        }

        // TODO 3. Удаление узла - c двумя детьми
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

            treeSize--;

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

        treeSize--;
        return nodeToDelete.getData();
    }

    public void printTree() {
        Deque<MyTreeNode<T>> globalStack = new LinkedList<>();

        globalStack.addLast(root);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Deque<MyTreeNode<T>> localStack = new LinkedList<>();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                MyTreeNode<T> temp = (MyTreeNode<T>) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getData()); // выводим его значение в консоли
                    localStack.addLast(
                            temp.getLeft()); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.addLast(temp.getRight());
                    if (temp.getLeft() != null || temp.getRight() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");// - если элемент пустой
                    localStack.addLast(null);
                    localStack.addLast(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(
                        localStack.removeLast()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
}