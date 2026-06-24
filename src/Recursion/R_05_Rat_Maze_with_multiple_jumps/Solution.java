package Recursion.R_05_Rat_Maze_with_multiple_jumps;

import java.util.ArrayList;
import java.util.Arrays;
/*
        Rat Maze With Multiple Jumps
        Difficulty: Medium
        Given a matrix mat[][] of size n × n, where mat[i][j] represents the maximum number of steps a rat can jump,
        either forward (right) or downward from that cell, find a path for the rat to reach from the top-left cell (0, 0)
        to the bottom-right cell (n - 1, n - 1). A cell containing 0 is blocked and cannot be used in the path.
        It is guaranteed that the cell mat[n-1][n-1] is not 0.

        Return an n × n matrix where 1 represents the cells included in the path and 0 represents the remaining cells.
        If no valid path exists, return [[-1]].

        Note: If multiple valid paths exist, choose the path with the shortest possible jumps first. For the same jump length,
        moving forward (right) should be preferred over moving downward.

        Example:

        Input: mat[][] = [[2, 1, 0, 0], [3, 0, 0, 1], [0, 1, 0, 1], [0, 0, 0, 1]]
        Output: [[1, 0, 0, 0], [1, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]]
        Explanation:

        Input Matrix             Output Matrix (Path Marked)
        +---+---+---+---+        +---+---+---+---+
        | 2 | 1 | 0 | 0 |        | 1 | 0 | 0 | 0 |
        +---+---+---+---+   =>   +---+---+---+---+
        | 3 | 0 | 0 | 1 |        | 1 | 0 | 0 | 1 |
        +---+---+---+---+        +---+---+---+---+
        | 0 | 1 | 0 | 1 |        | 0 | 0 | 0 | 1 |
        +---+---+---+---+        +---+---+---+---+
        | 0 | 0 | 0 | 1 |        | 0 | 0 | 0 | 1 |
        +---+---+---+---+        +---+---+---+---+

        Path taken: (0,0) -> (1,0) -> (0,3) -> (1,3) -> (2,3) -> (3,3)
        (The '>' symbol represents the movement direction in the path)


        The rat starts from cell (0, 0) which contains value 2, so it can jump at most 2 steps either right or downward.
        Steps:
        -> Moves downward to (1, 0) which contains value 3.
        -> Jumps 3 steps right to reach (1, 3).
        -> Moves downward through (2, 3) and reaches the destination cell (3, 3).
        Input: mat[][] = [[2, 1, 0, 0], [2, 0, 0, 1], [0, 1, 0, 1], [0, 0, 0, 1]]
        Output: [[-1]]
        Explanation: The rat starts at (0, 0) with value 2, but every possible path from there eventually reaches a cell containing 0. Since no sequence of jumps can reach the destination cell (3, 3), no valid path exists and the output is [[-1]].
        Constraints:
        1 ≤ n ≤ 50
        0 ≤ mat[i][j] ≤ 20
 */

public class Solution {
    public static void main(String[] args) {

    }


    public ArrayList<ArrayList<Integer>> shortestDist(int[][] mat) {
        // code here
        int n = mat.length;

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            res.add(row);
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        if (helper(0, 0, mat, n, dp, res)) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add(res.get(i).get(j));
                }
                ans.add(row);
            }
            return ans;
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>(Arrays.asList(-1)));
        return ans;
    }

    private boolean helper(int i, int j, int[][] mat, int n, int[][] dp,
                           ArrayList<ArrayList<Integer>> res) {

        if (i >= n || j >= n || mat[i][j] == 0)
            return false;

        if (i == n - 1 && j == n - 1) {
            res.get(i).set(j, 1);
            return true;
        }

        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        res.get(i).set(j, 1);
        int jumps = mat[i][j];

        for (int jump = 1; jump <= jumps; jump++) {
            if (helper(i, j + jump, mat, n, dp, res)) {
                dp[i][j] = 1;
                return true;
            }

            if (helper(i + jump, j, mat, n, dp, res)) {
                dp[i][j] = 1;
                return true;
            }
        }

        res.get(i).set(j, 0);
        dp[i][j] = 0;
        return false;
    }

}
