package HashMap.HM_04_Create_Binary_Tree_from_description_2196;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
        2196. Create Binary Tree From Descriptions
        Medium

        You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi,
        isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

        If isLefti == 1, then childi is the left child of parenti.
        If isLefti == 0, then childi is the right child of parenti.
        Construct the binary tree described by descriptions and return its root.

        The test cases will be generated such that the binary tree is valid.


        Example 1:
                50
               /  \
             20    80
            /  \   /
          15   17 19


        Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
        Output: [50,20,80,15,17,19]
        Explanation: The root node is the node with value 50 since it has no parent.
        The resulting binary tree is shown in the diagram.


        Example 2:
                                1
                               /
                              2
                               \
                                3
                               /
                              4

        Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
        Output: [1,2,null,null,3,4]
        Explanation: The root node is the node with value 1 since it has no parent.
        The resulting binary tree is shown in the diagram.


        Constraints:

        1 <= descriptions.length <= 10^4
        descriptions[i].length == 3
        1 <= parenti, childi <= 10^5
        0 <= isLefti <= 1
        The binary tree described by descriptions is valid.
 */

public class Solution {
    public static void main(String[] args) {

    }

    // Definition for a binary tree node.
     class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
    TreeNode createBinaryTree(int[][] descriptions) {
        int n = descriptions.length;
        Map<Integer, TreeNode> mp = new HashMap<>();
        Set<Integer> st = new HashSet<>();
        for(int i = 0; i < n; i++){
            int parent = descriptions[i][0];
            int child = descriptions[i][1];
            int ischild = descriptions[i][2];
            mp.putIfAbsent(parent, new TreeNode(parent));
            mp.putIfAbsent(child, new TreeNode(child));

            if(ischild == 0){
                mp.get(parent).right = mp.get(child);
            }
            else if(ischild == 1){
                mp.get(parent).left = mp.get(child);
            }
            st.add(child);
        }
        for(int i : mp.keySet()){
            if(!st.contains(i)){
                return mp.get(i);
            }
        }
        return null;
    }
}
