# 🔢 Count Binary Strings with No Consecutive 1s (Fibonacci DP)

## 📌 Problem Statement

Given an integer `n`, count the number of **binary strings of length n** such that:

> ❌ No two consecutive `1`s are allowed

---

## 🎯 Goal

Return the **total number of valid binary strings** of length `n`.

---

## 💡 Intuition

At each position:
- You can place `0` freely
- You can place `1` only if previous was not `1`

---

## 🧠 Key Insight (VERY IMPORTANT)

Instead of tracking previous value (`prev`), we observe a **pattern**:

👉 If we build strings:

- For length `n`, the result depends on:
    - Strings of length `n-1`
    - Strings of length `n-2`

---

### 🔥 Why?

Let’s think:

### Case 1: String starts with `0`
- Remaining length = `n-1`
- Ways = `f(n-1)`

---

### Case 2: String starts with `10`
- After placing `1`, next must be `0`
- Remaining length = `n-2`
- Ways = `f(n-2)`

---

### ✅ Final Relation:

```
f(n) = f(n-1) + f(n-2)
```

👉 This is exactly **Fibonacci**

---

## ⚙️ Recurrence

```
f(n) = f(n-1) + f(n-2)
```

### Base Cases:

```
f(0) = 1
f(1) = 2
```

---

## 🚀 Code (Java - Memoization)

```java
package Dynamic_Programming.DP_05_Consecutive_1s_not_allowed;

public class solution {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(countStrings(n));
    }

    static int countStrings(int n) {

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        return helper(n, 0, dp);
    }

    static int helper(int n, int idx, int[] dp) {

        if (idx >= n) return 1;

        if (dp[idx] != -1) return dp[idx];

        return dp[idx] = helper(n, idx + 1, dp) + helper(n, idx + 2, dp);
    }
}
```

---

## 🔍 Explanation of Code

### 🔹 What does `idx` represent?

- Current position in the string

---

### 🔹 Why `idx + 1` and `idx + 2`?

- `idx + 1` → placing `0`
- `idx + 2` → placing `10`

👉 Because:
- If we place `1`, next must be `0`, so we skip one index

---

### 🔹 Base Case

```java
if (idx >= n) return 1;
```

- If we reach or cross length → valid string formed

---

## 🧪 Dry Run Example

### Input:
```
n = 3
```

---

### Recursive Calls:

```
f(0)
├── f(1)
│   ├── f(2)
│   │   ├── f(3) → 1
│   │   └── f(4) → 1
│   └── f(3) → 1
└── f(2)
    ├── f(3) → 1
    └── f(4) → 1
```

---

### Output:
```
5
```

---

## 🔁 Recursion Tree Insight

```
idx=0
├── idx=1
│   ├── idx=2
│   │   ├── idx=3 (valid)
│   │   └── idx=4 (valid)
│   └── idx=3 (valid)
└── idx=2
    ├── idx=3 (valid)
    └── idx=4 (valid)
```

---

## ⚡ Optimized Version (Iterative)

```java
static int countStrings(int n) {
    if (n == 0) return 1;
    if (n == 1) return 2;

    int prev2 = 1; // f(0)
    int prev1 = 2; // f(1)

    for (int i = 2; i <= n; i++) {
        int cur = prev1 + prev2;
        prev2 = prev1;
        prev1 = cur;
    }

    return prev1;
}
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(n) (can be O(1) optimized) |

---

## 🎯 Key Takeaways

- This problem reduces to **Fibonacci**
- No need for `prev` state
- Think in terms of:
    - placing `0`
    - placing `10`
- Very important **pattern recognition problem**

---

## 🏁 Summary

> Count binary strings by converting constraint into Fibonacci recurrence.

---

## 📚 Related Topics

- Fibonacci DP
- Climbing Stairs Problem
- Binary Strings
- Recursion + Memoization  