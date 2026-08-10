
# Friends Pairing Problem

---

# Problem Statement

Given `n` friends, each friend can either:

```text
1. Remain single
2. Pair up with exactly one other friend
```

Each friend can participate in at most one pair.

Find the total number of different ways in which all `n` friends can remain single or be paired.

Two pairings with the same two friends are considered identical:

```text
{1,2} == {2,1}
```

---

### Example 1

```text
Input:
n = 3

Output:
4
```

The possible arrangements are:

```text
{1}, {2}, {3}

{1}, {2,3}

{1,2}, {3}

{1,3}, {2}
```

Therefore:

```text
Answer = 4
```

---

### Example 2

```text
Input:
n = 2

Output:
2
```

The possibilities are:

```text
{1}, {2}

{1,2}
```

Therefore:

```text
Answer = 2
```

---

### Example 3

```text
Input:
n = 1

Output:
1
```

The only possibility is:

```text
{1}
```

---

# Goal

Calculate the total number of ways to arrange `n` friends such that every friend is either:

```text
Single
```

or:

```text
Paired with exactly one other friend
```

---

# Intuition

The easiest way to understand this problem is to focus on **one particular friend**.

Suppose there are `n` friends.

Take the first friend.

There are only two possibilities.

### Case 1: The Friend Remains Single

If the first friend remains single, then we only need to arrange the remaining:

```text
n - 1
```

friends.

Number of ways:

```text
solve(n - 1)
```

---

### Case 2: The Friend Gets Paired

The first friend can pair with any one of the remaining:

```text
n - 1
```

friends.

After choosing the partner, there are:

```text
n - 2
```

friends left to arrange.

Number of ways:

```text
(n - 1) * solve(n - 2)
```

---

### Combine Both Cases

Therefore:

```text
solve(n)
=
solve(n - 1)
+
(n - 1) * solve(n - 2)
```

This is the fundamental recurrence.

---

# Key Idea

The recurrence is:

```text
F(n) = F(n - 1) + (n - 1) * F(n - 2)
```

where:

```text
F(n) = number of ways to arrange n friends
```

The two terms represent:

```text
F(n - 1)
```

→ First friend stays single.

and:

```text
(n - 1) * F(n - 2)
```

→ First friend pairs with one of the remaining `n - 1` friends.

---

# Thought Process

### Step 1: Choose One Friend

Consider friend `1`.

Every valid arrangement must put friend `1` into exactly one of two categories:

```text
Single
```

or:

```text
Paired
```

These cases are mutually exclusive, so their counts can be added.

---

### Step 2: Friend Remains Single

If friend `1` remains single:

```text
Remaining friends = n - 1
```

Therefore:

```text
ways = solve(n - 1)
```

---

### Step 3: Friend Is Paired

Friend `1` can pair with:

```text
friend 2
friend 3
...
friend n
```

There are:

```text
n - 1
```

choices.

After selecting the partner, two friends are already handled:

```text
1 friend + 1 partner
```

So:

```text
n - 2
```

friends remain.

For each partner choice:

```text
solve(n - 2)
```

arrangements are possible.

Therefore:

```text
ways = (n - 1) * solve(n - 2)
```

---

### Step 4: Add the Cases

```text
solve(n)
=
solve(n - 1)
+
(n - 1) * solve(n - 2)
```

---

# Code (Functions Only)

```java
int countFriendsPairings(int n) {

    return solve(n);
}

private int solve(int n) {

    if (n == 1 || n == 2)
        return n;

    return solve(n - 1) + (n - 1) * solve(n - 2);
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
n = 4
```

We want:

```text
solve(4)
```

Using the recurrence:

```text
solve(4)
=
solve(3)
+
3 * solve(2)
```

---

## Calculate solve(3)

```text
solve(3)
=
solve(2)
+
2 * solve(1)
```

Base cases:

```text
solve(2) = 2
solve(1) = 1
```

Therefore:

```text
solve(3)
=
2 + 2 * 1

= 4
```

---

## Calculate solve(4)

Now:

```text
solve(4)
=
solve(3)
+
3 * solve(2)
```

Substitute:

```text
solve(4)
=
4 + 3 * 2

= 10
```

