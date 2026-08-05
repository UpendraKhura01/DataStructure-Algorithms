
# 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum

---

# Problem Statement

You are given a `0-indexed` integer array `nums`.

A prefix `nums[0..i]` is called **sequential** if every element after the first is exactly `1` greater than the previous element.

That means:

```text
nums[j] = nums[j - 1] + 1
```

for every valid `j`.

The prefix containing only `nums[0]` is always sequential.

Your task is to:

1. Find the **longest sequential prefix**.
2. Calculate its sum.
3. Find the smallest integer that:
    - is greater than or equal to this sum, and
    - does not exist in `nums`.

---

# Goal

Find the smallest missing integer `x` such that:

```text
x >= sum(longest sequential prefix)
```

and:

```text
x is not present in nums
```

---

# Intuition

The problem has two independent parts.

### Part 1: Find the Sequential Prefix

Starting from the first element, continue while:

```text
nums[i] = nums[i - 1] + 1
```

For example:

```text
nums = [1, 2, 3, 2, 5]
```

The prefix:

```text
[1, 2, 3]
```

is sequential because:

```text
2 = 1 + 1
3 = 2 + 1
```

But:

```text
2 != 3 + 1
```

so the sequential prefix stops there.

Its sum is:

```text
1 + 2 + 3 = 6
```

---

### Part 2: Find the Missing Integer

Now start from:

```text
sum = 6
```

If `6` already exists in the array, try:

```text
7
```

If `7` also exists, try:

```text
8
```

Continue until finding a number that is not present.

A `HashSet` gives efficient membership checking.

---

# Key Idea

Use a `HashSet` to store all elements of the array.

Then:

```text
sum = nums[0]
```

Traverse the array from index `1`.

If:

```text
nums[i] == nums[i - 1] + 1
```

add it to the prefix sum.

Otherwise, stop.

After finding the sum:

```text
while (set.contains(sum))
    sum++;
```

The first value not present in the set is the answer.

---

# Thought Process

### Step 1: Store All Numbers

Create:

```java
HashSet<Integer>
```

and insert every element of `nums`.

This allows us to check whether a number exists in approximately:

```text
O(1)
```

time.

---

### Step 2: Initialize Prefix Sum

The first element always forms a valid sequential prefix.

So:

```text
sum = nums[0]
```

---

### Step 3: Find the Longest Sequential Prefix

Start from index `1`.

For each element:

```text
nums[i]
```

check:

```text
nums[i] == nums[i - 1] + 1
```

If true:

```text
sum += nums[i]
```

Otherwise:

```text
break
```

---

### Step 4: Find the Smallest Missing Number

Start checking from:

```text
sum
```

If the number exists:

```text
sum++
```

Continue until:

```text
set.contains(sum) == false
```

That value is the answer.

---

# Code (Functions Only)

```java
int missingInteger(int[] nums) {

    int n = nums.length;

    HashSet<Integer> st = new HashSet<>();

    for (int i : nums) {
        st.add(i);
    }

    int sum = nums[0];

    for (int i = 1; i < n; i++) {

        if (nums[i] != nums[i - 1] + 1) {
            break;
        }

        sum += nums[i];
    }

    while (st.contains(sum)) {
        sum++;
    }

    return sum;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
nums = [3,4,5,1,12,14,13]
```

---

### Step 1: Build HashSet

```text
Set = {1, 3, 4, 5, 12, 13, 14}
```

---

### Step 2: Find Sequential Prefix

Start:

```text
sum = 3
```

---

### Check `4`

```text
4 == 3 + 1
```

Yes.

```text
sum = 3 + 4
    = 7
```

---

### Check `5`

```text
5 == 4 + 1
```

Yes.

```text
sum = 7 + 5
    = 12
```

---

### Check `1`

```text
1 == 5 + 1
```

False.

Therefore, the longest sequential prefix is:

```text
[3,4,5]
```

and:

```text
sum = 12
```

---

### Step 3: Find Missing Integer

Check:

```text
12
```

`12` exists.

Move to:

```text
13
```

`13` exists.

Move to:

```text
14
```

`14` exists.

Move to:

```text
15
```

`15` does not exist.

Therefore:

```text
Answer = 15
```

---

# Logic Flow

```text
Start

↓

Create HashSet containing all nums

↓

sum = nums[0]

↓

Traverse from index 1

↓

Is nums[i] == nums[i-1] + 1?

        |
        +---- Yes ----> Add nums[i] to sum
        |
        +---- No -----> Stop prefix search

↓

Start from sum

↓

Does HashSet contain sum?

        |
        +---- Yes ----> sum++
        |                 |
        |                 └── Repeat
        |
        +---- No ------> Return sum

↓

End
```

---

# Complexity

### Time Complexity

Building the HashSet:

```text
O(N)
```

Finding the sequential prefix:

```text
O(N)
```

Finding the missing integer:

```text
O(N)
```

in the worst case.

Therefore:

```text
Overall Time = O(N)
```

---

### Space Complexity

The HashSet stores all elements:

```text
O(N)
```

Therefore:

```text
Overall Space = O(N)
```

---

# Key Takeaways

- A sequential prefix must increase by exactly `1` at every step.
- The first element is always a valid sequential prefix.
- Stop immediately when the sequential condition fails.
- Use a `HashSet` for fast existence checking.
- Start searching for the answer from the prefix sum.
- Keep increasing the candidate while it exists in the array.
- The first absent value is the required answer.

---

# Most Important Insight

The problem becomes simple when separated into two stages:

```text
Longest sequential prefix
          ↓
       Its sum
          ↓
Smallest missing value >= sum
```

The sequential prefix is found using the condition:

```text
nums[i] == nums[i - 1] + 1
```

Then the `HashSet` allows us to efficiently skip all values starting from the prefix sum that already exist in the array.

---

# Summary

The solution first stores every array value in a `HashSet`. It then scans from the beginning to determine the longest sequential prefix and calculates its sum. Once the sum is known, it repeatedly checks whether that value exists in the set. If it does, the candidate is incremented until a missing value is found.

The final algorithm runs in:

```text
Time  : O(N)
Space : O(N)
```

and directly follows the two requirements of the problem: finding the sequential prefix and then finding the smallest missing integer starting from its sum.
