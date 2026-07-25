# Max Sum Square Sub-Matrix of Size K

## Problem Statement

Given an `n × n` matrix `mat[][]` (which may contain negative values) and an integer `k`, find the **maximum sum** among all possible **k × k** square submatrices.

---

## Example 1

**Input**
```text
k = 3

mat = [
  [ 1,  2, -1,  4],
  [-8, -3,  4,  2],
  [ 3,  8, 10, -8],
  [-4, -1,  1,  7]
]
```

**Output**
```text
20
```

**Explanation**

The maximum sum comes from the highlighted `3 × 3` submatrix:

```text
-3   4   2
 8  10  -8
-1   1   7
```

Sum:

```text
-3 + 4 + 2 + 8 + 10 - 8 - 1 + 1 + 7 = 20
```

---

## Example 2

**Input**
```text
k = 1

mat = [
  [4]
]
```

**Output**
```text
4
```

---

## Approach (2D Prefix Sum)

Calculating every `k × k` submatrix directly would require:

- `O(k²)` time for one submatrix.
- `O((n-k+1)² × k²)` overall.

This is too slow for `n = 1000`.

Instead, build a **2D Prefix Sum** matrix so that the sum of any rectangle can be computed in **O(1)**.

---

## Prefix Sum Formula

Let `pref[i][j]` denote the sum of all elements inside the rectangle from `(0,0)` to `(i-1,j-1)`.

Then,

```text
pref[i][j] =
mat[i-1][j-1]
+ pref[i-1][j]
+ pref[i][j-1]
- pref[i-1][j-1]
```

---

## Computing a k × k Sum

Suppose the bottom-right corner of a square is `(i, j)` in the prefix matrix.

Then the corresponding square sum is

```text
sum =
pref[i][j]
- pref[i-k][j]
- pref[i][j-k]
+ pref[i-k][j-k]
```

Compute this for every possible square and keep the maximum.

---

## Algorithm

1. Build the 2D prefix sum matrix.
2. Iterate over every possible `k × k` square.
3. Compute its sum using the prefix sums.
4. Update the maximum answer.
5. Return the maximum sum.

---

## Correctness

The prefix sum matrix stores the cumulative sum of every rectangle from the top-left corner.

For every `k × k` square, the inclusion-exclusion formula:

```text
pref[i][j]
- pref[i-k][j]
- pref[i][j-k]
+ pref[i-k][j-k]
```

removes the extra regions and leaves exactly the desired square.

Since every possible `k × k` square is examined once and its sum is computed exactly, the maximum among them is the correct answer.

---

## Complexity Analysis

Let `n` be the size of the matrix.

- Building prefix sum: **O(n²)**
- Checking all squares: **O(n²)**

**Overall Time:** `O(n²)`

**Space:** `O(n²)`

---

## Java Solution

```java
class Solution {

    int maximumSum(int[][] mat, int k) {

        int n = mat.length;

        int[][] pref = new int[n + 1][n + 1];

        // Build prefix sum matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                pref[i][j] = mat[i - 1][j - 1]
                        + pref[i - 1][j]
                        + pref[i][j - 1]
                        - pref[i - 1][j - 1];
            }
        }

        int ans = pref[k][k];

        // Evaluate every k × k square
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= n; j++) {

                int sum = pref[i][j]
                        - pref[i - k][j]
                        - pref[i][j - k]
                        + pref[i - k][j - k];

                ans = Math.max(ans, sum);
            }
        }

        return ans;
    }
}
```