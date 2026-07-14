package Grid.GD_08_Tower_Reaching_Both_Stations;

import java.util.ArrayDeque;
import java.util.Queue;
/*
        Towers Reaching Both Stations
        Difficulty: Medium
        Given a matrix mat[][] of size n x m, where mat[i][j] represents the signal strength of a communication tower.
        Two control stations monitor the network:

        Station P covers the top and left boundaries of the grid.
        Station Q covers the bottom and right boundaries of the grid.
        A signal can propagate from a tower to one of its neighbouring towers in the four directions (North, South, East, and West)
        only if the neighbouring tower has a signal strength less than or equal to that of the current tower.

        Determine the number of towers (x, y) from which a signal can eventually reach both Station P and Station Q.
        Any tower located on a boundary covered by a station can transmit directly to that station.

        Examples:

        Input: mat[][] = [[1, 2, 2, 3, 5], [3, 2, 3, 4, 4], [2, 4, 5, 3, 1], [6, 7, 1, 4, 5], [5, 1, 1, 2, 4]]
        Output: 7
        Explanation:

        (0, 4) & (4, 0) are part of both P & Q
        (1, 3) reaches P using (1,3)->(0,3) and Q using (1,3)->(1,4)
        (1, 4) reaches P using (1,4)->(1,3)->(1,2)->(0,2) and it is on Q
        (2, 2) reaches P using (2,2)->(2,1)->(2,0) and Q using (2,2)->(2,3)->(2,4)
        (3, 0) is on P and reaches Q using (3,0)->(4,0)
        (3, 1) reaches P using (3,1)->(3,0) and Q using (3,1)->(4,1)
        Input: mat[][] = [[2, 2], [2, 2]]
        Output: 4
        Explanation: In the following example, all cells allow signals to propagate to both the stations.
        Constraints:
        1 ≤ n, m ≤ 10^3
        1 ≤ mat[i][j] ≤ 10^3
 */

public class Solution {
    public static void main(String[] args) {

    }
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int[][] mat;
    int n;
    int m;
    public int countCoordinates(int[][] mat) {
        n = mat.length;
        m = mat[0].length;
        this.mat = mat;

        boolean[][] visp = new boolean[n][m];
        boolean[][] visq = new boolean[n][m];

        Queue<int[]> pq = new ArrayDeque<>();
        Queue<int[]> qq = new ArrayDeque<>();

        // Station P -> top row
        for (int j = 0; j < m; j++) {

            visp[0][j] = true;
            pq.offer(new int[] {0, j});

        }

        // Station P -> left column
        for (int i = 0; i < n; i++) {

            visp[i][0] = true;
            pq.offer(new int[] {i, 0});

        }

        // Station Q -> bottom row
        for (int j = 0; j < m; j++) {
            visq[n - 1][j] = true;
            qq.offer(new int[] {n - 1, j});

        }

        // Station Q -> right column
        for (int i = 0; i < n; i++) {
            visq[i][m - 1] = true;
            qq.offer(new int[] {i, m - 1});

        }

        bfs(pq, visp);
        bfs(qq, visq);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visp[i][j] && visq[i][j])
                    ans++;
            }
        }

        return ans;
    }

    private void bfs(Queue<int[]> queue, boolean[][] vis) {
        int n = mat.length;
        int m = mat[0].length;



        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                        !vis[nr][nc] &&
                        mat[nr][nc] >= mat[r][c]) {

                    vis[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
    }
}
