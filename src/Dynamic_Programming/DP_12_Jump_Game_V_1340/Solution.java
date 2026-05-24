package Dynamic_Programming.DP_12_Jump_Game_V_1340;

/*
    1340. Jump Game V
    Hard

    Given an array of integers arr and an integer d. In one step you can jump from index i to index:

    i + x where: i + x < arr.length and  0 < x <= d.
    i - x where: i - x >= 0 and  0 < x <= d.
    In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j
     (More formally min(i, j) < k < max(i, j)).

    You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

    Notice that you can not jump outside of the array at any time.



    Example 1:


    Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
    Output: 4
    Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
    Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9.
    You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
    Similarly You cannot jump from index 3 to index 2 or index 1.
    Example 2:

    Input: arr = [3,3,3,3,3], d = 3
    Output: 1
    Explanation: You can start at any index. You always cannot jump to any index.
    Example 3:

    Input: arr = [7,6,5,4,3,2,1], d = 1
    Output: 7
    Explanation: Start at index 0. You can visit all the indicies.


    Constraints:

    1 <= arr.length <= 1000
    1 <= arr[i] <= 105
    1 <= d <= arr.length
 */

public class Solution {
    public static void main(String[] args) {
        int[] arr = {6,4,14,6,8,13,9,7,10,6,12};
        int d = 2;
        System.out.println(maxJumps(arr, d));
    }

    /************************************************ DP with Recursion ************************************************/
    static int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int ans = 1;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        for(int i = 0; i < n; i++){
            ans = Math.max(ans, helper(i, n, arr, d, dp));
        }
        return ans;
    }

    static int helper(int idx, int n, int[] arr, int d, int[] dp){
        int cur = 1;

        if(dp[idx] != -1) return dp[idx];
        for (int i = 1; i <= d; i++) {
            if (idx + i >= n) break;
            if (!cango(idx, idx + i, arr)) break;

            cur = Math.max(cur, 1 + helper(idx + i, n, arr, d, dp));
        }

        for (int i = 1; i <= d; i++) {
            if (idx - i < 0) break;
            if (!cango(idx, idx - i, arr)) break;

            cur = Math.max(cur, 1 + helper(idx - i, n, arr, d, dp));
        }
        return dp[idx] = cur;
    }
    static boolean cango(int src, int target, int[] arr){
        if(arr[src] <= arr[target])
            return false;
        for(int i = Math.min(src, target) + 1; i < Math.max(src,target); i++){
            if(arr[i] >= arr[src]) return false;
        }
        return true;
    }
    /***********************************************DP without Recursion***********************************************/
//    static int Solve(int[] arr, int d){
//
//    }

}
