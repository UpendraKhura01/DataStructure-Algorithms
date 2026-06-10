# 🧩 Maximum Score From Grid Operations

## 📌 Problem Statement

Given an `n x n` grid of integers.

We perform operations column by column.  
For every column, we can choose a height `h`, meaning some cells from the top part of that column are considered painted/selected.

The score is gained based on the difference between the selected heights of adjacent columns.

The given code solves the problem using:

    1. Prefix sum for fast column sum calculation
    2. Dynamic Programming
    3. Memoization
    4. Tabulation

---

## 🎯 Goal

Find the maximum score possible from the grid operations.

We need to decide the best height for every column so that the total score becomes maximum.

---

## 💡 Intuition

For every column, we need to choose how many cells are affected.

Suppose the previous column had height:

    prevheight

And current column chooses height:

    h

Now there are two important cases:

    1. Current height is greater than previous height
    2. Previous height is greater than current height

The score comes from the cells that become exposed between two neighboring columns.

Instead of recalculating cell sums again and again, we use prefix sums column-wise.

---

## 🔥 Key Idea

For each column, the DP state keeps track of:

    prevheight = height chosen for previous column
    col        = current column
    prevused   = whether previous column contribution has already been used or not

DP state:

    dp[prevheight][col][prevused]

Meaning:

    Maximum score possible from column `col` to the end,
    when previous column had height `prevheight`,
    and previous contribution status is `prevused`.

---

## 🧠 Thought Process

The main challenge is that the score of one column depends on the height chosen in the previous column.

So while standing at column `col`, we try every possible height:

    h = 0 to n

For each height `h`, we calculate two possible scores:

    1. prevscore
    2. curscore

---

### Case 1: Current height is greater than previous height

If:

    h > prevheight

Then the extra part in the previous column can contribute.

Condition used:

    if (prevused == 0 && col - 1 >= 0 && h > prevheight)

Score added:

    prefix[h][col] - prefix[prevheight][col]

This means:

    Add cells from row prevheight to h - 1
    in the previous column.

---

### Case 2: Previous height is greater than current height

If:

    prevheight > h

Then some part of the current column contributes.

Condition used:

    if (prevheight > h)

Score added:

    prefix[prevheight][col + 1] - prefix[h][col + 1]

This means:

    Add cells from row h to prevheight - 1
    in the current column.

---

For every height `h`, there are two choices:

    1. Take current column contribution
    2. Skip current column contribution

In code:

    taken = prevscore + curscore + next state with prevused = 1
    skip  = prevscore + next state with prevused = 0

Then we take the maximum.

---

## 💻 Code

```java
static long maximumScore(int[][] grid) {
    int n = grid.length;
    long[][] prefix = new long[n + 1][n + 1];

    for (int j = 1; j <= n; j++) {
        for (int i = 1; i <= n; i++) {
            prefix[i][j] = prefix[i - 1][j] + grid[i - 1][j - 1];
        }
    }

    long[][][] dp = new long[n + 1][n + 1][2];

    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k < 2; k++) {
                dp[i][j][k] = -1;
            }
        }
    }

    return helper(0, 0, 0, grid, prefix, dp, n);
}

static long helper(
    int prevused,
    int prevheight,
    int col,
    int[][] grid,
    long[][] prefix,
    long[][][] dp,
    int n
) {
    if (col == n) {
        return 0;
    }

    if (dp[prevheight][col][prevused] != -1) {
        return dp[prevheight][col][prevused];
    }

    long ans = 0;

    for (int h = 0; h <= n; h++) {
        long curscore = 0;
        long prevscore = 0;

        if (prevused == 0 && col - 1 >= 0 && h > prevheight) {
            prevscore += prefix[h][col] - prefix[prevheight][col];
        }

        if (prevheight > h) {
            curscore = prefix[prevheight][col + 1] - prefix[h][col + 1];
        }

        long taken = prevscore + curscore + helper(1, h, col + 1, grid, prefix, dp, n);
        long skip = prevscore + helper(0, h, col + 1, grid, prefix, dp, n);

        ans = Math.max(ans, Math.max(taken, skip));
    }

    return dp[prevheight][col][prevused] = ans;
}

static long maximumScore2(int[][] grid) {
    int n = grid.length;
    long[][] prefix = new long[n + 1][n + 1];

    for (int j = 1; j <= n; j++) {
        for (int i = 1; i <= n; i++) {
            prefix[i][j] = prefix[i - 1][j] + grid[i - 1][j - 1];
        }
    }

    long[][][] dp = new long[n + 1][n + 1][2];

    for (int r = 0; r <= n; r++) {
        dp[r][n][0] = 0;
        dp[r][n][1] = 0;
    }

    for (int col = n - 1; col >= 0; col--) {
        for (int prevheight = 0; prevheight <= n; prevheight++) {
            for (int prevused = 0; prevused < 2; prevused++) {

                long ans = 0;

                for (int h = 0; h <= n; h++) {
                    long curscore = 0;
                    long prevscore = 0;

                    if (prevused == 0 && col - 1 >= 0 && h > prevheight) {
                        prevscore += prefix[h][col] - prefix[prevheight][col];
                    }

                    if (prevheight > h) {
                        curscore = prefix[prevheight][col + 1] - prefix[h][col + 1];
                    }

                    long taken = prevscore + curscore + dp[h][col + 1][1];
                    long skip = prevscore + dp[h][col + 1][0];

                    ans = Math.max(ans, Math.max(taken, skip));
                }

                dp[prevheight][col][prevused] = ans;
            }
        }
    }

    return dp[0][0][0];
}
```

