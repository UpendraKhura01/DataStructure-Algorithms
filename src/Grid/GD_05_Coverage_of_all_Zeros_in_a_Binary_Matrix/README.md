# Coverage of all Zeros in a Binary Matrix

## Problem Statement

You are given a binary matrix containing only `0`s and `1`s.

For every cell containing `0`, its **coverage** is determined by checking all four directions:

- Left
- Right
- Up
- Down

For each direction, if there exists **at least one `1` anywhere between that cell and the matrix boundary**, the coverage for that direction is `1`; otherwise, it is `0`.

Return the **sum of the coverage values of all zero cells**.

**Constraints**

- `1 ≤ rows, columns ≤ 100`

---

# Goal

Efficiently compute the total coverage of all `0` cells without repeatedly searching in all four directions for every cell.

---

# Intuition

A brute-force solution would, for every `0`, scan:

- Entire left side
- Entire right side
- Entire upper side
- Entire lower side

This results in a much higher time complexity.

Instead, preprocess the matrix so that for every cell we already know whether a `1` exists in each direction.

This allows every coverage calculation to be performed in constant time.

---

# Key Idea

Create four helper matrices:

```
l[][] → Is there a 1 on the left?

r[][] → Is there a 1 on the right?

u[][] → Is there a 1 above?

d[][] → Is there a 1 below?
```

Each matrix stores either

```
0 → No 1 exists

1 → At least one 1 exists
```

After preprocessing, every zero cell simply adds

```
l + r + u + d
```

---

# Thought Process

### Step 1

Create four matrices

```
l[][]
r[][]
u[][]
d[][]
```

Each has the same dimensions as the original matrix.

---

### Step 2

Process every row from left to right.

Maintain a variable

```
find
```

Initially

```
find = 0
```

Whenever a `1` is encountered,

```
find = 1
```

Every subsequent cell knows whether a `1` exists somewhere to its left.

Store this inside

```
l[][]
```

---

### Step 3

Repeat the same process from

```
Right → Left
```

Store the results inside

```
r[][]
```

---

### Step 4

Traverse every column

```
Top → Bottom
```

Store whether a `1` exists above every cell.

This forms

```
u[][]
```

---

### Step 5

Traverse every column

```
Bottom → Top
```

Store whether a `1` exists below every cell.

This forms

```
d[][]
```

---

### Step 6

Visit every cell.

If it is

```
0
```

its contribution becomes

```
l[i][j]
+
r[i][j]
+
u[i][j]
+
d[i][j]
```

Add this value to the final answer.

---

# Code

```java
int findCoverage(int[][] mat) {

    int n = mat.length;
    int m = mat[0].length;

    int[][] l = new int[n][m];
    int[][] r = new int[n][m];
    int[][] u = new int[n][m];
    int[][] d = new int[n][m];

    // Left
    for (int i = 0; i < n; i++) {
        int find = 0;
        for (int j = 0; j < m; j++) {
            l[i][j] = find;
            if (mat[i][j] == 1)
                find = 1;
        }
    }

    // Right
    for (int i = 0; i < n; i++) {
        int find = 0;
        for (int j = m - 1; j >= 0; j--) {
            r[i][j] = find;
            if (mat[i][j] == 1)
                find = 1;
        }
    }

    // Up
    for (int j = 0; j < m; j++) {
        int find = 0;
        for (int i = 0; i < n; i++) {
            u[i][j] = find;
            if (mat[i][j] == 1)
                find = 1;
        }
    }

    // Down
    for (int j = 0; j < m; j++) {
        int find = 0;
        for (int i = n - 1; i >= 0; i--) {
            d[i][j] = find;
            if (mat[i][j] == 1)
                find = 1;
        }
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mat[i][j] == 0) {
                ans += l[i][j] + r[i][j] + u[i][j] + d[i][j];
            }
        }
    }

    return ans;
}
```

---

# Dry Run

### Input

```
mat =

1 1 1 0
1 0 0 1
```

---

### Step 1

Build Left matrix

```
0 1 1 1
0 1 1 1
```

Explanation

Every cell stores whether a `1` exists somewhere to its left.

---

### Step 2

Build Right matrix

```
1 1 0 0
1 1 1 0
```

---

### Step 3

Build Up matrix

```
0 0 0 0
1 1 1 0
```

---

### Step 4

Build Down matrix

```
1 0 0 1
0 0 0 0
```

---

### Step 5

Calculate coverage

Cell

```
(0,3)
```

```
Left  = 1
Right = 0
Up    = 0
Down  = 1

Coverage = 2
```

---

Cell

```
(1,1)
```

```
Left  = 1
Right = 1
Up    = 1
Down  = 0

Coverage = 3
```

---

Cell

```
(1,2)
```

```
Left  = 1
Right = 1
Up    = 1
Down  = 0

Coverage = 3
```

---

Final Answer

```
2 + 3 + 3

= 8
```

---

# Logic Flow

```
Start
   │
   ▼
Create four helper matrices
   │
   ▼
Scan Left → Right
Build left matrix
   │
   ▼
Scan Right → Left
Build right matrix
   │
   ▼
Scan Top → Bottom
Build upper matrix
   │
   ▼
Scan Bottom → Top
Build lower matrix
   │
   ▼
Visit every zero cell
   │
   ▼
Coverage =
Left + Right + Up + Down
   │
   ▼
Add to answer
   │
   ▼
Return total coverage
```

---

# Complexity

### Time Complexity

```
O(n × m)
```

Reason

- Four preprocessing passes
- One final traversal

Each cell is visited a constant number of times.

---

### Space Complexity

```
O(n × m)
```

Used for

```
Left matrix
Right matrix
Upper matrix
Lower matrix
```

---

# Key Takeaways

- Never search in four directions separately for every zero.
- Preprocessing converts repeated searches into constant-time lookups.
- Directional preprocessing is a common optimization in matrix problems.
- Every helper matrix answers one directional query instantly.
- Total coverage is obtained with only one additional traversal.

---

# Most Important Insight

Instead of asking

```
"Does this zero have a 1 on its left?"
```

for every zero repeatedly,

answer that question once for every cell during preprocessing.

Then each zero's coverage becomes

```
Coverage

=

Left
+
Right
+
Up
+
Down
```

allowing every query to be answered in **O(1)** time.

---

# Summary

- Build four directional helper matrices.
- Each matrix records whether a `1` exists in that direction.
- Traverse the matrix once more.
- For every `0`, add its four directional values.
- Return the total coverage.

**Time Complexity:** `O(n × m)`

**Space Complexity:** `O(n × m)`