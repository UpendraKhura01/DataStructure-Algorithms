
# 3517. Smallest Palindromic Rearrangement I

---

# Problem Statement

You are given a **palindromic string** `s`.

Your task is to rearrange its characters to obtain the **lexicographically smallest palindrome** possible.

Return the resulting palindrome.

### Example 1

```text
Input:
s = "z"

Output:
"z"
```

---

### Example 2

```text
Input:
s = "babab"

Output:
"abbba"
```

---

### Example 3

```text
Input:
s = "daccad"

Output:
"acddca"
```

---

# Goal

Construct the **smallest lexicographical palindrome** using exactly the same characters present in the input string.

Since the input is already guaranteed to be a palindrome, a valid palindromic rearrangement always exists.

---

# Intuition

A palindrome is symmetric.

If a character appears:

```text
4 times
```

then

```text
2 copies go on the left
2 copies go on the right
```

Similarly,

```text
6 occurrences

↓

3 on the left
3 on the right
```

To make the palindrome **lexicographically smallest**, place the **smallest available characters first** on the left half.

The right half is automatically determined because it must mirror the left half.

The middle character (if any) remains unchanged since exactly one character can have an odd frequency.

---

# Key Idea

1. Count the frequency of every character.
2. Traverse characters from `'a'` to `'z'`.
3. For every pair of equal characters:
    - Place one at the current left position.
    - Place the other at the corresponding right position.
4. Continue until all pairs are placed.
5. The center character (if one exists) is already present and remains unchanged.

Because characters are processed in alphabetical order, the resulting palindrome is the smallest possible lexicographically.

---

# Thought Process

### Step 1

Count the frequency of each character.

```text
freq[c]
```

---

### Step 2

Create a mutable string using

```text
StringBuilder
```

This allows updating characters in-place.

---

### Step 3

Maintain a pointer

```text
idx = 0
```

representing the current position from the left.

---

### Step 4

For every character from

```text
'a' → 'z'
```

while at least two occurrences remain,

place the pair:

```text
left  = idx

right = n - 1 - idx
```

Update:

```text
freq -= 2

idx++
```

---

### Step 5

After all pairs are placed,

return the modified string.

---

# Code (Functions Only)

```java
String smallestPalindrome(String s) {

    int n = s.length();

    int[] freq = new int[26];

    for (int i = 0; i < n; i++) {
        char c = s.charAt(i);
        freq[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder(s);

    int idx = 0;

    for (int i = 0; i < 26; i++) {

        char c = (char) (i + 97);

        while (freq[i] > 1) {

            sb.setCharAt(idx, c);
            sb.setCharAt(n - 1 - idx, c);

            idx++;

            freq[i] -= 2;
        }
    }

    return sb.toString();
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
s = "daccad"
```

---

### Step 1 : Frequency Count

```text
a → 2

c → 2

d → 2
```

---

### Initial String

```text
d a c c a d
```

```text
idx = 0
```

---

### Process 'a'

Frequency

```text
2
```

Place pair

```text
a _ _ _ _ a
```

Update

```text
idx = 1
```

---

### Process 'b'

```text
Frequency = 0
```

Skip.

---

### Process 'c'

Frequency

```text
2
```

Place pair

```text
a c _ _ c a
```

Update

```text
idx = 2
```

---

### Process 'd'

Frequency

```text
2
```

Place pair

```text
a c d d c a
```

---

### Final Output

```text
"acddca"
```

---

# Logic Flow

```text
Start

↓

Count frequency of every character

↓

Create StringBuilder

↓

idx = 0

↓

For every character from 'a' to 'z'

↓

Frequency ≥ 2 ?

↓

Yes

↓

Place character at

left = idx

right = n-1-idx

↓

Decrease frequency by 2

↓

Increment idx

↓

Repeat

↓

Return StringBuilder as String

↓

End
```

---

# Complexity

## Time Complexity

Counting frequencies:

```text
O(N)
```

Processing all 26 lowercase letters:

```text
O(26)
```

Placing character pairs:

```text
O(N)
```

Overall:

```text
O(N)
```

---

## Space Complexity

Frequency array:

```text
O(26)
```

StringBuilder:

```text
O(N)
```

Auxiliary space (excluding the output representation):

```text
O(1)
```

---

# Key Takeaways

- A palindrome is completely determined by its left half.
- Characters should be placed in alphabetical order to minimize lexicographical order.
- Each pair of identical characters is placed symmetrically.
- Only one character may have an odd frequency, and it naturally occupies the center.
- The algorithm avoids sorting the entire string by using a frequency array.

---

# Most Important Insight

The lexicographically smallest palindrome is obtained by filling the **left half** with the smallest available character pairs first. Since the right half is simply the mirror image of the left half, processing characters from `'a'` to `'z'` guarantees the smallest possible palindrome.

---

# Summary

The solution counts the frequency of each character and reconstructs the palindrome directly. By iterating from `'a'` to `'z'`, every available character pair is placed symmetrically at the leftmost and rightmost available positions. This greedy strategy ensures the left half is as small as possible lexicographically, which automatically minimizes the entire palindrome. The algorithm runs in **O(N)** time with **O(1)** auxiliary space (excluding the output), making it both simple and optimal.
