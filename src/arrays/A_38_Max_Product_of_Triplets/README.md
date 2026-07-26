
# 628. Maximum Product of Three Numbers (Maximum Product of Triplets)

---

# Problem Statement

Given an integer array `nums`, return the **maximum possible product** that can be obtained by multiplying **any three numbers** from the array.

The array may contain:

- Positive numbers
- Negative numbers
- Zero

Your task is to determine the maximum product efficiently.

### Example

```text
Input : [1,2,3]
Output: 6

Input : [1,2,3,4]
Output: 24

Input : [-10,-10,5,2]
Output: 500
```

---

# Goal

Find the maximum product of any three numbers in the array.

There are only two possible candidates:

1. Product of the **three largest numbers**
2. Product of the **two smallest (most negative) numbers** and the **largest number**

Return whichever is larger.

---

# Intuition

At first glance, it feels obvious to multiply the three largest numbers.

However, negative numbers change everything.

Consider:

```text
[-10, -10, 5, 2]
```

Three largest numbers:

```text
5 × 2 × (-10) = -100
```

But,

```text
(-10) × (-10) × 5 = 500
```

Since:

```text
Negative × Negative = Positive
```

Two very small negative numbers can produce a huge positive product.

Therefore, we only need to compare these two possibilities.

---

# Key Idea

Maintain during one traversal:

- Largest number (`m1`)
- Second largest (`m2`)
- Third largest (`m3`)
- Smallest number (`min1`)
- Second smallest (`min2`)

Finally compute:

```text
Candidate 1 = m1 × m2 × m3

Candidate 2 = min1 × min2 × m1
```

Return:

```text
max(Candidate1, Candidate2)
```

This avoids sorting and gives an optimal linear-time solution.

---

# Thought Process

### Step 1

Track the three largest values.

Whenever a larger element is found, shift the previous maximums accordingly.

---

### Step 2

Track the two smallest values.

These could be large negative numbers that create the maximum product.

---

### Step 3

After processing every element:

```text
Largest Product
=
largest × secondLargest × thirdLargest
```

and

```text
Negative Product
=
smallest × secondSmallest × largest
```

---

### Step 4

Return the larger product.

This works because every maximum-product triplet must belong to one of these two cases.

---

# Code (Functions Only)

```java
int maximumProduct(int[] nums) {

    int m1 = Integer.MIN_VALUE;
    int m2 = Integer.MIN_VALUE;
    int m3 = Integer.MIN_VALUE;

    int min1 = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;

    for (int num : nums) {

        if (num >= m1) {
            m3 = m2;
            m2 = m1;
            m1 = num;
        }
        else if (num >= m2) {
            m3 = m2;
            m2 = num;
        }
        else if (num > m3) {
            m3 = num;
        }

        if (num <= min1) {
            min2 = min1;
            min1 = num;
        }
        else if (num < min2) {
            min2 = num;
        }
    }

    return Math.max(m1 * m2 * m3, min1 * min2 * m1);
}
```

### Sorting Approach

```java
int Sorting(int[] nums) {

    int n = nums.length;

    Arrays.sort(nums);

    int left = nums[0] * nums[1] * nums[n - 1];
    int right = nums[n - 1] * nums[n - 2] * nums[n - 3];

    return Math.max(left, right);
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
nums = [-10, -10, 5, 2]
```

### Initial Values

```text
m1 = -∞
m2 = -∞
m3 = -∞

min1 = +∞
min2 = +∞
```

---

### Process -10

Largest:

```text
m1 = -10
m2 = -∞
m3 = -∞
```

Smallest:

```text
min1 = -10
min2 = +∞
```

---

### Process -10

Largest:

```text
m1 = -10
m2 = -10
m3 = -∞
```

Smallest:

```text
min1 = -10
min2 = -10
```

---

### Process 5

Largest:

```text
m1 = 5
m2 = -10
m3 = -10
```

Smallest:

```text
min1 = -10
min2 = -10
```

---

### Process 2

Largest:

```text
m1 = 5
m2 = 2
m3 = -10
```

Smallest:

```text
min1 = -10
min2 = -10
```

---

### Candidate 1

```text
5 × 2 × (-10)

= -100
```

### Candidate 2

```text
(-10) × (-10) × 5

= 500
```

### Answer

```text
Maximum Product = 500
```

---

# Logic Flow

```text
Start

↓

Initialize
largest1
largest2
largest3

smallest1
smallest2

↓

Traverse array once

↓

Update largest three values

↓

Update smallest two values

↓

Compute

largest1 × largest2 × largest3

↓

Compute

smallest1 × smallest2 × largest1

↓

Return maximum of both

↓

End
```

---

# Complexity

## Optimal Approach

### Time Complexity

```text
O(N)
```

Single traversal of the array.

### Space Complexity

```text
O(1)
```

Only five variables are maintained.

---

## Sorting Approach

### Time Complexity

```text
O(N log N)
```

Sorting dominates the runtime.

### Space Complexity

```text
O(1)
```

(ignoring the sorting implementation's internal stack usage)

---

# Key Takeaways

- Maximum product is **not always** formed by the three largest numbers.
- Two negative numbers can produce a larger positive product.
- Only two candidate products need to be compared.
- Tracking five values is enough to solve the problem.
- The optimal solution avoids sorting and runs in linear time.

---

# Most Important Insight

The maximum product of three numbers must be one of these:

```text
largest × secondLargest × thirdLargest
```

or

```text
smallest × secondSmallest × largest
```

No other combination can produce a larger product.

---

# Summary

This problem demonstrates how negative numbers influence optimization problems. Instead of sorting the array, we maintain the three largest and two smallest values during a single traversal. At the end, we compare the product of the three largest numbers with the product of the two smallest numbers and the largest number. This elegant observation reduces the time complexity from **O(N log N)** to **O(N)** while using only **constant extra space**, making it the optimal solution.
