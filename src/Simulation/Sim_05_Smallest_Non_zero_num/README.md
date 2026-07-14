# Smallest Non-Zero Number

**Difficulty:** Medium

## Problem Statement

Given an array `arr[]`, find the **smallest non-negative integer `x`** such that after processing every element of the array sequentially, `x` never becomes negative.

For every element `arr[i]`:

- If `x > arr[i]`
  ```text
  x = x + (x - arr[i])
    = 2x - arr[i]
  ```

- Otherwise (`x ≤ arr[i]`)
  ```text
  x = x - (arr[i] - x)
    = 2x - arr[i]
  ```

Thus, irrespective of the condition, the update is always

```text
x = 2x - arr[i]
```

The value of `x` must remain **greater than or equal to 0** after every operation.

Return the smallest possible initial value of `x`.

---

## Examples

### Example 1

**Input**
```text
arr = [3, 4, 3, 2, 4]
```

**Output**
```text
4
```

**Explanation**

Start with `x = 4`.

| Element | New x |
|---------|-------|
|3|5|
|4|6|
|3|9|
|2|16|
|4|28|

The value never becomes negative.

---

### Example 2

**Input**
```text
arr = [4, 4]
```

**Output**
```text
3
```

**Explanation**

Start with `x = 3`.

```text
3 → 2 → 0
```

The value never becomes negative.

---

## Constraints

- `1 ≤ arr.length ≤ 10^6`
- `1 ≤ arr[i] ≤ 10^4`

---

# Observation

Every operation becomes

```text
new = 2 × old − arr[i]
```

Instead of guessing the starting value, work **backwards**.

Suppose we already know the minimum value required **after** processing the current element.

Let

```text
need = minimum value required after processing arr[i]
```

Before processing,

```text
2 × old − arr[i] ≥ need
```

Therefore,

```text
old ≥ (need + arr[i]) / 2
```

Since `old` must be an integer,

```text
old = ceil((need + arr[i]) / 2)
```

Integer ceiling can be computed as

```text
(old) = (need + arr[i] + 1) / 2
```

Process the array from **right to left**, repeatedly computing this minimum required value.

---

# Algorithm

1. Let `need = 0`.
2. Traverse the array from the last element to the first.
3. Update

```text
need = ceil((need + arr[i]) / 2)
```

implemented as

```java
need = (need + arr[i] + 1) / 2;
```

4. After processing all elements, `need` is the smallest valid starting number.

---

## Code

```java
int find(int[] arr) {

    int n = arr.length;
    long need = 0;

    for (int i = n - 1; i >= 0; i--) {

        need = (need + arr[i] + 1) / 2;

    }

    return (int) need;
}
```

---

## Dry Run

Input

```text
arr = [4, 4]
```

Start

```text
need = 0
```

### Last element = 4

```text
need = ceil((0 + 4)/2)
     = 2
```

### First element = 4

```text
need = ceil((2 + 4)/2)
     = 3
```

Answer

```text
3
```

Verification:

```text
3 → 2 → 0
```

Never negative.

---

## Another Dry Run

Input

```text
arr = [3,4,3,2,4]
```

Working backwards:

| arr[i] | Required value |
|--------|----------------|
|4|2|
|2|2|
|3|3|
|4|4|
|3|4|

Answer

```text
4
```

---

# Why Reverse Processing Works

Forward simulation depends on an unknown starting value.

Backward processing instead answers:

> "What is the minimum value required **before** this operation so that the remaining operations are always valid?"

Each step computes that minimum exactly, eventually yielding the smallest possible initial value.

---

# Complexity Analysis

- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`

---

# Summary

- Every update simplifies to

```text
x = 2x - arr[i]
```

- Process the array **from right to left**.
- At each step compute

```text
need = ceil((need + arr[i]) / 2)
```

- The final `need` is the smallest starting value that never becomes negative.

This provides an **optimal** solution with **O(n)** time and **O(1)** extra space.