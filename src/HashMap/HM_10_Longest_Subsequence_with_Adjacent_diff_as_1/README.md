# 📌 Longest Subsequence with Adjacent Difference as 1

## 📝 Problem Statement

Given an array `arr[]`, find the **length of the longest subsequence** such that the absolute difference between every pair of adjacent elements is exactly `1`.

A subsequence does **not** need to be contiguous. We can skip elements, but their original order must be maintained.

### Example

```text
Input  : [10, 9, 4, 5, 4, 8, 6]
Output : 3

Possible longest subsequences:
[10, 9, 8]
[4, 5, 4]
[4, 5, 6]
```

---

## 🎯 Goal

Find the maximum length of a subsequence where:

```text
|current - previous| = 1
```

---

## 💡 Intuition

For every number `num`, the previous element in a valid subsequence can only be:

```text
num - 1
       OR
num + 1
```

So instead of checking every previous element, maintain a `HashMap`:

```text
mp[x] = longest valid subsequence ending with value x
```

When processing `num`:

```text
mp[num - 1] → best subsequence we can extend
mp[num + 1] → best subsequence we can extend
```

Take the better one and add `num`.

---

## 🔑 Key Idea

For every `num`:

### 1. Check `num - 1`

```java
int prev = mp.getOrDefault(num - 1, 0);
```

This means:

> What is the longest subsequence ending with `num - 1`?

We can append `num` to it.

### 2. Check `num + 1`

```java
int next = mp.getOrDefault(num + 1, 0);
```

This means:

> What is the longest subsequence ending with `num + 1`?

We can also append `num` to it because their difference is `1`.

### 3. Choose the best

```java
int cur = Math.max(prev, next) + 1;
```

The `+1` represents the current element.

### 4. Store the result

```java
mp.put(num, cur);
```

Now `mp[num]` represents the longest subsequence ending at `num` found so far.

---

## 🧠 Thought Process

For every element:

```text
current = num

Can I attach num to a subsequence ending at:
    num - 1 ?
    num + 1 ?

Take the longer one.

current length = max(previous lengths) + 1
```

So the recurrence is:

```text
dp[num] = max(dp[num - 1], dp[num + 1]) + 1
```

The `HashMap` acts like a compressed DP table.

---

## 💻 Code

```java
int longestSubseq(int[] arr) {
    HashMap<Integer, Integer> mp = new HashMap<>();

    int ans = 0;

    for (int num : arr) {

        int prev = mp.getOrDefault(num - 1, 0);
        int next = mp.getOrDefault(num + 1, 0);

        int cur = Math.max(prev, next) + 1;

        mp.put(num, cur);

        ans = Math.max(ans, cur);
    }

    return ans;
}
```

---

## 🔍 Dry Run

Consider:

```text
arr = [1, 2, 3, 2, 3, 7, 2, 1]
```

| num | best from `num-1` | best from `num+1` | `cur` | `mp[num]` |
|---:|---:|---:|---:|---:|
| 1 | 0 | 0 | 1 | 1 |
| 2 | 1 | 0 | 2 | 2 |
| 3 | 2 | 0 | 3 | 3 |
| 2 | 1 | 3 | 4 | 4 |
| 3 | 4 | 0 | 5 | 5 |
| 7 | 0 | 0 | 1 | 1 |
| 2 | 1 | 5 | 6 | 6 |
| 1 | 0 | 6 | 7 | 7 |

Therefore:

```text
Answer = 7
```

One valid subsequence is:

```text
[1, 2, 3, 2, 3, 2, 1]
```

Every adjacent difference is exactly `1`.

---

## 🔄 Logic Flow

```text
Take current num
       ↓
Check num - 1
       ↓
Check num + 1
       ↓
Take maximum
       ↓
Add 1 for current num
       ↓
Store mp[num]
       ↓
Update global answer
```

---

## ⏱️ Complexity

Let `n` be the size of the array.

### Time Complexity

```text
O(n)
```

Each element performs a constant number of average `HashMap` operations.

### Space Complexity

```text
O(n)
```

In the worst case, the map can contain `O(n)` distinct values.

---

## 📌 Key Takeaways

- This is a **Dynamic Programming + HashMap** problem.
- `mp[x]` stores the longest valid subsequence ending with value `x`.
- For `num`, only `num - 1` and `num + 1` can precede it.
- The main recurrence is:

```text
dp[num] = max(dp[num - 1], dp[num + 1]) + 1
```

- The array does not need to be sorted because the HashMap stores the best subsequence information from elements already processed.

---

## ⭐ Most Important Insight

The key observation is:

```text
Absolute difference = 1
```

means the previous value has only **two possibilities**:

```text
num - 1
num + 1
```

Therefore, instead of comparing `num` with every previous element, we directly look up these two values in a `HashMap`.

This converts a potentially quadratic DP into an efficient:

```text
O(n)
```

solution.

---

## 📚 Summary

```text
For every num:
    1. Find best subsequence ending at num - 1.
    2. Find best subsequence ending at num + 1.
    3. Take the maximum.
    4. Add current num.
    5. Store the result in HashMap.
    6. Update the global maximum.

Recurrence:
dp[num] = max(dp[num - 1], dp[num + 1]) + 1

Time  : O(n) average
Space : O(n)
```