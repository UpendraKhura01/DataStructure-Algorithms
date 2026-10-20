package Simulation.Sim_11_Sum_of_Sortable_Integers_3886;

import java.util.ArrayList;
/*
        3886. Sum of Sortable Integers

        Hard

        You are given an integer array nums of length n.

        An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially
        performing the following operations:

        Partition nums into consecutive subarrays of length k.
        Cyclically rotate each subarray independently any number of times to the left or to the right.
        Return an integer denoting the sum of all possible sortable integers k.



        Example 1:

        Input: nums = [3,1,2]

        Output: 3

        Explanation:


        For n = 3, possible divisors are 1 and 3.
        For k = 1: each subarray has one element. No rotation can sort the array.
        For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
        Only k = 3 is sortable. Hence, the answer is 3.
        Example 2:

        Input: nums = [7,6,5]

        Output: 0

        Explanation:

        For n = 3, possible divisors are 1 and 3.
        For k = 1: each subarray has one element. No rotation can sort the array.
        For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
        No k is sortable. Hence, the answer is 0.
        Example 3:

        Input: nums = [5,8]

        Output: 3

        Explanation:


        For n = 2, possible divisors are 1 and 2.
        Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.


        Constraints:

        1 <= n == nums.length <= 10^5
        1 <= nums[i] <= 10^S5
 */
public class Solution {


    public static void main(String[] args) {

    }
    int ans;
    int n;
    int[] nums;
    ArrayList<Integer> div;

    public int sortableIntegers(int[] nums) {
        ans = 0;
        n = nums.length;
        this.nums = nums;
        div = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                div.add(i);
                if (i != n / i) {
                    div.add(n / i);
                }
            }

        }

        for (int i : div) {
            if (check(i)) {
                ans += i;
            }
        }

        return ans;
    }

    boolean check(int k) {
        int prev = -1;

        for (int i = 0; i < n; i += k) {
            int curMax = 0;
            int curMin = 1000000;
            int down = 0;

            for (int j = 0; j < k; j++) {
                int cur = nums[i + j];
                int next = nums[i + (j + 1) % k];

                if (cur > next) {
                    down++;
                }
                curMax = Math.max(curMax, cur);
                curMin = Math.min(curMin, cur);
            }

            if (down > 1) {
                return false;
            }
            if (prev > curMin) {
                return false;
            }

            prev = curMax;
        }

        return true;
    }
}
