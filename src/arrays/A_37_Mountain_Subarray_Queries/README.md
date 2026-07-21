# Mountain Subarray Queries

## Problem Statement

Given an integer array `arr[]` and multiple queries `[l, r]`, determine whether the subarray `arr[l...r]` is a **mountain array**.

A subarray is considered a mountain if there exists an index `k` (`l ≤ k ≤ r`) such that:

- `arr[l] ≤ arr[l+1] ≤ ... ≤ arr[k]`
- `arr[k] ≥ arr[k+1] ≥ ... ≥ arr[r]`

The increasing or decreasing part may be empty. Therefore:

- An entirely non-decreasing subarray is a mountain.
- An entirely non-increasing subarray is also a mountain.

---

## Example 1

**Input**

```text
arr = [2, 3, 2, 4, 4, 6, 3, 2]

queries = [[0,2], [1,3]]
```

**Output**

```text
[true, false]
```

**Explanation**

### Query `[0,2]`

Subarray:

```text
[2, 3, 2]
```

It first increases and then decreases.

✅ Mountain.

---

### Query `[1,3]`

Subarray:

```text
[3, 2, 4]
```

It decreases first and then increases.

❌ Not a mountain.

---

## Example 2

**Input**

```text
arr = [2, 2, 2, 2]

queries = [[0,2], [1,3]]
```

**Output**

```text
[true, true]
```

**Explanation**

Every subarray is simultaneously non-decreasing and non-increasing.

---

# Approach

Answering every query by scanning the subarray would take

```text
O(length of subarray)
```

which is too slow for up to `10^5` queries.

Instead, preprocess two arrays.

---

## 1. Increasing Reach (`inc`)

`inc[i]` stores the **furthest index** reachable from `i` while the sequence remains **non-decreasing**.

Example:

```text
arr = [2,3,3,1]

inc = [2,2,2,3]
```

---

## 2. Decreasing Reach (`dec`)

`dec[i]` stores the **leftmost index** from which we can reach `i` while remaining **non-increasing**.

Example:

```text
arr = [5,4,4,6]

dec = [0,0,0,3]
```

---

## Observation

For a query `[l, r]`:

- Starting from `l`, increasing can continue until `inc[l]`.
- Ending at `r`, decreasing begins from `dec[r]`.

If these two ranges overlap, then there exists some peak.

Condition:

```text
dec[r] <= inc[l]
```

If true:

- There exists an index `k`
- Left side is non-decreasing
- Right side is non-increasing

Hence the subarray is a mountain.

---

# Algorithm

### Preprocessing

Build `inc` from right to left.

```text
if arr[i] <= arr[i+1]
    inc[i] = inc[i+1]
else
    inc[i] = i
```

Build `dec` from left to right.

```text
if arr[i] <= arr[i-1]
    dec[i] = dec[i-1]
else
    dec[i] = i
```

---

### Answer Queries

For every query:

```text
if dec[r] <= inc[l]
    true
else
    false
```

Each query is answered in **O(1)**.

---

# Correctness

The array `inc` stores the farthest point reachable by moving only through non-decreasing elements.

The array `dec` stores the earliest point from which the suffix ending at `r` remains non-increasing.

If

```text
dec[r] <= inc[l]
```

then there exists an index lying in both ranges that serves as the mountain peak.

Thus:

- Left portion is non-decreasing.
- Right portion is non-increasing.

Therefore, the subarray satisfies the mountain property.

If the condition fails, no such peak exists, so the subarray cannot be a mountain.

Hence the algorithm is correct.

---

# Complexity Analysis

Let

- `n` = array size
- `q` = number of queries

### Time

- Preprocessing: **O(n)**
- Each query: **O(1)**

Overall:

```text
O(n + q)
```

### Space

```text
O(n)
```

for the `inc` and `dec` arrays.

---

# Java Solution

```java
import java.util.ArrayList;

class Solution {

    ArrayList<Boolean> processQueries(int[] arr, int[][] queries) {

        int n = arr.length;

        int[] inc = new int[n];
        int[] dec = new int[n];

        // Build increasing reach
        inc[n - 1] = n - 1;

        for (int i = n - 2; i >= 0; i--) {

            if (arr[i] <= arr[i + 1]) {
                inc[i] = inc[i + 1];
            } else {
                inc[i] = i;
            }
        }

        // Build decreasing reach
        dec[0] = 0;

        for (int i = 1; i < n; i++) {

            if (arr[i] <= arr[i - 1]) {
                dec[i] = dec[i - 1];
            } else {
                dec[i] = i;
            }
        }

        ArrayList<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            ans.add(dec[r] <= inc[l]);
        }

        return ans;
    }
}
```