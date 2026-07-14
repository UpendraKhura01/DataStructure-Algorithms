# Towers Reaching Both Stations

**Difficulty:** Medium

## Problem Statement

You are given an `n × m` matrix `mat[][]` where each cell represents the **signal strength** of a communication tower.

There are two control stations:

- **Station P** covers the **top row** and **left column**.
- **Station Q** covers the **bottom row** and **right column**.

A signal can propagate from a tower to one of its four neighbouring towers (up, down, left, right) **only if**

```text
neighbour strength ≤ current strength
```

A tower located on a station's boundary can directly transmit to that station.

Return the number of towers from which a signal can eventually reach **both Station P and Station Q**.

---

## Examples

### Example 1

**Input**

```text
mat =
[
 [1,2,2,3,5],
 [3,2,3,4,4],
 [2,4,5,3,1],
 [6,7,1,4,5],
 [5,1,1,2,4]
]
```

**Output**

```text
7
```

**Explanation**

The following towers can reach both stations:

```text
(0,4)
(1,3)
(1,4)
(2,2)
(3,0)
(3,1)
(4,0)
```

---

### Example 2

**Input**

```text
mat =
[
 [2,2],
 [2,2]
]
```

**Output**

```text
4
```

All towers can reach both stations.

---

## Constraints

- `1 ≤ n, m ≤ 1000`
- `1 ≤ mat[i][j] ≤ 1000`

---

# Key Observation

A tower can move

```text
High → Low
```

because

```text
next ≤ current
```

Instead of checking every tower separately, reverse the process.

Imagine travelling **from a station into the grid**.

If a signal normally moves

```text
A → B
```

when

```text
B ≤ A
```

then the reverse traversal is possible when

```text
B ≥ A
```

Therefore, while expanding from a station we only move to neighbours having **greater or equal strength**.

---

# Reverse BFS

We perform two BFS traversals.

## BFS 1

Start from all cells touching **Station P**

- Top row
- Left column

Mark every tower that can eventually reach P.

---

## BFS 2

Start from all cells touching **Station Q**

- Bottom row
- Right column

Mark every tower that can eventually reach Q.

---

Finally,

Count every cell visited in **both** traversals.

---

# Algorithm

### Step 1

Create

```text
visP
visQ
```

---

### Step 2

Insert every top-row and left-column cell into the queue.

Run BFS.

Move only when

```text
nextHeight ≥ currentHeight
```

---

### Step 3

Repeat for

- bottom row
- right column

---

### Step 4

Count cells where

```text
visP && visQ
```

---

# Code

```java
public int countCoordinates(int[][] mat) {

    n = mat.length;
    m = mat[0].length;
    this.mat = mat;

    boolean[][] visP = new boolean[n][m];
    boolean[][] visQ = new boolean[n][m];

    Queue<int[]> pQueue = new ArrayDeque<>();
    Queue<int[]> qQueue = new ArrayDeque<>();

    // Top boundary
    for (int j = 0; j < m; j++) {
        visP[0][j] = true;
        pQueue.offer(new int[]{0, j});
    }

    // Left boundary
    for (int i = 0; i < n; i++) {
        visP[i][0] = true;
        pQueue.offer(new int[]{i, 0});
    }

    // Bottom boundary
    for (int j = 0; j < m; j++) {
        visQ[n - 1][j] = true;
        qQueue.offer(new int[]{n - 1, j});
    }

    // Right boundary
    for (int i = 0; i < n; i++) {
        visQ[i][m - 1] = true;
        qQueue.offer(new int[]{i, m - 1});
    }

    bfs(pQueue, visP);
    bfs(qQueue, visQ);

    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {

            if (visP[i][j] && visQ[i][j])
                ans++;

        }
    }

    return ans;
}
```

---

### BFS

```java
private void bfs(Queue<int[]> queue,
                 boolean[][] vis) {

    while (!queue.isEmpty()) {

        int[] cur = queue.poll();

        int r = cur[0];
        int c = cur[1];

        for (int k = 0; k < 4; k++) {

            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 &&
                nr < n &&
                nc >= 0 &&
                nc < m &&
                !vis[nr][nc] &&
                mat[nr][nc] >= mat[r][c]) {

                vis[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
```

---

# Dry Run

Consider

```text
1 2
4 3
```

## BFS from Station P

Initial queue

```text
1 2
4
```

Expansion

```text
1 → 2
1 → 4

2 cannot go to 3
4 can go to 3
```

Reachable

```text
1 2
4 3
```

---

## BFS from Station Q

Initial queue

```text
2
3 4
```

Expansion

```text
3 → 4
2 cannot reach 1
```

Reachable

```text
  2
4 3
```

---

Common cells

```text
2
4
3
```

Answer

```text
3
```

---

# Why Reverse BFS Works

A signal normally flows

```text
Higher → Lower
```

If a tower can send a signal to a station, then in reverse, the station can reach that tower by moving

```text
Lower → Higher
```

Thus,

Instead of running DFS/BFS from every cell (`O((nm)^2)`), we perform only **two BFS traversals**.

---

# Complexity Analysis

Let

```text
N = rows
M = columns
```

Each BFS visits every cell at most once.

### Time Complexity

```text
O(N × M)
```

Two BFS traversals:

```text
2 × O(NM)
```

which simplifies to

```text
O(NM)
```

---

### Space Complexity

Visited arrays

```text
O(NM)
```

Queues

```text
O(NM)
```

Overall

```text
O(NM)
```

---

# Summary

- Reverse the direction of signal propagation.
- Start BFS from the boundaries of each station.
- Move only to neighbours with **greater or equal** signal strength.
- Mark towers reachable from each station.
- Count towers reachable from **both** stations.

This is the optimal solution with **O(NM)** time complexity.