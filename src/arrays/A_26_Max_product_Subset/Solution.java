package arrays.A_26_Max_product_Subset;
/*
        Max Product Subset
        Difficulty: Medium
        Given an array arr[], find and return the maximum product possible with the subset of elements present in the array.

        Note:

        The maximum product can be of a single element also.
        Since the product can be large, return it modulo 109 + 7.
        Examples:

        Input: arr[] = [-1, 0, -2, 4, 3]
        Output: 24
        Explanation: Maximum product will be ( -1 * -2 * 4 * 3 ) = 24
        Input: arr[] = [-1, 0]
        Output: 0
        Explanation: Maximum product will be ( -1 * 0) = 0
        Input: arr[] = [5]
        Output: 5
        Explanation: Maximum product will be 5.
        Constraints:
        1 ≤ arr.size() ≤ 2 * 104
        -10 ≤ arr[i] ≤ 10
 */
public class Solution {
    public static void main(String[] args) {

    }
    int findMaxProduct(int[] arr) {
        // code here
        long mod = 1000000000 + 7;
        int n = arr.length;
        int zero = 0;
        int negative = 0;

        int smallest_negative = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                zero++;
            else if (arr[i] < 0) {
                smallest_negative = Math.max(smallest_negative, arr[i]);
                negative++;
            }

        }
        if (n == 1) return arr[0];

        if (zero == n)
            return 0;
        if (negative == 1 && zero == n - 1)
            return 0;

        long ans = 1;
        boolean skip = false;
        for (int i = 0; i < n; i++) {
            if(arr[i] == smallest_negative && negative % 2 != 0 && !skip){
                skip = true;
                continue;
            }
            else if (arr[i] != 0) {
                ans = ((ans * arr[i]) % mod + mod) % mod;
            }
        }



        return (int)ans;
    }
}
