
# Shortest Path in 1-2 Graph

---

# Problem Statement

Given a weighted **undirected graph** with `V` vertices numbered from `0` to `V - 1`, where every edge has a weight of either **1** or **2**, find the **shortest distance** between two given vertices `src` and `dest`.

If no path exists, return **-1**.

### Example

```text
Input

V = 4

Edges

0 --1-- 1
|       |
2       2
|       |
2 --1-- 3

src = 0
dest = 3

Output

3
```

Explanation:

Two shortest paths are:

```text
0 → 1 → 3

Cost = 1 + 2 = 3
```

or

```text
0 → 2 → 3

Cost = 2 + 1 = 3
```

Hence, the shortest distance is **3**.

---

# Goal

Determine the minimum total edge weight required to travel from `src` to `dest`.

If the destination cannot be reached, return **-1**.

---

# Intuition

Although edge weights are limited to only **1** and **2**, the graph is still **weighted**.

A normal BFS guarantees shortest paths only when **every edge has the same weight**.

Since weights differ, we must relax edges whenever a shorter path is discovered.

This solution presents two approaches:

1. **Queue-based relaxation (SPFA-like BFS)**
2. **Dijkstra's Algorithm using a Priority Queue** (recommended)

---

# Key Idea

First, convert the edge list into an adjacency list.

Each adjacency entry stores:

```text
(neighbor, edgeWeight)
```

Then compute the shortest distance.

### Approach 1

Maintain a queue.

Whenever a better distance is found,

```text
distance[neighbor] > distance[current] + weight
```

update the distance and push the neighbor back into the queue.

---

### Approach 2 (Optimal)

Use a **Min Priority Queue**.

Always process the node having the smallest known distance.

This guarantees that once the destination is removed from the priority queue, its shortest distance has been found.

---

# Thought Process

## Step 1

Build the adjacency list.

```text
u -----w----- v
```

Store

```text
u → (v,w)

v → (u,w)
```

because the graph is undirected.

---

## Step 2

Initialize the distance array.

```text
distance[i] = ∞

distance[src] = 0
```

---

## Step 3

Process vertices.

### Queue Approach

Whenever a shorter distance is found,

```text
distance[neighbor]
=
distance[current] + weight
```

enqueue the neighbor again.

---

### Dijkstra Approach

Always remove the vertex having the minimum distance.

Relax all outgoing edges.

If a better distance is found,

push the updated state into the priority queue.

---

## Step 4

When the destination is reached (Dijkstra),

return the current distance immediately.

If traversal finishes without reaching the destination,

return

```text
-1
```

---

# Code (Functions Only)

```java
int shortestPath(int V, int src, int dest, int[][] edges) {

    if (src == dest) return 0;

    ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

    for (int i = 0; i < V; i++)
        adj.add(new ArrayList<>());

    for (int[] e : edges) {
        int u = e[0];
        int v = e[1];
        int w = e[2];

        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }

    int[] best = new int[V];
    Arrays.fill(best, Integer.MAX_VALUE);

    best[src] = 0;

    Queue<Integer> q = new ArrayDeque<>();
    q.add(src);

    while (!q.isEmpty()) {

        int node = q.poll();

        for (int[] edge : adj.get(node)) {

            int next = edge[0];
            int weight = edge[1];

            if (best[node] + weight < best[next]) {

                best[next] = best[node] + weight;
                q.add(next);
            }
        }
    }

    return best[dest] == Integer.MAX_VALUE ? -1 : best[dest];
}
```

### Dijkstra's Algorithm

