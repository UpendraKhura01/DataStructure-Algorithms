# ⚡ Fast Exponential

## 📌 Problem Statement

Given two integers:

    a
    b

calculate:

    a^b

efficiently.

A simple approach would multiply `a` by itself `b` times, which takes:

    O(b)

Instead, this solution uses **Binary Exponentiation** to calculate the power in:

    O(log b)

---

## 🎯 Goal

Calculate:

    a^b

using the minimum number of multiplications possible by taking advantage of the binary representation of `b`.

---

## 💡 Intuition

The key observation is that the exponent can be repeatedly divided by `2`.

For example:

    a^10

Since:

    10 = 1010₂

we can write:

    a^10 = a^8 × a^2

Instead of multiplying `a` ten times, we repeatedly square `a`:

    a
    a²
    a⁴
    a⁸
    a¹⁶
    ...

At every step, we check whether the current exponent is odd.

If it is odd, the current value of `a` contributes to the answer.

---

## 🔥 Key Idea

Use the binary representation of the exponent.

At every iteration:

    if b is odd:
        res = res × a

Then square the base:

    a = a × a

And divide the exponent by `2`:

    b = b >> 1

The right shift:

    b >> 1

is equivalent to:

    b / 2

for non-negative integers.

The important condition is:

    (b & 1) == 1

This checks whether `b` is odd.

---

## 🧠 Thought Process

Initially:

    res = 1

We maintain the invariant:

    res × a^b

represents the required power.

Suppose:

    a = 3
    b = 5

Binary representation:

    5 = 101₂

Therefore:

    3^5 = 3^4 × 3

The algorithm discovers these powers automatically:

    3¹
    3²
    3⁴

When the current exponent is odd, that power is included in `res`.

---

## 💻 Code

```java
private long power(long a, long b) {
    long res = 1;

    while (b > 0) {

        if ((b & 1) == 1) {
            res = res * a;
        }

        a = a * a;
        b >>= 1;
    }

    return res;
}
```

---

## 🧪 Dry Run

Input:

    a = 3
    b = 5

Binary:

    5 = 101₂

Initial:

    res = 1
    a = 3
    b = 5

### Step 1

`b = 5` is odd.

    res = 1 × 3
        = 3

Square the base:

    a = 3 × 3
      = 9

Divide exponent by 2:

    b = 5 >> 1
      = 2

---

### Step 2

`b = 2` is even.

No multiplication with `res`.

Square:

    a = 9 × 9
      = 81

Shift:

    b = 2 >> 1
      = 1

---

### Step 3

`b = 1` is odd.

    res = 3 × 81
        = 243

Square:

    a = 81 × 81
      = 6561

Shift:

    b = 1 >> 1
      = 0

Loop ends.

Final:

    res = 243

Therefore:

    3^5 = 243

---

## 🔁 Logic Flow

    Start
      |
      v
    res = 1
      |
      v
    Is b > 0?
      |
      +---- No ----> Return res
      |
      v
    Is b odd?
      |
      +---- Yes ----> res = res × a
      |
      v
    Square a
      |
      v
    b = b >> 1
      |
      v
    Repeat
      |
      v
    Return res

---

## 📊 Complexity

### Time Complexity

At every iteration:

    b = b / 2

Therefore, the number of iterations is:

    O(log b)

So:

    Time = O(log b)

---

### Space Complexity

Only a few variables are used:

    res
    a
    b

Therefore:

    Space = O(1)

---

## 🎯 Key Takeaways

- This is **Binary Exponentiation**.
- Do not multiply `a` by itself `b` times.
- Check whether `b` is odd using:

      (b & 1) == 1

- If `b` is odd, multiply the current base into `res`.
- Square the base after every iteration.
- Halve the exponent using:

      b >>= 1

- This reduces the time complexity from `O(b)` to `O(log b)`.

---

## 🔥 Most Important Insight

The entire optimization comes from:

    a^(2k) = (a^k)^2

and:

    a^(2k + 1) = a × (a^k)^2

Therefore, instead of processing every exponent individually, we repeatedly:

    Square the base
          ↓
    Halve the exponent
          ↓
    Take the base if exponent is odd

For:

    b = 5 = 101₂

the required powers are:

    5 = 4 + 1

so:

    a^5 = a^4 × a

The algorithm finds exactly these required powers.

---

## 🏁 Summary

Fast Exponential uses binary exponentiation to calculate:

    a^b

efficiently.

The process is:

    1. Start with `res = 1`.
    2. Check whether `b` is odd.
    3. If odd, multiply `a` into `res`.
    4. Square `a`.
    5. Divide `b` by `2`.
    6. Repeat until `b = 0`.

Core operations:

    if ((b & 1) == 1)
        res = res × a

    a = a × a

    b >>= 1

Complexity:

    Time  : O(log b)
    Space : O(1)