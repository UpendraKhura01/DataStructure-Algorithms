# Minimum Deletions to Make Sorted

## Problem Statement

Given an integer array `arr[]`, find the **minimum number of elements** that must be deleted so that the remaining elements form a **strictly increasing sequence** while preserving their original order.

---

## Example 1

**Input**
```text
arr = [5, 6, 1, 7, 4]
```

**Output**
```text
2
```

**Explanation**

Delete `1` and `4`.

Remaining array:

```text
[5, 6, 7]
```

which is strictly increasing.

---

## Example 2

**Input**
```text
arr = [1, 1, 1]
```

**Output**
```text
2
```

**Explanation**

Since the sequence must be **strictly increasing**, only one `1` can remain.

Minimum deletions = **2**.

---

## Key Observation

Instead of deciding which elements to delete, find the **Longest Increasing Subsequence (LIS)**.

If the LIS has length `L`, then those elements can remain while every other element must be removed.

Therefore,

```text
Minimum Deletions = n - LIS Length
```

---

## Efficient LIS (Binary Search)

Maintain a list `temp` where:

- `temp[i]` stores the **smallest possible ending value** of an increasing subsequence of length `i + 1`.

For every element:

- If it is greater than the last element of `temp`, append it.
- Otherwise, replace the **first element ≥ current value** (lower bound).

Replacing keeps future subsequences as flexible as possible without changing the LIS length.

---

## Algorithm

1. Initialize `temp` with the first element.
2. Traverse the remaining array.
3. If the current element is larger than the last element of `temp`, append it.
4. Otherwise, find its lower bound using binary search and replace that position.
5. The size of `temp` is the LIS length.
6. Return:

```text
n - LIS Length
```

---

## Correctness

The algorithm maintains the smallest possible ending value for every increasing subsequence length.

- Appending extends the longest subsequence.
- Replacing a larger ending value with a smaller one preserves the subsequence length while increasing the chances of future extensions.

Thus, `temp.size()` equals the length of the Longest Increasing Subsequence.

Since deleting all elements outside the LIS produces a strictly increasing array with the fewest deletions,

```text
Answer = n − LIS Length
```

is optimal.

---

## Complexity Analysis

Let `n` be the array size.

- Each element performs one binary search.
- Binary search takes **O(log n)**.

**Time Complexity:** `O(n log n)`

**Space Complexity:** `O(n)`

---

## Java Solution

```java
import java.util.ArrayList;

class Solution {

    int n;

    public int minDeletions(int[] arr) {

        n = arr.length;

        int lisLength = LIS(arr);

        return n - lisLength;
    }

    // Computes LIS length in O(n log n)
    private int LIS(int[] arr) {

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);

        for (int i = 1; i < n; i++) {

            if (arr[i] > temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
            } else {

                int idx = lowerBound(temp, arr[i]);
                temp.set(idx, arr[i]);
            }
        }

        return temp.size();
    }

    // First index where value >= num
    private int lowerBound(ArrayList<Integer> temp, int num) {

        int left = 0;
        int right = temp.size() - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (temp.get(mid) >= num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
```