---

## 🧪 Dry Run

Consider a small grid:

    grid = [
        [1, 2],
        [3, 4]
    ]

Here:

    n = 2

---

Step 1: Build column-wise prefix sum

For column 1:

    prefix[1][1] = 1
    prefix[2][1] = 1 + 3 = 4

For column 2:

    prefix[1][2] = 2
    prefix[2][2] = 2 + 4 = 6

So prefix table becomes:

    prefix[0][1] = 0
    prefix[1][1] = 1
    prefix[2][1] = 4

    prefix[0][2] = 0
    prefix[1][2] = 2
    prefix[2][2] = 6

---

Step 2: Start recursive DP

Initial call:

    helper(prevused = 0, prevheight = 0, col = 0)

Meaning:

    Previous column has height 0
    No previous contribution has been used
    We are currently at column 0

---

Step 3: Try all possible heights for column 0

Possible heights:

    h = 0, 1, 2

Since this is the first column:

    col - 1 < 0

So:

    prevscore = 0

Now calculate `curscore`.

Current condition:

    if (prevheight > h)

But:

    prevheight = 0

So:

    prevheight > h is false for all h

Therefore for first column:

    curscore = 0

The first column only sets up the height for the next column.

---

Step 4: Suppose we choose h = 2 for column 0

Next call becomes:

    helper(prevused = 1, prevheight = 2, col = 1)

Now we are at column 1.

Try heights:

    h = 0, 1, 2

---

For h = 0:

    prevheight = 2
    h = 0

Since:

    prevheight > h

Current column contributes:

    curscore = prefix[prevheight][col + 1] - prefix[h][col + 1]
             = prefix[2][2] - prefix[0][2]
             = 6 - 0
             = 6

Next:

    helper(1, 0, 2) = 0

So:

    taken = 0 + 6 + 0
          = 6

---

For h = 1:

    prevheight = 2
    h = 1

Since:

    prevheight > h

Current column contributes:

    curscore = prefix[2][2] - prefix[1][2]
             = 6 - 2
             = 4

So:

    taken = 4

---

For h = 2:

    prevheight = 2
    h = 2

No height difference:

    curscore = 0

So:

    taken = 0

---

Best from this state:

    max(6, 4, 0) = 6

So one possible best answer is:

    6

---

## 🔁 Logic Flow

    Start
      |
      v
    Read grid size n
      |
      v
    Build column-wise prefix sum
      |
      v
    Create 3D DP table
      |
      v
    State:
        dp[prevheight][col][prevused]
      |
      v
    For every column
      |
      v
    Try every possible height h from 0 to n
      |
      v
    Calculate prevscore if previous column contributes
      |
      v
    Calculate curscore if current column contributes
      |
      v
    Option 1: take current contribution
      |
      v
    Option 2: skip current contribution
      |
      v
    Store maximum answer in DP
      |
      v
    Return dp[0][0][0]

---

## 📊 Complexity

Time Complexity:

    O(n³)

Reason:

    There are O(n² * 2) DP states.
    For every state, we try O(n) heights.

So:

    O(n² * n) = O(n³)

Space Complexity:

    O(n²)

Reason:

    DP table uses:
        (n + 1) * (n + 1) * 2

    Prefix table uses:
        (n + 1) * (n + 1)

So total space is:

    O(n²)

---

## 🎯 Key Takeaways

- This is a grid DP problem.
- The height chosen in one column affects the score in the next column.
- Prefix sum helps calculate column ranges in O(1).
- DP state stores previous height, current column, and usage status.
- Every possible height is tried for every column.
- Memoization and tabulation both solve the same recurrence.

---

## 🔥 Most Important Insight

The score depends only on:

    1. Current column
    2. Previous column height
    3. Whether previous contribution was already used

So the correct DP state is:

    dp[prevheight][col][prevused]

This avoids recalculating the same decisions repeatedly.

---

## 🏁 Summary

To solve this problem:

    1. Build column-wise prefix sums.
    2. Use DP with state:
           previous height, current column, previous-used flag
    3. Try every possible current height.
    4. Calculate score using prefix sums.
    5. Choose max between taking and skipping current contribution.
    6. Return the maximum score.

Efficient solution:

    Time  : O(n³)
    Space : O(n²)