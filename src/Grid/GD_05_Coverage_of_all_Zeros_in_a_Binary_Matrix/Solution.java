package Grid.GD_05_Coverage_of_all_Zeros_in_a_Binary_Matrix;

/*
        Coverage of all Zeros in a Binary Matrix
        Difficulty: Easy
        Given a binary matrix mat[][] containing only 0s and 1s,find the total coverage of all 0's.
        The coverage of a particular 0 cell is defined by checking 1's in its four directions (left, right, up, and down).
        For each direction, if there is at least one 1 anywhere between the 0 and the boundary of the matrix,
        the coverage increases by one.

        Return the sum of the coverage values for all 0 cells in the matrix.

        Examples:

        Input : mat[][] = [[1, 1, 1, 0],
                          [1, 0, 0, 1]]
        Output : 8
        Explanation: Coverage of first zero is 2. Coverages of other two zeros is 3 Total coverage = 2 + 3 + 3 = 8
        2056958046
        Input: mat[][] = [[0, 1, 0],
                       [0, 1, 1],
                       [0, 0, 0]]
        Output: 6
        Explanation: Total Coverage is 1 + 2 + 1 + 0 + 1 + 1 = 6

        Input: mat[][] = [[0, 1]]
        Output: 1
        Explanation: There are only 1 coverage. Therefore answer for this test case is 1.
        Constraints:
        1 ≤ matrix.size,
        matrix[0].size ≤ 100
 */
public class Solution {
    public static void main(String[] args) {

    }
    int findCoverage(int[][] mat) {
        // code here
        int n = mat.length;
        int m = mat[0].length;

        int[][] l = new int[n][m];
        int[][] r = new int[n][m];
        int[][] u = new int[n][m];
        int[][] d = new int[n][m];

        //left
        for(int i = 0; i < n; i++){
            int find = 0;
            for(int j = 0; j < m; j++){
                l[i][j] = find;
                if(mat[i][j] == 1) find = 1;

            }
        }

        //right
        for(int i = 0; i < n; i++){
            int find = 0;
            for(int j = m -1; j >= 0; j--){
                r[i][j] = find;
                if(mat[i][j] == 1) find = 1;

            }
        }

        //up
        for(int j = 0; j < m; j++){
            int find = 0;
            for(int i = 0; i < n; i++){
                u[i][j] = find;
                if(mat[i][j] == 1) find = 1;

            }
        }

        for(int j = 0; j < m; j++){
            int find = 0;
            for(int i = n - 1; i >= 0; i--){
                d[i][j] = find;
                if(mat[i][j] == 1) find = 1;

            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0){
                    ans = ans + l[i][j] + r[i][j] + u[i][j] + d[i][j];
                }
            }
        }

        return ans;
    }
}
