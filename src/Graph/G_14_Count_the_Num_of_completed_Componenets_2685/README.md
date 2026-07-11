# Count the Number of Complete Components (LeetCode 2685)

**Difficulty:** Medium

## Problem Statement

You are given an undirected graph with `n` vertices numbered from `0` to `n-1`.

A **connected component** is called **complete** if every pair of vertices inside that component has an edge between them.

Return the number of complete connected components.

---

## Example 1

### Input

```text
n = 6

edges = [
 [0,1],
 [0,2],
 [1,2],
 [3,4]
]
```

### Graph

```
0 ----- 1
 \     /
   \ /
    2

3 ----- 4

5
```

### Output

```text
3
```

### Explanation

There are three connected components.

```
{0,1,2}
```

Every pair is connected.

```
Edges = 3

Expected = 3C2 = 3
```

Complete.

---

```
{3,4}
```

One edge exists.

Expected

```
2C2 = 1
```

Complete.

---

```
{5}
```

Single vertex is always complete.

Total = **3**

---

## Example 2

### Input

```text
n = 6

edges = [
 [0,1],
 [0,2],
 [1,2],
 [3,4],
 [3,5]
]
```

### Graph

```
0 ----- 1
 \     /
   \ /
    2


4

 \
 3

 /
5
```

### Output

```text
1
```

### Explanation

Component

```
{0,1,2}
```

is complete.

Component

```
{3,4,5}
```

has only

```
3-4
3-5
```

Missing

```
4-5
```

Hence not complete.

Answer = **1**

---

# Observation

Suppose a connected component has

```
k vertices
```

A complete graph must contain

```
k × (k−1) / 2
```

edges.

Therefore,

For every connected component

- Count vertices.
- Count edges.
- Compare with

```
k(k−1)/2
```

If equal,

the component is complete.

---

# Algorithm

1. Build adjacency list.
2. Maintain a visited array.
3. Traverse every unvisited node.
4. Run DFS.
5. During DFS count
    - number of vertices
    - sum of degrees
6. Since every edge is counted twice,

```
edges = degreeSum / 2
```

7. Compute expected edges

```
k(k−1)/2
```

8. If equal,
   increment answer.

---

# Dry Run

### Graph

```
0 -- 1
 \  /
  2

3 -- 4
```

---

### DFS from 0

Visit

```
0
1
2
```

Nodes

```
3
```

Degrees

```
0 → 2
1 → 2
2 → 2
```

Degree Sum

```
6
```

Actual edges

```
6/2 = 3
```

Expected

```
3×2/2 = 3
```

Complete.

---

### DFS from 3

Nodes

```
2
```

Degree Sum

```
2
```

Edges

```
1
```

Expected

```
2×1/2 = 1
```

Complete.

Answer becomes

```
2
```

---

# Why divide edges by 2?

Consider

```
0 ----- 1
```

Adjacency list

```
0 → 1
1 → 0
```

While DFS

```
degreeSum = 2
```

But there is only

```
1 edge
```

Therefore

```
edges = degreeSum / 2
```

---

# Correctness Proof

For every connected component, DFS visits every vertex exactly once.

During traversal

- `nodes` stores the number of vertices.
- `edges` stores the sum of degrees.

Since every undirected edge contributes to the degree of both endpoints,

```
Actual Edges = degreeSum / 2
```

A graph with `k` vertices is complete **iff**

```
Edges = k(k−1)/2
```

Hence every complete component is counted exactly once.

Therefore the algorithm is correct.

---

# Complexity Analysis

Let

```
V = n
E = number of edges
```

### Time Complexity

Building graph

```
O(E)
```

DFS

```
O(V + E)
```

Total

```text
O(V + E)
```

---

### Space Complexity

Adjacency list

```
O(V + E)
```

Visited array

```
O(V)
```

Overall

```text
O(V + E)
```

---

# Java Solution

```java
class Solution {

    boolean[] vis;
    ArrayList<ArrayList<Integer>> adj;

    public int countCompleteComponents(int n, int[][] edges) {

        vis = new boolean[n];
        adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!vis[i]) {

                int[] res = dfs(i);

                int nodes = res[0];
                int edgeCount = res[1] / 2;

                if (edgeCount == nodes * (nodes - 1) / 2)
                    ans++;
            }
        }

        return ans;
    }

    private int[] dfs(int node) {

        vis[node] = true;

        int nodes = 1;
        int edges = adj.get(node).size();

        for (int next : adj.get(node)) {

            if (!vis[next]) {

                int[] res = dfs(next);

                nodes += res[0];
                edges += res[1];
            }
        }

        return new int[]{nodes, edges};
    }
}
```

---

# Key Takeaways

- Traverse each connected component using DFS.
- Count:
    - Number of vertices.
    - Sum of degrees.
- Actual edges = `degreeSum / 2`.
- A component with `k` vertices is complete if

```
edges == k(k−1)/2
```

---

# Tags

- Graph
- DFS
- Connected Components
- Adjacency List
- Graph Theory