# 3756. Concatenate Non-Zero Digits and Multiply by Sum II

**Difficulty:** Medium

You are given a string `s` consisting of digits and a list of queries.

For each query `[l, r]`:

1. Extract the substring `s[l...r]`.
2. Remove all `0`s while preserving the order of the remaining digits.
3. Concatenate the remaining digits to form an integer `x`.
    - If there are no non-zero digits, `x = 0`.
4. Let `sum` be the sum of the digits of `x`.
5. Return

```text
x × sum
```

modulo

```text
10^9 + 7
```

---

## Examples

### Example 1

**Input**

```text
s = "10203004"

queries = [[0,7],[1,3],[4,6]]
```

**Output**

```text
[12340,4,9]
```

**Explanation**

### Query 1

```text
Substring = "10203004"

Non-zero digits = 1234

x = 1234

sum = 1+2+3+4 = 10

Answer = 1234 × 10 = 12340
```

---

### Query 2

```text
Substring = "020"

x = 2

sum = 2

Answer = 4
```

---

### Query 3

```text
Substring = "300"

x = 3

sum = 3

Answer = 9
```

---

### Example 2

**Input**

```text
s = "1000"

queries = [[0,3],[1,1]]
```

**Output**

```text
[1,0]
```

---

### Example 3

**Input**

```text
s = "9876543210"

queries = [[0,9]]
```

**Output**

```text
[444444137]
```

**Explanation**

```text
x = 987654321

sum = 45

Answer = 987654321 × 45
       = 44444444445

Modulo (10^9+7)

= 444444137
```

---

## Constraints

- `1 ≤ s.length ≤ 10^5`
- `1 ≤ queries.length ≤ 10^5`
- `0 ≤ li ≤ ri < s.length`

---

# Optimal Prefix Processing Approach

## Key Observation

Each query asks for:

1. Sum of digits.
2. Concatenated number after removing zeros.

Doing this independently for every query costs

```text
O(length of substring)
```

which becomes

```text
O(NQ)
```

and is too slow.

Instead, preprocess three prefix arrays.

---

## Prefix Arrays

### 1. `presum`

Stores the prefix sum of all digits.

```text
presum[i]
=
sum of digits before index i
```

Example

```text
s = 10203004

presum

0 1 1 3 3 6 6 6 10
```

Then

```text
Digit Sum

=
presum[r+1] - presum[l]
```

---

### 2. `digits`

Stores how many **non-zero digits** have appeared.

Example

```text
s = 10203004

digits

0 1 1 2 2 3 3 3 4
```

Therefore

```text
Non-zero digits inside query

=
digits[r+1] - digits[l]
```

---

### 3. `numbers`

Suppose we continuously concatenate only non-zero digits.

Example

```text
s = 10203004

After reading

1 -> 1

2 -> 12

3 ->123

4 ->1234
```

Store

```text
numbers[i]

=
concatenated non-zero digits
before position i
(mod M)
```

For every non-zero digit

```text
numbers[i+1]

=
numbers[i] * 10 + digit
```

---

## Removing the Prefix

Suppose

```text
numbers[r]

contains

ABCDE
```

and

```text
numbers[l]

contains

AB
```

We want

```text
CDE
```

If

```text
k

=
number of non-zero digits inside query
```

then

```text
AB

occupies

k

digits before CDE
```

So

```text
AB

becomes

AB × 10^k
```

Hence

```text
Number

=
numbers[r]

-

numbers[l] × 10^k
```

Modulo arithmetic gives

```text
(number + MOD) % MOD
```

---

## Algorithm

### Preprocessing

Build

- `presum`
- `digits`
- `numbers`
- powers of 10

All in

```text
O(N)
```

---

### For every query

Compute

```text
sum

=
presum[r]-presum[l]
```

Compute

```text
digitCount

=
digits[r]-digits[l]
```

Remove prefix

```text
number

=
numbers[r]

-

numbers[l] × 10^(digitCount)
```

Finally

```text
(number × sum) % MOD
```

---

# Dry Run

## Input

```text
s = "10203004"

query = [0,7]
```

### Prefix arrays

```text
numbers

0
1
1
12
12
123
123
123
1234
```

```text
digits

0
1
1
2
2
3
3
3
4
```

```text
presum

0
1
1
3
3
6
6
6
10
```

Now

```text
sum

=
10
```

```text
digitCount

=
4
```

```text
prefix

=
0
```

```text
number

=
1234
```

Answer

```text
1234 × 10

=
12340
```

---

# Correctness

The prefix arrays guarantee:

- `presum` gives digit sums in O(1).
- `digits` tells exactly how many digits belong to the query.
- `numbers` stores concatenations modulo `M`.

Multiplying the prefix by

```text
10^(digits in query)
```

aligns it with the suffix and allows subtraction to isolate exactly the query's concatenated number.

Thus every query returns the required

```text
x × digitSum
```

correctly.

---

# Complexity Analysis

### Preprocessing

- Prefix arrays : **O(N)**
- Powers of 10 : **O(N)**

### Each Query

- Constant work

```text
O(1)
```

### Overall

**Time Complexity**

```text
O(N + Q)
```

**Space Complexity**

```text
O(N)
```

---

# Java Solution

```java
class Solution {

    long MOD = 1_000_000_007;

    int n;
    String s;
    long[] pow;

    public int[] sumAndMultiply(String s, int[][] queries) {

        this.s = s;
        n = s.length();

        int[] numbers = new int[n + 1];
        int[] digits = new int[n + 1];
        int[] presum = new int[n + 1];

        pow = new long[n + 1];
        pow[0] = 1;

        for (int i = 1; i <= n; i++)
            pow[i] = (pow[i - 1] * 10) % MOD;

        buildPrefix(numbers, digits, presum);

        int[] ans = new int[queries.length];

        int idx = 0;

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1] + 1;

            int sum = presum[r] - presum[l];

            int digitCount = digits[r] - digits[l];

            long prefix = numbers[l];

            long remove = (prefix * pow[digitCount]) % MOD;

            long number = (numbers[r] - remove + MOD) % MOD;

            ans[idx++] = (int)((number * sum) % MOD);
        }

        return ans;
    }

    private void buildPrefix(int[] numbers,
                             int[] digits,
                             int[] presum) {

        for (int i = 0; i < n; i++) {

            int d = s.charAt(i) - '0';

            presum[i + 1] = presum[i] + d;

            if (d == 0) {

                digits[i + 1] = digits[i];
                numbers[i + 1] = numbers[i];

            } else {

                digits[i + 1] = digits[i] + 1;

                numbers[i + 1] =
                        (int)((1L * numbers[i] * 10 + d) % MOD);
            }
        }
    }
}
```