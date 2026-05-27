# 🔁 Minimum Absolute Distance Between Mirror Pairs

## 📌 Problem Statement

Given an integer array `nums`.

A mirror pair is defined as a pair of indices `(i, j)` such that:

    0 <= i < j < nums.length
    reverse(nums[i]) == nums[j]

Where:

    reverse(x) means reversing the digits of x
    (leading zeros are removed automatically)

Example:

    reverse(120) = 21

We need to find:

    Minimum value of |i - j| among all mirror pairs

If no such pair exists:

    return -1

---

## 🎯 Goal

Find the minimum absolute distance between indices of any valid mirror pair efficiently.

---

## 💡 Intuition

Brute force approach:

    Try every pair (i, j)
    Check if reverse(nums[i]) == nums[j]

This takes:

    O(n²)

which is too slow for large `n`.

Instead, we want:

    O(n)

So we need a way to quickly check:

    "Have we seen the reverse of current number before?"

This is where HashMap helps.

---

## 🔥 Key Idea

Use a HashMap to store:

    key   → reversed value
    value → index where it appeared

For each element:

    1. Check if current number exists in map
    2. If yes → we found a mirror pair
    3. Update minimum distance
    4. Store reverse of current number in map

---

## 🧠 Thought Process

We iterate through the array from left to right.

At each index `i`:

Step 1:

    Check if nums[i] exists in map

Meaning:

    Someone earlier had reverse(nums[j]) = nums[i]

So:

    (j, i) is a mirror pair

Step 2:

    Calculate distance:

        i - previous_index

Step 3:

    Update minimum

Step 4:

    Insert reverse(nums[i]) into map with index i

Why?

Because future numbers might match this reverse.

---

## 💻 Code

```java
static int minMirrorPairDistance(int[] nums) {

    Map<Integer, Integer> map = new HashMap<>();
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {

        if (map.containsKey(nums[i])) {
            min = Math.min(min, i - map.get(nums[i]));
        }

        int r = reverse(nums[i]);
        map.put(r, i);
    }

    return min == Integer.MAX_VALUE ? -1 : min;
}

static int reverse(int num) {
    int reversed = 0;

    while (num != 0) {
        int digit = num % 10;
        reversed = reversed * 10 + digit;
        num /= 10;
    }

    return reversed;
}
```

---

## 🧪 Dry Run

Input:

    nums = [12, 21, 45, 33, 54]

---

Initialize:

    map = {}
    min = ∞

---

i = 0

    nums[0] = 12

Check:

    map.containsKey(12) → NO

Compute reverse:

    reverse(12) = 21

Store:

    map = {21 → 0}

---

i = 1

    nums[1] = 21

Check:

    map.containsKey(21) → YES

Mirror pair found:

    previous index = 0

Distance:

    1 - 0 = 1

Update:

    min = 1

Compute reverse:

    reverse(21) = 12

Store:

    map = {21 → 0, 12 → 1}

---

i = 2

    nums[2] = 45

Check:

    map.containsKey(45) → NO

Compute reverse:

    reverse(45) = 54

Store:

    map = {21 → 0, 12 → 1, 54 → 2}

---

i = 3

    nums[3] = 33

Check:

    map.containsKey(33) → NO

Compute reverse:

    reverse(33) = 33

Store:

    map = {21 → 0, 12 → 1, 54 → 2, 33 → 3}

---

i = 4

    nums[4] = 54

Check:

    map.containsKey(54) → YES

Mirror pair found:

    previous index = 2

Distance:

    4 - 2 = 2

Update:

    min = min(1, 2) = 1

---

Final Answer:

    1

---

## 🔁 Logic Flow

    Start
      |
      v
    Initialize HashMap and min = ∞
      |
      v
    Loop through array
      |
      v
    Check if current number exists in map
      |
      v
    If yes:
        update min distance
      |
      v
    Compute reverse of current number
      |
      v
    Store reverse in map with index
      |
      v
    Continue loop
      |
      v
    If min not updated:
        return -1
    Else:
        return min

---

## 📊 Complexity

Time Complexity:

    O(n * d)

Where:

    n = number of elements
    d = number of digits in each number

Since reversing takes O(d):

    Total = O(n)

---

Space Complexity:

    O(n)

Reason:

    HashMap stores at most n elements

---

## 🎯 Key Takeaways

- Avoid brute force pair checking.
- Use HashMap for fast lookup.
- Store reversed values instead of original.
- Always update minimum distance.
- Reverse operation is crucial.
- Works efficiently for large inputs.

---

## 🔥 Most Important Insight

Instead of checking:

    reverse(nums[i]) == nums[j]

We flip the logic:

    Store reverse(nums[i]) in map

Then check:

    nums[j] exists in map

This reduces complexity from:

    O(n²) → O(n)

---

## 🏁 Summary

To solve this problem:

    1. Traverse the array once.
    2. For each element, check if it exists in map.
    3. If yes, update minimum distance.
    4. Store reverse of current number.
    5. Return minimum distance or -1.

Efficient solution:

    Time  : O(n)
    Space : O(n)