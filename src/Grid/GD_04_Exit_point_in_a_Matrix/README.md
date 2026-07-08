# Exit Point in a Matrix

## Problem Statement

You are given a binary matrix `mat[][]` consisting of `0`s and `1`s.

You start from the top-left cell `(0,0)` and initially move towards the **right**.

While traversing the matrix:

- If the current cell contains `0`, continue moving in the same direction.
- If the current cell contains `1`:
    - Turn **90° clockwise** (right turn).
    - Change the current cell to `0`.
- Continue until you move outside the matrix.

Return the coordinates of the cell from which you exit the matrix.

**Constraints**

- `1 ≤ n, m ≤ 100`

---

# Goal

Simulate the movement inside the matrix while following the direction-changing rules and determine the exit point.

---

# Intuition

The problem is purely a simulation.

At every step, only two decisions are required:

- Continue moving in the current direction.
- Turn right if a `1` is encountered.

Since there are only four possible directions, we can store them in arrays and keep changing the current direction whenever needed.

The moment the next move goes outside the matrix, the current position becomes the exit point.

---

# Key Idea

Represent the four directions using two arrays.

```
Direction      dr     dc

Right          0      1
Down           1      0
Left           0     -1
Up            -1      0
```

Maintain a variable

```
dir
```

where

```
0 → Right
1 → Down
2 → Left
3 → Up
```

Whenever a `1` is encountered,

```
dir = (dir + 1) % 4
```

This performs a clockwise rotation.

---

# Thought Process

### Step 1

Start from

```
(0,0)
```

Current direction

```
Right
```

```
dir = 0
```

---

### Step 2

Check the current cell.

If it contains

```
0
```

Continue in the same direction.

If it contains

```
1
```

- Convert it into `0`.
- Rotate clockwise.

---

### Step 3

Compute the next position

```
nextRow = currentRow + dr[dir]

nextCol = currentCol + dc[dir]
```

---

### Step 4

If the next position lies outside the matrix,

return the current cell because that is where the exit occurs.

---

### Step 5

Otherwise,

move to the next cell and repeat the process.

---

# Code

```java
List<Integer> exitPoint(int[][] mat) {

    int n = mat.length;
    int m = mat[0].length;

    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    int row = 0;
    int col = 0;
    int dir = 0;

    while (true) {

        if (mat[row][col] == 1) {
            mat[row][col] = 0;
            dir = (dir + 1) % 4;
        }

        int nextRow = row + dr[dir];
        int nextCol = col + dc[dir];

        if (nextRow < 0 || nextCol < 0 ||
            nextRow >= n || nextCol >= m) {

            return Arrays.asList(row, col);
        }

        row = nextRow;
        col = nextCol;
    }
}
```

---

# Dry Run

### Input

```
0 1 0
0 1 1
0 0 0
```

---

### Initial State

```
Position

(0,0)

Direction

Right
```

---

### Step 1

Current cell

```
(0,0)

Value = 0
```

Continue moving right.

Move to

```
(0,1)
```

---

### Step 2

Current cell

```
(0,1)

Value = 1
```

Convert it into

```
0
```

Turn clockwise

```
Right

↓

Down
```

Move to

```
(1,1)
```

---

### Step 3

Current cell

```
(1,1)

Value = 1
```

Convert into

```
0
```

Turn clockwise

```
Down

↓

Left
```

Move to

```
(1,0)
```

---

### Step 4

Current cell

```
(1,0)

Value = 0
```

Continue moving left.

Next position

```
(1,-1)
```

This is outside the matrix.

Therefore,

Exit Point

```
(1,0)
```

Final Answer

```
[1,0]
```

---

# Logic Flow

```
Start
   │
   ▼
Begin at (0,0)
Direction = Right
   │
   ▼
Current cell is 1 ?
   │
 ┌─┴──────────────┐
 │                │
Yes              No
 │                │
 ▼                ▼
Turn Right    Continue
Make cell 0
 │
 ▼
Compute next cell
 │
 ▼
Outside matrix?
 │
 ┌───────┴────────┐
 │                │
Yes              No
 │                │
 ▼                ▼
Return       Move to
Current Cell Next Cell
                 │
                 └────► Repeat
```

---

# Complexity

### Time Complexity

```
O(n × m)
```

Reason

Every cell containing `1` becomes `0` after the first visit.

Hence, the total number of effective moves is bounded by the size of the matrix.

---

### Space Complexity

```
O(1)
```

Only a few variables and two small direction arrays are used.

---

# Key Takeaways

- The problem is a straightforward simulation.
- Direction changes can be efficiently handled using direction arrays.
- Modifying visited `1`s into `0`s prevents repeated unnecessary turns.
- The exit point is the last valid cell before leaving the matrix.
- Using modulo arithmetic makes direction changes simple and clean.

---

# Most Important Insight

Instead of writing separate logic for moving right, down, left, and up, represent all directions using two arrays:

```
dr = {0, 1, 0, -1}

dc = {1, 0, -1, 0}
```

Then every clockwise turn is simply

```
dir = (dir + 1) % 4
```

This eliminates complex conditional statements and makes the simulation concise.

---

# Summary

- Start from `(0,0)` facing right.
- Move according to the current direction.
- Whenever a `1` is encountered:
    - Change it to `0`.
    - Rotate clockwise.
- Continue until the next move leaves the matrix.
- Return the current position as the exit point.

**Time Complexity:** `O(n × m)`

**Space Complexity:** `O(1)`