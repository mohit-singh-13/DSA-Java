package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class DijkstrasAlgorithm {

  public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> vec, int vertices, int edges, int source) {
    // Write your code here.
    List<List<Pair>> adjacencyList = buildAdjacencyList(vec, vertices);

    int[] distance = initDistance(vertices, source);

    TreeSet<Pair> set = new TreeSet<>((e1, e2) -> {
      if (e1.distance != e2.distance) {
        return Integer.compare(e1.distance, e2.distance);
      }
      return Integer.compare(e1.node, e2.node);
    });
    set.add(new Pair(source, 0));

    while (!set.isEmpty()) {
      Pair elementPair = set.pollFirst();
      relax(elementPair, adjacencyList, distance, set);
    }

    return new ArrayList<>(Arrays.stream(distance).boxed().toList());
  }

  private static class Pair {
    int node;
    int distance;

    Pair(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  private static List<List<Pair>> buildAdjacencyList(ArrayList<ArrayList<Integer>> inputVec, int V) {
    List<List<Pair>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (ArrayList<Integer> edge : inputVec) {
      adjacencyList.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
      adjacencyList.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
    }

    return adjacencyList;
  }

  private static int[] initDistance(int V, int source) {
    int[] distance = new int[V];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[source] = 0;

    return distance;
  }

  private static void relax(Pair elementPair, List<List<Pair>> adjacencyList, int[] distance, TreeSet<Pair> set) {
    for (Pair neighbour : adjacencyList.get(elementPair.node)) {
      int totalDistance = distance[elementPair.node] + neighbour.distance;
      if (totalDistance < distance[neighbour.node]) {
        set.remove(new Pair(neighbour.node, distance[neighbour.node]));
        distance[neighbour.node] = totalDistance;
        set.add(new Pair(neighbour.node, totalDistance));
      }
    }
  }
}
