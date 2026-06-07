# ⌨️ Special Keyboard

## 📌 Problem Statement

Given a special keyboard with only four keys:

    Key 1: A
        Prints one character 'A' on the screen.

    Key 2: Ctrl + A
        Selects all characters currently on the screen.

    Key 3: Ctrl + C
        Copies selected characters into the buffer.

    Key 4: Ctrl + V
        Pastes the buffer content onto the screen.

Initially:

    screen = empty
    buffer = empty

Given exactly `n` key presses, find the maximum number of `A` characters that can be displayed on the screen.

---

## 🎯 Goal

Return the maximum number of `A`s possible after exactly `n` key presses.

---

## 💡 Intuition

For small values of `n`, the best strategy is simple:

    Press A every time

Example:

    n = 3

Best sequence:

    A A A

Output:

    3

So for:

    n <= 6

the answer is:

    n

---

For larger `n`, copying and pasting becomes useful.

The useful pattern is:

    Build some number of A's first
    Then do:
        Ctrl + A
        Ctrl + C
        Ctrl + V
        Ctrl + V
        ...

The goal is to decide:

    At which key press should we stop typing A's
    and start copy-pasting?

---

## 🔥 Key Idea

Suppose we first use `i` key presses to create the best possible number of A's.

That gives:

    dp[i] A's

Now remaining key presses:

    n - i

To use copy-paste, we need:

    Ctrl + A → 1 press
    Ctrl + C → 1 press

So pastes available:

    n - i - 2

Total times the copied content appears:

    original content + pasted copies

That is:

    1 + (n - i - 2)
    = n - i - 1

Therefore:

    result = dp[i] * (n - i - 1)

So we try every possible breakpoint `i` and take the maximum.

---

## 🧠 Thought Process

For every `n`, we ask:

    What is the best previous state from which copy-paste should start?

If we start copy-pasting after `i` key presses:

    1. First i presses produce dp[i] A's
    2. Then Ctrl + A uses 1 press
    3. Then Ctrl + C uses 1 press
    4. Remaining presses are Ctrl + V
    5. Each paste adds dp[i] more A's

So:

    total multiplier = n - i - 1

Hence:

    dp[n] = max(dp[i] * (n - i - 1))

where:

    1 <= i <= n - 3

Why `n - 3`?

Because after index `i`, we need at least:

    Ctrl + A
    Ctrl + C
    Ctrl + V

That requires 3 operations.

---

## 💻 Code

```java
static int optimalKeys(int n) {
    int[] dp = new int[n + 1];

    return helper(n, dp);
}

static int helper(int n, int[] dp) {

    if (n <= 6) {
        return n;
    }

    if (dp[n] != 0) {
        return dp[n];
    }

    int ans = 0;

    for (int i = 1; i <= n - 3; i++) {

        int cur = helper(i, dp) * (n - i - 1);

        ans = Math.max(ans, cur);
    }

    return dp[n] = ans;
}

static int optimalKeys1(int n) {

    int[] dp = new int[n + 1];

    for (int i = 0; i <= 6; i++) {
        dp[i] = i;
    }

    for (int i = 7; i <= n; i++) {

        int ans = 0;

        for (int j = 1; j <= i - 3; j++) {

            int cur = dp[j] * (i - j - 1);

            ans = Math.max(ans, cur);
        }

        dp[i] = ans;
    }

    return dp[n];
}
```

---

## 🧪 Dry Run

Input:

    n = 7

---

For:

    n <= 6

we already know:

    dp[1] = 1
    dp[2] = 2
    dp[3] = 3
    dp[4] = 4
    dp[5] = 5
    dp[6] = 6

Now calculate:

    dp[7]

Try every breakpoint `j` from:

    1 to 4

because:

    j <= n - 3

---

### Case 1: j = 1

First 1 key press produces:

    dp[1] = 1

Remaining operation multiplier:

    n - j - 1
    = 7 - 1 - 1
    = 5

Total:

    dp[1] * 5
    = 1 * 5
    = 5

---

### Case 2: j = 2

First 2 key presses produce:

    dp[2] = 2

Multiplier:

    7 - 2 - 1 = 4

Total:

    2 * 4 = 8

---

### Case 3: j = 3

First 3 key presses produce:

    dp[3] = 3

Multiplier:

    7 - 3 - 1 = 3

Total:

    3 * 3 = 9

---

### Case 4: j = 4

First 4 key presses produce:

    dp[4] = 4

Multiplier:

    7 - 4 - 1 = 2

Total:

    4 * 2 = 8

---

Best value:

    max(5, 8, 9, 8) = 9

So:

    dp[7] = 9

---

Optimal sequence:

    A
    A
    A
    Ctrl + A
    Ctrl + C
    Ctrl + V
    Ctrl + V

Screen after first 3 presses:

    AAA

After Ctrl + A and Ctrl + C:

    buffer = AAA

After first Ctrl + V:

    AAAAAA

After second Ctrl + V:

    AAAAAAAAA

Final Answer:

    9

---

## 🔁 Logic Flow

    Start
      |
      v
    If n <= 6:
        return n
      |
      v
    Create dp array
      |
      v
    Base case:
        dp[i] = i for i = 0 to 6
      |
      v
    For every key count i from 7 to n
      |
      v
    Try every breakpoint j
      |
      v
    Calculate:
        cur = dp[j] * (i - j - 1)
      |
      v
    Update:
        dp[i] = max(dp[i], cur)
      |
      v
    Return dp[n]

---

## 📊 Complexity

Time Complexity:

    O(n²)

Reason:

    For every value from 7 to n,
    we try all possible breakpoints.

---

Space Complexity:

    O(n)

Reason:

    DP array of size n + 1 is used.

---

## 🎯 Key Takeaways

- For `n <= 6`, pressing A every time is optimal.
- For larger `n`, copy-paste gives better results.
- The main decision is the breakpoint where copying starts.
- `Ctrl + A` and `Ctrl + C` consume 2 operations.
- Remaining operations become pastes.
- Dynamic Programming avoids repeated calculation.

---

## 🔥 Most Important Insight

If we stop at key press `i` and start copy-pasting, then:

    dp[i] A's are copied

Remaining operations create a multiplier of:

    n - i - 1

So the core formula is:

    dp[n] = max(dp[i] * (n - i - 1))

for:

    1 <= i <= n - 3

---

## 🏁 Summary

To solve this problem:

    1. Use DP.
    2. For n <= 6, answer is n.
    3. For every larger n, try all breakpoints.
    4. Calculate result after copy-paste.
    5. Store the maximum in dp[n].
    6. Return dp[n].

Efficient solution:

    Time  : O(n²)
    Space : O(n)