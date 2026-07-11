package Grid.GD_07_Longest_possible_Route_in_a_Matrix_with_Hurdles;

/*
        Longest Possible Route in a Matrix with Hurdles
        Difficulty: Medium
        Given a binary matrix mat[][] of size n × m containing values 0 and 1, and four integers xs, ys, xd,
        and yd representing the source cell (xs, ys) and destination cell (xd, yd),
        find the length of the longest possible path from the source cell to the destination cell. From any cell,
        you can move to its adjacent cells in the up, down, left, and right directions.

        1 represents a traversable cell.
        0 represents a blocked cell that cannot be visited.
        A cell can be visited at most once in a path.
        If the destination cannot be reached from the source, return -1.
        Examples:

        Input: mat[][] = [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 0, 1, 1, 0, 1, 1, 0, 1],[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]],
        xs = 0, ys = 0, xd = 1, yd = 7
        Output: 24
        Explanation: The longest valid path from (0, 0) to (1, 7) without revisiting any cell has length 24.

        Input: mat[][] = [[1, 0, 0, 1, 0],[0, 0, 0, 1, 0],[0, 1, 1, 0, 0]], xs = 0, ys = 3, xd = 2, yd = 2
        Output: -1
        Explanation: The destination cell (2, 2) cannot be reached from the source cell (0, 3), so the answer is -1.

        Constraints:
        1 ≤ n, m ≤ 10
        mat[i][j] == 0 or mat[i][j] == 1
        The source and destination cells are always inside the matrix.
 */
public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int m;
    int xs, ys, xd, yd;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int[][] mat;
    public int longestPath(int[][] mat, int xs, int ys, int xd, int yd) {
        // code here
        this.mat = mat;
        n = mat.length;
        m = mat[0].length;
        this.xs = xs;
        this.ys = ys;
        this.xd = xd;
        this.yd = yd;
        boolean[][] vis = new boolean[n][m];
        return solve(xs, ys, vis);

    }
    private int solve(int i, int j, boolean[][] vis) {
        if (i == xd && j == yd) {
            return 0;
        }

        if (mat[xs][ys] == 0 || mat[xd][yd] == 0)
            return - 1;

        vis[i][j] = true;

        int cur_dist = -1;

        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];

            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                continue;
            }
            if (mat[ni][nj] == 0 || vis[ni][nj])
                continue;

            cur_dist = Math.max(cur_dist, solve(ni, nj, vis));

        }

        vis[i][j] = false;

        if (cur_dist == -1)
            return - 1;

        return 1 + cur_dist;

    }
}
