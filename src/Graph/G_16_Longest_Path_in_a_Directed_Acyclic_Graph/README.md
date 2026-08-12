
# Longest Path in a Directed Acyclic Graph

---

# Problem Statement

Given a weighted **Directed Acyclic Graph (DAG)** with `V` vertices numbered from:

```text
0 to V - 1
```

and a source vertex `src`.

Each edge is represented as:

```text
[u, v, w]
```

which means:

```text
u ──w──> v
```

where:

- `u` = source vertex
- `v` = destination vertex
- `w` = edge weight

The task is to find the **longest distance from `src` to every vertex**.

If a vertex cannot be reached from `src`, its distance should be:

```text
Integer.MIN_VALUE
```

The graph is a DAG, so there are no directed cycles.

---

### Example 1

```text
V = 4
src = 0

edges =
[
    [0,1,1],
    [0,2,1],
    [1,2,5],
    [3,1,2],
    [3,2,-1]
]
```

The important paths from source `0` are:

```text
0 -> 1
```

with distance:

```text
1
```

and:

```text
0 -> 1 -> 2
```

with distance:

```text
1 + 5 = 6
```

Vertex `3` cannot be reached from `0`.

Therefore:

```text
Output = [0, 1, 6, INF]
```

where `INF` represents `Integer.MIN_VALUE` for unreachable vertices.

---

### Example 2

```text
V = 5
src = 1

edges =
[
    [0,1,1],
    [0,2,2],
    [1,4,4],
    [3,2,-1],
    [4,2,3],
    [4,3,6]
]
```

From source `1`:

```text
1 -> 4
```

has distance:

```text
4
```

Then:

```text
1 -> 4 -> 2
```

has distance:

```text
4 + 3 = 7
```

and:

```text
1 -> 4 -> 3 -> 2
```

has distance:

```text
4 + 6 + (-1)
= 9
```

Therefore:

```text
Output = [INF, 0, 9, 10, 4]
```

---

# Goal

For every vertex `v`, calculate:

```text
Longest distance from src to v
```

If no path exists:

```text
dist[v] = Integer.MIN_VALUE
```

---

# Intuition

For shortest paths, we usually try to minimize the distance.

Here, the objective is the opposite:

```text
Instead of:
dist[v] = minimum possible distance

We need:
dist[v] = maximum possible distance
```

For every edge:

```text
u -> v with weight w
```

if we already know the best distance to `u`, then we can attempt to improve the distance to `v`:

```text
newDistance = dist[u] + w
```

If:

```text
newDistance > dist[v]
```

then we have found a better path.

So the core relaxation becomes:

```text
if (dist[u] + w > dist[v])
    dist[v] = dist[u] + w
```

---

# Key Idea

The solution uses a **max-priority queue**.

The priority queue stores:

```text
[vertex, currentDistance]
```

and always processes the vertex having the largest currently known distance first.

The relaxation rule is:

```text
if (currentDistance + edgeWeight > dist[nextVertex])
```

then update:

```text
dist[nextVertex] = currentDistance + edgeWeight
```

and push the updated state into the priority queue.

---

### Why `Integer.MIN_VALUE`?

Initially, we do not know whether a vertex is reachable.

So every vertex starts as:

```text
Integer.MIN_VALUE
```

The source starts with:

```text
dist[src] = 0
```

This gives us a clear distinction:

```text
Integer.MIN_VALUE
        ↓
unreachable

0 or greater / other reachable value
        ↓
reachable
```

---

# Thought Process

### Step 1: Build the Graph

Convert the edge list into an adjacency list.

For:

```text
[u, v, w]
```

store:

```text
u -> [v, w]
```

Because the graph is directed, we add the edge only in one direction.

---

### Step 2: Initialize Distances

Create:

```text
dist[V]
```

and initialize everything to:

```text
Integer.MIN_VALUE
```

Then:

```text
dist[src] = 0
```

because the distance from the source to itself is zero.

---

### Step 3: Use a Max-Heap

