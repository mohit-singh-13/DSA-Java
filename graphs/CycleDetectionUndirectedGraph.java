package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionUndirectedGraph {
  private static class Pair {
    int node;
    int parent;

    Pair(int node, int parent) {
      this.node = node;
      this.parent = parent;
    }
  }

  private static List<List<Integer>> makeAdjacencyList(int n, int m, int[][] edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int u = edges[i][0] - 1;
      int v = edges[i][1] - 1;

      adjacencyList.get(u).add(v);
      adjacencyList.get(v).add(u);
    }

    return adjacencyList;
  }

  private static boolean isCyclePresentBFS(int node, boolean[] visited, List<List<Integer>> adjacencyList) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(node, -1));
    visited[node] = true;

    while (!queue.isEmpty()) {
      Pair front = queue.poll();

      for (int i : adjacencyList.get(front.node)) {
        if (visited[i] && i != front.parent) {
          return true;
        } else if (!visited[i]) {
          queue.add(new Pair(i, front.node));
          visited[i] = true;
        }
      }
    }

    return false;
  }

  public static String cycleDetection(int[][] edges, int n, int m) {
    // Write your code here.
    List<List<Integer>> adjacencyList = makeAdjacencyList(n, m, edges);
    boolean[] visited = new boolean[n];
    String ans = "No";

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        if (isCyclePresentBFS(i, visited, adjacencyList)) {
          ans = "Yes";
          break;
        }
      }
    }

    return ans;
  }
}
