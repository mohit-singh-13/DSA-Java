package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MSTWithPrimsAlgorithm {

  private record Pair(int node, int distance) {
  }

  private static List<List<Pair>> buildAdjacencyList(int n, ArrayList<ArrayList<Integer>> edges) {
    List<List<Pair>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (ArrayList<Integer> edge : edges) {
      int u = edge.get(0) - 1;
      int v = edge.get(1) - 1;
      int w = edge.get(2);

      adjacencyList.get(u).add(new Pair(v, w));
      adjacencyList.get(v).add(new Pair(u, w));
    }

    return adjacencyList;
  }

  public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int n, int m, ArrayList<ArrayList<Integer>> g) {
    // Write your code here.
    List<List<Pair>> adjacencyList = buildAdjacencyList(n, g);

    int[] parent = new int[n];
    Arrays.fill(parent, -1);

    boolean[] mst = new boolean[n];

    int[] weight = new int[n];
    Arrays.fill(weight, Integer.MAX_VALUE);
    weight[0] = 0;

    PriorityQueue<Pair> queue = new PriorityQueue<>((e1, e2) -> {
      if (e1.distance != e2.distance) {
        return Integer.compare(e1.distance, e2.distance);
      }
      return Integer.compare(e1.node, e2.node);
    });

    queue.add(new Pair(0, 0));
    while (!queue.isEmpty()) {
      Pair frontPair = queue.poll();

      mst[frontPair.node] = true;

      for (Pair neighbour : adjacencyList.get(frontPair.node)) {
        if (neighbour.distance < weight[neighbour.node] && !mst[neighbour.node]) {
          weight[neighbour.node] = neighbour.distance;
          parent[neighbour.node] = frontPair.node;
          queue.add(new Pair(neighbour.node, neighbour.distance));
        }
      }
    }

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    for (int i = 1; i < n; i++) {
      int p = parent[i] + 1;
      int w = weight[i];

      ArrayList<Integer> node = new ArrayList<>();
      node.add(p);
      node.add(i + 1);
      node.add(w);
      ans.add(node);
    }

    return ans;
  }
}
