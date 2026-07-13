# Sequential Digits (LeetCode 1291)

**Difficulty:** Medium

## Problem Statement

An integer is said to have **sequential digits** if every digit is exactly **one greater** than the previous digit.

For example,

```
1234
4567
6789
```

are sequential numbers, while

```
1245
1357
987
```

are not.

Given two integers `low` and `high`, return **all sequential digit numbers** in the range **[low, high]** in increasing order.

---

## Example 1

### Input

```text
low = 100
high = 300
```

### Output

```text
[123, 234]
```

### Explanation

The sequential numbers having 3 digits are

```
123
234
345
456
567
678
789
```

Only

```
123
234
```

lie inside the range.

---

## Example 2

### Input

```text
low = 1000
high = 13000
```

### Output

```text
[1234,2345,3456,4567,5678,6789,12345]
```

---

# Observation

Every sequential number is simply a **substring** of

```
123456789
```

Examples

```
12
23
34
45
...

123
234
345
...

1234
2345
3456
...

12345
23456
...
```

Thus, instead of generating numbers digit by digit, we can generate every substring of

```
"123456789"
```

with the required length.

---

# Algorithm

1. Convert `low` and `high` into strings.
2. Compute

```
minDigits = digits(low)

maxDigits = digits(high)
```

3. Store

```
"123456789"
```

4. For every possible length

```
minDigits → maxDigits
```

5. Generate every substring of that length.
6. Convert it to an integer.
7. If it lies inside the range, add it to the answer.
8. Since generated numbers are already in increasing order, the answer is automatically sorted.

---

# Dry Run

## Input

```text
low = 100

high = 300
```

Minimum digits

```
3
```

Maximum digits

```
3
```

String

```
123456789
```

Generate every substring of length 3

```
123
234
345
456
567
678
789
```

Check range

```
123 ✓
234 ✓
345 ✗
```

Since

```
345 > high
```

all future numbers will also be larger.

Stop.

Answer

```
[123,234]
```

---

# Why is the Output Already Sorted?

For a fixed length,

```
123
234
345
456
...
```

are naturally increasing.

Also,

every 4-digit sequential number is larger than every 3-digit sequential number.

Therefore,

generating by increasing length automatically produces sorted output.

No sorting is required.

---

# Correctness Proof

Every sequential digit number consists of consecutive digits.

The string

```
123456789
```

contains every possible consecutive digit sequence exactly once.

By generating every substring whose length lies between the number of digits in `low` and `high`, the algorithm considers every possible sequential number.

Each generated number is checked against the range `[low, high]`.

Thus,

- every valid sequential number is included,
- no invalid number is added,
- and the output is naturally sorted.

Hence the algorithm is correct.

---

# Complexity Analysis

The maximum length is

```
9
```

For every length,

at most

```
9
```

substrings exist.

Therefore,

### Time Complexity

```text
O(1)
```

(At most **36** numbers are generated.)

---

### Space Complexity

```text
O(1)
```

excluding the output list.

---

# Java Solution

```java
class Solution {

    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();

        for (int len = minLen; len <= maxLen; len++) {

            for (int i = 0; i <= 9 - len; i++) {

                int num = Integer.parseInt(
                        digits.substring(i, i + len));

                if (num >= low && num <= high)
                    ans.add(num);

                if (num > high)
                    return ans;
            }
        }

        return ans;
    }
}
```

---

# Key Takeaways

- Every sequential digit number is a substring of

```
123456789
```

- Generate substrings whose lengths lie between the digit counts of `low` and `high`.
- Check whether each generated number lies within the required range.
- The generated numbers are already sorted, so no additional sorting is needed.
- Since only a constant number of candidates exist, the solution runs in **O(1)** time.

---

# Tags

- Math
- String
- Simulation
- Enumeration