
# Maximum Area Between Bars

---

# Problem Statement

Given an integer array `height[]`, where:

```text
height[i] = height of the ith bar
```

we need to select **two bars** such that the rectangular area formed between them is maximum.

The width is based on the original positions of the selected bars.

The area is calculated as:

```text
Area = minimum height of two bars × number of bars between them
```

For two positions `l` and `r`:

```text
Area = min(height[l], height[r]) × (r - l - 1)
```

---

### Example 1

```text
Input:
height = [2, 5, 4, 3, 7]

Output:
10
```

Choose bars:

```text
height[1] = 5
height[4] = 7
```

There are:

```text
4 - 1 - 1 = 2
```

bars between them.

The limiting height is:

```text
min(5, 7) = 5
```

Therefore:

```text
Area = 5 × 2
     = 10
```

---

### Example 2

```text
Input:
height = [1, 3, 4]

Output:
1
```

Choose:

```text
height[0] = 1
height[2] = 4
```

There is:

```text
2 - 0 - 1 = 1
```

bar between them.

Therefore:

```text
Area = min(1, 4) × 1
     = 1
```

---

# Goal

Find two bars that maximize:

```text
min(height[l], height[r]) × (r - l - 1)
```

The solution should efficiently handle:

```text
height.length <= 10^5
```

An `O(N²)` brute-force solution would be too slow.

The goal is to solve it using the **Two Pointer Technique** in:

```text
O(N)
```

---

# Intuition

Start with the two bars that have the **maximum possible distance**:

```text
l = 0
r = n - 1
```

For these two bars:

```text
width = r - l - 1
```

As we move either pointer toward the center, the width always decreases.

Therefore, we need to make the best possible choice when reducing the width.

The area depends on two things:

```text
Area = smaller height × width
```

Suppose:

```text
height[l] < height[r]
```

Then the left bar is the limiting factor.

Moving `r` inward would:

```text
decrease width
```

while the limiting height would still be:

```text
height[l]
```

or potentially become even smaller.

So there is no reason to keep the smaller left bar.

Instead:

```text
l++
```

Similarly, if:

```text
height[r] <= height[l]
```

the right bar is limiting the area, so we move:

```text
r--
```

This is the core idea behind the two-pointer solution.

---

# Key Idea

Maintain two pointers:

```text
l = 0
r = n - 1
```

At every step:

### 1. Calculate the current area

```text
width = r - l - 1
height = min(height[l], height[r])
area = height × width
```

Update:

```text
ans = max(ans, area)
```

### 2. Move the pointer with the smaller height

If:

```text
height[l] < height[r]
```

then:

```text
l++
```

Otherwise:

```text
r--
```

Continue until:

```text
l >= r
```

---

# Thought Process

### Step 1: Why Start From Both Ends?

Initially:

```text
l = 0
r = n - 1
```

This gives the maximum possible width.

Any other pair of bars will have a smaller width.

So we start with the widest possible container.

---

### Step 2: Calculate the Area

For:

```text
height = [2,5,4,3,7]
```

initially:

```text
l = 0
r = 4
```

Heights:

```text
2 and 7
```

Width:

```text
4 - 0 - 1 = 3
```

Limiting height:

```text
min(2,7) = 2
```

Area:

```text
2 × 3 = 6
```

---

### Step 3: Decide Which Pointer to Move

Since:

```text
2 < 7
```

the left bar is the limiting bar.

Moving the right pointer would reduce the width without removing the limiting left bar.

Therefore:

```text
l++
```

Now:

```text
l = 1
r = 4
```

Heights:

```text
5 and 7
```

Width:

```text
4 - 1 - 1 = 2
```

Area:

```text
min(5,7) × 2
= 5 × 2
= 10
```

This becomes the maximum.

---

### Why Not Move the Taller Bar?

Suppose:

```text
height[l] < height[r]
```

and we move:

```text
r--
```

The width decreases:

```text
r - l - 1
```

but the left height remains unchanged.

The new right height may or may not be larger than the left height.

Therefore, the maximum possible limiting height cannot improve by keeping the smaller left bar fixed.

So the only useful move is:

```text
l++
```

The same argument applies symmetrically to the right side.

---

# Code (Functions Only)

```java
int maxArea(List<Integer> height) {

    int ans = 0;

    int l = 0;
    int r = height.size() - 1;

    while (l < r) {

        int dist = r - l - 1;

        int cur_area =
                Math.min(height.get(l), height.get(r)) * dist;

        ans = Math.max(ans, cur_area);

        if (height.get(l) < height.get(r)) {
            l++;
        } else {
            r--;
        }
    }

    return ans;
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
height = [2,5,4,3,7]
```

Index:

```text
Index:   0  1  2  3  4
Height:  2  5  4  3  7
```

---

### Step 1

```text
l = 0
r = 4
```

Heights:

```text
2, 7
```

Distance:

```text
r - l - 1
= 4 - 0 - 1
= 3
```

Minimum height:

```text
min(2,7) = 2
```

Area:

```text
2 × 3 = 6
```

Update:

```text
ans = 6
```

