package Two_Pointer_Technique.TP_04_Max_Frequencies_with_K_increaments;

import java.util.Arrays;

/*
        Maximum Frequency with K Increments

        Difficulty: Medium
        Given an integer array arr[]. In one operation, you can choose an index and increment its value by 1.

        Find the maximum possible frequency of any element after performing at most k operations.

        Examples:

        Input: arr[] = [2, 2, 4], k = 4
        Output: 3
        Explanation: Apply two increment operations on index 0 and two operations on index 1 to make arr[]= [4, 4, 4]. Frequency of 4 is 3.
        Input: arr[] = [7, 7, 7, 7], k = 5
        Output: 4
        Explanation: The frequency of 7 is already 4, so no operations are needed.
        Constraints:

        1 ≤ arr.size() ≤ 10^5
        1 ≤ arr[i] ≤ 10^6
        0 ≤ k ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxFrequency(int[] arr, int k) {
        // code here
        Arrays.sort(arr);
        int n = arr.length;
        int ans = 0;
        int sum = 0;
        int i = 0;
        int j = 0;
        while (j < n) {
            sum += arr[j];
            int num = arr[j];

            while (i < j && (num * (j - i + 1) - sum) > k) {
                sum -= arr[i];

                i++;
            }

            ans = Math.max(ans, j - i + 1);
            j++;
        }

        return ans;
    }
}
