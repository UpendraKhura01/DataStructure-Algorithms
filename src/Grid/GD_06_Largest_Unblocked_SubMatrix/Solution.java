package Grid.GD_06_Largest_Unblocked_SubMatrix;

/*
        Largest Unblocked Submatrix
        Difficulty: Medium
        Given two integers n and m, and an array arr[][] of size k, where arr[i] = [r, c] represents a blocked cell (1-based indexing)
        in an n × m grid. Each blocked cell blocks its entire row and column. Find the largest continuous unblocked area in the grid.

        Note: No two blocked cells are in the same row or the same column.

        Examples:

        Input: n = 5, m = 5, arr[][] = [[2, 3], [5, 1]]
        Output: 4
        Explanation:

        - Blocked cells (2, 3) and (5, 1) block rows 2, 5 and columns 1, 3.
        - The longest unblocked row segment has length 2 (rows 3–4).
        - The longest unblocked column segment has length 2 (columns 4–5).
        - Therefore, the largest unblocked rectangle has area 2 × 2 = 4.
        Input: n = 2, m = 2, arr[][] = [[2, 2]]
        Output: 1
        Explanation: Since only (1,1) cell is free from the enemy hence answer is 1.
        Constraints:
        1 ≤ n, m ≤ 10^4
        0 ≤ k ≤ min(n, m)
        1 ≤ r ≤ n
        1 ≤ c ≤ m
 */
public class Solution {
    public static void main(String[] args) {

    }
/****************************************************Optimal Approach****************************************************/
    int largestArea(int n, int m, int[][] arr) {
        // code here
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[m];

        for (int[] a : arr) {
            int r = a[0];
            int c = a[1];
            row[r - 1] = true;
            col[c - 1] = true;
        }
        int max_row_gap = 0;
        int cur_row = 0;
        int max_col_gap = 0;
        int cur_col = 0;
        for (int i = 0; i < row.length; i++) {
            if(row[i]){
                cur_row = 0;
            }
            else{
                cur_row++;
                max_row_gap = Math.max(cur_row, max_row_gap);
            }
        }
        for (int i = 0; i < col.length; i++) {
            if(col[i]){
                cur_col = 0;
            }
            else{
                cur_col++;
                max_col_gap = Math.max(cur_col, max_col_gap);
            }
        }
        return max_col_gap * max_row_gap;
    }

}
