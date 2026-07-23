package Binary_Tree.Check_pre_order_of_BST;

import java.util.List;
import java.util.Stack;
/*
        Check Preorder of BST
        Solved
        Difficulty: MediumAccuracy: 36.84%Submissions: 50K+Points: 4Average Time: 20m
        Given an array arr[ ] consisting of distinct integers, check if the given array can represent preorder traversal of a BST.

        Examples :

        Input: arr[] = [2, 4, 3]
        Output: true
        Explaination: Given arr[] can represent preorder traversal of following BST:

        Input: arr[] = [2, 4, 1]
        Output: false
        Explaination: Given arr[] cannot represent preorder traversal of a BST.
        Constraints:
        1 ≤ arr.size() ≤ 10^5
        0 ≤ arr[i] ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    boolean canRepresentBST(List<Integer> arr) {
        // code here
        int n = arr.size();

        Stack<Integer> st = new Stack<>();

        int limit = -1;

        for(int i = 0; i < n; i++){

            int node = arr.get(i);

            if(node < limit){
                return false;
            }

            while(!st.empty() && node > st.peek()){
                limit = st.peek();
                st.pop();
            }
            st.push(node);
        }
        return true;
    }
}
