# 🔤 Lexicographically Smallest Permutation Greater Than Target

## 📌 Problem Statement

You are given two strings:

    s
    target

Both have the same length and contain lowercase English letters.

You must return the **lexicographically smallest permutation of `s` that is strictly greater than `target`**.

If no such permutation exists, return:

    ""

### Example 1

    Input:
    s = "abc"
    target = "bba"

    Output:
    "bca"

The permutations of `abc` are:

    abc
    acb
    bac
    bca
    cab
    cba

The smallest permutation greater than `"bba"` is:

    "bca"

### Example 2

    Input:

    s = "leet"
    target = "code"

    Output:

    "eelt"

### Example 3

    Input:

    s = "baba"
    target = "bbaa"

    Output:
    ""

No permutation of `"baba"` is strictly greater than `"bbaa"`.

---

## 🎯 Goal

Construct the smallest possible permutation of `s` that is greater than `target`.

The solution must:

    - Use every character of s exactly once.
    - Never choose a character smaller than target[i] while still equal to target's prefix.
    - Once the constructed prefix becomes greater than target, use the smallest remaining characters.
    - Stop as soon as the first valid answer is found.

---

## 💡 Intuition

Lexicographical comparison depends on the **first position where two strings differ**.

For example:

    target = "bba"
    candidate = "bca"

Compare from left to right:

    b = b
    c > b

Therefore:

    "bca" > "bba"

Once a position becomes greater, all remaining characters can be chosen as small as possible.

So while constructing the answer:

    If current prefix == target prefix:
        We cannot choose a character smaller than target[i].

    If current character > target[i]:
        The whole string is now greater.

    If the string is already greater:
        Choose the smallest remaining characters.

This naturally leads to **backtracking + frequency counting**.

---

## 🔥 Key Idea

Maintain the frequency of every character:

    freq[0] -> count of 'a'
    freq[1] -> count of 'b'
    ...
    freq[25] -> count of 'z'

At every position, try characters from:

    'a' → 'z'

This is important because we want the **lexicographically smallest** valid answer.

There are two states:

    isGreater = false

means:

    The prefix constructed so far is exactly equal to target's prefix.

And:

    isGreater = true

means:

    The prefix is already lexicographically greater.

When `isGreater == false`:

    c < target[i]

is not allowed.

But:

    c == target[i]

keeps the prefix equal.

And:

    c > target[i]

makes the entire string greater.

When `isGreater == true`, any remaining character is allowed because the answer is already greater than target.

---

## 🧠 Thought Process

### Step 1: Count Characters

Instead of storing and removing characters from a list, store their frequencies.

For:

    s = "baba"

we get:

    a -> 2
    b -> 2

This allows us to know immediately whether a character is still available.

---

### Step 2: Build the Answer Left to Right

At position `idx`, try:

    'a', 'b', 'c', ..., 'z'

This guarantees that the first complete valid answer found is the lexicographically smallest one.

---

### Step 3: Do Not Go Below Target While Equal

Suppose:

    target = "bba"

and we are currently at index `0`.

We cannot choose:

    'a'

because:

    "a..." < "b..."

So while the prefix is still equal:

    c < target[idx]

must be skipped.

---

### Step 4: Track When the Answer Becomes Greater

Suppose:

    target = "bba"

At index `0`, choose:

    'b'

The prefix is still equal.

At index `1`, choose:

    'c'

Now:

    c > b

Therefore:

    isGreater = true

From this point onward, we only need the smallest possible remaining characters.

---

### Step 5: Backtrack When Necessary

Sometimes choosing the same character as the target leads to a dead end.

For example:

    s = "baba"
    target = "bbaa"

Try to match:

    b
    b

But the remaining characters are:

    a, a

The only possible completion is:

    "bbaa"

which is equal to target, not greater.

So the recursion backtracks and tries another possibility.

Eventually, if no valid greater permutation exists, it returns:

    ""

---

## 💻 Code

```java
int[] freq;
int n;
StringBuilder ans = new StringBuilder();
String target;

public String lexGreaterPermutation(String s, String target) {
    this.n = s.length();
    this.target = target;

    freq = new int[26];

    for (int i = 0; i < n; i++) {
        char c = s.charAt(i);
        freq[c - 'a']++;
    }

    solve(0, false, new StringBuilder());

    return ans.toString();
}

private boolean solve(int idx, boolean isGreater, StringBuilder cur) {
    if (idx == n) {
        if (isGreater) {
            ans = cur;
            return true;
        }

        return false;
    }

    for (char c = 'a'; c <= 'z'; c++) {

        if (freq[c - 'a'] == 0 ||
            (c < target.charAt(idx) && !isGreater)) {
            continue;
        }

        cur.append(c);
        freq[c - 'a']--;

        boolean greater =
            isGreater || (c > target.charAt(idx));

        if (solve(idx + 1, greater, cur)) {
            return true;
        }

        cur.deleteCharAt(cur.length() - 1);
        freq[c - 'a']++;
    }

    return false;
}
```

