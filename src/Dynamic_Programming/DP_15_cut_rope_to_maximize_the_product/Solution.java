package Dynamic_Programming.DP_15_cut_rope_to_maximize_the_product;

/*
        Cut rope to maximise product
        Difficulty: Medium
        Given a rope of length n meters, cut it into multiple smaller ropes such that the product of their lengths is maximized.
        At least one cut is mandatory.

        Examples:

        Input: n = 2
        Output: 1
        Explanation: Since 1 cut is mandatory. Maximum obtainable product is 1 * 1 = 1.
        Input: n = 5
        Output: 6
        Explanation: Maximum obtainable product is 2 * 3 = 6.
        Constraints:
        2 ≤ n ≤ 58


 */
public class Solution {
    public static void main(String[] args) {

    }
/*******************************************************Memoization*******************************************************/
    int maxProduct(int n) {
        // code here
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        return helper(n, dp);
    }
    private int helper(int n, int[] dp) {
        if (n == 2)
            return 1;
        if(dp[n] != -1) return dp[n];

        int ans = 1;

        for (int i = 2; i < n; i++) {
            int once = i * (n - i);
            int more = i * helper(n - i, dp);
            ans = Math.max(ans, Math.max(once, more));
        }

        return dp[n] = ans;
    }
/******************************************************Tabulation******************************************************/
        int Tabulation(int n) {
            // code here

            int[] dp = new int[n + 1];
            dp[2] = 1;
            for (int i = 2; i <= n; i++) {
                int ans = 1;
                for (int j = 2; j <= i; j++) {
                    int once = j * (i - j);
                    int more = j * dp[i - j];
                    ans = Math.max(ans, Math.max(once, more));

                }
                dp[i] = ans;
            }
            return dp[n];
        }
/************************************************Optimal Greedy Solution************************************************/
        int Greedy(int n) {
            // code here
            if(n == 2) return 1;
            if(n == 3) return 2;
            int ans = 1;
            while(n > 4){
                ans *= 3;
                n -= 3;

            }
            return ans * n;
        }

}
