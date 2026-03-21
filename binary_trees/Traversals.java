package binary_trees;

import java.util.Scanner;

class TraversalTechniques {
  public static void inOrderTraversal(Node root) {
    if (root == null) {
      return;
    }

    inOrderTraversal(root.left);
    System.out.print(root.data + " ");
    inOrderTraversal(root.right);
  }

  public static void preOrderTraversal(Node root) {
    if (root == null) {
      return;
    }

    System.out.print(root.data + " ");
    preOrderTraversal(root.left);
    preOrderTraversal(root.right);
  }

  public static void postOrderTraversal(Node root) {
    if (root == null) {
      return;
    }

    postOrderTraversal(root.left);
    postOrderTraversal(root.right);
    System.out.print(root.data + " ");
  }
}

public class Traversals {
  public static void main(String[] args) {
    Node root = null;
    Scanner sc = new Scanner(System.in);
    root = BinaryTree.buildTree(root, sc);
    sc.close();

    System.out.println("Inorder Traversal : ");
    TraversalTechniques.inOrderTraversal(root);
    System.out.println();

    System.out.println("Preorder Traversal : ");
    TraversalTechniques.preOrderTraversal(root);
    System.out.println();

    System.out.println("Postorder Traversal : ");
    TraversalTechniques.postOrderTraversal(root);
    System.out.println();
  }
}
