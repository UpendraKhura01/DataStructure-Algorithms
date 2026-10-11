package arrays.A_42_Sort_Two_parts_Sorted;

/*
        Sort Two Parts Sorted

        Difficulty: Easy
        Given an integer array arr[] where the two parts around a break point are individually sorted,
        merge them into a single sorted array.  The break point can be anywhere in the array,
        including at the beginning or end.

        Examples:

        Input: arr[] = [2, 3, 8, -1, 7, 10]
        Output: [-1, 2, 3, 7, 8, 10]
        Explanation: [2, 3, 8] and [-1, 7, 10] are sorted in the original array. The overall sorted version is [-1 2 3 7 8 10].
        Input: arr[] = [-4, 6, 9, -1, 3]
        Output: [-4, -1, 3, 6, 9]
        Explanation: [-4, 6, 9] and [-1, 3] are sorted in the original array. The overall sorted version is [-4 -1 3 6 9].
        Input: arr[] = [10, 20, 30]
        Output: [10, 20, 30]
        Explanation: One part is empty and the other part is whole array which is already sorted.
        Constraints:

        1 ≤ arr.size() ≤ 10^6
        -105 ≤ arr[i] ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    void mergeTwoParts(int[] arr) {
        // code here
        int n = arr.length;

        int mid = -1;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                mid = i + 1;
            }
        }
        mid = (mid == -1) ? 0 : mid;

        int[] temp = new int[n];
        int i = 0;
        int j = mid;
        int idx = 0;
        while (i < mid && j < n) {
            if (arr[i] <= arr[j]) {

                temp[idx++] = arr[i];
                i++;
            }
            else {
                temp[idx++] = arr[j];
                j++;
            }
        }

        while (i < mid) {

            temp[idx++] = arr[i];
            i++;
        }
        while (j < n) {
            temp[idx++] = arr[j];
            j++;
        }

        for(int k = 0; k < n; k++){
            arr[k] = temp[k];
        }
    }
}
