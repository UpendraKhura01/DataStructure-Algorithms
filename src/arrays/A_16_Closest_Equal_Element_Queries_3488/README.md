# 🔁 Closest Equal Element Queries (Circular Array)

## 📌 Problem Statement

You are given:
- A **circular array** `nums[]`
- A list of queries `queries[]`

### 🎯 Goal:

For each query index `i`:

Find the **minimum distance** to another index `j` such that:
```
nums[j] == nums[queries[i]]
```

👉 If no such index exists → return `-1`

---

## 🔄 Circular Distance

Distance is circular:

```
distance = min(|i - j|, n - |i - j|)
```

---

## 💡 Intuition

👉 Same values repeat → track their positions

👉 For each query:
- Only check indices where value is same
- Find **closest neighbor**

---

# 🧠 Thought Process

---

## 🔹 Step 1: Preprocessing

Store indices of each value:

```
nums = [1,3,1,4,1,3,2]

Map:
1 → [0,2,4]
3 → [1,5]
4 → [3]
2 → [6]
```

---

## 🔹 Step 2: For Each Query

Given:
```
index = queries[i]
value = nums[index]
```

👉 Get all positions of that value

---

## 🔹 Step 3: Find Closest Index

Instead of checking all:

👉 Use **Binary Search**

Find:
- Current position index in list
- Check only:
    - Left neighbor
    - Right neighbor

---

## 🔥 Why Only Neighbors?

Because:
- List is sorted
- Closest index must be adjacent

---

# ⚙️ Distance Calculation

```
straight = |i - j|
circular = n - straight

answer = min(straight, circular)
```

---

## 🚀 Code (Functions Only)

```java
static List<Integer> solveQueries(int[] nums, int[] queries) {

    int n = nums.length;

    HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

    for (int i = 0; i < n; i++) {
        mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
    }

    List<Integer> ans = new ArrayList<>();

    for (int q : queries) {

        int num = nums[q];
        ArrayList<Integer> list = mp.get(num);

        if (list.size() == 1) {
            ans.add(-1);
            continue;
        }

        int idx = binarysearch(list, q);
        int size = list.size();

        int left = (idx == 0) ? size - 1 : idx - 1;
        int right = (idx == size - 1) ? 0 : idx + 1;

        int leftDist = getDist(q, list.get(left), n);
        int rightDist = getDist(q, list.get(right), n);

        ans.add(Math.min(leftDist, rightDist));
    }

    return ans;
}
```

---

### 🔹 Binary Search

```java
static int binarysearch(ArrayList<Integer> list, int target) {

    int left = 0, right = list.size() - 1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        if (list.get(mid) == target) return mid;

        else if (list.get(mid) < target) left = mid + 1;

        else right = mid - 1;
    }

    return -1;
}
```

---

### 🔹 Distance Helper

```java
static int getDist(int i, int j, int n) {

    int straight = Math.abs(i - j);
    int circular = n - straight;

    return Math.min(straight, circular);
}
```

---

# 🧪 Dry Run Example

## Input:
```
nums = [1,3,1,4,1,3,2]
queries = [0,3,5]
```

---

## Query 0 → index = 0

```
value = 1
positions = [0,2,4]

neighbors → 2 and 4

dist(0,2) = 2
dist(0,4) = min(4, 7-4=3) = 3

answer = 2
```

---

## Query 1 → index = 3

```
value = 4
positions = [3]

only one → -1
```

---

## Query 2 → index = 5

```
value = 3
positions = [1,5]

neighbor = 1

dist(5,1):
straight = 4
circular = 7 - 4 = 3

answer = 3
```

---

## Output:
```
[2, -1, 3]
```

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Preprocessing | O(n) |
| Per Query | O(log n) |
| Total | O(n + q log n) |
| Space | O(n) |

---

# 🎯 Key Takeaways

- Use HashMap to group indices
- Use Binary Search to find position
- Only check adjacent indices
- Handle circular distance carefully

---

# 🏁 Summary

> Find nearest same-value index using binary search and circular distance.

---

# 📚 Related Topics

- HashMap
- Binary Search
- Circular Arrays
- Optimization Techniques  