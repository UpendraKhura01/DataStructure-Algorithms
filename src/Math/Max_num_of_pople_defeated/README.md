# Maximum Number of People Defeated

**Difficulty:** Medium

## Problem Statement

There are infinitely many people standing in a row, indexed from **1**. The strength of the person at index **i** is **i²**.

Given a strength **p**, determine the **maximum number of people** that can be defeated.

A person with strength **x** can be defeated only if:

- `p ≥ x`
- After defeating that person, `p` decreases by `x`.

Return the maximum number of people that can be defeated.

---

## Examples

### Example 1

**Input**
```text
p = 14
```

**Output**
```text
3
```

**Explanation**

The strengths are:

- Person 1 → 1
- Person 2 → 4
- Person 3 → 9

Total strength required:

```text
1 + 4 + 9 = 14
```

Hence all first three people can be defeated.

---

### Example 2

**Input**
```text
p = 10
```

**Output**
```text
2
```

**Explanation**

After defeating:

- Person 1 → remaining strength = 9
- Person 2 → remaining strength = 5

Next person requires strength `9`, so no further defeats are possible.

---

## Constraints

- `1 ≤ p ≤ 3 × 10^8`

---

# Approach 1: Binary Search (Optimal)

### Idea

Suppose we defeat the first **k** people.

The total strength required is

```text
1² + 2² + 3² + ... + k²
```

Using the formula:

```text
k(k + 1)(2k + 1) / 6
```

We need the largest `k` such that

```text
k(k + 1)(2k + 1) / 6 ≤ p
```

Since this expression increases monotonically with `k`, we can use **Binary Search**.

---

## Algorithm

1. Binary search on the answer.
2. Compute the sum of squares till `mid`.
3. If the sum is within `p`, store the answer and search right.
4. Otherwise search left.

---

## Code

```java
int maxPeopleDefeated(int p) {
    int ans = 0;
    long l = 1;
    long r = 10000;

    while (l <= r) {

        long mid = l + (r - l) / 2;
        long sum = (mid * (mid + 1) * (2 * mid + 1)) / 6;

        if (sum <= p) {
            ans = (int) mid;
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }

    return ans;
}
```

---

## Dry Run

For

```text
p = 14
```

Binary search eventually checks

| mid | Sum of Squares | Valid |
|-----|----------------|-------|
| 2 | 5 | ✔ |
| 3 | 14 | ✔ |
| 4 | 30 | ✘ |

Largest valid answer = **3**

---

## Complexity Analysis

- **Time Complexity:** `O(log N)`
- **Space Complexity:** `O(1)`

---

# Approach 2: Linear Simulation

### Idea

Simply defeat people one by one.

For every `i`

- strength needed = `i²`
- if enough power remains, defeat them.
- otherwise stop.

---

## Code

```java
int maxPeopleDefeated1(int p) {

    int count = 0;

    for (int i = 1; i < 100000; i++) {

        long cur = (long) i * i;

        if (p >= cur) {
            p -= (int) cur;
            count++;
        } else {
            break;
        }
    }

    return count;
}
```

---

## Complexity Analysis

- **Time Complexity:** `O(√p)`
- **Space Complexity:** `O(1)`

---

# Key Formula

Sum of first `n` squares:

```text
1² + 2² + 3² + ... + n²
=
n(n + 1)(2n + 1) / 6
```

This allows binary searching directly on the answer.

---

# Summary

| Approach | Time | Space |
|----------|------|-------|
| Binary Search | **O(log N)** | O(1) |
| Linear Simulation | O(√p) | O(1) |

**Recommended:** Binary Search using the sum of squares formula.