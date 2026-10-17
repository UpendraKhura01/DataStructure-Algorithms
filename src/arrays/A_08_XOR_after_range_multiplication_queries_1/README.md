# 🔢 XOR After Range Multiplication Queries

## 📌 Problem Statement

You are given:

- An array `nums[]`
- A list of queries where each query is:

```
[l, r, k, v]
```

### 🔍 Meaning of Query:

For each query:
- Start from index `l`
- Go till index `r`
- Jump with step size `k`
- Multiply each selected element by `v`

---

### 🎯 Goal:

After applying all queries, return:

```
XOR of all elements in the final array
```

---

## 💡 Intuition

Each query modifies **specific indices**:

```
l, l+k, l+2k, ..., ≤ r
```

👉 This is not a continuous range → it’s a **stepped traversal**

---

## 🧠 Thought Process

### 🔹 Why simple iteration?

- Each query directly tells:
    - where to start
    - where to end
    - how to jump
- So we simulate the process

---

### 🔹 Why modulo?

```java
nums[i] = (nums[i] * v) % MOD
```

- Prevents integer overflow
- Keeps values within limits

---

## ⚙️ Approach

1. Loop through each query
2. For each query:
    - Start at `l`
    - Move with step `k`
    - Multiply elements by `v`
3. After all queries:
    - Compute XOR of array

---

## 🚀 Code (Java)

```java
package arrays.A_08_XOR_after_range_multiplication_queries_1;

public class String.S_16_Max_Digit_Sum_Number_in_1_to_N.Solution {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 4};
        int[][] queries = {
                {1, 4, 2, 3},
                {0, 2, 1, 2}
        };

        System.out.println(xorAfterQueries(nums, queries));
    }

    static int xorAfterQueries(int[] nums, int[][] queries) {

        int n = nums.length;
        int qn = queries.length;

        int MOD = 1000000000 + 7;

        for (int i = 0; i < qn; i++) {

            int idx = queries[i][0];
            int r = queries[i][1];
            int k = queries[i][2];
            int v = queries[i][3];

            while (idx <= r) {
                nums[idx] = (int)((long) nums[idx] * v % MOD);
                idx += k;
            }
        }

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}
```

---

## 🧪 Dry Run Example

### Input:

```
nums = [2, 3, 1, 5, 4]

queries = [
 [1, 4, 2, 3],
 [0, 2, 1, 2]
]
```

---

### 🔁 Query 1: [1,4,2,3]

Indices affected:
```
1 → 3
3 → 5
```

Multiply by 3:

```
nums = [2, 9, 1, 15, 4]
```

---

### 🔁 Query 2: [0,2,1,2]

Indices:
```
0,1,2
```

Multiply by 2:

```
nums = [4, 18, 2, 15, 4]
```

---

### 🔚 Final XOR:

```
4 ^ 18 ^ 2 ^ 15 ^ 4
= (4 ^ 4) ^ 18 ^ 2 ^ 15
= 0 ^ 18 ^ 2 ^ 15
= 18 ^ 2 ^ 15
= 31
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(Q * (range/k)) |
| Space | O(1) |

---

## 🔍 Key Observations

- Not a normal range update → step-based updates
- XOR properties:
    - `a ^ a = 0`
    - `a ^ 0 = a`

---

## 🎯 Key Takeaways

- Direct simulation works efficiently here
- Step-based traversal is important
- XOR is computed at the end
- Use modulo to avoid overflow

---

## 🏁 Summary

> Apply each query using step jumps, then compute XOR of final array.

---

## 📚 Related Topics

- Array Simulation
- Range Queries
- Bit Manipulation (XOR)
- Modular Arithmetic  