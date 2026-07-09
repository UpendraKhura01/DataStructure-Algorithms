# Minimum Insert and Delete to Convert

## Intuition

To convert array `a` into array `b`, we want to **keep as many elements as possible** that already appear in the correct relative order.

Since:

- `b` is **sorted**
- All elements of `b` are **distinct**

we can map every element of `b` to its index.

Now, replace every element of `a` (that exists in `b`) with its corresponding index in `b`.

The problem now becomes finding the **Longest Increasing Subsequence (LIS)** of these indices.

Why?

- Increasing indices mean the elements already appear in the same order as in `b`.
- Those elements can be kept.
- Everything else must be deleted or inserted.

---

## Key Observation

Suppose

```text
a = [1,2,5,3,1]
b = [1,3,5]
```

Mapping of `b`

```text
1 -> 0
3 -> 1
5 -> 2
```

Convert `a`

```text
[0,2,1,0]
```

Now find LIS

```text
0 2
```

or

```text
0 1
```

LIS length = **2**

So,

```text
Delete = n - LIS = 5 - 2 = 3

Insert = m - LIS = 3 - 2 = 1

Total = 4
```

---

## Approach

1. Store every element of `b` with its index in a HashMap.
2. Traverse `a`.
3. If the current element exists in `b`, replace it with its mapped index.
4. Find the LIS of this index array using Binary Search.
5. Let LIS length be `L`.
6. Answer is

```text
(n - L) + (m - L)
```

---

## Why LIS?

The mapped indices represent the order of elements in `b`.

An increasing subsequence means

- Elements occur in the same order in both arrays.
- Those elements do not need deletion or insertion.

Therefore,

```text
Common elements kept = LIS
```

---

## Algorithm

1. Create a HashMap storing

```text
value → index in b
```

2. Build an array containing mapped indices of elements present in both arrays.
3. Compute the LIS of this array using Binary Search.
4. Compute

```text
Delete = n − LIS

Insert = m − LIS
```

5. Return

```text
Delete + Insert
```

---

## Dry Run

### Input

```text
a = [1,2,5,3,1]
b = [1,3,5]
```

Map

```text
1 -> 0
3 -> 1
5 -> 2
```

Mapped array

```text
[0,2,1,0]
```

LIS

```text
[0,2]

Length = 2
```

Operations

```text
Delete = 5 - 2 = 3

Insert = 3 - 2 = 1

Answer = 4
```

---

## Code

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {

    public int minInsAndDel(int[] a, int[] b) {

        int n = a.length;
        int m = b.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            map.put(b[i], i);
        }

        ArrayList<Integer> indices = new ArrayList<>();

        for (int x : a) {
            if (map.containsKey(x)) {
                indices.add(map.get(x));
            }
        }

        int lis = LIS(indices);

        return (n - lis) + (m - lis);
    }

    private int LIS(ArrayList<Integer> arr) {

        if (arr.isEmpty())
            return 0;

        ArrayList<Integer> tail = new ArrayList<>();
        tail.add(arr.get(0));

        for (int i = 1; i < arr.size(); i++) {

            int x = arr.get(i);

            if (x > tail.get(tail.size() - 1)) {

                tail.add(x);

            } else {

                int idx = Collections.binarySearch(tail, x);

                if (idx < 0)
                    idx = -(idx + 1);

                tail.set(idx, x);
            }
        }

        return tail.size();
    }
}
```

---

## Complexity Analysis

### Time Complexity

Building HashMap

```text
O(m)
```

Creating mapped array

```text
O(n)
```

LIS using Binary Search

```text
O(n log n)
```

Overall

```text
O(n log n + m)
```

---

### Space Complexity

HashMap

```text
O(m)
```

Mapped array

```text
O(n)
```

LIS list

```text
O(n)
```

Overall

```text
O(n + m)
```

---

## Why This Works

- Every common element that remains must preserve the order of `b`.
- Mapping values to indices converts the problem into finding the **Longest Increasing Subsequence**.
- The LIS represents the maximum number of elements that can stay unchanged.
- Every other element in `a` must be deleted.
- Every missing element from `b` must be inserted.

Thus,

```text
Minimum Operations

= Deletions + Insertions

= (n − LIS) + (m − LIS)
```