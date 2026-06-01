# 🧹 Remove Invalid Parentheses

## 📌 Problem Statement

Given a string `s` consisting of lowercase letters and parentheses:

```text
'(' and ')'
```

A string is valid if:

```text
1. Every '(' has a matching ')'
2. Parentheses are properly nested
3. Letters do not affect validity
```

Remove the **minimum number of invalid parentheses** and return all distinct valid strings in sorted order.

---

## 🎯 Goal

Generate all valid strings such that:

```text
✓ Minimum removals are performed
✓ Parentheses remain valid
✓ No duplicate answers
✓ Output is lexicographically sorted
```

---

## 💡 Intuition

Instead of blindly removing brackets:

```text
Remove
Try
Check validity
Repeat
```

we first calculate:

```text
How many extra '(' exist?
How many extra ')' exist?
```

Then recursion only removes those brackets.

This guarantees:

```text
Minimum removals
```

---

## 🔥 Key Idea

The solution works in two phases.

### Phase 1 : Count Invalid Parentheses

Traverse the string.

For every character:

```text
If '(':
    have_open++

If ')':

    If have_open > 0:
        match previous '('
        have_open--

    Else:
        extra ')'
        have_close++
```

After traversal:

```text
have_open  = extra '(' to remove
have_close = extra ')' to remove
```

---

### Example

Input:

```text
()())(
```

Traversal:

```text
(
open = 1

)
open = 0

(
open = 1

)
open = 0

)
extra close = 1

(
open = 1
```

Final:

```text
have_open  = 1
have_close = 1
```

Meaning:

```text
Remove exactly:

1 '('
1 ')'
```

---

## 🧠 Variables Used

### idx

```java
int idx
```

Current position in the string.

---

### have_open

```java
int have_open
```

Represents:

```text
How many extra '(' still need removal
```

---

### have_close

```java
int have_close
```

Represents:

```text
How many extra ')' still need removal
```

---

### preserve

```java
int preserve
```

Most important variable.

Represents:

```text
Number of unmatched '(' currently kept
```

Think of it as:

```text
balance
```

Example:

```text
Current string:

((()

Unmatched '(' = 3
```

So:

```java
preserve = 3;
```

---

### cur

```java
StringBuilder cur
```

Current answer being built.

Example:

```text
()(
```

---

### ans

```java
TreeSet<String> ans
```

Stores:

```text
✓ Unique answers
✓ Sorted answers
```

---

## 🔄 Recursion Decisions

At every character:

```text
Keep it
OR
Remove it
```

---

### Case 1 : '('

#### Remove

Only if:

```java
have_open > 0
```

```text
Use one removal
```

---

#### Keep

```java
cur.append('(');
```

Update:

```java
preserve + 1
```

Because this bracket needs matching later.

---

### Case 2 : ')'

#### Remove

Only if:

```java
have_close > 0
```

---

#### Keep

Only if:

```java
preserve > 0
```

Why?

Because:

```text
)(
```

is invalid.

A closing bracket must have an unmatched opening bracket available.

When kept:

```java
preserve - 1
```

because one open bracket gets matched.

---

### Case 3 : Normal Character

Example:

```text
a
b
c
x
```

Always keep.

```java
cur.append(ch);
```

No effect on bracket counts.

---

## 🏁 Base Case

When:

```java
idx == n
```

Entire string has been processed.

Accept answer only if:

```java
have_open == 0
have_close == 0
preserve == 0
```

Meaning:

```text
✓ No removals left
✓ No unmatched '(' left
✓ Expression is valid
```

Then:

```java
ans.add(cur.toString());
```

---

## 🔙 Why Backtracking?

Whenever we do:

```java
cur.append(ch);
```

we must undo it:

```java
cur.deleteCharAt(cur.length() - 1);
```

Otherwise:

```text
One recursive branch
will affect another branch
```

This is called:

```text
Backtracking
```

Pattern:

```text
Choose
↓
Recurse
↓
Undo
```

---

## 🧪 Dry Run

Input:

```text
()())()
```

Invalid counts:

```text
have_open  = 0
have_close = 1
```

Start:

```text
helper(
    idx = 0,
    preserve = 0,
    cur = ""
)
```

Eventually recursion explores:

```text
Remove ')' at index 1
→ (())()

Remove ')' at index 4
→ ()()()
```

Valid answers:

```text
(())()
()()()
```

Output:

```text
["(())()", "()()()"]
```

---

## 🌳 Recursion Flow

```text
Start
  |
  v
Count extra '(' and ')'
  |
  v
Call helper()
  |
  v
At each character
  |
  +-- '('
  |      Remove
  |      Keep
  |
  +-- ')'
  |      Remove
  |      Keep only if preserve > 0
  |
  +-- Letter
         Always keep
  |
  v
End of string
  |
  v
Valid?
  |
  +-- Yes -> Add to TreeSet
  |
  +-- No  -> Ignore
```

---

## 📊 Complexity

### Time Complexity

```text
O(2^n)
```

Reason:

```text
Each bracket can be:

Keep
or
Remove
```

---

### Space Complexity

```text
O(2^n)
```

For storing valid answers.

Recursion depth:

```text
O(n)
```

---

## 🎯 Key Takeaways

```text
have_open
    -> extra '(' left to remove

have_close
    -> extra ')' left to remove

preserve
    -> unmatched '(' currently kept

cur
    -> current string being built

TreeSet
    -> removes duplicates
       keeps answers sorted

Backtracking
    -> append
       recurse
       delete
```

---

## 🔥 Most Important Insight

```text
Do NOT randomly remove brackets.
```

First calculate:

```text
Minimum '(' removals
Minimum ')' removals
```

Then recursively remove exactly those brackets.

This guarantees:

```text
Minimum-removal valid answers.
```
