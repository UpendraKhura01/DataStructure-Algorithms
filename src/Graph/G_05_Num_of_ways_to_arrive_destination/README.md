# 🚗 Number of Ways to Arrive at Destination (Shortest Paths Count)

## 📌 Problem Statement

Given a weighted undirected graph with `V` vertices and edges `[u, v, w]`,
find the **number of distinct shortest paths** from node `0` to node `V-1`.

---

## 💡 Intuition

This is not just a shortest path problem — we also need to **count how many shortest paths exist**.

### 🔍 Key Idea:

* Use **Dijkstra’s Algorithm** to find shortest distances.
* While doing that, also track:

    * How many ways we can reach each node with the **minimum distance**.

---

## 🧠 Thought Process

Normally, Dijkstra only tracks the **minimum distance**.

Here, we extend it:

### 🪜 For each node:

* `Dist[i]` → shortest distance to node `i`
* `pathN[i]` → number of ways to reach node `i` with shortest distance

---

### 🔁 While exploring neighbors:

For every edge:

#### ✅ Case 1: Found another shortest path

```text
new_dist == Dist[neighbour]
```

➡️ Add ways:

```text
pathN[neighbour] += pathN[node]
```

---

#### ✅ Case 2: Found a better (shorter) path

```text
new_dist < Dist[neighbour]
```

➡️ Update:

* New shortest distance
* Reset number of ways

```text
Dist[neighbour] = new_dist
pathN[neighbour] = pathN[node]
```

---

#### ❌ Case 3: Worse path

Ignore it.

---

## ⚙️ Algorithm Steps

1. Build adjacency list (node → {neighbor, weight})
2. Use **Min Heap (PriorityQueue)** for Dijkstra
3. Initialize:

    * `Dist[0] = 0`
    * `pathN[0] = 1`
4. Run Dijkstra:

    * Relax edges
    * Update path counts
5. Return `pathN[V-1]`

---

## 🚀 Code

```java id="x82kf1"
static int countPaths(int V, int[][] edges) {

    ArrayList<ArrayList<Pair>> AdjL = new ArrayList<>();
    for (int i = 0; i < V; i++) {
        AdjL.add(new ArrayList<>());
    }

    // Build graph
    for (int[] edge : edges) {
        int node = edge[0];
        int neighbour = edge[1];
        int w = edge[2];

        AdjL.get(node).add(new Pair(neighbour, w));
        AdjL.get(neighbour).add(new Pair(node, w));
    }

    PriorityQueue<Pair> pq = new PriorityQueue<>(
            Comparator.comparingInt((Pair p) -> p.dist)
                      .thenComparingInt(p -> p.node)
    );

    int[] Dist = new int[V];
    Arrays.fill(Dist, Integer.MAX_VALUE);
    Dist[0] = 0;

    int[] pathN = new int[V];
    pathN[0] = 1;

    pq.add(new Pair(0, 0));

    while (!pq.isEmpty()) {
        int node = pq.peek().node;
        int dist = pq.peek().dist;
        pq.remove();

        if (dist > Dist[node]) continue;

        for (int i = 0; i < AdjL.get(node).size(); i++) {
            int neighbour = AdjL.get(node).get(i).node;
            int weight = AdjL.get(node).get(i).dist;

            int new_dist = dist + weight;

            if (new_dist == Dist[neighbour]) {
                pathN[neighbour] += pathN[node];
            } 
            else if (new_dist < Dist[neighbour]) {
                Dist[neighbour] = new_dist;
                pq.add(new Pair(neighbour, new_dist));
                pathN[neighbour] = pathN[node];
            }
        }
    }

    return pathN[V - 1];
}
```

---

## 🧪 Example

### Input:

```id="p1k82a"
V = 4
edges = [
  [0,1,2],
  [1,2,3],
  [0,3,5],
  [1,3,3],
  [2,3,4]
]
```

### Graph:

```id="qwe92k"
0 --2-- 1 --3-- 2
 \      |
  5     3
   \    |
     3
```

### Output:

```id="x92lka"
2
```

### Explanation:

Shortest distance from `0 → 3` = `5`

Paths:

* `0 → 3`
* `0 → 1 → 3`

---

## 📊 Complexity Analysis

| Type  | Complexity       |
| ----- | ---------------- |
| Time  | O((V + E) log V) |
| Space | O(V + E)         |

---

## 🎯 Key Takeaways

* This is **Dijkstra + Path Counting**.
* Maintain:

    * Distance array
    * Ways array
* Update logic is the key trick 🔑
* Works only for **non-negative weights**

---

## ⚠️ Common Mistakes

* ❌ Forgetting to update path count on equal distance
* ❌ Not resetting count when shorter path found
* ❌ Not skipping outdated PQ entries

---

## 🏁 Summary

> Use Dijkstra to find shortest paths, and simultaneously count how many such paths exist.

---

## 📚 Related Concepts

* Dijkstra’s Algorithm
* Shortest Path in Weighted Graph
* Dynamic Programming on Graphs
* Priority Queue (Min Heap)

---


