# 🔢 Minimum Operations to Make Uni-Value Grid

## 📌 Problem Statement

Given a 2D grid and an integer `x`.

In one operation, you can:

    Add x to any cell
    OR
    Subtract x from any cell

---

## 🎯 Goal

Make all grid values equal using the minimum number of operations.

If impossible, return:

    -1

---

# 💡 Intuition

Since we can only add or subtract by `x`, two numbers can become equal only if:

    They have the same remainder when divided by x

So first check:

    grid value % x

If all remainders are not same:

    impossible

---

# 🔥 Key Idea

After flattening the grid:

    Convert 2D grid → 1D array

Then choose the best target value.

For minimum total absolute difference:

    Median is the best target

---

# 🧠 Why Median?

To minimize:

    |a1 - target| + |a2 - target| + ... + |an - target|

The best `target` is:

    median

So we sort the values and choose the middle element.

---

# 🚀 Code (Function Only)

```java
static int minOperations(int[][] grid, int x) {

    int m = grid.length;
    int n = grid[0].length;

    int[] flat = new int[m * n];

    int idx = 0;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            flat[idx++] = grid[i][j];
        }
    }

    Arrays.sort(flat);

    int remain = flat[0] % x;

    for (int value : flat) {
        if (value % x != remain) {
            return -1;
        }
    }

    int total = m * n;
    int median = flat[total / 2];

    int ans = 0;

    for (int value : flat) {
        ans += Math.abs(value - median) / x;
    }

    return ans;
}
```

---

# 🧪 Dry Run

Input:

    grid = [
      [2, 4],
      [6, 8]
    ]

    x = 2

---

Flatten:

    [2, 4, 6, 8]

---

Check remainders:

    2 % 2 = 0
    4 % 2 = 0
    6 % 2 = 0
    8 % 2 = 0

All same → possible

---

Median:

    flat[4 / 2] = flat[2] = 6

---

Operations:

    2 → 6 = 2 operations
    4 → 6 = 1 operation
    6 → 6 = 0 operation
    8 → 6 = 1 operation

Total:

    4

---

# ❌ Impossible Case

Input:

    grid = [
      [1, 2],
      [3, 4]
    ]

    x = 2

Remainders:

    1 % 2 = 1
    2 % 2 = 0

Remainders differ.

Output:

    -1

---

# 📊 Complexity

Time:

    O(m * n log(m * n))

Space:

    O(m * n)

---

# 🎯 Key Takeaways

- Flatten grid into 1D array
- All values must have same remainder modulo `x`
- Median minimizes total absolute distance
- Operations = distance / x

---

# 🔥 Most Important Insight

    If values do not have same remainder modulo x, they can never become equal.

---

# 🏁 Summary

To make grid uni-value:

    Flatten grid
    Check modulo condition
    Sort
    Pick median
    Count operations