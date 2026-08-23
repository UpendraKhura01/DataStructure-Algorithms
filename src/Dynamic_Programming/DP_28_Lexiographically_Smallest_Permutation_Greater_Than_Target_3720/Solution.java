package Dynamic_Programming.DP_28_Lexiographically_Smallest_Permutation_Greater_Than_Target_3720;

/*
        3720. Lexicographically Smallest Permutation Greater Than Target

        Medium

        You are given two strings s and target, both having length n, consisting of lowercase English letters.

        Return the lexicographically smallest permutation of s that is strictly greater than target.
        If no permutation of s is lexicographically strictly greater than target, return an empty string.

        A string a is lexicographically strictly greater than a string b (of the same length) if in the first position
        where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.



        Example 1:

        Input: s = "abc", target = "bba"

        Output: "bca"

        Explanation:

        The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
        The lexicographically smallest permutation that is strictly greater than target is "bca".
        Example 2:

        Input: s = "leet", target = "code"

        Output: "eelt"

        Explanation:

        The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
        The lexicographically smallest permutation that is strictly greater than target is "eelt".
        Example 3:

        Input: s = "baba", target = "bbaa"

        Output: ""

        Explanation:

        The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
        None of them is lexicographically strictly greater than target. Therefore, the answer is "".


        Constraints:

        1 <= s.length == target.length <= 300
        s and target consist of only lowercase English letters.

 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] freq;
    int n;
    StringBuilder ans = new StringBuilder();
    String target;
    public String lexGreaterPermutation(String s, String target) {
        this.n = s.length();
        this.target = target;
        freq = new int[26];
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        solve(0, false, new StringBuilder());
        return ans.toString();
    }
    private boolean solve(int idx, boolean isGreater, StringBuilder cur){
        if(idx == n){
            if(isGreater){
                ans = cur;
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
