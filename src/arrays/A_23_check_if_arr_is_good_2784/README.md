# ✅ Check if Array is Good

## 📌 Problem Statement

An array is considered good if it is a permutation of:

    base[n] = [1, 2, 3, ..., n - 1, n, n]

Meaning:

- Numbers from `1` to `n - 1` appear exactly once
- Number `n` appears exactly twice
- Total array length becomes:

      n + 1

Given an integer array `nums`, determine whether it is a good array.

Return:

    true  → if nums is good
    false → otherwise

---

## 🎯 Goal

Check whether the given array satisfies all conditions of:

    base[n]

---

## 💡 Intuition

The maximum element of the array must be:

    n

Because in:

    base[n]

the largest number is always `n`.

Once we know `n`, we can verify the:

    1. Array size must be n + 1
    2. Numbers 1 to n-1 must appear exactly once
    3. Number n must appear exactly twice

If all conditions are satisfied:

    array is good

Otherwise:

    array is not good

---

## 🔥 Key Idea

The entire problem depends on:

    maximum element = n

After finding `n`:

Step 1:

    Check size

Expected:

    nums.length == n + 1

Step 2:

    Count frequencies

Step 3:

    Verify:

        freq[1...n-1] == 1
        freq[n] == 2

---

## 🧠 Thought Process

Suppose:

``` id="example-good"
nums = [1, 3, 3, 2]
```

Find maximum element:

``` id="max-element"
n = 3
```

Now expected base array:

``` id="expected-base"
base[3] = [1, 2, 3, 3]
```

Check conditions:

``` id="condition-checks"
1 appears once  → valid
2 appears once  → valid
3 appears twice → valid
```

Array length:

``` id="length-check"
nums.length = 4
n + 1 = 4
```

Everything matches.

So answer is:

``` id="answer-true"
true
```

---

Now consider:

``` id="bad-example"
nums = [2, 1, 3]
```

Maximum:

``` id="bad-max"
n = 3
```

Expected base array:

``` id="bad-base"
[1, 2, 3, 3]
```

Expected size:

``` id="expected-size"
4
```

Actual size:

``` id="actual-size"
3
```

Sizes do not match.

So answer is:

``` id="answer-false"
false
```

---

## 💻 Code

```java
static boolean isGood(int[] nums) {

    int n = 0;

    // Find maximum element
    for (int num : nums) {
        n = Math.max(n, num);
    }

    // Size check
    if (nums.length != n + 1) {
        return false;
    }

    int[] freq = new int[201];

    // Count frequencies
    for (int num : nums) {
        freq[num]++;
    }

    // Check numbers 1 to n-1
    for (int i = 1; i < n; i++) {
        if (freq[i] != 1) {
            return false;
        }
    }

    // Check n appears twice
    return freq[n] == 2;
}
```

---

## 🧪 Dry Run

Input:

``` id="dry-input"
nums = [1, 3, 3, 2]
```

---

Step 1: Find maximum element

``` id="step1"
n = 3
```

---

Step 2: Check size

Expected size:

``` id="expected"
n + 1 = 4
```

Actual size:

``` id="actual"
nums.length = 4
```

Valid.

---

Step 3: Count frequencies

Frequency table:

``` id="freq-table"
freq[1] = 1
freq[2] = 1
freq[3] = 2
```

---

Step 4: Check numbers from 1 to n-1

Check:

``` id="check1"
freq[1] == 1 → true
freq[2] == 1 → true
```

All valid.

---

Step 5: Check maximum number

``` id="checkn"
freq[3] == 2
```

True.

---

Final Answer:

``` id="final-answer"
true
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Find maximum element n
      |
      v
    Check:
        nums.length == n + 1 ?
      |
     No -------> Return false
      |
     Yes
      |
      v
    Count frequencies
      |
      v
    For every number from 1 to n-1
      |
      v
    Does frequency equal 1?
      |
     No -------> Return false
      |
     Yes
      |
      v
    Check:
        freq[n] == 2 ?
      |
      v
    Return result

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    One traversal to find maximum
    One traversal for frequency count
    One traversal for validation

Total:

    O(n)

---

Space Complexity:

    O(1)

Reason:

    Frequency array size is fixed:

        freq[201]

Constant extra space is used.

---

## 🎯 Key Takeaways

- Maximum element determines `n`.
- Good array must have size `n + 1`.
- Numbers `1` to `n-1` appear once.
- Maximum number `n` appears twice.
- Frequency counting makes validation easy.
- Order does not matter because permutation is allowed.

---

## 🔥 Most Important Insight

The whole problem becomes simple after realizing:

    maximum element = n

Then we only need to verify the exact frequency pattern of:

``` id="base-pattern"
[1, 2, 3, ..., n-1, n, n]
```

---

## 🏁 Summary

To solve this problem:

    1. Find the maximum element n.
    2. Check array length equals n + 1.
    3. Count frequencies.
    4. Verify:
           numbers 1 to n-1 appear once
           number n appears twice
    5. Return true if all conditions match.

Efficient solution:

    Time  : O(n)
    Space : O(1)