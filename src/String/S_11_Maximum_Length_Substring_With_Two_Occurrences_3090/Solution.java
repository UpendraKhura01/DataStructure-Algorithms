package String.S_11_Maximum_Length_Substring_With_Two_Occurrences_3090;

/*
        3090. Maximum Length Substring With Two Occurrences

        Easy

        Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.


        Example 1:

        Input: s = "bcbbbcba"

        Output: 4

        Explanation:

        The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".
        Example 2:

        Input: s = "aaaa"

        Output: 2

        Explanation:

        The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".


        Constraints:

        2 <= s.length <= 100
        s consists only of lowercase English letters.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] freq = new int[26];
        int l = 0;
        int r = 0;

        int maxlen = 0;

        while(r < n){
            char c = s.charAt(r);
            freq[s.charAt(r) - 'a']++;

            while(freq[c - 'a'] > 2){
                freq[s.charAt(l++) - 'a']--;

            }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }

        return maxlen;
    }
}
