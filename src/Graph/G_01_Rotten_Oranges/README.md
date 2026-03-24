# 🍊 Rotten Oranges (Multi-Source BFS)



The goal is to determine the **minimum time required to rot all fresh oranges** in a grid.

---

## ❓ Problem Statement

You are given a 2D grid where:

- `0` → Empty cell
- `1` → Fresh orange
- `2` → Rotten orange

Every minute, any fresh orange adjacent (up, down, left, right) to a rotten orange becomes rotten.

👉 Return the **minimum time required to rot all oranges**.  
👉 If it is impossible, return `-1`.

---

## 🧪 Example


Input:
mat = [
[2, 1, 0, 2, 1],
[1, 0, 1, 2, 1],
[1, 0, 0, 2, 1]
]


---

### 🔍 Process

- Initially rotten oranges spread rot simultaneously
- Each level in BFS represents **1 minute**

---

### ✅ Output


2


---

## 🧠 Intuition

- This is a **multi-source BFS problem**
- All rotten oranges act as **starting points**
- Spread happens level-by-level → like **wave propagation**

---

### 🔥 Key Idea

- Push all rotten oranges into a queue initially
- Perform BFS:
    - Each level = **1 minute**
    - Rot adjacent fresh oranges

---

## ⚙️ Approach

1. Traverse grid and:
    - Add all rotten oranges (`2`) to queue
    - Mark them visited

2. Use BFS:
    - Process nodes level by level
    - For each rotten orange:
        - Check 4 directions
        - If fresh orange found → rot it and push to queue

3. Track time:
    - Increase time only if at least one orange rots in that level

4. Final check:
    - If any fresh orange remains → return `-1`

---

## 💻 Java Code

```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static int Rotten_oranges(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        return bfs(mat, n, m);
    }

    static int bfs(int[][] mat, int n, int m) {

        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // Step 1: Add all rotten oranges to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    queue.add(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }

        // Directions (right, down, left, up)
        int[] row = {0, 1, 0, -1};
        int[] col = {1, 0, -1, 0};

        int time = 0;

        // BFS traversal
        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean rotted = false;

            for (int i = 0; i < size; i++) {

                Pair current = queue.poll();
                int r = current.row;
                int c = current.col;

                for (int d = 0; d < 4; d++) {
                    int newRow = r + row[d];
                    int newCol = c + col[d];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {

                        if (mat[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                            visited[newRow][newCol] = true;
                            rotted = true;
                            queue.add(new Pair(newRow, newCol));
                        }
                    }
                }
            }

            // Increase time only if some orange rotted
            if (rotted) time++;
        }

        // Final check for any fresh orange
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 && !visited[i][j]) {
                    return -1;
                }
            }
        }

        return time;
    }

    static class Pair {
        int row, col;

        Pair(int r, int c) {
            row = r;
            col = c;
        }
    }
}
``` 

🔍 Dry Run (Quick)

Initial queue contains all rotten oranges:

[(0,0), (0,3), (1,3), (2,3)]

Minute 1:

Spread rot to adjacent fresh oranges

Minute 2:

Spread continues

No more fresh oranges → stop

✔ Total time = 2

🚨 Edge Cases
No fresh oranges → return 0
No rotten oranges → return -1
Isolated fresh oranges → return -1
Empty grid → return 0
⏱ Complexity Analysis
Time Complexity: O(N × M)
Space Complexity: O(N × M)

Each cell is visited at most once.

🔥 Key Takeaways
This is a multi-source BFS
BFS ensures minimum time
Each level = 1 unit of time
Use queue + visited array
🚀 Bonus Insight

This problem is similar to:

Flood Fill Algorithm
Spread of infection problems
Shortest path in unweighted grid
Multi-source BFS problems