package Grid.GD_11_Largest_Odd_Sqaures_With_Limited_Ones;

import java.util.ArrayList;
/*
        Largest Odd Squares with Limited 1s

        Difficulty: Medium
        Given a binary matrix mat[][] of size n*m and an integer k, process a list of queries queries[][].
        Each query contains coordinates [i, j] of the center of a square.

        For every query, find the side length of the largest odd-sized square centered at cell (i, j) such that
        the square contains at most k ones.
         A square centered at (i, j) expands outward symmetrically in all four directions by the same number of cells,
         so its side length is always odd.
        Note: If no odd-sized square centered at the given cell satisfies the condition of containing at most k ones,
        return -1 for that query.

        Examples:

        Input: mat[][] = [[1, 0, 1, 0, 0], [1, 0, 1, 1, 1], [1, 1, 1, 1, 1], [1, 0, 0, 1, 0]], queries[][] = [[1, 2]], k = 9
        Output: [3]
        Explanation: The largest odd-sized square centered at (1, 2) is the 3 × 3 square spanning rows 0 to 2 and columns 1 to 3.
        It contains 6 ones, which is at most k = 9. Hence, the answer is 3.
        Input: mat[][] = [[1, 1, 1], [1, 1, 1], [1, 1, 1]], queries[][] = [[1, 1], [2, 2]], K = 9
        Output: [3, 1]
        Explanation: For query (1, 1), the largest valid square is the entire 3 × 3 matrix, which contains 9 ones. Hence,
        the answer is 3.
        For query (2, 2), no expansion is possible without going outside the matrix, so only the 1 × 1 square centered at (2, 2) is valid.
        Hence, the answer is 1.
        Constraints:
        1 ≤ mat.size(), mat[0].size() ≤ 500
        1 ≤ queries.size() ≤ 104
        0 ≤ queries[q][0] < mat.size()
        0 ≤ queries[q][1] < mat[0].size()
        0 ≤ k ≤ mat.size() * mat[0].size()
 */

public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int m;
    int k;
    ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        // code here

        n = mat.length;
        m = mat[0].length;

        this.k = k;

        int[][] prefix = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1]
                        - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        ArrayList<Integer> res = new ArrayList<>();

        for(int[] q : queries){
            int i = q[0];
            int j = q[1];

            int l = 0;
            int r = i;
            int ans = -1;

            //binary search
            while(l <= r){
                int mid = l + (r - l)/2;
                if(check(prefix, i, j, mid)){
                    ans = (2 * mid) + 1;
                    l = mid + 1;
                }
                else{
                    r = mid - 1;
                }
            }
            res.add(ans);

        }
        return res;

    }
    private boolean check(int[][] prefix, int r, int c, int size){
        int top = r - size;
        int bottom = r + size;
        int right= c + size;
        int left = c - size;

        if(top < 0 || bottom >= n || left < 0 || right >= m){
            return false;
        }

        int one = prefix[bottom + 1][right + 1] - prefix[top][right + 1] - prefix[bottom + 1][left] + prefix[top][left];
        if(one <= k){
            return true;
        }
        return false;
    }
}
