package Grid.GD_20_Form_Coils_In_a_Matrix;

import java.util.ArrayList;
/*
        Coils in Matrix

        Difficulty: Medium

        Given a positive integer n, consider a 4n * 4n matrix filled with integers from 1 to (4n) * (4n) in
        row-major order (left to right, top to bottom). Form two coils from the matrix:

        The first coil starts from the top-left cell (0, 0) and spirals inward.
        The second coil starts from the bottom-right cell (4n - 1, 4n - 1) and spirals inward in the opposite direction.
        Return these two coils in the same order.

        Examples:

        Input: n = 1
        Output: [[1, 5, 9, 13, 14, 15, 11, 7], [16, 12, 8, 4, 3, 2, 6, 10]]
        Explanation: The matrix is

        So, the two coils are as given in the Output.
        Input: n = 2
        Output:
        [[1, 9, 17, 25, 33, 41, 49, 57, 58, 59, 60, 61, 62, 63, 55, 47, 39, 31, 23, 15, 14, 13, 12, 11, 19, 27, 35, 43, 44, 45, 37, 29],
         [64, 56, 48, 40, 32, 24, 16, 8, 7, 6, 5, 4, 3, 2, 10, 18, 26, 34, 42, 50, 51, 52, 53, 54, 46, 38, 30, 22, 21, 20, 28, 36]]
        Explanation:

        Constraints:

        1 ≤ n ≤ 20
 */
public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<ArrayList<Integer>> formCoils(int n) {
        // code here
        n = 4 * n;
        int[][] mat = new int[n][n];
        int count = 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = count++;
            }
        }

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int dir = 0;// down first

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> last = new ArrayList<>();

        int r = 0;
        int c = 0;
        first.add(mat[0][0]);
        for(int i = 0; i < n - 1; i++){
            r += dr[dir];
            c += dc[dir];
            first.add(mat[r][c]);
        }



        for(int i = n - 2; i >= 2; i -= 2){

            for(int turn = 0; turn < 2; turn++){
                dir = (dir + 1) % 4;

                for(int k = 0; k < i; k++){
                    r += dr[dir];
                    c += dc[dir];
                    first.add(mat[r][c]);
                }
            }
        }
        int total = n * n + 1;

        for(int i : first){
            last.add(total - i);
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ans.add(first);
        ans.add(last);

        return ans;
    }
}
