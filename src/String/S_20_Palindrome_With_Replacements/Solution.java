package String.S_20_Palindrome_With_Replacements;

/*
        Palindrome with Replacement

        Difficulty: Medium

        Given a string s consisting of lowercase English letters and the character '?',

        Replace every '?' with a lowercase English letter such that the resulting string is a palindrome.
        If it is possible to form a palindrome, return the minimum possible sum of the absolute ASCII differences
        between every pair of adjacent characters in the resulting string.
        If it is not possible to form a palindrome, return -1.
        Examples:

        Input: s = a???c??c????
        Output: 4
        Explanation: One optimal palindrome is "abbbccccbbba". The sum of the absolute ASCII differences between adjacent
        characters is 1 + 0 + 0 + 1 + 0 + 0 + 0 + 1 + 0 + 0 + 1 = 4, which is the minimum possible.
        Input: s = a???c??c???c
        Output: -1
        Explanation: It is not possible to replace the '?' characters to make the string a palindrome because the first
        and last characters are different and cannot be changed.
        Constraints:

        1 ≤ s.size() ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int minimumSum(String s) {
        // code here
        int n = s.length();
        if (n % 2 != 0) {
            return - 1;
        }

        StringBuilder sb = new StringBuilder(s);

        int f = 0;
        int l = n - 1;

        while (f < l) {
            char c1 = s.charAt(f);
            char c2 = s.charAt(l);

            if (c1 == '?' || c2 == '?') {
                if (c1 != '?') {
                    sb.setCharAt(l, c1);
                }
                else if (c2 != '?') {
                    sb.setCharAt(f, c2);
                }

            }
            else if (c1 != c2) {
                return - 1;
            }
            f++;
            l--;
        }

        for(int i = 1; i < n; i++){
            if(sb.charAt(i) == '?' && sb.charAt(i - 1) != '?'){
                sb.setCharAt(i, sb.charAt(i - 1));
            }
        }

        for(int i = n - 2; i >= 0; i--){
            if(sb.charAt(i) == '?' && sb.charAt(i + 1) != '?'){
                sb.setCharAt(i, sb.charAt(i + 1));
            }
        }
        if(sb.charAt(0) == '?'){
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int cur = sb.charAt(i);
            int pre = sb.charAt(i - 1);
            ans += Math.abs(cur - pre);
        }

        return ans;
    }
}
