package Math.Balancing_With_Distinct_Powers;

/*
        Balancing with Distinct Powers

        Difficulty: Easy
        Given a simple weighing scale with two pans, a target weight b,
        and a set of weights where each weight is a distinct power of a, find if the scale can be balanced such that:

        b + (some powers of a) = (some other powers of a)

        Note: Exactly one weight is available for each power of a, so each power can be used at most once.

        Examples:

        Input: a = 4, b = 11
        Output: true
        Explanation: 11 + 4 + 1 = 16. So, target = 11 can be balanced using powers of 4.
        Input: a = 3, b = 5
        Output: true
        Explanation: 5 + 3 + 1 = 9. So, target = 5 can be balanced using powers of 3.
        Constraints:

        2 ≤ a ≤ 10^9
        1 ≤ b ≤ 10^9
 */
public class Solution {
    public static void main(String[] args) {

    }
    boolean balancePan(int a, int b) {
        while (b > 0) {
            int rem = b % a;

            if (rem == 0) {
                b /= a;
            } else if (rem == 1) {
                b = (b - 1) / a;
            } else if (rem == a - 1) {
                b = (b + 1) / a;
            } else {
                // If remainder cannot be resolved with -1, 0, or +1 adjustments
                return false;
            }
        }
        return true;
    }
}
