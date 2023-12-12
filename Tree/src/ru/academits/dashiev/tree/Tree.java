package ru.academits.dashiev.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;

    private Comparator<E> comparator;

    public Tree() { // конструктор без компаратора
    }

    public Tree(Comparator<E> comparator){ // конструктор с компаратором
        this.comparator = comparator;
    }

    public Tree(Comparator<E> comparator, E... elements) {
        this.comparator = comparator;

        for (E element : elements) {
           add(element);
        }
    }

    private int compare(E data1, E data2){
        if (comparator != null){
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null){
            return -1;
        }

        if (data2 == null){
            return 1;
        }

        //Если для дерева используют конструктор без компаратора, то для сравнения нужно приводить
        // данные к Comparable<T> и вызывать метод compareTo оттуда

        @SuppressWarnings("unchecked")
        Comparable<E> convertedToComparableObject = (Comparable<E>) data1;

        return convertedToComparableObject.compareTo(data2);
    }

    public void add(E data) {
        if (root == null) {
            root = new TreeNode<>(data);

            size++;

            return; // обязательно, чтобы код ниже не выполнялся
        }

        TreeNode<E> currentNode = root;

        while (true) { // если значения будут повторяться, то ветка перейдет вправо
            if (compare(currentNode.getData(), data) > 0) { // из лекции если x<узла 18 стр., equals можно заменить на compare,
                // если у Объекта Comparable, equals они равны или не равны
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data)); // вставляем data как левого сына

                    size++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data)); // вставляем data как правого сына

                    size++;

                    return;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void bypassInWidth(Consumer<? super E> consumer) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // пока очередь не пус та
            TreeNode<E> data = queue.poll(); // достаем первый элемент из очереди и удаляем его

            consumer.accept(data.getData());

            if (data.getLeft() != null) {
                queue.offer(data.getLeft()); //добавить эл-т
            }

            if (data.getRight() != null) {
                queue.offer(data.getRight());
            }
        }
    }

    public void bypassInDeep(Consumer<? super E> consumer) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // пока очередь не пуста
            TreeNode<E> data = stack.removeLast(); // достаем последний элемент из stack и удаляем его

            consumer.accept(data.getData());

            if (data.getRight() != null) { // добавляем детей в обратном порядке
                stack.addLast(data.getRight());
            }

            if (data.getLeft() != null) {
                stack.addLast(data.getLeft());
            }
        }
    }

    public void bypassInDeepRecursively(Consumer<? super E> consumer) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        bypassInDeepRecursively(root, consumer);
    }

    // Todo правильно ли я понимаю, что это перегрузка функции bypassInDeepRecursively?
    private void bypassInDeepRecursively(TreeNode<E> data, Consumer<? super E> consumer) { // private, чтобы не вызвать извне
        consumer.accept(data.getData());

        // System.out.print(node.getData() + " ");

        if (data.getLeft() != null) {
            bypassInDeepRecursively(data.getLeft(), consumer);
        }

        if (data.getRight() != null) {
            bypassInDeepRecursively(data.getRight(), consumer);
        }
    }

    public boolean contains(E data) {
        // Если дерево пустое
        if(size == 0){
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int compareResult = compare(currentNode.getData(), data);

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0){
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private TreeNode<E> findNodeParent(E data) {
        // Вместо findNodeToDeleteParent переименовали в findNodeParent
        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = root;

        while(true){
            int compareResult = compare(currentNode.getData(), data);

            // todo Прошу проверить метод
            if(compareResult >= 0){
                if(currentNode.getLeft() != null && currentNode.getLeft() != null){
                    if(compare(currentNode.getLeft().getData(), data) == 0){
                        return currentNode;
                    }

                    currentNode = currentNode.getLeft();

                    continue;
                    // То этот элемент точно null
                }

                if (currentNode.getLeft() != null){
                    currentNode = currentNode.getLeft();

                    continue;
                }else {
                    // Нет такого элемента
                    return null;
                }
            }

            if(currentNode.getRight() != null){
                if(compare(currentNode.getRight().getData(), data) == 0){
                    return currentNode;
                }

                currentNode = currentNode.getRight();
            } else {
                // Нет такого элемента
                return null;
            }
        }
    }

    public boolean delete(E data) {
        if(size == 0){
            return false;
        }

        TreeNode<E> nodeToDeleteParent = null;
        TreeNode<E> nodeToDelete = null;

        if (compare(root.getData(), data) == 0){
            nodeToDelete = root; // nodeToDeleteParent == null
        } else {
            nodeToDeleteParent = findNodeParent(data);
        }

        //Узнаем есть ли вообще nodeToDelete

        boolean isRightChild = false; // Про тот узел, который надо удалить

        if(nodeToDeleteParent == null) {
            if (nodeToDelete != root){
                return false;
            }
        } else {
            if (nodeToDeleteParent.getLeft()!= null && compare(nodeToDeleteParent.getLeft().getData(), data) == 0){
                nodeToDelete = nodeToDeleteParent.getLeft();
            } else if (nodeToDeleteParent.getRight() != null && compare(nodeToDeleteParent.getRight().getData(), data) == 0) {
                nodeToDelete = nodeToDeleteParent.getRight();
                isRightChild = true;
            } else {
                // Так как точно нет элемента для удаления
                return false;
            }
        }

        /* Todo 1. Удаляемый узел - лист */
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDelete == root){
                root = null;
            } else if (isRightChild) {
                nodeToDeleteParent.setRight(null);
            } else {
                assert nodeToDeleteParent != null;
                nodeToDeleteParent.setLeft(null);
            }

            size--;

            return true; // 1. Удаляемый узел - лист
        }

        // Todo 2. Удаление узла - c одним ребенком
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) {
            TreeNode<E> nextNode;
            if (nodeToDelete.getRight() != null) {
                nextNode = nodeToDelete.getRight();

            } else {
                nextNode = nodeToDelete.getLeft();

            }

            if (nodeToDelete == root) {
                root = nextNode;
            } else if (isRightChild){
                nodeToDeleteParent.setRight(nextNode);
            }else {
                assert nodeToDeleteParent != null;
                nodeToDeleteParent.setLeft(nextNode);
            }

            size--;

            return true;
        }

        // Todo 3. Удаление узла - c двумя детьми
        TreeNode<E> minLastNodeParent;
        TreeNode<E> minLastNode;
        TreeNode<E> rootToDeleteSubTree = root.getRight();

        if (nodeToDelete!= root){
            rootToDeleteSubTree = nodeToDelete.getRight();
        }

        // В правом поддереве у узла нет левого ребенка
        if (rootToDeleteSubTree.getLeft() == null) {
            if (nodeToDelete == root){
                rootToDeleteSubTree.setLeft(root.getLeft());

                root = rootToDeleteSubTree;
            } else if(isRightChild){
                nodeToDeleteParent.setRight(rootToDeleteSubTree);

                rootToDeleteSubTree.setLeft(nodeToDelete.getLeft());

            } else {
                // TODO что такое assert? IDEA подсказала так сделать, чтобы не было "Method invocation 'setLeft' may produce 'NullPointerException'"
                assert nodeToDeleteParent != null;
                nodeToDeleteParent.setLeft(rootToDeleteSubTree);

                rootToDeleteSubTree.setLeft(nodeToDelete.getLeft());
            }

            size--;

            return true;
        }

        minLastNodeParent = rootToDeleteSubTree;
        minLastNode = rootToDeleteSubTree.getLeft();

        // Находим самый левый элемент
        while (minLastNode.getLeft() != null) {
            minLastNodeParent = minLastNode; // -- нашли minLastNodeParent

            minLastNode = minLastNodeParent.getLeft(); // -- нашли minLastNode
        }

        minLastNodeParent.setLeft(minLastNode.getRight());

        if (nodeToDelete != root){
            minLastNode.setRight(null);
        }

        minLastNode.setLeft(nodeToDelete.getLeft());
        minLastNode.setRight(nodeToDelete.getRight()); // привязываем детей nodeToDelete к minLastNode

        if (nodeToDelete == root){
            root = minLastNode;
        } else if (isRightChild) {
            nodeToDeleteParent.setRight(minLastNode);
        } else {
            assert nodeToDeleteParent != null;
            nodeToDeleteParent.setLeft(minLastNode);
        }

        size--;

        return true;
    }

    public void printTree() {
        Deque<TreeNode<E>> globalStack = new LinkedList<>();

        globalStack.addLast(root);

        int gapsCount = 32; // Начальное значение расстояния между элементами
        boolean isRowEmpty = false;

        while (!isRowEmpty) {
            Deque<TreeNode<E>> localStack = new LinkedList<>();
            isRowEmpty = true;

            for (int i = 0; i < gapsCount; i++){
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) { // Пока в общем стеке есть элементы
                TreeNode<E> node = globalStack.pop(); // Берем следующий, при этом удаляя его из стека

                if (node != null) {
                    System.out.print(node.getData()); // Выводим его значение в консоли
                    localStack.addLast(node.getLeft()); // Сохраняем в локальный стек, наследники текущего элемента
                    localStack.addLast(node.getRight());

                    if (node.getLeft() != null || node.getRight() != null){
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
    }
}