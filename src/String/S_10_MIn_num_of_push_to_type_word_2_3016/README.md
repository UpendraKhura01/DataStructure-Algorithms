
# 3016. Minimum Number of Pushes to Type Word II

---

# Problem Statement

You are given a string `word` consisting of lowercase English letters.

A telephone keypad has **8 usable keys** (`2` to `9`), and each letter must be assigned to exactly one key.

Typing a letter requires:

- **1 push** if it is the first letter on its key.
- **2 pushes** if it is the second letter.
- **3 pushes** if it is the third letter.
- and so on.

You may **remap the letters to the keys in any way**.

Return the **minimum number of key pushes** required to type the given word.

---

### Example 1

```text
Input

word = "abcde"

Output

5
```

Explanation

Assign every letter as the first character of a different key.

```text
Cost

1 + 1 + 1 + 1 + 1 = 5
```

---

### Example 2

```text
Input

word = "xyzxyzxyzxyz"

Output

12
```

Explanation

Each frequently occurring letter occupies the first position on a key.

```text
x → 1 push

y → 1 push

z → 1 push

Total = 12
```

---

### Example 3

```text
Input

word = "aabbccddeeffgghhiiiiii"

Output

24
```

---

# Goal

Assign letters to keypad positions so that the **total number of pushes** required to type the word is minimized.

---

# Intuition

Letters appearing more frequently should require fewer pushes.

For example,

```text
Frequency

a → 100

b → 2
```

It would be wasteful to assign

```text
a → 4 pushes

b → 1 push
```

Instead,

```text
a → 1 push

b → 4 pushes
```

This minimizes the total typing cost.

Since there are **8 keys**, only **8 letters** can occupy the first position.

The next **8 letters** occupy the second position, and so on.

---

# Key Idea

1. Count the frequency of every letter.
2. Sort frequencies in ascending order.
3. Process frequencies from largest to smallest.
4. Assign:
    - Highest 8 frequencies → **1 push**
    - Next 8 frequencies → **2 pushes**
    - Next 8 frequencies → **3 pushes**
    - Remaining (at most 2 letters) → **4 pushes**
5. Sum all contributions.

This greedy strategy guarantees the minimum total number of pushes.

---

# Thought Process

### Step 1

Count how many times every character appears.

```text
freq[26]
```

---

### Step 2

Sort the frequency array.

```text
Smallest ........ Largest
```

---

### Step 3

Traverse from the largest frequency downward.

The largest frequencies receive the smallest typing cost.

---

### Step 4

Assign costs in groups.

```text
First 8 letters

↓

1 push
```

```text
Next 8 letters

↓

2 pushes
```

```text
Next 8 letters

↓

3 pushes
```

```text
Remaining 2 letters

↓

4 pushes
```

---

### Step 5

Return the accumulated answer.

---

# Code (Functions Only)

```java
int minimumPushes(String word) {

    int n = word.length();

    int[] freq = new int[26];

    for (int i = 0; i < n; i++) {

        char c = word.charAt(i);

        freq[c - 'a']++;
    }

    Arrays.sort(freq);

    int ans = 0;

    for (int i = 25; i >= 18; i--) {
        ans += freq[i];
    }

    for (int i = 17; i >= 10; i--) {
        ans += freq[i] * 2;
    }

    for (int i = 9; i >= 2; i--) {
        ans += freq[i] * 3;
    }

    ans += freq[0] * 4;
    ans += freq[1] * 4;

    return ans;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
word = "abcde"
```

---

### Step 1 : Frequency Count

```text
a → 1

b → 1

c → 1

d → 1

e → 1
```

Remaining letters

```text
0
```

---

### Step 2 : Sort Frequencies

```text
0 0 0 ... 1 1 1 1 1
```

---

### Step 3 : Assign Costs

There are only five distinct letters.

All fit into the first group.

```text
a → 1 push

b → 1 push

c → 1 push

d → 1 push

e → 1 push
```

---

### Total

```text
1 + 1 + 1 + 1 + 1

= 5
```

---

### Final Output

```text
5
```

---

# Logic Flow

```text
Start

↓

Count frequency of each letter

↓

Sort frequencies

↓

Take largest 8 frequencies

↓

Multiply by 1

↓

Take next 8 frequencies

↓

Multiply by 2

↓

Take next 8 frequencies

↓

Multiply by 3

↓

Multiply remaining 2 frequencies by 4

↓

Return total pushes

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

Sorting 26 elements:

```text
O(26 log 26)
```

which is constant.

Final traversal:

```text
O(26)
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

Overall auxiliary space:

```text
O(1)
```

---

# Key Takeaways

- Only the **frequency** of each letter matters, not its original position.
- The most frequent letters should always receive the fewest key presses.
- Since there are exactly **8 usable keys**, the frequencies are naturally divided into groups of **8**.
- Sorting just **26** frequencies is effectively constant time.
- A greedy assignment based on descending frequency produces the optimal answer.

---

# Most Important Insight

The keypad mapping is completely flexible, so the optimal strategy is purely greedy: assign the **highest-frequency letters** to the **lowest push counts**. With 8 available keys, the first 8 most frequent letters cost **1 push**, the next 8 cost **2 pushes**, the next 8 cost **3 pushes**, and the remaining 2 letters cost **4 pushes**.

---

# Summary

The solution counts the frequency of every letter, sorts these frequencies, and greedily assigns the most frequent letters to the cheapest keypad positions. Because only 26 lowercase letters exist, sorting is effectively constant time, making the overall algorithm **O(N)**. This frequency-based greedy approach guarantees the minimum total number of key presses while keeping the implementation simple and efficient.
