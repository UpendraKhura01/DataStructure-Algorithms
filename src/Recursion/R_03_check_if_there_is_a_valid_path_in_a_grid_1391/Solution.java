package Recursion.R_03_check_if_there_is_a_valid_path_in_a_grid_1391;

/*
    1391. Check if There is a Valid Path in a Grid

    Each cell contains a street type:

        Type 1: left  <--> right
            ───

        Type 2: up    <--> down
             │
             │

        Type 3: left  <--> down
            ─┐
              │

        Type 4: right <--> down
            ┌─
            │

        Type 5: left  <--> up
              │
            ─┘

        Type 6: right <--> up
            │
            └─

    You start at the upper-left cell (0, 0).
    You need to reach the bottom-right cell (m - 1, n - 1).

    You may move from one cell to another only if:
    1. The current cell has a road opening toward the next cell.
    2. The next cell has a road opening back toward the current cell.

    Example 1:

    Input:
    grid = [
      [2, 4, 3],
      [6, 5, 2]
    ]

    Diagram:

        (0,0)   (0,1)   (0,2)
          │       ┌─     ─┐
          │       │       │

        (1,0)   (1,1)   (1,2)
          └─     ─┘      │
                         │

    Valid path:

        (0,0)
          ↓
        (1,0)
          →
        (1,1)
          ↑
        (0,1)
          →
        (0,2)
          ↓
        (1,2)

    Output:
    true


    Example 2:

    Input:
    grid = [
      [1, 2, 1],
      [1, 2, 1]
    ]

    Diagram:

        (0,0)   (0,1)   (0,2)
         ───      │      ───
                  │

        (1,0)   (1,1)   (1,2)
         ───      │      ───
                  │

    From (0,0), the street only goes left and right.
    But there is no valid connected street to move into.

    Output:
    false


    Example 3:

    Input:
    grid = [
      [1, 1, 2]
    ]

    Diagram:

        (0,0)   (0,1)   (0,2)
         ───     ───      │
                          │

    You can move from (0,0) to (0,1),
    but from (0,1), you cannot move to (0,2)
    because type 2 has no left opening.

    Output:
    false


    Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    1 <= grid[i][j] <= 6
*/

public class Solution {
    public static void main(String[] args) {
        int[][] grid = {
                {2, 4, 3},
                {6, 5, 2}
        };
        System.out.println(hasValidPath(grid));

    }

    static boolean hasValidPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        return helper(0, 0, grid, n, m, vis);
    }

    static boolean helper(int i, int j, int[][] grid, int n, int m, int[][] vis) {
        if (i == n - 1 && j == m - 1)
            return true;
        vis[i][j] = 1;
        int[][] newc = next(i, j, grid);
        for (int[] a : newc) {
            int ni = a[0];
            int nj = a[1];
            if (ni >= n || ni < 0 || nj >= m || nj < 0)
                continue;
            if (vis[ni][nj] == 1)
                continue;
            if (!cango(i, j, ni, nj, grid))
                continue;

            if (helper(ni, nj, grid, n, m, vis))
                return true;
        }

        return false;
    }

    static int[][] next(int i, int j, int[][] grid) {

        int num = grid[i][j];

        if (num == 1)
            return new int[][]{{i, j + 1}, {i, j - 1}}; //left right
        else if (num == 2)
            return new int[][]{{i + 1, j}, {i - 1, j}};// up down

        else if (num == 3)
            return new int[][]{{i, j - 1}, {i + 1, j}}; //left down
        else if (num == 4)
            return new int[][]{{i, j + 1}, {i + 1, j}};//right down

        else if (num == 5)
            return new int[][]{{i, j - 1}, {i - 1, j}};// left up
        else if (num == 6)
            return new int[][]{{i, j + 1}, {i - 1, j}}; // right up

        return new int[][]{{-1, -1}, {-1, -1}};
    }

    //checking if the destination allow entry
    static boolean cango(int i, int j, int ni, int nj, int[][] grid) {
        int[][] newc = next(ni, nj, grid);
        for (int[] a : newc) {
            int ai = a[0];
            int aj = a[1];

            if (ai == i && aj == j)
                return true;
        }
        return false;
    }
}
