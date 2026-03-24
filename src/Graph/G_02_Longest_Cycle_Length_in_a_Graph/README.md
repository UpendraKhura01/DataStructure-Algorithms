Here’s your **complete GitHub-style Markdown file** for your **Longest Cycle in Graph (your exact code + logic)** — everything in a **single file**, clean and ready to paste 👇

---

```md id="longest_cycle_full"
# 🔁 Longest Cycle in a Directed Graph (DFS + Map)

This repository contains a Java solution to find the **length of the longest cycle** in a directed graph where each node has **at most one outgoing edge**.

---

## ❓ Problem Statement

Given a directed graph with `V` vertices and an edge list, find the **length of the longest cycle**.  
If no cycle exists, return `-1`.

---

## 🧪 Example

```

Input:
V = 7
edges = [
[0, 5],
[1, 0],
[2, 4],
[3, 1],
[4, 6],
[5, 6],
[6, 3]
]

```id="ex1"

---

### 🔍 Graph Representation

```

0 → 5 → 6 → 3 → 1 → 0   (cycle length = 5)
2 → 4 → 6               (leads into cycle but not part of it)

```id="graph1"

---

### ✅ Output

```

5

```id="out1"

---

## 🧠 Intuition

- Each node has **only one outgoing edge** → this is a **functional graph**
- A node can:
  - Be part of a **cycle**
  - Lead to a cycle
  - Lead to a dead end (`-1`)

---

### 🔥 Key Idea

While traversing:

- If we revisit a node in the **current DFS path** → ✅ cycle found  
- If we revisit a node already processed earlier → ❌ ignore  

---

## ⚙️ Approach (DFS + Map)

1. Build a graph using an array/list where:
   - `AdjL[i] = next node of i`

2. Traverse each node using DFS

3. Maintain:
   - `vis[]` → tracks globally visited nodes
   - `mp` → stores nodes in current DFS path with their distance

4. During DFS:
   - If node = `-1` → stop
   - If node exists in `mp` → cycle detected
   - If node already visited → stop

5. Calculate cycle length using:
```

cycleLength = currentDistance - firstOccurrenceDistance



## 🔁 Cycle Detection

```

if (mp.containsKey(node))

```id="cycle_check"

👉 This ensures the node is part of the **current DFS path**

---

## 📏 Cycle Length Formula

```

dist - mp.get(node)


```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class solution {

    public static int longestCycle(int V, int[][] edges) {

        ArrayList<Integer> AdjL = new ArrayList<>();

        // initialize graph
        for (int i = 0; i < V; i++) AdjL.add(-1);

        int[] vis = new int[V];

        // build graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            AdjL.set(u, v);
        }

        int ans = -1;

        // try DFS from every node
        for (int i = 0; i < V; i++) {
            if (vis[i] != 1) {
                ans = Math.max(ans,
                        helper(i, AdjL, vis, 0, new HashMap<>()));
            }
        }

        return ans;
    }

    static int helper(int node,
                      ArrayList<Integer> AdjL,
                      int[] vis,
                      int dist,
                      Map<Integer, Integer> mp) {

        // no outgoing edge
        if (node == -1) return -1;

        // cycle detected
        if (mp.containsKey(node)) {
            return dist - mp.get(node);
        }

        // already visited earlier
        if (vis[node] == 1) return -1;

        vis[node] = 1;

        // store current node with its distance
        mp.put(node, dist);

        return helper(AdjL.get(node), AdjL, vis, dist + 1, mp);
    }
}
````

---

## 🔍 Dry Run

Starting from node `0`:

````
0 → 5 → 6 → 3 → 1 → 0
``` id="dry1"

Path map:

````

{0:0}
{0:0, 5:1}
{0:0, 5:1, 6:2}
{0:0, 5:1, 6:2, 3:3}
{0:0, 5:1, 6:2, 3:3, 1:4}

```id="dry2"

Revisit node `0`:

```

cycleLength = 5 - 0 = 5

```id="dry3"

---

## 🚨 Why NOT use only `vis[]`?

```

if (vis[node] == 1) → WRONG for cycle detection

```id="wrong1"

Because:
- It includes nodes from previous DFS
- Does not guarantee a cycle

---

### ✅ Correct Condition

```

if (mp.containsKey(node))

```id="correct1"

✔ Ensures node is in **current path**

---

## ⚡ Why `vis[node] == 1` returns -1?

- Node already fully processed earlier
- Any cycle from it is already counted
- Avoids recomputation

---

## ⏱ Complexity Analysis

- **Time Complexity:** `O(N)`
- **Space Complexity:** `O(N)`

Each node is processed only once.

---

## 🔥 Key Takeaways

- Use `mp` for **cycle detection**
- Use `vis[]` to **avoid reprocessing**
- Cycle exists only if node repeats in **same DFS path**

---

## 🚀 Bonus Insight

This problem is related to:

- Functional graphs
- DFS with path tracking
- Linked list cycle detection
- LeetCode 2360 (Longest Cycle in Graph)

---
```

---

This is exactly like your style:

* ✅ Single file
* ✅ All explanations inside
* ✅ Your code preserved
* ✅ Clean GitHub-ready

---


