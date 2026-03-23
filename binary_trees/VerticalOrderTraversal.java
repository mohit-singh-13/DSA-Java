package binary_trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class VerticalOrderTraversal {
  private void solve(TreeNode root, Map<Integer, Map<Integer, List<Integer>>> mpp, int distance, int level) {
    if (root == null)
      return;

    Map<Integer, List<Integer>> currentInnerMap = mpp.getOrDefault(distance, new TreeMap<>());
    List<Integer> currentList = currentInnerMap.getOrDefault(level, new ArrayList<>());

    currentList.add(root.val);
    currentInnerMap.put(level, currentList);
    mpp.put(distance, currentInnerMap);

    if (root.left != null) {
      solve(root.left, mpp, distance - 1, level + 1);
    }

    if (root.right != null) {
      solve(root.right, mpp, distance + 1, level + 1);
    }
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    Map<Integer, Map<Integer, List<Integer>>> mpp = new TreeMap<>();

    solve(root, mpp, 0, 0);

    List<List<Integer>> ans = new ArrayList<>();

    for (Map.Entry<Integer, Map<Integer, List<Integer>>> entry : mpp.entrySet()) {
      List<Integer> temp = new ArrayList<>();
      for (Map.Entry<Integer, List<Integer>> innerEntry : entry.getValue().entrySet()) {
        Collections.sort(innerEntry.getValue());
        temp.addAll(innerEntry.getValue());
      }

      ans.add(temp);
    }

    return ans;
  }
}