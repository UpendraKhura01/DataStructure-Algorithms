package Grid.GD_12_Largest_Rectangle_with_Column_Swaps;

import java.util.Arrays;

/*
        Largest Rectangle with Column Swaps
        Solved
        Difficulty: Hard
        Given a binary matrix mat[][] of size n × m containing only 0s and 1s, any pair of columns may be swapped any number of times.
        Return the maximum area of a rectangle consisting entirely of 1's that can be formed after performing the column swaps.

        Examples:

        Input: mat[][] = [[0, 1, 0, 1, 0], [0, 1, 0, 1, 1], [1, 1, 0, 1, 0]]
        Output: 6
        Explanation: After swapping the 2nd and 3rd columns, the largest rectangle of 1s has an area of 6.

        Input: mat[][] = [[0, 1, 1, 0, 0], [1, 1, 1, 0, 1], [1, 1, 1, 0, 1], [1, 1, 1, 1, 1]]
        Output: 12
        Explanation: After swapping the 4th and 5th columns, the largest rectangle of 1s has an area of 12.

        Constraints:

        1 ≤ n, m ≤ 10^3
        0 ≤ mat[i][j] ≤ 1
        mat.rows = n
        mat.cols = m
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxArea(int[][] mat) {
        // code here
        int n = mat.length;
        int m = mat[0].length;

        int[][] consecutiveOne = new int[n][m];
        for(int i = 0; i < m; i++){
            consecutiveOne[0][i] = mat[0][i];
        }

        for(int c = 0; c < m; c++){
            for(int r = 1; r < n; r++){
                if(mat[r][c] == 1){
                    consecutiveOne[r][c] += consecutiveOne[r - 1][c] + 1;
                }
            }
        }
        for(int i = 0; i < n; i++){
            Arrays.sort(consecutiveOne[i]);
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int cur = consecutiveOne[i][j] * (m - j);
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
}
