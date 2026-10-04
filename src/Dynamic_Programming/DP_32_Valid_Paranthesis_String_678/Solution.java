package Dynamic_Programming.DP_32_Valid_Paranthesis_String_678;

/*
            678. Valid Parenthesis String

            Medium

            Given a string s containing only three types of characters: '(', ')' and '*',
            return true if s is valid.

            The following rules define a valid string:

            Any left parenthesis '(' must have a corresponding right parenthesis ')'.
            Any right parenthesis ')' must have a corresponding left parenthesis '('.
            Left parenthesis '(' must go before the corresponding right parenthesis ')'.
            '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or
            an empty string "".


            Example 1:

            Input: s = "()"
            Output: true
            Example 2:

            Input: s = "(*)"
            Output: true
            Example 3:

            Input: s = "(*))"
            Output: true
            Example 4:

            Input: s = "("
            Output: false


            Constraints:

            1 <= s.length <= 100
            s[i] is '(', ')' or '*'.
 */
public class Solution {
    public static void main(String[] args) {

    }
    String s;
    int n;
    Boolean[][] dp;
    public boolean checkValidString(String s) {
        this.s = s;
        n = s.length();
        if(n == 1 && s.charAt(0) != '*'){
            return false;
        }
        dp = new Boolean[n + 1][n + 1];
        return solve(0, 0);

    }
    private boolean solve(int i, int count){
        if(count < 0){
            return false;
        }
        if(i == n){
            return (count == 0);
        }
        if(dp[i][count] != null){
            return dp[i][count];
        }
        char c = s.charAt(i);
        boolean res = false;
        if(count < 0){
            res = false;
        }

        if (c == '(') {
            res = solve(i + 1, count + 1);
        } else if (c == ')') {
            res = solve(i + 1, count - 1);
        } else {
            res = solve(i + 1, count + 1) || solve(i + 1, count - 1) || solve(i + 1, count);
        }

        return dp[i][count] = res;
    }
}
