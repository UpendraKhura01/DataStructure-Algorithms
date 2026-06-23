# 🧮 Binary String With Equal Sum of Two Halves

## 📌 Problem Statement

You are given an integer `n`.

You need to compute a combinational value based on:

    nC0² + nC1² + nC2² + ... + nCn²

Return the result modulo:

    1e9 + 7

---

## 🎯 Goal

Efficiently compute:

    Σ (C(n, r) × C(n, r))

for all:

    r ∈ [0, n]

---

## 💡 Intuition

This is a classic combinatorics identity problem.

We are summing:

    (nCr)²

Instead of computing each term independently, we use:

### Precomputation:
- factorial
- modular inverse factorial

This allows fast computation of:

    nCr in O(1)

Then we simply sum all squared combinations.

---

## 🔥 Key Idea

We use:

### 1. Factorials

    fact[i] = i!

### 2. Modular Inverse

Using Fermat’s theorem:

    inv(x) = x^(MOD-2)

So:

    nCr = fact[n] / (fact[r] × fact[n-r])

Under modulo:

    nCr = fact[n] × inv[r] × inv[n-r]

---

## 🧠 Thought Process

### Step 1: Precompute factorials

We compute:

    fact[0 ... 2n]

---

### Step 2: Compute inverse factorials

Using:

    inv[i] = (i!)⁻¹ mod M

via modular exponentiation.

---

### Step 3: Compute answer

For each `r`:

    ans += (nCr × nCr)

---

## 💻 Code

### 🔹 O(N) Approach

```java
long M = 1000000007;

public int computeValue(int n) {

    long[] fact = new long[2 * n + 1];
    long[] inv = new long[2 * n + 1];

    fact[0] = 1;

    for (int i = 1; i <= 2 * n; i++) {
        fact[i] = (fact[i - 1] * i) % M;
    }

    inv[2 * n] = power(fact[2 * n], M - 2);

    for (int i = 2 * n - 1; i >= 0; i--) {
        inv[i] = (inv[i + 1] * (i + 1)) % M;
    }

    long ans = 0;

    for (int r = 0; r <= n; r++) {
        long c = ncr(n, r, fact, inv);
        ans = (ans + (c * c) % M) % M;
    }

    return (int) ans;
}

private long ncr(int n, int r, long[] fact, long[] inv) {

    if (r < 0 || r > n) return 0;

    long res = fact[n];
    res = (res * inv[r]) % M;
    res = (res * inv[n - r]) % M;

    return res;
}
```

---

### 🔹 Optimized O(1) Summation Approach

Using identity:

    Σ (nCr)² = (2n)! / (n!)²

```java
int computeValue2(int n) {

    long[] fact = new long[2 * n + 1];

    fact[0] = 1;

    for (int i = 1; i <= 2 * n; i++) {
        fact[i] = (fact[i - 1] * i) % M;
    }

    long top = fact[2 * n];
    long temp = (fact[n] * fact[n]) % M;

    long inv_down = power(temp, M - 2);

    long ans = (top * inv_down) % M;

    return (int) ans;
}
```

---

### 🔹 Modular Exponentiation

```java
private long power(long a, long b) {

    long res = 1;

    while (b > 0) {

        if ((b & 1) == 1) {
            res = (res * a) % M;
        }

        a = (a * a) % M;
        b >>= 1;
    }

    return res;
}
```

---

## 🧪 Dry Run

Input:

    n = 3

---

### Step 1: Compute combinations

| r | nCr | nCr² |
|---|-----|------|
| 0 | 1   | 1    |
| 1 | 3   | 9    |
| 2 | 3   | 9    |
| 3 | 1   | 1    |

---

### Step 2: Sum

    1 + 9 + 9 + 1 = 20

---

Final Answer:

    20

---

## 🔁 Logic Flow

    Start
      |
      v
    Precompute factorials
      |
      v
    Precompute inverse factorials
      |
      v
    For r = 0 to n:
        compute nCr
        add (nCr * nCr)
      |
      v
    Return result mod M

---

## 📊 Complexity

### O(N) Approach

Time:

    O(n)

Space:

    O(n)

---

### Optimized Approach

Time:

    O(n) preprocessing + O(1) formula use

Space:

    O(n)

---

## 🎯 Key Takeaways

- Problem reduces to sum of squared combinations
- Use factorial + modular inverse for fast nCr
- Identity simplifies full summation
- Modular exponentiation is essential

---

## 🔥 Most Important Insight

Instead of computing:

    (nCr)² for every r

We can directly use identity:

    Σ (nCr)² = (2n)! / (n!)²

This reduces repeated computation dramatically.

---

## 🏁 Summary

To solve:

    1. Precompute factorials
    2. Compute nCr using modular inverse
    3. Sum squares of nCr
    OR
    4. Use combinatorial identity for direct answer

Efficient solution:

    Time  : O(n)
    Space : O(n)