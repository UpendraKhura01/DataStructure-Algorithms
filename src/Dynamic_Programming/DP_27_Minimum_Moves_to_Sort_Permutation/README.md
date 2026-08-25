
# Minimum Moves to Sort Permutation

---

# Problem Statement

Given an array `arr[]` containing every integer from:

```text
1 to n
```

exactly once, sort the array in ascending order.

In one operation, we can:

```text
Pick any element
+
Move it to the beginning OR the end of the array
```

Return the **minimum number of operations** required to sort the permutation.

---

### Example 1

```text
Input:
arr = [2, 1, 3]

Output:
1
```

Move `1` to the beginning:

```text
[2, 1, 3]
     ↓
[1, 2, 3]
```

Therefore:

```text
Answer = 1
```

---

### Example 2

```text
Input:
arr = [4, 3, 1, 2]

Output:
2
```

Move `3` to the end:

```text
[4, 3, 1, 2]
     ↓
[4, 1, 2, 3]
```

Then move `4` to the end:

```text
[4, 1, 2, 3]
 ↓
[1, 2, 3, 4]
```

Therefore:

```text
Answer = 2
```

---

# Goal

Find the minimum number of elements that must be moved to the beginning or end so that the remaining elements are already in their correct relative order.

The key observation is that we should **not** try to simulate every possible move.

Instead, we find the largest group of consecutive values that already appear in the correct order.

If that group has length `max`, then all other elements can be moved to the boundaries.

Therefore:

```text
Minimum Moves = n - max
```

---

# Intuition

The sorted array must be:

```text
[1, 2, 3, 4, ..., n]
```

Consider:

```text
arr = [4, 3, 1, 2]
```

The values:

```text
1, 2
```

already appear in the correct relative order.

They form a consecutive sequence:

```text
1 → 2
```

We can keep them in the middle and move everything else around them.

The elements:

```text
3, 4
```

need to be moved.

Therefore:

```text
Minimum moves = 4 - 2 = 2
```

So the problem becomes:

> Find the longest sequence of consecutive values `x, x+1, x+2, ...` that already appears in the correct order in the permutation.

---

# Key Idea

We use dynamic programming indexed by the **value**, not the array position.

Define:

```text
dp[x] = length of the longest valid consecutive-value sequence
        ending at value x
```

When processing a value:

```text
num = arr[i]
```

the sequence can be extended from:

```text
num - 1
```

Therefore:

```text
dp[num] = dp[num - 1] + 1
```

Since every value appears exactly once, there is no ambiguity.

For example:

```text
arr = [4, 3, 1, 2]
```

Process in order:

```text
4:
dp[4] = dp[3] + 1 = 1

3:
dp[3] = dp[2] + 1 = 1

1:
dp[1] = dp[0] + 1 = 1

2:
dp[2] = dp[1] + 1 = 2
```

The maximum is:

```text
max = 2
```

Therefore:

```text
answer = n - max
       = 4 - 2
       = 2
```

---

# Thought Process

### Step 1: Understand What Can Stay

Suppose a set of elements is already in the correct relative order:

```text
x, x+1, x+2, ..., y
```

These elements do not need to be moved.

All elements outside this sequence can be moved to the beginning or end.

Therefore, we want to maximize the number of elements we can keep.

---

### Step 2: Consecutive Values Are Important

The sorted permutation requires:

```text
1, 2, 3, 4, ..., n
```

So the elements that remain untouched must represent a consecutive range of values.

For example:

```text
2,3,4,5
```

is a valid group.

But:

```text
2,4,5
```

cannot remain as the complete central group because `3` must appear between `2` and `4` in the sorted array.

---

### Step 3: Track the Previous Value

For every value:

```text
num
```

we ask:

```text
Was num - 1 already seen?
```

If yes, then we can extend the existing sequence:

```text
dp[num] = dp[num - 1] + 1
```

Otherwise:

```text
dp[num] = 1
```

The implementation achieves this automatically because the initial values of `dp` are zero.

---

### Step 4: Track the Longest Sequence

Maintain:

```java
max
```

After calculating:

```text
dp[num]
```

update:

```text
max = Math.max(max, dp[num])
```

At the end:

```text
max = maximum number of elements that can remain unmoved
```

---

### Step 5: Calculate the Moves

There are `n` total elements.

If we can keep `max` elements in their required consecutive order, then:

```text
n - max
```

elements need to be moved.

Therefore:

```text
Answer = n - max
```

---

# Code (Functions Only)

```java
int minMoves(int[] arr) {

    int n = arr.length;

    int[] dp = new int[n + 1];

    int max = 0;

    for (int i = 0; i < n; i++) {

        int num = arr[i];

        dp[num] = dp[num - 1] + 1;

        max = Math.max(max, dp[num]);
    }

    return n - max;
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
arr = [4,3,1,2]
```

The sorted array should be:

```text
[1,2,3,4]
```

Initialize:

```text
n = 4
dp = [0,0,0,0,0]
max = 0
```

---

### Step 1: Process `4`

```text
num = 4
```

Use:

```text
dp[4] = dp[3] + 1
      = 0 + 1
      = 1
```

Update:

```text
max = 1
```

State:

```text
dp[4] = 1
max = 1
```

---

### Step 2: Process `3`

```text
num = 3
```

Use:

