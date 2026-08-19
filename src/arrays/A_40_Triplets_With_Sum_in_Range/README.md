
# Triplets with Sum in Range

---

# Problem Statement

Given an integer array `arr[]` and a range `[l, r]`, count the number of triplets whose sum lies within the given range.

A triplet consists of **three different indices**:

```text
i < j < k
```

and it is valid when:

```text
l <= arr[i] + arr[j] + arr[k] <= r
```

Return the total number of valid triplets.

### Example 1

```text
Input:
arr = [8, 3, 5, 2]
l = 7
r = 11

Output:
1
```

Explanation:

After sorting:

```text
[2, 3, 5, 8]
```

The valid triplet is:

```text
[2, 3, 5]

sum = 10
```

Since:

```text
7 <= 10 <= 11
```

there is exactly one valid triplet.

---

### Example 2

```text
Input:
arr = [5, 1, 4, 3, 2]
l = 2
r = 7

Output:
2
```

The valid triplets are:

```text
[1, 4, 2] -> sum = 7

[1, 3, 2] -> sum = 6
```

Therefore:

```text
Answer = 2
```

---

# Goal

Count all triplets whose sum belongs to the inclusive range:

```text
[l, r]
```

efficiently.

The main challenge is to avoid checking every possible triplet using three nested loops.

---

# Intuition

A direct brute-force solution would check:

```text
i
j
k
```

using three loops.

That takes:

```text
O(N^3)
```

which is unnecessarily expensive.

Instead, first sort the array.

Then we can solve a simpler problem:

> How many triplets have a sum `<= X`?

Once we can calculate that, the number of triplets whose sum lies in `[l, r]` is:

```text
count(sum <= r) - count(sum <= l - 1)
```

This is the key transformation.

---

# Key Idea

Define:

```text
solve(X)
```

as:

```text
Number of triplets whose sum is <= X
```

Then:

```text
Answer = solve(r) - solve(l - 1)
```

Why?

Suppose:

```text
sum < l
```

It is counted by:

```text
solve(l - 1)
```

and also by:

```text
solve(r)
```

so it gets cancelled.

A triplet with:

```text
l <= sum <= r
```

is counted only by:

```text
solve(r)
```

Therefore, the difference gives exactly the desired answer.

---

# Thought Process

### Step 1: Sort the Array

```text
Arrays.sort(arr)
```

After sorting:

```text
arr[i] <= arr[j] <= arr[k]
```

This allows the two-pointer technique.

---

### Step 2: Fix the First Element

For every:

```text
i
```

consider `arr[i]` as the first element of the triplet.

Then we need to find pairs:

```text
arr[left] + arr[right]
```

such that:

```text
arr[i] + arr[left] + arr[right] <= X
```

---

### Step 3: Use Two Pointers

Initialize:

```text
left = i + 1
right = n - 1
```

Now calculate:

```text
sum = arr[i] + arr[left] + arr[right]
```

---

### Step 4: If Sum Is Within the Limit

If:

```text
sum <= X
```

because the array is sorted, every element between `left` and `right` is also small enough with `arr[i]`.

Therefore, all these pairs are valid:

```text
(left, left + 1)
(left, left + 2)
...
(left, right)
```

Number of such pairs:

```text
right - left
```

So:

```text
count += right - left
```

Then move:

```text
left++
```

---

### Step 5: If Sum Is Too Large

If:

```text
sum > X
```

the current `right` value is too large.

Move:

```text
right--
```

to reduce the sum.

---

### Step 6: Convert Range Query

Finally:

```text
solve(r) - solve(l - 1)
```

gives the number of triplets whose sum is inside `[l, r]`.

---

# Code (Functions Only)

```java
int[] arr;

public int countTriplets(int[] arr, int l, int r) {

    Arrays.sort(arr);

    this.arr = arr;

    return solve(r) - solve(l - 1);
}

private int solve(int range) {

    int n = arr.length;

    int count = 0;

    for (int i = 0; i < n - 2; i++) {

        int left = i + 1;
        int right = n - 1;

        while (left < right) {

            int sum = arr[left] + arr[right] + arr[i];

            if (sum <= range) {

                count += right - left;

                left++;

            } else {

                right--;
            }
        }
    }

    return count;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
arr = [8, 3, 5, 2]

l = 7
r = 11
```

