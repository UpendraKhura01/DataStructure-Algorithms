package Dynamic_Programming.DP_23_Numbers_with_Given_Digit_sum;

import java.util.Arrays;
/*
        Numbers with Given Digit Sum

        Difficulty: Medium
        Given two integers n and sum, determine the number of n-digit positive integers whose digits add up to sum.

        An n-digit number cannot have leading zeros; that is, the first digit must be between 1 and 9.
        If there exist no n digit number with sum of digits equal to given sum, return -1.
        Examples :

        Input: n = 2, sum = 2
        Output: 2
        Explaination: The valid 2-digit numbers whose digits sum to 2 are 11 and 20.
        Input: n = 1, sum = 10
        Output: -1
        Explaination: A single-digit number can only have a digit sum between 0 and 9.
        Input: n = 2, sum = 10
        Output: 9
        Explaination: The 2-digit numbers whose digits add up to 10 are: 19, 28, 37, 46, 55, 64, 73, 82, 91.

        Constraints:

        1 ≤ n ≤ 9
        1 ≤ sum ≤ 81
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[][] dp;
    int n;
    int sum;
    public int countWays(int n, int sum) {
        // code here
        this.n = n;
        this.sum = sum;
        dp = new int[n + 1][sum + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;
        for (int i = 1; i <= 9; i++) {
            ans += solve(1, i);
        }

        return (ans == 0) ? -1 : ans;
    }
    private int solve(int i, int s) {

        if (i == n && s == sum) {
            return 1;
        }
        if (s > sum || i > n) {
            return 0;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        int count = 0;
        for (int d = 0; d <= 9; d++) {
            count += solve(i + 1, s + d);
        }
        return dp[i][s] = count;
    }
}
