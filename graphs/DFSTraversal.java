package graphs;

import java.util.ArrayList;
import java.util.List;

public class DFSTraversal {

  private static List<List<Integer>> makeAdjacencyList(int n, int e, ArrayList<ArrayList<Integer>> edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      int u = edges.get(i).get(0);
      int v = edges.get(i).get(1);

      adjacencyList.get(u).add(v);
      adjacencyList.get(v).add(u);
    }

    return adjacencyList;
  }

  private static void dfsTraversal(int node, int v, int e, boolean[] visited, List<List<Integer>> adjacencyList,
      ArrayList<ArrayList<Integer>> ans, int component) {
    visited[node] = true;
    ans.get(component).add(node);

    for (int i : adjacencyList.get(node)) {
      if (!visited[i]) {
        dfsTraversal(i, v, e, visited, adjacencyList, ans, component);
      }
    }
  }

  public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
    // Write your code here.
    boolean[] visited = new boolean[v];
    List<List<Integer>> adjacencyList = makeAdjacencyList(v, e, edges);
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    int component = 0;
    for (int i = 0; i < v; i++) {
      if (!visited[i]) {
        ans.add(new ArrayList<>());
        dfsTraversal(i, v, e, visited, adjacencyList, ans, component);
        component++;
      }
    }

    return ans;
  }
}