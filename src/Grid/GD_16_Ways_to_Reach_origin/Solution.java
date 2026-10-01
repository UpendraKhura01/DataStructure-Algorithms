package Grid.GD_16_Ways_to_Reach_origin;

import java.util.Arrays;

/*
        Ways to Reach Origin

        Difficulty: Medium
        Geek is standing at a point (x, y) on a 2D grid and wants to reach the origin (0, 0).

        From any point, Geek can move in only two directions: left, from (x, y) to (x - 1, y), or down,
        from (x, y) to (x, y - 1).

        Find the total number of distinct paths for Geek to reach (0, 0) from (x, y). Since the answer can be very large,
        return it modulo 109+7.

        Examples:

        Input: x = 3, y = 0
        Output: 1
        Explanation: The only possible path is (3, 0) -> (2, 0) -> (1, 0) -> (0, 0), since y = 0,
        there is no option to move down at any step.
        Input: x = 3, y = 6
        Output: 84
        Explanation: There are a total of 84 distinct paths from (3, 6) to (0, 0) using only left and down moves.
        Constraints:

        0 ≤ x, y ≤ 500
 */
public class Solution {
    public static void main(String[] args) {

    }
    long mod = 1000000000 + 7;

    int[] dr = {0 , -1};
    int[] dc = {-1 , 0};

    long[][] dp;

    public int ways(int x, int y) {
        // code here

        dp = new long[x + 1][y + 1];
        for(int i = 0; i <= x; i++){
            Arrays.fill(dp[i], -1);
        }

        long ans = solve(x, y);
        return (int)ans;
    }
    private long solve(int r, int c){
        if(r == 0 && c == 0){
            return 1;
        }

        if(dp[r][c] != -1){
            return dp[r][c];
        }
        long paths = 0;
        for(int i = 0; i < 2; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0){
                continue;
            }

            long path = solve(nr, nc);
            paths = (paths + path) % mod;
        }

        return dp[r][c] = paths;
    }
}
