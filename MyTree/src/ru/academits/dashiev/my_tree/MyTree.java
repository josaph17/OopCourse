package ru.academits.dashiev.my_tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/* Comparable обязан быть с generic!!! чтобы указ с
    каким типом он сравнивается. Если Comparable указ без типа то будет выдаваться ошибка
    непроверяемого типа. Компилятор не может проверить тип */
public class MyTree<T extends Comparable<T>> {
    private MyTreeNode<T> root;
    private int size;

    public MyTree() { // создаем пустое дерево
    }

    public MyTree(T... elements) {
        for (T node : elements) {
            add(node);
        }
    }

    public void add(T data) {
        if (root == null) {
            root = new MyTreeNode<>(data);

            size++;

            return; // обязательно, чтобы код ниже не выполнялся
        }

        MyTreeNode<T> current = root;

        while (true) { // если значения будут повторяться то ветка перейдет вправо
            if (data == null){
                while(true){
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        current.setLeft(new MyTreeNode<>(data)); // вставляем data как левого сына

                        size++;

                        return;
                    }
                }
            }

            if(current.getData() == null) {
                while (true){
                    if (current.getRight() != null){
                        current = current.getRight();

                        // обязательно выход из цыкла
                        break;
                    } else {
                        current.setRight(new MyTreeNode<>(data)); // вставляем data как правого сына

                        size++;

                        return;
                    }
                }
            }

            //  при data != null скорее всего захожу сюда
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

    public void bypassInWidth(Consumer<? super T> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Queue<MyTreeNode<T>> queue = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> node = queue.poll(); // достаем 1-ый элемент из очереди и удаляем его

            function.accept(node.getData());

            if (node.getLeft() != null) {
                queue.offer(node.getLeft()); //добавить эл-т
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }

        System.out.println();
    }

    public void bypassInDeep(Consumer<? super T> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Deque<MyTreeNode<T>> stack = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // пока очередь не пуста
            MyTreeNode<T> node = stack.removeLast(); // достаем последний элемент из стэка и удаляем его

            // System.out.print(node.getData() + " "); //  выводим его

            function.accept(node.getData());

            if (node.getRight() != null) { // ложим детей в обратном порядке
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }

        // System.out.println();
    }

    public void visitInDeepRecursion(Consumer<? super T> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        bypassInDeepRecursion(root, function);
    }

    private void bypassInDeepRecursion(MyTreeNode<T> node, Consumer<? super T> function) { // private, чтобы не вызвать извне
        function.accept(node.getData());

        // System.out.print(node.getData() + " ");

        if (node.getLeft() != null) {
            bypassInDeepRecursion(node.getLeft(), function);
        }

        if (node.getRight() != null) {
            bypassInDeepRecursion(node.getRight(), function);
        }
    }

    public boolean contains(T data) {
        if(size == 0){
            return false;
        }

        MyTreeNode<T> current = root;
        int compareResult = current.getData().compareTo(data);

        while (true) {
            if(current.getData() == null){
                if(data == null){
                    return true;
                }

                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0) { // из лекции если x < узла 17 стр.
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return false;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private MyTreeNode<T> findNodeToDeleteParent(T data) {
        MyTreeNode<T> current = root;

        while (true){
            while(true){
                if (current.getData() == null){
                    if (current.getRight() != null && current.getRight().getData().compareTo(data) == 0){
                        return current;
                    }

                    if (current.getRight() != null){
                        current = current.getRight();

                        continue;
                    }
                }

                if (current.getData() != null){
                    if(data == null){
                        if (current.getLeft() != null && current.getLeft().getData() == null){
                            return current;
                        }

                        if(current.getLeft() != null){
                            current = current.getLeft();

                            continue;
                        } else {
                            return null;
                        }
                    }

                    if(current.getData().compareTo(data) > 0){
                        if(current.getLeft() != null && current.getLeft().getData() != null){
                            if(current.getLeft().getData().compareTo(data) == 0){
                                return current;
                            } else {
                                current = current.getLeft();

                                continue;
                            }
                            // То этот элемент точно null
                        } else if (current.getLeft() != null){
                            current = current.getLeft();

                            continue;
                        }else {
                            // Нет такого элемента
                            return null;
                        }
                    }

                    if(current.getData().compareTo(data) < 0){
                        if(current.getRight() != null){
                            if(current.getRight().getData().compareTo(data) == 0){
                                return current;
                            } else {
                                current = current.getRight();
                            }
                        } else {
                            // Нет такого элемента
                            return null;
                        }
                    }
                }
            }
        }
    }

    public boolean deleteNode(T data) {
        if(size == 0){
            return false;
        }

        MyTreeNode<T> nodeToDeleteParent = null;
        MyTreeNode<T> nodeToDelete = null;

        if (root.getData() == null && data == null){
            nodeToDelete = root; // nodeToDeleteParent == null
        } else if (root.getData() != null && data != null && root.getData().compareTo(data) == 0){
            nodeToDelete = root; // nodeToDeleteParent == null
        } else {
            nodeToDeleteParent = findNodeToDeleteParent(data);
        }

        //Узнаем есть ли вообще nodeToDelete
        if(nodeToDelete == null) {
            if (nodeToDelete == null && nodeToDeleteParent == null){
                return false; // Нет такого элемента
            } else if (data == null && nodeToDelete == null && nodeToDeleteParent.getLeft()!= null && nodeToDeleteParent.getLeft().getData() == null){
                nodeToDelete = nodeToDeleteParent.getLeft();
            } else if (nodeToDelete == null && nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(data) == 0) {
                nodeToDelete = nodeToDeleteParent.getRight();
            } else if (nodeToDelete == null && nodeToDeleteParent.getLeft() != null && nodeToDeleteParent.getLeft().getData().compareTo(data) == 0){
                nodeToDelete = nodeToDeleteParent.getLeft();
            } else {
                // Так как точно нет элемента для удаления
                return false;
            }
        }

         /* Todo 1. Удаляемый узел - лист */
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDelete == root){
                root = null;
            } else if (nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(nodeToDelete.getData()) == 0) {
                nodeToDeleteParent.setRight(null);
            } else {
                nodeToDeleteParent.setLeft(null);
            }

            size--;

            return true; // 1. Удаляемый узел - лист
        }

        // Todo 2. Удаление узла - c одним ребенком
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) {
            if (nodeToDelete == root){
                if (nodeToDelete.getLeft() == null){
                    root = nodeToDelete.getRight();
                } else if (nodeToDelete.getRight() == null){
                    root = nodeToDelete.getLeft();
                }
            } else if ((nodeToDeleteParent.getRight() != null && nodeToDeleteParent.getRight().getData().compareTo(nodeToDelete.getData()) == 0)) {
                // удаляемый элемент не null и он точно справа
                nodeToDeleteParent.setRight(nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            } else { // удаляемый элемент не null и он точно слева
                nodeToDeleteParent.setLeft(nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            }

            size--;

            return true;
        }

        // Todo 3. Удаление узла - c двумя детьми
        MyTreeNode<T> current = root;
        MyTreeNode<T> minLastNodeParent;
        MyTreeNode<T> minLastNode;
        MyTreeNode<T> rootToDeleteSubTree = root.getRight();

        // Если узел - корень
        if (nodeToDelete == root){
            // В правом поддереве у узла нет левого ребенка
            if(rootToDeleteSubTree.getLeft() == null){
                rootToDeleteSubTree.setLeft(root.getLeft());

                root = rootToDeleteSubTree;

                size --;

                return true;
            } else {
                minLastNodeParent = rootToDeleteSubTree;
                minLastNode = rootToDeleteSubTree.getLeft();

                // Находим самый левый элемент
                while (minLastNode.getLeft() != null){
                    minLastNodeParent = minLastNode;
                    minLastNode = minLastNode.getLeft();
                }
            }

            if(minLastNode.getRight() != null){
                // Если у самого левого узла есть правый сын
                minLastNodeParent.setLeft(minLastNode.getRight());
            } else {
                minLastNodeParent.setLeft(null);
            }

            minLastNode.setLeft(nodeToDelete.getLeft());
            minLastNode.setRight(nodeToDelete.getRight());
            root = minLastNode;

            size --;

            return true;
        }

        rootToDeleteSubTree = nodeToDelete.getRight();

        if (rootToDeleteSubTree.getLeft() == null) {
            rootToDeleteSubTree.setLeft(nodeToDelete.getLeft());
            nodeToDeleteParent.setLeft(rootToDeleteSubTree);

            size--;

            return true;
        }

        minLastNodeParent = rootToDeleteSubTree;
        minLastNode = minLastNodeParent.getLeft();

        while (minLastNode.getLeft() != null) {
            minLastNodeParent = minLastNode; // -- нашли minLastNodeParent
            minLastNode = minLastNodeParent.getLeft(); // -- нашли minLastNode
        }

        if (minLastNode.getRight() == null) {
            minLastNodeParent.setLeft(null);
        } else {
            // если у minLastNode есть ребенок
            minLastNodeParent.setLeft(minLastNode.getRight());
            minLastNode.setRight(null);
        }

        if (data == null && nodeToDeleteParent.getLeft()!= null && nodeToDeleteParent.getLeft().getData() == null){
            nodeToDeleteParent.setLeft(minLastNode);
        } else if (data == null && nodeToDeleteParent.getRight()!= null && nodeToDeleteParent.getRight().getData() == null){
            nodeToDeleteParent.setRight(minLastNode);
        } else if (minLastNodeParent.getRight() != null && minLastNodeParent.getRight().getData().compareTo(nodeToDelete.getData()) == 0) {
            nodeToDeleteParent.setRight(minLastNode);
        } else {
            nodeToDeleteParent.setLeft(minLastNode);
        }

        minLastNode.setRight(nodeToDelete.getRight()); // привязываем детей nodeToDelete к minLastNode
        minLastNode.setLeft(nodeToDelete.getLeft());

        size--;

        return true;
    }

    public void printTree() {
        Deque<MyTreeNode<T>> globalStack = new LinkedList<>();

        globalStack.addLast(root);

        int gapsCount = 32; // Начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator); // Черта для указания начала нового дерева

        while (!isRowEmpty) {
            Deque<MyTreeNode<T>> localStack = new LinkedList<>();
            isRowEmpty = true;

            for (int i = 0; i < gapsCount; i++){
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) { // Пока в общем стеке есть элементы
                MyTreeNode<T> element = globalStack.pop(); // Берем следующий, при этом удаляя его из стека

                if (element != null) {
                    System.out.print(element.getData()); // Выводим его значение в консоли
                    localStack.addLast(element.getLeft()); // Сохраняем в локальный стек, наследники текущего элемента
                    localStack.addLast(element.getRight());

                    if (element.getLeft() != null || element.getRight() != null){
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("__");// Если элемент пустой
                    localStack.addLast(null);
                    localStack.addLast(null);
                }

                for (int i = 0; i < gapsCount * 2 - 2; i++){
                    System.out.print(' ');
                }
            }
            System.out.println();

            gapsCount /= 2; // При переходе на следующий уровень расстояние между элементами каждый раз уменьшается

            while (!localStack.isEmpty())
                globalStack.push(localStack.removeLast()); // Перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator); // Подводим черту
    }

    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>(10, 5, null, 8, 7, 6);

        tree.printTree();

        tree.deleteNode(8);

        tree.printTree();
    }
}