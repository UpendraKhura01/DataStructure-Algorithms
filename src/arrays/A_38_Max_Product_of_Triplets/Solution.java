package arrays.A_38_Max_Product_of_Triplets;

import java.util.Arrays;
/*
        628. Maximum Product of Three Numbers

        Easy

        Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

        Example 1:

        Input: nums = [1,2,3]
        Output: 6
        Example 2:

        Input: nums = [1,2,3,4]
        Output: 24
        Example 3:

        Input: nums = [-1,-2,-3]
        Output: -6


        Constraints:

        3 <= nums.length <= 10^4
        -1000 <= nums[i] <= 1000
 */
public class Solution {
    public static void main(String[] args) {

    }
/****************************************************Optimal Way O(N)****************************************************/
    int maximumProduct(int[] nums) {
        int n = nums.length;

        int m1 = Integer.MIN_VALUE;
        int m2 = Integer.MIN_VALUE;
        int m3 = Integer.MIN_VALUE;;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int num : nums){
            if(num >= m1){
                m3 = m2;
                m2 = m1;
                m1 = num;
            }
            else if(num >= m2){
                m3 = m2;
                m2 = num;
            }
            else if(num > m3){
                m3 = num;
            }

            if(num <= min1){
                min2 = min1;
                min1 = num;
            }
            else if(num < min2){
                min2 = num;
            }
        }

        return Math.max(m1 * m2 * m3, min1 * min2 * m1);
    }
/*************************************************With Sorting O(NlogN)*************************************************/
int Sorting(int[] nums) {
    int n = nums.length;

    Arrays.sort(nums);
    int left = nums[0] * nums[1] * nums[n - 1];
    int right = nums[n - 1] * nums[n - 2] * nums[n - 3];

    return Math.max(left, right);
}
}
