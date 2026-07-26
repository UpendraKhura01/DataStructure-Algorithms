
# Complete Binary Tree Traversal with Array Input

---

# Problem Statement

Given an integer array `arr[]` representing the **level-order traversal** of a **Complete Binary Tree**, return the nodes at **each level** in **sorted ascending order**.

Each level should be sorted **independently**, and the final result should be returned as a 2D list.

### Example

```text
Input:
arr = [7, 6, 5, 4, 3, 2, 1]

Output:
[
  [7],
  [5, 6],
  [1, 2, 3, 4]
]
```

Explanation:

```text
Level 0 → [7]          → Sorted → [7]

Level 1 → [6, 5]       → Sorted → [5, 6]

Level 2 → [4, 3, 2, 1] → Sorted → [1, 2, 3, 4]
```

---

# Goal

Traverse the array level by level (without actually constructing the binary tree), sort each level independently, and return all sorted levels.

---

# Intuition

A Complete Binary Tree stored in level-order has a predictable structure:

```text
Level 0 → 1 node

Level 1 → 2 nodes

Level 2 → 4 nodes

Level 3 → 8 nodes

...
```

Instead of building the tree, we can simply read the array in groups of:

```text
1, 2, 4, 8, ...
```

Each group represents one tree level.

After collecting one level, sort it and store it in the answer.

---

# Key Idea

The input is already in **level-order traversal**, so:

- First level contains `1` element.
- Second level contains `2` elements.
- Third level contains `4` elements.
- Every next level doubles in size.

Maintain:

```text
size = number of nodes in current level
```

Initially,

```text
size = 1
```

After processing each level,

```text
size = size × 2
```

Continue until all array elements are processed.

---

# Thought Process

### Step 1

Start from the first element of the array.

```text
idx = 0
size = 1
```

---

### Step 2

Read exactly `size` elements (or until the array ends).

These elements belong to the current tree level.

---

### Step 3

Sort the collected level.

```text
Collections.sort(level)
```

or

```text
list.sort(Comparator.naturalOrder())
```

---

### Step 4

Store the sorted level in the answer.

---

### Step 5

Double the level size.

```text
size = size × 2
```

Repeat until every array element has been processed.

---

# Code (Functions Only)

```java
ArrayList<ArrayList<Integer>> levelSort(int[] arr) {

    int n = arr.length;

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    int size = 1;
    int idx = 0;

    while (idx < n) {

        int temp = size;

        ArrayList<Integer> list = new ArrayList<>();

        while (temp-- > 0 && idx < n) {
            list.add(arr[idx++]);
        }

        list.sort(Comparator.naturalOrder());

        ans.add(list);

        size = size * 2;
    }

    return ans;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
arr = [7, 6, 5, 4, 3, 2, 1]
```

### Initial State

```text
size = 1
idx = 0

Answer = []
```

---

### Level 0

Take:

```text
[7]
```

Sort:

```text
[7]
```

Answer:

```text
[[7]]
```

Update:

```text
size = 2
idx = 1
```

---

### Level 1

Take:

```text
[6, 5]
```

Sort:

```text
[5, 6]
```

Answer:

```text
[
 [7],
 [5,6]
]
```

Update:

```text
size = 4
idx = 3
```

---

### Level 2

Take:

```text
[4,3,2,1]
```

Sort:

```text
[1,2,3,4]
```

Answer:

```text
[
 [7],
 [5,6],
 [1,2,3,4]
]
```

Update:

```text
idx = 7
```

Traversal ends.

---

### Final Output

```text
[
 [7],
 [5,6],
 [1,2,3,4]
]
```

---

# Logic Flow

```text
Start

↓

Initialize

size = 1
idx = 0

↓

While array is not fully processed

↓

Create an empty list

↓

Read "size" elements from the array

↓

Sort the current level

↓

Store the sorted level

↓

Double the level size

↓

Repeat

↓

Return all levels

↓

End
```

---

# Complexity

## Time Complexity

Reading all elements:

```text
O(N)
```

Sorting each level:

If the levels contain sizes:

```text
1, 2, 4, 8, ...
```

the total sorting cost is:

```text
O(Σ Li log Li)
```

where `Li` is the number of nodes at the `i-th` level.

In the worst case, the last level dominates, giving:

```text
O(N log N)
```

---

## Space Complexity

Result storage:

```text
O(N)
```

Temporary list for one level:

```text
O(W)
```

where `W` is the maximum width of the tree (up to `N/2`).

Overall auxiliary/result space:

```text
O(N)
```

---

# Key Takeaways

- There is **no need to build the binary tree**.
- A Complete Binary Tree in level-order naturally forms groups of:
  ```text
  1, 2, 4, 8, ...
  ```
- Each group corresponds to one tree level.
- Sort each level independently before storing it.
- Using an index and a level-size counter keeps the solution simple and efficient.

---

# Most Important Insight

Because the array already represents the **level-order traversal** of a **Complete Binary Tree**, each level can be identified solely by its expected size (`1, 2, 4, 8, ...`). This eliminates the need for tree construction or BFS traversal, allowing the problem to be solved directly by processing the array in progressively larger groups.

---

# Summary

The solution leverages the inherent structure of a Complete Binary Tree stored in level-order. By reading the array in groups of `1, 2, 4, 8, ...` elements, each group directly represents one tree level. Every level is sorted independently and added to the result. This approach avoids constructing the tree, keeps the implementation straightforward, and achieves an overall time complexity of **O(N log N)** due to sorting while using **O(N)** space for the output.
