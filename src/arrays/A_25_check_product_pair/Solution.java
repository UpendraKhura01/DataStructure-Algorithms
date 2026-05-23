package arrays.A_25_check_product_pair;

import java.util.HashSet;
/*
        Product Pair
        Difficulty: Medium
        Given an integer array arr[] and an integer target, determine whether there exists a pair of elements
         in the array whose product is equal to target.

        Return true if such a pair exists; otherwise, return false.

        Examples:

        Input: arr[] = [10, 20, 9, 40], target = 400
        Output: true
        Explanation: As 10 * 40 = 400, the answer is true.
        Input: arr[] = [-10, 20, 9, -40], target = 30
        Output: false
        Explanation: No pair exists with product 30.
        Input: arr[] = [-10, 0, 9, -40], target = 0
        Output: true
        Explanation: As -10 * 0 = 0, the answer is true.

        Constraints:
        2 ≤ arr.size ≤ 105
        -108 ≤ arr[i] ≤ 108
        -1018 ≤ target ≤ 1018
 */

public class Solution {
    public static void main(String[] args) {

    }
    static boolean isProduct(int[] arr, long target) {
        // code here
        HashSet<Long> st = new HashSet<>();
        for (int i : arr) {

            if (target == 0) {
                if (i == 0 && !st.isEmpty()) return true;
                if (i != 0 && st.contains(0L)) return true;
            }

            else if(i != 0 && target % i == 0) {
                long x = (target / i);
                if (st.contains(x))
                    return true;
            }
            st.add((long)i);
        }

        return false;
    }
}
