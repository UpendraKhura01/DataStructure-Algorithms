# 🔗 Intersection of Two Sorted Arrays

## 📌 Problem Statement

Given two **sorted arrays** `a[]` and `b[]`, find their **intersection**.

### 🎯 Goal:
Return a list of **common elements** present in both arrays.

### ⚠️ Conditions:
- Arrays are **sorted**
- Output should contain **unique elements only**
- Maintain sorted order in result

---

## 💡 Intuition

Since both arrays are **sorted**, we can use:

👉 **Two Pointer Technique**

Instead of checking every pair (O(n*m)), we move pointers intelligently.

---

## 🧠 Thought Process

We maintain two pointers:

```
first → for array a
second → for array b
```

---

### 🔁 Key Idea

Compare:

```
a[first] vs b[second]
```

---

### Cases:

#### ✅ Case 1: Equal
```java
a[first] == b[second]
```

- Element is common → add to result
- Move both pointers

---

#### 🔼 Case 2: a[first] < b[second]
```java
first++
```

- Need bigger value → move `first`

---

#### 🔽 Case 3: a[first] > b[second]
```java
second++
```

- Need bigger value → move `second`

---

## ⚠️ Handling Duplicates

```java
if (arr.isEmpty() || arr.get(arr.size()-1) != a[first])
```

👉 Ensures:
- Only **unique elements** are added
- Prevents duplicates in result

---

## ⚙️ Algorithm Steps

1. Initialize two pointers `first = 0`, `second = 0`
2. While both pointers are in bounds:
    - Compare elements
    - Move pointers accordingly
    - Add common elements (avoid duplicates)
3. Return result

---

## 🚀 Code (Java)

```java
    static ArrayList<Integer> intersection(int[] a, int[] b) {

        int n = a.length;
        int m = b.length;

        ArrayList<Integer> arr = new ArrayList<>();

        int first = 0, second = 0;

        while (first < n && second < m) {

            if (a[first] == b[second]) {

                if (arr.isEmpty() || arr.get(arr.size() - 1) != a[first]) {
                    arr.add(a[first]);
                }

                first++;
                second++;

            } else if (a[first] < b[second]) {
                first++;
            } else {
                second++;
            }
        }

        return arr;
    }

```

---

## 🧪 Dry Run Example

### Input:

```
a = [1, 1, 2, 2, 2, 4, 4]
b = [2, 4]
```

---

### Step-by-step:

| first | second | a[first] | b[second] | Action |
|------|--------|----------|----------|--------|
| 0 | 0 | 1 | 2 | first++ |
| 1 | 0 | 1 | 2 | first++ |
| 2 | 0 | 2 | 2 | add 2 |
| 3 | 1 | 2 | 4 | first++ |
| 4 | 1 | 2 | 4 | first++ |
| 5 | 1 | 4 | 4 | add 4 |

---

### Output:

```
[2, 4]
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n + m) |
| Space | O(1) (excluding output) |

---

## 🎯 Key Takeaways

- Use **two-pointer technique** for sorted arrays
- Efficient: avoids nested loops
- Always handle **duplicates carefully**
- Works only because arrays are sorted

---

## 🏁 Summary

> Traverse both arrays using two pointers and collect unique common elements.

---

## 📚 Related Topics

- Two Pointer Technique
- Array Traversal
- Union & Intersection Problems
- Merge Process (like Merge Sort)  