# Subarray Frequency Count Queries

## Problem Statement

Given an integer array `arr[]` and multiple queries of the form:

```text
[l, r, x]
```

determine how many times the element `x` appears in the subarray:

```text
arr[l...r]
```

Return the answer for every query.

---

## Example 1

**Input**

```text
arr = [1, 2, 1, 3, 1, 2, 3]

queries = [
    [0, 4, 1],
    [2, 5, 2],
    [1, 6, 3],
    [0, 6, 5]
]
```

**Output**

```text
[3, 1, 2, 0]
```

**Explanation**

- Query `[0,4,1]`
    - Subarray = `[1,2,1,3,1]`
    - Frequency of `1` = **3**

- Query `[2,5,2]`
    - Subarray = `[1,3,1,2]`
    - Frequency of `2` = **1**

- Query `[1,6,3]`
    - Subarray = `[2,1,3,1,2,3]`
    - Frequency of `3` = **2**

- Query `[0,6,5]`
    - `5` does not occur.
    - Answer = **0**

---

## Example 2

**Input**

```text
arr = [11, 21, 51, 101, 11, 51]

queries = [
    [0,4,11],
    [2,5,51]
]
```

**Output**

```text
[2,2]
```

---

# Approach

Scanning every subarray for every query would take

```text
O(n × q)
```

which is too slow for `10^5` queries.

Instead, preprocess the positions of every number.

---

## Step 1: Store Occurrences

Use a HashMap:

```text
value -> list of indices
```

Example:

```text
arr = [1,2,1,3,1,2,3]
```

Store

```text
1 -> [0,2,4]
2 -> [1,5]
3 -> [3,6]
```

These index lists are naturally sorted because we traverse the array once.

---

## Step 2: Binary Search

For a query

```text
[l, r, x]
```

Suppose

```text
x -> [0,2,4,7,10]
```

We need the number of indices inside

```text
[l, r]
```

Find

- first index **≥ l** (Lower Bound)
- first index **> r** (Upper Bound)

Then

```text
frequency = upperBound - lowerBound
```

---

## Example

Indices of `1`

```text
[0,2,4,7]
```

Query

```text
l = 2
r = 6
```

Lower Bound:

```text
2
```

(points to index `2`)

Upper Bound:

```text
3
```

(points just after index `4`)

Frequency

```text
3 - 1 = 2
```

(indices `2` and `4`)

---

# Algorithm

1. Build a map from value to its sorted list of indices.
2. For every query:
    - If `x` is absent, answer `0`.
    - Otherwise:
        - Compute `lowerBound(l)`
        - Compute `upperBound(r)`
        - Answer is

```text
upperBound - lowerBound
```

---

# Correctness

Each occurrence of a value is stored in sorted order.

For any query:

- Lower Bound finds the first occurrence within the range.
- Upper Bound finds the position immediately after the last occurrence within the range.

Therefore,

```text
upperBound - lowerBound
```

counts exactly the occurrences lying inside `[l, r]`.

Since this is done independently for every query, every answer is correct.

---

# Complexity Analysis

Let

- `n` = size of array
- `q` = number of queries

### Preprocessing

Building the map:

```text
O(n)
```

### Each Query

Two binary searches:

```text
O(log k)
```

where `k` is the frequency of `x`.

Overall:

```text
O(n + q log n)
```

### Space

```text
O(n)
```

for storing all indices.

---

# Java Solution

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {

    ArrayList<Integer> freqInRange(int[] arr, int[][] queries) {

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        // Store indices of every value
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        ArrayList<Integer> answer = new ArrayList<>();

        for (int[] query : queries) {

            int l = query[0];
            int r = query[1];
            int x = query[2];

            if (!map.containsKey(x)) {
                answer.add(0);
                continue;
            }

            ArrayList<Integer> indices = map.get(x);

            int left = lowerBound(indices, l);
            int right = upperBound(indices, r);

            answer.add(right - left);
        }

        return answer;
    }

    // First index >= target
    private static int lowerBound(ArrayList<Integer> list, int target) {

        int low = 0;
        int high = list.size();

        while (low < high) {

            int mid = (low + high) / 2;

            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // First index > target
    private static int upperBound(ArrayList<Integer> list, int target) {

        int low = 0;
        int high = list.size();

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
```