```java
int Dijkstra(int V, int src, int dest, int[][] edges) {

    if (src == dest) return 0;

    ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

    for (int i = 0; i < V; i++)
        adj.add(new ArrayList<>());

    for (int[] e : edges) {

        int u = e[0];
        int v = e[1];
        int w = e[2];

        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }

    PriorityQueue<state> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));

    pq.add(new state(src, 0));

    int[] best = new int[V];
    Arrays.fill(best, Integer.MAX_VALUE);

    while (!pq.isEmpty()) {

        state cur = pq.poll();

        int node = cur.v;
        int weight = cur.w;

        if (node == dest)
            return weight;

        for (int[] edge : adj.get(node)) {

            int next = edge[0];
            int newWeight = weight + edge[1];

            if (newWeight >= best[next])
                continue;

            best[next] = newWeight;

            pq.add(new state(next, newWeight));
        }
    }

    return -1;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
V = 4

Edges

0 --1-- 1
|       |
2       2
|       |
2 --1-- 3

src = 0
dest = 3
```

---

### Build Graph

```text
0

→ (1,1)
→ (2,2)

1

→ (0,1)
→ (2,1)
→ (3,2)

2

→ (0,2)
→ (1,1)
→ (3,1)

3

→ (1,2)
→ (2,1)
```

---

### Initial Distances

```text
0 : 0

1 : ∞

2 : ∞

3 : ∞
```

Priority Queue

```text
(0,0)
```

---

### Process Vertex 0

Update

```text
1 → 1

2 → 2
```

Queue

```text
(1,1)

(2,2)
```

---

### Process Vertex 1

Possible updates

```text
2

Current = 2

New = 1 + 1 = 2

No improvement.
```

Update

```text
3 = 1 + 2 = 3
```

Queue

```text
(2,2)

(3,3)
```

---

### Process Vertex 2

Possible path

```text
2 → 3

Cost

2 + 1 = 3
```

Already equal.

No update.

---

### Process Vertex 3

Destination reached.

Answer

```text
3
```

---

# Logic Flow

```text
Start

↓

Build adjacency list

↓

Initialize distance array

↓

distance[src] = 0

↓

Choose traversal

↓

Queue Relaxation
OR

Priority Queue (Dijkstra)

↓

Relax every adjacent edge

↓

Better distance found?

↓

Yes

↓

Update distance

↓

Insert again

↓

Destination reached?

↓

Return shortest distance

↓

Otherwise

Return -1

↓

End
```

---

# Complexity

## Queue-Based Relaxation

### Time Complexity

Worst case:

```text
O(V × E)
```

A vertex may be processed multiple times whenever a shorter path is found.

---

### Space Complexity

Adjacency list:

```text
O(V + E)
```

Distance array:

```text
O(V)
```

Queue:

```text
O(V)
```

Overall:

```text
O(V + E)
```

---

## Dijkstra's Algorithm

### Time Complexity

Building adjacency list:

```text
O(E)
```

Priority Queue operations:

```text
O((V + E) log V)
```

Overall:

```text
O((V + E) log V)
```

---

### Space Complexity

Adjacency list:

```text
O(V + E)
```

Distance array:

```text
O(V)
```

Priority Queue:

```text
O(V)
```

Overall:

```text
O(V + E)
```

---

# Key Takeaways

- A normal BFS is not sufficient for weighted graphs with edge weights 1 and 2.
- The queue-based approach repeatedly relaxes edges until no shorter paths remain.
- Dijkstra's algorithm is the standard and more efficient solution for positive weighted graphs.
- An adjacency list minimizes memory usage and supports efficient traversal.
- A priority queue ensures vertices are processed in increasing order of their current shortest distance.

---

# Most Important Insight

Even though edge weights are restricted to only **1** and **2**, the graph is still weighted. Therefore, the shortest path cannot be guaranteed using ordinary BFS. The correct strategy is to **relax edges whenever a shorter distance is found**, with Dijkstra's algorithm providing the optimal solution by always expanding the vertex with the minimum known distance.

---

# Summary

The solution begins by converting the edge list into an adjacency list representation. It then computes the shortest path using either a queue-based relaxation approach or Dijkstra's algorithm. The queue approach repeatedly updates distances whenever improvements are found, while Dijkstra's algorithm uses a min-priority queue to process vertices in increasing order of distance, achieving **O((V + E) log V)** time. Because all edge weights are positive, Dijkstra is the preferred and most efficient solution for this problem.
