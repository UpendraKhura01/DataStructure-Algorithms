package HashMap.HM_02_Sort_by_set_bit_count;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
/*
        Sort by Set Bit Count
        Difficulty: Easy
        Given an array arr[] of integers, sort the array (in descending order) according to count of set bits
         in binary representation of array elements.

        Note: For integers having same number of set bits in their binary representation,
         sort according to their position in the original array i.e., a stable sort.

        Examples:
        Input: arr[] = [5, 2, 3, 9, 4, 6, 7, 15, 32]
        Output: [15, 7, 5, 3, 9, 6, 2, 4, 32]
        Explanation: The integers in their binary representation are:
        15 - 1111
        7  - 0111
        5  - 0101
        3  - 0011
        9  - 1001
        6  - 0110
        2  - 0010
        4  - 0100
        32 - 10000
        hence the non-increasing sorted order is: [15], [7], [5, 3, 9, 6], [2, 4, 32]
        Input: arr[] = [1, 2, 3, 4, 5, 6]
        Output: [3, 5, 6, 1, 2, 4]
        Explanation: The integers in their binary representation are:
        3  - 0011
        5  - 0101
        6  - 0110
        1  - 0001
        2  - 0010
        4  - 0100
        hence the non-increasing sorted order is: [3, 5, 6], [1, 2, 4]
        Constraints:
        1 ≤ arr.size() ≤ 105
        1 ≤ arr[i] ≤ 106
 */
public class Solution {
    public static void main(String[] args) {

    }

    /***********************************************Without Inbuilt Function**********************************************/

    static ArrayList<Integer> sortBySetBitCount(int[] arr) {
        // code here
        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();
        int maxbit = 0;
        for (int i : arr) {
            int bits = countbit(i);
            mp.putIfAbsent(bits, new ArrayList<>());

            mp.get(countbit(i)).add(i);

            maxbit = Math.max(maxbit, bits);

        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = maxbit; i > 0; i--) {
            if (mp.containsKey(i)) {
                for (int j : mp.get(i)) {
                    ans.add(j);
                }
            }
        }
        return ans;
    }

    private static int countbit(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 2 == 1)
                count++;

            n /= 2;
        }
        return count;
    }
    /***********************************************With Inbuilt function***********************************************/

    static ArrayList<Integer> sortBySetBitCount2(int[] arr) {
        // code here
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ans.add(arr[i]);
        }
        ans.sort(Comparator.comparingInt(a -> -Integer.bitCount(a)));

        return ans;
    }

}
