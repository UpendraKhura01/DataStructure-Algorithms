# 🔄 Removing Minimum and Maximum From Array

## 📌 Problem Statement

Given a 0-indexed array `nums` containing distinct integers.

The array contains:

- The element with the lowest value → **minimum**
- The element with the highest value → **maximum**

The goal is to remove both the minimum and maximum elements.

A deletion can only be performed from:

    1. The front of the array
    2. The back of the array

Return the **minimum number of deletions** required to remove both elements.

### Example 1

    Input:
    nums = [2,10,7,5,4,1,8,6]

    Maximum = 10
    Maximum index = 1

    Minimum = 1
    Minimum index = 5

    Output:
    5

One optimal way is:

    2 deletions from the front
    3 deletions from the back

    Total = 2 + 3 = 5

---

## 🎯 Goal

Find the indices of the minimum and maximum elements and determine the cheapest way to remove both.

There are only three possible strategies:

    1. Remove both from the left.
    2. Remove both from the right.
    3. Remove one from the left and the other from the right.

Calculate the cost of all three strategies and return the minimum.

---

## 💡 Intuition

Suppose the minimum and maximum are located at:

    index = 1
    index = 5

Visually:

    Index:   0   1   2   3   4   5   6   7
             |   |               |       |
    Array:  [2, 10, 7, 5, 4, 1, 8, 6]
                 ↑               ↑
                max             min

The actual values do not matter anymore.

What matters is:

    Where are the two target elements?

Once we know their positions, we only need to compare three possible deletion strategies.

---

## 🔥 Key Idea

Let:

    first  = smaller index of minimum and maximum
    second = larger index of minimum and maximum

For example:

    min index = 5
    max index = 1

Then:

    first  = 1
    second = 5

Now calculate three possibilities.

### 1. Remove Both From the Left

To reach the element at `second`, we must remove everything from index `0` through `second`.

Therefore:

    left = second + 1

---

### 2. Remove Both From the Right

To reach the element at `first` from the right, we must remove elements from `first` through `n - 1`.

Therefore:

    right = n - first

---

### 3. Remove One From Each Side

Remove the element at `first` from the left:

    first + 1

Remove the element at `second` from the right:

    n - second

Therefore:

    opp = first + 1 + n - second

---

### Final Formula

    answer = min(
        second + 1,
        n - first,
        first + 1 + n - second
    )

The entire problem reduces to finding two indices and evaluating these three costs.

---

## 🧠 Thought Process

### Step 1: Find the Minimum and Maximum

Scan the array once.

Maintain:

    max = largest value seen so far
    min = smallest value seen so far

Also store their indices:

    idx1 = index of maximum
    idx2 = index of minimum

When:

    nums[i] > max

update:

    max = nums[i]
    idx1 = i

When:

    nums[i] < min

update:

    min = nums[i]
    idx2 = i

---

### Step 2: Arrange the Indices

The maximum may appear before or after the minimum.

We do not want separate cases.

So:

    first = Math.min(idx1, idx2)
    second = Math.max(idx1, idx2)

Now we always have:

    first <= second

This makes the remaining calculations simple.

---

### Step 3: Calculate the Three Costs

#### Both From Left

    left = second + 1

#### Both From Right

    right = n - first

#### One From Each Side

    opp = first + 1 + n - second

---

### Step 4: Take the Minimum

    answer = min(left, right, opp)

There is no need to actually delete elements.

We only calculate how many elements would have to be removed.

---

## 💻 Code

```java
int minimumDeletions(int[] nums) {
    int n = nums.length;

    int idx1 = -1;
    int idx2 = -2;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {

        int num = nums[i];

        if (num > max) {
            max = num;
            idx1 = i;
        }

        if (num < min) {
            min = num;
            idx2 = i;
        }
    }

    int first = Math.min(idx1, idx2);
    int second = Math.max(idx1, idx2);

    int left = second + 1;
    int right = n - first;

    int opp = first + 1 + n - second;

    return Math.min(left, Math.min(right, opp));
}
```
🧪 Dry Run
Input
nums = [2,10,7,5,4,1,8,6]

Array length:

n = 8
Step 1: Find Maximum and Minimum

Initially:

max = Integer.MIN_VALUE
min = Integer.MAX_VALUE

idx1 = -1
idx2 = -2

Now scan the array.

