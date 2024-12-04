package Hw8;

/******************************************************************
 *
 *   Kyle Mattey / SECTION 002
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

class ProblemSolutions {

    /**
     * Method canFinish
     * <p>
     * Determines if all exams can be completed given their prerequisites.
     *
     * @param numExams      - number of exams, which will produce a graph of n nodes
     * @param prerequisites - 2-dim array of directed edges.
     * @return boolean      - True if all exams can be taken, else false.
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        // Build in-degree array and adjacency list
        int[] inDegree = new int[numExams];
        ArrayList<Integer>[] adjList = getAdjList(numExams, prerequisites);

        for (int[] edge : prerequisites) {
            inDegree[edge[0]]++;
        }

        // Add all nodes with 0 in-degree to a queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int visited = 0; // Track visited nodes
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            for (int neighbor : adjList[node]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        // If all nodes are visited, the graph is acyclic
        return visited == numExams;
    }

    /**
     * Method getAdjList
     * <p>
     * Builds an Adjacency List for the directed graph based on number of nodes
     * and passed in directed edges.
     *
     * @param numNodes - number of nodes in graph (labeled 0 through n-1) for n nodes
     * @param edges    - 2-dim array of directed edges
     * @return ArrayList<Integer>[] - An adjacency list representing the provided graph.
     */
    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[numNodes]; // Create an array of ArrayList ADT

        for (int node = 0; node < numNodes; node++) {
            adj[node] = new ArrayList<Integer>(); // Allocate empty ArrayList per node
        }
        for (int[] edge : edges) {
            adj[edge[1]].add(edge[0]); // Reverse edge direction for topological sort
        }
        return adj;
    }

    /**
     * Method numGroups
     * <p>
     * Determines the number of connected components in an undirected graph
     * represented as an adjacency matrix.
     *
     * @param adjMatrix - 2D adjacency matrix of the graph
     * @return int      - Number of connected components (groups)
     */
    public int numGroups(int[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        boolean[] visited = new boolean[numNodes];
        int groups = 0;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                dfs(adjMatrix, visited, i);
                groups++;
            }
        }

        return groups;
    }

    /**
     * Helper Method dfs
     * <p>
     * Performs a Depth-First Search (DFS) to visit all nodes in a connected component.
     *
     * @param adjMatrix - 2D adjacency matrix of the graph
     * @param visited   - Boolean array to track visited nodes
     * @param node      - Current node being visited
     */
    private void dfs(int[][] adjMatrix, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            if ((adjMatrix[node][neighbor] == 1 || adjMatrix[neighbor][node] == 1) && !visited[neighbor]) {
                dfs(adjMatrix, visited, neighbor);
            }
        }
    }
}