package ru.academits.dashiev.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private final int[][] matrix;

    public Graph(int vertexCount) {
        matrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private void checkValue(int value) {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Wrong value");
        }
    }

    public void set(int vertex1, int vertex2, int value) {
        checkValue(value);

        if (vertex1 == vertex2) {
            throw new IllegalArgumentException("vertex1 can't equals vertex2.");
        }

        if (vertex1 < 0 || vertex1 >= matrix.length) {
            throw new IllegalArgumentException("False vertex value = " + vertex1 + //
                                                       ". Vertex must be >= 0 and < " + matrix.length);
        }

        if (vertex2 < 0 || vertex2 >= matrix.length) {
            throw new IllegalArgumentException("False vertex value = " + vertex2 + //
                                                       ". Vertex must be >= 0 and < " + matrix.length);
        }

        matrix[vertex1][vertex2] = value;
        matrix[vertex2][vertex1] = value;

    }

    public void bypassInWidth(Consumer<? super Number> consumer) {
        boolean[] visited = new boolean[matrix.length];

        Queue<Integer> queue = new LinkedList<>();

        // положить в очередь вершину графа, обычно это 0, false если не получается вставить, добавить в конец
        queue.offer(0);

        while (!queue.isEmpty()) {
            // пока очередь не пуста, достаем первый элемент из очереди и удаляем его
            Integer currentVertexFromQueue = queue.poll();

            if (!visited[currentVertexFromQueue]) {
                consumer.accept(currentVertexFromQueue);
                visited[currentVertexFromQueue] = true;

                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[currentVertexFromQueue][i] == 1) {
                        // добавляем элемент в очередь
                        if (!visited[i]) { // если false
                            queue.offer(i);
                        }
                    }
                }
            }

            // если очередь пуста, надо посмотреть остались ли еще не посещенные вершины
            // и добавим в очередь, но только по одной, постепенно
            if (queue.isEmpty()) {
                for (int i = 0; i < matrix.length; i++) {
                    if (!visited[i]) { // если вершина не посещена
                        queue.offer(i);
                        break;
                    }
                }
            }
        }
    }

    public void bypassInDeep(Consumer<? super Number> consumer) {
        boolean[] visited = new boolean[matrix.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        while (!stack.isEmpty()) { // пока очередь не пуста
            Integer currentElementFromStack = stack.removeLast(); // достаем последний элемент из stack и удаляем его

            if (!visited[currentElementFromStack]) {
                consumer.accept(currentElementFromStack);
                visited[currentElementFromStack] = true;

                for (int i = matrix.length - 1; i > 0; i--) {
                    if (matrix[currentElementFromStack][i] == 1) {
                        // добавляем элемент в очередь
                        if (!visited[i]) { // если false
                            stack.addLast(i);
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                for (int i = 0; i < matrix.length; i++) {
                    if (!visited[i]) { // если вершина не посещена
                        stack.addLast(i);
                        break;
                    }
                }
            }
        }
    }

    public void bypassInDeepRecursively(Consumer<? super Number> consumer){
        boolean[] visited = new boolean[matrix.length];

        for(int i = 0; i < matrix.length; i ++){
            if (!visited[i]){
                visitInDeepRecursively(i, visited, consumer);
            }
        }
    }

    private void visitInDeepRecursively(Integer vertex, boolean[] visited, Consumer<? super Number> consumer) { // private, чтобы не вызвать извне
        if (!visited[vertex]){
            //System.out.print(vertex + " ");
            consumer.accept(vertex);
            visited[vertex] = true;

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[vertex][i] == 1) {
                    // добавляем элемент
                    visitInDeepRecursively(i, visited, consumer);
                }
            }
        }
    }
}