package Dynamic_Programming.DP_21_Ways_to_increase_LCS_by_1;

/*
        Ways to Increase LCS by One
        Difficulty: Medium
        Given two strings s1 and s2 consisting of lowercase English letters of length n1 and n2 respectively,
        find the number of ways to insert exactly one character into string s1 such that the
        length of the Longest Common Subsequence (LCS) of both strings increases by exactly 1.

        Examples :

        Input: s1 = "abab", s2 = "abc"
        Output: 3
        Explanation: The LCS length of the given two strings is 2. There are 3 valid insertions in s1 which increase the LCS length to 3:
        "abcab" -> LCS = 3
        "abacb" -> LCS = 3
        "ababc" -> LCS = 3
        Input: s1 = "abcabc", s2 = "abcd"
        Output: 4
        Explanation: The LCS length of the given two strings is 3. There are 4 valid insertions in s1 which increase the LCS length to 4:
        "abcdabc" -> LCS = 4
        "abcadcb" -> LCS = 4
        "abcabdc" -> LCS = 4
        "abcabcd" -> LCS = 4
        Constraints:
        1<= n1, n2 <=100
 */
public class Solution {
    public static void main(String[] args) {

    }

    /**************************************Optimal Approach using Prefix and Suffix DP**************************************/
    int waysToIncreaseLCSBy1(String s1, String s2) {
        // code here
        int n = s1.length();
        int m = s2.length();

        int[][] pre = new int[n + 1][m + 1];
        int[][] suf = new int[n + 1][m + 1];


        //prefix + lcs
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    pre[i][j] = pre[i - 1][j - 1] + 1;
                } else {
                    int f = pre[i - 1][j];
                    int s = pre[i][j - 1];
                    pre[i][j] = Math.max(f, s);
                }
            }
        }

        //suffix
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    suf[i][j] = suf[i + 1][j + 1] + 1;
                } else {
                    int f = suf[i + 1][j];
                    int s = suf[i][j + 1];
                    suf[i][j] = Math.max(f, s);
                }
            }
        }
        int lcs = pre[n][m];
        int count = 0;

        for (int i = 0; i <= n; i++) {

            boolean[] used = new boolean[26];

            for (int j = 0; j < m; j++) {

                char c = s2.charAt(j);
                if (used[c - 'a']) {
                    continue;
                }
                if (pre[i][j] + 1 + suf[i][j + 1] == lcs + 1) {
                    count++;
                    used[c - 'a'] = true;
                }
            }
        }

        return count;
    }

    /*****************************************Using Brute force Dynamic Programming*****************************************/
    int Brute_Force(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        StringBuilder org = new StringBuilder(s1);
        StringBuilder pat = new StringBuilder(s2);

        int normal = lcs_tabulation(n, m, org, pat);

        int count = 0;

        boolean[] used = new boolean[26];

        for (int j = 0; j < m; j++) {

            char c = pat.charAt(j);

            if (used[c - 'a'])
                continue;

            used[c - 'a'] = true;

            for (int i = 0; i <= n; i++) {

                StringBuilder temp = new StringBuilder(org);
                temp.insert(i, c);

                int val = lcs_tabulation(n + 1, m, temp, pat);

                if (val == normal + 1)
                    count++;
            }
        }

        return count;
    }

    private int lcs_tabulation(int n, int m,
                               StringBuilder org, StringBuilder pat) {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][m] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[n][i] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (org.charAt(i) == pat.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    int f = dp[i + 1][j];
                    int s = dp[i][j + 1];
                    dp[i][j] = Math.max(f, s);
                }
            }
        }

        return dp[0][0];
    }
}
