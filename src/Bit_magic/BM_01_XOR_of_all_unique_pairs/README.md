# ⚡ Sum of XOR of All Pairs

## 📌 Problem Statement

Given an integer array `arr[]` of size `n`, find the sum of XOR values of all distinct pairs.

For every pair:

    (i, j) where 0 ≤ i < j < n

compute:

    arr[i] XOR arr[j]

and return the total sum.

---

## 🎯 Goal

Calculate:

    Σ (arr[i] XOR arr[j])

for all pairs satisfying:

    i < j

without explicitly generating every pair.

---

## 💡 Intuition

A brute force solution would generate every pair and compute XOR.

For an array of size:

    n

number of pairs is:

    n × (n - 1) / 2

which leads to:

    O(n²)

This is too slow for:

    n = 100000

Instead of looking at entire numbers, we analyze each bit independently.

Why?

Because XOR works bit by bit.

A bit contributes to XOR only when:

    one number has 0
    and
    the other has 1

So for every bit position, we can directly count how many pairs produce a set bit in the XOR.

---

## 🔥 Key Idea

For a particular bit position:

    i

Count:

    one  = numbers having ith bit set
    zero = numbers having ith bit unset

A pair contributes to XOR at this bit if:

    one number comes from one-group
    one number comes from zero-group

Number of such pairs:

    one × zero

Each such pair contributes:

    2^i

to the final answer.

Therefore contribution of bit i is:

    one × zero × 2^i

Total answer:

    Sum of contributions of all bits

---

## 🧠 Thought Process

Instead of calculating XOR for every pair:

For each bit position:

    Count numbers with bit = 1
    Count numbers with bit = 0

Every valid XOR pair requires:

    one set bit
    one unset bit

So:

    pairs = one × zero

Contribution of this bit:

    pairs × bitValue

where:

    bitValue = 2^i

Add contributions of all bits.

Since integers fit within 32 bits:

    iterate from bit 0 to bit 31

---

## 💻 Code

```java
static long sumXOR(int[] arr) {

    int n = arr.length;
    long ans = 0;

    for (int i = 0; i < 32; i++) {

        long zero = 0;
        long one = 0;

        for (int j = 0; j < n; j++) {

            if ((arr[j] & (1 << i)) != 0) {
                one++;
            } else {
                zero++;
            }
        }

        ans += zero * one * (1 << i);
    }

    return ans;
}
```

---

## 🧪 Dry Run

Input:

    arr = [7, 3, 5]

Binary Representation:

    7 = 111
    3 = 011
    5 = 101

Expected Answer:

    (7 XOR 3) +
    (7 XOR 5) +
    (3 XOR 5)

    = 4 + 2 + 6
    = 12

---

### Bit 0

Numbers:

    7 → 1
    3 → 1
    5 → 1

Count:

    one = 3
    zero = 0

Contribution:

    3 × 0 × 1
    = 0

Answer:

    ans = 0

---

### Bit 1

Numbers:

    7 → 1
    3 → 1
    5 → 0

Count:

    one = 2
    zero = 1

Pairs:

    2 × 1 = 2

Bit Value:

    2^1 = 2

Contribution:

    2 × 2
    = 4

Answer:

    ans = 4

---

### Bit 2

Numbers:

    7 → 1
    3 → 0
    5 → 1

Count:

    one = 2
    zero = 1

Pairs:

    2 × 1 = 2

Bit Value:

    2^2 = 4

Contribution:

    2 × 4
    = 8

Answer:

    ans = 12

---

### Remaining Bits

All bits are zero.

Contribution:

    0

---

Final Answer:

    12

---

## 🔁 Logic Flow

    Start
      |
      v
    Initialize answer = 0
      |
      v
    For each bit position 0 to 31
      |
      v
    Count:
        one  = set bits
        zero = unset bits
      |
      v
    Calculate:
        contribution =
        one × zero × 2^bit
      |
      v
    Add contribution to answer
      |
      v
    Repeat for all bits
      |
      v
    Return answer

---

## 📊 Complexity

### Time Complexity

Outer loop:

    32 bits

Inner loop:

    n elements

Total:

    O(32 × n)

Since 32 is constant:

    O(n)

---

### Space Complexity

Only a few variables are used:

    one
    zero
    ans

Therefore:

    O(1)

---

## 🎯 Key Takeaways

- XOR is independent for each bit.
- A XOR bit becomes 1 only when bits differ.
- For every bit:

      contribution =
      setBits × unsetBits × bitValue

- No need to generate all pairs.
- Bit counting reduces O(n²) to O(n).

---

## 🔥 Most Important Insight

For any bit position:

    XOR becomes 1

only when one number has:

    bit = 1

and the other has:

    bit = 0

Therefore:

    Number of contributing pairs

    = one × zero

and contribution becomes:

    one × zero × 2^bit

This completely eliminates pair generation.

---

## 🏁 Summary

To solve the problem:

    1. Iterate through all 32 bit positions.
    2. Count numbers with set and unset bits.
    3. Compute:
    
           contribution =
           one × zero × 2^bit
    
    4. Add contribution to answer.
    5. Return total sum.

Efficient solution:

    Time  : O(n)
    Space : O(1)

This bit manipulation approach transforms an O(n²) pair problem into an O(n) solution.