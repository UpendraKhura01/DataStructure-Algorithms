package Pattern_Matching.Search_for_Subarray;

import java.util.ArrayList;
/*
        Search for Subarray
        Difficulty: Hard
        You are given two integer arrays a[] and b[]. Return all the starting indexes of all the occurences of b[] as a subarray in a[].

        Examples:

        Input: a[] = [2, 4, 1, 0, 4, 1, 1], b[] = [4, 1]
        Output: [1, 4]
        Explanation: b[] occurs as a subarray in a[] from index 1 to 2 and from index 4 to 5.

        Input: a[] = [2, 4, 1, 0, 0, 3], b[] = [0]
        Output: [3, 4]
        Explanation: b[] occurs as a subarray in a[] from index 3 to 3 and from index 4 to 4.

        Input: a[] = [1, 3, 5, 3, 0], b[] = [1, 3, 0]
        Output: []
        Explanation: There is no subarray occurs as b[] in a[]
        Constraints:
        1 ≤ a.size() ≤ 106
        1 ≤ b.size() ≤ a.size()
        0 ≤ b[i], a[i] ≤ 104
 */

public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<Integer> search(int[] a, int[] b) {
        // code here
        int n = a.length;
        int m = b.length;
        int[] lps = new int[m];
        lps = lpsdo(m, lps, b);
        int first = 0;
        int second = 0;

        ArrayList<Integer> ans = new ArrayList<>();

        while (first < n) {
            if(a[first] == b[second]){
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
        return ans;

    }

    private int[] lpsdo(int m, int[] lps, int[] b) {
        int pre = 0;
        int suf = 1;
        while (suf < m) {
            if (b[pre] == b[suf]) {
                lps[suf] = pre + 1;
                pre++;
                suf++;

            }
            else {
                if (pre == 0) {
                    lps[suf] = 0;
                    suf++;
                }
                else {
                    pre = lps[pre - 1];
                }
            }
        }
        return lps;
    }
}
