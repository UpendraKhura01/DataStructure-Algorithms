# 2196. Create Binary Tree From Descriptions

## Approach
Use a **HashMap** to create and store a unique `TreeNode` for every value and a **HashSet** to record all nodes that appear as children.

- For every description `[parent, child, isLeft]`:
    - Create the parent and child nodes if they do not already exist.
    - Connect the child as either the left or right child of the parent.
    - Add the child value to the `HashSet`.
- After processing all descriptions, the **root** is the only node that never appears as a child.
- Return that node.

---

## Algorithm
1. Initialize:
    - `HashMap<Integer, TreeNode>` to store nodes.
    - `HashSet<Integer>` to store child node values.
2. Traverse each description:
    - Create parent and child nodes using `putIfAbsent()`.
    - Attach the child to the parent based on `isLeft`.
    - Insert the child value into the set.
3. Iterate through all nodes in the map.
4. The node not present in the child set is the root.
5. Return the root.

---

## Correctness
- Every node is created exactly once because of `putIfAbsent()`.
- Every child is recorded in the `HashSet`.
- In a valid binary tree, every node except the root has exactly one parent.
- Therefore, the only node that never appears as a child is the root.
- Since the tree is guaranteed to be valid, exactly one such node exists.

Hence, the algorithm always returns the correct root.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
    - One traversal to build the tree.
    - One traversal to identify the root.

- **Space Complexity:** `O(n)`
    - `HashMap` stores all nodes.
    - `HashSet` stores all child nodes.

---

## Java Solution

```java
class Solution {

    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            if (isLeft == 1)
                map.get(parent).left = map.get(child);
            else
                map.get(parent).right = map.get(child);

            children.add(child);
        }

        for (int node : map.keySet()) {
            if (!children.contains(node))
                return map.get(node);
        }

        return null;
    }
}
```