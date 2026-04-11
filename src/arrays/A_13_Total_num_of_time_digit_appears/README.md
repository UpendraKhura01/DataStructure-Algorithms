# 🔢 Count Total Occurrences of a Digit in an Array

## 📌 Problem Statement

Given:
- An array `nums[]` of integers
- A single digit `digit` (0–9)

### 🎯 Goal:
Count how many times the given `digit` appears **in all numbers combined**

---

## 💡 Intuition

We need to:
- Look at **each number**
- Break it into **individual digits**
- Count how many times the target digit appears

---

## 🧠 Thought Process

For every number:
1. Extract digits using:
   ```
   num % 10
   ```
2. Compare with target digit
3. Remove last digit:
   ```
   num / 10
   ```

---

## 🔁 Key Idea

👉 Process each number independently  
👉 Add all counts together

---

## ⚙️ Approach

1. Loop through array
2. For each number:
    - Count occurrences of digit
3. Add to total answer

---

## 🚀 Code (Function Only)

```java
static int countDigitOccurrences(int[] nums, int digit) {

    int ans = 0;

    for (int num : nums) {
        ans += countdigit(num, digit);
    }

    return ans;
}

static int countdigit(int num, int digit) {

    int count = 0;

    while (num > 0) {
        int d = num % 10;

        if (d == digit) count++;

        num = num / 10;
    }

    return count;
}
```

---

## 🧪 Dry Run Example

### Input:
```
nums = [1, 34, 7]
digit = 9
```

---

### Step-by-step:

```
1 → no 9
34 → no 9
7 → no 9
```

---

### Output:
```
0
```

---

### Another Example:

```
nums = [19, 91, 99]
digit = 9
```

```
19 → 1
91 → 1
99 → 2
Total = 4
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(N * digits) |
| Space | O(1) |

---

## 🎯 Key Takeaways

- Use `% 10` to extract digits
- Use `/ 10` to remove digits
- Process each number independently
- Simple but important **digit manipulation problem**

---

## 🏁 Summary

> Break each number into digits and count occurrences of the target digit.

---

## 📚 Related Topics

- Digit Manipulation
- Math / Number Processing
- Basic Looping  