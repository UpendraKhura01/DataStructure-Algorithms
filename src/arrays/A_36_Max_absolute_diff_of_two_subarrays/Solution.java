package arrays.A_36_Max_absolute_diff_of_two_subarrays;

/*
        Max Absolute Diff of Two Subarrays
        Solved
        Difficulty: Medium
        Given an array of integers arr[], find two non-overlapping contiguous sub-arrays such that
        the absolute difference between the sum of two sub-arrays is maximum.

        Examples :

        Input: arr[] = [-2, -3, 4, -1, -2, 1, 5, -3]
        Output: 12
        Explanation: Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]
        Input: arr[] = [2, -1, -2, 1, -4, 2, 8]
        Output: 16
        Explanation: Two subarrays are [-1, -2, 1, -4] and [2, 8]
        Constraints:
        2 ≤ arr.size() ≤ 10^5
        -103 ≤ arr[i] ≤ 10^3
 */
public class Solution {
    public static void main(String[] args) {

    }

    int maxDiffSubArrays(int[] arr) {
        // code here
        int n = arr.length;

        int[] maxright = new int[n];
        int[] minright = new int[n];

        int[] maxleft = new int[n];
        int[] minleft = new int[n];
        minleft[0] = arr[0];
        maxleft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxleft[i] = Math.max(arr[i], arr[i] + maxleft[i - 1]);
            minleft[i] = Math.min(arr[i], arr[i] + minleft[i - 1]);
        }

        maxright[n - 1] = arr[n - 1];
        minright[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minright[i] = Math.min(arr[i], minright[i + 1] + arr[i]);
            maxright[i] = Math.max(arr[i], maxright[i + 1] + arr[i]);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n - 1; i++) {

            int maxdiffR = Math.abs(minleft[i] - maxright[i + 1]);
            int maxdiffL = Math.abs(maxleft[i] - minright[i + 1]);
            ans = Math.max(ans, Math.max(maxdiffR, maxdiffL));
        }

        return ans;
    }
}
