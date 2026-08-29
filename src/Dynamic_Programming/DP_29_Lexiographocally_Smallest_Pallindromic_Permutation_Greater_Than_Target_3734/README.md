# 🔄 Lexicographically Smallest Palindromic Permutation Greater Than Target

## 📌 Problem Statement

You are given two strings:

    s
    target

Both have the same length and contain lowercase English letters.

Return the **lexicographically smallest string** that:

    1. Is a permutation of s.
    2. Is a palindrome.
    3. Is strictly greater than target.

If no such string exists, return:

    ""

### Example 1

    Input:
    s = "baba"
    target = "abba"

    Output:
    "baab"

### Example 2

    Input:
    s = "baba"
    target = "bbaa"

    Output:
    ""

### Example 3

    Input:
    s = "abc"
    target = "abb"

    Output:
    ""

`abc` cannot form a palindrome because more than one character has an odd frequency.

### Example 4

    Input:
    s = "aac"
    target = "abb"

    Output:
    "aca"

---

## 🎯 Goal

Construct the lexicographically smallest palindromic permutation of `s` that is strictly greater than `target`.

The main challenge is satisfying **both conditions**:

    Palindrome
        +
    Lexicographically greater than target

---

## 💡 Intuition

A palindrome is completely determined by its first half and its middle character.

For example:

    "baab"

can be divided into:

    First half : "ba"
    Middle     : ""
    Reverse    : "ab"

Therefore:

    "ba" + "ab" = "baab"

For an odd-length palindrome:

    "abcba"

we have:

    First half : "ab"
    Middle     : "c"
    Reverse    : "ba"

Therefore:

    "ab" + "c" + "ba"
    = "abcba"

So instead of constructing the entire palindrome, we only need to construct its **first half**.

---

## 🔥 Key Idea

### 1. Count Character Frequencies

A palindrome can exist only when:

    Even length:
        Every character has an even frequency.

    Odd length:
        Exactly one character can have an odd frequency.

If more than one character has an odd frequency:

    No palindrome is possible.

Return:

    ""

---

### 2. Use Half of Every Frequency

If a character occurs:

    4 times

then the first half needs:

    4 / 2 = 2

copies.

Example:

    s = "baba"

Frequencies:

    a = 2
    b = 2

Half frequencies:

    a = 1
    b = 1

So the first half must contain:

    "ab"

in some order.

---

### 3. Construct Only the First Half

Let:

    half = n / 2

Build exactly `half` characters.

Try characters in increasing order:

    'a' → 'z'

This is important because we want the final palindrome to be lexicographically smallest.

---

### 4. Track Whether We Are Already Greater

Use:

    isGreater

If:

    isGreater = false

the current prefix is still equal to the corresponding prefix of `target`.

Therefore, we cannot choose a character smaller than:

    target[idx]

If we choose:

    c > target[idx]

then the palindrome's first differing position is already greater.

From that point onward:

    isGreater = true

and any remaining character can be chosen.

---

## 🧠 Thought Process

### Step 1: Check Whether a Palindrome Is Possible

Count the frequency of every character.

For:

    s = "abc"

we get:

    a = 1
    b = 1
    c = 1

There are three odd frequencies.

Since more than one odd frequency is present:

    palindrome is impossible

Therefore:

    return ""

---

### Step 2: Store Half Frequencies

Suppose:

    s = "baba"

Frequency:

    a = 2
    b = 2

Divide each frequency by `2`:

    a = 1
    b = 1

The first half contains exactly:

    "ab"

After constructing it, the other half is simply its reverse.

---

### Step 3: Handle the Middle Character

For odd length, one character can have an odd frequency.

Example:

    s = "aac"

Frequencies:

    a = 2
    c = 1

The middle character is:

    c

Half frequencies:

    a = 1

So the only possible palindrome is:

    "a" + "c" + "a"

    = "aca"

---

### Step 4: Why We Only Need to Compare the First Half

A palindrome is:

    firstHalf + middle + reverse(firstHalf)

Suppose two palindromes have different first halves.

Their lexicographical order is decided by the first position where those first halves differ.

Therefore, while constructing the first half, we can track whether it is already greater than the corresponding prefix of `target`.

At the end, the complete palindrome is constructed and compared with `target` to guarantee strict correctness.

---

### Step 5: Backtracking

When a character is selected:

    freq[c]--

If that choice cannot produce a valid answer, undo it:

    freq[c]++

This allows the algorithm to try the next possible character.

Because characters are tried from:

    'a' → 'z'

the first valid palindrome found is the lexicographically smallest one.

---

## 💻 Code

