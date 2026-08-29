package Dynamic_Programming.DP_29_Lexiographocally_Smallest_Pallindromic_Permutation_Greater_Than_Target_3734;

/*
        3734. Lexicographically Smallest Palindromic Permutation Greater Than Target

        Hard

        You are given two strings s and target, each of length n, consisting of lowercase English letters.

        Return the lexicographically smallest string that is both a palindromic permutation of s and strictly greater than target.
        If no such permutation exists, return an empty string.

        Example 1:

        Input: s = "baba", target = "abba"

        Output: "baab"

        Explanation:

        The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
        The lexicographically smallest permutation that is strictly greater than target is "baab".
        Example 2:

        Input: s = "baba", target = "bbaa"

        Output: ""

        Explanation:

        The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
        None of them is lexicographically strictly greater than target. Therefore, the answer is "".
        Example 3:

        Input: s = "abc", target = "abb"

        Output: ""

        Explanation:

        s has no palindromic permutations. Therefore, the answer is "".

        Example 4:

        Input: s = "aac", target = "abb"

        Output: "aca"

        Explanation:

        The only palindromic permutation of s is "aca".
        "aca" is strictly greater than target. Therefore, the answer is "aca".


        Constraints:

        1 <= n == s.length == target.length <= 300
        s and target consist of only lowercase English letters.
 */
public class Solution {
    public static void main(String[] args) {

    }
    String target;
    int half;
    char mid = '@';
    int[] freq;
    String ans;
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        half = n / 2;

        this.target = target;

        freq = new int[26];
        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }
        int odd = 0;

        for(int i = 0; i < 26; i++){

            if(freq[i] % 2 == 1){

                odd++;
                mid = (char)(i + 'a');

                if(odd > 1){
                    return "";
                }
            }
            freq[i] /= 2;
        }
        if(solve(0, false, new StringBuilder())){
            return ans;
        }
        return "";

    }
    private boolean solve(int idx, boolean isGreater, StringBuilder cur){
        if(idx == half){
            String rev = new StringBuilder(cur).reverse().toString();
            String temp = (mid != '@') ? (cur.toString() + mid + rev) : (cur + rev);

            if(temp.compareTo(target) > 0){
                ans = temp;
                return true;
            }

            return false;

        }

        for(char c = 'a'; c <= 'z'; c++){
            if(freq[c - 'a'] == 0 || (c < target.charAt(idx) && !isGreater)){
                continue;
            }
            cur.append(c);
            freq[c - 'a']--;

            boolean greater = isGreater || (c > target.charAt(idx));
            if(solve(idx + 1, greater, cur)){
                return true;
            }
            cur.deleteCharAt(cur.length() - 1);
            freq[c - 'a']++;


        }
        return false;
    }
}
