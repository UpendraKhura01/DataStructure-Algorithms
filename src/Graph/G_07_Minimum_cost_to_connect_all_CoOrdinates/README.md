# 🌐 Minimum Cost to Connect All Coordinates (Prim’s Algorithm)

## 📌 Problem Statement

You are given an array `houses` where each element represents the coordinates of a house:

```
houses[i] = [xi, yi]
```

The cost to connect two houses is the **Manhattan Distance**:

```
|x1 - x2| + |y1 - y2|
```

### 🎯 Goal:
Connect all houses such that:
- All houses are reachable
- Total connection cost is **minimum**

---

## 💡 Intuition

- This is a **Minimum Spanning Tree (MST)** problem.
- We need to connect all nodes with **minimum total edge weight**.
- Since every pair of nodes can be connected → it's a **complete graph**.

👉 Best approach:
> Use **Prim’s Algorithm** (greedy + priority queue)

---

## 🧠 Thought Process

### 🔹 Why MST?

- We want:
    - Minimum cost
    - All nodes connected
- That is exactly the definition of a **Minimum Spanning Tree**

---

### 🔹 Why Prim’s Algorithm?

- Starts from any node
- Always picks the **smallest edge** connecting visited → unvisited
- Efficient with **priority queue**

---

## ⚙️ Approach

1. Build graph:
    - Each node connected to every other node
    - Edge weight = Manhattan distance

2. Use:
    - `PriorityQueue` → to pick minimum edge
    - `visited[]` → track included nodes

3. Start from node `0`

4. Repeat:
    - Pick minimum distance node
    - Add its cost
    - Push its neighbors into queue

5. Stop when all nodes are visited

---

## 🔍 Key Components

### 1. Distance Function

```java
|x1 - x2| + |y1 - y2|
```

Used to calculate cost between two houses.

---

### 2. Pair Class

```java
pair(dist, node)
```

- `first` → distance (weight)
- `second` → node index

---

### 3. Priority Queue

```java
PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
```

- Always gives **minimum distance edge**

---

## 🚀 Code (Java)

```java
    static int minCost(int[][] houses) {
        int n = houses.length;

        ArrayList<ArrayList<pair>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        // Build graph (complete graph)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = dist(houses[i][0], houses[j][0],
                             houses[i][1], houses[j][1]);

                list.get(i).add(new pair(d, j));
                list.get(j).add(new pair(d, i));
            }
        }

        PriorityQueue<pair> pq = new PriorityQueue<>(
                (a, b) -> a.first - b.first
        );

        boolean[] vis = new boolean[n];
        int ans = 0;

        pq.add(new pair(0, 0)); // start from node 0

        while (!pq.isEmpty()) {
            pair p = pq.poll();

            int dist = p.first;
            int node = p.second;

            if (vis[node]) continue;

            vis[node] = true;
            ans += dist;

            for (pair neighbour : list.get(node)) {
                if (!vis[neighbour.second]) {
                    pq.add(neighbour);
                }
            }
        }

        return ans;
    }

    static int dist(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class pair {
        int first;
        int second;

        pair(int dist, int node) {
            this.first = dist;
            this.second = node;
        }
    }

```

---

## 🧪 Dry Run Example

Input:

```
houses = [
 [0,7],
 [0,9],
 [20,7],
 [30,7],
 [40,70]
]
```

### Step-by-step:

1. Start at node `0`
2. Add edges:
    - (0 → 1), (0 → 2), (0 → 3), (0 → 4)
3. Pick smallest edge → go to next node
4. Repeat:
    - Always pick **minimum edge to unvisited node**

### Final MST Edges (example):

```
0 → 1
0 → 2
2 → 3
3 → 4
```

### Total Cost:

```
Minimum total cost = 105
```

---

## 🔁 How Greedy Works Here

- At each step:
    - Choose smallest available edge
    - Expand MST
- Never revisit nodes → avoids cycles

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n² log n) |
| Space | O(n²) |

- `n²` edges (complete graph)
- Priority queue operations → log n

---

## 🎯 Key Takeaways

- This is a **Minimum Spanning Tree problem**
- Use **Prim’s Algorithm + Min Heap**
- Graph is **complete** → every node connected to every other
- Greedy ensures **optimal solution**

---

## 🏁 Summary

> Connect all houses with minimum total cost using MST (Prim’s Algorithm)

---

## 📚 Related Topics

- Minimum Spanning Tree (MST)
- Prim’s Algorithm
- Kruskal’s Algorithm
- Graph Greedy Algorithms