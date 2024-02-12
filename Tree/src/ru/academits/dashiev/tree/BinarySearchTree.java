package ru.academits.dashiev.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;

    private Comparator<E> comparator;

    public BinarySearchTree() { // Конструктор без компаратора
    }

    public BinarySearchTree(Comparator<E> comparator) { // Конструктор с компаратором
        this.comparator = comparator;
    }

    @SafeVarargs
    public BinarySearchTree(Comparator<E> comparator, E... elements) {
        this.comparator = comparator;

        for (E element : elements) {
            add(element);
        }
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        // Если для дерева используют конструктор без компаратора, то для сравнения нужно приводить
        // Данные к Comparable<T> и вызывать метод compareTo оттуда

        @SuppressWarnings("unchecked")
        Comparable<E> comparable = (Comparable<E>) data1;

        return comparable.compareTo(data2);
    }

    public void add(E data) {
        if (root == null) {
            root = new TreeNode<>(data);

            size++;

            return; // Обязательно, чтобы код ниже не выполнялся
        }

        TreeNode<E> currentNode = root;

        while (true) { // Если значения будут повторяться, то ветка перейдет вправо
            if (compare(currentNode.getData(), data) > 0) { // Из лекции если x<узла 18 стр., equals можно заменить на compare,
                // Если у Объекта Comparable, equals они равны или не равны
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data)); // Вставляем data как левого сына

                    size++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data)); // Вставляем data как правого сына

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

        // Положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // Пока очередь не пус та
            TreeNode<E> node = queue.poll(); // Достаем первый элемент из очереди и удаляем его

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.offer(node.getLeft()); // Добавить эл-т
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }

    public void bypassInDepth(Consumer<? super E> consumer) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();

        // Положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // Пока очередь не пуста
            TreeNode<E> node = stack.removeLast(); // Достаем последний элемент из stack и удаляем его

            consumer.accept(node.getData());

            if (node.getRight() != null) { // Добавляем детей в обратном порядке
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }
    }

    public void bypassInDeepRecursively(Consumer<? super E> consumer) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        bypassInDeepRecursively(root, consumer);
    }

    // Это перегрузка функции bypassInDeepRecursively?
    private void bypassInDeepRecursively(TreeNode<E> node, Consumer<? super E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            bypassInDeepRecursively(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            bypassInDeepRecursively(node.getRight(), consumer);
        }
    }

    public boolean contains(E data) {
        // Если дерево пустое
        if (size == 0) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
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
        TreeNode<E> parentNode = null;

        while (true) {
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult == 0){
                return  parentNode;
            }

            parentNode = currentNode;

            if (comparisonResult > 0) {
                if (currentNode.getLeft() == null){
                    return null;
                }

                currentNode = currentNode.getLeft();

                // То этот элемент точно null
                continue;
            }

            if (currentNode.getRight() != null) {
                if (currentNode.getRight() == null){
                    return null;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean delete(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> nodeToDeleteParent = null;
        TreeNode<E> nodeToDelete = null;

        if (compare(root.getData(), data) == 0) {
            nodeToDelete = root; // nodeToDeleteParent == null
        } else {
            nodeToDeleteParent = findNodeParent(data);
        }

        // Узнаем есть ли вообще nodeToDelete
        boolean isRightChild = false; // Про тот узел, который надо удалить

        if (nodeToDeleteParent == null) {
            if (nodeToDelete != root) {
                return false;
            }
        } else {
            if (nodeToDeleteParent.getLeft() != null && compare(nodeToDeleteParent.getLeft().getData(), data) == 0) {
                nodeToDelete = nodeToDeleteParent.getLeft();
            } else if (nodeToDeleteParent.getRight() != null && compare(nodeToDeleteParent.getRight().getData(), data) == 0) {
                nodeToDelete = nodeToDeleteParent.getRight();
                isRightChild = true;
            } else {
                // Так как точно нет элемента для удаления
                return false;
            }
        }

        /* 1. Удаляемый узел - лист */
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDelete == root) {
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

        // 2. Удаление узла - c одним ребенком
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) {
            TreeNode<E> notNullChild;

            if (nodeToDelete.getLeft() != null) {
                notNullChild = nodeToDelete.getLeft();
            } else {
                notNullChild = nodeToDelete.getRight();
            }

            if (nodeToDelete == root) {
                root = notNullChild;
            } else if (isRightChild) {
                nodeToDeleteParent.setRight(notNullChild);
            } else {
                assert nodeToDeleteParent != null;
                nodeToDeleteParent.setLeft(notNullChild);
            }

            size--;

            return true;
        }

        // 3. Удаление узла - c двумя детьми
        TreeNode<E> rightSubTree = nodeToDelete.getRight();

        // В правом поддереве у узла нет левого ребенка
        if (rightSubTree.getLeft() == null) {
            if (nodeToDelete == root) {
                root = rightSubTree;
            } else if (isRightChild) {
                nodeToDeleteParent.setRight(rightSubTree);
            } else {
                /* assert IDEA подсказала так сделать, чтобы не было "Method invocation 'setLeft' may produce
                'NullPointerException'". Это проверка некоторого условия, которое, по идее, никогда не должно нарушаться.
                Если условие все же окажется ложным, то будет брошено исключение */
                assert nodeToDeleteParent != null;
                nodeToDeleteParent.setLeft(rightSubTree);
            }

            rightSubTree.setLeft(nodeToDelete.getLeft());

            size--;

            return true;
        }

        TreeNode<E> minLastNodeParent = rightSubTree;
        TreeNode<E> minLastNode = rightSubTree.getLeft();

        // Находим самый левый элемент
        while (minLastNode.getLeft() != null) {
            minLastNodeParent = minLastNode; // -- Нашли minLastNodeParent
            minLastNode = minLastNodeParent.getLeft(); // -- Нашли minLastNode
        }

        minLastNodeParent.setLeft(minLastNode.getRight());

        minLastNode.setLeft(nodeToDelete.getLeft());
        minLastNode.setRight(nodeToDelete.getRight()); // Привязываем детей nodeToDelete к minLastNode

        if (nodeToDelete == root) {
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

            for (int i = 0; i < gapsCount; i++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) { // Пока в общем стеке есть элементы
                TreeNode<E> node = globalStack.pop(); // Берем следующий, при этом удаляя его из стека

                if (node != null) {
                    System.out.print(node.getData()); // Выводим его значение в консоли
                    localStack.addLast(node.getLeft()); // Сохраняем в локальный стек, наследники текущего элемента
                    localStack.addLast(node.getRight());

                    if (node.getLeft() != null || node.getRight() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("__");// Если элемент пустой
                    localStack.addLast(null);
                    localStack.addLast(null);
                }

                for (int i = 0; i < gapsCount * 2 - 2; i++) {
                    System.out.print(' ');
                }
            }

            System.out.println();

            gapsCount /= 2; // При переходе на следующий уровень расстояние между элементами каждый раз уменьшается

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.removeLast()); // Перемещаем все элементы из локального стека в глобальный
            }
        }
    }
}