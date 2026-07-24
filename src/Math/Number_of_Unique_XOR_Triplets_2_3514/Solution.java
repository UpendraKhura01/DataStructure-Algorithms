package Math.Number_of_Unique_XOR_Triplets_2_3514;

/*
        3514. Number of Unique XOR Triplets II

        Medium

        You are given an integer array nums.

        A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.

        Return the number of unique XOR triplet values from all possible triplets (i, j, k).



        Example 1:

        Input: nums = [1,3]

        Output: 2

        Explanation:

        The possible XOR triplet values are:

        (0, 0, 0) → 1 XOR 1 XOR 1 = 1
        (0, 0, 1) → 1 XOR 1 XOR 3 = 3
        (0, 1, 1) → 1 XOR 3 XOR 3 = 1
        (1, 1, 1) → 3 XOR 3 XOR 3 = 3
        The unique XOR values are {1, 3}. Thus, the output is 2.

        Example 2:

        Input: nums = [6,7,8,9]

        Output: 4

        Explanation:

        The possible XOR triplet values are {6, 7, 8, 9}. Thus, the output is 4.



        Constraints:

        1 <= nums.length <= 1500
        1 <= nums[i] <= 1500
 */
public class Solution {
    public static void main(String[] args) {

    }
    int uniqueXorTriplets(int[] nums) {
        int max = 3000;
        int n = nums.length;

        boolean[] pair = new boolean[max];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int xor = nums[i] ^ nums[j];
                pair[xor] = true;
            }
        }

        boolean[] ans = new boolean[max];
        for(int i = 0; i < n; i++){
            int num = nums[i];
            for(int j =  0; j < max; j++){
                if(pair[j]){
                    ans[num ^ j] = true;
                }
            }
        }
        int c = 0;
        for(boolean b : ans){
            if(b){
                c++;
            }
        }
        return c;

    }
}
