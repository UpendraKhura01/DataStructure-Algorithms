# 🔢 Count Binary Strings with No Consecutive 1s (Dynamic Programming)

## 📌 Problem Statement

Given an integer `n`, count the number of **binary strings of length n** such that:

> ❌ No two consecutive `1`s are allowed

---

## 🎯 Goal

Return the **total number of valid binary strings** of length `n`.

---

## 💡 Intuition

At every position, we have 2 choices:
- Place `0`
- Place `1`

But:
- We **cannot place `1` after `1`**

👉 So the decision depends on the **previous character**

---

## 🧠 Thought Process

We define a state:

```java
helper(idx, prev)
```

### 🔹 Parameters:

| Parameter | Meaning |
|----------|--------|
| `idx` | current index in string |
| `prev` | previous character (0 or 1) |

---

### 🔹 Why `prev`?

- To ensure we **don’t place consecutive 1s**
- If `prev = 1`, we cannot place another `1`

---

## 🔁 Recursion Choices

### ✅ Always allowed:
```java
Put 0 → helper(idx + 1, 0)
```

---

### ⚠️ Conditional:
```java
Put 1 → only if prev != 1
```

---

## ⚙️ Recurrence

```java
if(prev == 1):
    only place 0
else:
    place 0 + place 1
```

---

## 🧠 Base Case

```java
if(idx == n) return 1;
```

- If we successfully build a string → count it

---

## 🚀 Code (Java - Memoization)

```java

    static int countStrings(int n) {

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        return helper(n, 0, 0, dp);
    }

    static int helper(int n, int idx, int prev, int[][] dp) {

        if (idx == n) return 1;

        if (dp[idx][prev] != -1) return dp[idx][prev];

        int putZero = helper(n, idx + 1, 0, dp);

        int putOne = 0;
        if (prev != 1) {
            putOne = helper(n, idx + 1, 1, dp);
        }

        return dp[idx][prev] = putZero + putOne;
    }
}
```

---

## 🧪 Dry Run Example

### Input:
```
n = 3
```

---

### All possible binary strings:

```
000 ✅
001 ✅
010 ✅
011 ❌ (invalid)
100 ✅
101 ✅
110 ❌ (invalid)
111 ❌ (invalid)
```

---

### Valid strings:
```
000, 001, 010, 100, 101
```

### Output:
```
5
```

---

## 🔁 Recursion Tree (Simplified)

```
idx=0
├── 0 → idx=1
│   ├── 0 → idx=2
│   │   ├── 0 → valid
│   │   └── 1 → valid
│   └── 1 → idx=2
│       └── 0 → valid
└── 1 → idx=1
    └── 0 → idx=2
        ├── 0 → valid
        └── 1 → valid
```

👉 DP avoids recomputing same states

---

## ⚡ Tabulation Insight (Important)

Let:

- `dp[i][0]` = strings ending with 0
- `dp[i][1]` = strings ending with 1

### Transitions:

```
dp[i][0] = dp[i-1][0] + dp[i-1][1]
dp[i][1] = dp[i-1][0]
```

---

### Pattern:

```
Total = Fibonacci Series
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n * 2) |
| Space | O(n * 2) |

---

## 🎯 Key Takeaways

- State = `(index, previous bit)`
- Prevent invalid patterns using `prev`
- Converts to **Fibonacci pattern**
- Classic **DP + recursion problem**

---

## 🏁 Summary

> Build binary strings step-by-step while avoiding consecutive 1s using DP.

---

## 📚 Related Topics

- Fibonacci DP
- Binary Strings
- Recursion + Memoization
- Constraint-based DP  