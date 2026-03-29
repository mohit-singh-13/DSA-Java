package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathDirectedGraph {

  private record Pair(int node, int distance) {
  }

  public int[] shortestPath(int V, int E, int[][] edges) {
    List<List<Pair>> adjacencyList = buildAdjacencyList(V, edges);

    Stack<Integer> topoStack = new Stack<>();
    boolean[] visited = new boolean[V];

    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        dfs(adjacencyList, visited, i, topoStack);
      }
    }

    int[] distance = initDistances(V);

    while (!topoStack.isEmpty()) {
      int node = topoStack.pop();
      relax(adjacencyList, distance, node);
    }

    replaceUnreachable(distance);

    return distance;
  }

  private List<List<Pair>> buildAdjacencyList(int V, int[][] edges) {
    List<List<Pair>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      adjacencyList.get(edge[0]).add(new Pair(edge[1], edge[2]));
    }
    return adjacencyList;
  }

  private void dfs(List<List<Pair>> adjacencyList, boolean[] visited, int node, Stack<Integer> stack) {
    visited[node] = true;
    for (Pair neighbour : adjacencyList.get(node)) {
      if (!visited[neighbour.node]) {
        dfs(adjacencyList, visited, neighbour.node, stack);
      }
    }
    stack.push(node);
  }

  private int[] initDistances(int V) {
    int[] distance = new int[V];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[0] = 0;
    return distance;
  }

  private void relax(List<List<Pair>> adjacencyList, int[] distance, int node) {
    if (distance[node] == Integer.MAX_VALUE)
      return;
    for (Pair neighbour : adjacencyList.get(node)) {
      int newDist = distance[node] + neighbour.distance;
      if (newDist < distance[neighbour.node]) {
        distance[neighbour.node] = newDist;
      }
    }
  }

  private void replaceUnreachable(int[] distance) {
    for (int i = 0; i < distance.length; i++) {
      if (distance[i] == Integer.MAX_VALUE)
        distance[i] = -1;
    }
  }
}
