package graphs;

import java.util.ArrayList;
import java.util.List;

public class PrintAdjacencyList {
  public static int[][] printAdjacency(int n, int m, int[][] edges) {
    // Write your code here.
    List<List<Integer>> tempMatrix = new ArrayList<>(n);
    for (int i = 0; i < n; i++)
      tempMatrix.add(new ArrayList<>());

    for (int i = 0; i < m; i++) {
      int u = edges[i][0];
      int v = edges[i][1];

      tempMatrix.get(u).add(v);
      tempMatrix.get(v).add(u);
    }

    List<List<Integer>> adjList = new ArrayList<>(n);
    for (int i = 0; i < n; i++)
      adjList.add(new ArrayList<>());

    for (int i = 0; i < n; i++) {
      adjList.get(i).add(i);

      for (int j = 0; j < tempMatrix.get(i).size(); j++) {
        adjList.get(i).add(tempMatrix.get(i).get(j));
      }
    }

    return adjList.stream().map(list -> list.stream().mapToInt(Integer::intValue).toArray())
        .toArray(int[][]::new);
  }
}