The priority queue is defined as:

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
```

The second element represents the distance.

Because the comparator reverses the order, the largest distance comes first.

---

### Step 4: Process the Best Current State

Remove the top state:

```text
[currentVertex, currentDistance]
```

Then inspect every outgoing edge.

For:

```text
currentVertex -> nextVertex
```

with weight `edgeWeight`:

```text
newDistance =
currentDistance + edgeWeight
```

---

### Step 5: Relax the Edge

If:

```text
newDistance > dist[nextVertex]
```

then update:

```text
dist[nextVertex] = newDistance
```

and add:

```text
[nextVertex, newDistance]
```

to the priority queue.

---

### Step 6: Return the Distance Array

After all possible improvements have been processed:

```text
return dist
```

---

# Code (Functions Only)

```java
int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {

    List<List<int[]>> adjl = new ArrayList<>();

    for (int i = 0; i < V; i++) {
        adjl.add(new ArrayList<>());
    }

    for (ArrayList<Integer> e : edges) {

        int u = e.get(0);
        int v = e.get(1);
        int w = e.get(2);

        int[] cur = new int[]{v, w};

        adjl.get(u).add(cur);
    }

    int[] dist = new int[V];

    Arrays.fill(dist, Integer.MIN_VALUE);

    PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

    pq.add(new int[]{src, 0});

    dist[src] = 0;

    while (!pq.isEmpty()) {

        int[] cur = pq.poll();

        int e = cur[0];
        int w = cur[1];

        for (int[] i : adjl.get(e)) {

            int ne = i[0];
            int nw = i[1];

            if (w + nw > dist[ne]) {

                dist[ne] = w + nw;

                pq.add(new int[]{ne, w + nw});
            }
        }
    }

    return dist;
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
V = 4
src = 0

Edges:

0 -> 1 (1)
0 -> 2 (1)
1 -> 2 (5)
3 -> 1 (2)
3 -> 2 (-1)
```

---

## Step 1: Initialize

Initially:

```text
dist = [MIN, MIN, MIN, MIN]
```

Source is `0`:

```text
dist[0] = 0
```

Therefore:

```text
dist = [0, MIN, MIN, MIN]
```

Priority queue:

```text
[0, 0]
```

---

## Step 2: Process Vertex 0

Remove:

```text
[0, 0]
```

Outgoing edges:

```text
0 -> 1 (1)
0 -> 2 (1)
```

### Edge 0 -> 1

```text
newDistance = 0 + 1
            = 1
```

Since:

```text
1 > MIN
```

update:

```text
dist[1] = 1
```

Queue:

```text
[1,1]
```

---

### Edge 0 -> 2

```text
newDistance = 0 + 1
            = 1
```

Update:

```text
dist[2] = 1
```

Queue:

```text
[1,1]
[2,1]
```

Distance array:

```text
[0, 1, 1, MIN]
```

---

## Step 3: Process Vertex 1

Remove:

```text
[1,1]
```

Outgoing edge:

```text
1 -> 2 (5)
```

Calculate:

```text
newDistance = 1 + 5
            = 6
```

Current:

```text
dist[2] = 1
```

Since:

```text
6 > 1
```

update:

```text
dist[2] = 6
```

Queue:

```text
[2,6]
[2,1]
```

---

## Step 4: Process Vertex 2

The maximum-distance state is:

```text
[2,6]
```

Vertex `2` has no outgoing edges.

Nothing changes.

We later encounter the old state:

```text
[2,1]
```

but it cannot improve anything.

---

## Step 5: Vertex 3

There is no path from:

```text
0 -> 3
```

Therefore:

```text
dist[3] = Integer.MIN_VALUE
```

---

## Final Result

```text
dist = [0, 1, 6, Integer.MIN_VALUE]
```

The unreachable value is displayed as `INF` by the problem's driver.

Therefore:

```text
[0, 1, 6, INF]
```

---

# Logic Flow

```text
Start

↓

Build adjacency list

↓

Initialize dist[]

↓

dist[v] = Integer.MIN_VALUE
for every vertex

↓

dist[src] = 0

↓

Insert [src, 0] into max-priority queue

↓

Is priority queue empty?

       No
        |
        v
   Remove state
   [node, distance]

        ↓

For every outgoing edge:

node -> next

        ↓

newDistance =
distance + weight

        ↓

Is newDistance > dist[next]?

       No
        |
        v
      Skip

       Yes
        |
        v
Update dist[next]

        ↓

Push [next, newDistance]

        ↓

Continue

        ↓

Queue empty

        ↓

Return dist[]

        ↓

End
```

---

# Complexity

Let:

```text
V = number of vertices
E = number of edges
```

Building the adjacency list requires:

```text
O(V + E)
```

The priority queue can contain multiple states for the same vertex because an improved distance is pushed again.

Each relaxation may cause a priority queue insertion.

Therefore, for this implementation:

```text
Time Complexity = O(E log E)
```

The adjacency list requires:

```text
O(V + E)
```

space.

The distance array requires:

```text
O(V)
```

and the priority queue can hold up to:

```text
O(E)
```

states.

Therefore:

```text
Space Complexity = O(V + E)
```

---

### DAG-Specific Note

Because the graph is guaranteed to be a DAG, there is an even more standard approach:

```text
Topological Sort
+
DP Relaxation
```

That approach can achieve:

```text
Time  = O(V + E)
Space = O(V + E)
```

The provided code instead uses a **max-priority queue relaxation strategy**.

The important distinction is that the graph being a DAG is what makes the topological-DP solution especially efficient and natural.

---

# Key Takeaways

- This is a **Longest Path in DAG** problem.
- The distance array is initialized to:
  ```text
  Integer.MIN_VALUE
  ```
  to represent unreachable vertices.
- The source has:
  ```text
  dist[src] = 0
  ```
- For every edge:
  ```text
  u -> v
  ```
  with weight `w`, perform:
  ```text
  dist[v] = max(dist[v], dist[u] + w)
  ```
- The provided implementation uses a **max-priority queue**.
- Unlike ordinary Dijkstra, this is a longest-distance relaxation strategy and should not be treated as a general shortest/longest path algorithm for arbitrary graphs.
- The DAG property is crucial for the standard longest-path solution.
- A topological-order DP solution is the preferred optimal approach for a DAG.

---

# Most Important Insight

The most important insight is that the longest distance to a vertex can be improved through one of its incoming paths.

For an edge:

```text
u --w--> v
```

if the best known distance to `u` is:

```text
dist[u]
```

then going through this edge gives:

```text
dist[u] + w
```

Therefore:

```text
dist[v] = max(dist[v], dist[u] + w)
```

This is called **relaxation**.

For a DAG, the strongest pattern to remember is:

```text
Topological Order
       ↓
Process vertices from left to right
       ↓
Relax every outgoing edge
       ↓
Longest distance is finalized
```

The general DP recurrence is:

```text
dist[v] = max(
    dist[v],
    dist[u] + weight(u, v)
)
```

The key difference from shortest-path problems is simply:

```text
Shortest Path:
min()

Longest Path in DAG:
max()
```

---

# Summary

The problem asks for the longest distance from a source vertex to every other vertex in a weighted DAG.

The provided solution:

```text
1. Builds an adjacency list
2. Initializes all distances to Integer.MIN_VALUE
3. Sets the source distance to 0
4. Uses a max-priority queue
5. Relaxes edges whenever a larger distance is found
6. Returns the final distance array
```

The central relaxation formula is:

```text
dist[next] =
max(dist[next], currentDistance + edgeWeight)
```

For the provided implementation:

```text
Time Complexity  = O(E log E)
Space Complexity = O(V + E)
```

Because the graph is a DAG, the standard and more optimal approach is:

```text
Topological Sort + Dynamic Programming
```

which achieves:

```text
Time Complexity  = O(V + E)
Space Complexity = O(V + E)
```

The core DSA pattern to remember is:

```text
DAG
 ↓
Topological Order
 ↓
Relax Edges
 ↓
Use max instead of min
 ↓
Longest Distance
```
