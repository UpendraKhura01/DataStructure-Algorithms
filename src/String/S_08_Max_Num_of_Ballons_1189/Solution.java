package String.S_08_Max_Num_of_Ballons_1189;

/*
        1189. Maximum Number of Balloons

        Easy

        Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

        You can use each character in text at most once. Return the maximum number of instances that can be formed.


        Example 1:

        Input: text = "nlaebolko"
        Output: 1
        Example 2:

        Input: text = "loonbalxballpoon"
        Output: 2
        Example 3:

        Input: text = "leetcode"
        Output: 0


        Constraints:

        1 <= text.length <= 10^4
        text consists of lower case English letters only.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;

        for (char c : text.toCharArray()) {
            switch (c) {
                case 'b': b++; break;
                case 'a': a++; break;
                case 'l': l++; break;
                case 'o': o++; break;
                case 'n': n++; break;
            }
        }

        l = l / 2;
        o = o / 2;

        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));

    }
}
