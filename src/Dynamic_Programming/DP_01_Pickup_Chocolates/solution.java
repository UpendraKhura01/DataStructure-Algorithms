package Dynamic_Programming.DP_01_Pickup_Chocolates;

public class solution {
    public static void main(String[] args) {
        int[][] grid = {
                {4, 1, 2},
                {3, 6, 1},
                {1, 6, 6},
                {3, 1, 2}
        };
        System.out.println("ans is "+maxChocolate(grid));

    }
    static int maxChocolate(int grid[][]) {
        // code here
        int n = grid.length;
        int m = grid[0].length;
        //Dp array creation
        //3d because row is same for both robot only column is changing so 1 for row and 2 for column
        int[][][] dp = new int[n][m][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                for (int k = 0; k < m; k++){
                    dp[i][j][k] = -1;
                    //filling of dp with -1
                }
            }
        }

        return helper(0, 0, m-1, grid, n, m, dp);

    }
    static int helper(int r, int c1, int c2, int[][] grid, int n, int m, int[][][] dp){
        //out of bound check
        if (c1 < 0 || c2 < 0 || c1 >= m || c2  >= m){
            return -1;
        }

        //if we are at the last row then we will return that value
        if (r == n-1){
            if (c1 == c2) return grid[r][c1];
            else return grid[r][c1] + grid[r][c2];
        }

        if (dp[r][c1][c2] != -1) return dp[r][c1][c2];

        // this stores the value of the total
        int max = Integer.MIN_VALUE;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2 ; j++){
                int cur = 0;
                if (c1 == c2) cur += grid[r][c1];
                else cur += (grid[r][c1] + grid[r][c2]);
                int future = helper(r + 1, c1 + i, c2 + j, grid, n, m, dp);
                if (future == -1) continue;
                cur += future;
                max = Math.max(cur, max);

            }
        }
        dp[r][c1][c2] = max;
        return max;
    }
}
