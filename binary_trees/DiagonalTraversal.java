package binary_trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Node {
  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class DiagonalTraversal {
  private final Map<Integer, List<Integer>> mpp = new TreeMap<>();

  private void collectNodes(Node root, Integer diagonal) {
    if (root == null)
      return;

    mpp.computeIfAbsent(diagonal, k -> new ArrayList<>()).add(root.data);
    collectNodes(root.left, diagonal + 1);
    collectNodes(root.right, diagonal);
  }

  private ArrayList<Integer> buildResult() {
    ArrayList<Integer> ans = new ArrayList<>();

    for (List<Integer> nodes : mpp.values()) {
      ans.addAll(nodes);
    }

    return ans;
  }

  public ArrayList<Integer> diagonal(Node root) {
    // add your code here.
    collectNodes(root, 0);
    return buildResult();
  }
}