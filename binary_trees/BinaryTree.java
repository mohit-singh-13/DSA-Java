package binary_trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
  public int data;
  public Node left;
  public Node right;

  public Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

public class BinaryTree {
  public static Node buildTree(Node root, Scanner sc) {
    System.out.print("Enter the data : ");
    int data = sc.nextInt();

    if (data == -1) {
      return null;
    }

    root = new Node(data);

    System.out.println("Enter data for left of " + data);
    root.left = buildTree(root.left, sc);

    System.out.println("Enter data for right of " + data);
    root.right = buildTree(root.right, sc);

    return root;
  }

  // NOTE : Generated with Claude Code
  public static Node buildTreeWithLoop(Node root, Scanner sc) {
    System.out.print("Enter the data : ");
    int data = sc.nextInt();

    if (data == -1) {
      return null;
    }

    root = new Node(data);

    // Each task: [parentNode, isLeftChild, headerMessage]
    Deque<Object[]> stack = new LinkedList<>();
    stack.push(new Object[] { root, false, "Enter data for right of " + data });
    stack.push(new Object[] { root, true, "Enter data for left of " + data });

    while (!stack.isEmpty()) {
      Object[] task = stack.pop();
      Node parent = (Node) task[0];
      boolean isLeft = (boolean) task[1];
      String header = (String) task[2];

      System.out.println(header);
      System.out.print("Enter the data : ");
      int childData = sc.nextInt();

      if (childData != -1) {
        Node child = new Node(childData);
        if (isLeft) {
          parent.left = child;
        } else {
          parent.right = child;
        }
        stack.push(new Object[] { child, false, "Enter data for right of " + childData });
        stack.push(new Object[] { child, true, "Enter data for left of " + childData });
      }
    }

    return root;
  }

  public static void levelOrderTraversal(Node root) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    queue.add(null);

    System.out.println("Elements are : ");
    while (!queue.isEmpty()) {
      Node temp = queue.remove();

      if (temp == null) {
        System.out.println();

        if (!queue.isEmpty()) {
          queue.add(null);
        }
      } else {
        System.out.print(temp.data + " ");

        if (temp.left != null) {
          queue.add(temp.left);
        }

        if (temp.right != null) {
          queue.add(temp.right);
        }
      }
    }
  }

  public static void main(String[] args) {
    Node root = null;
    Scanner sc = new Scanner(System.in);
    root = buildTree(root, sc);
    sc.close();
    levelOrderTraversal(root);
  }
}
