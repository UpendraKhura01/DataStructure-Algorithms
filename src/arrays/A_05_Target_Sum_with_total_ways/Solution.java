package arrays.A_05_Target_Sum_with_total_ways;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(totalWays(arr, target));

    }

    static int memoization(int[] arr, int target) {
        // code here
        int n = arr.length;
        int sum = 0;

        for (int i : arr) {
            sum += i;
        }

        int[][] dp = new int[n][2 * sum + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        return helper(arr, 0, target, 0, n, dp, sum);

    }

    static int helper(int[] arr, int idx, int target, int cur, int n, int[][] dp, int sum) {

        if (idx == n) {
            if (cur == target) return 1;
            else return 0;
        }
        if (dp[idx][cur + sum] != Integer.MIN_VALUE) return dp[idx][cur + sum];

        int minus = helper(arr, idx + 1, target, cur - arr[idx], n, dp, sum);
        int add = helper(arr, idx + 1, target, cur + arr[idx], n, dp, sum);

        return dp[idx][cur + sum] = minus + add;

    }

    // tabulation
    static int totalWays(int[] arr, int target) {
        // code here
        int n = arr.length;
        int sum = 0;

        for (int i : arr) {
            sum += i;
        }

        int[][] dp = new int[n + 1][2 * sum + 1];

        // base case
        dp[n][target] = 1;

        for (int idx = 0; idx < n; idx++) {
            for (int cur = -sum; cur <= sum; cur++) {

                int minus = dp[idx + 1][cur - arr[idx] + sum];
                int add = dp[idx + 1][cur + arr[idx] + sum];

                dp[idx][cur + sum] = minus + add;

            }
        }

        for (int[] i : dp){
            System.out.println(Arrays.toString(i));
        }

        return dp[0][0];
    }

}
