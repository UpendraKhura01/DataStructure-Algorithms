package Dynamic_Programming.DP_14_Count_non_decreasing_num_groups;

/*
Question: Count Non-Decreasing Number Groupings

        Given a string s consisting only of digits, split it into contiguous groups. Each group forms a number.

        A grouping is valid if the numbers are in non-decreasing order.

        Return the total number of valid groupings.

        Example
        Input:
        s = "112"

        Possible groupings:
        1 | 1 | 2  -> [1, 1, 2] valid
        1 | 12     -> [1, 12] valid
        11 | 2     -> [11, 2] invalid
        112        -> [112] valid

        Output:
        3
 */
public class Solution {
    public static void main(String[] args) {


    }
    static int validGroups(String s) {
        int firstDigit = s.charAt(0) - '0';
        return helper(1, s, 0, firstDigit);
    }

    static int helper(int idx, String s, long prev, long curr) {
        if (idx == s.length()) {
            return curr >= prev ? 1 : 0;
        }

        int digit = s.charAt(idx) - '0';

        // take: join current digit with curr
        int take = helper(idx + 1, s, prev, curr * 10 + digit);

        // not take: cut here and start a new number
        int notTake = 0;
        if (curr >= prev) {
            notTake = helper(idx + 1, s, curr, digit);
        }

        return take + notTake;
    }
}
