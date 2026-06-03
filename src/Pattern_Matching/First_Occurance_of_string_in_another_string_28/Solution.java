package Pattern_Matching.First_Occurance_of_string_in_another_string_28;
/*
        Find the Index of the First Occurrence in a String

        Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
        or -1 if needle is not part of haystack.

        Example 1:

        Input: haystack = "sadbutsad", needle = "sad"
        Output: 0
        Explanation: "sad" occurs at index 0 and 6.
        The first occurrence is at index 0, so we return 0.
        Example 2:

        Input: haystack = "leetcode", needle = "leeto"
        Output: -1
        Explanation: "leeto" did not occur in "leetcode", so we return -1.


        Constraints:

        1 <= haystack.length, needle.length <= 104
        haystack and needle consist of only lowercase English characters.
 */

public class Solution {
    public static void main(String[] args) {

    }
    int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        int[] lps = new int[m];
        lps = lpsdo(lps, needle);
        int first = 0;
        int second = 0;

        while((first < n && second < m)){
            if(haystack.charAt(first) == needle.charAt(second)){
                first++;
                second++;
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
        if(second == m) return first - second;

        return  -1;
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
