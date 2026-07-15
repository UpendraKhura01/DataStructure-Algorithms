# 3989. Maximum Consistent Columns in a Grid

**Difficulty:** Hard

You are given an `m × n` integer matrix `grid` and an integer `limit`.

You may remove any number of columns, but **at least one column must remain**. The relative order of the remaining columns must stay unchanged.

A grid is called **consistent** if for every row, every pair of **adjacent remaining columns** satisfies

```text
|grid[i][b] - grid[i][a]| ≤ limit
```

where `a` and `b` are consecutive remaining columns.

Return the **maximum number of columns** that can remain while keeping the grid consistent.

---

## Example 1

**Input**

```text
grid = [[-2,0,3]]
limit = 2
```

**Output**

```text
2
```

**Explanation**

Keep columns `0` and `1`.

```text
|-2 - 0| = 2 ≤ limit
```

So the answer is **2**.

---

## Example 2

**Input**

```text
grid = [[1,-1,1],
        [2, 2,2]]

limit = 1
```

**Output**

```text
2
```

**Explanation**

Keep columns `0` and `2`.

For every row,

```text
|1-1| = 0
|2-2| = 0
```

Both satisfy the condition.

---

## Example 3

**Input**

```text
grid = [[-5,5]]
limit = 9
```

**Output**

```text
1
```

**Explanation**

```text
|-5-5| = 10 > 9
```

Both columns cannot coexist.

---

## Constraints

- `1 ≤ m ≤ 250`
- `1 ≤ n ≤ 250`
- `-10^5 ≤ grid[i][j] ≤ 10^5`
- `0 ≤ limit ≤ 10^5`

---

# Key Observation

Suppose we keep columns

```text
c1 < c2 < c3 < ...
```

Only **adjacent kept columns** need to satisfy

```text
|grid[row][cj] - grid[row][cj-1]| ≤ limit
```

Therefore, if two columns are compatible, we can directly connect them.

This naturally forms a **Directed Acyclic Graph (DAG)**:

- Every column is a node.
- Edge

```text
i → j
```

exists if

```text
i < j
```

and every row satisfies

```text
|grid[row][j] - grid[row][i]| ≤ limit
```

The problem becomes:

> Find the **Longest Path in the DAG**.

---

# Dynamic Programming

Let

```text
dp[i]
```

denote

> Maximum number of columns that can be kept starting from column `i`.

Transition:

```text
dp[i] = 1
```

Try every later column.

If

```text
i → j
```

is valid,

```text
dp[i] = max(dp[i], 1 + dp[j])
```

The answer is

```text
max(dp[i])
```

---

# Helper Function

Two columns are compatible only if **every row** satisfies

```text
abs(grid[row][next] - grid[row][current]) ≤ limit
```

If any row violates it,

the transition is impossible.

---

# Memoization Approach

## Algorithm

For every column

- compute the longest valid chain beginning there.

Memoize the answer to avoid recomputation.

---

## Dry Run

### Input

```text
grid =

1 -1 1
2  2 2

limit = 1
```

Possible transitions

```text
0 → 1 ❌

Row 0

|1-(-1)| = 2

> limit
```

```text
0 → 2 ✔

Row0

|1-1|=0

Row1

|2-2|=0
```

```text
1 → 2 ❌
```

DP

```text
dp[2]=1

dp[1]=1

dp[0]=1+dp[2]=2
```

Answer

```text
2
```

---

# Tabulation

Instead of recursion, process columns from right to left.

Since every transition goes to a larger index,

future states are already computed.

For every column

```text
dp[col]=1
```

Check all later columns.

If compatible,

```text
dp[col]=max(dp[col],1+dp[next])
```

Take the maximum over all columns.

---

# Correctness Proof

We prove by induction.

Suppose `dp[next]` correctly stores the longest valid sequence beginning at `next`.

For column `cur`, every valid next column is examined.

Choosing

```text
1 + dp[next]
```

extends the optimal sequence beginning at `next`.

Taking the maximum over all valid transitions gives the optimal sequence beginning at `cur`.

Therefore every `dp[cur]` is correct.

Hence the global maximum is also correct.

---

# Complexity Analysis

Let

- `R = rows`
- `C = columns`

Checking compatibility costs

```text
O(R)
```

There are

```text
O(C²)
```

pairs.

Therefore

**Time Complexity**

```text
O(R × C²)
```

With constraints

```text
250 × 250²
≈ 1.56 × 10⁷
```

which is acceptable.

**Space Complexity**

```text
O(C)
```

---

# Java Solution (Memoization)

```java
class Solution {

    int[][] grid;
    int rows, cols, limit;
    int[] dp;

    int maxConsistentColumns(int[][] grid, int limit) {

        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.limit = limit;

        dp = new int[cols];
        Arrays.fill(dp, -1);

        int ans = 1;

        for (int col = 0; col < cols; col++) {
            ans = Math.max(ans, solve(col));
        }

        return ans;
    }

    private int solve(int col) {

        if (dp[col] != -1)
            return dp[col];

        int best = 1;

        for (int next = col + 1; next < cols; next++) {

            if (compatible(col, next)) {
                best = Math.max(best, 1 + solve(next));
            }
        }

        return dp[col] = best;
    }

    private boolean compatible(int c1, int c2) {

        for (int row = 0; row < rows; row++) {

            if (Math.abs(grid[row][c2] - grid[row][c1]) > limit)
                return false;
        }

        return true;
    }
}
```

---

# Java Solution (Tabulation)

```java
class Solution {

    int tabulation(int[][] grid, int limit) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[] dp = new int[cols];

        int ans = 1;

        for (int col = cols - 1; col >= 0; col--) {

            dp[col] = 1;

            for (int next = col + 1; next < cols; next++) {

                boolean ok = true;

                for (int row = 0; row < rows; row++) {

                    if (Math.abs(grid[row][next] - grid[row][col]) > limit) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    dp[col] = Math.max(dp[col], 1 + dp[next]);
                }
            }

            ans = Math.max(ans, dp[col]);
        }

        return ans;
    }
}
```