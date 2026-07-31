package String.S_10_MIn_num_of_push_to_type_word_2_3016;

import java.util.Arrays;
/*
        3016. Minimum Number of Pushes to Type Word II

        Medium

        You are given a string word containing lowercase English letters.

        Telephone keypads have keys mapped with distinct collections of lowercase English letters,
        which can be used to form words by pushing them. For example, the key 2 is mapped with ["a","b","c"],
        we need to push the key one time to type "a", two times to type "b", and three times to type "c" .

        It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters.
        The keys can be remapped to any amount of letters, but each letter must be mapped to exactly one key.
        You need to find the minimum number of times the keys will be pushed to type the string word.

        Return the minimum number of pushes needed to type word after remapping the keys.

        An example mapping of letters to keys on a telephone keypad is given below. Note that 1, *, #, and 0 do not map to any letters.

        +-----------+-----------+-----------+
        |     1     |   2 abc   |   3 def   |
        +-----------+-----------+-----------+
        |   4 ghi   |   5 jkl   |   6 mno   |
        +-----------+-----------+-----------+
        |  7 pqrs   |   8 tuv   |  9 wxyz   |
        +-----------+-----------+-----------+
        |     *     |     0     |     #     |
        +-----------+-----------+-----------+


        Example 1:
        +-----------+-----------+-----------+
        |     1     |   2 afg   |   3 bhi   |
        +-----------+-----------+-----------+
        |   4 cjk   |   5 dlm   |   6 eno   |
        +-----------+-----------+-----------+
        |  7 pqrs   |   8 tuv   |  9 wxyz   |
        +-----------+-----------+-----------+
        |     *     |     0     |     #     |
        +-----------+-----------+-----------+

        Input: word = "abcde"
        Output: 5
        Explanation: The remapped keypad given in the image provides the minimum cost.
        "a" -> one push on key 2
        "b" -> one push on key 3
        "c" -> one push on key 4
        "d" -> one push on key 5
        "e" -> one push on key 6
        Total cost is 1 + 1 + 1 + 1 + 1 = 5.
        It can be shown that no other mapping can provide a lower cost.

        Example 2:
        +-----------+-----------+-----------+
        |     1     |   2 xc    |   3 yd    |
        +-----------+-----------+-----------+
        |  4 zeabj  |  5 fklmn  |  6 gopq   |
        +-----------+-----------+-----------+
        |  7 hrstu  |   8 ivw   |     9     |
        +-----------+-----------+-----------+
        |     *     |     0     |     #     |
        +-----------+-----------+-----------+

        Input: word = "xyzxyzxyzxyz"
        Output: 12
        Explanation: The remapped keypad given in the image provides the minimum cost.
        "x" -> one push on key 2
        "y" -> one push on key 3
        "z" -> one push on key 4
        Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
        It can be shown that no other mapping can provide a lower cost.
        Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key, but to map all the letters.


        Example 3:
        +-----------+-----------+-----------+
        |     1     |    2 a    |    3 b    |
        +-----------+-----------+-----------+
        |   4 cz    |  5 dwxy   |  6 etuv   |
        +-----------+-----------+-----------+
        |  7 fpqrs  |  8 gmno   |  9 ihjkl  |
        +-----------+-----------+-----------+
        |     *     |     0     |     #     |
        +-----------+-----------+-----------+

        Input: word = "aabbccddeeffgghhiiiiii"
        Output: 24
        Explanation: The remapped keypad given in the image provides the minimum cost.
        "a" -> one push on key 2
        "b" -> one push on key 3
        "c" -> one push on key 4
        "d" -> one push on key 5
        "e" -> one push on key 6
        "f" -> one push on key 7
        "g" -> one push on key 8
        "h" -> two pushes on key 9
        "i" -> one push on key 9
        Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
        It can be shown that no other mapping can provide a lower cost.


        Constraints:

        1 <= word.length <= 10^5
        word consists of lowercase English letters.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];

        for(int i = 0; i < n; i++){
            char c = word.charAt(i);
            freq[c - 'a']++;

        }
        Arrays.sort(freq);

        int ans = 0;
        for(int i = 25; i >= 18; i--){
            ans += freq[i];
        }
        for(int i = 17; i >= 10; i--){
            ans += freq[i] * 2;
        }
        for(int i = 9; i >= 2; i--){
            ans += freq[i] * 3;
        }
        ans += (freq[0] * 4);
        ans += (freq[1] * 4);

        return ans;

    }
}
