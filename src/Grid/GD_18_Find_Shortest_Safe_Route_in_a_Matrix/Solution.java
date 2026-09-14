package Grid.GD_18_Find_Shortest_Safe_Route_in_a_Matrix;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Shortest Safe Route in Grid
Solved
Difficulty: MediumAccuracy: 50.58%Submissions: 48K+Points: 4
Given a 2D matrix mat[][] of size n × m, where each cell is either 0 (landmine) or 1 (safe), find the minimum number of steps required to travel from any cell in the leftmost column to any cell in the rightmost column.

You can move only in four directions: up, down, left, and right.
A cell is unsafe if it contains a landmine or is directly adjacent (up, down, left, or right) to a landmine, and such cells must be avoided.
Return -1 if no safe path exists.
Examples:

Input: mat[][] = [[1, 0, 1, 1, 1]
                , [1, 1, 1, 1, 1],
                 [1, 1, 1, 1, 1],
                 [1, 1, 1, 0, 1],
                 [1, 1, 1, 1, 0]]

Output: 6
Explanation: We can see that length of shortest safe route is 6.

Input: mat[][] = [[1, 1, 1, 1, 1],
                  [1, 1, 0, 1, 1],
                  [1, 1, 1, 1, 1]]

Output: -1
Explanation: There is no possible path from first column to last column.
Constraints:

1 ≤ n, m ≤ 10^3
0 ≤ mat[i][j] ≤ 1
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    boolean[][] grid;
    int n;
    int m;
    int shortestPath(int[][] mat) {
        // code here
        n = mat.length;
        m = mat[0].length;

        grid = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    grid[i][j] = true;
                    for (int k = 0; k < 4; k++) {

                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (check(nr, nc)) {
                            grid[nr][nc] = true;
                        }
                    }

                }
            }
        }

        return bfs();
    }

    int bfs() {

        boolean[][] vis = new boolean[n][m];

        Queue<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            if (grid[r][0] == false) {
                vis[r][0] = true;
                q.add(new int[] {r, 0, 1});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[1] == m - 1) {
                return cur[2];

            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (check(nr, nc) && grid[nr][nc] == false && vis[nr][nc] == false) {
                    int newDist = cur[2] + 1;

                    vis[nr][nc] = true;
                    q.add(new int[] {nr, nc, newDist});
                }
            }
        }

        return - 1;
    }

    boolean check(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= m) {
            return false;
        }
        return true;
    }
}
