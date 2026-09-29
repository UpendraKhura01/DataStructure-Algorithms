package Grid.GD_14_Check_If_There_is_a_Valid_Paranthesis_String_Path_2267;

/*
        2267. Check if There Is a Valid Parentheses String Path

        Hard

        A parentheses string is a non-empty string consisting only of '(' and ')'.
        It is valid if any of the following conditions is true:

        It is ().
        It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
        It can be written as (A), where A is a valid parentheses string.
        You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path
        satisfying all of the following conditions:

        The path starts from the upper left cell (0, 0).
        The path ends at the bottom-right cell (m - 1, n - 1).
        The path only ever moves down or right.
        The resulting parentheses string formed by the path is valid.
        Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.



        Example 1:


        Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
        Output: true
           Left Path ()(())            Right Path ((()))
             [ ( ) ( ( ) ) ]             [ ( ( ( ) ) ) ]
              ┌───┬───┬───┐               ┌───┬───┬───┐
              │ █ │ · │ · │               │ █ │ █ │ █ │
              ├───┼───┼───┤               ├───┼───┼───┤
              │ █ │ █ │ · │               │ · │ · │ █ │
              ├───┼───┼───┤               ├───┼───┼───┤
              │ · │ █ │ █ │               │ · │ · │ █ │
              ├───┼───┼───┤               ├───┼───┼───┤
              │ · │ · │ █ │               │ · │ · │ █ │
              └───┴───┴───┘               └───┴───┴───┘

        Explanation: The above diagram shows two possible paths that form valid parentheses strings.
        The first path shown results in the valid parentheses string "()(())".
        The second path shown results in the valid parentheses string "((()))".
        Note that there may be other valid parentheses string paths.


        Example 2:
         ┌───┬───┐
         │ ) │ ) │
         ├───┼───┤
         │ ( │ ( │
         └───┴───┘


        Input: grid = [[")",")"],["(","("]]
        Output: false
        Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 100
        grid[i][j] is either '(' or ')'.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] dr = { 0, 1 };
    int[] dc = { 1, 0 };

    char[][] grid;
    int n;
    int m;
    Boolean[][][] dp;
    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        dp = new Boolean[n][m][n + m + 1];

        return solve(0, 0, 0);
    }

    private boolean solve(int count, int r, int c){
        char ch = grid[r][c];

        if(ch == ')'){
            count--;
        }
        else{
            count++;
        }

        if(count < 0){
            return false;
        }

        if(r == n - 1 && c == m - 1){
            return (count == 0);
        }

        if(dp[r][c][count] != null){
            return dp[r][c][count];
        }


        for(int i = 0; i < 2; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m){
                continue;
            }
            if(solve(count, nr, nc)){
                return dp[r][c][count] = true;
            }
        }

        return dp[r][c][count] = false;
    }
}
