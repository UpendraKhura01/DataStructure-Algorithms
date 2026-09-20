package Grid.GD_19_Largest_SubSquare_Surrounded_By_X;

/*
        Largest Subsquare Surrounded by X

        Difficulty: Medium
        Given a square matrix mat[][] of size n * n, where each cell contains either 'X' or 'O'.
        Find the size of the largest square submatrix whose boundary is completely surrounded by 'X'.
        The cells inside the submatrix can contain either 'X' or 'O'. Only the four sides of the submatrix must contain 'X'.

        Return side length of the largest such square submatrix.


        Note: A square of size 1 is valid if its only cell is 'X'. If no such square submatrix exists, return 0.

        Examples:

        Input: mat[][] = [[X, X, X, O],[X, O, X, X],[X, X, X, O],[X, O, X, X]]

        Output: 3
        Explanation: Here, the input represents following matrix of size 4 x 4

        The square submatrix starting at (0,0) and ending at (2,2) is the largest submatrix surrounded by X.
        Therefore, size of that matrix would be 3.
        Input: mat[][] = [[X, X],[X, X]]

        Output: 2
        Explanation: The largest square submatrix surrounded by X is the whole input matrix.
        Constraints:

        1 ≤ n, mat.size(), mat[i].size() ≤ 400
        mat consists only of the characters O and X
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int[][] down;
    int[][] right;
    public int largestSubsquare(char mat[][]) {
        // code here
        n = mat.length;

        right = new int[n + 1][n + 1];
        down = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = mat[i][j];
                if (c == 'X') {
                    right[i][j] = (j == 0) ? 1 : right[i][j - 1] + 1;
                    down[i][j] = (i == 0) ? 1 : down[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = right[i][j];

                for (int k = size; k > 0; k--) {
                    int nj = j - k + 1;
                    int ni = i + k - 1;
                    if (check(ni, nj, j, k)) {
                        ans = Math.max(ans, k);
                        break;
                    }
                }
            }
        }
        return ans;
    }
    private boolean check(int ni, int nj, int j, int size) {
        if (nj >= 0 && ni < n && down[ni][j] >= size
                && right[ni][j] >= size && down[ni][nj] >= size) {
            return true;
        }
        return false;
    }
}
