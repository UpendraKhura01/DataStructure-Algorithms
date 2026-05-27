# 🔤 Count the Number of Special Characters II

## 📌 Problem Statement

Given a string `word` containing uppercase and lowercase English letters.

A letter `c` is called special if:

    1. Both lowercase and uppercase versions exist
    2. Every lowercase occurrence appears before the first uppercase occurrence

Return the number of special characters in the string.

---

## 🎯 Goal

Count how many characters satisfy:

``` id="special-condition"
lowercase appears before uppercase
```

and both forms exist.

---

## 💡 Intuition

For a character to be special:

``` id="condition-main"
all lowercase occurrences
must come before
the first uppercase occurrence
```

Example:

``` id="good-example"
word = "aaAbcBC"
```

Character `a`:

``` id="a-good"
a a A
```

All lowercase `a` come before uppercase `A`.

So:

``` id="a-valid"
a is special
```

---

Now consider:

``` id="bad-example"
word = "AbBCab"
```

Character `a`:

``` id="a-bad"
A ... a
```

Uppercase appears before lowercase.

So:

``` id="a-invalid"
a is NOT special
```

---

## 🔥 Key Idea

Two approaches are used.

---

### Approach 1: HashSet Simulation

Track:

``` id="sets-used"
capital → uppercase letters seen
invalid → lowercase letters invalidated
counted → already counted characters
```

Logic:

- If uppercase appears first
- Then later lowercase appears

That character becomes invalid.

---

### Approach 2: Optimized Index Tracking

For every character:

Track:

``` id="tracking"
last lowercase position
first uppercase position
```

A character is special if:

``` id="optimized-condition"
lastLowercase < firstUppercase
```

and both exist.

This is the cleanest solution.

---

## 🧠 Thought Process

Suppose:

``` id="input-example"
word = "aaAbcBC"
```

For character `a`:

Positions:

``` id="a-pos"
a → 0,1
A → 2
```

Check:

``` id="a-check"
last lowercase = 1
first uppercase = 2
```

Since:

``` id="a-valid-check"
1 < 2
```

`a` is special.

---

For character `b`:

``` id="b-pos"
b → 3
B → 5
```

Check:

``` id="b-check"
3 < 5
```

Special.

---

For character `c`:

``` id="c-pos"
c → 4
C → 6
```

Check:

``` id="c-check"
4 < 6
```

Special.

---

Total:

``` id="count-final"
3
```

---

Now consider:

``` id="bad-input"
word = "AbBCab"
```

Character `a`:

``` id="bad-a"
A at index 0
a at index 4
```

Check:

``` id="bad-a-check"
last lowercase = 4
first uppercase = 0
```

Condition fails:

``` id="bad-fail"
4 < 0 → false
```

So `a` is not special.

---

## 💻 Code

### HashSet Approach

```java
static int numberOfSpecialChars(String word) {

    HashSet<Character> capital = new HashSet<>();
    HashSet<Character> invalid = new HashSet<>();
    HashSet<Character> counted = new HashSet<>();

    int n = word.length();

    for (int i = 0; i < n; i++) {

        char c = word.charAt(i);

        if (c >= 'A' && c <= 'Z') {
            capital.add(c);
        }

        else if (capital.contains((char)(c - 32))) {
            invalid.add(c);
        }
    }

    int count = 0;

    for (int i = 0; i < n; i++) {

        char c = word.charAt(i);

        if (
            capital.contains((char)(c - 32))
            && !invalid.contains(c)
            && !counted.contains(c)
        ) {
            count++;
            counted.add(c);
        }
    }

    return count;
}
```

---

### Optimized Approach

```java
static int numberOfSpecialChars1(String word) {

    int[] last = new int[26];
    int[] first = new int[26];

    Arrays.fill(last, -1);
    Arrays.fill(first, -1);

    int n = word.length();

    for (int i = 0; i < n; i++) {

        char c = word.charAt(i);

        if (c >= 'a' && c <= 'z') {
            last[c - 'a'] = i;
        }

        else {

            int idx = c - 'A';

            if (first[idx] == -1) {
                first[idx] = i;
            }
        }
    }

    int count = 0;

    for (int i = 0; i < 26; i++) {

        if (
            last[i] != -1
            && first[i] != -1
            && last[i] < first[i]
        ) {
            count++;
        }
    }

    return count;
}
```

---

## 🧪 Dry Run

Input:

``` id="dryrun-input"
word = "aaAbcBC"
```

Length:

``` id="length"
n = 7
```

---

Step 1: Store positions

Lowercase last positions:

``` id="lowercase"
a → 1
b → 3
c → 4
```

Uppercase first positions:

``` id="uppercase"
A → 2
B → 5
C → 6
```

---

Step 2: Validate characters

Character `a`:

``` id="validate-a"
last[a] = 1
first[A] = 2

1 < 2 → true
```

Count:

``` id="count-a"
1
```

---

Character `b`:

``` id="validate-b"
3 < 5 → true
```

Count:

``` id="count-b"
2
```

---

Character `c`:

``` id="validate-c"
4 < 6 → true
```

Count:

``` id="count-c"
3
```

---

Final Answer:

``` id="final-answer"
3
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Store:
        last lowercase positions
        first uppercase positions
      |
      v
    Traverse all 26 characters
      |
      v
    Does lowercase exist?
      |
      v
    Does uppercase exist?
      |
      v
    Check:
        lastLowercase < firstUppercase
      |
      v
    If true:
        count++
      |
      v
    Return count

---

## 📊 Complexity

### HashSet Approach

Time Complexity:

``` id="tc1"
O(n)
```

Reason:

    Two traversals of string.

---

Space Complexity:

``` id="sc1"
O(1)
```

Reason:

    At most 26 letters stored.

---

### Optimized Approach

Time Complexity:

``` id="tc2"
O(n)
```

Reason:

    One traversal for positions
    One traversal over 26 letters

---

Space Complexity:

``` id="sc2"
O(1)
```

Reason:

    Fixed-size arrays of size 26.

---

## 🎯 Key Takeaways

- Both lowercase and uppercase must exist.
- Lowercase must completely appear before uppercase.
- Track positions instead of simulating transformations.
- Last lowercase position is important.
- First uppercase position is important.
- Optimized array solution is cleaner and faster.

---

## 🔥 Most Important Insight

A character is special if:

``` id="main-insight"
last lowercase occurrence
comes before
first uppercase occurrence
```

This single condition completely solves the problem.

---

## 🏁 Summary

To solve this problem:

    1. Track last occurrence of lowercase letters.
    2. Track first occurrence of uppercase letters.
    3. For every character:
           check if both exist
           and
           last lowercase < first uppercase
    4. Count valid characters.
    5. Return final count.

Efficient solution:

    Time  : O(n)
    Space : O(1)