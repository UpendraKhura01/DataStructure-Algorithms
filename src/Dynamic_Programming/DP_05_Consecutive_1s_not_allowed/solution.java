package Dynamic_Programming.DP_05_Consecutive_1s_not_allowed;

public class solution {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(countStrings(n));

    }

    static int countStrings(int n) {
        // code here

        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;

        }
        return helper(n, 0, 0, dp);
    }

    static int helper(int n, int idx, int prev, int[][] dp) {
        if (idx == n) {
            return 1;
        }
        if (dp[idx][prev] != -1) return dp[idx][prev];
        int putZero = helper(n, idx + 1, 0, dp);
        int putOne = 0;
        if (prev != 1) {
            putOne = helper(n, idx + 1, 1, dp);
        }

        return dp[idx][prev] = putOne + putZero;
    }

}
