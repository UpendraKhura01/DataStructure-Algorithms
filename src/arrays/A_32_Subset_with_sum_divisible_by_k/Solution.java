package arrays.A_32_Subset_with_sum_divisible_by_k;

/*
        Check Subset sum divisible by k
        Difficulty: Medium
        Given an array arr[] of positive integers and a value k.
        Return true if the sum of any non-empty subset of the given array is divisible by k otherwise, return false.

        Examples:

        Input: arr[] = [3, 1, 7, 5] , k = 6
        Output: true
        Explanation: If we take the subset {7, 5} then sum will be 12 which is divisible by 6.
        Input: arr[] = [1, 2, 6] , k = 5
        Output: false
        Explanation: All possible subsets of the given set are {1}, {2}, {6}, {1, 2}, {2, 6}, {1, 6} and {1, 2, 6}.
        There is no subset whose sum is divisible by 5.
        Constraints:
        1 ≤ arr.size(), k ≤ 10^3
        1 ≤ arr[i] ≤ 10^3
 */
public class Solution {
    public static void main(String[] args) {

    }
/****************************************************Optimal solution****************************************************/
        boolean divisibleByK(int[] arr, int k) {
            // code here
            int n = arr.length;

            boolean [] dp = new boolean[k];

            for (int i = 0; i < n; i++) {

                boolean[] cur = new boolean[k];

                cur[arr[i] % k] = true;

                for (int j = 0; j < k; j++) {

                    if (dp[j]) {
                        cur[j] = true;

                        cur[(j + arr[i]) % k] = true;
                    }
                }
                dp = cur;
                if (dp[0]) {
                    return true;
                }
            }
            return false;
        }
/***************************************Dynamic programming solution(memoization)***************************************/
int[] arr;
    int n;
    int k;
    Boolean[][][] dp;
    public boolean Memoization(int[] arr, int k) {
        // code here
        this.arr = arr;
        this.k = k;
        n = arr.length;
        if(n > k) return true;
        dp = new Boolean[n + 1][k][2];

        return (solve(0, 0, 0));
    }
    private boolean solve(int i, int rem, int taken) {
        if (rem == 0 && taken == 1) {
            return true;
        }
        if (i == n) {
            return false;
        }
        if(dp[i][rem][taken] != null){
            return dp[i][rem][taken];
        }
        // take the cur
		/*
		(S + arr[idx]) % k
		= ((S % k) + arr[idx]) % k
		= (rem + arr[idx]) % k
		*/
        if (solve(i + 1, (rem + arr[i]) % k, 1))
            return dp[i][rem][taken] = true;
        if (solve(i + 1, rem, taken))
            return dp[i][rem][taken] = true;

        return dp[i][rem][taken] = false;
    }
/**************************************************Tabulation Approach**************************************************/
        boolean[][][] dp1;
        boolean Tabulation(int[] arr, int k) {
            // code here
            this.arr = arr;
            this.k = k;
            n = arr.length;
            if (n > k)
                return true;
            dp1 = new boolean[n + 1][k][2];
            for (int rem = 0; rem < k; rem++) {
                dp1[n][rem][0] = false;
                dp1[n][rem][1] = false;
            }
            dp1[n][0][1] = true;
            for (int i = n - 1; i >= 0; i--) {
                for (int rem = 0; rem < k; rem++) {
                    for (int taken = 0; taken < 2; taken++) {
                        dp1[i][rem][taken] =
                                dp1[i + 1][(rem + arr[i]) % k][1] ||
                                        dp1[i + 1][rem][taken];

                    }
                }
            }

            return dp1[0][0][0];
        }
}
