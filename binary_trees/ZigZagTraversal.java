package binary_trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

public class ZigZagTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    boolean leftToRight = true;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      Integer[] tempAns = new Integer[size];

      for (int i = 0; i < size; i++) {
        TreeNode frontNode = queue.remove();
        int data = frontNode.val;

        int index = leftToRight ? i : size - i + 1;
        tempAns[index] = data;

        if (frontNode.left != null) {
          queue.add(frontNode.left);
        }

        if (frontNode.right != null) {
          queue.add(frontNode.right);
        }
      }

      leftToRight = !leftToRight;

      ans.add(new ArrayList<>(Arrays.asList(tempAns)));
    }

    return ans;
  }
}