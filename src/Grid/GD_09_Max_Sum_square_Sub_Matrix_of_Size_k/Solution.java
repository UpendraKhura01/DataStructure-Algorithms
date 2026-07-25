package Grid.GD_09_Max_Sum_square_Sub_Matrix_of_Size_k;

/*
        Max Sum Square Sub-Matrix of Size k

        Difficulty: Medium
        Given a n × n grid mat[][] of integers where values can be negative, find the maximum sum among all possible k × k sub-grids.

        Examples:

        Input: k = 3, mat[][] = [[1, 2, -1, 4], [-8, -3, 4, 2], [3, 8, 10, -8], [-4, -1, 1, 7]]
        ┌────┬────┬────┬────┐
        │  1 │  2 │ -1 │  4 │
        ├────┼────┼────┼────┤
        │ -8 │ -3 │  4 │  2 │
        ├────┼────┼────┼────┤
        │  3 │  8 │ 10 │ -8 │
        ├────┼────┼────┼────┤
        │ -4 │ -1 │  1 │  7 │
        └────┴────┴────┴────┘
        Output: 20
        Explanation: The 3 × 3 sub-grid [[-3, 4, 2], [8, 10, -8], [-1, 1, 7]] highlighted in red has the maximum sum of 20.



        Input: k = 1, mat[][] = [[4]]
        Output: 4
        Explanation: Only one 1×1 sub-grid exists with sum 4.

        Constraints:

        1 ≤ n ≤ 1000
        1 ≤ k ≤ n
        -1000 ≤ mat[i][j] ≤ 1000
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maximumSum(int[][] mat, int k) {
        // code here
        int n = mat.length;

        int[][] pref = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                pref[i][j] = mat[i - 1][j - 1] +
                        pref[i - 1][j] + pref[i][j - 1]-pref[i - 1][j - 1];

            }
        }

        int ans = pref[k][k];

        for (int i = k; i <= n; i++) {
            for (int j = k; j <= n; j++) {

                int sum = pref[i][j] - pref[i - k][j] - pref[i][j - k] + pref[i - k][j - k];
                ans = Math.max(sum, ans);
            }
        }

        return ans;

    }
}
