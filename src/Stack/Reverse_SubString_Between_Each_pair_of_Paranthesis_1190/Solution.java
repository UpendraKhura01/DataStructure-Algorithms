package Stack.Reverse_SubString_Between_Each_pair_of_Paranthesis_1190;

import java.util.Stack;

/*
        1190. Reverse Substrings Between Each Pair of Parentheses

        Medium

        You are given a string s that consists of lower case English letters and brackets.

        Reverse the strings in each pair of matching parentheses, starting from the innermost one.

        Your result should not contain any brackets.



        Example 1:

        Input: s = "(abcd)"
        Output: "dcba"
        Example 2:

        Input: s = "(u(love)i)"
        Output: "iloveu"
        Explanation: The substring "love" is reversed first, then the whole string is reversed.
        Example 3:

        Input: s = "(ed(et(oc))el)"
        Output: "leetcode"
        Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.


        Constraints:

        1 <= s.length <= 2000
        s only contains lower case English characters and parentheses.
        It is guaranteed that all parentheses are balanced.
 */
public class Solution {
    public static void main(String[] args) {

    }
    public String reverseParentheses(String s) {
        int n = s.length();

        Stack<Character> st = new Stack<>();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == ')'){
                StringBuilder sb = new StringBuilder();

                while(st.peek() != '('){
                    sb.append(st.pop());

                }
                st.pop();
                for(int j = 0; j < sb.length(); j++){
                    st.push(sb.charAt(j));
                }
            }
            else{
                st.push(c);
            }

        }

        StringBuilder sb = new StringBuilder();
        for (char ch : st) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
