package Binary_Tree.BT_14_Construct_a_Full_Binary_Tree;

import java.util.HashMap;
/*
        Construct a Full Binary Tree
        Solved
        Difficulty: Medium
        Given two arrays pre[] and preMirror[] of size n containing unique elements,
        where pre[] represents the preorder traversal of a full binary tree and preMirror[] represents the preorder traversal
        of its mirror tree, construct the original full binary tree using these traversals.

        Note: A general binary tree cannot be uniquely constructed using these two traversals. However,
        a full binary tree can be constructed uniquely from the given traversals without any ambiguity.

        Examples:

        Input: pre[] = [0,1,2], preMirror[] = [0,2,1]
        Output: [0, 1, 2]
        Explanation: The tree will look like
                0
               / \
              1   2


        Input: pre[] = [1, 2, 4, 5, 3, 6, 7], preMirror[] = [1, 3, 7, 6, 2, 5, 4]
        Output: [1, 2, 4, 5, 3, 6, 7]
        Explanation: The tree will look like
                    1
                  /   \
                 2     3
                / \   / \
               4   5 6   7
        Constraints:

        1 ≤ pre.size() ≤ 10^5
        0 ≤ pre[i] ≤ 10^9
        1 ≤ preMirror.size() ≤ 10^5
        0 ≤ preMirror[i] ≤ 10^9

 */
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    int preIdx = 0;
    int n;
    int[] pre;
    int[] preMirror;
    HashMap<Integer, Integer> pos;
    public Node constructBinaryTree(int[] pre, int[] preMirror) {
        // code here
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
        if (left > right) {
            return null;
        }

        Node root = new Node(pre[preIdx++]);

        if (left == right) {
            return root;
        }
        int newidx = pos.get(pre[preIdx]);

        root.left = solve(newidx, right);
        root.right = solve(left + 1, newidx - 1);

        return root;
    }
}
