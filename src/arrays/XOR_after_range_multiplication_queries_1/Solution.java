package arrays.XOR_after_range_multiplication_queries_1;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 4};
        int[][] queries = {
                {1, 4, 2, 3},
                {0, 2, 1, 2}
        };
        System.out.println(xorAfterQueries(nums, queries));
    }
    static int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int qn = queries.length;
        int MOD = 1000000000 + 7;


        for (int i = 0; i < qn; i++) {

            int idx = queries[i][0];
            int r = queries[i][1];
            int k = queries[i][2];
            int v = queries[i][3];

            while (idx <= r){
                nums[idx] = (int)((long)nums[idx] * v % MOD);
                idx += k;
            }
        }
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }

        return xor;
    }
}
