
# 1260. Shift 2D Grid

---

# Problem Statement

Given an `m × n` 2D grid and an integer `k`, perform the grid shift operation exactly `k` times.

In one shift operation:

- Each element moves one position to the right.
- The last element of a row becomes the first element of the next row.
- The last element of the entire grid becomes the first element of the grid.

Return the grid after performing all shifts.

### Example

```text
Input:

1 2 3
4 5 6
7 8 9

k = 1

Output:

9 1 2
3 4 5
6 7 8
```

---

# Goal

Efficiently perform the required shifts without simulating each individual shift operation.

Instead, determine the final position of every element directly.

---

# Intuition

Observe that the grid is stored in **row-major order**.

For example,

```text
1 2 3
4 5 6
7 8 9
```

can be viewed as the 1D array

```text
[1,2,3,4,5,6,7,8,9]
```

A grid shift is exactly the same as a **right circular shift** of this array.

After shifting, we simply rebuild the 2D grid.

This avoids repeatedly moving elements.

---

# Key Idea

The solution consists of three simple steps:

### Step 1

Flatten the grid into a 1D array.

```text
Grid

1 2 3
4 5 6

↓

[1,2,3,4,5,6]
```

---

### Step 2

Perform a circular right shift.

For every index:

```text
newIndex = (oldIndex + k) % totalElements
```

Formula:

```text
shifted[(i + k) % len] = arr[i]
```

---

### Step 3

Convert the shifted array back into a 2D grid.

---

# Thought Process

### Step 1

Find the total number of elements.

```text
len = rows × columns
```

---

### Step 2

Since shifting more than the total number of elements repeats the pattern,

reduce:

```text
k = k % len
```

---

### Step 3

Flatten the grid into a 1D array.

---

### Step 4

Place every element into its final position using modular arithmetic.

```text
newIndex = (currentIndex + k) % len
```

---

### Step 5

Read the shifted array row by row to rebuild the answer grid.

---

# Code (Functions Only)

```java
List<List<Integer>> shiftGrid(int[][] grid, int k) {

    int n = grid.length;
    int m = grid[0].length;

    int len = n * m;

    k %= len;

    int idx = 0;

    int[] arr = new int[len];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            arr[idx++] = grid[i][j];
        }
    }

    int[] shifted = new int[len];

    for (int i = 0; i < len; i++) {
        shifted[(i + k) % len] = arr[i];
    }

    List<List<Integer>> ans = new ArrayList<>();

    idx = 0;

    for (int i = 0; i < n; i++) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            list.add(shifted[idx++]);
        }

        ans.add(list);
    }

    return ans;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
grid =

1 2 3
4 5 6
7 8 9

k = 1
```

---

### Step 1 : Flatten

```text
arr =

[1,2,3,4,5,6,7,8,9]
```

Length:

```text
len = 9
```

---

### Step 2 : Shift

Using

```text
newIndex = (oldIndex + 1) % 9
```

| Original Index | Value | New Index |
|---------------:|------:|----------:|
| 0 | 1 | 1 |
| 1 | 2 | 2 |
| 2 | 3 | 3 |
| 3 | 4 | 4 |
| 4 | 5 | 5 |
| 5 | 6 | 6 |
| 6 | 7 | 7 |
| 7 | 8 | 8 |
| 8 | 9 | 0 |

Shifted array:

```text
[9,1,2,3,4,5,6,7,8]
```

---

### Step 3 : Rebuild Grid

```text
9 1 2
3 4 5
6 7 8
```

Final Answer:

```text
[
 [9,1,2],
 [3,4,5],
 [6,7,8]
]
```

---

# Logic Flow

```text
Start

↓

Find rows and columns

↓

Compute

len = rows × columns

↓

Reduce

k = k % len

↓

Flatten grid into a 1D array

↓

Create another array

↓

For every element

newIndex = (currentIndex + k) % len

↓

Store element in new position

↓

Convert shifted array back into rows

↓

Return answer

↓

End
```

---

# Complexity

## Time Complexity

Flattening the grid:

```text
O(m × n)
```

Shifting the array:

```text
O(m × n)
```

Reconstructing the grid:

```text
O(m × n)
```

Overall:

```text
O(m × n)
```

---

## Space Complexity

Extra arrays:

```text
arr      → O(m × n)

shifted  → O(m × n)
```

Result list:

```text
O(m × n)
```

Overall auxiliary space:

```text
O(m × n)
```

---

# Key Takeaways

- A 2D grid stored in row-major order behaves exactly like a linear array.
- Multiple shifts can be optimized using:
  ```text
  k = k % totalElements
  ```
- Modular arithmetic directly computes the destination of every element.
- No repeated shifting is required.
- Flatten → Shift → Rebuild is a clean and efficient strategy.

---

# Most Important Insight

A grid shift is nothing more than a **circular right rotation of its row-major representation**. Once the grid is flattened into a 1D array, every element's final position is computed using:

```text
newIndex = (oldIndex + k) % totalElements
```

This transforms a potentially expensive repeated-shift simulation into a single linear traversal.

---

# Summary

The optimal approach treats the 2D grid as a single linear sequence. First, the grid is flattened into a 1D array. Then, each element is moved directly to its final position using modular arithmetic, avoiding repeated shift operations. Finally, the shifted array is reconstructed into a 2D grid. This method processes every element exactly once during each phase, achieving **O(m × n)** time complexity with **O(m × n)** additional space while keeping the implementation simple and easy to understand.
