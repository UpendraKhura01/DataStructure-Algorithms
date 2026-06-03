package Pattern_Matching.Longest_Happy_prefix_1392;
/*
        1392. Longest Happy Prefix
        Hard
        A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).

        Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.

        Example 1:

        Input: s = "level"
        Output: "l"
        Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel").
        The largest prefix which is also suffix is given by "l".
        Example 2:

        Input: s = "ababab"
        Output: "abab"
        Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.


        Constraints:

        1 <= s.length <= 105
        s contains only lowercase English letters.

 */

public class solution {
    public static void main(String[] args) {

    }
    String longestPrefix(String s) {
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
        return s.substring(0,lps[n-1]);
    }
}
