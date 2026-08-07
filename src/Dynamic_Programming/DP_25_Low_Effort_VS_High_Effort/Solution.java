package Dynamic_Programming.DP_25_Low_Effort_VS_High_Effort;

/*
        High Effort vs Low Effort

        Difficulty: Easy
        Given two integer arrays h[] and l[], where h[i] and l[i] denote the number of tasks that can be completed on the i-th day by performing a high-effort task and a low-effort task, respectively.

        For each day, you may choose exactly one of the following:

        Perform no task.
        Perform a low-effort task.
        Perform a high-effort task, which can only be performed on the first day or if no task was performed on the previous day.
        Return the maximum total number of tasks that can be completed over all days.

        Examples:

        Input: h[] = [2, 8, 1], l[] = [1, 2, 1]
        Output: 9
        Explanation: Pick the high-effort task on day 1 and the low-effort task on day 2. Total = 8 + 1 = 9.
        Input: h[] = [3, 6, 8, 7, 6], l[] = [1, 5, 4, 5, 3]
        Output: 20
        Explanation: Pick the high-effort task on day 0 and low-effort tasks on all remaining days. Total = 3 + 5 + 4 + 5 + 3 = 20.
        Constraints:

        1 ≤ h.size() ≤ 10^5
        0 ≤ h[i] ≤ 10^3
        1 ≤ l.size() ≤ 10^5
        0 ≤ l[i] ≤ 10^3
        l.size() = h.size()

 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int[] h;
    int[] l;
    int[][] dp;

    public int maxTask(int[] h, int[] l) {
        // code here
        n = h.length;
        this.h = h;
        this.l = l;
        dp = new int[n + 1][2];
        for(int i = 0; i < n; i++){
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        return solve(0, 1);
    }
    private int solve(int i, int rested){
        if(i == n){
            return 0;
        }
        if(dp[i][rested] != -1){
            return dp[i][rested];
        }
        int max = 0;
        int low = l[i] + solve(i + 1, 0);
        int rest = solve(i + 1, 1);
        int high = 0;
        if(rested == 1){
            high = h[i] + solve(i + 1, 0);
        }
        max = Math.max(low, rest);
        return dp[i][rested] = Math.max(max, high);
    }
}
