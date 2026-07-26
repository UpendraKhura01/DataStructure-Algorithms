package Binary_Tree.BT_13_Complete_Binary_Tree_Traversal_With_Array_Input;

import java.util.ArrayList;
import java.util.Comparator;

/*
        Complete Binary Tree Traversal with Array Input

        Difficulty: Medium
        Given an integer array arr[] representing the nodes of a Complete Binary Tree in level order traversal,
        return the nodes at each level in sorted ascending order.

        For every level of the binary tree, sort the values present at that level independently and
        return the resulting levels as a 2D array, where the i-th row contains the sorted values of the i-th level.

        Examples:

        Input: arr[] = [7, 6, 5, 4, 3, 2, 1]
        Output: [[7], [5, 6], [1, 2, 3, 4]]
        Explanation: The complete binary tree formed from the given level order traversal is:

        The nodes at each level after sorting are:
        Level 0: [7]
        Level 1: [5, 6]
        Level 2: [1, 2, 3, 4]
        Input: arr[] = [7, 16, 1, 4, 13]
        Output: [[7], [1, 16], [4, 13]]
        Explanation: The complete binary tree formed from the given level order traversal is:

        The nodes at each level after sorting are:
        Level 0: [7]
        Level 1: [1, 16]
        Level 2: [4, 13]

        Constraints:

        1 ≤ arr.size() ≤ 10^4
        1 ≤ arr[i] ≤ 10^9
 */
public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<ArrayList<Integer>> levelSort(int[] arr) {
        // code here
        int n = arr.length;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int size = 1;
        int idx = 0;

        while(idx < n){
            int temp = size;
            ArrayList<Integer> list = new ArrayList<>();

            while(temp-- > 0 && idx < n){
                list.add(arr[idx++]);
            }

            list.sort(Comparator.naturalOrder());

            ans.add(list);
            size = size * 2;
        }

        return ans;
    }
}
