package Grid.GD_13_Minimum_Cost_Selection;

import java.util.Arrays;
/*
        Minimum Cost Selection
        Solved
        Difficulty: Medium
        Given an n × 3 matrix mat[][], where each row represents the costs of three available choices at a shop, select exactly one choice from each row such that the same choice is not selected in two adjacent rows.

        Return the minimum total cost required.

        Examples:

        Input: mat[][] = [[1, 50, 50], [50, 50, 50], [1, 50, 50]]
        Output: 52
        Explanation: One optimal selection is- Row 1: Choice 1 (Cost = 1), Row 2: Choice 2 (Cost = 50), Row 3: Choice 1 (Cost = 1)
        Total cost = 1 + 50 + 1 = 52.
        Input: mat[][] = [[1, 4, 1], [3, 2, 2], [3, 2, 3]]
        Output: 5
        Explanation: One optimal selection is- Row 1: Choice 1 (Cost = 1), Row 2: Choice 2 (Cost = 2), Row 3: Choice 3 (Cost = 2)
        Total cost = 1 + 2 + 2 = 5.
        Constraints:

        1 ≤ n ≤ 105
        3 ≤ mat[0].size() ≤ 3
        1 ≤ mat[i][j] ≤ 100
        mat.rows = n
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int[][] mat;
    int[][] dp;
    public int minCost(int[][] mat) {
        // code here
        n = mat.length;
        this.mat = mat;
        dp = new int[n + 1][4];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(0, -1);
    }
    private int solve(int row, int prevcol){

        if(row == n){
            return 0;
        }

        if(prevcol != -1 && dp[row][prevcol] != -1){

            return dp[row][prevcol];
        }

        int min = Integer.MAX_VALUE;

        for(int col = 0; col < 3; col++){
            if(col == prevcol){
                continue;
            }
            int sum = mat[row][col] + solve(row + 1, col);
            min = Math.min(min, sum);
        }
        if(prevcol != -1){
            dp[row][prevcol] = min;
        }
        return min;
    }
}
