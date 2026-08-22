# 🟩 Largest Rectangle with Column Swaps

## 📌 Problem Statement

Given a binary matrix `mat[][]` containing only `0`s and `1`s.

Any pair of columns can be swapped any number of times.

Return the maximum possible area of a rectangle containing only `1`s after performing column swaps.

### Example

    Input:
    mat = [
        [0, 1, 0, 1, 0],
        [0, 1, 0, 1, 1],
        [1, 1, 0, 1, 0]
    ]

    Output:
    6

---

## 🎯 Goal

For every row:

    1. Calculate consecutive `1`s vertically for each column.
    2. Sort the heights.
    3. Use each height as a possible rectangle height.
    4. Calculate the maximum possible width.
    5. Track the maximum area.

The main formula is:

    area = height × width

After sorting, for index `j`:

    height = consecutiveOne[i][j]
    width = m - j

Therefore:

    area = consecutiveOne[i][j] × (m - j)

---

## 💡 Intuition

Think of every row as a histogram.

For example:

    [1, 1, 0, 1]
    [1, 1, 1, 1]

The heights at the second row are:

    [2, 2, 1, 2]

Because columns can be swapped, we can rearrange them:

    [1, 2, 2, 2]

Now the three columns having height `2` can form:

    height = 2
    width = 3

So:

    area = 2 × 3
         = 6

Therefore, instead of actually swapping columns, we simply sort the heights of each row.

---

## 🔥 Key Idea

For every cell containing `1`:

    consecutiveOne[r][c] =
        consecutiveOne[r - 1][c] + 1

If the cell contains `0`, its height is:

    0

This creates histogram heights for every row.

Then sort each row.

Example:

    Before sorting:

    [1, 3, 0, 3, 0]

    After sorting:

    [0, 0, 1, 3, 3]

For index `j = 3`:

    height = 3
    width = 5 - 3
          = 2

Therefore:

    area = 3 × 2
         = 6

---

## 🧠 Thought Process

### Step 1: Calculate Vertical Heights

For every column, maintain the number of consecutive `1`s ending at the current row.

If:

    mat[r][c] == 1

then:

    height[r][c] = height[r - 1][c] + 1

Otherwise:

    height[r][c] = 0

---

### Step 2: Sort Each Row

Since arbitrary column swaps are allowed, the columns can be arranged in any order.

So:

    Arrays.sort(consecutiveOne[i]);

puts the smallest heights first and largest heights last.

---

### Step 3: Calculate Rectangle Area

After sorting, consider the element at index `j`.

There are:

    m - j

elements from `j` to the end.

Because the row is sorted, all these elements have height at least:

    consecutiveOne[i][j]

Therefore:

    height = consecutiveOne[i][j]
    width = m - j

and:

    area = height × width

---

## 💻 Code

```java
int maxArea(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;

    int[][] consecutiveOne = new int[n][m];

    for (int i = 0; i < m; i++) {
        consecutiveOne[0][i] = mat[0][i];
    }

    for (int c = 0; c < m; c++) {
        for (int r = 1; r < n; r++) {
            if (mat[r][c] == 1) {
                consecutiveOne[r][c] += consecutiveOne[r - 1][c] + 1;
            }
        }
    }

    for (int i = 0; i < n; i++) {
        Arrays.sort(consecutiveOne[i]);
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int cur = consecutiveOne[i][j] * (m - j);

            ans = Math.max(ans, cur);
        }
    }

    return ans;
}
```

---

## 🧪 Dry Run

Input:

    mat = [
        [0, 1, 0, 1, 0],
        [0, 1, 0, 1, 1],
        [1, 1, 0, 1, 0]
    ]

### Step 1: Consecutive Heights

    Row 0:
    [0, 1, 0, 1, 0]

    Row 1:
    [0, 2, 0, 2, 1]

    Row 2:
    [1, 3, 0, 3, 0]

### Step 2: Sort Rows

    Row 0:
    [0, 0, 0, 1, 1]

    Row 1:
    [0, 0, 1, 2, 2]

    Row 2:
    [0, 0, 1, 3, 3]

### Step 3: Maximum Area

For the last row:

    [0, 0, 1, 3, 3]

At `j = 3`:

    height = 3
    width = 5 - 3
          = 2

Therefore:

    area = 3 × 2
         = 6

Final answer:

    6

---

## 🔁 Logic Flow

    Start
      |
      v
    Get n and m
      |
      v
    Create consecutiveOne[][]
      |
      v
    Calculate vertical consecutive-1 heights
      |
      v
    Sort every row
      |
      v
    For every row
      |
      v
    For every index j
      |
      v
    height = consecutiveOne[i][j]
      |
      v
    width = m - j
      |
      v
    area = height × width
      |
      v
    Update maximum answer
      |
      v
    Return answer

---

## 📊 Complexity

Let:

    n = number of rows
    m = number of columns

### Time Complexity

Calculating consecutive heights:

    O(n × m)

Sorting every row:

    O(n × m log m)

Calculating maximum area:

    O(n × m)

Therefore:

    Total = O(n × m log m)

---

### Space Complexity

The `consecutiveOne` matrix contains:

    n × m

elements.

Therefore:

    Space = O(n × m)

---

## 🎯 Key Takeaways

- Treat every row as a histogram.
- Store the number of consecutive `1`s for every column.
- Column swaps allow the heights to be rearranged.
- Sorting each row simulates the optimal column arrangement.
- After sorting, the width for index `j` is:

      m - j

- The area is:

      height × width

- Check every row and every possible height.
- No actual column swapping is required.

---

## 🔥 Most Important Insight

The key observation is:

    Arbitrary column swaps
            ↓
    Columns can be reordered freely
            ↓
    Sort histogram heights
            ↓
    Group large heights together
            ↓
    Calculate maximum rectangle

For a sorted row:

    [0, 0, 1, 3, 3]

Choosing the last two columns gives:

    height = 3
    width = 2

Therefore:

    area = 3 × 2
         = 6

The formula to remember is:

    area = consecutiveOne[i][j] × (m - j)

---

## 🏁 Summary

The solution converts the binary matrix into histogram heights.

For each row:

    1. Calculate consecutive `1`s vertically.
    2. Sort the heights.
    3. For every position `j`, consider the suffix from `j` to `m - 1`.
    4. Use `consecutiveOne[i][j]` as the height.
    5. Use `m - j` as the width.
    6. Update the maximum area.

The central formula is:

    area = height × width

    area = consecutiveOne[i][j] × (m - j)

For the given example:

    Maximum Area = 6

Complexity:

    Time  : O(n × m log m)
    Space : O(n × m)