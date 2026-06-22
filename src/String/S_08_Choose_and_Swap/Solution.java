package String.S_08_Choose_and_Swap;
/*
        Choose and Swap
        Difficulty: Medium
        Given a string s of lowercase English letters, you can swap all occurrences of any two distinct characters at most once.
        Return the lexicographically smallest string after this operation.
        Examples:

        Input: s = "ccad"
        Output: "aacd"
        Explanation: In ccad, we choose a and c and after doing the replacement operation once,
        we get aacd and this is the lexicographically smallest string possible.
        Input: s = "abba"
        Output: "abba"
        Explanation: In abba, we can get baab after doing the replacement operation once for a and b
        but that is not lexicographically smaller than abba. So, the answer is abba.
        Constraints:
        1 ≤ |s| ≤ 10^5
 */

public class Solution {
    public static void main(String[] args) {

    }
    String chooseSwap(String s) {
        // code here
        int n = s.length();
        int[] first = new int[26 + 1];

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == 0) {
                first[c] = i + 1;
            }
        }
        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);
            for (int j = 0; j < ch - 'a'; j++) {

                if (first[j] - 1 > i) {

                    char a = (char) (j + 'a');

                    for (int k = 0; k < n; k++) {

                        if (arr[k] == a)
                            arr[k] = ch;
                        else if (arr[k] == ch)
                            arr[k] = a;
                    }
                    return new String(arr);
                }
            }
        }

        return s;
    }

}
