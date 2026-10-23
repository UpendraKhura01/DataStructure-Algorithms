package Grid.GD_22_Longest_Increasing_Path_In_a_matrix;

/*
        Longest Increasing Path in Matrix

        Difficulty: Hard
        Given a 2d matrix mat[][], find the length of the longest path such that:

        The path can start and end at any cell.
        A cell cannot be visited more than once.
        The values in path are strictly increasing.
        From each cell,  you can move left, right, up, or down.
        Diagonal moves and moves outside the matrix are not allowed.
        Examples:

        Input: mat[][] = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        Output: 5
        Explanation: One such path is 1 -> 2 -> 3 -> 6 -> 9, where each number is strictly greater than the previous.

        Input: mat[][] = [[3, 4, 5], [6, 2, 6], [2, 2, 1]]
        Output: 4
        Explanation: One of the longest increasing paths is 3 -> 4 -> 5 -> 6.

        Input: mat[][] = [[1, 1], [1, 1]]
        Output: 1
        Explanation: There can at most one vertex as all vertices are same.
        Constraints:

        1 ≤ mat.size(), mat[i].size() ≤ 10^3
        0 ≤ mat[i][j] ≤ 2^30
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int m;

    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int[][] mat;
    int[][] dp;
    public int longIncPath(int[][] mat, int n, int m) {
        // code here
        this.n = n;
        this.m = m;
        this.mat = mat;
        int ans = 0;
        this.dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int cur = solve(i, j);

                ans = Math.max(ans, cur);
            }
        }


        return ans;
    }
    private int solve(int r, int c) {
        if(dp[r][c] != 0){
            return dp[r][c];
        }
        int cur = mat[r][c];


        int max = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nc >= 0 && nr < n && nc < m && mat[nr][nc] > cur){
                int trav = 1 + solve(nr, nc);

                max = Math.max(max, trav);
            }

        }


        return dp[r][c] = max;
    }
}
