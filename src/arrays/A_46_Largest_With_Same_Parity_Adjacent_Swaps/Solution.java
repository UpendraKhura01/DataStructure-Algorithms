package arrays.A_46_Largest_With_Same_Parity_Adjacent_Swaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
        Largest with Same Parity Adjacent Swaps

        Difficulty: Medium
        Given an array arr[], you are allowed to swap two adjacent elements, arr[i] and arr[i + 1] only if their sum is even.
        Find the lexicographically largest array that can be obtained by performing this operation any number of times,
        including zero times.

        Note: An array X is lexicographically larger than another array Y if, at the first position where they differ,
        X has the larger element.

        Examples:

        Input: arr[] = [1, 3, 5]
        Output: [5, 3, 1]
        Explanation: We can swap any adjacent pair as all have even sum. All possible obtainable arrays are [1, 3, 5],
        [1, 5, 3], [3, 1, 5], [3, 5, 1], [5, 1, 3] and [5, 3, 1] The array [5, 3, 1] is lexicographically largest.

        Input: arr[] = [1, 3, 4, 2]
        Output: [3, 1, 4, 2]
        Explanation: [1, 3, 4, 2], [1, 3, 2, 4], [3, 1, 2, 4] and [3, 1, 4, 2] are all possible,
        among which the last one is lexicographically largest one.

        Constraints:

        1 ≤ arr.size() ≤ 105
        0 ≤ arr[i] ≤ 105
        Array may contain duplicate elements.

 */
public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<Integer> lexicographicallyLargest(int[] arr) {
        // code here
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0;
        List<Integer> cur;

        while(i < n){
            cur = new ArrayList<>();
            if(arr[i] % 2 == 0){
                while(i < n && arr[i] % 2 == 0){
                    cur.add(arr[i]);
                    i++;
                }
            }
            else{
                while(i < n && arr[i] % 2 != 0){
                    cur.add(arr[i]);
                    i++;
                }
            }

            cur.sort(Collections.reverseOrder());
            ans.addAll(cur);

        }

        return ans;

    }
}
