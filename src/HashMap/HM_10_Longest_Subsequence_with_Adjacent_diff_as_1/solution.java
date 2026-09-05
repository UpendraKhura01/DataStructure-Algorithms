package HashMap.HM_10_Longest_Subsequence_with_Adjacent_diff_as_1;

import java.util.HashMap;
/*
        Longest Subsequence with Adjacent Diff as 1

        Difficulty: Medium
        Given an array arr[] with n elements. find the longest subsequence such that the absolute difference
        between adjacent elements is one.

        Examples:

        Input : arr[] = [10, 9, 4, 5, 4, 8, 6]
        Output : 3
        Explanation: Longest subsequences with difference 1 are [10, 9, 8], [4, 5, 4] and [4, 5, 6].
        Input : arr[] = [1, 2, 3, 2, 3, 7, 2, 1]
        Output : 7
        Explanation:  Longest subsequences with difference 1 is [1, 2, 3, 2, 3, 2, 1].
        Constraints:

        1 ≤ arr.size(), arr[i] ≤ 10^6
 */

public class solution {
    public static void main(String[] args) {

    }
    int longestSubseq(int[] arr) {
        // code here
        HashMap<Integer, Integer> mp = new HashMap<>();

        int ans = 0;
        for(int num : arr){

            int prev = mp.getOrDefault(num - 1, 0);
            int next = mp.getOrDefault(num + 1, 0);

            int cur = Math.max(prev, next) + 1;
            mp.put(num, cur);

            ans = Math.max(ans, cur);

        }
        return ans;
    }
}
