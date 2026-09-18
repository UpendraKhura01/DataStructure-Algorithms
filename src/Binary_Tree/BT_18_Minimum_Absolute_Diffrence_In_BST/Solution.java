package Binary_Tree.BT_18_Minimum_Absolute_Diffrence_In_BST;


/*
        Minimum Absolute Difference In BST
        Solved
        Difficulty: Medium
        Given the root of a Binary Search Tree (BST) containing n (n > 1) nodes,
        find the minimum absolute difference between the values of any two different nodes in the tree.

        Return the minimum absolute difference.

        Examples:

        Input: root[] = [50, 30, 70, 20, N, 60, 80]
        420046990
        Output: 10
        Explanation: There are no two nodes whose absolute difference is smaller than 10.
        Input: root[] = [60, 30, 90, 10]
        2056957925
        Output: 20
        Explanation: There are no two nodes whose absolute difference is smaller than 20.
        Constraints:

        2 ≤ size of binary tree ≤ 10^5
        0 ≤ node.data ≤ 10^6
 */
class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    int ans = Integer.MAX_VALUE;
    int prev = -1;

    public int absDiff(Node root) {
        // code here
        solve(root);
        return ans;
    }
    private void solve(Node node){
        if(node == null){
            return;
        }

        solve(node.left);

        int cur = node.data;

        if(prev != -1){
            ans = Math.min(ans, Math.abs(cur - prev));
        }
        prev = cur;
        solve(node.right);
    }
}
