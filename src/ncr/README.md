# 🧮 nCr Using Factorials and Modular Inverse

## 📌 Problem Statement

Given two integers:

    n
    r

calculate the binomial coefficient:

    nCr = n! / (r! × (n-r)!)

The result is returned modulo:

    M = 1,000,000,007

The challenge is to calculate `nCr` efficiently while avoiding direct division under modulo arithmetic.

---

## 🎯 Goal

Calculate:

    nCr

using:

    n! × inverse(r!) × inverse((n-r)!)

under modulo `1,000,000,007`.

The key formula is:

    nCr = n! / (r! × (n-r)!)

Under modular arithmetic:

    nCr = n! × (r!)^(-1) × ((n-r)!)^(-1) mod M

---

## 💡 Intuition

Normally, we calculate:

    nCr = n! / (r! × (n-r)!)

But division does not work directly with modulo.

For example:

    a / b mod M

cannot simply be calculated using:

    a * (1 / b)

Instead, we use the **modular inverse** of `b`.

The modular inverse of `b` is a number `x` such that:

    b × x ≡ 1 (mod M)

Since:

    M = 1,000,000,007

is prime, Fermat's Little Theorem gives:

    b^(M-2) mod M

as the modular inverse of `b`.

Therefore:

    inverse(b) = power(b, M - 2)

---

## 🔥 Key Idea

The solution uses three main ideas.

### 1. Precompute Factorials

Calculate:

    fact[i] = i! mod M

For example:

    fact[0] = 1
    fact[1] = 1
    fact[2] = 2
    fact[3] = 6
    fact[4] = 24

The recurrence is:

    fact[i] = fact[i - 1] × i mod M

---

### 2. Calculate Inverse Factorials

Instead of calculating the inverse of every factorial separately, first calculate:

    inv[2n] = inverse(fact[2n])

using:

    power(fact[2n], M - 2)

Then calculate the remaining inverse factorials backwards:

    inv[i] = inv[i + 1] × (i + 1) mod M

This works because:

    i! × (i + 1) = (i + 1)!

Therefore:

    inverse(i!)
    = inverse((i + 1)!) × (i + 1)

---

### 3. Apply the nCr Formula

Finally:

    nCr = fact[n] × inv[r] × inv[n-r] mod M

So the required division is converted into multiplication using modular inverses.

---

## 🧠 Thought Process

### Step 1: Handle Invalid r

A combination is invalid when:

    r < 0

or:

    r > n

Therefore:

    return 0

---

### Step 2: Build Factorials

The code creates:

    fact[0 ... 2n]

and calculates all factorials modulo `M`.

The size is:

    2 × n + 1

---

### Step 3: Find One Inverse

We calculate:

    inv[2n] = inverse(fact[2n])

using fast exponentiation:

    inv[2n] = fact[2n]^(M-2) mod M

This takes:

    O(log M)

---

### Step 4: Build All Other Inverses

Starting from `2n - 1`, calculate:

    inv[i] = inv[i+1] × (i+1) mod M

This gives the inverse factorial for every required index.

---

### Step 5: Calculate nCr

Use:

    fact[n]
    inv[r]
    inv[n-r]

Therefore:

    result =
        fact[n] × inv[r] × inv[n-r] mod M

---

## 💻 Code

```java
long M = 1000000007;

long ncr(int n, int r) {
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

    if (r < 0 || r > n) {
        return 0;
    }

    long res = fact[n];

    res = (res * inv[r]) % M;
    res = (res * inv[n - r]) % M;

    return res;
}

long power(long a, long b) {
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

    n = 5
    r = 2

We need:

    5C2 = 10

### Step 1: Factorials

    fact[5] = 5!
            = 120

    fact[2] = 2
    fact[3] = 6

### Step 2: Formula

    5C2 = 5! / (2! × 3!)

Therefore:

    5C2 = 120 / (2 × 6)

    = 120 / 12
    = 10

Using modular inverses:

    result =
        fact[5] × inv[2] × inv[3] mod M

    result = 10

Therefore:

    Output = 10

---

## 🔁 Logic Flow

    Start
      |
      v
    Receive n and r
      |
      v
    Is r invalid?
      |
      +---- Yes ----> Return 0
      |
      v
    Calculate factorials
      |
      v
    Calculate inverse of fact[2n]
      |
      v
    Build inverse factorials backwards
      |
      v
    fact[n]
      |
      +---- × inv[r]
      |
      +---- × inv[n-r]
      |
      v
    Apply modulo M
      |
      v
    Return nCr

---

## 📊 Complexity

Let:

    N = 2n

### Time Complexity

Building factorials:

    O(N)

Building inverse factorials:

    O(N)

Computing the first modular inverse using fast exponentiation:

    O(log M)

Therefore:

    Time = O(n + log M)

Since `M` is fixed:

    Time ≈ O(n)

---

### Space Complexity

Two arrays are created:

    fact[2n + 1]
    inv[2n + 1]

Therefore:

    Space = O(n)

---

## 🎯 Key Takeaways

- Use the combination formula:

      nCr = n! / (r! × (n-r)!)

- Direct division cannot be used normally under modulo arithmetic.
- Replace division with modular inverse.
- Since `M` is prime:

      inverse(x) = x^(M-2) mod M

- Use fast exponentiation to calculate the modular inverse.
- Precompute factorials.
- Calculate inverse factorials backwards.
- Finally:

      nCr = fact[n] × inv[r] × inv[n-r] mod M

---

## 🔥 Most Important Insight

The most important concept is converting division into multiplication under modulo arithmetic.

Normally:

    nCr = n! / (r! × (n-r)!)

Under modulo `M`:

    nCr =
        n!
        × inverse(r!)
        × inverse((n-r)!)
        mod M

And because `M` is prime:

    inverse(x) = x^(M-2) mod M

So:

    nCr =
        fact[n]
        × inv[r]
        × inv[n-r]
        mod M

The second important optimization is that we do **not** calculate every inverse using exponentiation.

We calculate only one:

    inv[2n]

and derive the rest backwards:

    inv[i] = inv[i+1] × (i+1) mod M

This keeps the overall preprocessing efficient.

---

## 🏁 Summary

The solution calculates `nCr` using:

    Factorials
          ↓
    Modular Inverses
          ↓
    Fast Exponentiation
          ↓
    Combination Formula

Steps:

    1. Calculate all factorials up to `2n`.
    2. Calculate the inverse of `fact[2n]`.
    3. Build all inverse factorials backwards.
    4. Use `fact[n]`, `inv[r]`, and `inv[n-r]`.
    5. Return the result modulo `1,000,000,007`.

Core formula:

    nCr =
        fact[n] × inv[r] × inv[n-r] mod M

Complexity:

    Time  : O(n + log M)
    Space : O(n)

For:

    n = 5
    r = 2

the result is:

    5C2 = 10