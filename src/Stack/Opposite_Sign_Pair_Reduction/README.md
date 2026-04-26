# 🔥 Opposite Sign Pair Reduction (Stack)

## 📌 Problem Statement

Given an integer array `arr[]`, reduce elements based on opposite signs.

Two elements interact only when:

    They have opposite signs

---

## ⚔️ Reduction Rules

When current element meets stack top:

1. If absolute values are equal:

       Both are removed

2. If stack top has greater absolute value:

       Current element is removed

3. If current element has greater absolute value:

       Stack top is removed
       Current element keeps checking with previous elements

---

## 🎯 Goal

Return the final remaining elements after all reductions.

---

# 💡 Intuition

This problem behaves like a collision problem.

👉 The most recent unresolved element is important.

So we use:

    Stack

---

# 🧠 Thought Process

For each element:

1. Compare it with stack top
2. Only reduce if signs are opposite
3. The element with smaller absolute value disappears
4. If current survives, it may collide again with next stack top
5. Push current only if it survives all collisions

---

# 🔥 Why Stack?

Because collisions happen with the nearest previous unresolved element.

Stack gives:

    Last unresolved element → stack.peek()

---

# 🚀 Code (Functions Only)

```java
static ArrayList<Integer> reducePairs(int[] arr) {

    Stack<Integer> stack = new Stack<>();

    for (int ele1 : arr) {

        boolean flag = true;

        while (!stack.isEmpty() && !samesign(ele1, stack.peek())) {

            int top = stack.peek();

            if (Math.abs(top) == Math.abs(ele1)) {
                stack.pop();
                flag = false;
                break;
            }

            else if (Math.abs(top) > Math.abs(ele1)) {
                flag = false;
                break;
            }

            else {
                stack.pop();
            }
        }

        if (flag) {
            stack.push(ele1);
        }
    }

    return new ArrayList<>(stack);
}

static boolean samesign(int a, int b) {
    return (a >= 0 && b >= 0) || (a < 0 && b < 0);
}
```

---

# 🧪 Dry Run

Input:

    arr = [10, -5, -8, 2, -5]

---

## Step 1

Current:

    10

Stack empty → push

    stack = [10]

---

## Step 2

Current:

    -5

Compare with top:

    10 and -5 → opposite signs

Absolute values:

    |10| > |5|

So current `-5` is removed.

    stack = [10]

---

## Step 3

Current:

    -8

Compare with top:

    10 and -8 → opposite signs

Absolute values:

    |10| > |8|

So current `-8` is removed.

    stack = [10]

---

## Step 4

Current:

    2

Compare with top:

    10 and 2 → same sign

No collision → push

    stack = [10, 2]

---

## Step 5

Current:

    -5

Compare with top:

    2 and -5 → opposite signs

Absolute values:

    |2| < |5|

So stack top `2` is removed.

    stack = [10]

Current `-5` still survives, so compare again.

Compare with top:

    10 and -5 → opposite signs

Absolute values:

    |10| > |5|

So current `-5` is removed.

    stack = [10]

---

## Final Output

    [10]

---

# 🔁 Collision Flow

    Current element enters
        ↓
    Compare with stack top
        ↓
    Same sign?
        → push
        ↓
    Opposite sign?
        → smaller absolute value removed
        ↓
    Current may continue fighting

---

# 📊 Complexity

Time:

    O(n)

Space:

    O(n)

---

# 🎯 Key Takeaways

- Use stack for nearest unresolved collision
- Compare only opposite signs
- Use absolute value to decide winner
- Current element may remove multiple stack elements

---

# 🏁 Summary

This is a stack-based collision simulation.

At every step:

    If signs are same → push
    If signs are opposite → reduce by absolute value