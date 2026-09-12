package arrays.A_41_Max_Product_SubSequence_of_Size_K;

import java.util.Arrays;
/*
        Max Product Subsequence of Size K
        Solved
        Difficulty: Medium
        Given an array arr[] of integers and an integer k, find a subsequence of size k whose product is maximum
        among all possible subsequences of size k. Return the maximum product that can be obtained.

        Examples:

        Input: arr[] = [1, 2, 0, 3], k = 2
        Output: 6
        Explanation: Subsequence containing elements {2, 3} gives maximum product: 2*3 = 6
        Input: arr[] = [1, 2, -1, -3, -6, 4], k = 4
        Output: 144
        Explanation: Subsequence containing {2, -3, -6, 4} gives maximum product: 2*(-3)*(-6)*4 = 144
        Constraints:

        arr.size() ≤ 30
        -10 ≤ arr[i] ≤ 10
        1 ≤ k ≤ arr.size()
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxProduct(int[] arr, int k) {
        int n = arr.length;


        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int product = 1;

        if (k % 2 != 0 && arr[right] <= 0) {
            for (int i = 0; i < k; i++) {
                product *= arr[right - i];
            }
            return product;
        }

        if (k % 2 != 0) {
            product *= arr[right];
            right--;
            k--;
        }

        while (k > 0) {

            int leftProduct = arr[left] * arr[left + 1];
            int rightProduct = arr[right] * arr[right - 1];

            if (leftProduct > rightProduct) {
                product *= leftProduct;
                left += 2;
            } else {
                product *= rightProduct;
                right -= 2;
            }
            k -= 2;
        }

        return product;
    }
}
