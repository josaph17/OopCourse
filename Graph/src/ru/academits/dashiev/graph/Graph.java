package ru.academits.dashiev.graph;

import java.util.*;
import java.util.function.Consumer;

public class Graph<E> {
    // Вершины
    private final ArrayList<E> vertexesValues;
    private final int[][] adjacencyMatrix;

    @SafeVarargs
    public Graph(E... vertexValues) {
        vertexesValues = new ArrayList<>();

        vertexesValues.addAll(Arrays.asList(vertexValues));

        adjacencyMatrix = new int[vertexValues.length][vertexValues.length];
    }

    private void checkValue(int value) {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Wrong value. Value must be 0 or 1");
        }
    }

    public void set(E vertex1, E vertex2, int value) {
        checkValue(value);

        if (vertex1 == vertex2) {
            return;
        }

        int vertex1Index = vertexesValues.indexOf(vertex1);

        if (vertex1Index == -1) {
            throw new IllegalArgumentException("There is no vertex1 with the value = " + vertex1 + " in the graph");
        }

        int vertex2Index = vertexesValues.indexOf(vertex2);

        if (vertex2Index == -1) {
            throw new IllegalArgumentException("There is no vertex2 with the value = " + vertex2 + " in the graph");
        }

        adjacencyMatrix[vertex1Index][vertex2Index] = value;
        adjacencyMatrix[vertex2Index][vertex1Index] = value;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];

        Queue<E> queue = new LinkedList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                // Положить в очередь вершину графа, обычно это 0
                queue.offer(vertexesValues.get(i));

                while (!queue.isEmpty()) {
                    E currentVertexFromQueue = queue.poll();

                    int currentVertexFromQueueIndex = vertexesValues.indexOf(currentVertexFromQueue);

                    if (!visited[currentVertexFromQueueIndex]) {
                        consumer.accept(currentVertexFromQueue);
                        visited[currentVertexFromQueueIndex] = true;

                        // Кладем детей вершины в очередь
                        for (int j = 0; j < adjacencyMatrix.length; j++) {
                            // Берем те, где есть ребро и где мы еще не были
                            if (adjacencyMatrix[currentVertexFromQueueIndex][j] == 1 && !visited[j]) {
                                // Добавляем элемент в очередь
                                queue.offer(vertexesValues.get(j));
                            }
                        }
                    }
                }
            }
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];

        Deque<E> stack = new LinkedList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                stack.addLast(vertexesValues.get(i));

                while (!stack.isEmpty()) { // Пока стек не пуст
                    E currentElementFromStack = stack.removeLast();

                    int currentVertexFromStackIndex = vertexesValues.indexOf(currentElementFromStack);

                    if (!visited[currentVertexFromStackIndex]) {
                        consumer.accept(currentElementFromStack);
                        visited[currentVertexFromStackIndex] = true;

                        for (int j = adjacencyMatrix.length - 1; j >= 0; j--) {
                            if (adjacencyMatrix[currentVertexFromStackIndex][j] == 1 && !visited[j]) {
                                // Добавляем элемент в стек
                                stack.addLast(vertexesValues.get(j));
                            }
                        }
                    }
                }
            }
        }
    }


    public void bypassInDeepRecursive(Consumer<E> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                bypassInDeepRecursively(i, visited, consumer);
            }
        }
    }

    private void bypassInDeepRecursively(int vertexIndex, boolean[] visited, Consumer<E> consumer) { // private, чтобы не вызвать извне
        if (!visited[vertexIndex]) {
            E vertexValue = vertexesValues.get(vertexIndex);

            consumer.accept(vertexValue);
            visited[vertexIndex] = true;

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertexIndex][i] == 1) {
                    // Добавляем элемент
                    bypassInDeepRecursively(i, visited, consumer);
                }
            }
        }
    }
}