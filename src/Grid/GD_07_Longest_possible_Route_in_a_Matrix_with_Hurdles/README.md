# Longest Possible Route in a Matrix with Hurdles

**Difficulty:** Medium

## Problem Statement

Given a binary matrix `mat[][]` of size `n × m` containing values **0** and **1**, and four integers:

- `(xs, ys)` → Source cell
- `(xd, yd)` → Destination cell

find the **length of the longest possible path** from the source to the destination.

### Rules

- `1` → Traversable cell.
- `0` → Blocked cell.
- Movement is allowed in **4 directions**:
    - Up
    - Down
    - Left
    - Right
- A cell can be visited **only once** in a path.
- Return **-1** if destination cannot be reached.

---

## Example

### Input

```text
mat =
[
 [1,1,1,1,1,1,1,1,1,1],
 [1,1,0,1,1,0,1,1,0,1],
 [1,1,1,1,1,1,1,1,1,1]
]

Source = (0,0)
Destination = (1,7)
```

### Output

```text
24
```

---

### Input

```text
mat =
[
 [1,0,0,1,0],
 [0,0,0,1,0],
 [0,1,1,0,0]
]

Source = (0,3)
Destination = (2,2)
```

### Output

```text
-1
```

---

# Intuition

Unlike the shortest path problem, here we must find the **maximum path length** without revisiting any cell.

Since

- `n,m ≤ 10`

the matrix has at most

```
100 cells
```

which allows us to perform **DFS + Backtracking**.

At every cell we:

- mark it visited
- explore every possible direction
- keep the longest answer
- backtrack by unmarking the cell

This explores every simple path exactly once.

---

# Algorithm

1. If source or destination is blocked → return -1.
2. Start DFS from the source.
3. If destination is reached → return 0.
4. Mark current cell visited.
5. Explore all four directions.
6. Ignore
    - blocked cells
    - visited cells
    - out-of-bound cells
7. Take the maximum distance returned.
8. Backtrack.
9. If no direction reaches destination → return -1.
10. Else return `1 + longest distance`.

---

# Dry Run

Matrix

```text
1 1 1
1 0 1
1 1 1
```

Source

```text
(0,0)
```

Destination

```text
(2,2)
```

DFS explores

```
(0,0)
   |
(1,0)
   |
(2,0)
   |
(2,1)
   |
(2,2)
```

Length

```
4
```

Backtracks.

Now tries

```
(0,0)
   |
(0,1)
   |
(0,2)
   |
(1,2)
   |
(2,2)
```

Length

```
4
```

DFS checks every possible path and returns the maximum.

---

# Correctness

We prove that the algorithm always returns the longest valid path.

### Base Case

When DFS reaches the destination,

```
distance = 0
```

which is correct.

---

### Recursive Step

From every cell,

the algorithm recursively computes the longest path from every valid neighboring cell.

Since every possible move is explored,

the maximum of those paths is the longest continuation.

Adding one accounts for the current move.

---

### Backtracking

After recursion,

the current cell is unmarked.

Therefore every possible simple path is explored independently.

---

Hence the algorithm always finds the longest possible route.

---

# Complexity Analysis

Let

```
N = n × m
```

In the worst case, DFS explores every simple path.

**Time Complexity**

```text
O(4^N)
```

(Exponential)

Since

```
N ≤ 100
```

and the constraints are intentionally small, this solution is acceptable.

**Space Complexity**

```text
O(N)
```

for the recursion stack and visited array.

---

# Java Solution

```java
class Solution {

    int n, m;
    int xd, yd;
    int[][] mat;

    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    public int longestPath(int[][] mat, int xs, int ys, int xd, int yd) {

        if (mat[xs][ys] == 0 || mat[xd][yd] == 0)
            return -1;

        this.mat = mat;
        this.n = mat.length;
        this.m = mat[0].length;
        this.xd = xd;
        this.yd = yd;

        boolean[][] vis = new boolean[n][m];

        return dfs(xs, ys, vis);
    }

    private int dfs(int r, int c, boolean[][] vis) {

        if (r == xd && c == yd)
            return 0;

        vis[r][c] = true;

        int longest = -1;

        for (int k = 0; k < 4; k++) {

            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                continue;

            if (vis[nr][nc] || mat[nr][nc] == 0)
                continue;

            longest = Math.max(longest, dfs(nr, nc, vis));
        }

        vis[r][c] = false;

        if (longest == -1)
            return -1;

        return longest + 1;
    }
}
```

---

# Key Observations

- This is **not** a shortest path problem.
- BFS cannot find the longest simple path.
- DFS + Backtracking is required.
- Every cell is visited at most once in a single path.
- Backtracking allows exploration of all possible valid paths.
- Return **-1** if no path reaches the destination.

---

# Tags

- Matrix
- DFS
- Backtracking
- Recursion
- Graph Traversal