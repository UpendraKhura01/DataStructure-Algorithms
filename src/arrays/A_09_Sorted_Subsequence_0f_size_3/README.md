# 📈 Sorted Subsequence of Size 3

## 📌 Problem Statement

Given an array `arr[]`, find **any increasing subsequence of size 3** such that:

```
arr[i] < arr[j] < arr[k]  where i < j < k
```

### 🎯 Goal:
Return any such triplet `[a, b, c]`.  
If no such triplet exists, return empty list.

---

## 💡 Intuition

We need 3 elements:
- One **smaller on left**
- One **current element**
- One **greater on right**

👉 Instead of checking all triplets (O(n³)), we optimize.

---

# 🧠 Approach 1: Left Min + Right Max (O(n) + O(n))

---

## 🔹 Idea

For every index `i`, we check:

- Is there a **smaller element on left?**
- Is there a **greater element on right?**

If YES → valid triplet found

---

## ⚙️ Steps

### 1. Create two arrays:
- `smallest[i]` → index of smaller element on left
- `biggest[i]` → index of greater element on right

---

### 2. Fill `biggest[]` (right max)

```java
int maxIndex = n-1;
for (i from n-2 to 0):
    if arr[i] >= arr[maxIndex]:
        maxIndex = i
    else:
        biggest[i] = maxIndex
```

---

### 3. Fill `smallest[]` (left min)

```java
int minIndex = 0;
for (i from 1 to n-1):
    if arr[i] <= arr[minIndex]:
        minIndex = i
    else:
        smallest[i] = minIndex
```

---

### 4. Find valid triplet

```java
if smallest[i] != -1 AND biggest[i] != -1:
    return triplet
```

---

## 🚀 Code (Approach 1)

```java
static ArrayList<Integer> find3Numbers(int[] arr) {
    int n = arr.length;
    ArrayList<Integer> ans = new ArrayList<>();

    int[] smallest = new int[n];
    int[] biggest = new int[n];

    Arrays.fill(smallest, -1);
    Arrays.fill(biggest, -1);

    int maxIndex = n - 1;
    for (int i = n - 2; i >= 0; i--) {
        if (arr[i] >= arr[maxIndex]) {
            maxIndex = i;
        } else {
            biggest[i] = maxIndex;
        }
    }

    int minIndex = 0;
    for (int i = 1; i < n; i++) {
        if (arr[i] <= arr[minIndex]) {
            minIndex = i;
        } else {
            smallest[i] = minIndex;
        }
    }

    for (int i = 0; i < n; i++) {
        if (smallest[i] != -1 && biggest[i] != -1) {
            ans.add(arr[smallest[i]]);
            ans.add(arr[i]);
            ans.add(arr[biggest[i]]);
            break;
        }
    }

    return ans;
}
```

---

# ⚡ Approach 2: Greedy (Optimized O(n), O(1) Space)

---

## 💡 Intuition

We try to build:

```
first < mid < current
```

---

## 🧠 Key Variables

| Variable | Meaning |
|----------|--------|
| `potential_first` | best candidate for smallest value |
| `first` | confirmed smallest |
| `mid` | second element |

---

## 🔥 Why `potential_first` is needed?

### ❗ Problem without it:

If we directly update `first`, we may lose the correct sequence.

---

### 🎯 Role of `potential_first`

- Tracks the **smallest value seen so far**
- Helps us **rebuild a better sequence** if a smaller element appears later

---

### 🧠 Think like this:

```
potential_first → candidate for start
first → fixed start of sequence
mid → second element
```

---

## 🔁 Working Logic

```java
for each element:

1. if arr[i] <= potential_first:
       update potential_first

2. else if arr[i] < mid:
       first = potential_first
       mid = arr[i]

3. else:
       we found arr[i] > mid → triplet found
```

---

## 🚀 Code (Approach 2)

```java
static ArrayList<Integer> find3NumbersBetter(int[] arr) {
    int n = arr.length;

    int first = Integer.MAX_VALUE;
    int mid = Integer.MAX_VALUE;
    int potential_first = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {

        if (arr[i] <= potential_first) {
            potential_first = arr[i];
        }

        else if (arr[i] < mid) {
            first = potential_first;
            mid = arr[i];
        }

        else {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(first);
            ans.add(mid);
            ans.add(arr[i]);
            return ans;
        }
    }

    return new ArrayList<>();
}
```

---

## 🧪 Dry Run Example

### Input:
```
arr = [12, 11, 10, 5, 6, 2, 30]
```

---

### Step-by-step:

| i | value | potential_first | first | mid | Action |
|--|------|----------------|------|-----|--------|
|0|12|12|-|-|update potential|
|1|11|11|-|-|update|
|2|10|10|-|-|update|
|3|5 |5 |-|-|update|
|4|6 |5 |5 |6 |set first, mid|
|5|2 |2 |5 |6 |update potential (better start!)|
|6|30|2 |5 |6 |FOUND → 5,6,30|

---

## 🔥 Important Insight

👉 `potential_first = 2` did NOT break existing sequence  
Because:
- `first = 5` was already locked
- So sequence remains valid

---

## 📊 Complexity Analysis

| Approach | Time | Space |
|----------|------|--------|
| Approach 1 | O(n) | O(n) |
| Approach 2 | O(n) | O(1) |

---

## 🎯 Key Takeaways

- Approach 1 = safer, easier to understand
- Approach 2 = optimized, tricky but powerful
- `potential_first` ensures:
    - we don’t miss better starting points
    - we maintain valid increasing sequence

---

## 🏁 Summary

> Find increasing triplet using either prefix/suffix arrays or greedy tracking.

---

## 📚 Related Topics

- Increasing Subsequence
- Greedy Algorithms
- Array Traversal
- Pattern Recognition  