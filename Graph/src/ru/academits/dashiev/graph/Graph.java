package ru.academits.dashiev.graph;

import java.util.*;
import java.util.function.Consumer;

public class Graph<E> {
    // Матрица вершин
    private final ArrayList<E> vertexesValues;
    private final int[][] adjacencyMatrix;


    public Graph(E ... vertexValues) {
        this.vertexesValues = new ArrayList<E>();

        this.vertexesValues.addAll(Arrays.asList(vertexValues));

        adjacencyMatrix = new int[vertexValues.length][vertexValues.length];

        for (int i = 0; i < vertexValues.length; i++) {
            for (int j = 0; j < vertexValues.length; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void checkValue(int value) {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Wrong value");
        }
    }

    public void set(E vertex1, E vertex2, int value) {
        checkValue(value);

        if (vertex1 == vertex2) {
            return;
        }

        int indexOfVertex1 = vertexesValues.indexOf(vertex1);
        int indexOfVertex2 = vertexesValues.indexOf(vertex2);

        if (indexOfVertex1 == -1) {
            throw new IllegalArgumentException("There is no vertex1 with the value = " + vertex1 +" in the graph");
        }

        if (indexOfVertex2 == -1) {
            throw new IllegalArgumentException("There is no vertex2 with the value = " + vertex2 +" in the graph");
        }

        adjacencyMatrix[indexOfVertex1][indexOfVertex2] = value;
        adjacencyMatrix[indexOfVertex2][indexOfVertex1] = value;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        Queue<E> queue = new LinkedList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]){
                // Положить в очередь вершину графа, обычно это 0
                queue.offer(vertexesValues.get(i));

                while (!queue.isEmpty()){
                    E currentVertexFromQueue = queue.poll();

                    int currentVertexFromQueueIndex = vertexesValues.indexOf(currentVertexFromQueue);

                    if (!visited[currentVertexFromQueueIndex]){
                        consumer.accept(currentVertexFromQueue);
                        visited[currentVertexFromQueueIndex] = true;

                        // Кладем детей распечатанной вершины в очередь
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

    public void bypassInDeep(Consumer<? super Number> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        while (!stack.isEmpty()) { // пока очередь не пуста
            Integer currentElementFromStack = stack.removeLast(); // достаем последний элемент из stack и удаляем его

            if (!visited[currentElementFromStack]) {
                consumer.accept(currentElementFromStack);
                visited[currentElementFromStack] = true;

                for (int i = adjacencyMatrix.length - 1; i > 0; i--) {
                    if (adjacencyMatrix[currentElementFromStack][i] == 1) {
                        // добавляем элемент в очередь
                        if (!visited[i]) { // если false
                            stack.addLast(i);
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                for (int i = 0; i < adjacencyMatrix.length; i++) {
                    if (!visited[i]) { // если вершина не посещена
                        stack.addLast(i);
                        break;
                    }
                }
            }
        }
    }

    public void bypassInDeepRecursively(Consumer<? super Number> consumer){
        boolean[] visited = new boolean[adjacencyMatrix.length];

        for(int i = 0; i < adjacencyMatrix.length; i ++){
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

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    // добавляем элемент
                    visitInDeepRecursively(i, visited, consumer);
                }
            }
        }
    }
}