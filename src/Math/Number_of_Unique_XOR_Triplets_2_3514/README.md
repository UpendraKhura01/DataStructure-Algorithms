# Number of Unique XOR Triplets II (LeetCode 3514)

## Problem Statement
You are given an integer array `nums`.

A XOR triplet is defined as:

`nums[i] XOR nums[j] XOR nums[k]` where `i <= j <= k`.

Return the number of **distinct XOR values** obtainable from all possible triplets.

---

## Example

### Example 1
**Input**
```text
nums = [1,3]
```

**Output**
```text
2
```

**Explanation**

Possible XOR values:

- 1 ^ 1 ^ 1 = 1
- 1 ^ 1 ^ 3 = 3
- 1 ^ 3 ^ 3 = 1
- 3 ^ 3 ^ 3 = 3

Unique values = `{1,3}`

Answer = **2**

---

### Example 2

**Input**
```text
nums = [6,7,8,9]
```

**Output**
```text
4
```

---

## Approach

Observe that

```
a ^ b ^ c
```

can be viewed as

```
(a ^ b) ^ c
```

So instead of checking every triplet (`O(n³)`):

1. Compute every possible XOR of two elements.
2. Store these XOR values.
3. XOR every original number with every stored pair XOR.
4. Count distinct results.

Since `nums[i] ≤ 1500`, every XOR result is less than `2048`. Using a boolean array of size `3000` is sufficient.

---

## Algorithm

1. Create a boolean array `pair[]`.
2. For every pair `(i, j)`:
    - Compute `nums[i] ^ nums[j]`
    - Mark it in `pair`.
3. Create another boolean array `ans[]`.
4. For every number:
    - XOR it with every stored pair XOR.
    - Mark the resulting value.
5. Count marked values.

---

## Correctness

Let

```
x = nums[i] ^ nums[j]
```

Every triplet XOR can be written as

```
x ^ nums[k]
```

Since every possible pair XOR is generated and every array element is combined with it, every valid triplet XOR is produced.

Conversely, every generated value corresponds to

```
(nums[i] ^ nums[j]) ^ nums[k]
```

which is a valid triplet XOR.

Therefore, the algorithm finds **all and only** the unique triplet XOR values.

---

## Complexity Analysis

Let `n = nums.length`.

- Building pair XORs: **O(n²)**
- Combining with all XOR values: **O(n × MAXXOR)**

Since

```
MAXXOR ≈ 2048
```

Overall complexity:

- **Time:** `O(n² + n × 2048)`
- **Space:** `O(2048)`

---

## Java Solution

```java
class Solution {

    public int uniqueXorTriplets(int[] nums) {

        int max = 3000;
        int n = nums.length;

        boolean[] pair = new boolean[max];

        // Store all possible XORs of two elements
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }

        boolean[] ans = new boolean[max];

        // XOR every number with every pair XOR
        for (int num : nums) {
            for (int x = 0; x < max; x++) {
                if (pair[x]) {
                    ans[num ^ x] = true;
                }
            }
        }

        int count = 0;

        for (boolean exists : ans) {
            if (exists) {
                count++;
            }
        }

        return count;
    }
}
```