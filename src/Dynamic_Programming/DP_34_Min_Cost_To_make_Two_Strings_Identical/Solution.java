package Dynamic_Programming.DP_34_Min_Cost_To_make_Two_Strings_Identical;

import java.util.Arrays;

/*
        Min Cost To Make Two Strings Identical

        Difficulty: Medium
        Given two strings s1 and s2, and two integers costS1 and costS2, where costS1 is the cost of deleting one character
         from s1 and costS2 is the cost of deleting one character from s2,
         find the minimum cost required to make the two strings identical.

        You can delete any number of characters from either string, but the order of the remaining characters must be preserved.

        Examples:

        Input: s1 = "abcd", s2 = "acdb", costS1 = 10, costS2 = 20
        Output: 30
        Explanation: Delete 'b' from both strings to obtain "acd". The total cost is 10 + 20 = 30.
        Input: s1 = "ef", s2 = "gh", costS1 = 10, costS2 = 20
        Output: 60
        Explanation: The two strings have no common characters, so delete all characters from both strings.
        The total cost is (2 × 10) + (2 × 20) = 60.
        Constraints:

        1 ≤ s1.size(), s2.size() ≤ 1000
        1 ≤ costS1, costS2 ≤ 10^5

 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int m;
    StringBuilder f;
    StringBuilder s;
    int cost1;
    int cost2;
    int[][] dp;
    public int findMinCost(String s1, String s2, int costS1, int costS2) {
        // code here
        f = new StringBuilder(s1);
        s = new StringBuilder(s2);
        n = s1.length();
        m = s2.length();
        this.cost1 = costS1;
        this.cost2 = costS2;
        dp = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(0, 0);


    }
    private int solve(int i, int j){
        if(i == n){
            return (m - j) * cost2;

        }
        if(j == m){
            return (n - i) * cost1;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }


        int c1 = f.charAt(i);
        int c2 = s.charAt(j);
        int cur = Integer.MAX_VALUE;
        if(c1 == c2){
            cur = solve(i + 1, j + 1);
        }
        else{
            int first = cost1 + solve(i + 1, j);

            int second = cost2 + solve(i, j + 1);

            cur = Math.min(cur, Math.min(first, second));
        }

        return dp[i][j] = cur;
    }
}
