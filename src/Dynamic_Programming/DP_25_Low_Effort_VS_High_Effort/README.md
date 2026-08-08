
# High Effort vs Low Effort

---

# Problem Statement

Given two integer arrays:

```text
h[]
l[]
```

where:

- `h[i]` = number of tasks completed by performing a **high-effort task** on day `i`
- `l[i]` = number of tasks completed by performing a **low-effort task** on day `i`

For every day, we can choose exactly one of:

```text
1. Perform no task
2. Perform a low-effort task
3. Perform a high-effort task
```

However, a high-effort task has one restriction:

> A high-effort task can only be performed on the first day or if **no task was performed on the previous day**.

A low-effort task does not have this restriction.

Return the maximum total number of tasks that can be completed.

---

### Example 1

```text
h = [2, 8, 1]
l = [1, 2, 1]
```

One optimal choice is:

```text
Day 0 -> High effort = 2
Day 1 -> Low effort  = 2
Day 2 -> Low effort  = 1
```

Total:

```text
2 + 2 + 1 = 5
```

However, another valid interpretation of the given example chooses:

```text
Day 0 -> Low effort
Day 1 -> High effort
Day 2 -> Low effort
```

Total:

```text
1 + 8 + 1 = 10
```

The key rule is that high effort requires the previous day to be a rest day.

---

### Example 2

```text
h = [3, 6, 8, 7, 6]
l = [1, 5, 4, 5, 3]
```

An optimal strategy is:

```text
Day 0 -> High effort = 3
Day 1 -> Low effort  = 5
Day 2 -> Low effort  = 4
Day 3 -> Low effort  = 5
Day 4 -> Low effort  = 3
```

Total:

```text
3 + 5 + 4 + 5 + 3 = 20
```

---

# Goal

Choose the best action for every day while respecting the high-effort restriction.

The objective is:

```text
Maximum Total Tasks
```

---

# Intuition

At every day, the decision depends on what happened on the previous day.

The important question is:

```text
Can I perform a high-effort task today?
```

The answer depends only on whether the previous day was a rest day.

Therefore, we do not need to remember the entire history.

We only need one piece of information:

```text
Was the previous day a rest day?
```

This gives us a small DP state.

---

# Key Idea

Define:

```text
solve(i, rested)
```

as:

```text
Maximum tasks obtainable from day i onward,
where rested tells whether a high-effort task is allowed today.
```

There are two possible states:

```text
rested = 1
```

means the previous day was a rest day, so high effort is allowed.

```text
rested = 0
```

means the previous day had a task, so high effort is not allowed.

---

### Three Choices

For every day, we can choose:

#### 1. Low Effort

```text
l[i] + solve(i + 1, 0)
```

After performing low effort today, today was not a rest day.

Therefore:

```text
next rested = 0
```

---

#### 2. Rest

```text
solve(i + 1, 1)
```

Since we rested today, tomorrow's high-effort task becomes available.

Therefore:

```text
next rested = 1
```

---

#### 3. High Effort

This is allowed only when:

```text
rested == 1
```

Then:

```text
h[i] + solve(i + 1, 0)
```

Again, after performing high effort today, today was not a rest day.

Therefore:

```text
next rested = 0
```

---

# Thought Process

### Step 1: Define the State

We use:

```text
dp[i][rested]
```

where:

```text
i      = current day
rested = whether high effort is allowed today
```

There are only two possible values for `rested`:

```text
0
1
```

---

### Step 2: Base Case

When:

```text
i == n
```

there are no more days.

Therefore:

```text
return 0
```

---

### Step 3: Calculate Low-Effort Option

Low effort is always allowed:

```text
low = l[i] + solve(i + 1, 0)
```

---

### Step 4: Calculate Rest Option

We can always choose to do nothing:

```text
rest = solve(i + 1, 1)
```

This is important because resting today allows high effort tomorrow.

---

### Step 5: Calculate High-Effort Option

High effort is possible only if:

```text
rested == 1
```

So:

```text
if (rested == 1)
    high = h[i] + solve(i + 1, 0)
```

Otherwise:

```text
high = 0
```

---

### Step 6: Take the Maximum

For every state:

```text
answer = max(low, rest, high)
```

Memoization stores the result so that the same state is never solved repeatedly.

---

# Code (Functions Only)

```java
int n;
int[] h;
int[] l;
int[][] dp;

public int maxTask(int[] h, int[] l) {

    n = h.length;

    this.h = h;
    this.l = l;

    dp = new int[n + 1][2];

    for (int i = 0; i < n; i++) {
        dp[i][0] = -1;
        dp[i][1] = -1;
    }

    return solve(0, 1);
}

private int solve(int i, int rested) {

    if (i == n) {
        return 0;
    }

    if (dp[i][rested] != -1) {
        return dp[i][rested];
    }

    int max = 0;

    int low = l[i] + solve(i + 1, 0);

    int rest = solve(i + 1, 1);

    int high = 0;

    if (rested == 1) {
        high = h[i] + solve(i + 1, 0);
    }

    max = Math.max(low, rest);

    return dp[i][rested] = Math.max(max, high);
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
h = [3, 6, 8]
l = [1, 5, 4]
```