```text
dp[3] = dp[2] + 1
      = 0 + 1
      = 1
```

Update:

```text
max = 1
```

State:

```text
dp[3] = 1
dp[4] = 1
max = 1
```

---

### Step 3: Process `1`

```text
num = 1
```

Use:

```text
dp[1] = dp[0] + 1
      = 0 + 1
      = 1
```

Update:

```text
max = 1
```

---

### Step 4: Process `2`

```text
num = 2
```

Now:

```text
dp[1] = 1
```

Therefore:

```text
dp[2] = dp[1] + 1
      = 1 + 1
      = 2
```

This represents the consecutive sequence:

```text
1 → 2
```

Update:

```text
max = 2
```

---

### Final Calculation

We have:

```text
n = 4
max = 2
```

Therefore:

```text
answer = n - max
       = 4 - 2
       = 2
```

So:

```text
Minimum Moves = 2
```

---

### Dry Run Table

```text
i   num   dp[num]              max
-----------------------------------
0    4     dp[3] + 1 = 1        1
1    3     dp[2] + 1 = 1        1
2    1     dp[0] + 1 = 1        1
3    2     dp[1] + 1 = 2        2
```

The longest consecutive sequence is:

```text
1, 2
```

Its length is:

```text
2
```

Elements that must be moved:

```text
4 - 2 = 2
```

---

## Another Example

Consider:

```text
arr = [2,1,3]
```

Process:

```text
2:
dp[2] = dp[1] + 1
      = 1

1:
dp[1] = dp[0] + 1
      = 1

3:
dp[3] = dp[2] + 1
      = 2
```

Therefore:

```text
max = 2
```

The consecutive sequence is:

```text
2,3
```

So:

```text
answer = 3 - 2
       = 1
```

Move `1` to the beginning:

```text
[2,1,3]
   ↓
[1,2,3]
```

---

# Logic Flow

```text
Start

↓

n = arr.length

↓

Create dp[n + 1]

↓

max = 0

↓

Traverse arr

↓

num = arr[i]

↓

Find longest consecutive sequence
ending at num

↓

dp[num] = dp[num - 1] + 1

↓

Update:

max = max(max, dp[num])

↓

Continue until all elements
are processed

↓

max = largest group that can
remain unmoved

↓

Moves = n - max

↓

Return moves

↓

End
```

---

# Complexity

We process every element exactly once.

For each element, we perform constant-time array accesses:

```text
dp[num]
dp[num - 1]
```

Therefore:

```text
Time Complexity = O(N)
```

The `dp` array contains `n + 1` elements.

Therefore:

```text
Space Complexity = O(N)
```

---

# Key Takeaways

- The problem is not about simulating moves.
- We should find the largest group of elements that can remain untouched.
- The elements that remain must form a consecutive sequence of values.
- `dp[num]` stores the longest valid consecutive sequence ending at `num`.
- The transition is:
  ```text
  dp[num] = dp[num - 1] + 1
  ```
- `max` stores the largest such sequence.
- Every element outside this sequence must be moved.
- Therefore:
  ```text
  Minimum Moves = n - max
  ```
- The permutation property is crucial because every value from `1` to `n` appears exactly once.
- The solution runs in:
  ```text
  O(N)
  ```
  time and:
  ```text
  O(N)
  ```
  space.

---

# Most Important Insight

The most important observation is:

> **Instead of finding the elements to move, find the maximum number of elements that do not need to move.**

In the sorted array, consecutive values must appear together in this order:

```text
x, x+1, x+2, ..., y
```

If these values already appear in the correct relative order in the input permutation, we can leave them untouched.

For example:

```text
arr = [4, 3, 1, 2]
```

The sequence:

```text
1, 2
```

already appears in the correct order.

So we keep:

```text
1, 2
```

and move:

```text
3, 4
```

Hence:

```text
moves = 4 - 2 = 2
```

The DP captures this with:

```text
dp[num] = dp[num - 1] + 1
```

This means:

```text
If num - 1 has already appeared
in the correct sequence,
then num can extend that sequence.
```

For example:

```text
dp[1] = 1

dp[2] = dp[1] + 1 = 2

dp[3] = dp[2] + 1 = 3
```

The order in which values appear in the original permutation determines whether this chain can be formed.

The final transformation is:

```text
Longest sequence that stays
            ↓
           max
            ↓
Total elements - elements kept
            ↓
         n - max
            ↓
Minimum moves
```

---

# Summary

The problem allows us to move any element to either the beginning or the end.

Instead of simulating those operations, we identify the largest consecutive sequence of values that already appears in the correct relative order.

For every value `num`:

```text
dp[num] = dp[num - 1] + 1
```

This gives the length of the longest consecutive sequence ending at `num`.

Then:

```text
max = longest sequence that can remain untouched
```

and the remaining elements must be moved:

```text
Minimum Moves = n - max
```

For:

```text
arr = [4,3,1,2]
```

we find:

```text
Longest sequence = [1,2]
Length = 2
```

Therefore:

```text
Minimum Moves = 4 - 2 = 2
```

Final complexity:

```text
Time  : O(N)
Space : O(N)
```

The core pattern to remember is:

```text
Find maximum consecutive-value subsequence
                  ↓
             Keep those elements
                  ↓
          Move all remaining elements
                  ↓
             n - longest
```
