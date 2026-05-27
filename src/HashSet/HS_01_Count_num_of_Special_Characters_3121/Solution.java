package HashSet.HS_01_Count_num_of_Special_Characters_3121;

import java.util.Arrays;
import java.util.HashSet;

/*
        3121. Count the Number of Special Characters II
        Medium

        You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word,
        and every lowercase occurrence of c appears before the first uppercase occurrence of c.
        Return the number of special letters in word.

        Example 1:

        Input: word = "aaAbcBC"

        Output: 3

        Explanation:

        The special characters are 'a', 'b', and 'c'.

        Example 2:

        Input: word = "abc"

        Output: 0

        Explanation:

        There are no special characters in word.

        Example 3:

        Input: word = "AbBCab"

        Output: 0

        Explanation:

        There are no special characters in word.
        Constraints:

        1 <= word.length <= 2 * 105
        word consists of only lowercase and uppercase English letters.

 */

public class Solution {
    public static void main(String[] args) {

    }

    /**************************************************Using HashSets**************************************************/
    static int numberOfSpecialChars(String word) {
        HashSet<Character> capital = new HashSet<>();
        HashSet<Character> invalid = new HashSet<>();
        HashSet<Character> counted = new HashSet<>();

        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                capital.add(c);
            } else if (capital.contains((char) (c - 32))) {
                invalid.add(c);
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {

            char c = word.charAt(i);
            if (capital.contains((char) (c - 32)) && !invalid.contains(c) && !counted.contains(c)) {
                count++;
                counted.add(c);
            }

        }

        return count;
    }


    /**************************************************Optimized way**************************************************/
    static int numberOfSpecialChars1(String word) {
        int[] last = new int[26];
        int[] first = new int[26];
        Arrays.fill(last, -1);
        Arrays.fill(first, -1);

        int n = word.length();

        for(int i = 0; i < n; i++){
            char c = word.charAt(i);
            if(c >= 'a' && c <= 'z'){
                last[c - 'a'] = i;
            }
            else{
                int idx = c - 'A';
                if(first[idx] == -1){
                    first[idx] = i;
                }
            }
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(last[i] != -1 && first[i] != -1 && last[i] < first[i]){
                count++;
            }
        }
        return count;
    }
}
