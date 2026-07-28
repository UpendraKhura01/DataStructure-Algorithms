package String.S_09_Smallest_Palindromic_Rearrangement_1_3517;
/*
        3517. Smallest Palindromic Rearrangement I

        Medium

        You are given a palindromic string s.
        Return the lexicographically smallest palindromic permutation of s.

        Example 1:

        Input: s = "z"

        Output: "z"

        Explanation:
        A string of only one character is already the lexicographically smallest palindrome.

        Example 2:

        Input: s = "babab"

        Output: "abbba"

        Explanation:
        Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.

        Example 3:

        Input: s = "daccad"

        Output: "acddca"

        Explanation:
        Rearranging "daccad" → "acddca" gives the smallest lexicographic palindrome.



        Constraints:

        1 <= s.length <= 10^5
        s consists of lowercase English letters.
        s is guaranteed to be palindromic.

 */
public class Solution {
    public static void main(String[] args) {

    }
    String smallestPalindrome(String s) {
        int n = s.length();

        int[] freq = new int[26];
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder(s);

        int idx = 0;
        for(int i = 0; i < 26; i++){
            char c = (char)(i + 97);
            while(freq[i] > 1){
                sb.setCharAt(idx, c);
                sb.setCharAt(n - 1 -idx, c);
                idx++;
                freq[i] = freq[i] - 2;
            }
        }
        return sb.toString();
    }
}
