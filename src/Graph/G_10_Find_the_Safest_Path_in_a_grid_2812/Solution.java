package Graph.G_10_Find_the_Safest_Path_in_a_grid_2812;

import java.util.*;
/*
        2812. Find the Safest Path in a Grid

        Medium
        T
        You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

        A cell containing a thief if grid[r][c] = 1
        An empty cell if grid[r][c] = 0
        You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid,
        including cells containing thieves.

        The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path
        to any thief in the grid.

        Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

        An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

        The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value
        of val.



        Example 1:
                1 0 0
                0 0 0
                0 0 1


        Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
        Output: 0
        Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
        Example 2:
                x 0 1
                x 0 0
                x x x

        Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
        Output: 2
        Explanation: The path depicted in the picture above has a safeness factor of 2 since:
        - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
        It can be shown that there are no other paths with a higher safeness factor.
        Example 3:
                x x 0 1
                0 x x 0
                0 0 x 0
                1 0 x x


        Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
        Output: 2
        Explanation: The path depicted in the picture above has a safeness factor of 2 since:
        - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
        - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
        It can be shown that there are no other paths with a higher safeness factor.


        Constraints:

        1 <= grid.length == n <= 400
        grid[i].length == n
        grid[i][j] is either 0 or 1.
        There is at least one thief in the grid.
 */

public class Solution {
    public static void main(String[] args) {

    }

    int n;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    /**************************************************Using Priority Queue***************************************************/
    int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1 || n == 1)
            return 0;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<state> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.add(new state(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()) {
            state s = q.poll();
            int r = s.x();
            int c = s.y();
            int val = s.val();
            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= n || nc < 0) {
                    continue;
                }
                int nval = val + 1;
                if (nval < dist[nr][nc]) {
                    dist[nr][nc] = nval;
                    q.offer(new state(nr, nc, nval));
                }

            }
        }

        int ans = get_ans(dist);

        return ans;
    }

    private int get_ans(int[][] dist) {

        boolean[][] vis = new boolean[n][n];

        PriorityQueue<state> pq = new PriorityQueue<>((a, b) -> (b.val() - a.val()));

        pq.add(new state(0, 0, dist[0][0]));
        vis[0][0] = true;
        while (!pq.isEmpty()) {
            state s = pq.poll();
            int r = s.x();
            int c = s.y();
            int val = s.val();

            if (r == n - 1 && c == n - 1) {
                return val;
            }

            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= n || nc < 0) {
                    continue;
                }
                if (vis[nr][nc]) {
                    continue;
                }
                int nval = Math.min(val, dist[nr][nc]);
                vis[nr][nc] = true;
                pq.offer(new state(nr, nc, nval));


            }
        }
        return -1;
    }

    /***********************************************Using_Binary_Search_and_BFS***********************************************/


    public int BinarySearch(List<List<Integer>> grid) {
        n = grid.size();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<state> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.add(new state(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()) {
            state s = q.poll();
            int r = s.x();
            int c = s.y();
            int val = s.val();
            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= n || nc < 0) {
                    continue;
                }
                int nval = val + 1;
                if (nval < dist[nr][nc]) {
                    dist[nr][nc] = nval;
                    q.offer(new state(nr, nc, nval));
                }

            }
        }

        int ans = 0;
        int l = 0;
        int h = n;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (isvalid(dist, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return ans;
    }

    private boolean isvalid(int[][] dist, int range) {
        boolean[][] vis = new boolean[n][n];

        if (dist[0][0] < range) return false;
        Queue<state> q = new ArrayDeque<>();
        q.add(new state(0, 0, dist[0][0]));
        vis[0][0] = true;
        while (!q.isEmpty()) {
            state s = q.poll();
            int r = s.x();
            int c = s.y();
            if (r == n - 1 && c == n - 1) {
                return true;
            }

            int val = s.val();

            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= n || nc < 0) {
                    continue;
                }
                int nval = dist[nr][nc];
                if (nval >= range && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.add(new state(nr, nc, nval));

                }
            }
        }
        return false;
    }

    private record state(int x, int y, int val) {
    }
}
