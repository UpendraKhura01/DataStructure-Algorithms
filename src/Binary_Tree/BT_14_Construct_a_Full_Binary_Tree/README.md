
# Construct a Full Binary Tree

---

# Problem Statement

Given two arrays:

- `pre[]` → Preorder traversal of a **Full Binary Tree**
- `preMirror[]` → Preorder traversal of the **Mirror** of the same tree

Construct and return the original **Full Binary Tree**.

### Note

A general binary tree **cannot** be uniquely reconstructed using these traversals.

However, because the tree is **Full** (every node has either 0 or 2 children), the reconstruction is **unique**.

---

### Example 1

```text
Input

pre       = [0,1,2]
preMirror = [0,2,1]

Output

        0
       / \
      1   2
```

---

### Example 2

```text
Input

pre       = [1,2,4,5,3,6,7]
preMirror = [1,3,7,6,2,5,4]

Output

            1
          /   \
         2     3
        / \   / \
       4   5 6   7
```

---

# Goal

Reconstruct the original Full Binary Tree using only:

- Preorder traversal
- Mirror preorder traversal

without constructing any additional traversals.

---

# Intuition

Recall the preorder traversal:

```text
Root → Left → Right
```

The mirror tree follows:

```text
Root → Right → Left
```

Suppose

```text
Preorder

A B C D ...

Mirror Preorder

A X Y Z ...
```

After creating root `A`,

the next preorder node (`B`) must belong to the **left subtree**.

Where does this node appear inside the mirror preorder?

Its position tells us exactly where the mirror traversal splits into

- Left subtree
- Right subtree

Because the tree is **Full**, every internal node has exactly two children.

Hence the subtree boundaries become uniquely identifiable.

---

# Key Idea

Instead of searching repeatedly inside `preMirror`, create a map:

```text
Node Value
      ↓
Index in preMirror
```

```text
HashMap<Integer,Integer>
```

Then:

1. Create the root using preorder.
2. The next preorder element is the left child.
3. Find that node inside `preMirror`.
4. Use its position to determine subtree boundaries.
5. Recursively build:
    - Left subtree
    - Right subtree

Each node is created exactly once.

---

# Thought Process

### Step 1

Store every value's position inside `preMirror`.

Example

```text
preMirror

1 3 7 6 2 5 4

Map

1 → 0
3 → 1
7 → 2
6 → 3
2 → 4
5 → 5
4 → 6
```

---

### Step 2

Maintain one preorder pointer.

```text
preIdx
```

Every recursive call creates

```text
pre[preIdx]
```

as the current root.

---

### Step 3

If only one node exists,

```text
left == right
```

then it is a leaf node.

Return it immediately.

---

### Step 4

Otherwise,

the next preorder element

```text
pre[preIdx]
```

must be the root of the left subtree.

Find its position inside `preMirror`.

```text
newIdx = position(pre[preIdx])
```

---

### Step 5

Recursively construct:

```text
Left subtree

solve(newIdx, right)
```

and

```text
Right subtree

solve(left + 1, newIdx - 1)
```

Finally connect both children.

---

# Code (Functions Only)

```java
int preIdx = 0;
int n;
int[] pre;
int[] preMirror;
HashMap<Integer, Integer> pos;

public Node constructBinaryTree(int[] pre, int[] preMirror) {

    this.n = pre.length;
    this.pre = pre;
    this.preMirror = preMirror;

    pos = new HashMap<>();

    for (int i = 0; i < n; i++) {
        pos.put(preMirror[i], i);
    }

    return solve(0, n - 1);
}

private Node solve(int left, int right) {

    if (left > right)
        return null;

    Node root = new Node(pre[preIdx++]);

    if (left == right)
        return root;

    int newIdx = pos.get(pre[preIdx]);

    root.left = solve(newIdx, right);

    root.right = solve(left + 1, newIdx - 1);

    return root;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
pre

[1,2,4,5,3,6,7]

preMirror

[1,3,7,6,2,5,4]
```

---

### Initial State

```text
preIdx = 0
```

Create root

```text
1
```

---

### Next preorder node

```text
2
```

Inside mirror preorder,

```text
2 is at index 4
```

Split recursively.

---

### Build Left Subtree

Create

```text
2
```

Next preorder

```text
4
```

Mirror index

```text
6
```

Create

```text
4
```

Leaf reached.

Backtrack.

Create

```text
5
```

Leaf reached.

Subtree becomes

```text
      2
     / \
    4   5
```

---

### Build Right Subtree

Next preorder

```text
3
```

Next preorder

```text
6
```

Mirror position determines split.

Create

```text
6
```

Leaf.

Create

```text
7
```

Leaf.

Subtree becomes

```text
      3
     / \
    6   7
```

---

### Final Tree

```text
            1
          /   \
         2     3
        / \   / \
       4   5 6   7
```

---

# Logic Flow

```text
Start

↓

Create HashMap
(value → index in mirror preorder)

↓

Initialize preorder pointer

↓

Create current root

↓

Leaf node?

↓

Yes → Return node

↓

No

↓

Find next preorder node's position
inside mirror preorder

↓

Recursively build left subtree

↓

Recursively build right subtree

↓

Attach children

↓

Return root

↓

End
```

---

# Complexity

## Time Complexity

Building HashMap:

```text
O(N)
```

Each node is processed exactly once:

```text
O(N)
```

Overall:

```text
O(N)
```

---

## Space Complexity

HashMap:

```text
O(N)
```

Recursive call stack:

```text
O(H)
```

where `H` is the height of the tree.

Worst case:

```text
O(N)
```

Balanced Full Binary Tree:

```text
O(log N)
```

Overall auxiliary space:

```text
O(N)
```

---

# Key Takeaways

- A Full Binary Tree can be uniquely reconstructed using preorder and mirror preorder traversals.
- The next node in preorder is always the root of the left subtree.
- A HashMap allows constant-time lookup of node positions in the mirror traversal.
- Recursive subtree boundaries are determined using the mirror preorder indices.
- Every node is created exactly once, resulting in an optimal linear-time solution.

---

# Most Important Insight

The uniqueness comes from the **Full Binary Tree property**. After creating a root from the preorder traversal, the next preorder element is guaranteed to belong to the left subtree. Its position in the mirror preorder precisely identifies the subtree boundaries, allowing the entire tree to be reconstructed uniquely without ambiguity.

---

# Summary

The algorithm reconstructs the Full Binary Tree by combining preorder traversal with its mirror preorder traversal. A HashMap stores each node's position in the mirror traversal for constant-time lookups, while a preorder pointer sequentially creates nodes. Using the position of the next preorder node in the mirror traversal, the algorithm recursively determines the left and right subtree boundaries. Since every node is processed exactly once, the solution achieves **O(N)** time complexity with **O(N)** auxiliary space, making it both elegant and optimal.
