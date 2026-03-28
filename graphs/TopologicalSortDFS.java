package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDFS {
  private static List<List<Integer>> prepareAdjacencyList(int n, int e, ArrayList<ArrayList<Integer>> edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (ArrayList<Integer> edge : edges) {
      int u = edge.get(0);
      int v = edge.get(1);

      adjacencyList.get(u).add(v);
    }

    return adjacencyList;
  }

  private static void topoSort(int node, List<List<Integer>> adjacencyList, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;

    for (int i : adjacencyList.get(node)) {
      if (!visited[i]) {
        topoSort(i, adjacencyList, visited, stack);
      }
    }

    stack.push(node);
  }

  public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) {
    // Write your code here
    List<List<Integer>> adjacencyList = prepareAdjacencyList(v, e, edges);
    boolean[] visited = new boolean[v];

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < v; i++) {
      if (!visited[i]) {
        topoSort(i, adjacencyList, visited, stack);
      }
    }

    ArrayList<Integer> ans = new ArrayList<>();

    while (!stack.isEmpty()) {
      ans.add(stack.pop());
    }

    return ans;
  }
}
