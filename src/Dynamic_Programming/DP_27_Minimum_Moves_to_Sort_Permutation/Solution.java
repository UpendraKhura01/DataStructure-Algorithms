package Dynamic_Programming.DP_27_Minimum_Moves_to_Sort_Permutation;

/*
        Minimum Moves to Sort Permutation

        Difficulty: Easy
        Given an array arr[] containing integers from 1 to n exactly once, sort the array in ascending order.

        In one operation, you can pick any element and move it either to the beginning or to the end of the array.

        Return the minimum number of operations required to sort the array.

        Examples:

        Input: arr[] = [2, 1, 3]
        Output: 1
        Explanation: Move 1 to the beginning.
        Input: arr[] = [4, 3, 1, 2]
        Output: 2
        Explanation: Move 3 to the end to get [4, 1, 2, 3]. Then move 4 to the end to get [1, 2, 3, 4].
        Constraints:

        arr.size() ≤ 10^5
        1 ≤ arr[i] ≤ arr.size()

 */
public class Solution {
    public static void main(String[] args) {

    }
    int minMoves(int[] arr) {
        // code here
        int n = arr.length;
        int[] dp = new int[n + 1];

        int max = 0;
        for(int i = 0; i < n; i++){
            int num = arr[i];
            dp[num] = dp[num - 1] + 1;
            max = Math.max(max, dp[num]);
        }


        return n - max;
    }
}
