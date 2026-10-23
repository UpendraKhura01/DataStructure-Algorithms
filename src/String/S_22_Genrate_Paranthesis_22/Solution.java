package String.S_22_Genrate_Paranthesis_22;

import java.util.ArrayList;
import java.util.List;
/*
        22. Generate Parentheses

        Medium

        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



        Example 1:

        Input: n = 3
        Output: ["((()))","(()())","(())()","()(())","()()()"]
        Example 2:

        Input: n = 1
        Output: ["()"]


        Constraints:

        1 <= n <= 8
 */
public class Solution {
    public static void main(String[] args) {

    }
    List<String> ans;
    int n;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        this.n = n;
        solve(0, 0, "");
        return ans;
    }
    private void solve(int o, int c, String s){

        if(o > n || c > n){
            return;
        }

        if(o == n && c == n){
            ans.add(s);
        }


        solve(o + 1, c, s + '(');

        if(o > c){
            solve(o, c + 1, s + ')');
        }
    }
}

