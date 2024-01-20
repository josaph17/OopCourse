package ru.academits.dashiev.graph;

import java.util.*;
import java.util.function.Consumer;

public class Graph<E> {
    // Матрица вершин
    private final ArrayList<E> vertexesValues;
    private final int[][] adjacencyMatrix;


    @SafeVarargs
    public Graph(E ... vertexValues) {
        this.vertexesValues = new ArrayList<>();

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

    public void bypassInDeep(Consumer<E> consumer) {
        boolean[] visited = new boolean[adjacencyMatrix.length];

        Deque<E> stack = new LinkedList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++){
            if (!visited[i]){
                stack.addLast(vertexesValues.get(i));

                while (!stack.isEmpty()){ // пока стек не пуст
                    E currentElementFromStack = stack.removeLast();

                    int currentVertexFromStackIndex = vertexesValues.indexOf(currentElementFromStack);

                    if (!visited[currentVertexFromStackIndex]){
                        consumer.accept(currentElementFromStack);
                        visited[currentVertexFromStackIndex] = true;

                        for (int j = adjacencyMatrix.length - 1; j >= 0; j--) {
                            if (adjacencyMatrix[currentVertexFromStackIndex][j] == 1 && !visited[j]) {
                                // добавляем элемент в стек
                                stack.addLast(vertexesValues.get(j));
                            }
                        }
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