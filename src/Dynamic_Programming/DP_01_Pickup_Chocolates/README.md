# 🍫 Pickup Maximum Chocolates by Two Robots

## 📌 Problem Statement

You are given a grid of size `n x m` representing chocolates, where `grid[i][j]` indicates the number of chocolates in cell `(i, j)`.

Two robots start simultaneously from the **top row**:

- Robot 1 starts at `(0, 0)` (first column)
- Robot 2 starts at `(0, m-1)` (last column)

Rules:

1. Both robots can move to **row + 1** in the next step.
2. They can move **left, stay, or right** in the next column (i.e., `c ± 1`).
3. If both robots land on the same cell, only **one of them picks the chocolates**.
4. Collect maximum chocolates possible by the time both robots reach the **last row**.

**Goal:** Return the maximum chocolates both robots can collect.

---

## 💡 Intuition

- This is a **classic 2-robot DP problem**.
- Both robots move **simultaneously** from top to bottom.
- Each robot has **3 choices** per step (`left`, `down`, `right`).
- If we try all possibilities using recursion, the time complexity becomes **exponential** → so we need **memoization**.
- Hence, we use **3D DP** to store intermediate results for `(row, col1, col2)`.

---

## 🧠 Thought Process

### 1. Why 3 parameters for recursion?

The recursive function is defined as:

```java
helper(r, c1, c2, grid, n, m, dp)
```

- `r` → current row
- `c1` → column of Robot 1
- `c2` → column of Robot 2

**Reason:**

- Both robots’ positions in a row determine the **maximum chocolates they can pick in the rest of the grid**.
- Therefore, the DP table is **3D**: `dp[row][col1][col2]`.

---

### 2. Base case

```java
if (r == n-1) {
    if (c1 == c2) return grid[r][c1];
    else return grid[r][c1] + grid[r][c2];
}
```

- When robots reach **last row**, they pick chocolates from their cells.
- If both are on the **same cell**, count chocolates only **once**.

---

### 3. Out-of-bound check

```java
if (c1 < 0 || c2 < 0 || c1 >= m || c2 >= m) return -1;
```

- If any robot moves **outside the grid**, return `-1` → invalid path.

---

### 4. Recursive transition

```java
int max = Integer.MIN_VALUE;

for (int i = -1; i <= 1; i++) {         // robot1's move
    for (int j = -1; j <= 1; j++) {     // robot2's move
        int cur = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        int future = helper(r+1, c1+i, c2+j, grid, n, m, dp);
        if (future == -1) continue;
        cur += future;
        max = Math.max(max, cur);
    }
}
dp[r][c1][c2] = max;
```

**Explanation:**

- Both robots have **3 choices**: move left (`-1`), down (`0`), right (`+1`) in the next row.
- So total **9 combinations** per step (`3 x 3`).
- `cur` → chocolates collected **in current row**.
- `future` → maximum chocolates **from next row onward** (recursive call).
- `max` → maximum value among all 9 options.

This is classic **Top-Down DP with memoization**.

---

### 5. DP Memoization

- `dp[r][c1][c2]` stores **maximum chocolates from row r with robot1 at c1 and robot2 at c2**.
- If already computed, we **return dp[r][c1][c2]** → avoids recomputation.

---

## ⚙️ Algorithm Steps

1. Create `dp[n][m][m]` and fill with `-1`.
2. Call `helper(0, 0, m-1, grid, n, m, dp)` → robots start at top-left and top-right.
3. In recursion:
    - Check **base case** (last row)
    - Check **out-of-bound**
    - Try **all 9 moves**
    - Compute chocolates for **current row + future rows**
    - Save in **dp**
4. Return **dp[0][0][m-1]** → maximum chocolates

---

## 🚀 Code (Java)

```java
package Dynamic_Programming.DP_01_Pickup_Chocolates;

public class solution {
    public static void main(String[] args) {
        int[][] grid = {
                {4, 1, 2},
                {3, 6, 1},
                {1, 6, 6},
                {3, 1, 2}
        };
        System.out.println("ans is " + maxChocolate(grid));
    }

    static int maxChocolate(int grid[][]) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < m; k++)
                    dp[i][j][k] = -1;

        return helper(0, 0, m - 1, grid, n, m, dp);
    }

    static int helper(int r, int c1, int c2, int[][] grid, int n, int m, int[][][] dp) {
        if (c1 < 0 || c2 < 0 || c1 >= m || c2 >= m) return -1;

        if (r == n - 1)
            return (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];

        if (dp[r][c1][c2] != -1) return dp[r][c1][c2];

        int max = Integer.MIN_VALUE;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int cur = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
                int future = helper(r + 1, c1 + i, c2 + j, grid, n, m, dp);
                if (future == -1) continue;
                cur += future;
                max = Math.max(max, cur);
            }
        }

        dp[r][c1][c2] = max;
        return max;
    }
}
```

---

## 🧪 Dry Run Example

Grid:

```
4 1 2
3 6 1
1 6 6
3 1 2
```

1. Start: Robot1 `(0,0)`, Robot2 `(0,2)` → chocolates = 4 + 2 = 6
2. Try all 9 moves for next row (r=1):
    - Robot1 moves to 0,1,2
    - Robot2 moves to 1,2,3 (max 2)
3. For each combination, recursively compute max chocolates from r=1 → r=3
4. Combine **current row + future row chocolates**
5. Return **maximum path sum** → `28`

**Key Point:** DP ensures **no recomputation** for same `(r, c1, c2)`.

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n * m * m * 9) = O(n * m^2) |
| Space | O(n * m * m) for DP |

- `n` = rows, `m` = columns
- Each cell `(r, c1, c2)` is computed **once**
- 9 transitions per step (robot1 3 moves x robot2 3 moves)

---

## 🎯 Key Takeaways

- Use **recursion + memoization** for multi-robot/grid DP problems.
- 3D DP → row + 2 columns (robot positions)
- Avoid double counting when robots are on the same cell.
- This is a **Top-Down DP variant of matrix traversal**.

---

## 👨‍💻 References

- Dynamic Programming Grid Problems
- Multi-agent path DP
- LeetCode “Cherry Pickup II” problem (similar concept)