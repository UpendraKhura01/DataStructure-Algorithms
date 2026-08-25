# 3718. Smallest Missing Multiple of K

## 1. Problem Statement
Given an integer array `nums` and an integer `k`, return the smallest positive multiple of `k` that is missing from `nums`. A positive multiple of `k` is any integer of the form `k * i` where `i >= 1` (i.e., `k, 2k, 3k, 4k...`).

---

## 2. Goal
- Identify the smallest positive integer `M = k * i` (where `i >= 1`) such that `M` is not in `nums`.
- Achieve fast O(1) lookup times for checking whether a multiple exists in the array.
- Implement a clean Java solution using an appropriate data structure.

---

## 3. Intuition
Checking if an element exists in an array via linear scan takes O(N) time. If we need to check sequential multiples (k, 2k, 3k...), repeated linear scans would lead to high complexity.

By inserting all elements of `nums` into a HashSet upfront, we reduce lookup time to O(1) on average. We can then test multiples sequentially starting from 1 * k until we find the first multiple absent from the hash set.

---

## 4. Key Idea
- HashSet for O(1) Lookup: Store array values in a HashSet to achieve constant-time existence checks.
- Sequential Multiples Generation: Iterate an integer multiplier `i = 1, 2, 3...` to test values `k * i` in ascending order.
- First Miss Terminates: The very first candidate `k * i` that is not present in the HashSet is guaranteed to be the smallest missing multiple.

---

## 5. Thought Process
1. Analyze Input Bounds & Constraints:
    - 1 <= nums.length <= 100
    - 1 <= nums[i] <= 100
    - 1 <= k <= 100
    - Because the maximum value in `nums` is 100, the missing multiple can be at most 101 * k. Checking multipliers from `i = 1` to 101 guarantees finding the missing value.

2. Choose the Strategy:
    - Step 1: Populate a HashSet with all numbers in `nums`.
    - Step 2: Loop `i` from 1 upwards.
    - Step 3: Compute candidate multiple = `k * i`.
    - Step 4: If `set.contains(candidate)` is false, return `candidate`.

3. Handle Edge Cases:
    - If `k` itself is not in `nums`, `i = 1` will immediately yield `k` as the answer.
    - If all multiples of `k` present in `nums` are contiguous starting from `k`, the loop naturally finds the next integer multiple.

---

## 6. Code
```java
class Solution {
    int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= 101; i++) {
            int multiple = k * i;
            if (!set.contains(multiple)) {
                return multiple;
            }
        }

        return -1;
    }
}
```

---

## 7. Dry Run (Step-by-Step)

Input: nums = [8, 2, 3, 4, 6], k = 2

HashSet Initialization:
Add elements to set: {2, 3, 4, 6, 8}

Loop Execution:
| Iteration (i) | Candidate (k * i) | set.contains(multiple) | Status | Action |
|:---:|:---:|:---:|:---:|:---:|
| 1 | 2 * 1 = 2 | set.contains(2) -> true | Present | Continue loop |
| 2 | 2 * 2 = 4 | set.contains(4) -> true | Present | Continue loop |
| 3 | 2 * 3 = 6 | set.contains(6) -> true | Present | Continue loop |
| 4 | 2 * 4 = 8 | set.contains(8) -> true | Present | Continue loop |
| 5 | 2 * 5 = 10 | set.contains(10) -> false | Missing | Return 10 |

Final Output: 10

---

Example 2 Dry Run: nums = [1, 4, 7, 10, 15], k = 5

HashSet Initialization:
Add elements to set: {1, 4, 7, 10, 15}

| Iteration (i) | Candidate (k * i) | set.contains(multiple) | Status | Action |
|:---:|:---:|:---:|:---:|:---:|
| 1 | 5 * 1 = 5 | set.contains(5) -> false | Missing | Return 5 |

Final Output: 5

---

## 8. Logic Flow
```
[Start] ──> Read nums & k
              │
              ▼
   Build HashSet from nums
              │
              ▼
     Initialize multiplier i = 1
              │
              ▼
    ┌──► Calculate candidate = k * i
    │         │
    │         ▼
    │    Is candidate in HashSet?
    │       ├── YES ──> Increment i ──┐
    │       │                         │
    │       └── NO                    │
    │            │                    │
    └────────────┼────────────────────┘
                 ▼
          Return candidate [End]
```

---

## 9. Complexity
- Time Complexity: O(N + M)
    - Building the HashSet takes O(N) where N is nums.length.
    - The search loop runs at most M times (where M <= 101 based on constraints). Each hash lookup is O(1) average time.
    - Total Time: O(N) amortized.
- Space Complexity: O(N)
    - The HashSet stores at most N unique elements from nums.

---

## 10. Key Takeaways
- HashSet conversion simplifies existence queries: Storing elements in a hash-based set turns repeated membership checks into constant-time operations.
- Leveraging constraints: Knowing that nums[i] <= 100 guarantees that iterating i up to 101 will safely find the smallest missing multiple.
- Ascending order guarantee: Iterating i starting from 1 ensures the first missing candidate discovered is mathematically the smallest positive multiple.

---

## 11. Most Important Insight
Generating positive multiples in strict ascending order (k, 2k, 3k...) allows us to terminate and return immediately upon encountering the first missing value in the hash set, eliminating the need to check higher multiples or sort the array.

---

## 12. Summary
We insert all numbers from nums into a HashSet for O(1) existence checking. We then iterate an integer multiplier i from 1 upwards, generating candidates k * i. The first candidate absent from the set is returned as the smallest missing positive multiple of k.