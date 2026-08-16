
# Length of Longest Subarray With at Most K Frequency

---

# Problem Statement

Given an integer array `nums` and an integer `k`.

The frequency of an element is the number of times that element appears in the array.

A subarray is called **good** if every element appears at most `k` times inside that subarray.

The task is to return the length of the **longest good subarray**.

A subarray must be:

```text
Contiguous
+
Non-empty
```

---

### Example 1

```text
Input:
nums = [1,2,3,1,2,3,1,2]
k = 2

Output:
6
```

One longest valid subarray is:

```text
[1,2,3,1,2,3]
```

Frequencies:

```text
1 -> 2
2 -> 2
3 -> 2
```

Every frequency is at most `2`.

Therefore:

```text
Answer = 6
```

---

### Example 2

```text
Input:
nums = [1,2,1,2,1,2,1,2]
k = 1

Output:
2
```

A valid longest subarray is:

```text
[1,2]
```

Both elements appear exactly once.

Any subarray of length `3` contains either two `1`s or two `2`s, violating:

```text
frequency <= 1
```

Therefore:

```text
Answer = 2
```

---

### Example 3

```text
Input:
nums = [5,5,5,5,5,5,5]
k = 4

Output:
4
```

The longest valid subarray is:

```text
[5,5,5,5]
```

because:

```text
frequency(5) = 4
```

The next element would make the frequency:

```text
5 > 4
```

Therefore:

```text
Answer = 4
```

---

# Goal

Find the maximum length of a contiguous subarray such that:

```text
frequency of every element <= k
```

The important challenge is that the subarray must remain contiguous while maintaining the frequency constraint.

---

# Intuition

This is a classic **Sliding Window + HashMap** problem.

We maintain a window:

```text
[left ... right]
```

and keep track of the frequency of every element inside that window.

As `right` moves forward, we add new elements to the window.

The window is valid when:

```text
frequency of every element <= k
```

If the newly added element appears more than `k` times, the window becomes invalid.

At that point, we move `left` forward until the window becomes valid again.

So the basic strategy is:

```text
Expand window
     ↓
Check frequency
     ↓
If invalid
     ↓
Shrink from left
     ↓
Update maximum length
```

---

# Key Idea

Maintain:

```text
left = beginning of current window
right = end of current window
```

and a HashMap:

```text
mp[value] = frequency inside current window
```

For every `right`:

```text
mp[nums[right]]++
```

If:

```text
mp[nums[right]] > k
```

then the current window is invalid.

We repeatedly remove elements from the left:

```text
mp[nums[left]]--
left++
```

until:

```text
mp[nums[right]] <= k
```

Now the window is valid again.

Its length is:

```text
right - left + 1
```

Update:

```text
max = Math.max(max, right - left + 1)
```

---

# Thought Process

### Step 1: Start With an Empty Window

Initially:

```text
left = 0
right = 0
```

The HashMap is empty.

---

### Step 2: Expand the Window

Add:

```text
nums[right]
```

to the frequency map.

For example:

```text
nums[right] = 2
```

then:

```text
mp[2]++
```

---

### Step 3: Detect an Invalid Window

Suppose:

```text
k = 2
```

and the current window contains:

```text
[1,2,1,2,1]
```

Frequency:

```text
1 -> 3
2 -> 2
```

The frequency of `1` is:

```text
3 > 2
```

Therefore, the window is invalid.

---

### Step 4: Shrink From the Left

Remove elements from the left:

```text
mp[nums[left]]--
left++
```

Continue until the frequency constraint becomes valid.

For example:

```text
[1,2,1,2,1]
```

Remove the first `1`:

```text
[2,1,2,1]
```

Now:

```text
1 -> 2
2 -> 2
```

The window is valid.

---

### Step 5: Update the Answer

For every valid window:

```text
length = right - left + 1
```

Then:

```text
max = Math.max(max, length)
```

The largest valid window encountered is the final answer.

---

# Code (Functions Only)

```java
int maxSubarrayLength(int[] nums, int k) {

    int n = nums.length;

    HashMap<Integer, Integer> mp = new HashMap<>();

    int max = 1;
    int left = 0;
    int right = 0;

    while (right < n) {

        mp.put(
            nums[right],
            mp.getOrDefault(nums[right], 0) + 1
        );

        while (mp.get(nums[right]) > k) {

            mp.put(
                nums[left],
                mp.get(nums[left++]) - 1
            );
        }

        max = Math.max(
            max,
            right - left + 1
        );

        right++;
    }

    return max;
}
```

---

# Dry Run (Step-by-Step)

Consider:

```text
nums = [1,2,3,1,2,3,1,2]
k = 2
```

We want the longest subarray where every number occurs at most twice.

---

## Step 1

```text
left = 0
right = 0
```

Add:

```text
1
```

Window:

```text
[1]
```

Frequency:

```text
1 -> 1
```

Valid.

Length:

```text
1
```

Maximum:

```text
max = 1
```

---

## Step 2

Add:

```text
2
```

Window:

```text
[1,2]
```

Frequency:

```text
1 -> 1
2 -> 1
```

Valid.

Length:

```text
2
```

Maximum:

```text
max = 2
```

---

## Step 3

Add:

```text
3
```

Window:

```text
[1,2,3]
```

Frequency:

```text
1 -> 1
2 -> 1
3 -> 1
```

Valid.

Length:

```text
3
```

Maximum:

```text
max = 3
```

---

## Step 4

Add:

```text
1
```

Window:

```text
[1,2,3,1]
```

Frequency:

```text
1 -> 2
2 -> 1
3 -> 1
```

