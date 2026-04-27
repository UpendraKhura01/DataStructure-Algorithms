# 🛣️ Check if There is a Valid Path in a Grid

## 📌 Problem Statement

You are given a grid where each cell represents a **street type (1–6)**.

Each type allows movement in specific directions.

You start at:

    (0,0)

You need to reach:

    (n-1, m-1)

---

## ⚙️ Movement Rule

You can move from one cell → next cell ONLY IF:

1. Current cell has a path toward next cell
2. Next cell has a path back toward current cell

---

## 🎯 Goal

Return:

    true  → if valid path exists
    false → otherwise

---

# 💡 Intuition

This is a **graph traversal problem (DFS)**

Each cell = node  
Valid road = edge

But edge exists only if:

    BOTH cells agree to connect

---

# 🔥 Key Idea

👉 Not all adjacent cells are connected

We must check:

    current → next is allowed
    AND
    next → current is allowed

---

# 🧠 Thought Process

For each cell:

1. Get all possible directions it can go
2. Try each direction
3. Check:
   - inside grid
   - not visited
   - connection is valid (both sides match)
4. Continue DFS

---

# 🔁 Street Type Mapping

```
Type 1 → left, right
Type 2 → up, down
Type 3 → left, down
Type 4 → right, down
Type 5 → left, up
Type 6 → right, up
```

---

# 🚀 Code (Functions Only)

```java
static boolean hasValidPath(int[][] grid) {

    int n = grid.length;
    int m = grid[0].length;

    int[][] vis = new int[n][m];

    return helper(0, 0, grid, n, m, vis);
}
```

---

## 🔹 DFS

```java
static boolean helper(int i, int j, int[][] grid,
                      int n, int m, int[][] vis) {

    if (i == n - 1 && j == m - 1)
        return true;

    vis[i][j] = 1;

    int[][] newc = next(i, j, grid);

    for (int[] a : newc) {

        int ni = a[0];
        int nj = a[1];

        if (ni < 0 || nj < 0 || ni >= n || nj >= m)
            continue;

        if (vis[ni][nj] == 1)
            continue;

        if (!cango(i, j, ni, nj, grid))
            continue;

        if (helper(ni, nj, grid, n, m, vis))
            return true;
    }

    return false;
}
```

---

## 🔹 Get Next Moves

```java
static int[][] next(int i, int j, int[][] grid) {

    int num = grid[i][j];

    if (num == 1) return new int[][]{{i, j+1}, {i, j-1}};
    if (num == 2) return new int[][]{{i+1, j}, {i-1, j}};
    if (num == 3) return new int[][]{{i, j-1}, {i+1, j}};
    if (num == 4) return new int[][]{{i, j+1}, {i+1, j}};
    if (num == 5) return new int[][]{{i, j-1}, {i-1, j}};
    if (num == 6) return new int[][]{{i, j+1}, {i-1, j}};

    return new int[][]{{-1,-1}};
}
```

---

## 🔹 Check Bidirectional Connection

```java
static boolean cango(int i, int j, int ni, int nj, int[][] grid) {

    int[][] newc = next(ni, nj, grid);

    for (int[] a : newc) {
        if (a[0] == i && a[1] == j)
            return true;
    }

    return false;
}
```

---

# 🧪 Dry Run

Grid:

    [2,4,3]
    [6,5,2]

---

Path:

    (0,0)
      ↓
    (1,0)
      →
    (1,1)
      ↑
    (0,1)
      →
    (0,2)
      ↓
    (1,2)

---

Output:

    true

---

# ❌ Invalid Case Example

    [1,2,1]
    [1,2,1]

From (0,0):

    Can only go left-right

But next cell doesn't allow connection back

---

Output:

    false

---

# 🔁 DFS Flow

    Start (0,0)
        ↓
    Explore valid directions
        ↓
    Check connection validity
        ↓
    Move forward
        ↓
    If reach end → true

---

# 📊 Complexity

Time:

    O(n * m)

Space:

    O(n * m)

---

# 🎯 Key Takeaways

- Treat grid as graph
- Check BOTH directions for connection
- Use DFS + visited array
- Street type defines edges

---

# 🔥 Most Important Insight

    Movement is valid ONLY if both cells agree

---

# 🏁 Summary

To solve:

    DFS + valid connection check + visited tracking