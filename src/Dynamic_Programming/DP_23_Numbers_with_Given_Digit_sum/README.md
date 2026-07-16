# Numbers with Given Digit Sum

**Difficulty:** Medium

## 🧩 Problem Statement
Given two integers **n** and **sum**, determine the number of **n-digit positive integers** whose digits add up to **sum**.

- The first digit **cannot be 0** (no leading zeros).
- If no such number exists, return **-1**.

---

## 📌 Examples

### Example 1
**Input**
```text
n = 2, sum = 2
```

**Output**
```text
2
```

**Explanation**

Possible numbers:
- 11
- 20

---

### Example 2
**Input**
```text
n = 1, sum = 10
```

**Output**
```text
-1
```

**Explanation**

A single digit number can only have a digit sum between **0** and **9**.

---

### Example 3
**Input**
```text
n = 2, sum = 10
```

**Output**
```text
9
```

**Explanation**

The valid numbers are:

19, 28, 37, 46, 55, 64, 73, 82, 91

---

## 🔍 Approach (Digit DP / Memoization)

We recursively build the number digit by digit.

### State

`solve(pos, currentSum)`

- `pos` = current digit position
- `currentSum` = sum formed so far

The recursion tries every possible digit (0–9) for the remaining positions.

The **first digit** is handled separately by trying digits **1 to 9**.

Memoization stores already computed states.

---

## ✅ Algorithm

1. Create a DP table `dp[position][currentSum]`.
2. Try every first digit from **1 to 9**.
3. Recursively place digits **0 to 9**.
4. If:
    - position == n
    - currentSum == required sum

   return 1.

5. If currentSum exceeds target or digits are exhausted, return 0.
6. Sum all possible ways.
7. If answer is 0, return -1.

---

## ▶ Dry Run

### Input

```text
n = 2
sum = 2
```

Start first digit:

- 1 → remaining sum = 1
    - second digit = 1 ✅

Number = 11

- 2 → remaining sum = 0
    - second digit = 0 ✅

Number = 20

Other first digits exceed required sum.

Answer = **2**

---

## ⏱ Complexity Analysis

Let

- n = number of digits
- S = required digit sum

### Time Complexity

```text
O(n × S × 10)
```

Each DP state tries all 10 digits.

### Space Complexity

```text
O(n × S)
```

For memoization.

---

## ✅ Java Solution (Memoization)

```java
import java.util.Arrays;

public class Solution {

    int[][] dp;
    int n;
    int sum;

    public int countWays(int n, int sum) {

        this.n = n;
        this.sum = sum;

        dp = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;

        // First digit cannot be zero
        for (int digit = 1; digit <= 9; digit++) {
            ans += solve(1, digit);
        }

        return ans == 0 ? -1 : ans;
    }

    private int solve(int pos, int currentSum) {

        if (pos == n && currentSum == sum)
            return 1;

        if (currentSum > sum || pos > n)
            return 0;

        if (dp[pos][currentSum] != -1)
            return dp[pos][currentSum];

        int ways = 0;

        for (int digit = 0; digit <= 9; digit++) {
            ways += solve(pos + 1, currentSum + digit);
        }

        return dp[pos][currentSum] = ways;
    }
}
```

---

## 💡 Key Idea

This is a classic **Digit DP** problem.

- First digit is handled separately (cannot be 0).
- Remaining digits can be 0–9.
- Memoization avoids recomputing identical `(position, currentSum)` states.

The final answer is the total number of valid digit combinations whose digit sum equals the target.