```java
String target;
int half;
char mid = '@';
int[] freq;
String ans;

public String lexPalindromicPermutation(String s, String target) {

    int n = s.length();
    half = n / 2;

    this.target = target;

    freq = new int[26];

    for (int i = 0; i < n; i++) {
        freq[s.charAt(i) - 'a']++;
    }

    int odd = 0;

    for (int i = 0; i < 26; i++) {

        if (freq[i] % 2 == 1) {

            odd++;
            mid = (char) (i + 'a');

            if (odd > 1) {
                return "";
            }
        }

        freq[i] /= 2;
    }

    if (solve(0, false, new StringBuilder())) {
        return ans;
    }

    return "";
}

private boolean solve(int idx, boolean isGreater, StringBuilder cur) {

    if (idx == half) {

        String rev =
            new StringBuilder(cur).reverse().toString();

        String temp =
            (mid != '@')
                ? (cur.toString() + mid + rev)
                : (cur + rev);

        if (temp.compareTo(target) > 0) {
            ans = temp;
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

    s = "baba"
    target = "abba"

### Step 1: Frequencies

    a = 2
    b = 2

Half frequencies:

    a = 1
    b = 1

No middle character because the length is even.

---

### Step 2: Build First Half

We need:

    half = 2

Target prefix:

    "ab"

Try:

    'a'

It matches the target:

    cur = "a"

Still equal.

Next position:

    target = 'b'

Try:

    'a'

Unavailable.

Try:

    'b'

    cur = "ab"

The complete palindrome is:

    "ab" + "ba"
    = "abba"

But:

    "abba" == "abba"

So it is not strictly greater.

Backtrack.

---

### Step 3: Try Next Possibility

At the first position, choose:

    'b'

Now:

    "b" > "a"

So:

    isGreater = true

Remaining character:

    'a'

First half:

    "ba"

Construct palindrome:

    "ba" + "ab"

    = "baab"

Since:

    "baab" > "abba"

the answer is:

    "baab"

---

## 🔁 Logic Flow

    Start
      |
      v
    Count character frequencies
      |
      v
    Check number of odd frequencies
      |
      +---- More than 1 ----> Return ""
      |
      v
    Divide frequencies by 2
      |
      v
    Store middle character if needed
      |
      v
    Build first half using backtracking
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
    Is prefix still equal to target?
      |
      +---- c < target[idx] ----> Skip
      |
      v
    Choose character
      |
      v
    Update isGreater
      |
      v
    Recurse
      |
      v
    First half complete
      |
      v
    Create palindrome
      |
      v
    Compare palindrome with target
      |
      +---- Greater ----> Return answer
      |
      +---- Not greater ----> Backtrack
      |
      v
    No valid palindrome
      |
      v
    Return ""

---

## 📊 Complexity

Let:

    n = length of s

The first half has:

    n / 2

characters.

The algorithm uses backtracking over the available characters.

In the worst case, the number of possible arrangements can be exponential:

    O(26^(n/2))

However, character frequencies significantly reduce the number of distinct arrangements when duplicates exist, and the search stops immediately after finding the first valid answer.

Palindrome construction and comparison at a complete candidate take:

    O(n)

The frequency array uses:

    O(26)

space.

The recursion depth is:

    O(n)

Therefore, auxiliary space is:

    O(n)

excluding the input and output strings.

---

## 🎯 Key Takeaways

- A palindrome is determined completely by its first half and middle character.
- A valid palindrome requires at most one odd character frequency.
- Divide every character frequency by `2` to get the characters needed for the first half.
- Construct only the first half using backtracking.
- Try characters from `'a'` to `'z'` to obtain the smallest possible result.
- Use `isGreater` to track whether the current prefix has already exceeded the target.
- Once the prefix is greater, smaller characters can be used freely.
- Mirror the first half to construct the complete palindrome.
- Finally, ensure the complete palindrome is strictly greater than `target`.

---

## 🔥 Most Important Insight

The most important transformation is:

    Full Palindrome
          ↓
    First Half + Middle + Reverse(First Half)
          ↓
    Only construct the First Half
          ↓
    Mirror it at the end

For example:

    First half = "ba"
    Middle     = none
    Reverse    = "ab"

Therefore:

    "ba" + "ab"
    = "baab"

This reduces the number of positions that need to be constructed from:

    n

to:

    n / 2

The second key insight is that **lexicographical order is decided from left to right**, so while constructing the first half we can maintain:

    isGreater

Once:

    current character > target character

the complete palindrome is guaranteed to be greater based on that earlier position.

---

## 🏁 Summary

The solution works in four main stages:

    1. Count character frequencies.
    2. Check whether a palindromic permutation is possible.
    3. Use half of each frequency to construct the first half.
    4. Mirror the first half and check whether the resulting palindrome is greater than target.

For:

    s = "baba"
    target = "abba"

The first possible palindrome is:

    "abba"

But:

    "abba" == "target"

so it is rejected.

The next valid palindrome is:

    "baab"

Therefore:

    Answer = "baab"

Core structure:

    Frequency Count
          ↓
    Check Odd Frequencies
          ↓
    Half Frequencies
          ↓
    Backtracking
          ↓
    Track isGreater
          ↓
    Build Palindrome
          ↓
    Compare with Target
          ↓
    Return Smallest Valid Answer

If no valid palindromic permutation is greater than `target`:

    return ""

Complexity:

    Worst-case Time  : O(26^(n/2) × n)
    Space            : O(n)