package Pattern_Matching.Search_pattern;

import java.util.ArrayList;
/*
        Search Pattern
        Difficulty: Hard
        Given two strings, a text string txt and a pattern string pat, both consisting of lowercase English alphabets. Return the starting indices (0-based) of all the occurrences of the pattern string pat in the text string txt.

        Note: Return an empty list in case of no occurrences of pattern.

        Examples:

        Input: txt = "geeksforgeeks", pat = "geek"
        Output: [0, 8]
        Explanation: The string "geek" occurs twice in txt, one starts at index 0 and the other at index 8.
        Input: txt = "abesdu", pat = "edu"
        Output: []
        Explanation: There's no substring "edu" present in txt.
        Input: txt = "aabaacaadaabaaba", pat = "aaba"
        Output: [0, 9, 12]
        Explanation:

        Constraints:
        1 ≤ txt.size() ≤ 105
        1 ≤ pat.size() ≤ txt.size()
 */

public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<Integer> search(String needle, String haystack) {
        // code here
        int n = haystack.length();
        int m = needle.length();

        ArrayList<Integer> ans = new ArrayList<>();

        int[] lps = new int[m];
        lps = lpsdo(lps, needle);
        int first = 0;
        int second = 0;

        while((first < n && second < m)){
            if(haystack.charAt(first) == needle.charAt(second)){
                first++;
                second++;
                if(second == m){
                    ans.add(first - second);
                    second = lps[second - 1];
                }
            }
            else{
                if(second == 0){
                    first++;
                }
                else{
                    second = lps[second - 1];
                }
            }
        }

        return  ans;
    }

    private int[] lpsdo(int[] lps, String s){
        int n = s.length();
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
        return lps;
    }
}
