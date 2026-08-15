package Math.Longest_Subsequence_With_Non_Zero_Bitwise_XOR_3702;

/*
        3702. Longest Subsequence With Non-Zero Bitwise XOR

        Medium

        You are given an integer array nums.

        Return the length of the longest subsequence in nums whose bitwise XOR is non-zero. If no such subsequence exists, return 0.



        Example 1:

        Input: nums = [1,2,3]

        Output: 2

        Explanation:

        One longest subsequence is [2, 3]. The bitwise XOR is computed as 2 XOR 3 = 1, which is non-zero.

        Example 2:

        Input: nums = [2,3,4]

        Output: 3

        Explanation:

        The longest subsequence is [2, 3, 4]. The bitwise XOR is computed as 2 XOR 3 XOR 4 = 5, which is non-zero.



        Constraints:

        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^9
 */
public class Solution {
    public static void main(String[] args) {

    }
    int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        boolean non_zero = false;

        for(int i : nums){
            xor = xor ^ i;
            if(i > 0){
                non_zero = true;
            }
        }
        if(non_zero == false){
            return 0;
        }
        if(xor != 0){
            return n;
        }
        if(xor == 0 && non_zero){
            return n - 1;
        }
        return -1;
    }
}
