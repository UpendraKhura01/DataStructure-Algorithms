```markdown
# Predecessor and Successor in BST

This repository contains both **Iterative** and **Recursive** Java solutions to find the **Inorder Predecessor** and **Inorder Successor** of a given key in a Binary Search Tree (BST).

* **Predecessor:** The largest node value smaller than the given key.
* **Successor:** The smallest node value larger than the given key.

---

## Problem Statement

Given a Binary Search Tree (BST) and a `key`, find the nodes that would come immediately before and after the key in an inorder traversal. If a predecessor or successor does not exist (e.g., the key is the minimum or maximum value), the result should return `null`.

**Example Tree:**
```text
        50
       /  \
     30    70
    /  \   /  \
   20  40 60   80
```
**Input:** Key = 65  
**Output:** Predecessor = 60, Successor = 70

---

## Approach

### 1. Iterative Approach (Optimized)
This approach leverages the BST property ($left < root < right$) to find the boundaries without using extra space for recursion.
* **Predecessor:** If `curr.data < key`, this node is a potential predecessor. We store it and move `right` to find a larger value that is still less than the key.
* **Successor:** If `curr.data > key`, this node is a potential successor. We store it and move `left` to find a smaller value that is still greater than the key.

### 2. Recursive Approach
* We use two separate recursive functions: `PreS` for the predecessor and `PostS` for the successor.
* Since Java passes by value, we use a `Node[]` array (a single-element container) to maintain the reference to the found nodes across the recursive call stack.

---

## Java Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class solution {
    public static void main(String[] args) {
        Node root = new Node(50);

        // Left subtree
        root.left = new Node(30);
        root.left.left = new Node(20);
        root.left.right = new Node(40);

        // Right subtree
        root.right = new Node(70);
        root.right.left = new Node(60);
        root.right.right = new Node(80);

        // Finding Predecessor and Successor for key 65
        ArrayList<Node> list = findPreSuc(root, 65);
        
        String preVal = (list.get(0) != null) ? String.valueOf(list.get(0).data) : "None";
        String sucVal = (list.get(1) != null) ? String.valueOf(list.get(1).data) : "None";
        
        System.out.println("Predecessor: " + preVal);
        System.out.println("Successor: " + sucVal);
    }

    /**
     * Iterative Approach
     * Time Complexity: O(H)
     * Space Complexity: O(1)
     */
    static ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> result = new ArrayList<>();
        Node pre = null, suc = null;
        Node curr = root;

        // Find predecessor
        while (curr != null) {
            if (curr.data < key) {
                pre = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        curr = root;
        // Find successor
        while (curr != null) {
            if (curr.data > key) {
                suc = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        result.add(pre);
        result.add(suc);
        return result;
    }

    /**
     * Recursive Approach
     * Time Complexity: O(H)
     * Space Complexity: O(H) (due to recursion stack)
     */
    static ArrayList<Node> findPreSuc1(Node root, int key) {
        ArrayList<Node> list = new ArrayList<>();
        Node[] pre = {null};
        Node[] post = {null};

        PreS(root, key, pre);
        PostS(root, key, post);

        list.add(pre[0]);
        list.add(post[0]);
        return list;
    }

    static void PreS(Node node, int key, Node[] pre) {
        if (node == null) return;

        if (node.data >= key) {
            PreS(node.left, key, pre);
        } else {
            pre[0] = node;
            PreS(node.right, key, pre);
        }
    }

    static void PostS(Node node, int key, Node[] post) {
        if (node == null) return;

        if (node.data <= key) {
            PostS(node.right, key, post);
        } else {
            post[0] = node;
            PostS(node.left, key, post);
        }
    }
}
```

---

## Complexity Analysis

| Metric | Iterative | Recursive |
| :--- | :--- | :--- |
| **Time Complexity** | $O(H)$ | $O(H)$ |
| **Space Complexity** | $O(1)$ | $O(H)$ |

*Where **H** is the height of the tree. In the worst case (skewed tree), $H = N$. In a balanced tree, $H = \log N$.*

---

