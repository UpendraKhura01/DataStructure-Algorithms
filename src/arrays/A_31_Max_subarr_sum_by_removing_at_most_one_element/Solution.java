package arrays.A_31_Max_subarr_sum_by_removing_at_most_one_element;

/*
        Max Subarray Sum by Removing At Most One
        Difficulty: Medium
        Given an array arr[], find the maximum sum of a non-empty subarray.
        You are allowed to skip at most one element in the subarray.

        Note: After skipping the element, the subarray must still be non-empty.

        Examples:

        Input: arr[] = [1, 2, 3, -4, 5]
        Output: 11
        Explanation: We can get maximum sum subarray by skipping -4.
        Input: arr[] = [-2, -3, 4, -1, -2, 1, 5, -3]
        Output: 9
        Explanation: We can get maximum sum subarray by skipping -2 as [4,-1,1,5] sums to 9, which is the maximum achievable sum.
        Constraints:
        1 ≤ arr.size() ≤ 10^6
        -10^3 ≤ arr[i] ≤ 10^3
 */
public class Solution {
    public static void main(String[] args) {

    }

    int[] arr;
    int n;
/**********************************************Modified Kadane's Algorithm**********************************************/
    int maxSumSubarray(int[] arr) {
        // code here
        int n = arr.length;
        int ans = arr[0];
        int take = arr[0];
        int skip = 0;
        for (int i = 1; i < n; i++) {
			/*we either skip the ele by taking the prev computed sum or
			increase the cur sum by adiing the ele
			we are first computing the skip because the take has the solution till i - 1 and
			if we want to skip the cur element we can return to the take state */
            skip = Math.max(take, arr[i] + skip);

            // normal kadanes algo, it stores the max subarr sum till i index
            take = Math.max(arr[i], arr[i] + take);
            ans = Math.max(ans, Math.max(take,skip));

        }
        return ans;
    }
/*************************************************Prefix Suffix Approach*************************************************/

    int Prefix_Suffix(int[] arr) {
        // code here
        int n = arr.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        //prefix sum
        prefix[0] = arr[0];
        int max_sum = arr[0];
        for(int i = 1; i < n; i++){
            prefix[i] = Math.max(arr[i], prefix[i - 1] + arr[i]);
            max_sum = Math.max(max_sum, prefix[i]);
        }
        //suffix sum
        suffix[n - 1] = arr[n - 1];
        for(int i = n - 2; i >= 0; i--){
            suffix[i] = Math.max(arr[i], suffix[i + 1] + arr[i]) ;
        }

        int ans = max_sum;
        for(int i = 1; i < n - 1; i++){
            ans = Math.max(ans, prefix[i -1 ] + suffix[i + 1]);
        }
        return ans;
    }
/*****************************************************Tabulation Approach**************************************************/
    int Tabulation(int[] arr) {
        // code here
        this.arr = arr;
        n = arr.length;
        int[][][] dp = new int[n + 1][2][2];

        dp[n][0][0] = Integer.MIN_VALUE;
        dp[n][0][1] = 0;
        dp[n][1][0] = Integer.MIN_VALUE;
        dp[n][1][1] = 0;


        for (int i = n - 1; i >= 0; i--) {
            for (int skipped = 0; skipped <= 1; skipped++) {
                for (int isinsidenewsubarr = 0; isinsidenewsubarr <= 1; isinsidenewsubarr++) {
                    int cur = arr[i];
                    int ans = 0;
                    int take;
                    int skip;
                    if (isinsidenewsubarr == 0) {

                        take = cur + dp[i + 1][0][1];
                        skip = dp[i + 1][1][0];
                    } else {

                        take = cur + dp[i + 1][skipped][isinsidenewsubarr];

                        skip = 0;
                        if (skipped == 0) {
                            skip = dp[i + 1][1][isinsidenewsubarr];
                        }
                    }

                    dp[i][skipped][isinsidenewsubarr] = Math.max(take, skip);
                }
            }

        }

        return dp[0][0][0];
    }

    /****************************************Memoization Dynamic Programming approach****************************************/

    public int Memoization(int[] arr) {
        // code here
        this.arr = arr;
        n = arr.length;
        int[][][] dp = new int[n + 1][2][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }

        return solve(0, 0, 0, dp);
    }

    private int solve(int i, int skipped, int isinsidenewsubarr, int[][][] dp) {
        if (i == n) {
            return isinsidenewsubarr == 0 ? Integer.MIN_VALUE : 0;
        }
        if (dp[i][skipped][isinsidenewsubarr] != -1)
            return dp[i][skipped][isinsidenewsubarr];
        int cur = arr[i];
        int ans = 0;
        int take;
        int skip;
        if (isinsidenewsubarr == 0) {
            // we are not inside the subarr, we may create a new one
            // or skip the one to create a new one later
            take = cur + solve(i + 1, 0, 1, dp);
            skip = solve(i + 1, 1, 0, dp);
        } else {
            // we are inside a subarr

            take = cur + solve(i + 1, skipped, isinsidenewsubarr, dp);

            skip = 0;
            if (skipped == 0) {
                skip = solve(i + 1, 1, isinsidenewsubarr, dp);
            }
        }
        return dp[i][skipped][isinsidenewsubarr] = Math.max(take, skip);
    }
}
