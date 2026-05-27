package HashMap.HM_01_Min_Absolute_diff_Between_Mirror_Pairs_3761;

import java.util.HashMap;
import java.util.Map;
/*
        3761. Minimum Absolute Distance Between Mirror Pairs
        Medium

        You are given an integer array nums.

        A mirror pair is a pair of indices (i, j) such that:

        0 <= i < j < nums.length, and
        reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x.
         Leading zeros are omitted after reversing, for example reverse(120) = 21.
        Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices i and j is abs(i - j).

        If no mirror pair exists, return -1.

        Example 1:

        Input: nums = [12,21,45,33,54]

        Output: 1

        Explanation:

        The mirror pairs are:

        (0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
        (2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
        The minimum absolute distance among all pairs is 1.

        Example 2:

        Input: nums = [120,21]

        Output: 1

        Explanation:

        There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].

        The minimum absolute distance is 1.

        Example 3:

        Input: nums = [21,120]

        Output: -1

        Explanation:

        There are no mirror pairs in the array.

        constraints:

        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9
 */

public class Solution {
    public static void main(String[] args) {

        int[] nums = {12,21,45,33,54};
        int ans = minMirrorPairDistance(nums);
        System.out.println(ans);
    }
    static int minMirrorPairDistance(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                min = Math.min(min, i - map.get(nums[i]));
            }
            int r = reverse(nums[i]);
            map.put(r, i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    static int reverse(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10; // Extract last digit
            reversed = reversed * 10 + digit; // Append to result
            num /= 10; // Remove last digit
        }
        return reversed;
    }
}
