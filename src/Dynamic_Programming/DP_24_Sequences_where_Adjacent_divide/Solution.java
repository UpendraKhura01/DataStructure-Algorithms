package Dynamic_Programming.DP_24_Sequences_where_Adjacent_divide;

import java.util.Arrays;

/*
        Sequences where Adjacent Divide
        Solved
        Difficulty: Medium
        Given two positive integer n and m. Find the number of arrays of size n that can be formed such that:

        Each element is in the range [1, m].
        All adjacent are such that one of them divide the another i.e element Ai divides Ai + 1 or Ai+1 divides Ai.
        Examples:

        Input: n = 3, m = 3
        Output : 17
        Explanation: The possible arrays are [1, 1, 1], [1, 1, 2], [1, 1, 3], [1, 2, 1], [1, 2, 2], [1, 3, 1], [1, 3, 3],
        [2, 1, 1], [2, 1, 2], [2, 1, 3], [2, 2, 1], [2, 2, 2], [3, 1, 1], [3, 1, 2], [3, 1, 3], [3, 3, 1] and [3, 3, 3].


        Input: n = 1, m = 10
        Output: 10
        Explanation: The possible arrays are [1], [2], [3], [4], [5], [6], [7], [8], [9] and [10].

        Constraints:

        1 ≤ n ≤ 11
        1 ≤ m ≤ 11
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n, m;
    int[][] dp;

    public int count(int n, int m) {
        // code here

        this.n = n;
        this.m = m;

        dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, 0);
    }
    private int solve(int prev, int size) {

        if (size == n) {
            return 1;
        }

        if (prev != 0 && dp[size][prev] != -1) {
            return dp[size][prev];
        }

        int ans = 0;

        for (int i = 1; i <= m; i++) {

            if (prev == 0 || prev % i == 0 || i % prev == 0) {
                ans += solve(i, size + 1);
            }
        }

        return dp[size][prev] = ans;
    }
}
