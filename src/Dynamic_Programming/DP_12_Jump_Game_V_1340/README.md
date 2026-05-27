# 🦘 Jump Game V

## 📌 Problem Statement

Given an integer array `arr` and an integer `d`.

From an index `i`, you can jump to another index `j` if:

    1. j is within distance d from i

That means:

    i - d <= j <= i + d

and:

    j != i

---

You can jump either:

    i + x

where:

    i + x < arr.length
    0 < x <= d

or:

    i - x

where:

    i - x >= 0
    0 < x <= d

---

But the jump is valid only if:

    arr[i] > arr[j]

and every element between `i` and `j` must also be smaller than `arr[i]`.

So if there is a taller or equal height in between, the jump is blocked.

Return the maximum number of indices you can visit.

---

## 🎯 Goal

Find the maximum number of indices that can be visited starting from any index.

---

## 💡 Intuition

From any index, we can only jump to smaller values.

That means the movement always goes from:

    higher value → lower value

So there can be no cycle.

This makes the problem suitable for:

    DFS + Memoization

For every index:

    dp[index] = maximum number of indices we can visit starting from index

Then the answer is:

    max(dp[i]) for all indices i

---

## 🔥 Key Idea

At each index `idx`, try jumping:

    1. Right side up to distance d
    2. Left side up to distance d

But stop immediately when:

    arr[next] >= arr[idx]

Why?

Because a taller or equal element blocks all further jumps in that direction.

For every valid jump:

    current answer = max(current answer, 1 + helper(nextIndex))

The `1` represents the current index.

---

## 🧠 Thought Process

For each index:

    Start with cur = 1

because we can always visit the current index itself.

Then check right direction:

    idx + 1, idx + 2, ..., idx + d

Stop if:

    out of bounds
    OR
    arr[idx + i] >= arr[idx]

If valid:

    cur = max(cur, 1 + helper(idx + i))

Then check left direction:

    idx - 1, idx - 2, ..., idx - d

Stop if:

    out of bounds
    OR
    arr[idx - i] >= arr[idx]

If valid:

    cur = max(cur, 1 + helper(idx - i))

Memoize the result:

    dp[idx] = cur

This avoids solving the same index again and again.

---

## 💻 Code

```java
static int maxJumps(int[] arr, int d) {
    int n = arr.length;

    int ans = 1;

    int[] dp = new int[n + 1];

    for (int i = 0; i <= n; i++) {
        dp[i] = -1;
    }

    for (int i = 0; i < n; i++) {
        ans = Math.max(ans, helper(i, n, arr, d, dp));
    }

    return ans;
}

static int helper(int idx, int n, int[] arr, int d, int[] dp) {

    int cur = 1;

    if (dp[idx] != -1) {
        return dp[idx];
    }

    for (int i = 1; i <= d; i++) {

        if (idx + i >= n) {
            break;
        }

        if (arr[idx + i] >= arr[idx]) {
            break;
        }

        cur = Math.max(
            cur,
            1 + helper(idx + i, n, arr, d, dp)
        );
    }

    for (int i = 1; i <= d; i++) {

        if (idx - i < 0) {
            break;
        }

        if (arr[idx - i] >= arr[idx]) {
            break;
        }

        cur = Math.max(
            cur,
            1 + helper(idx - i, n, arr, d, dp)
        );
    }

    return dp[idx] = cur;
}
```

---

## 🧪 Dry Run

Input:

    arr = [6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12]
    d = 2

Index mapping:

    index:  0   1   2   3   4   5   6   7   8   9   10
    value:  6   4   14  6   8   13  9   7   10  6   12

---

We need to find the best starting index.

The best path is:

    index 10 → index 8 → index 6 → index 7

Values:

    12 → 10 → 9 → 7

Total visited indices:

    4

---

Step 1: Start at index 10

    arr[10] = 12

Try left jumps because right side is outside array.

Distance allowed:

    d = 2

Check index 9:

    arr[9] = 6

Since:

    6 < 12

jump is valid.

Check index 8:

    arr[8] = 10

Since:

    10 < 12

jump is valid.

Possible moves:

    10 → 9
    10 → 8

Best move is:

    10 → 8

---

Step 2: At index 8

    arr[8] = 10

Check left within distance 2:

    index 7 → arr[7] = 7
    index 6 → arr[6] = 9

Both are smaller than 10.

Possible moves:

    8 → 7
    8 → 6

Best move is:

    8 → 6

---

Step 3: At index 6

    arr[6] = 9

Check right within distance 2:

    index 7 → arr[7] = 7

Valid because:

    7 < 9

Next index:

    index 8 → arr[8] = 10

But:

    10 >= 9

So direction is blocked.

Valid move:

    6 → 7

---

Step 4: At index 7

    arr[7] = 7

Check nearby values within distance 2:

    index 6 → 9
    index 5 → 13
    index 8 → 10
    index 9 → 6

Right side index 9 is smaller, but index 8 lies between 7 and 9.

Since:

    arr[8] = 10 >= arr[7] = 7

the jump from 7 to 9 is blocked.

So no valid further move.

Therefore:

    helper(7) = 1

---

Backtracking:

    helper(6) = 1 + helper(7)
              = 1 + 1
              = 2

    helper(8) = 1 + helper(6)
              = 1 + 2
              = 3

    helper(10) = 1 + helper(8)
               = 1 + 3
               = 4

Final answer:

    4

---

## 🔁 Logic Flow

    Start
      |
      v
    Create dp array initialized with -1
      |
      v
    For every index i
      |
      v
    Call helper(i)
      |
      v
    In helper:
        if dp[i] already known, return it
      |
      v
    Set cur = 1
      |
      v
    Try jumps to the right up to distance d
      |
      v
    Stop if:
        out of bounds
        OR arr[next] >= arr[current]
      |
      v
    For valid jumps:
        cur = max(cur, 1 + helper(next))
      |
      v
    Try jumps to the left up to distance d
      |
      v
    Stop if:
        out of bounds
        OR arr[next] >= arr[current]
      |
      v
    Store dp[i] = cur
      |
      v
    Return maximum answer over all starting indices

---

## 📊 Complexity

Time Complexity:

    O(n * d)

Reason:

    For every index, we may check up to d positions on the right
    and up to d positions on the left.

So:

    O(n * 2d) = O(n * d)

---

Space Complexity:

    O(n)

Reason:

    dp array stores result for every index.

Recursion stack can also go up to:

    O(n)

in the worst case.

Total:

    O(n)

---

## 🎯 Key Takeaways

- We can start from any index.
- Jump is allowed only to smaller values.
- Equal or greater value blocks the direction.
- This blocking condition is very important.
- Since jumps go from higher to lower values, cycles are not possible.
- DFS with memoization avoids recomputation.
- `dp[i]` means maximum jumps starting from index `i`.

---

## 🔥 Most Important Insight

The jump does not only depend on:

    arr[next] < arr[current]

It also depends on whether the path is blocked.

If while moving in one direction we find:

    arr[next] >= arr[current]

then:

    all further jumps in that direction are impossible

So we must:

    break

not continue.

---

## 🏁 Summary

To solve this problem:

    1. Try every index as a starting point.
    2. Use DFS to explore valid jumps.
    3. Move only to smaller values.
    4. Stop in a direction when a greater or equal value appears.
    5. Memoize result for every index.
    6. Return the maximum value among all starting indices.

Efficient solution:

    Time  : O(n * d)
    Space : O(n)