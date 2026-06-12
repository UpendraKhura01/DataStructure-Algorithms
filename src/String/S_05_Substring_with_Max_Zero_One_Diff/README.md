# 🔢 Substring with Max Zero-One Diff

## 📌 Problem Statement

Given a binary string `s` consisting only of:

    0 and 1

Find the maximum difference between the number of `0`s and the number of `1`s in any substring.

The difference is calculated as:

    number of 0s - number of 1s

If the string contains only `1`s, return:

    -1

---

## 🎯 Goal

Return the maximum possible value of:

    count(0) - count(1)

among all substrings of the given binary string.

---

## 💡 Intuition

We need a substring where:

    number of 0s is maximum
    and
    number of 1s is minimum

So we can convert the problem into a maximum subarray sum problem.

Treat:

    '0' as +1
    '1' as -1

Now the problem becomes:

    Find the maximum subarray sum

Because:

    +1 represents one zero
    -1 represents one one

So the maximum sum directly gives:

    maximum zero-one difference

This is exactly Kadane's Algorithm.

---

## 🔥 Key Idea

Convert characters into values:

    '0' → +1
    '1' → -1

Then apply Kadane's Algorithm:

    cur = current substring score
    ans = maximum score found so far

If:

    cur < 0

then reset:

    cur = 0

because a negative score will only reduce the future substring answer.

---

## 🧠 Thought Process

For each character:

    If character is '0':
        cur++

    If character is '1':
        cur--

After updating `cur`:

    If cur becomes negative:
        reset cur = 0

Then update:

    ans = max(ans, cur)

Why reset?

Because if a substring gives negative score, it means:

    it contains more 1s than 0s

Keeping this substring will not help in maximizing:

    zeros - ones

So we discard it and start fresh.

---

## 💻 Code

```java
int maxSubstring(String s) {

    int n = s.length();

    if (isall1(s)) {
        return -1;
    }

    int ans = -1;
    int cur = 0;

    for (int i = 0; i < n; i++) {

        if (s.charAt(i) == '0') {
            cur++;
        } else {
            cur--;
        }

        if (cur < 0) {
            cur = 0;
        }

        ans = Math.max(ans, cur);
    }

    return ans;
}

private boolean isall1(String s) {

    for (int i = 0; i < s.length(); i++) {

        if (s.charAt(i) == '0') {
            return false;
        }
    }

    return true;
}
```

---

## 🧪 Dry Run

Input:

    s = "11000010001"

---

Step 1: Check if all characters are `1`

String contains `0`, so:

    isall1(s) = false

Continue.

---

Step 2: Convert logic mentally

Mapping:

    0 → +1
    1 → -1

String:

    1  1  0  0  0  0  1  0  0  0  1

Converted values:

    -1 -1 +1 +1 +1 +1 -1 +1 +1 +1 -1

---

Step 3: Apply Kadane's Algorithm

Initialize:

    cur = 0
    ans = -1

---

Index 0:

    char = '1'
    value = -1

Update:

    cur = 0 - 1 = -1

Since cur < 0:

    cur = 0

Update answer:

    ans = max(-1, 0) = 0

---

Index 1:

    char = '1'
    value = -1

Update:

    cur = 0 - 1 = -1

Since cur < 0:

    cur = 0

Answer:

    ans = 0

---

Index 2:

    char = '0'
    value = +1

Update:

    cur = 0 + 1 = 1

Answer:

    ans = max(0, 1) = 1

---

Index 3:

    char = '0'
    value = +1

Update:

    cur = 1 + 1 = 2

Answer:

    ans = max(1, 2) = 2

---

Index 4:

    char = '0'
    value = +1

Update:

    cur = 2 + 1 = 3

Answer:

    ans = max(2, 3) = 3

---

Index 5:

    char = '0'
    value = +1

Update:

    cur = 3 + 1 = 4

Answer:

    ans = max(3, 4) = 4

---

Index 6:

    char = '1'
    value = -1

Update:

    cur = 4 - 1 = 3

Answer:

    ans = max(4, 3) = 4

---

Index 7:

    char = '0'
    value = +1

Update:

    cur = 3 + 1 = 4

Answer:

    ans = max(4, 4) = 4

---

Index 8:

    char = '0'
    value = +1

Update:

    cur = 4 + 1 = 5

Answer:

    ans = max(4, 5) = 5

---

Index 9:

    char = '0'
    value = +1

Update:

    cur = 5 + 1 = 6

Answer:

    ans = max(5, 6) = 6

---

Index 10:

    char = '1'
    value = -1

Update:

    cur = 6 - 1 = 5

Answer:

    ans = max(6, 5) = 6

---

Final Answer:

    6

---

## 🔁 Logic Flow

    Start
      |
      v
    Check if string contains only 1s
      |
     Yes
      |
      v
    Return -1

      |
     No
      |
      v
    Initialize:
        cur = 0
        ans = -1
      |
      v
    Traverse each character
      |
      v
    If character is 0:
        cur++
      |
      v
    If character is 1:
        cur--
      |
      v
    If cur < 0:
        cur = 0
      |
      v
    Update:
        ans = max(ans, cur)
      |
      v
    Return ans

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    We traverse the string once to check all 1s.
    We traverse the string once again for Kadane's Algorithm.

Total:

    O(n)

---

Space Complexity:

    O(1)

Reason:

    Only constant variables are used.

---

## 🎯 Key Takeaways

- Convert `0` to `+1`.
- Convert `1` to `-1`.
- The problem becomes maximum subarray sum.
- Kadane's Algorithm gives the maximum difference.
- Reset current sum when it becomes negative.
- If all characters are `1`, return `-1`.

---

## 🔥 Most Important Insight

The main trick is transformation:

    0 → +1
    1 → -1

After this, the required answer becomes:

    maximum subarray sum

So Kadane's Algorithm solves it efficiently.

---

## 🏁 Summary

To solve this problem:

    1. If string contains only 1s, return -1.
    2. Treat every 0 as +1.
    3. Treat every 1 as -1.
    4. Apply Kadane's Algorithm.
    5. Track the maximum score.
    6. Return the maximum score.

Efficient solution:

    Time  : O(n)
    Space : O(1)