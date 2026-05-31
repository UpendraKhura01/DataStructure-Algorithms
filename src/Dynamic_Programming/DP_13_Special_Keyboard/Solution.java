package Dynamic_Programming.DP_13_Special_Keyboard;
/*
        Special Keyboard
        Difficulty: MediumAccuracy: 28.66%Submissions: 63K+Points: 4
        Given a special keyboard that contains only four keys:

        Key 1: Prints a single character 'A' on the screen.
        Key 2 (Ctrl + A): Selects all the characters currently present on the screen.
        Key 3 (Ctrl + C): Copies the selected characters to a buffer, overwriting its previous content.
        Key 4 (Ctrl + V): Pastes the content of the buffer onto the screen, appending it to the existing text.
        Initially, the screen is empty and the buffer is also empty.

        Determine the maximum number of 'A' characters that can be displayed on the screen after performing exactly n key presses.

        Examples :

        Input: n = 3
        Output: 3
        Explanation: With only 3 key presses, the best option is to press Key 1 each time. So, the screen shows "AAA" and
         the total number of A’s is 3.
        Input: n = 7
        Output: 9
        Explanation: An optimal sequence is: press Key 1 three times to get "AAA", then use Key 2 (select all) and Key 3 (copy),
         followed by Key 4 twice (paste). This results in "AAAAAAAAA", so the total number of A’s is 9.
        Constraints:
        1 ≤ n ≤ 70
 */
public class Solution {
    public static void main(String[] args) {

    }
    /***************************************************** Memoization ****************************************************/
    static int optimalKeys(int n) {
        // code here
        int[] dp = new int[n + 1];
        return helper(n, dp);
    }
    static int helper(int n, int[] dp){
        if(n <= 6)
            return n;
        int ans = 0;
        if(dp[n] != 0) return dp[n];
        for(int i = 1; i <= n - 3; i++){
            int cur = helper(i, dp) * (n - i - 1);
            ans = Math.max(ans, cur);
        }

        return dp[n] = ans;
    }
    /****************************************************Tabulation****************************************************/
    static int optimalKeys1(int n){
        int[] dp = new int[n + 1];
        for(int i = 0; i <= 6; i++){
            dp[i] = i;
        }
        for(int i = 7; i <= n; i++){
            int ans = 0;

            for(int j = 1; j <= i - 3; j++){
                int cur = dp[j] * (i - j - 1);
                ans = Math.max(ans, cur);
            }
            dp[i] = ans;
        }
        return dp[n];
    }
}
