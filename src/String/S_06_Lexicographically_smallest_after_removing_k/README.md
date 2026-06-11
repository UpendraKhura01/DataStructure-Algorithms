# 🔡 Lexicographically Smallest After Removing K

## 📌 Problem Statement

Given a string `s` of lowercase English characters and an integer `k`.

Before removing characters, the value of `k` must be corrected:

    1. If length of string is a power of 2:
           k = k / 2

    2. Otherwise:
           k = k * 2

After correcting `k`, remove exactly `k` characters from the string.

Return the lexicographically smallest possible string.

If it is not possible to remove `k` characters, or if the final string becomes empty, return:

    -1

---

## 🎯 Goal

Generate the smallest lexicographical string after removing exactly corrected `k` characters.

---

## 💡 Intuition

To make a string lexicographically smallest, smaller characters should appear as early as possible.

So while scanning the string:

    If current character is smaller than the last chosen character,
    remove the last chosen character.

This is because removing a bigger previous character allows a smaller current character to move forward.

This idea is similar to:

    Monotonic Stack

Here, `StringBuilder` is used like a stack.

---

## 🔥 Key Idea

Use a stack-like structure.

For each character `c`:

    While:
        stack is not empty
        AND current character is smaller than stack top
        AND k > 0

    Remove stack top.

Then add current character.

After processing all characters:

    If k is still greater than 0,
    remove characters from the end.

Why from the end?

Because if the string is already increasing, removing from the end keeps it lexicographically smallest.

---

## 🧠 Thought Process

First correct the value of `k`.

Power of 2 check:

    (n & (n - 1)) == 0

This works because powers of 2 have only one set bit.

Examples:

    4  = 100
    3  = 011

    4 & 3 = 0

So 4 is power of 2.

---

After correcting `k`:

    if k >= n

then removing `k` characters is impossible or makes the string empty.

So return:

    -1

---

Then we build the answer greedily.

For every character:

    1. Remove bigger characters from the end while possible
    2. Add current character
    3. Continue

This keeps the result as small as possible at every step.

---

## 💻 Code

```java
String lexicographicallySmallest(String s, int k) {

    StringBuilder sb = new StringBuilder();

    int n = s.length();

    if ((n & (n - 1)) == 0) {
        k /= 2;
    } else {
        k *= 2;
    }

    if (k >= n) {
        return "-1";
    }

    for (int i = 0; i < n; i++) {

        char c = s.charAt(i);

        while (
            !sb.isEmpty()
            && c < sb.charAt(sb.length() - 1)
            && k > 0
        ) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }

        sb.append(c);
    }

    while (k > 0 && !sb.isEmpty()) {

        sb.deleteCharAt(sb.length() - 1);

        k--;

        if (k > 0 && sb.isEmpty()) {
            return "-1";
        }
    }

    return sb.isEmpty() ? "-1" : sb.toString();
}
```

---

## 🧪 Dry Run

Input:

    s = "fooland"
    k = 2

---

Step 1: Find length

    n = 7

Check if `n` is power of 2:

    7 is not a power of 2

So correct `k`:

    k = k * 2
      = 2 * 2
      = 4

We need to remove exactly:

    4 characters

---

Step 2: Start with empty StringBuilder

    sb = ""

---

Step 3: Process character 'f'

Current:

    c = 'f'

StringBuilder is empty.

Append:

    sb = "f"

Remaining removals:

    k = 4

---

Step 4: Process character 'o'

Current:

    c = 'o'

Check:

    'o' < 'f' ? false

Append:

    sb = "fo"

Remaining removals:

    k = 4

---

Step 5: Process character 'o'

Current:

    c = 'o'

Check:

    'o' < 'o' ? false

Append:

    sb = "foo"

Remaining removals:

    k = 4

---

Step 6: Process character 'l'

Current:

    c = 'l'

Check top:

    'l' < 'o' ? true

Remove last character:

    sb = "fo"
    k = 3

Check again:

    'l' < 'o' ? true

Remove last character:

    sb = "f"
    k = 2

Check again:

    'l' < 'f' ? false

Append:

    sb = "fl"

---

Step 7: Process character 'a'

Current:

    c = 'a'

Check:

    'a' < 'l' ? true

Remove:

    sb = "f"
    k = 1

Check again:

    'a' < 'f' ? true

Remove:

    sb = ""
    k = 0

Append:

    sb = "a"

---

Step 8: Process character 'n'

Since:

    k = 0

No more removal allowed.

Append:

    sb = "an"

---

Step 9: Process character 'd'

Append:

    sb = "and"

---

Final result:

    "and"

---

## 🔁 Logic Flow

    Start
      |
      v
    Find length n
      |
      v
    Correct k:
        if n is power of 2:
            k = k / 2
        else:
            k = k * 2
      |
      v
    If k >= n:
        return -1
      |
      v
    Create empty StringBuilder as stack
      |
      v
    Traverse each character
      |
      v
    While current character is smaller than last chosen character
    and k > 0:
        remove last chosen character
      |
      v
    Append current character
      |
      v
    If k still remains:
        remove characters from end
      |
      v
    If final string is empty:
        return -1
      |
      v
    Return final string

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    Each character is inserted once and removed at most once.

---

Space Complexity:

    O(n)

Reason:

    StringBuilder stores the final result.

---

## 🎯 Key Takeaways

- Correcting `k` is the first important step.
- Power of 2 can be checked using bit manipulation.
- Use greedy + monotonic stack logic.
- Remove previous larger characters when a smaller character arrives.
- If removals remain after traversal, remove from the end.
- If final string is empty, return `-1`.

---

## 🔥 Most Important Insight

To get the lexicographically smallest string:

    Whenever a smaller character comes,
    remove previous bigger characters if removals are available.

This keeps the smallest possible characters as early as possible.

---

## 🏁 Summary

To solve this problem:

    1. Correct the value of k based on string length.
    2. If k >= n, return -1.
    3. Use StringBuilder as a stack.
    4. Remove larger previous characters while current character is smaller.
    5. Append current character.
    6. Remove remaining characters from the end if needed.
    7. Return the final string or -1.

Efficient solution:

    Time  : O(n)
    Space : O(n)