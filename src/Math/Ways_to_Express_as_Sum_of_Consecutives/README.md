# Ways to Express as Sum of Consecutive Numbers

**Difficulty:** Medium

## Problem Statement

Given a positive integer `n`, find the number of ways to represent it as the sum of **two or more consecutive natural numbers**.

A sequence must:

- Contain **at least 2** numbers.
- Contain **consecutive positive integers**.
- Sum exactly to `n`.

---

## Examples

### Example 1

**Input**

```text
n = 10
```

**Output**

```text
1
```

**Explanation**

```
10 = 1 + 2 + 3 + 4
```

No other consecutive sequence exists.

---

### Example 2

**Input**

```text
n = 15
```

**Output**

```text
3
```

**Explanation**

```
15 = 1 + 2 + 3 + 4 + 5

15 = 4 + 5 + 6

15 = 7 + 8
```

Hence the answer is **3**.

---

# Observation

Suppose a sequence has **k consecutive numbers**.

Let the first number be **x**.

Then the sequence is

```
x
x + 1
x + 2
...
x + k - 1
```

The sum becomes

```
x + (x+1) + (x+2) + ... + (x+k-1)
```

---

## Formula

The sum of these numbers is

\[
k \times x + \frac{k(k-1)}{2}
\]

Therefore,

\[
n = kx + \frac{k(k-1)}{2}
\]

Rearranging,

\[
x = \frac{n-\frac{k(k-1)}2}{k}
\]

For a valid sequence,

- `x` must be an integer.
- `x ≥ 1`.

---

# Algorithm

For every possible sequence length `k`:

1. Compute

```
remaining = n - k(k-1)/2
```

2. If

```
remaining <= 0
```

stop the loop.

3. Check

```
remaining % k == 0
```

If true,

```
x = remaining / k
```

is a positive integer.

Count this sequence.

---

# Why do we stop when

```
k(k−1)/2 >= n ?
```

Even if the starting number is **1**, the smallest possible sum of `k` consecutive numbers is

```
1 + 2 + ... + k
```

which equals

\[
\frac{k(k+1)}2
\]

Our rearranged formula subtracts

\[
\frac{k(k-1)}2
\]

from `n`.

Once

\[
\frac{k(k-1)}2 \ge n
\]

the value of

```
remaining
```

becomes zero or negative.

Therefore,

```
x ≤ 0
```

which is invalid.

So no larger value of `k` can produce a valid sequence.

---

# Dry Run

## Example

```
n = 15
```

### k = 2

```
remaining = 15 - 1 = 14

14 % 2 = 0

x = 7
```

Sequence

```
7 8
```

Valid.

Count = 1

---

### k = 3

```
remaining = 15 - 3 = 12

12 % 3 = 0

x = 4
```

Sequence

```
4 5 6
```

Count = 2

---

### k = 4

```
remaining = 15 - 6 = 9

9 % 4 ≠ 0
```

Invalid.

---

### k = 5

```
remaining = 15 - 10 = 5

5 % 5 = 0

x = 1
```

Sequence

```
1 2 3 4 5
```

Count = 3

---

### k = 6

```
remaining = 15 - 15 = 0
```

Stop.

Answer

```
3
```

---

# Correctness Proof

Assume a valid sequence has length `k` and starts at `x`.

Its sum is

\[
kx+\frac{k(k-1)}2=n
\]

Thus,

\[
x=\frac{n-\frac{k(k-1)}2}{k}
\]

The algorithm checks every possible `k`.

For each `k`, it verifies that

- `x` is an integer.
- `x ≥ 1`.

Every valid representation corresponds to exactly one pair `(k, x)`.

Therefore, every valid sequence is counted exactly once.

Hence the algorithm is correct.

---

# Complexity Analysis

Let

```
k(k−1)/2 < n
```

Then

```
k ≈ √(2n)
```

Therefore,

### Time Complexity

```text
O(√n)
```

---

### Space Complexity

```text
O(1)
```

---

# Java Solution

```java
class Solution {

    int getCount(int n) {

        int count = 0;

        for (int k = 2; k * (k - 1) / 2 < n; k++) {

            int remaining = n - (k * (k - 1)) / 2;

            if (remaining % k == 0 && remaining >= 1) {
                count++;
            }
        }

        return count;
    }
}
```

---

# Key Takeaways

- Assume the sequence has length **k**.
- Derive the starting number using the arithmetic progression formula.
- The starting number must be:
    - Positive.
    - An integer.
- Only iterate while

```
k(k−1)/2 < n
```

making the solution run in **O(√n)** time.

---

# Tags

- Math
- Arithmetic Progression
- Number Theory
- Simulation
- Greedy