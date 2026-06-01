# 🧹 Remove Invalid Parentheses

> Difficulty: Hard
>
> Topics: Recursion, Backtracking, Strings

---

## 📌 Problem Statement

Given a string `s` consisting of lowercase letters and parentheses:

```text
'(' and ')'
```

A string is considered valid if:

```text
1. Every '(' has a matching ')'
2. Parentheses are properly nested
3. Letters do not affect validity
```

Remove the **minimum number of invalid parentheses** and return all distinct valid strings in lexicographically sorted order.

---

## 🎯 Goal

Generate all valid strings such that:

```text
✓ Minimum removals are performed
✓ Parentheses remain valid
✓ No duplicate answers
✓ Output is sorted
```

---

## 💡 Intuition

Instead of randomly removing brackets:

```text
Remove
Check
Repeat
```

we first determine:

```text
How many extra '(' exist?
How many extra ')' exist?
```

Then recursion removes exactly those brackets.

This guarantees:

```text
Minimum removals
```

---

## 🔥 Key Idea

The solution works in two phases.

### Phase 1 : Count Invalid Parentheses

Traverse the string.

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

### Phase 2 : Backtracking

At every character:

```text
Keep it
OR
Remove it
```

Rules:

```text
For '(':
    Remove if have_open > 0
    Keep and preserve++

For ')':
    Remove if have_close > 0
    Keep only if preserve > 0

For letters:
    Always keep
```

---

## 🧠 Variables Used

### idx

```java
int idx
```

Current index being processed.

---

### have_open

```java
int have_open
```

Represents:

```text
Extra '(' still left to remove
```

---

### have_close

```java
int have_close
```

Represents:

```text
Extra ')' still left to remove
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

Therefore:

```java
preserve = 3;
```

---

### cur

```java
StringBuilder cur
```

Current string being built.

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

# 💻 Code

```java
class Solution {

    public List<String> validParenthesis(String s) {

        int n = s.length();

        int have_open = 0;
        int have_close = 0;

        // Calculate the minimum number of invalid '(' and ')'
        // that must be removed
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);

            if(ch == '(')
                have_open++;

            else if(ch == ')')
                if(have_open > 0)
                    have_open--;
                else
                    have_close++;
        }

        Set<String> ans = new TreeSet<>();

        helper(
                0,
                have_open,
                0,
                have_close,
                new StringBuilder(),
                ans,
                s,
                n
        );

        return new ArrayList<>(ans);
    }

    private void helper(
            int idx,
            int have_open,
            int preserve,
            int have_close,
            StringBuilder cur,
            Set<String> ans,
            String s,
            int n
    ){

        if(idx == n){

            // Valid expression formed
            if(have_open == 0 &&
               have_close == 0 &&
               preserve == 0){

                ans.add(cur.toString());
            }

            return;
        }

        char ch = s.charAt(idx);

        if(ch == '('){

            if(have_open > 0){

                // remove this '('
                helper(
                        idx + 1,
                        have_open - 1,
                        preserve,
                        have_close,
                        cur,
                        ans,
                        s,
                        n
                );
            }

            // keep this '('
            cur.append(ch);

            helper(
                    idx + 1,
                    have_open,
                    preserve + 1,
                    have_close,
                    cur,
                    ans,
                    s,
                    n
            );

            cur.deleteCharAt(cur.length() - 1);
        }

        else if(ch == ')'){

            if(have_close > 0){

                // remove this ')'
                helper(
                        idx + 1,
                        have_open,
                        preserve,
                        have_close - 1,
                        cur,
                        ans,
                        s,
                        n
                );
            }

            // keep ')' only if an unmatched '(' exists
            if(preserve > 0){

                cur.append(ch);

                helper(
                        idx + 1,
                        have_open,
                        preserve - 1,
                        have_close,
                        cur,
                        ans,
                        s,
                        n
                );

                cur.deleteCharAt(cur.length() - 1);
            }
        }

        else{

            // letters are always preserved
            cur.append(ch);

            helper(
                    idx + 1,
                    have_open,
                    preserve,
                    have_close,
                    cur,
                    ans,
                    s,
                    n
            );

            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
```

---

## 🧪 Dry Run

Input:

```text
()())()
```

---

### Step 1 : Count Invalid Parentheses

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

)
open = 0
```

Final:

```text
have_open  = 0
have_close = 1
```

Meaning:

```text
Remove exactly one ')'
```

---

### Step 2 : Start Recursion

Initial state:

```text
idx        = 0
have_open  = 0
have_close = 1
preserve   = 0
cur        = ""
```

---

### Step 3 : Explore Choices

Eventually recursion discovers:

```text
Remove ')' at index 1
→ (())()

Remove ')' at index 4
→ ()()()
```

Both are valid.

---

### Final Output

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

Each bracket can be:

```text
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