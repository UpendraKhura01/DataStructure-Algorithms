# 📏 Minimum Distance Between Three Equal Elements

## 📌 Problem Statement

Given an array `nums[]`, find the **minimum distance** between any **three equal elements**.

### 🎯 Goal:

Find indices `i < j < k` such that:

```
nums[i] == nums[j] == nums[k]
```

Minimize:

```
distance = 2 * (k - i)
```

If no such triplet exists → return `-1`

---

## 💡 Intuition

We need:
- 3 same elements
- Minimum spread between first and third

👉 So for same values:
- Find their indices
- Choose closest triplets

---

# 🧠 Approach 1: Brute Force

---

## 🔹 Idea

Try all possible triplets `(i, j, k)`:

- Check if all equal
- Compute distance
- Track minimum

---

## 🚀 Code (Function Only)

```java
static int minimumDistanceBruteforce(int[] nums) {

    int n = nums.length;
    int ans = Integer.MAX_VALUE;

    for (int i = 0; i < n - 2; i++) {
        for (int j = i + 1; j < n - 1; j++) {

            if (nums[i] != nums[j]) continue;

            for (int k = j + 1; k < n; k++) {

                if (nums[i] == nums[k]) {
                    int dist = 2 * (k - i);
                    ans = Math.min(ans, dist);
                    break; // smallest k for this (i,j)
                }
            }
        }
    }

    return (ans == Integer.MAX_VALUE) ? -1 : ans;
}
```

---

## 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n³) |
| Space | O(1) |

---

# ⚡ Approach 2: HashMap + Index Tracking (Optimal)

---

## 💡 Intuition

Instead of checking all triplets:

👉 Group indices of same elements

Example:
```
nums = [1,2,1,1,3]

Map:
1 → [0,2,3]
2 → [1]
3 → [4]
```

---

## 🔥 Key Observation

For indices:

```
[i1, i2, i3, i4, ...]
```

Valid triplets are:

```
(i1,i2,i3), (i2,i3,i4), ...
```

👉 Only need **consecutive groups of 3**

---

## ❗ Why only consecutive?

Because:
- They give **minimum gap**
- Any non-consecutive triplet will have larger distance

---

## 🚀 Code (Function Only)

```java
static int minimumDistance(int[] nums) {

    int n = nums.length;
    int ans = Integer.MAX_VALUE;

    HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

    for (int i = 0; i < n; i++) {
        mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
    }

    for (int key : mp.keySet()) {

        ArrayList<Integer> list = mp.get(key);

        if (list.size() >= 3) {

            for (int k = 2; k < list.size(); k++) {
                int dist = 2 * (list.get(k) - list.get(k - 2));
                ans = Math.min(ans, dist);
            }
        }
    }

    return (ans == Integer.MAX_VALUE) ? -1 : ans;
}
```

---

## 🧪 Dry Run Example

### Input:
```
nums = [1, 2, 1, 1, 3]
```

---

### Step 1: Build Map

```
1 → [0,2,3]
2 → [1]
3 → [4]
```

---

### Step 2: Check Triplets

For `1 → [0,2,3]`

```
i = 0, j = 2, k = 3

distance = 2 * (3 - 0) = 6
```

---

### Output:
```
6
```

---

## 🔁 Why `(k - 2)`?

For list:
```
[0,2,3,7]
```

Triplets:
```
(0,2,3)
(2,3,7)
```

👉 So:
```
k and k-2 → first and third element
```

---

## 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(n) |

---

## 🎯 Key Takeaways

- Group indices using HashMap
- Only check **consecutive triples**
- Avoid unnecessary comparisons
- Huge optimization from O(n³) → O(n)

---

## 🏁 Summary

> Track indices of equal elements and compute minimum distance using sliding window of size 3.

---

## 📚 Related Topics

- HashMap
- Array Index Tracking
- Sliding Window (fixed size)
- Optimization Techniques  