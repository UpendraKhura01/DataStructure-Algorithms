# 🔢 Minimum Operations to Make Array Alternating Prime

## 📌 Problem Statement

Given an array `nums[]`, transform it such that:

```
Index 0 → PRIME
Index 1 → NON-PRIME
Index 2 → PRIME
Index 3 → NON-PRIME
...
```

### 🎯 Goal:
Convert the array into an **alternating prime pattern** with **minimum total operations**

---

## ⚙️ Operation Allowed

- You can **increment any element by 1**
- Cost = total number of increments

---

## 💡 Intuition

For each index:
- If it's **even index** → should be **prime**
- If it's **odd index** → should be **non-prime**

👉 If current value doesn't match requirement:
- Move it to the **next valid number**
- Add the difference to answer

---

# 🧠 Approach 1: Brute Force (Prime Checking)

---

## 🔹 Idea

For each element:
- Check if it's prime
- If not matching requirement:
    - Find next valid number using loop

---

## 🚀 Code (Functions Only)

```java
static int minOperations(int[] nums) {

    int ans = 0;

    for (int i = 0; i < nums.length; i++) {

        if (i % 2 == 0) { // need PRIME
            if (!isprime(nums[i])) {
                int next = nextPrime(nums[i]);
                ans += (next - nums[i]);
            }
        } else { // need NON-PRIME
            if (isprime(nums[i])) {
                int next = nums[i] + 1;
                while (isprime(next)) next++;
                ans += (next - nums[i]);
            }
        }
    }

    return ans;
}

static int nextPrime(int n) {
    while (!isprime(n)) n++;
    return n;
}

static boolean isprime(int n) {
    if (n < 2) return false;

    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) return false;
    }

    return true;
}
```

---

## ⚠️ Drawback

- Repeated prime checking → slow
- Worst case: **O(n * √max)**

---

# ⚡ Approach 2: Sieve Optimization (Best)

---

## 💡 Key Idea

Precompute:
- All primes using **Sieve**
- Next prime for every number
- Next non-prime for every number

👉 Then answer each query in **O(1)**

---

## 🧠 Why this works?

Instead of searching every time:
- We store future answers in arrays

---

## 🚀 Code (Functions Only)

```java
static int minOperationsSieve(int[] nums) {

    int max = 0;
    for (int x : nums) max = Math.max(max, x);

    int LIMIT = max + 100;

    boolean[] isPrime = sieve(LIMIT);
    int[] nextPrime = buildNextPrime(isPrime);
    int[] nextNonPrime = buildNextNonPrime(isPrime);

    int ans = 0;

    for (int i = 0; i < nums.length; i++) {

        if (i % 2 == 0) {
            int target = nextPrime[nums[i]];
            ans += (target - nums[i]);
        } else {
            int target = nextNonPrime[nums[i]];
            ans += (target - nums[i]);
        }
    }

    return ans;
}
```

---

### 🔹 Sieve Function

```java
static boolean[] sieve(int max) {

    boolean[] isPrime = new boolean[max + 1];

    for (int i = 2; i <= max; i++) isPrime[i] = true;

    for (int i = 2; i * i <= max; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= max; j += i) {
                isPrime[j] = false;
            }
        }
    }

    return isPrime;
}
```

---

### 🔹 Next Prime Lookup

```java
static int[] buildNextPrime(boolean[] isPrime) {

    int n = isPrime.length;
    int[] nextPrime = new int[n];

    int next = -1;

    for (int i = n - 1; i >= 0; i--) {
        if (isPrime[i]) next = i;
        nextPrime[i] = next;
    }

    return nextPrime;
}
```

---

### 🔹 Next Non-Prime Lookup

```java
static int[] buildNextNonPrime(boolean[] isPrime) {

    int n = isPrime.length;
    int[] nextNonPrime = new int[n];

    int next = -1;

    for (int i = n - 1; i >= 0; i--) {
        if (!isPrime[i]) next = i;
        nextNonPrime[i] = next;
    }

    return nextNonPrime;
}
```

---

## 🧪 Dry Run Example

### Input:
```
nums = [8, 12]
```

---

### Index 0 → need PRIME
```
8 → next prime = 11 → cost = 3
```

---

### Index 1 → need NON-PRIME
```
12 → already non-prime → cost = 0
```

---

### Output:
```
3
```

---

## 📊 Complexity Analysis

| Approach | Time | Space |
|----------|------|--------|
| Brute    | O(n * √max) | O(1) |
| Sieve    | O(max log log max + n) | O(max) |

---

## 🎯 Key Takeaways

- Alternate pattern → depends on index parity
- Precomputation (Sieve) → huge optimization
- Convert repeated work → lookup tables
- Classic **optimization using preprocessing**

---

## 🏁 Summary

> Convert each element to required prime/non-prime using minimum increments.

---

## 📚 Related Topics

- Sieve of Eratosthenes
- Prime Numbers
- Precomputation
- Greedy Optimization  