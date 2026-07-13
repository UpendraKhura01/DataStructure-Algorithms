package Math.Sequential_Digits_1291;

import java.util.ArrayList;
import java.util.List;
/*
        1291. Sequential Digits

        Medium

        An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

        Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.



        Example 1:

        Input: low = 100, high = 300
        Output: [123,234]
        Example 2:

        Input: low = 1000, high = 13000
        Output: [1234,2345,3456,4567,5678,6789,12345]


        Constraints:

        10 <= low <= high <= 10^9
 */
public class Solution {
    public static void main(String[] args) {

    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        String s = "123456789";

        int l = String.valueOf(low).length();
        int r = String.valueOf(high).length();

        for (int size = l; size <= r; size++) {

            for (int i = 0; i <= 9 - size; i++) {
                int j = i + size;
                int num = Integer.parseInt(s.substring(i, j));
                if (num <= high && num >= low) {
                    ans.add(num);
                }
                if (num > high) {
                    return ans;
                }
            }
        }


        return ans;
    }
}
