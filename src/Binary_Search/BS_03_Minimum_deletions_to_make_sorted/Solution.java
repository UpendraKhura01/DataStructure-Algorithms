package Binary_Search.BS_03_Minimum_deletions_to_make_sorted;

import java.util.ArrayList;

/*
        Minimum Deletions to Make Sorted
        Difficulty: Easy
        Given an array arr[], find the minimum number of elements to delete so that the remaining elements form
        a strictly increasing sequence in the same order.

        Examples:

        Input: arr[] = [5, 6, 1, 7, 4]
        Output: 2
        Explanation: Removing 1 and 4 leaves [5, 6, 7] which is strictly increasing.
        Input: arr[] = [1, 1, 1]
        Output: 2
        Explanation: Removing any 2 elements leaves [1] which is strictly increasing.
        Constraints:
        1 ≤ n ≤ 10^5
        1 ≤ arr[i] ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;

    public int minDeletions(int[] arr) {
        n = arr.length;
        int len = LIS(arr);
        return n - len;
    }

    // finding LIS length
    private int LIS(int[] arr) {

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);
        for (int i = 1; i < n; i++) {

            if (arr[i] > temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
            }
            else {
                int idx = lowerbound(temp, arr[i]);
                temp.set(idx, arr[i]);
            }
        }

        return temp.size();
    }

    private int lowerbound(ArrayList<Integer> temp, int num) {
        int l = 0;
        int r = temp.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (temp.get(mid) >= num) {
                r = mid - 1;

            }
            else {
                l = mid + 1;
            }
        }

        return l;
    }
}
