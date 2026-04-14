# 🔁 Next Smallest Palindrome (Integer Array)

## 📌 Problem Statement

Given a number represented as an array `num[]` (each element is a digit):

### 🎯 Goal:
Find the **next smallest palindrome strictly greater** than the given number.

---

## 💡 Intuition

A palindrome reads the same forward and backward.

👉 Example:
```
12321 ✅
12345 ❌
```

---

## 🧠 Thought Process

We want:
1. Closest palindrome
2. Strictly greater than original

---

# 🔥 Key Idea

👉 Mirror the left half to the right  
👉 If that is not enough → increment middle and propagate

---

# ⚠️ Special Case

## All 9s

```
999 → 1001
```

---

## 🚀 Code (Functions Only)

```java
static int[] nextPalindrome(int[] num) {

    int n = num.length;

    if (all9(num)) {
        int[] arr = new int[n + 1];
        arr[0] = arr[n] = 1;
        return arr;
    }

    int mid = n / 2;
    int i = mid - 1;
    int j = (n % 2 == 0) ? mid : mid + 1;

    int[] ans = num;

    // Step 1: Find mismatch
    while (i >= 0 && num[i] == num[j]) {
        i--;
        j++;
    }

    boolean needIncrement = false;

    if (i < 0 || num[i] < num[j]) {
        needIncrement = true;
    }

    // Step 2: Mirror left to right
    i = mid - 1;
    j = (n % 2 == 0) ? mid : mid + 1;

    while (i >= 0) {
        ans[j] = ans[i];
        i--;
        j++;
    }

    // Step 3: Handle increment
    if (needIncrement) {

        int carry = 1;
        i = mid - 1;

        if (n % 2 != 0) {
            ans[mid] += carry;
            carry = ans[mid] / 10;
            ans[mid] %= 10;
            j = mid + 1;
        } else {
            j = mid;
        }

        while (i >= 0) {
            ans[i] += carry;
            carry = ans[i] / 10;
            ans[i] %= 10;

            ans[j] = ans[i];

            i--;
            j++;
        }
    }

    return ans;
}
static boolean all9(int[] arr) {
    for (int x : arr) {
        if (x != 9) return false;
    }
    return true;
}
```

---

# 🧠 Step-by-Step Logic

---

## 🔹 Step 1: Compare Left & Right

Move from middle outward:
- If left < right → need increment
- If left > right → just mirror works

---

## 🔹 Step 2: Mirror

Copy:
```
left → right
```

---

## 🔹 Step 3: Increment (if needed)

- Add `1` to middle
- Handle carry
- Propagate to left
- Mirror again

---

# 🧪 Dry Run Example

## Input:
```
[2, 3, 5, 4, 5]
```

---

### Step 1: Compare

```
Left: 3
Right: 4
→ left < right → need increment
```

---

### Step 2: Mirror

```
23545 → 23532
```

---

### Step 3: Increment middle

```
middle = 5 → 6
```

---

### Final:
```
23632 ✅
```

---

# 🧪 Another Example

## Input:
```
[9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2]
```

---

### Output:
```
[9, 4, 1, 8, 8, 0, 8, 8, 1, 4, 9]
```

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

# 🎯 Key Takeaways

- Always try **mirroring first**
- Only increment if needed
- Handle **middle & carry carefully**
- Special case: all 9s

---

# 🏁 Summary

> Mirror left half, and if needed, increment middle to get next palindrome.

---

# 📚 Related Topics

- Arrays
- Two Pointer Technique
- Carry Propagation
- Number Manipulation  