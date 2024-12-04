package Hw8;

/******************************************************************
 *
 *   Kyle Mattey / SECTION 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
    int numVertices;                  // vertices in graph
    LinkedList<Integer>[] adjListArr; // Adjacency list
    List<Integer> vertexValues;       // vertex values

    // Constructor
    public Graph(int numV) {
        numVertices = numV;
        adjListArr = new LinkedList[numVertices];
        vertexValues = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjListArr[i] = new LinkedList<>();
            vertexValues.add(0);
        }
    }

    /* method setValue */
    public void setValue(int vertexIndex, int value) {
        if (vertexIndex >= 0 && vertexIndex < numVertices) {
            vertexValues.set(vertexIndex, value);
        } else {
            throw new IllegalArgumentException("Invalid vertex index: " + vertexIndex);
        }
    }

    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    /* method printGraph */
    public void printGraph() {
        System.out.println("\nAdjacency Matrix Representation:\n");
        int[][] matrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                matrix[i][dest] = 1;
            }
        }

        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("| ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    /** method findRoot */
    public int findRoot() {
        int[] incomingEdges = new int[numVertices];

        for (LinkedList<Integer> list : adjListArr) {
            for (int dest : list) {
                incomingEdges[dest]++;
            }
        }

        int root = -1;
        for (int i = 0; i < numVertices; i++) {
            if (incomingEdges[i] == 0) {
                if (root != -1) {
                    return -1; // More than one root found
                }
                root = i;
            }
        }

        return root == -1 ? -1 : vertexValues.get(root);
    }
}