Valid.

Length:

```text
4
```

Maximum:

```text
max = 4
```

---

## Step 5

Add:

```text
2
```

Window:

```text
[1,2,3,1,2]
```

Frequency:

```text
1 -> 2
2 -> 2
3 -> 1
```

Valid.

Length:

```text
5
```

Maximum:

```text
max = 5
```

---

## Step 6

Add:

```text
3
```

Window:

```text
[1,2,3,1,2,3]
```

Frequency:

```text
1 -> 2
2 -> 2
3 -> 2
```

Valid.

Length:

```text
6
```

Maximum:

```text
max = 6
```

---

## Step 7

Add:

```text
1
```

Window becomes:

```text
[1,2,3,1,2,3,1]
```

Frequency:

```text
1 -> 3
2 -> 2
3 -> 2
```

Invalid because:

```text
1 -> 3 > k
```

---

### Shrink the Window

Remove the leftmost `1`.

```text
left = 1
```

Window:

```text
[2,3,1,2,3,1]
```

Frequencies:

```text
1 -> 2
2 -> 2
3 -> 2
```

Now valid.

Length:

```text
6
```

Maximum remains:

```text
6
```

---

## Step 8

Add:

```text
2
```

Current window:

```text
[2,3,1,2,3,1,2]
```

Frequency:

```text
1 -> 2
2 -> 3
3 -> 2
```

Invalid because:

```text
2 -> 3 > 2
```

Shrink from the left.

Remove first `2`:

```text
left = 2
```

Window:

```text
[3,1,2,3,1,2]
```

Frequency:

```text
1 -> 2
2 -> 2
3 -> 2
```

Valid.

Length:

```text
6
```

Final answer:

```text
6
```

---

### Dry Run Table

```text
right   Added   Window Length   Valid?   max
------------------------------------------------
0        1          1            Yes      1
1        2          2            Yes      2
2        3          3            Yes      3
3        1          4            Yes      4
4        2          5            Yes      5
5        3          6            Yes      6
6        1          7            No       6
          ↓
       shrink
          ↓
        length 6   Yes            6
7        2          7            No       6
          ↓
       shrink
          ↓
        length 6   Yes            6
```

Therefore:

```text
Answer = 6
```

---

# Logic Flow

```text
Start

↓

Initialize HashMap

↓

left = 0
right = 0

↓

Add nums[right]
to frequency map

↓

Is frequency of nums[right] > k?

        No
         |
         v
    Window is valid
         |
         v
   Update maximum

        Yes
         |
         v
    Remove nums[left]
         |
         v
       left++
         |
         v
Check frequency again
         |
         +------+
                |
             Invalid?
                |
              Yes
                |
                └──> Keep shrinking

                No
                |
                v
         Update maximum

↓

right++

↓

right < n ?

    Yes → Continue

    No
     |
     v
Return max

↓

End
```

---

# Complexity

Each element enters the sliding window exactly once.

Each element can also be removed from the window at most once.

Therefore, the total number of left and right pointer movements is linear.

HashMap operations such as:

```text
get()
put()
getOrDefault()
```

take average:

```text
O(1)
```

Therefore:

```text
Time Complexity = O(N)
```

The HashMap can contain up to `N` different elements.

Therefore:

```text
Space Complexity = O(N)
```

---

# Key Takeaways

- This is a classic **Sliding Window** problem.
- Use a `HashMap` to maintain frequencies inside the current window.
- Expand the window using the `right` pointer.
- If an element's frequency becomes greater than `k`, shrink from the left.
- The window becomes valid again when:
  ```text
  frequency of every element <= k
  ```
- The current window length is:
  ```text
  right - left + 1
  ```
- Update the answer only after restoring the validity of the window.
- Each element is added and removed at most once.
- Therefore, the solution runs in:
  ```text
  O(N)
  ```

---

# Most Important Insight

The most important insight is:

> We do not need to restart the search whenever a window becomes invalid.

Suppose the current window is:

```text
[left ........ right]
```

and adding `nums[right]` causes:

```text
frequency > k
```

The only possible problem is that the current window contains too many occurrences of the newly added value.

Instead of checking every possible subarray again, move:

```text
left → right
```

one step at a time until the constraint is restored.

This works because of the **sliding window property**:

```text
If a window is invalid,
removing elements from the left can only decrease frequencies.
```

So:

```text
Expand
  ↓
Invalid?
  ↓
Shrink
  ↓
Valid
  ↓
Record answer
```

The fundamental pattern is:

```text
[left ---------------- right]
          valid window

                ↓ add element

[left ----------------------- right]
             invalid

                ↓ move left

     [left ------------------ right]
             valid again
```

This allows us to solve what could look like an `O(N²)` subarray problem in:

```text
O(N)
```

---

# Summary

The problem asks for the longest contiguous subarray in which every element appears at most `k` times.

The solution uses:

```text
Sliding Window
+
HashMap
```

The HashMap stores:

```text
element -> frequency inside current window
```

The two pointers maintain:

```text
[left ... right]
```

Whenever the frequency of the newly added element becomes greater than `k`, the left side is removed until the window becomes valid again.

The core logic is:

```text
Add nums[right]

while frequency(nums[right]) > k:
    remove nums[left]
    left++

max =
    max(max, right - left + 1)
```

Final complexity:

```text
Time  : O(N)
Space : O(N)
```

The key lesson is:

```text
Maintain a valid window
     ↓
Expand when possible
     ↓
Shrink only when necessary
     ↓
Track the maximum valid length
```

This is one of the most important patterns for solving **longest subarray with frequency/constraint conditions** efficiently.
