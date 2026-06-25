package Dynamic_Programming.DP_16_Num_of_Zigzag_Arrays_3699;

/*
        3699. Number of ZigZag Arrays I

        Hard

        You are given three integers n, l, and r.

        A ZigZag array of length n is defined as follows:

        Each element lies in the range [l, r].
        No two adjacent elements are equal.
        No three consecutive elements form a strictly increasing or strictly decreasing sequence.
        Return the total number of valid ZigZag arrays.

        Since the answer may be large, return it modulo 109 + 7.

        A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).

        A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).



        Example 1:

        Input: n = 3, l = 4, r = 5

        Output: 2

        Explanation:

        There are only 2 valid ZigZag arrays of length n = 3 using values in the range [4, 5]:

        [4, 5, 4]
        [5, 4, 5]
        Example 2:

        Input: n = 3, l = 1, r = 3

        Output: 10

        Explanation:

        There are 10 valid ZigZag arrays of length n = 3 using values in the range [1, 3]:

        [1, 2, 1], [1, 3, 1], [1, 3, 2]
        [2, 1, 2], [2, 1, 3], [2, 3, 1], [2, 3, 2]
        [3, 1, 2], [3, 1, 3], [3, 2, 3]
        All arrays meet the ZigZag conditions.



        Constraints:

        3 <= n <= 2000
        1 <= l < r <= 2000
 */

public class Solution {
    public static void main(String[] args) {

    }
/*******************************************************Optimized*******************************************************/
public int Prefix_tabulation(int n, int l, int r){
    int max = r - l + 1;
    long ans = 0;
    long[][][] dp = new long[n + 1][max + 1][2];
    for(int i = 1; i <= max; i++){
        dp[n][i][0] = 1;
        dp[n][i][1] = 1;
    }
    for(int i = n -1; i >= 1; i--){
        long[] prefix_dec = new long[max + 1];
        long[] prefix_inc = new long[max + 1];
        for(int c= 1; c <= max; c++){
            prefix_inc[c] = (prefix_inc[c - 1] + dp[i + 1][c][1]) % M;
            prefix_dec[c] = (prefix_dec[c - 1] + dp[i + 1][c][0]) % M;
        }
        for(int j = max; j >= 1; j--){

            dp[i][j][0] = (prefix_inc[max]- prefix_inc[j] + M) % M; //increasing
            dp[i][j][1] = prefix_dec[j - 1];  //decreasing

        }
    }
    for(int i = 1; i <= max; i++){
        ans += dp[1][i][1] % M;
        ans += dp[1][i][0] % M;
    }

    return (int)(ans % M);
}
/*******************************************************Tabulation*******************************************************/
public int tabulation(int n, int l, int r){
    int max = r - l + 1;
    long ans = 0;
    long[][][] dp = new long[n + 1][max + 1][2];
    for(int i = 1; i <= max; i++){
        dp[n][i][0] = 1;
        dp[n][i][1] = 1;
    }
    for(int i = n -1; i >= 0; i--){
        for(int j = max; j >= 1; j--){
            //increasing
            for(int k = j + 1; k <= max; k++){
                dp[i][j][0] = (dp[i][j][0] + dp[i + 1][k][1]) % M;
            }
            //decreasing
            for(int k = 1; k < j; k++){
                dp[i][j][1] = (dp[i][j][1] + dp[i + 1][k][0]) % M;
            }
        }
    }
    for(int i = 1; i <= max; i++){
        ans += dp[1][i][1] % M;
        ans += dp[1][i][0] % M;
    }

    return (int)(ans % M);
}

/******************************************************Memoization******************************************************/
    long M = 1000000000 + 7;
    public int zigZagArrays(int n, int l, int r) {
        int max = r - l + 1;
        long ans = 0;
        int[][][] dp = new int[n + 1][max + 1][2];
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < max + 1; j++){
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        for(int i = 1; i <= max; i++){
            ans += solve(1, i, 1, max, n, dp) % M;
            ans += solve(1, i, 0, max, n, dp) % M;
        }
        return (int)(ans % M);
    }
    private int solve(int idx, int prev, int decreasing, int max, int n, int[][][] dp){
        // 0 = decreasing
        //1 = increasing
        if(idx == n) return 1;
        long ans = 0;
        if(dp[idx][prev][decreasing] != -1){
            return dp[idx][prev][decreasing];
        }
        if(decreasing == 0){
            for(int i = prev + 1; i <= max; i++){
                ans += solve(idx + 1, i, 1, max, n, dp) % M;
            }
        }
        else{
            for(int i = 1; i < prev; i++){
                ans += solve(idx + 1, i, 0, max, n, dp) % M;
            }
        }
        return dp[idx][prev][decreasing] = (int)(ans % M);
    }
}
