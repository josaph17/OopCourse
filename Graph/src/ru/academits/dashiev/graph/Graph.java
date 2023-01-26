package ru.academits.dashiev.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    int[][] matrix;
    int vertexCount;

    public Graph(int vertexCount1) {
        vertexCount = vertexCount1; // vertex - вершина

        matrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private void checkValue(int value) {
        if (value <= 0 || value > 2) {
            throw new IllegalArgumentException("Wrong value");
        }
    }

    public void set(int vertex1, int vertex2, int value) {
        checkValue(value);

        if (vertex1 == vertex2) {
            throw new IllegalArgumentException("vertex1 can't equals vertex2.");
        }

        if (vertex1 <= 0 || vertex1 > vertexCount) {
            throw new IllegalArgumentException("False vertex value = " + vertex1 + //
                                                       ". Vertex must be >= 1 and <= " + vertexCount);
        }

        if (vertex2 <= 0 || vertex2 > vertexCount) {
            throw new IllegalArgumentException("False vertex value = " + vertex2 + //
                                                       ". Vertex must be >= 1 and <= " + vertexCount);
        }

        vertex1 = vertex1 - 1;
        vertex2 = vertex2 - 1;

        if (vertex1 == vertex2) {
            throw new IllegalArgumentException("Row can't equals vertex2.");
        }

        if (matrix[vertex1][vertex2] != 1) {
            matrix[vertex1][vertex2] = value;

            if (matrix[vertex2][vertex1] != 1) {
                matrix[vertex2][vertex1] = value;
            }
        }
    }

    public void widthBypass() {
        int n = vertexCount; // число вершин

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        Queue<Integer> queue = new LinkedList<>();

        // положить в очередь вершину графа, обычно это 1, false если не получается вставить, добавл в конец
        queue.offer(1);

        while (!queue.isEmpty()) { // пока очередь не пуста
            // достаем 1-ый элемент из очереди и удаляем его
            Integer recently = queue.poll();

            if (!visited[recently - 1]){
                visited[recently - 1] = true;

                System.out.print((recently) + " ");

                for (int i = 0; i < n; i++) {
                    if (matrix[recently - 1][i] == 1) {
                        // добавляем элемент в очередь
                        if(!visited[i]){ // если false
                            queue.offer(i+1);

                            visited[recently - 1] = true;
                        }
                    }
                }
            }

            // если очередь пуста, посмотрим остались ли еще непосещенные вершины
            // и добавим в очередь, но тоько по одной, постепенно
            if (queue.isEmpty()){
                for(int i = 0; i < n; i ++){
                    if(!visited[i]){ // если вершина не посещена
                        queue.offer(i+1);
                        break;
                    }
                }
            }
        }
    }
}