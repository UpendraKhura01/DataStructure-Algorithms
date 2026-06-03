package HashMap.HM_03_Subarray_Frequency_count_queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
        Subarray Frequency Count Queries
        Difficulty: Medium

        Given an array arr[] of n integers and a 2D array queries[][] representing q queries,
        where each queries[i] consists of three integers: l, r, and x.
        For each query determine how many times the element x appears in the arr[] from index l to r (both inclusive).

        Return a list of integers where the i-th value represents the answer to the i-th query.

        Examples:

        Input: arr[] = [1, 2, 1, 3, 1, 2, 3], queries[][] = [[0, 4, 1], [2, 5, 2], [1, 6, 3], [0, 6, 5]]
        Output: [3, 1, 2, 0]
        Explanation:
        query [0, 4, 1] -> Subarray = [1, 2, 1, 3, 1], 1 appears 3 times
        query [2, 5, 2] -> Subarray = [1, 3, 1, 2], 2 appears 1 time
        query [1, 6, 3] -> Subarray = [2, 1, 3, 1, 2, 3] 3 appears 2 times
        query [0, 6, 5] -> Subarray = [1, 2, 1, 3, 1, 2, 3],  5 appears 0 times

        Input: arr[] = [11, 21, 51, 101, 11, 51], queries[][] = [[0, 4, 11], [2, 5, 51]]
        Output: [2, 2]
        Explanation:
        query [0, 4, 11] -> Subarray = [11, 21, 51, 101, 11], 11 appears 2 times
        query [2, 5, 51] -> Subarray = [51, 101, 11, 51], 51 appears 2 times

        Constraints:
        1 ≤ arr.size(), queries.size() ≤ 105
        1 ≤ arr[i], queries[i][2] ≤ 105
        0 ≤ queries[i][0] ≤ queries[i][1] < arr.size()
 */

public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<Integer> freqInRange(int[] arr, int[][] queries) {
        // code here
        int n = arr.length;
        int m = queries.length;
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();

        for(int i = 0; i < n; i++){
            int a = arr[i];
            mp.computeIfAbsent(a, k -> new ArrayList<>()).add(i);
        }
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int l = queries[i][0];
            int r = queries[i][1];
            int x = queries[i][2];
            if(mp.get(x) == null){
                ans.add(0);
                continue;
            }
            int left = lowerbound(mp.get(x), l);
            int right = upperbound(mp.get(x), r);
            ans.add(right - left);

        }
        return ans;

    }
    static int lowerbound(ArrayList<Integer> list, int target){
        int low = 0;
        int high = list.size();
        while(low < high){
            int mid = (low + high) / 2;

            if(list.get(mid) >= target){
                high = mid;
            }
            else low = mid + 1;
        }
        return low;
    }
    static int upperbound(ArrayList<Integer> list, int target){
        int low = 0;
        int high = list.size();
        while(low < high){
            int mid = low + (high - low) / 2;
            if(list.get(mid) <= target){
                low = mid + 1;
            }
            else high = mid;
        }
        return low;
    }
}
