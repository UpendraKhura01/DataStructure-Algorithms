
# Longest Subsequence With Non-Zero Bitwise XOR

---

# Problem Statement

Given an integer array `nums`, return the length of the **longest subsequence** whose bitwise XOR is non-zero.

If no such subsequence exists, return:

```text
0
```

A subsequence does not need to be contiguous. We can choose any elements while maintaining their original relative order.

---

### Example 1

```text
Input:
nums = [1,2,3]

Output:
2
```

The XOR of the complete array is:

```text
1 ^ 2 ^ 3 = 0
```

So the entire array cannot be used.

Remove `1`:

```text
[2,3]
```

Now:

```text
2 ^ 3 = 1
```

which is non-zero.

Therefore:

```text
Answer = 2
```

---

### Example 2

```text
Input:
nums = [2,3,4]

Output:
3
```

XOR of the complete array:

```text
2 ^ 3 ^ 4 = 5
```

Since:

```text
5 != 0
```

the entire array is already a valid subsequence.

Therefore:

```text
Answer = 3
```

---

# Goal

Find the maximum number of elements that can be selected from `nums` such that their XOR is:

```text
!= 0
```

The key is to determine whether the **entire array** already has a non-zero XOR.

If it does, the answer is immediately `n`.

If the XOR is zero, we need to determine whether removing one element can make it non-zero.

---

# Intuition

The first thing to calculate is the XOR of the entire array:

```text
xor = nums[0] ^ nums[1] ^ ... ^ nums[n-1]
```

There are two important cases.

### Case 1: Total XOR is non-zero

If:

```text
xor != 0
```

then the entire array is already a valid subsequence.

The entire array has the maximum possible length:

```text
Answer = n
```

There is no reason to remove anything.

---

### Case 2: Total XOR is zero

If:

```text
xor == 0
```

the complete array is invalid.

We want the longest possible subsequence, so ideally we remove only **one element**.

If there is at least one non-zero element, removing that element will make the XOR non-zero.

Why?

Let the total XOR be:

```text
X = 0
```

Suppose we remove an element `x`.

The XOR of the remaining elements is:

```text
0 ^ x
```

Since:

```text
0 ^ x = x
```

If:

```text
x != 0
```

then the remaining XOR is non-zero.

Therefore, if the array contains any non-zero element:

```text
Answer = n - 1
```

---

### Case 3: Every element is zero

If every element is:

```text
0
```

then every possible subsequence also has XOR:

```text
0
```

There is no valid subsequence.

Therefore:

```text
Answer = 0
```

---

# Key Idea

The entire problem can be reduced to three checks:

```text
1. Is there any non-zero element?
2. What is the XOR of the entire array?
3. Based on those two values, return n, n-1, or 0.
```

The decision table is:

```text
Non-zero element exists?     Total XOR       Answer
----------------------------------------------------
No                           0               0
Yes                          != 0            n
Yes                          0               n - 1
```

Because the array contains only non-negative integers, checking:

```java
i > 0
```

is enough to determine whether an element is non-zero.

---

# Thought Process

### Step 1: Calculate the XOR

Initialize:

```java
int xor = 0;
```

For every number:

```java
xor = xor ^ i;
```

After the loop:

```text
xor = XOR of the entire array
```

---

### Step 2: Check Whether a Non-Zero Element Exists

Maintain:

```java
boolean non_zero = false;
```

Whenever:

```java
i > 0
```

set:

```java
non_zero = true;
```

This tells us whether removing one element can produce a non-zero XOR when the total XOR is zero.

---

### Step 3: If All Elements Are Zero

If:

```java
non_zero == false
```

then every element is zero.

Any subsequence has XOR:

```text
0 ^ 0 ^ 0 ^ ... = 0
```

So:

```text
Answer = 0
```

---

### Step 4: If Total XOR Is Non-Zero

If:

```java
xor != 0
```

the complete array is valid.

Therefore:

```text
Answer = n
```

---

### Step 5: Total XOR Is Zero but a Non-Zero Element Exists

We know:

```text
xor = 0
```

and there is at least one:

```text
x != 0
```

Remove that one element.

Remaining XOR:

```text
0 ^ x = x
```

Since:

```text
x != 0
```

the remaining XOR is non-zero.

Thus:

```text
Answer = n - 1
```

---

# Code (Functions Only)

```java
int longestSubsequence(int[] nums) {

    int n = nums.length;

    int xor = 0;
    boolean non_zero = false;

    for (int i : nums) {

        xor = xor ^ i;

        if (i > 0) {
            non_zero = true;
        }
    }

    if (non_zero == false) {
        return 0;
    }

    if (xor != 0) {
        return n;
    }

    if (xor == 0 && non_zero) {
        return n - 1;
    }

    return -1;
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
nums = [1,2,3]
```

### Step 1

Initialize:

