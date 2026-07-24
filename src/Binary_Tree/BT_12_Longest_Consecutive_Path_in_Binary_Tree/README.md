# Longest Consecutive Path in Binary Tree

**Difficulty:** Medium

## 🧩 Problem Statement

Given the root of a **Binary Tree**, find the length of the **longest consecutive path** such that:

- The path moves **only from parent to child**.
- Every child has a value exactly **1 greater** than its parent.

If no such consecutive path exists, return **-1**.

---

## 📌 Examples

### Example 1

**Input**

```text
root = [1,2,3]
```

Tree:

```text
    1
   / \
  2   3
```

**Output**

```text
2
```

**Explanation**

The longest consecutive path is:

```text
1 → 2
```

Length = **2**

---

### Example 2

**Input**

```text
root = [10,20,30,40,N,60,90]
```

Tree:

```text
       10
      /  \
    20    30
   /     /  \
 40     60   90
```

**Output**

```text
-1
```

**Explanation**

No parent-child pair differs by exactly **1**, so no consecutive path exists.

---

## 🔍 Approach (DFS)

Use **postorder DFS** to compute the longest consecutive path starting from each node.

For every node:

1. Recursively compute the longest consecutive path from the left child.
2. Recursively compute the longest consecutive path from the right child.
3. Initially, the current node contributes a path of length **1**.
4. If the left child exists and:

```text
left.data == node.data + 1
```

extend the path using the left subtree.

5. Similarly, check the right child.
6. Update the global maximum answer.
7. Return the longest consecutive path starting from the current node.

---

## ▶ Dry Run

### Input

```text
        1
       / \
      2   5
     /
    3
```

### DFS

Visit node **3**

```
curr = 1
```

Return:

```
1
```

---

Visit node **2**

```
3 == 2 + 1 ✔

curr = 1 + left = 2
```

Return:

```
2
```

---

Visit node **5**

```
No child

curr = 1
```

---

Visit node **1**

Left child:

```
2 == 1 + 1 ✔

curr = 2 + 1 = 3
```

Right child:

```
5 != 2
```

Answer:

```
3
```

Path:

```text
1 → 2 → 3
```

---

## 🌳 Recursion Tree

```text
dfs(1)

├── dfs(2)
│      │
│      └── dfs(3)
│              return 1
│
│      return 2
│
└── dfs(5)
       return 1

return 3
```

---

## ⏱ Complexity Analysis

### Time Complexity

```text
O(n)
```

Each node is visited exactly once.

---

### Space Complexity

```text
O(h)
```

where **h** is the height of the tree (recursion stack).

- Worst case: **O(n)**
- Balanced tree: **O(log n)**

---

## ✅ Java Solution

```java
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

public class Solution {

    int ans = 1;

    public int longestConsecutive(Node root) {

        dfs(root);

        return ans == 1 ? -1 : ans;
    }

    private int dfs(Node node) {

        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int curr = 1;

        if (node.left != null && node.left.data == node.data + 1) {
            curr = Math.max(curr, left + 1);
        }

        if (node.right != null && node.right.data == node.data + 1) {
            curr = Math.max(curr, right + 1);
        }

        ans = Math.max(ans, curr);

        return curr;
    }
}
```

---

## 💡 Key Idea

- Every node computes the **longest consecutive path starting from itself**.
- A child contributes only if its value is exactly **parent + 1**.
- The answer is the maximum path length found during the DFS.
- If the maximum length remains **1**, it means no consecutive parent-child pair exists, so return **-1**.

This DFS solution efficiently solves the problem in **O(n)** time.