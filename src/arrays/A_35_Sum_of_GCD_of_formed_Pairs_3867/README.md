# Sum of GCD of Formed Pairs

**Difficulty:** Medium

## 🧩 Problem Statement

You are given an integer array **nums** of length **n**.

Construct an array **prefixGcd** where:

- `mxi` = maximum element among `nums[0...i]`
- `prefixGcd[i] = gcd(nums[i], mxi)`

After constructing `prefixGcd`:

1. Sort it in non-decreasing order.
2. Pair the smallest remaining element with the largest remaining element.
3. Compute the GCD of every formed pair.
4. If `n` is odd, the middle element remains unpaired.
5. Return the **sum of the GCDs** of all formed pairs.

---

## 📌 Examples

### Example 1

**Input**
```text
nums = [2,6,4]
```

**Output**
```text
2
```

**Explanation**

Construct `prefixGcd`:

| i | nums[i] | Prefix Maximum | prefixGcd |
|---|---------|----------------|-----------|
|0|2|2|2|
|1|6|6|6|
|2|4|6|2|

```
prefixGcd = [2,6,2]
```

After sorting:

```
[2,2,6]
```

Pair:

```
(2,6) → gcd = 2
```

Middle element `2` is ignored.

Answer = **2**

---

### Example 2

**Input**
```text
nums = [3,6,2,8]
```

**Output**
```text
5
```

**Explanation**

Construct:

```
prefixGcd = [3,6,2,8]
```

Sorted:

```
[2,3,6,8]
```

Pairs:

```
gcd(2,8) = 2
gcd(3,6) = 3
```

Answer:

```
2 + 3 = 5
```

---

## 🔍 Approach

### Step 1

Maintain the maximum value seen so far.

For every element,

```
prefixGcd[i] = gcd(currentMaximum, nums[i])
```

---

### Step 2

Sort the `prefixGcd` array.

---

### Step 3

Use two pointers:

- left → smallest element
- right → largest element

For every pair,

```
answer += gcd(prefixGcd[left], prefixGcd[right])
```

Move both pointers inward until they meet.

If one element remains (odd length), ignore it.

---

## ▶ Dry Run

### Input

```text
nums = [3,6,2,8]
```

Maximums:

```
3
6
6
8
```

prefixGcd:

```
gcd(3,3)=3
gcd(6,6)=6
gcd(6,2)=2
gcd(8,8)=8
```

Array:

```
[3,6,2,8]
```

Sort:

```
[2,3,6,8]
```

Pairs:

```
(2,8) → gcd = 2
(3,6) → gcd = 3
```

Total:

```
5
```

---

## ⏱ Complexity Analysis

### Time Complexity

Building prefix array:

```
O(n)
```

Sorting:

```
O(n log n)
```

Pairing:

```
O(n)
```

Overall:

```text
O(n log n)
```

---

### Space Complexity

```text
O(n)
```

For the `prefixGcd` array.

---

## ✅ Java Solution

```java
import java.util.Arrays;

public class Solution {

    long gcdSum(int[] nums) {

        int n = nums.length;

        int currentMax = -1;
        int[] prefixGcd = new int[n];

        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = gcd(currentMax, nums[i]);
        }

        Arrays.sort(prefixGcd);

        int left = 0;
        int right = n - 1;

        long sum = 0;

        while (left < right) {
            sum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }

        return sum;
    }

    private int gcd(int a, int b) {

        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
```

---

## 💡 Key Idea

The prefix GCD array captures the relationship between each element and the maximum value seen so far.

After sorting:

- Smallest values are paired with largest values.
- Two pointers efficiently form all required pairs.
- Euclid's algorithm computes each pair's GCD in logarithmic time.

Overall complexity is dominated by sorting:

**O(n log n)**.