package String.S_07_check_repeated_string_with_k_replacements;

import java.util.HashMap;
/*
        Check Repeated Substring with K Replacements
        Difficulty: Medium
        Given a string s and an integer k, check if it is possible to convert s to a string that is repetition of a
        consicutive substring with k characters else returns false. In order to convert we can replace one substring of
        length k with any k characters.

        Note:  In one operation, you can replace any substring of length k whose starting index i (0-based) satisfies i % k == 0
        with any sequence of k characters.

        Examples:

        Input: s = "abcbedabcabc",  k = 3
        Output: true
        Explanation: Replace "bed" with "abc" so that the whole string becomes repetition of "abc".
        Input: s = "bdac", k = 2
        Output: true
        Explanation: The string can be divided into substrings of length k: ["bd", "ac"].
        Since there are exactly two substrings and both are different, we can make them identical by replacing one
        substring with the other. Hence, the result is true.
        Input: s = "abcdabcd", k = 2
        Output: false
        Explanation: Since we are allowed to change only one instance, we cannot covert.
        Constraints:
        2 ≤ k ≤ s.size() ≤ 10^5
 */

public class Solution {
    public static void main(String[] args) {
        String s = "abcbedabcabc";
        int k = 3;
        System.out.println(kSubstr(s, k));
    }
    static boolean kSubstr(String s, int k) {
        // code here
        HashMap<String, Integer> mp = new HashMap<>();
        int n = s.length();

        if (k <= 0 || n % k != 0)
            return false;

        for (int i = 0; i < s.length(); i += k) {
            String sub = s.substring(i, i + k);
            mp.put(sub, mp.getOrDefault(sub, 0) + 1);
        }
        if (mp.size() == 1)
            return true;

        if (mp.size() > 2)
            return false;
        for (int val : mp.values()) {
            if (val == 1)
                return true;
        }

        return false;
    }
}
