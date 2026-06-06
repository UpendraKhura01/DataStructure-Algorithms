package Recursion.Non_attacking_Black_and_White_Knights;
/*
        Non-Attacking Black and White Knights
        Difficulty: Medium
        Given two integers n and m representing the dimensions of a chessboard,
         find the number of ways to place one black knight and one white knight on the chessboard such that they cannot attack each other.

        Note:

        The knights have to be placed on different squares.
        A knight can move two squares horizontally and one square vertically (L shaped),
        or two squares vertically and one square horizontally (L shaped).
        The knights attack each other if one can reach the other in one move.
        Examples:

        Input: n = 2, m = 2
        Output: 12
        Explanation: There are 12 ways we can place a black and a white Knight on this chessboard such that they cannot attack each other.
        Input: n = 2, m = 3
        Output: 26
        Explanation: There are 26 ways we can place a black and a white Knight on this chessboard such that they cannot attack each other.
        Constraints:
        1 ≤ n ≤ 200
        1 ≤ m ≤ 225
 */

public class Solution {
    public static void main(String[] args) {

    }

/*******************************************************Normal Way*******************************************************/
    int numOfWays(int n, int m) {
        // code here
        int total = n * m;

        int attacked = 0;
        int[] x = { 1, 2, 2, 1, -1, -2, -2, -1 };
        int[] y = { 2, 1, -1, -2, -2, -1, 1, 2 };

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < 8; k++){
                    int newx = i + x[k];
                    int newy = j + y[k];
                    if(newx >= 0 && newx < n && newy < m && newy >= 0){
                        attacked++;

                    }
                }
            }
        }
        return total * (total - 1) - attacked;
    }
/****************************************************Constant Time Way****************************************************/
    int numOfWays1(int n, int m) {
        // code here
        int total = n * m;

        int attacking = 0;

        if(n >= 2 && m >= 3){
            attacking += 4 * (n - 1) * (m - 2);

        }
        if(n >= 3 && m >= 2){
            attacking  += 4 * (n - 2) * (m - 1);
        }

        return total * (total - 1) - attacking;
    }

}
