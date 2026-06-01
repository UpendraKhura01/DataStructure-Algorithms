package Recursion.R_04_Remove_Invalid_Paranthesis;

import java.util.*;
/*
        Remove Invalid Parentheses
        Difficulty: Hard
        Given a string s consisting of lowercase letters and parentheses '(' and ')'.

        A string is considered valid if:

        Every opening parenthesis '(' has a corresponding closing parenthesis ')'.
        Parentheses are properly nested.
        Remove the minimum number of invalid parentheses from s so that the resulting string becomes valid.
        Return all the possible distinct valid strings in lexicographically sorted order.

        Examples :

        Input:  = "()())()"
        Output: ["(())()", "()()()"]
        Explanation:
        The string "()())()" has one extra ')', making it invalid. By removing one ')', we can make it valid in two ways:
        Remove the 1st index ')' -> "(())()"
        Remove the 4th index ')' -> "()()()"
        Both are valid and require the minimum removals.
        Input: s = "(a)())()"
        Output: ["(a())()", "(a)()()"]
        Explanation:
        We remove one ')' (minimum removals) to make it valid. Possible valid results:
        Remove a ')' -> "(a())()"
        Remove another ')' -> "(a)()()"
        Input: s = ")("
        Output: [""]
        Explanation: The string ")(" is invalid. Removing both parentheses (minimum removals) gives an empty string "",
        which is valid.
        Constraints:
        1 ≤ |s| ≤ 20
        s consists of lowercase English letters and parentheses '(' and ')'
 */

public class Solution {
    public static void main(String[] args) {

    }
    List<String> validParenthesis(String s) {
        // code here
        int n = s.length();

        int have_open = 0;
        int have_close = 0;

        // Calculate the minimum number of invalid '(' and ')'
        // that must be removed to make the expression valid
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '(')
                have_open++;
            else if(ch == ')')
                if(have_open > 0) have_open--;
                else have_close++;
        }

        Set<String> ans = new TreeSet<>();
        helper(0, have_open,0, have_close, new StringBuilder(), ans, s, n);
        return new ArrayList<>(ans);
    }

    private void helper(int idx, int have_open, int preserve, int have_close,
                        StringBuilder cur, Set<String> ans, String s, int n){

        if(idx == n){

            // Valid expression formed:
            // 1. No more brackets left to remove
            // 2. No unmatched '(' remaining
            if(have_open == 0 && have_close == 0 && preserve == 0){
                ans.add(cur.toString());
            }
            return;
        }

        char ch = s.charAt(idx);

        if(ch == '('){

            if(have_open > 0){

                // remove this '(' if we still need to delete extra opens

                //Not Take
                //deleting if we have open brackets
                helper(idx + 1, have_open - 1, preserve, have_close, cur, ans, s, n);
            }

            // keep this '(' and increase unmatched open count

            //Take
            //preserving the brackets so adding it to the preserve num so that we can check it for future close brackets validity
            cur.append(ch);
            helper(idx + 1, have_open, preserve + 1, have_close, cur, ans, s, n);
            cur.deleteCharAt(cur.length() - 1);
        }

        else if(ch == ')'){

            if(have_close > 0){

                // remove this ')' if we still need to delete extra closes

                //Not Take
                //removing the close brackets if we have it
                helper(idx + 1, have_open, preserve, have_close - 1, cur, ans, s, n);

            }

            // keep ')' only if there is an unmatched '(' available
            if(preserve > 0){

                //Take
                //if we have a open which hasn't been paired with a closing bracket, take it
                cur.append(ch);
                helper(idx + 1, have_open, preserve - 1, have_close, cur, ans, s, n);
                cur.deleteCharAt(cur.length() - 1);
            }
        }

        else {

            // normal characters are always preserved
            cur.append(ch);
            helper(idx + 1, have_open, preserve, have_close, cur, ans, s, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
