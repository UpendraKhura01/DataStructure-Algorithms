package Binary_Tree.BT_21_Count_Pairs_Violating_BST_Property;

import java.util.ArrayList;
import java.util.List;
/*
        Count Pairs Violating BST Property

        Difficulty: Medium

        Given a binary tree, count pairs of nodes that violate any of the following Binary Search Tree (BST) properties.


        All values in the left subtree are strictly smaller.
        All values in the right subtree are strictly greater.
        Examples:

        Input: root[] = [10, 50, 40, N, N, 20, 30]
        2056958715
        Output: 5
        Explanation: Pairs violating BST property are:-
        (10,50), 10 should be greater than its left child value.
        (40,30), 40 should be less than its right child value.
        (50,20), (50,30) and (50,40), maximum of left subtree of 10 is 50 greater than 20, 30 and 40 of its right subtree.
        Input: root[] = [80, 40, 30, 70, N, 60, 70]
        2056958716
        Output: 9
        Explanation: There are total 9 pairs (80,30),(80,60),(80,70),(30,60),(40,70),(40,30),(70,30),(70,60),(70,70) which violate the BST properties.
        Constraints:

        1 ≤ size of binary tree ≤ 5*10^4
        1 ≤ node.data ≤ 10^9
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
    List<Integer> list;
    int n;
    int ans;
    public int pairsViolatingBST(Node root) {
        // code here
        list = new ArrayList<>();
        ans = 0;
        dfs(root);
        n = list.size();
        divide(0, n - 1);

        return ans;

    }
    void dfs(Node node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        list.add(node.data);
        dfs(node.right);
    }
    void divide(int left, int right) {
        if (left < right) {

            int mid = left + (right - left) / 2;

            divide(left, mid);
            divide(mid + 1, right);
            merge(left, mid, right);
        }
    }

    void merge(int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i = 0; i < n1; i++){
            left[i] = list.get(l + i);
        }
        for(int i = 0; i < n2; i++){
            right[i] = list.get(m + i + 1);
        }

        int i = 0;
        int j = 0;

        int k = l;

        while(i < n1 && j < n2){
            if(left[i] < right[j]){
                list.set(k++, left[i++]);
            }
            else if(right[j] < left[i]){
                ans += (n1 - i);
                list.set(k++, right[j++]);
            }

        }
        while(i < n1){
            list.set(k++, left[i++]);
        }
        while(j < n2){
            list.set(k++, right[j++]);
        }

    }
}
