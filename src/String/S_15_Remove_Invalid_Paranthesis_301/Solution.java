package String.S_15_Remove_Invalid_Paranthesis_301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
        301. Remove Invalid Parentheses

        Hard

        Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses,
         to make the input string valid.

        Return a list of unique strings that are valid with the minimum number of removals.
        You may return the answer in any order.



        Example 1:

        Input: s = "()())()"
        Output: ["(())()","()()()"]
        Example 2:

        Input: s = "(a)())()"
        Output: ["(a())()","(a)()()"]
        Example 3:

        Input: s = ")("
        Output: [""]


        Constraints:

        1 <= s.length <= 25
        s consists of lowercase English letters and parentheses '(' and ')'.
        There will be at most 20 parentheses in s.
 */
public class Solution {
    public static void main(String[] args) {

    }
    String s;
    int n;
    Set<String> ans;
    int minRemoval;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        n = s.length();
        ans = new HashSet<>();
        int cnt = 0;
        minRemoval = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                cnt--;
            }
            if (cnt < 0) {
                minRemoval++;
                cnt = 0;
            }

        }
        minRemoval += cnt;

        solve(0, new StringBuilder(), 0);
        return new ArrayList<>(ans);
    }

    private void solve(int i, StringBuilder str, int cnt) {
        if (cnt < 0) {
            return;
        }
        if (i == n) {
            if (cnt == 0 && str.length() == n - minRemoval) {
                ans.add(str.toString());
            }
            return;
        }

        char c = s.charAt(i);

        int add;
        if (c == '(') {
            add = 1;
        } else if (c == ')') {
            add = -1;
        } else {
            add = 0;
        }

        str.append(c);
        solve(i + 1, str, cnt + add);
        str.deleteCharAt(str.length() - 1);

        solve(i + 1, str, cnt);

    }
}
