package Binary_Search.Binary_Searchable_Count;
/*
        Binary Searchable Count
        Difficulty: Medium
        Given an array arr[] consisting of n distinct integers,
        find the maximum count of integers that are binary searchable in the given array.
        Binary searchable elements are determined using the standard Binary Search implementation described below.

        Initially l is 0 and r is size of array - 1
        while(l <= r), compute mid as floor of (l + r)/2 and compare with mid.
        If the target element is same as mid, return true. Else if mid is smaller, change l = mid + 1, else change r = mid - 1.
        For example:

        In arr[] = [2, 1, 3, 4, 6, 5], the element 5 is not binary searchable. During Binary Search,
        the search eventually reaches the subarray containing 6, and since 6 > 5, the search moves left (r = mid - 1), causing the element 5 to be skipped.
        In arr[] = [2, 1, 3, 4, 5, 6], the element 5 is binary searchable because the standard Binary Search process
        eventually reaches and finds 5.
        Examples:

        Input: arr[] = [1, 3, 2]
        Output: 2
        Explanation: arr[0], arr[1] can be found.
        Input: arr[] = [2, 1, 3, 5, 4, 6]
        Output: 3
        Explanation: arr[1], arr[3], arr[5] can be found.
        Constraints:
        1 ≤ n ≤ 10^5
        1 ≤ arr[i] ≤ 10^5
 */

public class Solution {
    public static void main(String[] args) {

    }
    int binarySearchable(int[] arr) {
        // code here
        int n = arr.length;

        int left = 0;
        int right = n - 1;
        return binarysearch(arr, 0, n - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private int binarysearch(int[] arr, int left, int right, int left_limit, int right_limit) {
        if (right < left) {
            return 0;
        }
        int cur = 0;

        int mid = left + (right - left) / 2;
        if (arr[mid] < right_limit && arr[mid] > left_limit) {
            cur = 1;
        }
        int l = binarysearch(arr, left, mid - 1, left_limit, Math.min(right_limit, arr[mid]));

        int r = binarysearch(arr, mid + 1, right, Math.max(left_limit, arr[mid]), right_limit);

        return cur + l + r;
    }
}
