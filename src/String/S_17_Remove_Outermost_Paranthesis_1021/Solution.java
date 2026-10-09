package String.S_17_Remove_Outermost_Paranthesis_1021;

/*
        1021. Remove Outermost Parentheses

        Easy

        A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings,
        and + represents string concatenation.

        For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
        A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to
        split it into s = A + B, with A and B nonempty valid parentheses strings.

        Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are
        primitive valid parentheses strings.

        Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.



        Example 1:

        Input: s = "(()())(())"
        Output: "()()()"
        Explanation:
        The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
        After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
        Example 2:

        Input: s = "(()())(())(()(()))"
        Output: "()()()()(())"
        Explanation:
        The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
        After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
        Example 3:

        Input: s = "()()"
        Output: ""
        Explanation:
        The input string is "()()", with primitive decomposition "()" + "()".
        After removing outer parentheses of each part, this is "" + "" = "".


        Constraints:

        1 <= s.length <= 10^5
        s[i] is either '(' or ')'.
        s is a valid parentheses string.
 */
public class Solution {
    public static void main(String[] args) {

    }
    String removeOuterParentheses(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        int level = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (level > 0) {
                    sb.append(c);
                }
                level++;
            } else {
                level--;
                if (level > 0) {
                    sb.append(c);
                }

            }

        }
        return sb.toString();
    }
}
