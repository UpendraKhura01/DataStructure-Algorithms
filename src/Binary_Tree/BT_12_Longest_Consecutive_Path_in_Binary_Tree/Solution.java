package Binary_Tree.BT_12_Longest_Consecutive_Path_in_Binary_Tree;

/*
        Longest Consecutive Path in Binary tree

        Difficulty: Medium
        Given the root of a Binary Tree, find the length of the longest path consisting of connected nodes such that
        each next node has a value exactly 1 greater than its parent.

        The path must move from parent to child only and follow increasing consecutive values.

        If no such path exists, return -1.

        Examples:

        Input: root[] = [1, 2, 3]


        Output: 2
        Explanation : Longest sequence is 1, 2. So answer for this test case is 2.
        Input : root[] = [10, 20, 30, 40, N, 60, 90]

        Output : -1
        Explanation: For the above test case no sequence is possible. So output is -1.

        Constraints:

        1 ≤ no. of nodes in root ≤ 10^5
        1 ≤ root.node->data ≤ 10^5
 */
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class Solution {
    public static void main(String[] args) {

    }
    int ans = 1;
    public int longestConsecutive(Node root) {
        // code here
        dfs(root);
        return ans == 1 ? -1 : ans;

    }

    private int dfs(Node node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int curr = 1;

        if (node.left != null && node.left.data == node.data + 1) {
            curr = Math.max(curr, left + 1);
        }

        if (node.right != null && node.right.data == node.data + 1) {
            curr = Math.max(curr, right + 1);
        }

        ans = Math.max(ans, curr);

        return curr;
    }
}
