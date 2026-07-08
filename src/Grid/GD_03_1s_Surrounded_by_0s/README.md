# 1s Surrounded by 0s

## Intuition

A `1` can move out of the grid **only if** it is connected (4-directionally) to a `1` on the boundary.

Instead of checking every `1` individually, we do the opposite:

- Start from all boundary `1`s.
- Perform **Multi-Source BFS** to mark every reachable `1`.
- After BFS, every unvisited `1` is completely surrounded by `0`s.
- Count those cells.

---

## Approach

1. Create a `visited` matrix.
2. Insert all boundary `1`s into a queue.
3. Perform BFS in four directions.
4. Mark every connected `1` as visited.
5. Traverse the entire grid and count the `1`s that were never visited.

---

## Algorithm

1. Push every boundary `1` into the queue.
2. Mark them as visited.
3. While the queue is not empty:
    - Remove the front cell.
    - Visit all four neighbours.
    - If a neighbour is inside the grid, equals `1`, and is unvisited:
        - Mark it visited.
        - Push it into the queue.
4. Finally count every `1` that is still unvisited.
5. Return the count.

---

## Dry Run

### Input

```text
0 0 0 0
1 0 1 0
0 1 1 0
0 0 0 0
```

Boundary `1`

```text
(1,0)
```

BFS visits only

```text
(1,0)
```

Remaining unvisited `1`s

```text
(1,2)
(2,1)
(2,2)
```

Answer

```text
3
```

---

## Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    int cntOnes(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        Queue<Pair> q = new LinkedList<>();

        // Add all boundary 1's

        for (int i = 0; i < n; i++) {

            if (grid[i][0] == 1 && !vis[i][0]) {
                vis[i][0] = true;
                q.offer(new Pair(i, 0));
            }

            if (grid[i][m - 1] == 1 && !vis[i][m - 1]) {
                vis[i][m - 1] = true;
                q.offer(new Pair(i, m - 1));
            }
        }

        for (int j = 0; j < m; j++) {

            if (grid[0][j] == 1 && !vis[0][j]) {
                vis[0][j] = true;
                q.offer(new Pair(0, j));
            }

            if (grid[n - 1][j] == 1 && !vis[n - 1][j]) {
                vis[n - 1][j] = true;
                q.offer(new Pair(n - 1, j));
            }
        }

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        while (!q.isEmpty()) {

            Pair cur = q.poll();

            for (int k = 0; k < 4; k++) {

                int nr = cur.row + dr[k];
                int nc = cur.col + dc[k];

                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < m &&
                    !vis[nr][nc] &&
                    grid[nr][nc] == 1) {

                    vis[nr][nc] = true;
                    q.offer(new Pair(nr, nc));
                }
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 1 && !vis[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    static class Pair {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

---

## Complexity Analysis

### Time Complexity

- Every cell is processed at most once during BFS.

**Time:** `O(N × M)`

### Space Complexity

- `visited` matrix → `O(N × M)`
- Queue (worst case) → `O(N × M)`

**Space:** `O(N × M)`

---

## Why Multi-Source BFS?

Instead of running DFS/BFS from every `1`, we start from **all escapable cells (boundary `1`s)** simultaneously.

This marks every cell that can escape in one traversal.

Finally, the only `1`s left unvisited are those completely surrounded by `0`s, which is exactly what the problem asks us to count.