# 3658. GCD of Odd and Even Sums

**Difficulty:** Easy

You are given an integer `n`.

Compute the GCD (Greatest Common Divisor) of:

- `sumOdd` = sum of the first `n` positive odd numbers.
- `sumEven` = sum of the first `n` positive even numbers.

Return the GCD of these two sums.

---

## Examples

### Example 1

**Input**
```text
n = 4
```

**Output**
```text
4
```

**Explanation**

```text
sumOdd  = 1 + 3 + 5 + 7 = 16
sumEven = 2 + 4 + 6 + 8 = 20

GCD(16, 20) = 4
```

---

### Example 2

**Input**
```text
n = 5
```

**Output**
```text
5
```

**Explanation**

```text
sumOdd  = 1 + 3 + 5 + 7 + 9 = 25
sumEven = 2 + 4 + 6 + 8 + 10 = 30

GCD(25, 30) = 5
```

---

## Constraints

- `1 ≤ n ≤ 1000`

---

# Optimal Mathematical Approach

## Observation

The sum of the first `n` odd numbers is a well-known formula:

```text
sumOdd = n²
```

The sum of the first `n` even numbers is:

```text
sumEven = 2 + 4 + ... + 2n
        = 2(1 + 2 + ... + n)
        = 2 × n(n + 1) / 2
        = n(n + 1)
```

Therefore,

```text
GCD(sumOdd, sumEven)
= GCD(n², n(n + 1))
```

Factor out `n`:

```text
= n × GCD(n, n + 1)
```

Since consecutive integers are always coprime,

```text
GCD(n, n + 1) = 1
```

Hence,

```text
GCD(sumOdd, sumEven) = n
```

So the answer is simply:

```text
return n;
```

---

## Dry Run

### Input

```text
n = 4
```

```text
sumOdd  = 4² = 16
sumEven = 4 × 5 = 20

GCD(16,20)
= GCD(4²,4×5)
= 4 × GCD(4,5)
= 4 × 1
= 4
```

Answer:

```text
4
```

---

### Input

```text
n = 7
```

```text
sumOdd  = 49
sumEven = 56

GCD(49,56)
= 7
```

Answer:

```text
7
```

---

# Correctness Proof

We have

```text
sumOdd = n²
sumEven = n(n + 1)
```

Then,

```text
GCD(n², n(n + 1))
= n × GCD(n, n + 1)
```

Since

```text
GCD(n, n + 1) = 1
```

it follows that

```text
GCD(sumOdd, sumEven) = n
```

Thus returning `n` is always correct.

---

# Complexity Analysis

### Optimal Approach

- **Time Complexity:** `O(1)`
- **Space Complexity:** `O(1)`

---

# Java Solution (Optimal)

```java
class Solution {

    int gcdOfOddEvenSums(int n) {
        return n;
    }

}
```

---

# Alternative Approach (Using Euclidean GCD)

## Idea

Compute the sums using formulas and then apply the Euclidean Algorithm.

### Steps

1. Compute

```text
odd = n²
even = n(n + 1)
```

2. Find

```text
GCD(odd, even)
```

---

## Complexity

- **Time Complexity:** `O(log n)`
- **Space Complexity:** `O(1)`

---

## Java Solution

```java
class Solution {

    int bruteForce(int n) {

        int odd = n * n;
        int even = n * (n + 1);

        return gcd(odd, even);
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```