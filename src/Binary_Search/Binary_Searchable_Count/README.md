# Binary Searchable Count

## Intuition

An element is **binary searchable** if the standard Binary Search will always be able to find it, even though the array is **not necessarily sorted**.

During Binary Search:

- Every element in the **left subtree** of a chosen midpoint must be **smaller** than the midpoint.
- Every element in the **right subtree** must be **greater** than the midpoint.

So while recursively splitting the array, every position gets a **valid range** of values.

If the value at that position lies within this range, then Binary Search can successfully reach it.

---

## Key Observation

Suppose

```text
arr = [2,1,3,5,4,6]
```

First midpoint

```text
          3
       /      \
   [2,1]    [5,4,6]
```

Since `3` is the root,

every element on the left must satisfy

```text
value < 3
```

and every element on the right must satisfy

```text
value > 3
```

Now recursively continue.

For the right subtree,

```text
        5
      /   \
     4     6
```

Now,

```text
4 must satisfy

3 < 4 < 5
```

which is true.

Similarly,

```text
6 must satisfy

5 < 6 < +∞
```

which is also true.

---

## Approach

For every recursive Binary Search segment:

- Maintain the minimum possible value (`leftLimit`).
- Maintain the maximum possible value (`rightLimit`).

At each midpoint,

if

```text
leftLimit < arr[mid] < rightLimit
```

then this element is binary searchable.

Then recursively process

- Left half with updated upper bound.
- Right half with updated lower bound.

---

## Algorithm

1. Start with

```text
leftLimit = -∞
rightLimit = +∞
```

2. Compute the middle element.
3. If

```text
leftLimit < arr[mid] < rightLimit
```

increase answer.
4. Recurse on left half

```text
rightLimit = min(rightLimit, arr[mid])
```

5. Recurse on right half

```text
leftLimit = max(leftLimit, arr[mid])
```

6. Return total count.

---

## Dry Run

### Input

```text
arr = [2,1,3,5,4,6]
```

Initial

```text
Range

(-∞ , +∞)
```

Mid

```text
3

Valid ✔
```

Left subtree

```text
Range

(-∞ , 3)
```

Mid

```text
2

Valid ✔
```

Left child

```text
1

(-∞ , 2)

Valid ✔
```

Right subtree

```text
Range

(3 , +∞)
```

Mid

```text
4

Needs

3 < 4 < +∞

Valid ✔
```

Right child

```text
6

Needs

4 < 6 < +∞

Valid ✔
```

Total binary searchable elements

```text
5
```

---

## Code

```java
class Solution {

    int binarySearchable(int[] arr) {

        return solve(
                arr,
                0,
                arr.length - 1,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
    }

    private int solve(int[] arr,
                      int left,
                      int right,
                      int leftLimit,
                      int rightLimit) {

        if (left > right)
            return 0;

        int mid = left + (right - left) / 2;

        int count = 0;

        if (arr[mid] > leftLimit && arr[mid] < rightLimit) {
            count = 1;
        }

        count += solve(
                arr,
                left,
                mid - 1,
                leftLimit,
                Math.min(rightLimit, arr[mid])
        );

        count += solve(
                arr,
                mid + 1,
                right,
                Math.max(leftLimit, arr[mid]),
                rightLimit
        );

        return count;
    }
}
```

---

## Complexity Analysis

### Time Complexity

Every index becomes the midpoint exactly once.

```text
O(N)
```

---

### Space Complexity

Recursive stack.

Balanced array

```text
O(log N)
```

Worst case

```text
O(N)
```

---

## Why This Works

Binary Search reaches an element only if every previous comparison directs the search toward its position.

For every recursive partition:

- All values in the left partition must be **smaller** than the current midpoint.
- All values in the right partition must be **greater** than the current midpoint.

By propagating these valid value ranges (`leftLimit`, `rightLimit`) during recursion, we verify exactly the condition required for Binary Search to succeed.

Hence, every element satisfying

```text
leftLimit < value < rightLimit
```

is binary searchable.