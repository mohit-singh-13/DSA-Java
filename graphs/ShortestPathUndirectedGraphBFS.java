package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathUndirectedGraphBFS {

  private static List<List<Integer>> prepareAdjacencyList(int n, int m, int[][] edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int u = edges[i][0];
      int v = edges[i][1];

      adjacencyList.get(u).add(v);
      adjacencyList.get(v).add(u);
    }

    return adjacencyList;
  }

  private static void fillParentArray(List<List<Integer>> adjacencyList, int n, int m, int[] parent,
      boolean[] visited, int source) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(source);
    parent[source] = -1;
    visited[source] = true;

    while (!queue.isEmpty()) {
      int front = queue.poll();

      for (int neighbour : adjacencyList.get(front)) {
        if (!visited[neighbour]) {
          queue.add(neighbour);
          parent[neighbour] = front;
          visited[neighbour] = true;
        }
      }
    }
  }

  public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
    // Write your code here.
    List<List<Integer>> adjacencyList = prepareAdjacencyList(n, m, edges);

    boolean[] visited = new boolean[n + 1];
    int[] parent = new int[n + 1];

    fillParentArray(adjacencyList, n, m, parent, visited, s);

    LinkedList<Integer> ans = new LinkedList<>();
    ans.add(t);
    int current = t;

    while (current != s) {
      current = parent[current];
      ans.add(current);
    }

    Collections.reverse(ans);
    return ans;
  }
}
