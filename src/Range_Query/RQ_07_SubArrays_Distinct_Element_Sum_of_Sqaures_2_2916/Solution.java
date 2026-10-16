package Range_Query.RQ_07_SubArrays_Distinct_Element_Sum_of_Sqaures_2_2916;

import java.util.HashMap;

/*
        2916. Subarrays Distinct Element Sum of Squares II

        Hard

        You are given a 0-indexed integer array nums.

        The distinct count of a subarray of nums is defined as:

        Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length.
        Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].
        Return the sum of the squares of distinct counts of all subarrays of nums.

        Since the answer may be very large, return it modulo 109 + 7.

        A subarray is a contiguous non-empty sequence of elements within an array.



        Example 1:

        Input: nums = [1,2,1]
        Output: 15
        Explanation: Six possible subarrays are:
        [1]: 1 distinct value
        [2]: 1 distinct value
        [1]: 1 distinct value
        [1,2]: 2 distinct values
        [2,1]: 2 distinct values
        [1,2,1]: 2 distinct values
        The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.
        Example 2:

        Input: nums = [2,2]
        Output: 3
        Explanation: Three possible subarrays are:
        [2]: 1 distinct value
        [2]: 1 distinct value
        [2,2]: 1 distinct value
        The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.


        Constraints:

        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    long m = 1000000000 + 7;
    long[] segTree;
    long[] sqTree;
    long[] lazy;
    int[] nums;
    HashMap<Integer, Integer> mp;

    public int sumCounts(int[] nums) {
        long ans = 0;
        int n = nums.length;
        this.nums = nums;

        segTree = new long[4 * n];
        sqTree = new long[4 * n];
        lazy = new long[4 * n];
        mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int start = -1;
            if (mp.containsKey(nums[i])) {
                start = mp.get(nums[i]);
            }
            int end = i;
            update(0, 0, n - 1, start + 1, end, 1);
            ans = (ans + sqTree[0]) % m;;
            mp.put(nums[i], i);

        }

        return (int)ans;

    }

    void update(int node, int tl, int tr, int ql, int qr, int val) {
        if (lazy[node] != 0) {
            lazyAddition(tl, tr, node, lazy[node]);
            if (tl != tr) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }
        if (tr < ql || tl > qr) {
            return;
        }

        if (tl >= ql && tr <= qr) {
            lazyAddition(tl, tr, node, val);
            if (tl != tr) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }
        int mid = tl + (tr - tl) / 2;
        update(2 * node + 1, tl, mid, ql, qr, val);
        update(2 * node + 2, mid + 1, tr, ql, qr, val);

        segTree[node] = (segTree[2 * node + 1] + segTree[2 * node + 2]) % m;
        sqTree[node] = (sqTree[2 * node + 1] + sqTree[2 * node + 2]) % m;

    }

    void lazyAddition(int l, int r, int node, long val) {
        int len = r - l + 1;

        sqTree[node] = (sqTree[node] + (2 * val * segTree[node]) + (len * val * val)) % m;
        segTree[node] = (segTree[node] + (len * val)) % m;

    }
}
