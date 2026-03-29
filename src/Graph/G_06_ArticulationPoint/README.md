# 🔴 Articulation Points in Graph (Tarjan’s Algorithm)

## 📌 Problem Statement

Given an **undirected graph** with `V` vertices and a list of edges, find all **Articulation Points (Cut Vertices)**.

### 👉 What is an Articulation Point?

A vertex is called an **Articulation Point** if:
> Removing that vertex (and its edges) increases the number of connected components in the graph.

---

## 💡 Intuition

- We use **DFS (Depth First Search)** to explore the graph.
- The idea is to track:
    - **Discovery Time (f_time)** → when a node is first visited
    - **Lowest Time (l_time)** → earliest reachable node from that subtree

👉 If a node cannot reach any ancestor through its children → it is an **Articulation Point**

---

## 🧠 Thought Process

### 1. Why DFS?

- DFS helps us explore **deep paths**
- Allows us to track:
    - parent-child relationships
    - back edges

---

### 2. Why `f_time` and `l_time`?

| Array | Meaning |
|------|--------|
| `f_time[node]` | Time when node is first discovered |
| `l_time[node]` | Lowest reachable discovery time |

---

### 3. Key Idea

For a node `u` and its child `v`:

```
If f_time[u] <= l_time[v]
→ No back edge from subtree of v to ancestors of u
→ Removing u disconnects v
→ u is an articulation point
```

---

## ⚙️ Algorithm Steps

1. Build adjacency list
2. Initialize:
    - visited array
    - discovery time (`f_time`)
    - low time (`l_time`)
    - articulation array (`isArt`)
3. Run DFS for all components
4. During DFS:
    - Track children count
    - Update low times
    - Check articulation conditions
5. Collect all articulation points

---

## 🚀 Code (Java)

```java
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj_l = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj_l.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj_l.get(u).add(v);
            adj_l.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        boolean[] isArt = new boolean[V];

        int[] l_time = new int[V];
        int[] f_time = new int[V];

        int[] time = {0};

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                helper(i, -1, vis, isArt, l_time, f_time, adj_l, time);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArt[i]) ans.add(i);
        }

        if (ans.isEmpty()) ans.add(-1);
        return ans;
    }

    static void helper(int node, int parent, boolean[] vis, boolean[] isArt,
                       int[] l_time, int[] f_time,
                       ArrayList<ArrayList<Integer>> adjL, int[] time) {

        time[0]++;
        l_time[node] = f_time[node] = time[0];

        vis[node] = true;

        int child = 0;

        for (int i : adjL.get(node)) {

            if (!vis[i]) {

                child++;

                helper(i, node, vis, isArt, l_time, f_time, adjL, time);

                l_time[node] = Math.min(l_time[node], l_time[i]);

                if (f_time[node] <= l_time[i] && parent != -1) {
                    isArt[node] = true;
                }
            }

            else if (i != parent) {
                l_time[node] = Math.min(l_time[node], f_time[i]);
            }
        }

        if (parent == -1 && child > 1) {
            isArt[node] = true;
        }
    }
}
```

---

## 🔍 Deep Explanation

### 🔹 Why `time[]` as array?

- Java passes primitives by value.
- Using `int[] time` allows **shared mutable counter** across recursion.

---

### 🔹 Why `parent`?

- To avoid considering the **same edge as back edge**
- Prevents incorrect low time updates

---

### 🔹 What is happening in DFS loop?

```java
for(int i : adjL.get(node))
```

Two cases:

---

### ✅ Case 1: Unvisited Node (Tree Edge)

```java
if (!vis[i])
```

- This is a **forward DFS step**
- Steps:
    1. Increase child count
    2. Recursively visit child
    3. Update low time

```java
l_time[node] = Math.min(l_time[node], l_time[i]);
```

---

### 🔥 Articulation Condition

```java
if (f_time[node] <= l_time[i] && parent != -1)
```

Means:

- Child `i` cannot reach ancestor of `node`
- So removing `node` disconnects the graph

---

### 🔁 Case 2: Back Edge

```java
else if (i != parent)
```

- Already visited node → back edge
- Update:

```java
l_time[node] = Math.min(l_time[node], f_time[i]);
```

---

### 🌳 Special Case: Root Node

```java
if (parent == -1 && child > 1)
```

- Root is articulation point only if it has **more than 1 child**

---

## 🧪 Dry Run Example

Graph:

```
0 — 1 — 4
      / \
     2 — 3
```

### DFS Flow:

1. Start at 0 → go to 1 → go to 4
2. From 4 → go to 3 → then 2 → back to 4

### Key Observations:

- Node **1**:
    - Removing it disconnects node 0 → so it's an articulation point

- Node **4**:
    - Removing it separates (2,3) from rest → articulation point

### Output:

```
1
4
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(V + E) |
| Space | O(V) |

---

## 🎯 Key Takeaways

- Uses **Tarjan’s Algorithm**
- Based on **DFS + Low Time**
- Detects critical nodes in graph
- Important for:
    - Network design
    - Failure analysis
    - Graph connectivity

---

## 🏁 Summary

> A node is an articulation point if removing it breaks connectivity.

---

## 📚 Related Topics

- Bridges in Graph
- Tarjan’s Algorithm
- DFS Traversal
- Strongly Connected Components