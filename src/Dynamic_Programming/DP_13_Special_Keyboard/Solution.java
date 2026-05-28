package Dynamic_Programming.DP_13_Special_Keyboard;

public class Solution {
    public static void main(String[] args) {

    }
    /***************************************************** Memoization ****************************************************/
    static int optimalKeys(int n) {
        // code here
        int[] dp = new int[n + 1];
        return helper(n, dp);
    }
    static int helper(int n, int[] dp){
        if(n <= 6)
            return n;
        int ans = 0;
        if(dp[n] != 0) return dp[n];
        for(int i = 1; i <= n - 3; i++){
            int cur = helper(i, dp) * (n - i - 1);
            ans = Math.max(ans, cur);
        }

        return dp[n] = ans;
    }
    /****************************************************Tabulation****************************************************/
    static int optimalKeys1(int n){
        int[] dp = new int[n + 1];
        for(int i = 0; i <= 6; i++){
            dp[i] = i;
        }
        for(int i = 7; i <= n; i++){
            int ans = 0;

            for(int j = 1; j <= i - 3; j++){
                int cur = dp[j] * (i - j - 1);
                ans = Math.max(ans, cur);
            }
            dp[i] = ans;
        }
        return dp[n];
    }
}
