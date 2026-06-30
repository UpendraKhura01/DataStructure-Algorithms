package Two_Pointer_Technique.TP_02_Num_of_substring_containing_all_three_characters_1358;

/*
        1358. Number of Substrings Containing All Three Characters

        Medium

        Given a string s consisting only of characters a, b and c.

        Return the number of substrings containing at least one occurrence of all these characters a, b and c.



        Example 1:

        Input: s = "abcabc"
        Output: 10
        Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc",
        "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
        Example 2:

        Input: s = "aaacb"
        Output: 3
        Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
        Example 3:

        Input: s = "abc"
        Output: 1


        Constraints:

        3 <= s.length <= 5 x 10^4
        s only consists of a, b or c characters.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int numberOfSubstrings(String s) {
        int n = s.length();

        int[] f = new int[3];
        int l = 0;
        int r = 0;
        int cnt = 0;
        while (r < n) {
            char c = s.charAt(r);
            f[c - 'a']++;
            while (f[0] > 0 && f[1] > 0 && f[2] > 0) {
                f[s.charAt(l) - 'a']--;
                l++;
                cnt += n - r;
            }

            r++;
        }

        return cnt;
    }
}