---

### Step 1: Sort

```text
[2, 3, 5, 8]
```

We need:

```text
solve(11) - solve(6)
```

because:

```text
l - 1 = 6
```

---

## Calculate solve(11)

### Fix `i = 0`

```text
arr[i] = 2
```

Initialize:

```text
left = 1
right = 3
```

So:

```text
arr[left] = 3
arr[right] = 8
```

Sum:

```text
2 + 3 + 8 = 13
```

Since:

```text
13 > 11
```

move:

```text
right--
```

Now:

```text
right = 2
```

Sum:

```text
2 + 3 + 5 = 10
```

Since:

```text
10 <= 11
```

all pairs from `left` to `right` are valid.

Number of valid pairs:

```text
right - left
= 2 - 1
= 1
```

So:

```text
count = 1
```

Move:

```text
left++
```

Now:

```text
left = 2
right = 2
```

Stop.

---

### Remaining `i`

No additional valid triplets are found.

Therefore:

```text
solve(11) = 1
```

---

## Calculate solve(6)

The smallest possible triplet is:

```text
2 + 3 + 5 = 10
```

which is already greater than `6`.

Therefore:

```text
solve(6) = 0
```

---

## Final Calculation

```text
solve(11) - solve(6)

= 1 - 0

= 1
```

Final answer:

```text
1
```

---

# Logic Flow

```text
Start

↓

Sort array

↓

Calculate solve(r)

↓

Calculate solve(l - 1)

↓

Answer = solve(r) - solve(l - 1)

↓

Inside solve(X):

↓

Fix i

↓

left = i + 1
right = n - 1

↓

Calculate sum

↓

Is sum <= X?

       Yes
        |
        v
All pairs between left and right
are valid

        |
        v
count += right - left

        |
        v
left++

       No
        |
        v
right--

↓

Repeat while left < right

↓

Return count

↓

End
```

---

# Complexity

### Sorting

```text
O(N log N)
```

### Counting Triplets

For each fixed `i`, the two pointers move only toward each other:

```text
O(N)
```

for each `i`.

There are `N` possible values of `i`.

Therefore:

```text
O(N^2)
```

---

### Overall Time Complexity

```text
O(N log N) + O(N^2)

= O(N^2)
```

---

### Space Complexity

The algorithm uses only a constant amount of extra working space apart from the sorting implementation:

```text
O(1)
```

---

# Key Takeaways

- Sort the array before using the two-pointer technique.
- Convert the range condition:
  ```text
  l <= sum <= r
  ```
  into two prefix-count problems.
- Use:
  ```text
  solve(r) - solve(l - 1)
  ```
- When:
  ```text
  sum <= range
  ```
  every pair between `left` and `right` is valid.
- The number of valid pairs is:
  ```text
  right - left
  ```
- This avoids an `O(N^3)` brute-force approach.
- The final complexity is:
  ```text
  O(N^2)
  ```

---

# Most Important Insight

The most important trick is:

```text
Count(sum <= r) - Count(sum <= l - 1)
```

Instead of trying to directly find triplets inside a range, solve the easier problem of counting triplets whose sum is at most a given value.

After sorting, the two-pointer technique lets us count many valid triplets at once.

When:

```text
arr[i] + arr[left] + arr[right] <= X
```

all elements between `left` and `right` also form valid triplets with `arr[i]` and `arr[left]`.

Therefore:

```text
count += right - left
```

is the crucial optimization that reduces the solution from `O(N^3)` to `O(N^2)`.

---

# Summary

The solution first sorts the array and defines a helper function `solve(X)` that counts the number of triplets with sum at most `X`. For every fixed first element, two pointers are used to efficiently count valid pairs. If the current sum is within the limit, all pairs between the two pointers are valid, allowing multiple triplets to be counted in `O(1)` time.

Finally, the required range count is obtained using:

```text
solve(r) - solve(l - 1)
```

This gives an efficient:

```text
Time  : O(N^2)
Space : O(1)
```

solution.
