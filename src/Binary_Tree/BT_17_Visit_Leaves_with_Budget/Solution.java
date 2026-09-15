package Binary_Tree.BT_17_Visit_Leaves_with_Budget;

import java.util.ArrayDeque;
import java.util.Queue;

/*
        Visit Leaves with Budget
        Solved
        Difficulty: Easy
        Given a binary tree and an integer k, where you start from the root at level 1. The cost of visiting a leaf node
         is equal to the level of that leaf node. You can visit any number of leaf nodes,
         but the total cost of visiting them must not exceed k.

        Return the maximum number of leaf nodes that can be visited within the given budget.

        Examples:

        Input: root[] = [10, 8, 2, 3, N, 3, 6, N, N, N, 4], k = 8

        Output: 2
        Explanation:
        Cost For visiting Leaf Node 3: 3
        Cost For visiting Leaf Node 4: 4
        Cost For visiting Leaf Node 6: 3
        To maximize the number of visited leaves, choose the two cheapest leaves: Cost = 3 + 3 = 6 ≤ 8.
        Thus, the maximum number of leaf nodes that can be visited is 2.
        Input: root[] = [1, 2, 3, 4, 5, 6, 7], k = 5

        Output: 1
        Explanation: The leaf nodes are 4, 5, 6 and 7, and all are at level 3. Therefore, visiting each leaf costs 3.
        With a budget of 5, we can visit only one leaf because: 3 ≤ 5, but 3 + 3 > 5. Thus,
        the maximum number of leaf nodes that can be visited is 1.

        Input: root[] = [1], k = 1
        Output: 1
        Explanation: The root node is also a leaf node and is at level 1. Therefore, its visiting cost is 1. Thus,
        the maximum number of leaf nodes that can be visited is 1.
        Constraints:

        1 ≤ size of binary tree ≤ 10^5
        1 ≤ k ≤ 10^4
 */
class Node {
    int data;
    Node left, right;

    public Node(int data){
        this.data = data;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    public int getCount(Node root, int k) {
        // code here
        int ans = 0;
        Queue<pair> q = new ArrayDeque<>();
        q.offer(new pair(root, 1));

        while(!q.isEmpty()){
            pair cur = q.poll();
            Node node = cur.node();
            int level = cur.level();

            if(node.left == null && node.right == null){
                if(k >= level){
                    ans++;
                    k -= level;
                }
                else{
                    break;
                }
            }
            else{
                if(node.left != null){
                    q.offer(new pair(node.left, level + 1));
                }
                if(node.right != null){
                    q.offer(new pair(node.right, level + 1));
                }
            }
        }
        return ans;
    }
    record pair(Node node, int level){};
}
