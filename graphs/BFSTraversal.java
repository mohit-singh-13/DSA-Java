package graphs;

import java.util.*;

public class BFSTraversal {
  private static final Map<Integer, List<Integer>> adjList = new HashMap<>();
  private static final Map<Integer, Boolean> visited = new HashMap<>();

  private static void prepareAdjList(int n, List<List<Integer>> adj) {
    for (int i = 0; i < adj.size(); i++) {
      for (int j = 0; j < adj.get(i).size(); j++) {
        adjList.computeIfAbsent(i, k -> new ArrayList<>()).add(adj.get(i).get(j));
      }
    }
  }

  private static void bfs(int node, List<Integer> ans) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(node);

    visited.put(node, true);

    while (!queue.isEmpty()) {
      Integer frontNode = queue.remove();

      ans.add(frontNode);

      List<Integer> neighbours = adjList.get(frontNode);
      if (neighbours != null) {
        for (Integer value : neighbours) {
          if (visited.get(value) == null) {
            queue.add(value);
            visited.put(value, true);
          }
        }
      }
    }
  }

  public static List<Integer> bfsTraversal(int n, List<List<Integer>> adj) {
    // Write your code here
    prepareAdjList(n, adj);

    List<Integer> ans = new ArrayList<>();
    bfs(0, ans);

    return ans;
  }
}
