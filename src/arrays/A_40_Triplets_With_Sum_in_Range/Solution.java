package arrays.A_40_Triplets_With_Sum_in_Range;

import java.util.Arrays;
/*
        Triplets with Sum in Range

        Difficulty: Medium
        Given an array arr[]  and a range from l to r, the task is to count the number of triplets having a sum in the range [l, r].

        Examples :

        Input: arr = [8, 3, 5, 2], l = 7, r = 11
        Output: 1
        Explanation: There is only one triplet [2, 3, 5] having sum 10 in range [7, 11].
        Input: arr = [5, 1, 4, 3, 2], l = 2, r = 7
        Output: 2
        Explanation: There are two triplets having sum in range, [1,4,2] and [1,3,2].
        Constraints:
        1 ≤ arr.size≤ 10^3
        1 ≤ arr[i] ≤ 10^3
        1 ≤ l ≤ r ≤ 10^9

 */

public class Solution {
    public static void main(String[] args) {

    }
    int[] arr;
    public int countTriplets(int[] arr, int l, int r) {
        // code here
        Arrays.sort(arr);
        this.arr = arr;

        return solve(r) - solve(l - 1);
    }
    private int solve(int range){
        int n = arr.length;

        int count = 0;

        for(int i = 0; i < n - 2; i++){
            int left = i + 1;
            int right = n - 1;
            while(left < right){
                int sum = arr[left] + arr[right] + arr[i];
                if(sum <= range){
                    count += right - left;
                    left++;
                }else{
                    right--;
                }

            }
        }

        return count;
    }
}