```text
xor = 0
non_zero = false
```

---

### Step 2: Process `1`

```text
xor = 0 ^ 1
     = 1
```

Since:

```text
1 > 0
```

we set:

```text
non_zero = true
```

Current state:

```text
xor       = 1
non_zero  = true
```

---

### Step 3: Process `2`

```text
xor = 1 ^ 2
     = 3
```

`2` is non-zero, so:

```text
non_zero = true
```

Current state:

```text
xor       = 3
non_zero  = true
```

---

### Step 4: Process `3`

```text
xor = 3 ^ 3
     = 0
```

`3` is non-zero:

```text
non_zero = true
```

Final state:

```text
xor       = 0
non_zero  = true
n         = 3
```

---

### Step 5: Apply Conditions

The array contains a non-zero element:

```text
non_zero = true
```

But:

```text
xor = 0
```

Therefore:

```text
Answer = n - 1
       = 3 - 1
       = 2
```

One valid subsequence is:

```text
[2,3]
```

Its XOR:

```text
2 ^ 3 = 1
```

which is non-zero.

---

## Second Dry Run

Consider:

```text
nums = [2,3,4]
```

Calculate XOR:

```text
xor = 0 ^ 2
     = 2

xor = 2 ^ 3
     = 1

xor = 1 ^ 4
     = 5
```

At least one element is non-zero:

```text
non_zero = true
```

And:

```text
xor = 5 != 0
```

Therefore:

```text
Answer = n
       = 3
```

The entire array works:

```text
2 ^ 3 ^ 4 = 5
```

---

## Third Dry Run

Consider:

```text
nums = [0,0,0]
```

XOR:

```text
0 ^ 0 ^ 0 = 0
```

No non-zero element exists:

```text
non_zero = false
```

Therefore:

```text
Answer = 0
```

There is no subsequence with non-zero XOR.

---

# Logic Flow

```text
Start

↓

Initialize:
xor = 0
non_zero = false

↓

Traverse every element

↓

xor = xor ^ element

↓

Is element > 0?

   Yes
    |
    v
non_zero = true

↓

After traversal

↓

Are all elements zero?

non_zero == false
       |
       v
Return 0

       No
       |
       v
Is total XOR non-zero?

xor != 0
   |
   v
Return n

   No
   |
   v
Total XOR = 0
but a non-zero element exists

↓

Remove one non-zero element

↓

Remaining XOR becomes non-zero

↓

Return n - 1

↓

End
```

---

# Complexity

We traverse the array exactly once.

For each element, we perform:

```text
XOR operation
+
constant-time comparison
```

Therefore:

```text
Time Complexity = O(N)
```

Only a few variables are used.

Therefore:

```text
Space Complexity = O(1)
```

---

# Key Takeaways

- XOR of the entire array is the first thing to calculate.
- If the total XOR is non-zero, the entire array is the answer.
- If the total XOR is zero but at least one element is non-zero, removing one non-zero element gives a non-zero XOR.
- If every element is zero, no valid subsequence exists.
- The final answer is always one of:
  ```text
  0
  n - 1
  n
  ```
- No dynamic programming, recursion, sorting, or extra data structure is required.
- The solution uses constant extra space.

---

# Most Important Insight

The key XOR property is:

```text
x ^ x = 0
```

and:

```text
0 ^ x = x
```

Suppose the XOR of the complete array is:

```text
0
```

and we remove a non-zero element `x`.

The XOR of all remaining elements is:

```text
0 ^ x
```

which becomes:

```text
x
```

Since:

```text
x != 0
```

the remaining subsequence has a non-zero XOR.

Therefore:

```text
Total XOR != 0
    → use all n elements

Total XOR == 0
    + at least one non-zero element
    → remove exactly one element
    → answer n - 1

All elements == 0
    → impossible
    → answer 0
```

This is the entire mathematical reason the solution works.

The most important observation is that **we never need to search through subsequences**.

Instead, the XOR of the complete array tells us whether we can keep everything. If not, one non-zero element is enough to repair the XOR.

---

# Summary

The problem asks for the longest subsequence whose XOR is non-zero.

The solution uses a simple XOR observation.

First calculate:

```text
XOR = nums[0] ^ nums[1] ^ ... ^ nums[n-1]
```

Then:

```text
If every element is 0:
    return 0

Else if total XOR != 0:
    return n

Else:
    return n - 1
```

The reason `n - 1` works when the total XOR is zero is:

```text
remaining XOR
= total XOR ^ removed element
= 0 ^ x
= x
```

For any non-zero `x`, this is non-zero.

Final complexity:

```text
Time  : O(N)
Space : O(1)
```

The core pattern to remember is:

```text
Check complete solution
        ↓
If valid → take everything
        ↓
If invalid → remove one non-zero element
        ↓
If no non-zero element exists → impossible
```
