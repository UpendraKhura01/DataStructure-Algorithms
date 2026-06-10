package Grid.GD_01_MAX_Score_from_grid_operations_3225;

public class Solution {
    public static void main(String[] args) {

    }

    static long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] prefix = new long[n + 1][n + 1];

        for(int j = 1; j <= n; j++){
            for (int i = 1; i <=n ; i++) {
                prefix[i][j] = prefix[i - 1][j] + grid[i - 1][j - 1];
            }
        }


        long[][][] dp = new long[n + 1][n + 1][2];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }



        return helper(0, 0, 0, grid, prefix, dp, n);
    }

    //Memoization with DP
    static long helper(int prevused, int prevheight, int col, int[][] grid, long[][] prefix,long[][][] dp, int n){
        if(col == n){
            return 0;
        }
        if (dp[prevheight][col][prevused] != -1)
            return dp[prevheight][col][prevused];
        long ans = 0;
        for(int h = 0; h <= n; h++){
            long curscore = 0;
            long prevscore = 0;

            if(prevused == 0 && col - 1 >= 0 && h > prevheight){
                prevscore += prefix[h][col] - prefix[prevheight][col];
            }

            if(prevheight > h){
                curscore = prefix[prevheight][col + 1] - prefix[h][col + 1];
            }

            long taken = prevscore + curscore + helper(1, h, col + 1, grid, prefix, dp, n);
            long skip = prevscore + helper(0, h, col + 1, grid, prefix, dp, n);
            ans = Math.max(ans, Math.max(taken, skip));

        }
        return dp[prevheight][col][prevused] = ans;
    }

    //TABULATION
    static long maximumScore2(int[][] grid) {
        int n = grid.length;
        long[][] prefix = new long[n + 1][n + 1];

        for(int j = 1; j <= n; j++){
            for (int i = 1; i <=n ; i++) {
                prefix[i][j] = prefix[i - 1][j] + grid[i - 1][j - 1];
            }
        }

        long[][][] dp = new long[n+1][n+1][2];

        for (int r = 0; r <= n; r++) {
            dp[r][n][0] = 0;
            dp[r][n][1] = 0;
        }
        for (int col = n - 1; col >= 0; col--) {
            for (int prevheight = 0; prevheight <= n; prevheight++) {
                for (int prevused = 0; prevused < 2; prevused++) {

                    long ans = 0;
                    for(int h = 0; h <= n; h++){
                        long curscore = 0;
                        long prevscore = 0;

                        if(prevused == 0 && col - 1 >= 0 && h > prevheight){
                            prevscore += prefix[h][col] - prefix[prevheight][col];
                        }

                        if(prevheight > h){
                            curscore = prefix[prevheight][col + 1] - prefix[h][col + 1];
                        }

                        long taken = prevscore + curscore + dp[h][col + 1][1];
                        long skip = prevscore + dp[h][col + 1][0];
                        ans = Math.max(ans, Math.max(taken, skip));

                    }
                    dp[prevheight][col][prevused] = ans;
                }
            }
        }

        return dp[0][0][0];
    }
}
