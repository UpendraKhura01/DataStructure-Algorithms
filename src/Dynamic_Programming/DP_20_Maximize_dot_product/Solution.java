package Dynamic_Programming.DP_20_Maximize_dot_product;

import java.util.Arrays;
/*
        Max Dot Product with 0 Insertions
        Difficulty: Medium
        Given two arrays a[] and b[] of positive integers of size n and m respectively, where m ≤ n.
        You are allowed to insert zeros anywhere into the second array b so that its length becomes equal to n.

        The dot product of two arrays of equal length n is defined as: a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1].
        Return the maximum possible dot product of the two arrays.
        Examples :

        Input: a[] = [2, 3, 1, 7, 8], b[] = [3, 6, 7]
        Output: 107
        Explanation: Maximum dot product is obtained after inserting 0 at the first and third positions in array b.
        Therefore b becomes [0, 3, 0, 6, 7].
        Maximum dot product = 2*0 + 3*3 + 1*0 + 7*6 + 8*7 = 107. Therefore answer for this test case is 107.
        Input: a[] = [1, 2, 3], b[] = [4]
        Output: 12
        Explanation: Maximum dot product is obtained after inserting 0 at the first and second positions in array b.
        Therefore b becomes [0, 0, 4].
        Maximum Dot Product = 1*0 + 2*0 + 3*4 = 12. Therefore answer for this test case is 12.
        Constraints:
        1 ≤ m ≤ n ≤ 10^3
        1 ≤ a[i], b[i] ≤ 10^3
 */

public class Solution {
    public static void main(String[] args) {

    }

    int[] a;
    int[] b;
    int n;
    int m;

    /*******************************************************Memoization*******************************************************/
    public int maxDotProduct(int[] a, int[] b) {
        // code here
        this.a = a;
        this.b = b;
        this.n = a.length;
        this.m = b.length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(0, 0, dp);
    }

    private int solve(int i, int j, int[][] dp) {
        if (j == m) {
            return 0;
        }
        if (n - i < m - j || i == n) {
            return Integer.MIN_VALUE;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int product = a[i] * b[j];
        int take = product + solve(i + 1, j + 1, dp);

        int skip = solve(i + 1, j, dp);

        return dp[i][j] = Math.max(take, skip);
    }

    /*******************************************************Tabulation*******************************************************/
    int Tabulation(int[] a, int[] b) {
        // code here
        this.a = a;
        this.b = b;
        this.n = a.length;
        this.m = b.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < m; i++) {
            dp[n][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][m] = 0;

        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (n - i < m - j) {
                    dp[i][j] = Integer.MIN_VALUE;
                    continue;
                } else {
                    int product = a[i] * b[j];
                    int take = Integer.MIN_VALUE;
                    if (dp[i + 1][j + 1] != Integer.MIN_VALUE)
                        take = product + dp[i + 1][j + 1];

                    int skip = dp[i + 1][j];

                    dp[i][j] = Math.max(take, skip);
                }
            }
        }

        return dp[0][0];
    }
}
