# 🍬 Distribute Candies in Binary Tree (Java)

## 📌 Problem Statement

You are given the root of a binary tree with `n` nodes, where each node contains a certain number of candies, and the **total number of candies across all nodes is exactly `n`**.

In one move:

* You can transfer **one candy** between two **adjacent nodes** (parent ↔ child).
* The transfer can happen in **either direction**.

👉 Your task is to determine the **minimum number of moves** required so that:

> ✅ Every node has exactly **one candy**

---

## 🌳 What the Problem Means (Example)

### Input (Level Order)

```
[3, 0, 0]
```

### Tree Representation

```
    3
   / \
  0   0
```

---

## 🎯 Goal

We want to transform the tree into:

```
    1
   / \
  1   1
```

---

## 🔄 How Moves Work

* Move 1 candy from root → left child
* Move 1 candy from root → right child

✅ Total moves = **2**

---

## 🧠 Key Insight

Instead of asking:

> ❌ "How many candies does this node need?"

We think:

> ✅ "How many **extra or missing candies** does this node have?"

---

## 🔍 Core Logic

Each node returns:

```java
net = node.data + left + right - 1;
```

### Meaning:

* `net > 0` → extra candies to give
* `net < 0` → needs candies
* `net = 0` → perfectly balanced

---

## 🔁 Counting Moves

```java
count += Math.abs(left) + Math.abs(right);
```

👉 Why absolute?

Because:

* Sending OR receiving candy both count as **1 move**

---

## 🌳 Your Example

Input:

```
[5, 0, 0, N, N, 0, 0]
```

Tree:

```
        5
       / \
      0   0
         / \
        0   0
```

* Root has **extra candies**
* Other nodes need candies
* Candies flow down the tree

👉 Each transfer is counted → gives total moves

---

## 💻 Code

```java

static int distCandy(Node root) {
        int[] count = {0};
        helper(root, count);
        return count[0];
    }

    static int helper(Node node, int[] count) {
        if (node == null) return 0;

        int l = helper(node.left, count);
        int r = helper(node.right, count);

        count[0] += Math.abs(l) + Math.abs(r);

        return node.data + l + r - 1;
    }
```

---

## 📊 Complexity

* **Time Complexity:** `O(N)`
* **Space Complexity:** `O(H)` (recursion stack)

---

## 🎯 Final Takeaway

* We don’t return **needed candies**
* We return **net balance (excess/deficit)**
* Moves = **total flow of candies**
* Use **postorder traversal** to solve

---

## 🚀 One-Line Intuition

> Treat every node as a **bank balance**, not a requirement.

---

