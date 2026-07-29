
# Construct List using XOR Queries

---

# Problem Statement

Initially, an array contains only one element:

```text
[0]
```

You are given a list of queries of two types:

- **0 x** → Insert `x` into the array.
- **1 x** → Replace every element `a` in the array with `a XOR x`.

After processing all queries, return the final array in **sorted order**.

### Example 1

```text
Input

queries =
[
 [0,6],
 [0,3],
 [0,2],
 [1,4],
 [1,5]
]

Output

[1,2,3,7]
```

---

### Example 2

```text
Input

queries =
[
 [0,2],
 [1,3],
 [0,5]
]

Output

[1,3,5]
```

---

# Goal

Efficiently compute the final contents of the array after all insertions and XOR operations without repeatedly applying XOR to every existing element.

---

# Intuition

A straightforward solution would be:

- Insert numbers normally.
- For every XOR query, update every element in the array.

However, this becomes expensive.

For example,

```text
Insert 100000 elements

Perform 100000 XOR operations
```

Updating every element each time results in:

```text
O(Q × N)
```

which is too slow.

### Key Observation

XOR operations are **reversible**.

Instead of applying XOR immediately, we can delay it.

If we process the queries **backwards**, we can keep track of one cumulative XOR value.

Whenever we encounter an insertion, we simply insert:

```text
value XOR cumulativeXOR
```

This automatically gives the value that would exist after all future XOR operations.

---

# Key Idea

Maintain:

```text
xor = cumulative XOR of all pending XOR operations
```

Traverse the queries from **last to first**.

### If query is

```text
1 x
```

Update

```text
xor ^= x
```

---

### If query is

```text
0 x
```

Store

```text
x ^ xor
```

because this value represents the final value after all later XOR operations.

Finally,

remember that the array initially contains:

```text
0
```

So add

```text
0 ^ xor
```

Sort the resulting list and return it.

---

# Thought Process

### Step 1

Initialize

```text
xor = 0
```

---

### Step 2

Process queries from the end.

---

### Step 3

For every XOR query,

accumulate

```text
xor ^= value
```

---

### Step 4

For every insertion,

store

```text
insertedValue ^ xor
```

---

### Step 5

After processing all queries,

include the original element

```text
0 ^ xor
```

---

### Step 6

Sort the list.

Return the answer.

---

# Code (Functions Only)

```java
ArrayList<Integer> constructList(int[][] queries) {

    int n = queries.length;

    int xor = 0;

    ArrayList<Integer> arr = new ArrayList<>();

    for (int i = n - 1; i >= 0; i--) {

        int type = queries[i][0];
        int value = queries[i][1];

        if (type == 0) {

            arr.add(value ^ xor);

        } else {

            xor ^= value;
        }
    }

    arr.add(0 ^ xor);

    Collections.sort(arr);

    return arr;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
queries

[
 [0,6],
 [0,3],
 [0,2],
 [1,4],
 [1,5]
]
```

---

### Initial State

```text
xor = 0

answer = []
```

---

### Traverse Backwards

#### Query

```text
1 5
```

Update

```text
xor = 5
```

---

#### Query

```text
1 4
```

Update

```text
xor = 5 ^ 4 = 1
```

---

#### Query

```text
0 2
```

Store

```text
2 ^ 1 = 3
```

Answer

```text
[3]
```

---

#### Query

```text
0 3
```

Store

```text
3 ^ 1 = 2
```

Answer

```text
[3,2]
```

---

#### Query

```text
0 6
```

Store

```text
6 ^ 1 = 7
```

Answer

```text
[3,2,7]
```

---

### Original Element

Initial array contains

```text
0
```

Store

```text
0 ^ 1 = 1
```

Answer

```text
[3,2,7,1]
```

---

### Sort

```text
[1,2,3,7]
```

Final Output

```text
[1,2,3,7]
```

---

# Logic Flow

```text
Start

↓

xor = 0

↓

Traverse queries from right to left

↓

Is query type 1?

↓

Yes

↓

xor ^= value

↓

No

↓

Store

value ^ xor

↓

Continue

↓

Add initial element

0 ^ xor

↓

Sort list

↓

Return answer

↓

End
```

---

# Complexity

## Time Complexity

Reverse traversal:

```text
O(Q)
```

Sorting the final list:

If there are `M` elements in the array,

```text
O(M log M)
```

where

```text
M = Number of insert queries + 1
```

Overall:

```text
O(Q + M log M)
```

In the worst case,

```text
O(Q log Q)
```

---

## Space Complexity

Result list:

```text
O(M)
```

Auxiliary variables:

```text
O(1)
```

Overall:

```text
O(M)
```

---

# Key Takeaways

- Applying XOR immediately after every query is inefficient.
- XOR is reversible, making reverse processing possible.
- A single cumulative XOR value represents all pending transformations.
- Every inserted value is adjusted exactly once using the cumulative XOR.
- Only the final list needs to be sorted before returning.

---

# Most Important Insight

Instead of updating every element after each XOR query, process the queries **in reverse** while maintaining a **cumulative XOR**. When an insertion is encountered, simply insert `value ^ cumulativeXOR`. This transforms repeated array updates into a single pass, dramatically improving efficiency.

---

# Summary

The solution exploits the reversible nature of the XOR operation. By traversing the queries from the end, all pending XOR operations are accumulated into one variable. Each inserted value is immediately converted to its final state using this cumulative XOR, eliminating the need to repeatedly modify the entire array. After adding the initial element, the resulting list is sorted and returned. The algorithm runs in **O(Q + M log M)** time with **O(M)** extra space, making it an efficient and elegant approach for handling large numbers of queries.
