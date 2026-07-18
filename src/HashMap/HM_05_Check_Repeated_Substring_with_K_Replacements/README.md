# Check Repeated Substring with K Replacements

## Problem Statement

Given a string `s` and an integer `k`, determine whether it is possible to convert `s` into a string formed by repeating the same substring by performing **at most one replacement operation**.

In one operation, you may replace **exactly one substring of length `k`** (whose starting index is a multiple of `k`) with any sequence of `k` characters.

Return:

- `true` if the conversion is possible.
- `false` otherwise.

---

## Example 1

**Input**

```text
s = "abcbedabcabc"
k = 3
```

**Output**

```text
true
```

**Explanation**

The string is divided into blocks of length `3`:

```text
["abc", "bed", "abc", "abc"]
```

Replace `"bed"` with `"abc"`.

Result:

```text
"abcabcabcabc"
```

which is a repetition of `"abc"`.

---

## Example 2

**Input**

```text
s = "bdac"
k = 2
```

**Output**

```text
true
```

**Explanation**

The blocks are

```text
["bd", "ac"]
```

There are only two blocks.

Replace either one with the other to make both identical.

---

## Example 3

**Input**

```text
s = "abcdabcd"
k = 2
```

**Output**

```text
false
```

**Explanation**

The blocks are

```text
["ab", "cd", "ab", "cd"]
```

Both `"ab"` and `"cd"` appear multiple times.

Changing only one block cannot make every block identical.

---

# Approach

Divide the string into consecutive blocks of length `k`.

Store the frequency of each block using a **HashMap**.

There are three possible cases.

### Case 1

Only one unique block exists.

```text
["abc", "abc", "abc"]
```

The string is already valid.

Return `true`.

---

### Case 2

More than two unique blocks exist.

Example:

```text
["ab", "cd", "ef"]
```

One replacement cannot eliminate more than one distinct block.

Return `false`.

---

### Case 3

Exactly two unique blocks exist.

Suppose the frequencies are

```text
A -> x
B -> y
```

One replacement can succeed **only if one of the blocks appears exactly once**.

Example:

```text
["abc", "abc", "bed", "abc"]
```

Frequency:

```text
abc -> 3
bed -> 1
```

Replace `"bed"` with `"abc"`.

Otherwise,

```text
ab -> 2
cd -> 2
```

Changing one occurrence still leaves another different block.

Hence the answer is `false`.

---

# Algorithm

Check if the string length is divisible by `k`.

```text
If n % k != 0
    return false
```

Split the string into blocks of length `k`.

Store each block's frequency in a HashMap.

Then:

```text
If map size == 1
    return true

If map size > 2
    return false

Otherwise
    return true only if one frequency equals 1
```

---

# Correctness

The string consists of fixed blocks of length `k`.

A single operation can modify **only one block**.

- If every block is already identical, no replacement is needed.
- If there are more than two distinct blocks, changing one block cannot remove all remaining differences.
- If there are exactly two distinct blocks, the conversion is possible only when one block occurs exactly once. Replacing that unique block makes every block identical.
- If both blocks occur multiple times, changing one occurrence still leaves another mismatching block.

Therefore, the algorithm returns `true` if and only if the string can be converted into a repetition of one substring using at most one replacement.

---

# Complexity Analysis

Let

- `n` = length of the string

The number of blocks is

```text
n / k
```

### Time

Building the HashMap:

```text
O(n)
```

Checking the frequencies:

```text
O(1)
```

Overall:

```text
O(n)
```

### Space

The HashMap stores at most one entry per distinct block.

```text
O(n / k)
```

which is `O(n)` in the worst case.

---

# Java Solution

```java
import java.util.HashMap;

class Solution {

    boolean kSubstr(String s, int k) {

        int n = s.length();

        if (k <= 0 || n % k != 0)
            return false;

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i += k) {
            String block = s.substring(i, i + k);
            map.put(block, map.getOrDefault(block, 0) + 1);
        }

        if (map.size() == 1)
            return true;

        if (map.size() > 2)
            return false;

        for (int freq : map.values()) {
            if (freq == 1)
                return true;
        }

        return false;
    }
}
```