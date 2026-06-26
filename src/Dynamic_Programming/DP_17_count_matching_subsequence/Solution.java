package Dynamic_Programming.DP_17_count_matching_subsequence;

/*
        Count Matching Subsequences
        Difficulty: Medium
        Given two strings, s1 and s2, count the number of subsequences of string s1 equal to string s2.

        Return the total count modulo 1e9+7.

        Examples :

        Input: s1 = "geeksforgeeks", s2 = "gks"
        Output: 4
        Explanation: We can pick characters from s1 as a subsequence from indices [0, 3, 4], [0, 3, 12], [0, 11, 12] and [8, 11, 12].
        So total 4 subsequences of s1 that are equal to s2.
        Input: s1 = "problemoftheday", s2 = "geek"
        Output: 0
        Explanation: No subsequence of string s1 is equal to string s2.
        Constraints:
        1 ≤ s1.size(), s2.size() ≤ 10^3
 */
public class Solution {
    public static void main(String[] args) {

    }
    static long M = 1000000000 + 7;
    static int n1;
    static int n2;
    public static int countWays(String s1, String s2) {
        // code here

        n1 = s1.length();
        n2 = s2.length();

        long[][] dp = new long[n1 + 1][n2 + 1];

        for(int i = 0; i <= n2; i++){
            dp[n1][i] = 0;
        }
        for(int i = 0; i <= n1; i++){
            dp[i][n2] = 1;
        }

        for(int idx1 = n1 - 1; idx1 >= 0; idx1--){
            for(int idx2 = n2 - 1; idx2 >= 0; idx2--){

                char cur = s1.charAt(idx1);
                char pat = s2.charAt(idx2);
                long ans = 0;
                if(cur == pat){

                    ans = (dp[idx1 + 1][ idx2 + 1] +
                            dp[idx1 + 1][ idx2]) % M;
                }
                else{
                    ans = (ans + dp[idx1 + 1][ idx2]) % M;
                }
                dp[idx1][idx2] = ans;
            }
        }

        long ans = dp[0][0];
        return (int)ans;
    }
}
