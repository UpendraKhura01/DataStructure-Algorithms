package Dynamic_Programming.DP_33_Min_Steps_to_Reach_num;

/*
        Minimum Operations to Reach n

        Difficulty: Easy
        Given a number n. Find the minimum number of operations required to reach n starting from 0.

        You have two operations available:

        Double the number
        Add one to the number
        Examples:

        Input: n = 8
        Output: 4
        Explanation: 0 + 1 = 1 --> 1 + 1 = 2 --> 2 * 2 = 4 --> 4 * 2 = 8.
        Input: n = 7
        Output: 5
        Explanation: 0 + 1 = 1 --> 1 + 1 = 2 --> 1 + 2 = 3 --> 3 * 2 = 6 --> 6 + 1 = 7.
        Constraints:

        1 ≤ n ≤ 10^6
 */
public class Solution {
    public static void main(String[] args) {

    }

/*******************************************************Tabulation*******************************************************/
    int[] dp;
    public int minOperation(int n) {
        // code here
        dp = new int[n + 1];
        if(n <= 3){
            return n;
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int num = 4; num <= n; num++){
            int op = num;

            if(num % 2 == 0){
                op = 1 + dp[num / 2];
            }
            else{
                op = 1 + dp[num - 1];
            }

            dp[num] = op;
        }

        return dp[n];
    }



/****************************************************** Memoization******************************************************/
    private int solve(int num) {
        if (num <= 3) {
            return num;
        }

        if (dp[num] != -1) {
            return dp[num];
        }

        int op = num;
        if (num % 2 == 0) {
            op = 1 + solve(num / 2);
        }
        else {
            op = 1 + solve(num - 1);

        }

        return dp[num] = op;
    }
}
