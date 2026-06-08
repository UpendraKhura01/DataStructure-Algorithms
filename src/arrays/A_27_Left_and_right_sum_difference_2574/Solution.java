package arrays.A_27_Left_and_right_sum_difference_2574;

/*
        2574. Left and Right Sum Differences

        Easy

        You are given a 0-indexed integer array nums of size n.

        Define two arrays leftSum and rightSum where:

        leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
        rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
        Return an integer array answer of size n where answer[i] = |leftSum[i] - rightSum[i]|.

        Example 1:

        Input: nums = [10,4,8,3]
        Output: [15,1,11,22]
        Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
        The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
        Example 2:

        Input: nums = [1]
        Output: [0]
        Explanation: The array leftSum is [0] and the array rightSum is [0].
        The array answer is [|0 - 0|] = [0].


        Constraints:

        1 <= nums.length <= 1000
        1 <= nums[i] <= 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] leftRightDifference(int[] nums) {
        int n = nums.length;

        int total = 0;

        for (int i : nums) {
            total += i;
        }
        int left = 0;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int right = total - nums[i] - left;
            ans[i] = Math.abs(left - right);
            left += nums[i];
        }
        return ans;
    }
}
