# Largest Unblocked Submatrix

## Problem Statement

You are given an `n × m` grid and a list of blocked cells.

Each blocked cell blocks:

- Its entire row.
- Its entire column.

Your task is to determine the area of the largest continuous rectangle that remains completely unblocked.

**Constraints**

- `1 ≤ n, m ≤ 10^4`
- `0 ≤ k ≤ min(n, m)`
- No two blocked cells lie in the same row or column.

---

# Goal

Find the maximum possible area of a continuous rectangle consisting only of unblocked rows and unblocked columns.

---

# Intuition

Instead of thinking about individual cells, notice an important observation:

- Once a row contains a blocked cell, the **entire row becomes unusable**.
- Once a column contains a blocked cell, the **entire column becomes unusable**.

Therefore,

The problem no longer depends on cells.

It becomes:

- Find the longest consecutive sequence of usable rows.
- Find the longest consecutive sequence of usable columns.

The largest rectangle is simply

```
Longest Free Rows × Longest Free Columns
```

---

# Key Idea

Maintain two boolean arrays.

```
row[i]
```

Indicates whether row `i` is blocked.

```
col[j]
```

Indicates whether column `j` is blocked.

Then scan each array once.

Whenever a blocked row/column appears:

- Reset current streak.

Otherwise:

- Increase current streak.
- Update maximum streak.

Finally,

```
Answer =
Longest Row Gap × Longest Column Gap
```

---

# Thought Process

### Step 1

Create two boolean arrays.

```
row[]
col[]
```

Initially every row and column is free.

---

### Step 2

For every blocked position

```
(r, c)
```

mark

```
row[r-1] = true
col[c-1] = true
```

Now every blocked row and blocked column is known.

---

### Step 3

Traverse the row array.

Maintain

```
cur_row
```

If current row is blocked

```
Reset cur_row = 0
```

Else

```
Increase cur_row
Update maximum
```

This gives the longest continuous free row segment.

---

### Step 4

Repeat exactly the same process for columns.

Obtain

```
Longest Free Columns
```

---

### Step 5

Multiply both values.

That rectangle is the largest possible unblocked rectangle.

---

# Code

```java
int largestArea(int n, int m, int[][] arr) {

    boolean[] row = new boolean[n];
    boolean[] col = new boolean[m];

    for (int[] a : arr) {
        row[a[0] - 1] = true;
        col[a[1] - 1] = true;
    }

    int maxRow = 0;
    int curRow = 0;

    for (int i = 0; i < n; i++) {
        if (row[i]) {
            curRow = 0;
        } else {
            curRow++;
            maxRow = Math.max(maxRow, curRow);
        }
    }

    int maxCol = 0;
    int curCol = 0;

    for (int i = 0; i < m; i++) {
        if (col[i]) {
            curCol = 0;
        } else {
            curCol++;
            maxCol = Math.max(maxCol, curCol);
        }
    }

    return maxRow * maxCol;
}
```

---

# Dry Run

### Input

```
n = 5
m = 5

Blocked Cells

(2,3)
(5,1)
```

---

### Step 1

Blocked rows

```
Row 1 -> Free
Row 2 -> Blocked
Row 3 -> Free
Row 4 -> Free
Row 5 -> Blocked
```

Represented as

```
F B F F B
```

---

### Step 2

Find longest free row segment

```
Row 1

Current = 1
Maximum = 1

Row 2

Blocked
Current = 0

Row 3

Current = 1

Row 4

Current = 2
Maximum = 2

Row 5

Blocked
Current = 0
```

Longest free rows

```
2
```

---

### Step 3

Blocked columns

```
Column 1 -> Blocked
Column 2 -> Free
Column 3 -> Blocked
Column 4 -> Free
Column 5 -> Free
```

Represented as

```
B F B F F
```

---

### Step 4

Find longest free column segment

```
Column 1

Blocked

Current = 0

Column 2

Current = 1

Maximum = 1

Column 3

Blocked

Current = 0

Column 4

Current = 1

Column 5

Current = 2

Maximum = 2
```

Longest free columns

```
2
```

---

### Step 5

Largest rectangle

```
Area

=
Longest Free Rows × Longest Free Columns

= 2 × 2

= 4
```

Final Answer

```
4
```

---

# Logic Flow

```
Start
   │
   ▼
Create blocked row array
Create blocked column array
   │
   ▼
Mark every blocked row
Mark every blocked column
   │
   ▼
Scan rows
Find longest continuous free rows
   │
   ▼
Scan columns
Find longest continuous free columns
   │
   ▼
Multiply both lengths
   │
   ▼
Return maximum area
```

---

# Complexity

### Time Complexity

```
O(k + n + m)
```

Where

- `k` = number of blocked cells
- `n` = number of rows
- `m` = number of columns

Each row and column is scanned only once.

---

### Space Complexity

```
O(n + m)
```

Used for

- row[]
- col[]

---

# Key Takeaways

- Entire blocked rows and columns eliminate many cells at once.
- There is no need to construct the entire grid.
- The problem reduces to finding the longest consecutive free rows and columns.
- Consecutive streak calculation is a common array technique.
- Multiplying the two maximum streaks directly gives the largest rectangle.

---

# Most Important Insight

The actual positions of free cells do not matter.

Only the longest continuous sequence of unblocked rows and unblocked columns determines the answer.

Therefore,

```
Largest Rectangle

=

Longest Free Row Segment

×

Longest Free Column Segment
```

This transforms a seemingly two-dimensional grid problem into two simple one-dimensional scans.

---

# Summary

- Mark blocked rows and blocked columns.
- Scan rows to find the longest consecutive free segment.
- Scan columns to find the longest consecutive free segment.
- Multiply both maximum lengths.
- Return the resulting area.

This approach avoids building the entire grid and achieves an efficient solution with:

- **Time Complexity:** `O(k + n + m)`
- **Space Complexity:** `O(n + m)`