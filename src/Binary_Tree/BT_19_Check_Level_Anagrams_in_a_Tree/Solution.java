package Binary_Tree.BT_19_Check_Level_Anagrams_in_a_Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/*
        Check Level Anagrams in Binary Trees

        Difficulty: Medium
        Given the roots of two binary trees root1 and root2, check whether the nodes at every corresponding level of
        the two trees are anagrams of each other.

        Two levels are considered anagrams if they contain the same node values with the same frequencies,
        regardless of their order.

        Examples:

        Input: root1 = [1, 3, 2, N, N, 5, 4], root2 = [1, 2, 3, 4, 5, N, N]

        Output: true
        Explanation:
        Level 0: [1] and [1]
        Level 1: [3, 2] and [2, 3]
        Level 2: [5, 4] and [4, 5]
        The node values at every corresponding level are anagrams of each other. Hence, the answer is true.
        Input: root1 = [1, 2, 3, 5, 4], root2 = [1, 2, 4, 5, 3]

        Output: false
        Explanation:
        Level 0: [1] and [1]
        Level 1: [2, 3] and [2, 4]
        Since the node values at level 1 are not anagrams, the answer is false.
        Constraints:

        1 ≤ size of binary tree ≤ 10^5
        1 ≤ node.data ≤ 10^6
        The character N represents a null child.


 */

class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    boolean areAnagrams(Node root1, Node root2) {
        // code here
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();

        q1.add(root1);
        q2.add(root2);

        while(!q1.isEmpty() && !q2.isEmpty()){
            int n1 = q1.size();
            int n2 = q2.size();

            if(n1 != n2){
                return false;
            }
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();

            for(int i = 0; i < n1; i++){
                Node cur = q1.poll();
                list1.add(cur.data);

                if(cur.left != null){
                    q1.add(cur.left);
                }
                if(cur.right != null){
                    q1.add(cur.right);
                }
            }

            for(int i = 0; i < n1; i++){
                Node cur = q2.poll();
                list2.add(cur.data);

                if(cur.left != null){
                    q2.add(cur.left);
                }
                if(cur.right != null){
                    q2.add(cur.right);
                }
            }
            Collections.sort(list1);
            Collections.sort(list2);
            if(!list1.equals(list2)){
                return false;
            }
        }

        return q1.isEmpty() && q2.isEmpty();
    }
}
