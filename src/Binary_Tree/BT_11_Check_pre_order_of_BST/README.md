# Check Preorder of BST

**Difficulty:** Medium

## 🧩 Problem Statement

Given an array `arr[]` consisting of **distinct integers**, determine whether it can represent the **preorder traversal** of a **Binary Search Tree (BST)**.

Recall that in a BST:

- Left subtree contains only smaller values.
- Right subtree contains only larger values.

Return:

- `true` if the array can represent a BST preorder traversal.
- `false` otherwise.

---

## 📌 Examples

### Example 1

**Input**

```text
arr = [2, 4, 3]
```

**Output**

```text
true
```

**Explanation**

The preorder corresponds to the BST:

```text
    2
     \
      4
     /
    3
```

Preorder traversal:

```text
2 → 4 → 3
```

---

### Example 2

**Input**

```text
arr = [2, 4, 1]
```

**Output**

```text
false
```

**Explanation**

After visiting node `4`, we are already in the **right subtree** of `2`.

The value `1` appears afterward, but it is smaller than `2`, which violates BST properties.

---

## 🔍 Approach (Stack + Lower Bound)

The preorder traversal follows this order:

```
Root
Left Subtree
Right Subtree
```

We simulate the preorder traversal using a stack.

### Key Idea

Maintain:

- A **stack** of ancestors.
- A variable `lowerBound` representing the smallest value allowed for future nodes.

Initially:

```
lowerBound = -∞
```

For every node:

1. If `node < lowerBound`
    - BST property is violated.
    - Return `false`.

2. While stack is not empty and

```
node > stack.top()
```

it means we are moving from a left subtree to a right subtree.

Pop elements and update

```
lowerBound = popped value
```

3. Push the current node.

If every node satisfies the constraints, return `true`.

---

## ▶ Dry Run

### Input

```text
arr = [2,4,3]
```

Start:

```
stack = []
lowerBound = -∞
```

### Visit 2

```
push 2

stack = [2]
```

---

### Visit 4

```
4 > 2

pop 2
lowerBound = 2

push 4

stack = [4]
```

---

### Visit 3

```
3 > lowerBound (2) ✔

3 < stack.top (4)

push 3

stack = [4,3]
```

Traversal ends.

Answer:

```
true
```

---

### Invalid Example

```text
arr = [2,4,1]
```

After processing:

```
lowerBound = 2
```

Next node:

```
1 < 2
```

Violation.

Answer:

```
false
```

---

## ⏱ Complexity Analysis

### Time Complexity

```text
O(n)
```

Each element is pushed and popped at most once.

---

### Space Complexity

```text
O(n)
```

For the stack.

---

## ✅ Java Solution

```java
import java.util.List;
import java.util.Stack;

public class Solution {

    boolean canRepresentBST(List<Integer> arr) {

        Stack<Integer> stack = new Stack<>();

        int lowerBound = -1;

        for (int node : arr) {

            if (node < lowerBound)
                return false;

            while (!stack.isEmpty() && node > stack.peek()) {
                lowerBound = stack.pop();
            }

            stack.push(node);
        }

        return true;
    }
}
```

---

## 💡 Key Idea

- The stack stores the path from the root to the current node.
- Whenever a larger value is encountered, we move to the **right subtree**, updating the minimum allowed value (`lowerBound`).
- Any future node smaller than this bound violates the BST preorder property.

This greedy stack-based simulation verifies the preorder in **O(n)** time.