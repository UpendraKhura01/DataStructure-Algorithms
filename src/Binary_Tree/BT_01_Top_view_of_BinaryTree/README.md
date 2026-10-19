# Top View of Binary Tree

This repository contains a Java solution to print the **top view** of a binary tree.  
The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.

In the top view, only the first node at each vertical distance from the root is visible.

---

## Problem Statement

Given a binary tree, return an `ArrayList` of integers representing the **top view** of the tree from left to right.

**Example:**  
```
    1
  /   \
 2     3
  \
   4
    \
     5
      \
       6
```

---


**Top view output:** `[2, 1, 3, 6]`

---

## Approach

1. **Breadth-First Search (BFS)**
    - Perform a level-order traversal using a queue to visit nodes level by level.

2. **Track Vertical Distance (VD)**
    - Assign each node a vertical distance relative to the root:
        - Root node → VD = 0
        - Left child → VD = parent VD - 1
        - Right child → VD = parent VD + 1

3. **Map to store first node at each VD**
    - Use a `TreeMap<Integer, Integer>`:
        - Key → vertical distance
        - Value → node’s data
    - `putIfAbsent()` ensures only the first node at each vertical distance is stored.

4. **Return top view**
    - Traverse the `TreeMap` from leftmost to rightmost VD to collect the top view nodes.

---

## Java Code

```java
class String.S_16_Max_Digit_Sum_Number_in_1_to_N.Solution {
    static ArrayList<Integer> topView(Node root) {
        // code here
        Queue<pair> q=new LinkedList<>();
        int vertical=0;
        q.add(new pair(root, vertical));
       /*
       we are storing the Vertical Distance-> VD in the key section of the map because we want to have only one value per VD.
       and we are storing the Node data in the value section.
        */
        Map<Integer,Integer> mp=new TreeMap<>();
        while(!q.isEmpty()){
            int s=q.size();
            for(int i=0;i<s;i++){
                pair pair=q.poll();

                mp.putIfAbsent(pair.second,pair.first.data);
                /*pair stores node in first and vertical distance in second
                so when we go to left we should decrease the VD and when we go to right we should increase it.

                 */

                if(pair.first.left!=null) q.add(new pair(pair.first.left,pair.second-1));
                if(pair.first.right!=null) q.add(new pair(pair.first.right, pair.second+1));

            }
        }

        return new ArrayList<>(mp.values());
    }

    static class pair {
        Node first;//stores the node
        int second;//stores the vertical distance

        pair(Node first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
```

---

## Classes Used

- **Node:** Represents a node in the binary tree.

- **pair:** Custom class that stores:
    - `first` → Node
    - `second` → Vertical distance (VD)

---

## Complexity Analysis

- **Time Complexity:** `O(N)` where `N` is the number of nodes (each node is processed once).
- **Space Complexity:** `O(N)` for queue and map storage.

---

## Notes

- BFS ensures nodes are processed level by level, so the first node at each vertical distance is always chosen.
- `TreeMap` automatically sorts vertical distances, so output is from leftmost to rightmost node.
- Using `putIfAbsent()` guarantees only the topmost node is selected for each vertical line.

---