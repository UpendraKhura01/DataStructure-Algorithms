package String.S_05_Substring_with_Max_Zero_One_Diff;
/*
        Substring with Max Zero-One Diff
        Difficulty: Medium
        Given a binary string s consisting of 0s and 1s. Find the maximum difference of the number of 0s and
         the number of 1s (number of 0s – number of 1s) in a substring of the string.

        Note: In the case of all 1s, the answer will be -1.

        Examples:

        Input : s = "11000010001"
        Output : 6
        Explanatio: From index 2 to index 9, there are 7 0s and 1 1s, so number of 0s - number of 1s is 6.
        Input: s = "111111"
        Output: -1
        Explanation: s contains 1s only
        Constraints:
        1 ≤ s.size() ≤ 105
 */

public class Solution {
    public static void main(String[] args) {

    }
    int maxSubstring(String s) {
        // code here
        int n = s.length();
        if (isall1(s))
            return - 1;
        int ans = -1;
        int cur = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                cur++;
            }
            else
                cur--;

            if (cur < 0) {
                cur = 0;
            }

            ans = Math.max(ans, cur);
        }
        return ans;
    }
    private boolean isall1(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                return false;
        }
        return true;
    }
}