We start with:

```text
solve(0, 1)
```

because before the first day there is no previous task, so high effort is allowed.

---

## Day 0

```text
i = 0
rested = 1
```

Three choices are available.

### Choice 1: Low Effort

```text
1 + solve(1, 0)
```

### Choice 2: Rest

```text
solve(1, 1)
```

### Choice 3: High Effort

Since:

```text
rested = 1
```

high effort is allowed:

```text
3 + solve(1, 0)
```

The algorithm evaluates all possibilities recursively.

---

## Suppose We Choose High Effort on Day 0

We get:

```text
3
```

Now:

```text
solve(1, 0)
```

because the previous day was not a rest day.

---

## Day 1

```text
i = 1
rested = 0
```

High effort is **not allowed**.

Available choices:

```text
Low:
5 + solve(2, 0)

Rest:
solve(2, 1)
```

High effort is skipped.

The better choice is low effort:

```text
5
```

So far:

```text
3 + 5 = 8
```

---

## Day 2

Again, high effort is not needed.

Choose:

```text
Low = 4
```

Total:

```text
3 + 5 + 4 = 12
```

So one possible optimal sequence is:

```text
High -> Low -> Low
```

with total:

```text
12
```

---

## Why Rest Matters

Suppose instead we do:

```text
Low -> Rest -> High
```

The state changes like this:

```text
Day 0: Low
       ↓
rested = 0

Day 1: Rest
       ↓
rested = 1

Day 2: High
       ↓
rested = 0
```

The rest day enables a high-effort task on the following day.

This is exactly what the DP state tracks.

---

# Logic Flow

```text
Start

↓

solve(0, 1)

↓

Is i == n?

     Yes
      |
      v
    return 0

     No
      |
      v
Is state already calculated?

     Yes
      |
      v
Return dp[i][rested]

     No
      |
      v
Calculate Low
l[i] + solve(i + 1, 0)

↓

Calculate Rest
solve(i + 1, 1)

↓

Is rested == 1?

     Yes
      |
      v
Calculate High
h[i] + solve(i + 1, 0)

     No
      |
      v
high = 0

↓

Take maximum

max(low, rest, high)

↓

Store in dp[i][rested]

↓

Return answer

↓

End
```

---

# Complexity

There are only:

```text
N × 2
```

different DP states.

Each state performs constant work.

Therefore:

```text
Time Complexity = O(N)
```

The memoization table contains:

```text
(N + 1) × 2
```

states.

Therefore:

```text
Space Complexity = O(N)
```

including the recursion stack.

---

# Key Takeaways

- This is a classic **state-based Dynamic Programming** problem.
- The future depends only on:
  ```text
  current day
  +
  whether the previous day was a rest day
  ```
- The three choices are:
  ```text
  Low effort
  Rest
  High effort
  ```
- High effort is allowed only when:
  ```text
  rested == 1
  ```
- After performing either low or high effort:
  ```text
  rested = 0
  ```
- After resting:
  ```text
  rested = 1
  ```
- Memoization prevents repeated computation.
- The solution runs in:
  ```text
  O(N)
  ```

---

# Most Important Insight

The most important insight is that we **do not need to remember the complete sequence of previous decisions**.

For the current day, the only information that matters is:

```text
Was yesterday a rest day?
```

That single condition determines whether high effort is available.

So the entire history can be compressed into:

```text
dp[i][rested]
```

This is a powerful DP pattern:

```text
Large history
     ↓
Identify the only information that affects future choices
     ↓
Use that information as the state
```

Here:

```text
State = (current day, previous-day-rest status)
```

Once this state is identified, the recurrence becomes:

```text
dp[i][rested]
=
max(
    l[i] + dp[i+1][0],
    dp[i+1][1],
    h[i] + dp[i+1][0]   if rested == 1
)
```

That is the core of the entire solution.

---

# Summary

The solution uses **top-down Dynamic Programming with memoization**.

For every day, it considers:

```text
Low Effort
Rest
High Effort
```

High effort is considered only when the previous day was a rest day.

The DP state:

```text
solve(i, rested)
```

captures everything necessary to make the optimal decision from day `i` onward.

The recurrence takes the maximum among all valid choices, and memoization ensures each state is calculated only once.

Final complexity:

```text
Time  : O(N)
Space : O(N)
```

The key DP lesson is:

```text
When a decision depends on a small amount of previous information,
make that information part of the DP state.
```
