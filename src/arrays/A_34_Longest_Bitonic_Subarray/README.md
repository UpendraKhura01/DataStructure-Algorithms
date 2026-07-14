# Longest Bitonic Subarray

**Difficulty:** Medium

Given an array `arr[]` of size `n` containing positive integers, return the maximum length of the **bitonic subarray**.

A subarray `arr[i...j]` is considered **bitonic** if its elements first monotonically increase and then monotonically decrease.

Formally, there exists an index `k` (`i ≤ k ≤ j`) such that:

- `arr[i] ≤ arr[i+1] ≤ ... ≤ arr[k]`
- `arr[k] ≥ arr[k+1] ≥ ... ≥ arr[j]`

> A completely increasing or completely decreasing subarray is also considered bitonic.

---

## Examples

### Example 1

**Input**
```text
arr[] = [12, 4, 78, 90, 45, 23]
```

**Output**
```text
5
```

**Explanation**

The longest bitonic subarray is:

```text
[4, 78, 90, 45, 23]
```

Length = **5**

---

### Example 2

**Input**
```text
arr[] = [10, 20, 30, 40]
```

**Output**
```text
4
```

**Explanation**

The whole array is increasing, so it is itself a valid bitonic subarray.

---

### Example 3

**Input**
```text
arr[] = [10, 10, 10, 10]
```

**Output**
```text
4
```

---

## Constraints

- `1 ≤ n ≤ 10^6`
- `1 ≤ arr[i] ≤ 10^6`

---

# Optimal Approach (Prefix & Suffix DP)

### Idea

For every index, determine:

- `inc[i]` → length of the longest **non-decreasing** subarray ending at `i`.
- `dec[i]` → length of the longest **non-increasing** subarray starting from `i`.

If index `i` is chosen as the peak, then

```text
Bitonic Length = inc[i] + dec[i] - 1
```

Subtract `1` because the peak gets counted twice.

Take the maximum over all indices.

---

## Algorithm

1. Compute `inc[]`
    - If `arr[i] >= arr[i-1]`
        - extend previous sequence
    - else start new sequence.

2. Compute `dec[]`
    - Traverse from right.
    - If `arr[i] >= arr[i+1]`
        - extend sequence
    - else start new sequence.

3. For every index

```text
answer = max(answer, inc[i] + dec[i] - 1)
```

---

## Dry Run

### Input

```text
arr = [12, 4, 78, 90, 45, 23]
```

### Increasing lengths

|Index|Value|inc|
|-----:|----:|--:|
|0|12|1|
|1|4|1|
|2|78|2|
|3|90|3|
|4|45|1|
|5|23|1|

So

```text
inc = [1,1,2,3,1,1]
```

---

### Decreasing lengths

|Index|Value|dec|
|-----:|----:|--:|
|5|23|1|
|4|45|2|
|3|90|3|
|2|78|1|
|1|4|1|
|0|12|2|

So

```text
dec = [2,1,1,3,2,1]
```

---

### Compute answer

|Index|inc|dec|Length|
|-----:|--:|--:|-----:|
|0|1|2|2|
|1|1|1|1|
|2|2|1|2|
|3|3|3|5|
|4|1|2|2|
|5|1|1|1|

Maximum = **5**

---

# Correctness

For every possible peak:

- `inc[i]` gives the maximum increasing portion ending at `i`.
- `dec[i]` gives the maximum decreasing portion beginning at `i`.

Combining them constructs the longest bitonic subarray having peak at `i`.

Since every index is checked once, the global maximum is found.

---

# Complexity Analysis

- Computing `inc[]` → **O(n)**
- Computing `dec[]` → **O(n)**
- Final traversal → **O(n)**

**Time Complexity:** **O(n)**

**Space Complexity:** **O(n)**

---

# Java Solution

```java
class Solution {
    int bitonic(int[] arr) {
        int n = arr.length;

        int[] inc = new int[n];
        int[] dec = new int[n];

        inc[0] = 1;
        dec[n - 1] = 1;

        // Longest non-decreasing subarray ending at i
        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1])
                inc[i] = inc[i - 1] + 1;
            else
                inc[i] = 1;
        }

        // Longest non-increasing subarray starting at i
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1])
                dec[i] = dec[i + 1] + 1;
            else
                dec[i] = 1;
        }

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, inc[i] + dec[i] - 1);
        }

        return ans;
    }
}
```