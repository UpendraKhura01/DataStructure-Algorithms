package Dynamic_Programming.DP_18_Num_of_ways_to_tile_the_floor_of_size_n_m;

/*
        Ways to Tile the Floor
        Difficulty: Medium
        Given a floor of dimensions n × m and an unlimited supply of tiles of size 1 × m,
        find the total number of ways to completely tile the floor.
        Each tile can be placed in one of the following ways:

        Horizontally, covering 1 row and m columns.
        Vertically, covering m rows and 1 column.
        Count all possible ways to cover the entire floor such that there are no overlaps and no uncovered cells.

        Since the number of possible tilings can be very large, return the answer modulo 109+7.

        Note: n and m are positive integers, and m ≥ 2.

        Examples:

        Input: n = 4, m = 4
        Output: 2
        Explanation: There are exactly two valid ways to tile the floor.

        Input: n = 2, m = 3
        Output: 1
        Explanation: Placing a tile vertically would require a height of 3, which exceeds the floor's height of 2. The only way to cover the entire floor is by placing 2 horizontally, one in each row, resulting in exactly one valid tiling.
        Constraints:
        1 ≤ n ≤ 10^5
        2 ≤ m ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    long M = 1000000000 + 7;
    public int countWays(int n, int m) {
        // code here

        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++){
            dp[i] = -1;
        }
        return solve(n, m, dp);
    }
    private int solve(int n, int m, int[] dp){
        if(n < m){
            return 1;
        }

        if(n == m) return 2;

        if(dp[n] != -1) return dp[n];

        int ans = 0;
        ans = (int)((ans + solve(n - 1, m, dp)) % M);
        ans = (int)((ans + solve(n - m, m , dp)) % M);


        return dp[n] = ans;
    }
}