Therefore:

```text
Answer = 10
```

---

### The 10 Arrangements

```text
All single:
{1},{2},{3},{4}

One pair:
{1,2},{3},{4}
{1,3},{2},{4}
{1,4},{2},{3}
{2,3},{1},{4}
{2,4},{1},{3}
{3,4},{1},{2}

Two pairs:
{1,2},{3,4}
{1,3},{2,4}
{1,4},{2,3}
```

Total:

```text
4 + 6 + 3 = 13
```

This reveals an important issue: the recurrence actually gives:

```text
F(4) = F(3) + 3F(2)
     = 4 + 6
     = 10
```

The listed one-pair arrangements are six, while the all-single arrangement is one, not four. Correct grouping is:

```text
All single:
{1},{2},{3},{4} -> 1 arrangement

One pair:
6 arrangements

Two pairs:
3 arrangements

Total:
1 + 6 + 3 = 10
```

So:

```text
Answer = 10
```

---

# Logic Flow

```text
Start

↓

countFriendsPairings(n)

↓

solve(n)

↓

Is n == 1 or n == 2?

     Yes
      |
      v
   return n

     No
      |
      v
Consider one friend

↓

Case 1:
Friend remains single

ways = solve(n - 1)

↓

Case 2:
Friend gets paired

Number of partners = n - 1

Remaining friends = n - 2

ways = (n - 1) * solve(n - 2)

↓

Combine:

solve(n)
=
solve(n - 1)
+
(n - 1) * solve(n - 2)

↓

Return answer

↓

End
```

---

# Complexity

The provided code uses plain recursion without memoization.

The same subproblems are recalculated many times.

For example:

```text
solve(n)
├── solve(n-1)
│   ├── solve(n-2)
│   └── solve(n-3)
└── solve(n-2)
    ├── solve(n-3)
    └── solve(n-4)
```

This creates an exponential recursion tree.

Therefore, the time complexity of the **given implementation** is approximately:

```text
O(2^N)
```

More precisely, it follows the growth of the Fibonacci-like recurrence.

The recursion depth is:

```text
O(N)
```

Therefore:

```text
Space Complexity = O(N)
```

due to the recursive call stack.

---

# Key Takeaways

- Focus on one friend to derive the recurrence.
- Every friend has two possibilities:
  ```text
  Single
  Paired
  ```
- If the friend stays single:
  ```text
  solve(n - 1)
  ```
- If the friend is paired:
  ```text
  (n - 1) * solve(n - 2)
  ```
- Therefore:
  ```text
  solve(n)
  =
  solve(n - 1)
  +
  (n - 1) * solve(n - 2)
  ```
- The base cases are:
  ```text
  solve(1) = 1
  solve(2) = 2
  ```
- The provided recursive solution has exponential time because it does not use memoization.

---

# Most Important Insight

The most important insight is to **fix one friend and divide all possibilities into two mutually exclusive cases**.

For friend `1`:

```text
                    Friend 1
                       |
             +---------+---------+
             |                   |
           Single              Paired
             |                   |
        n - 1 friends       Choose partner
                                 |
                              n - 1 choices
                                 |
                           n - 2 friends remain
```

Therefore:

```text
F(n)
=
F(n - 1)
+
(n - 1) × F(n - 2)
```

This is a classic DP recurrence.

The key pattern is:

```text
Choose one object
      ↓
Break into mutually exclusive cases
      ↓
Count each case
      ↓
Add the results
```

---

# Summary

The Friends Pairing Problem asks for the number of ways `n` friends can either remain single or form pairs.

By focusing on one friend, there are two possibilities:

```text
1. The friend stays single
   → solve(n - 1)

2. The friend pairs with one of n - 1 friends
   → (n - 1) * solve(n - 2)
```

Thus the recurrence is:

```text
F(n) = F(n - 1) + (n - 1)F(n - 2)
```

with:

```text
F(1) = 1
F(2) = 2
```

The provided code correctly implements this recurrence using recursion.

Its complexity is:

```text
Time  : O(2^N)
Space : O(N)
```

For larger constraints, the same recurrence should be optimized using **memoization or bottom-up DP**, reducing the time complexity to `O(N)`.
