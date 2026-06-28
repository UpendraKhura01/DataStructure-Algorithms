package Dynamic_Programming.Dp_19_K_times_apparing_Adjacent_Two_1s;

/*
        k Times Appearing Adjacent Two 1's
        Difficulty: Medium
        Given two integers n and k, count the number of binary strings of length n where adjacent 1 appear k times.

        Since the answer can be huge, return it modulo 109+7.

        Examples:

        Input: n = 3, k = 2
        Output: 1
        Explanation: Possible string is "111" where 2 adjacent 1 appear.
        Input: n = 5, k = 2
        Output: 6
        Explanation: Possible strings are "00111", "10111", "01110", "11100", "11101" and "11011".
        Constraints:
        1 ≤ n, k ≤ 10^3
 */
public class Solution {
    public static void main(String[] args) {

    }
    long M = 1000000000 + 7;
    int n;
    int k;
    public int countStrings(int n, int k) {
        // code here
        this.n = n;
        this.k = k;

        int[][][] dp = new int[n + 1][k + 1][2];
        for(int i = 0;  i<= n; i++){
            for(int j = 0; j <= k; j++){
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        long ans = solve(1, 0, 0, dp);
        ans = (ans + solve(1, 1, 0, dp)) % M;
        return (int)ans;
    }
    private long solve(int idx, int last, int adj, int[][][] dp){
        if(adj > k){
            return 0;
        }
        if(idx == n){
            return (adj == k) ? 1 : 0;
        }
        if(dp[idx][adj][last] != -1){
            return dp[idx][adj][last];
        }
        long ans = 0;

        ans += (ans + solve(idx + 1, 0, adj, dp)) % M;

        if(last == 1){
            ans = (ans + solve(idx + 1, 1, adj + 1, dp)) % M;
        }
        else{
            ans = (ans + solve(idx + 1, 1, adj, dp)) % M;
        }
        dp[idx][adj][last] = (int)ans;
        return ans;
    }
}
