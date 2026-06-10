package Grid.GD_03_1s_Surrounded_by_0s;


/*
        1s Surrounded by 0s
        Difficulty: Medium
        Given an n × m binary matrix grid[][], find the total count of all cells containing 1 that are unable
        to move out of the grid through a path of adjacent 1s.

        Adjacency means you can only move in four directions: Up, Down, Left, and Right. Diagonal moves are not allowed.
        Assume that the space immediately outside the grid is an open path.
        Any 1 located directly on the outer boundary of the grid (first row, last row, first column, or last column)
        can immediately step out, and any 1 connected to it can follow and also step out of the grid.
        Examples:

        Input: grid[][] = [[0, 0, 0, 0],
                           [1, 0, 1, 0],
                           [0, 1, 1, 0],
                           [0, 0, 0, 0]]
        Output: 3
                           ╔═══╦═══╦═══╦═══╗
                           ║ 0 ║ 0 ║ 0 ║ 0 ║
                           ╠═══╬═══╬═══╬═══╣
                           ║ 1 ║ 0 ║░1░║ 0 ║
                           ╠═══╬═══╬═══╬═══╣
                           ║ 0 ║░1░║░1░║ 0 ║
                           ╠═══╬═══╬═══╬═══╣
                           ║ 0 ║ 0 ║ 0 ║ 0 ║
                           ╚═══╩═══╩═══╩═══╝
        Explanation: The highlighted cells represent the land cells.


        Input: grid[][] = [[1, 1, 0, 0, 0, 1]
                           [0, 1, 1, 0, 1, 0],
                           [0, 0, 0, 1, 1, 0],
                           [0, 0, 0, 1, 1, 0],
                           [0, 1, 0, 1, 0, 0],
                           [1, 1, 0, 0, 0, 1]]
        Output: 6
                        ╔═══╦═══╦═══╦═══╦═══╦═══╗
                        ║ 1 ║ 1 ║ 0 ║ 0 ║ 0 ║ 1 ║
                        ╠═══╬═══╬═══╬═══╬═══╬═══╣
                        ║ 0 ║ 1 ║ 1 ║ 0 ║░1░║ 0 ║
                        ╠═══╬═══╬═══╬═══╬═══╬═══╣
                        ║ 0 ║ 0 ║ 0 ║░1░║░1░║ 0 ║
                        ╠═══╬═══╬═══╬═══╬═══╬═══╣
                        ║ 0 ║ 0 ║ 0 ║░1░║░1░║ 0 ║
                        ╠═══╬═══╬═══╬═══╬═══╬═══╣
                        ║ 0 ║ 1 ║ 0 ║░1░║ 0 ║ 0 ║
                        ╠═══╬═══╬═══╬═══╬═══╬═══╣
                        ║ 1 ║ 1 ║ 0 ║ 0 ║ 0 ║ 1 ║
                        ╚═══╩═══╩═══╩═══╩═══╩═══╝
        Explanation: The highlighted cells represent the land cells.
        425537429
        Constraints:
        1 ≤ n, m ≤ 500
        0 ≤ grid[i][j] ≤ 1
 */

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

    }

    int cntOnes(int[][] grid) {
        // code here
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        Queue<pair> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if (grid[i][0] == 1) {
                vis[i][0] = true;
                q.add(new pair(i, 0));
            }
            if (grid[i][m - 1] == 1) {
                vis[i][m - 1] = true;
                q.add(new pair(i, m - 1));
            }
        }

        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 1) {
                vis[0][i] = true;
                q.add(new pair(0, i));
            }
            if (grid[n - 1][i] == 1) {
                vis[n - 1][i] = true;
                q.add(new pair(n - 1, i));
            }

        }

        int[] x = {0, 1, 0, -1};
        int[] y = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int newi = p.row() + x[i];
                int newj = p.col() + y[i];
                if (newi < n && newi >= 0 && newj < m && newj >= 0 && !vis[newi][newj] && grid[newi][newj] == 1) {
                    vis[newi][newj] = true;
                    q.offer(new pair(newi, newj));
                }
            }

        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    public record pair(int row, int col) {};
}