i	nums[i]	max	idx1	min	idx2
0	2	2	0	2	0
1	10	10	1	2	0
2	7	10	1	2	0
3	5	10	1	2	0
4	4	10	1	2	0
5	1	10	1	1	5
6	8	10	1	1	5
7	6	10	1	1	5

At the end:

Maximum = 10
Maximum index = 1

Minimum = 1
Minimum index = 5

Therefore:

idx1 = 1
idx2 = 5
Step 2: Arrange the Indices
first = Math.min(1, 5)
      = 1

second = Math.max(1, 5)
       = 5

So:

first  = 1
second = 5

Array:

Index:   0   1   2   3   4   5   6   7
         |   |               |       |
Array:  [2, 10, 7, 5, 4, 1, 8, 6]
             ↑               ↑
            max             min
Step 3: Calculate left

Formula:

left = second + 1

Substitute:

left = 5 + 1
     = 6

This means removing:

[2,10,7,5,4,1]

from the front.

Cost:

6 deletions
Step 4: Calculate right

Formula:

right = n - first

Substitute:

right = 8 - 1
      = 7

This means removing elements from the right until index 1 is reached.

Cost:

7 deletions
Step 5: Calculate opp

Here we remove:

first element from the left
second element from the right

Formula:

opp = first + 1 + n - second

Substitute:

opp = 1 + 1 + 8 - 5
    = 2 + 3
    = 5

So:

2 deletions from the left
3 deletions from the right

Total:

2 + 3 = 5
Step 6: Choose Minimum

We have:

left  = 6
right = 7
opp   = 5

Therefore:

answer = min(6, 7, 5)
       = 5

Final answer:

5
🔁 Logic Flow
Start
  |
  v
Get array length n
  |
  v
Scan array
  |
  +-------------------------+
  |                         |
  v                         v
Find maximum             Find minimum
and its index            and its index
  |                         |
  +------------+------------+
               |
               v
      Arrange the indices
               |
               v
    first = smaller index
    second = larger index
               |
               v
      Calculate 3 costs
               |
    +----------+----------+
    |          |          |
    v          v          v
  left       right       opp
    |          |          |
    |          |          |
second+1     n-first    first+1
                       + n-second
    |          |          |
    +----------+----------+
               |
               v
         Take minimum
               |
               v
            Return
               |
               v
             End
📊 Complexity
Time Complexity
O(n)

There is one traversal of the array:

for(int i = 0; i < n; i++)

This takes O(n) time.

After the traversal, all remaining calculations are constant-time operations:

O(1)

Therefore:

Total Time = O(n)
Space Complexity
O(1)

Only a fixed number of variables are used:

n
idx1
idx2
max
min
first
second
left
right
opp

No extra array or data structure is created.

Therefore:

Space = O(1)
🎯 Key Takeaways
We do not need to physically remove elements.
We only need the indices of the minimum and maximum.
There are exactly three possible optimal strategies.

Sort the two relevant indices conceptually using:

first  = min(idx1, idx2)
second = max(idx1, idx2)

Calculate:

left  = second + 1
right = n - first
opp   = first + 1 + n - second

Return:

min(left, right, opp)
The array is scanned only once.
Time complexity is O(n).
Space complexity is O(1).
🔥 Most Important Insight

The key insight is:

We do NOT need to simulate deletion.

Suppose the two target elements are at:

first
second

Because deletion is allowed only from the two ends, every optimal solution must be one of these three patterns:

Pattern 1:
Remove both from the left.

Cost = second + 1


Pattern 2:
Remove both from the right.

Cost = n - first


Pattern 3:
Remove first from the left
and second from the right.

Cost = first + 1 + n - second

Therefore:

answer = min(
    second + 1,
    n - first,
    first + 1 + n - second
)

This is the heart of the solution.

The problem looks like an array-deletion problem, but it is actually an index-position optimization problem.

🏁 Summary

The solution follows four simple steps:

1. Find the minimum and maximum elements.
2. Store their indices.
3. Calculate the cost of the three possible deletion strategies.
4. Return the minimum cost.

For:

nums = [2,10,7,5,4,1,8,6]

We get:

Maximum index = 1
Minimum index = 5

Therefore:

first  = 1
second = 5

Calculate:

left  = second + 1
      = 6

right = n - first
      = 7

opp   = first + 1 + n - second
      = 5

Finally:

answer = min(6, 7, 5)
       = 5

So the minimum number of deletions is:

5

Efficient solution:

Time  : O(n)
Space : O(1)