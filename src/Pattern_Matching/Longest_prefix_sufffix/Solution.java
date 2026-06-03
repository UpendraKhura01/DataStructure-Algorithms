package Pattern_Matching.Longest_prefix_sufffix;
/*
        Longest Prefix Suffix
        Difficulty: Hard
        Given a string s, of lowercase english alphabets, find the length of the longest proper prefix which is also a suffix.
        Note: Prefix and suffix can be overlapping but they should not be equal to the entire string.

        Examples :

        Input: s = "abab"
        Output: 2
        Explanation: The string "ab" is the longest prefix and suffix.

        Input: s = "aabcdaabc"
        Output: 4
        Explanation: The string "aabc" is the longest prefix and suffix.

        Input: s = "aaaa"
        Output: 3
        Explanation: "aaa" is the longest prefix and suffix.

        Constraints:
        1 ≤ s.size() ≤ 106
        s contains only lowercase English alphabets.
*/

public class Solution {
    public static void main(String[] args) {

    }
    static int getLPSLength(String s) {
        // code here
        int n = s.length();

        int[] lps = new int[n];
        int pre = 0;
        int suf = 1;

        while(suf < n){
            if(s.charAt(pre) == s.charAt(suf)){
                lps[suf] = pre + 1;
                pre++;
                suf++;
            }
            else{
                if(pre == 0){
                    lps[suf] = 0;
                    suf++;
                }
                else{
                    pre = lps[pre - 1];
                }
            }
        }
        return lps[n - 1];
    }
}