---

## 🧪 Dry Run

Input:

    s = "abc"
    target = "bba"

Character frequencies:

    a = 1
    b = 1
    c = 1

### Position 0

Target:

    b

Try:

    a

Not allowed because:

    a < b

Try:

    b

Allowed.

Current:

    "b"

Still equal to target prefix.

---

### Position 1

Target:

    b

Try:

    a

Not allowed because the prefix is still equal and:

    a < b

Try:

    b

Not available.

Try:

    c

Allowed because:

    c > b

Now:

    isGreater = true

Current:

    "bc"

---

### Position 2

Since the prefix is already greater, choose the smallest remaining character:

    a

Final:

    "bca"

Therefore:

    "bca" > "bba"

and it is the smallest valid permutation.

Output:

    "bca"

---

## 🔁 Logic Flow

    Start
      |
      v
    Count frequency of every character
      |
      v
    Start recursion at index 0
      |
      v
    Try characters from 'a' to 'z'
      |
      v
    Is character available?
      |
      +---- No ----> Try next character
      |
      v
    Is prefix equal to target?
      |
      +---- Yes
      |       |
      |       v
      |   Is c < target[idx]?
      |       |
      |       +---- Yes ----> Skip
      |       |
      |       +---- No -----> Choose c
      |
      +---- No ----> Choose smallest available c
      |
      v
    Update isGreater
      |
      v
    Recurse to next position
      |
      v
    Complete string?
      |
      +---- Greater ----> Save answer and return
      |
      +---- Equal ------> Backtrack
      |
      v
    No valid permutation?
      |
      v
    Return ""

---

## 📊 Complexity

Let:

    n = length of s

There are at most `26` possible characters at every position.

The solution uses backtracking and, in the worst case, may explore many possible permutations.

A loose worst-case bound is:

    O(26^n)

However, because the input contains only lowercase English letters and the recursion stops immediately after finding the first valid lexicographically smallest answer, the practical search is much smaller for many inputs.

Space complexity:

    O(n + 26)

The recursion depth is:

    O(n)

and the frequency array requires:

    O(26)

---

## 🎯 Key Takeaways

- Lexicographical comparison is decided by the first differing character.
- Use a frequency array instead of generating all permutations explicitly.
- Try characters in increasing order from `'a'` to `'z'`.
- While equal to the target prefix, never choose a smaller character.
- Choosing a larger character makes the entire prefix greater.
- Once `isGreater == true`, any remaining character is allowed.
- Backtracking restores the character frequency when a choice fails.
- The first complete valid answer found is the lexicographically smallest valid permutation.

---

## 🔥 Most Important Insight

The most important concept is the `isGreater` state.

There are only two situations.

### Case 1: Prefix Is Equal

    isGreater = false

Example:

    target = "bba"
    current = "b"

At the next position, we cannot choose:

    'a'

because that would make:

    "ba..." < "bb..."

We can choose:

    'b'  → remain equal

or:

    'c'  → become greater

---

### Case 2: Prefix Is Already Greater

    isGreater = true

Example:

    target = "bba"
    current = "bc"

Since:

    "bc" > "bb"

the remaining characters cannot make the final string smaller than target.

Therefore, we can simply choose the smallest remaining characters.

This is why the condition:

    c < target.charAt(idx) && !isGreater

is the core condition of the solution.

It means:

    "Only reject smaller characters when we are still equal to target."

---

## 🏁 Summary

The solution constructs the answer from left to right using backtracking.

The main strategy is:

    1. Count the frequency of every character in `s`.
    2. Start constructing the permutation from index `0`.
    3. Try characters from `'a'` to `'z'`.
    4. If the prefix is equal to target, skip characters smaller than target[idx].
    5. If a character is greater than target[idx], mark the prefix as greater.
    6. Once the prefix is greater, use any remaining characters.
    7. Backtrack if the current choice cannot produce a valid answer.
    8. Return the first complete greater permutation found.
    9. If no valid permutation exists, return `""`.

Core rule:

    Equal prefix:
        c < target[idx]  → reject
        c = target[idx]  → stay equal
        c > target[idx]  → become greater

    Already greater:
        Any available character is allowed.

For:

    s = "abc"
    target = "bba"

the answer is:

    "bca"

For:

    s = "baba"
    target = "bbaa"

no greater permutation exists:

    ""

Final approach:

    Frequency Array
          ↓
    Backtracking
          ↓
    Try 'a' → 'z'
          ↓
    Maintain isGreater
          ↓
    First valid answer
          ↓
    Lexicographically smallest permutation