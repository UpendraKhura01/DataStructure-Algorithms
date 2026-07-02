package Graph.G_12_Find_a_safe_walk_through_a_grid_3286;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
/*
        3286. Find a Safe Walk Through a Grid

        Medium

        You are given an m x n binary matrix grid and an integer health.

        You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).

        You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.

        Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.

        Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
------------------------------------------------------------------------------------------------------------------------
        Example 1:

        Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
        Grid (3x5), Health = 1
        [0, 1, 0, 0, 0]
        [0, 1, 0, 1, 0]
        [0, 0, 0, 1, 0]
        Path: (0,0) -> (1,0) -> (2,0) -> (2,1) -> (2,2) -> (1,2) -> (0,2) -> (0,3) -> (0,4) -> (1,4) -> (2,4)
        Output: true

        Explanation:

        The final cell can be reached safely by walking along the gray cells below.
------------------------------------------------------------------------------------------------------------------------
        Example 2:
        Example 2:
         Grid (4x6), Health = 3
         [0, 1, 1, 0, 0, 0]
         [1, 0, 1, 0, 0, 0]
         [0, 1, 1, 1, 0, 1]
         [0, 0, 1, 0, 1, 0]

        Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3

        Output: false

        Explanation:

        A minimum of 4 health points is needed to reach the final cell safely.
------------------------------------------------------------------------------------------------------------------------
        Example 3:
          [1, 1, 1]
          [1, 0, 1]
          [1, 1, 1]
        Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5

        Output: true

        Explanation:

        The final cell can be reached safely by walking along the gray cells below.

        Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.
------------------------------------------------------------------------------------------------------------------------


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        2 <= m * n
        1 <= health <= m + n
        grid[i][j] is either 0 or 1.
 */

public class Solution {
    public static void main(String[] args) {

    }

    /*******************************************************Using Deque BFS*******************************************************/
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int m;
    int n;
    List<List<Integer>> grid;
    int[][] best;

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        m = grid.size();
        n = grid.get(0).size();
        this.grid = grid;
        best = new int[m][n];
        return solve1(health);
    }

    private boolean solve1(int health) {
        Deque<State> q = new ArrayDeque<>();

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0)
            return false;

        q.offerFirst(new State(0, 0, startHealth));
        best[0][0] = startHealth;
        while (!q.isEmpty()) {

            State cur = q.pollFirst();
            int r = cur.r();
            int c = cur.c();
            int cur_health = cur.health();
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                int new_health = cur_health - grid.get(nr).get(nc);

                if (new_health <= 0) {
                    continue;
                }
                if (best[nr][nc] >= new_health) {
                    continue;
                }
                best[nr][nc] = new_health;
                if (grid.get(nr).get(nc) == 0)
                    q.offerFirst(new State(nr, nc, new_health));
                else {
                    q.offerLast(new State(nr, nc, new_health));
                }
            }
        }

        return false;
    }

    /**************************************************using priority Queue**************************************************/

    public boolean PriorityQueue(List<List<Integer>> grid, int health) {
        m = grid.size();
        n = grid.get(0).size();
        this.grid = grid;
        best = new int[m][n];
        return solve2(health);
    }

    private boolean solve2(int health) {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> b.health() - a.health());

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0)
            return false;

        pq.add(new State(0, 0, startHealth));
        best[0][0] = startHealth;
        while (!pq.isEmpty()) {

            State cur = pq.poll();
            int r = cur.r();
            int c = cur.c();
            int cur_health = cur.health();
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                int new_health = cur_health - grid.get(nr).get(nc);

                if (new_health <= 0) {
                    continue;
                }
                if (best[nr][nc] >= new_health) {
                    continue;
                }
                best[nr][nc] = new_health;
                pq.offer(new State(nr, nc, new_health));
            }
        }

        return false;
    }

    /*******************************************************using DFS*******************************************************/
    boolean DFS(List<List<Integer>> grid, int health) {
        m = grid.size();
        n = grid.get(0).size();
        this.grid = grid;
        best = new int[m][n];
        return solve3(0, 0, health);

    }

    private boolean solve3(int r, int c, int cur_health) {
        cur_health -= grid.get(r).get(c);
        if (r == m - 1 && c == n - 1) {
            return (cur_health > 0) ? true : false;
        }

        if (cur_health <= 0) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }
            if (cur_health <= best[nr][nc]) {
                continue;
            }

            best[nr][nc] = cur_health;

            if (solve3(nr, nc, cur_health)) {
                return true;
            }
        }

        return false;
    }

    private record State(int r, int c, int health) {
    }


}
