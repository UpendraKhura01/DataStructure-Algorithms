# Number of Unique XOR Triplets I

**Difficulty:** Medium

## 🧩 Problem Statement

You are given an integer array **nums** of length **n**, where `nums` is a permutation of the integers from **1** to **n**.

A **XOR triplet** is defined as:

```
nums[i] XOR nums[j] XOR nums[k]
```

where

```
i ≤ j ≤ k
```

Return the **number of distinct XOR values** that can be obtained from all possible triplets.

---

## 📌 Examples

### Example 1

**Input**

```text
nums = [1,2]
```

**Output**

```text
2
```

**Explanation**

Possible triplets:

| Triplet | XOR |
|---------|-----|
|(0,0,0)|1|
|(0,0,1)|2|
|(0,1,1)|1|
|(1,1,1)|2|

Unique XOR values:

```
{1,2}
```

Answer:

```
2
```

---

### Example 2

**Input**

```text
nums = [3,1,2]
```

**Output**

```text
4
```

**Explanation**

Some triplets are:

```
3 ^ 3 ^ 3 = 3
3 ^ 3 ^ 1 = 1
3 ^ 3 ^ 2 = 2
3 ^ 1 ^ 2 = 0
```

Unique values:

```
{0,1,2,3}
```

Answer:

```
4
```

---

## 🔍 Observation

Since `nums` is a **permutation of 1...n**, every number in that range is available exactly once.

### Case 1

If

```
n = 1
```

Only one value exists.

Answer:

```
1
```

---

### Case 2

If

```
n = 2
```

Possible XOR values are only:

```
1
2
```

Answer:

```
2
```

---

### Case 3

For

```
n ≥ 3
```

A known property of XOR permutations is:

- Every XOR value representable with the required number of bits becomes achievable.
- The number of distinct XOR values equals the smallest power of two **strictly greater than** `n`.

For example:

| n | Answer |
|---|---------|
|3|4|
|4|8|
|5|8|
|6|8|
|7|8|
|8|16|

So we simply compute:

```
power = 1

while(power <= n)
    power *= 2
```

Return `power`.

---

## ▶ Dry Run

### Input

```text
nums = [1,2,3]
```

```
n = 3
```

Smallest power of two greater than 3:

```
1
2
4
```

Answer:

```
4
```

---

### Input

```text
nums = [1,2,3,4,5]
```

```
n = 5
```

Powers:

```
1
2
4
8
```

Answer:

```
8
```

---

## ⏱ Complexity Analysis

### Time Complexity

Finding the next power of two:

```text
O(log n)
```

---

### Space Complexity

```text
O(1)
```

---

## ✅ Java Solution

```java
public class Solution {

    int uniqueXorTriplets(int[] nums) {

        int n = nums.length;

        if (n == 1 || n == 2)
            return n;

        int power = 1;

        while (power <= n) {
            power <<= 1;
        }

        return power;
    }
}
```

---

## 💡 Key Idea

- For `n = 1` and `n = 2`, the answers are simply `1` and `2`.
- For every `n ≥ 3`, the set of XOR triplets covers **all values representable with the minimum number of bits**, resulting in the **smallest power of two greater than `n`**.

Thus the solution runs in **O(log n)** time with **O(1)** extra space.