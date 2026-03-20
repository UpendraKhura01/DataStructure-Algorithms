# 🔥 Burning Tree Problem (Java)

## 📌 Problem

Given a binary tree and a target node, find the **minimum time required to burn the entire tree**.

### 🔥 Fire Rules:

* Fire starts from the **target node**
* Every second, it spreads to:

    * Left child
    * Right child
    * Parent

---

## 🌳 Example

```
                1
             /     \
           2         3
         /   \     /   \
        4     5   6     7
```

**Target = 2**

### ⏱️ Burning Process

| Time | Nodes Burning |
| ---- | ------------- |
| 0    | 2             |
| 1    | 4, 5, 1       |
| 2    | 3             |
| 3    | 6, 7          |

👉 **Output: 3**

---

## 🧠 Approach

### Step 1: Build Parent Mapping

We store:

```
child → parent
```

---

### Step 2: Find Target Node

Use recursion to locate the starting node.

---

### Step 3: BFS to Simulate Fire

* Start from target node
* Spread fire to all neighbors
* Count time level by level

---

## 💻 Java Implementation

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {

    public static int minTime(Node root, int target) {

        // Step 1: Build parent map
        Map<Node, Node> parent = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.left != null) {
                parent.put(cur.left, cur);
                q.add(cur.left);
            }

            if (cur.right != null) {
                parent.put(cur.right, cur);
                q.add(cur.right);
            }
        }

        // Step 2: Find target node
        Node start = find(root, target);

        // Step 3: Burn tree using BFS
        return burnTree(start, parent);
    }

    static int burnTree(Node node, Map<Node, Node> parent) {

        Queue<Node> q = new LinkedList<>();
        Set<Node> vis = new HashSet<>();

        q.add(node);
        vis.add(node);

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();

                // parent
                if (parent.get(cur) != null && !vis.contains(parent.get(cur))) {
                    q.add(parent.get(cur));
                    vis.add(parent.get(cur));
                    burned = true;
                }

                // left
                if (cur.left != null && !vis.contains(cur.left)) {
                    q.add(cur.left);
                    vis.add(cur.left);
                    burned = true;
                }

                // right
                if (cur.right != null && !vis.contains(cur.right)) {
                    q.add(cur.right);
                    vis.add(cur.right);
                    burned = true;
                }
            }

            if (burned) time++;
        }

        return time;
    }

    static Node find(Node node, int target) {
        if (node == null || node.data == target) return node;

        Node left = find(node.left, target);
        if (left != null) return left;

        return find(node.right, target);
    }
}
```

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n)       |
| Space | O(n)       |

---

## 🧠 Key Concepts

* BFS (Level Order Traversal)
* Graph Conversion (Tree → Undirected Graph)
* Visited Set to avoid cycles

---

## 🎯 Key Insight

> Time = Number of BFS levels required for fire to spread

---

## 🚀 Summary

```
Tree → Convert to Graph → BFS → Count Time
```

---