Since:

```text
2 < 7
```

move:

```text
l++
```

---

### Step 2

```text
l = 1
r = 4
```

Heights:

```text
5, 7
```

Distance:

```text
4 - 1 - 1 = 2
```

Minimum height:

```text
5
```

Area:

```text
5 × 2 = 10
```

Update:

```text
ans = 10
```

Since:

```text
5 < 7
```

move:

```text
l++
```

---

### Step 3

```text
l = 2
r = 4
```

Heights:

```text
4, 7
```

Distance:

```text
4 - 2 - 1 = 1
```

Area:

```text
min(4,7) × 1
= 4
```

Maximum remains:

```text
ans = 10
```

Since:

```text
4 < 7
```

move:

```text
l++
```

---

### Step 4

```text
l = 3
r = 4
```

Heights:

```text
3, 7
```

Distance:

```text
4 - 3 - 1 = 0
```

Area:

```text
3 × 0 = 0
```

Maximum remains:

```text
10
```

Since:

```text
3 < 7
```

move:

```text
l++
```

Now:

```text
l = 4
r = 4
```

Loop ends because:

```text
l < r
```

is false.

Final answer:

```text
10
```

---

### Dry Run Table

```text
l   r   Left   Right   Width   Min Height   Area   Max
---------------------------------------------------------
0   4     2      7       3         2          6      6
1   4     5      7       2         5         10     10
2   4     4      7       1         4          4     10
3   4     3      7       0         3          0     10
```

Therefore:

```text
Answer = 10
```

---

# Logic Flow

```text
Start

↓

Set:
l = 0
r = n - 1
ans = 0

↓

While l < r

↓

Calculate width:

r - l - 1

↓

Calculate limiting height:

min(height[l], height[r])

↓

Calculate:

currentArea =
min(height[l], height[r])
× (r - l - 1)

↓

Update:

ans = max(ans, currentArea)

↓

Is height[l] < height[r]?

       Yes
        |
        v
      l++

       No
        |
        v
      r--

↓

Repeat

↓

l >= r

↓

Return ans

↓

End
```

---

# Complexity

Each pointer only moves in one direction.

The left pointer moves from:

```text
0 → n - 1
```

and the right pointer moves from:

```text
n - 1 → 0
```

Neither pointer ever moves backward.

Therefore, the total number of pointer movements is at most `N`.

Thus:

```text
Time Complexity = O(N)
```

The algorithm uses only a few variables:

```text
l
r
ans
dist
cur_area
```

Therefore:

```text
Space Complexity = O(1)
```

---

# Key Takeaways

- This is a classic **Two Pointer** problem.
- Start with the two endpoints to obtain the maximum possible width.
- The area is:
  ```text
  min(height[l], height[r]) × (r - l - 1)
  ```
- The smaller height determines the area.
- Always move the pointer corresponding to the smaller height.
- Moving the taller pointer cannot improve the limiting height while also reducing the width.
- Continue until the two pointers meet.
- The brute-force approach takes:
  ```text
  O(N²)
  ```
- The two-pointer solution reduces it to:
  ```text
  O(N)
  ```
- Extra space is:
  ```text
  O(1)
  ```

---

# Most Important Insight

The most important observation is:

> **The smaller of the two selected bars is always the limiting height.**

For two pointers `l` and `r`:

```text
Area = min(height[l], height[r]) × (r - l - 1)
```

Suppose:

```text
height[l] < height[r]
```

Then:

```text
height[l]
```

is limiting the current area.

If we move `r`:

```text
width decreases
```

while the limiting left height stays the same.

So that move cannot produce a better area than a move that discards the smaller left bar.

Therefore:

```text
height[l] < height[r]
        ↓
      l++
```

Similarly:

```text
height[r] <= height[l]
        ↓
      r--
```

This allows us to eliminate many impossible pairs without explicitly checking them.

The fundamental pattern is:

```text
Start with maximum width
        ↓
Calculate current area
        ↓
Discard the smaller boundary
        ↓
Try a smaller width with a chance of a larger limiting height
        ↓
Repeat
```

That is why the algorithm achieves:

```text
O(N)
```

instead of:

```text
O(N²)
```

---

# Summary

The problem asks us to find the maximum rectangular area formed using two bars.

For two selected positions:

```text
l < r
```

the area is:

```text
Area = min(height[l], height[r]) × (r - l - 1)
```

The optimal approach uses two pointers:

```text
l = 0
r = n - 1
```

At every step:

```text
1. Calculate the current area.
2. Update the maximum.
3. Move the pointer with the smaller height.
```

The reason for moving the smaller height is that the smaller bar limits the current area. Keeping it while reducing the width cannot help.

Final complexity:

```text
Time  : O(N)
Space : O(1)
```

The core pattern to remember is:

```text
Two boundaries
     ↓
Smaller height limits area
     ↓
Discard smaller boundary
     ↓
Reduce width
     ↓
Search for a taller limiting boundary
```

This is a fundamental **Two Pointer Technique** pattern and is useful whenever an optimal answer depends on two endpoints and one endpoint limits the result.
