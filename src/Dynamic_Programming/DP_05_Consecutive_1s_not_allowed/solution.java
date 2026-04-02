package Dynamic_Programming.DP_05_Consecutive_1s_not_allowed;

public class solution {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(countStrings(n));

    }

    static int countStrings(int n) {
        // code here

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
            dp[i] = -1;

        }
        return helper(n, 0, dp);
    }

    static int helper(int n, int idx, int[] dp) {
        if (idx >= n) {
            return 1;
        }
        if (dp[idx] != -1) return dp[idx];

        return dp[idx] = helper(n, idx + 1, dp) + helper(n, idx + 2, dp);
    }

}
