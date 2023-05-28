package ru.academits.dashiev.my_tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class MyTree<E> {
    private MyTreeNode<E> root;
    private int size;

    private Comparator<E> comparator;

    public MyTree() { // конструктор без компаратора
    }

    public MyTree(Comparator<E> comparator){ // конструктор с компаратором
        this.comparator = comparator;
    }

    public MyTree(Comparator<E> comparator, E... elements) { // конструктор без компаратора
        this.comparator = comparator;

        for (E element : elements) {
           add(element);
        }
    }

    private int compare(E o1, E o2){
        // в задаче shape o1 - текущий узел, 02 - x, сравниваем текущий узел с x
        if (comparator != null){
            return comparator.compare(o1, o2);
        }

        if (o1 == null && o2 == null) {
            return 0;
        }

        if (o1 == null){
            return -1;
        }

        if (o2 == null){
            return 1;
        }

        @SuppressWarnings("unchecked")
        Comparable<E> object1 = (Comparable<E>) o1;

        return object1.compareTo(o2);
    }

    public void add(E data) {
        if (root == null) {
            root = new MyTreeNode<>(data);

            size++;

            return; // обязательно, чтобы код ниже не выполнялся
        }

        MyTreeNode<E> current = root;

        while (true) { // если значения будут повторяться то ветка перейдет вправо
            if (compare(current.getData(), data) > 0) { // из лекции если x<узла 18 стр., equals можно заменить на compare,
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

    public void bypassInWidth(Consumer<? super E> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Queue<MyTreeNode<E>> queue = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        queue.offer(root);

        while (!queue.isEmpty()) { // пока очередь не пус та
            MyTreeNode<E> data = queue.poll(); // достаем 1-ый элемент из очереди и удаляем его

            function.accept(data.getData());

            if (data.getLeft() != null) {
                queue.offer(data.getLeft()); //добавить эл-т
            }

            if (data.getRight() != null) {
                queue.offer(data.getRight());
            }
        }
    }

    public void bypassInDeep(Consumer<? super E> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        Deque<MyTreeNode<E>> stack = new LinkedList<>();

        // положить в очередь в очередь корень дерева, false если не получается вставить
        stack.addLast(root);

        while (!stack.isEmpty()) { // пока очередь не пуста
            MyTreeNode<E> data = stack.removeLast(); // достаем последний элемент из стэка и удаляем его

            function.accept(data.getData());

            if (data.getRight() != null) { // ложим детей в обратном порядке
                stack.addLast(data.getRight());
            }

            if (data.getLeft() != null) {
                stack.addLast(data.getLeft());
            }
        }
    }

    public void visitInDeepRecursively(Consumer<? super E> function) {
        if (size == 0) {
            return; // Когда дерево пустое
        }

        bypassInDeepRecursively(root, function);
    }

    private void bypassInDeepRecursively(MyTreeNode<E> data, Consumer<? super E> function) { // private, чтобы не вызвать извне
        function.accept(data.getData());

        // System.out.print(node.getData() + " ");

        if (data.getLeft() != null) {
            bypassInDeepRecursively(data.getLeft(), function);
        }

        if (data.getRight() != null) {
            bypassInDeepRecursively(data.getRight(), function);
        }
    }

    public boolean contains(E data) {
        // Если дерево пустое
        if(size == 0){
            return false;
        }

        MyTreeNode<E> current = root;

        int compareResult = 0;

        while (true) {
            compareResult = compare(current.getData(), data);

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0){
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return false;
                }
            }

            if (compareResult < 0){
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private MyTreeNode<E> findNodeToDeleteParent(E data) {
        MyTreeNode<E> current = root;

        while(true){
            if (current.getData() == null){
                if (current.getRight() != null && compare(current.getRight().getData(), data) == 0){
                    return current;
                }

                if (current.getRight() != null){
                    current = current.getRight();
                }
            } else {
                if(data == null){
                    if (current.getLeft() != null && current.getLeft().getData() == null){
                        return current;
                    }

                    if(current.getLeft() != null){
                        current = current.getLeft();

                        continue;
                    }

                    return null;
                }

                if(compare(current.getData(), data) > 0){
                    if(current.getLeft() != null && current.getLeft().getData() != null){
                        if(compare(current.getLeft().getData(), data) == 0){
                            return current;
                        }

                        current = current.getLeft();

                        continue;
                        // То этот элемент точно null
                    }

                    if (current.getLeft() != null){
                        current = current.getLeft();

                        continue;
                    }

                    // Нет такого элемента
                    return null;
                }

                if(compare(current.getData(), data) < 0){
                    if(current.getRight() != null){
                        if(compare(current.getRight().getData(), data) == 0){
                            return current;
                        }

                        current = current.getRight();
                    } else {
                        // Нет такого элемента
                        return null;
                    }
                }
            }
        }
    }

    public boolean delete(E data) {
        if(size == 0){
            return false;
        }

        MyTreeNode<E> nodeToDeleteParent = null;
        MyTreeNode<E> nodeToDelete = null;

        if (compare(root.getData(), data) == 0){
            nodeToDelete = root; // nodeToDeleteParent == null
        } else {
            nodeToDeleteParent = findNodeToDeleteParent(data);
        }

        //Узнаем есть ли вообще nodeToDelete

        boolean isRightChild = false;

        if(nodeToDeleteParent == null) {
            if (nodeToDelete != root){
                return false;
            }
        } else {
           if (data == null && nodeToDeleteParent.getLeft()!= null && nodeToDeleteParent.getLeft().getData() == null){
                nodeToDelete = nodeToDeleteParent.getLeft();
            } else if (nodeToDeleteParent.getRight() != null && compare(nodeToDeleteParent.getRight().getData(), data) == 0) {
                nodeToDelete = nodeToDeleteParent.getRight();
                isRightChild = true;
            } else if (nodeToDeleteParent.getLeft() != null && compare(nodeToDeleteParent.getLeft().getData(), data) == 0){
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
            } else if (isRightChild) {
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
                } else {
                    root = nodeToDelete.getLeft();
                }
            } else if (isRightChild) {
                // удаляемый элемент не null и он точно справа
                nodeToDeleteParent.setRight(nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            } else { // удаляемый элемент не null и он точно слева
                nodeToDeleteParent.setLeft(nodeToDelete.getRight() != null ? nodeToDelete.getRight() : nodeToDelete.getLeft());
            }

            size--;

            return true;
        }

        // Todo 3. Удаление узла - c двумя детьми
        MyTreeNode<E> minLastNodeParent;
        MyTreeNode<E> minLastNode;
        MyTreeNode<E> rootToDeleteSubTree = root.getRight();

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
            if(isRightChild){
                nodeToDeleteParent.setRight(rootToDeleteSubTree);
            } else {
                nodeToDeleteParent.setLeft(rootToDeleteSubTree);
            }

            rootToDeleteSubTree.setLeft(nodeToDelete.getLeft());

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

        if (isRightChild) {
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
        Deque<MyTreeNode<E>> globalStack = new LinkedList<>();

        globalStack.addLast(root);

        int gapsCount = 32; // Начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator); // Черта для указания начала нового дерева

        while (!isRowEmpty) {
            Deque<MyTreeNode<E>> localStack = new LinkedList<>();
            isRowEmpty = true;

            for (int i = 0; i < gapsCount; i++){
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) { // Пока в общем стеке есть элементы
                MyTreeNode<E> node = globalStack.pop(); // Берем следующий, при этом удаляя его из стека

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
        
        System.out.println(separator); // Подводим черту
    }
}