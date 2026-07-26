package Grid.GD_10_Shift_2D_grid_1260;

import java.util.ArrayList;
import java.util.List;
/*
        1260. Shift 2D Grid

        Easy

        Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

        In one shift operation:

        Element at grid[i][j] moves to grid[i][j + 1].
        Element at grid[i][n - 1] moves to grid[i + 1][0].
        Element at grid[m - 1][n - 1] moves to grid[0][0].
        Return the 2D grid after applying shift operation k times.



        Example 1:
                1 2 3       9 1 2
                4 5 6  ---> 3 4 5
                7 8 9       6 7 8


        Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
        Output: [[9,1,2],[3,4,5],[6,7,8]]


        Example 2:

              3  8  1  9      13  3  8  1      21 13  3  8      0 21 13  3      12  0 21 13
              19 7  2  5 ---> 9 19  7  2 --->  1 9 19  7 --->   8  1  9 19 --->  3  8  1  9
              4  6 11 10       5  4  6 11      2  5  4  6       7  2  5  4      19  7  2  5
              12 0 21 13      10 12  0 21      11 10 12  0       6 11 10 12       4  6 11 10

        Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
        Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
        Example 3:

        Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
        Output: [[1,2,3],[4,5,6],[7,8,9]]


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m <= 50
        1 <= n <= 50
        -1000 <= grid[i][j] <= 1000
        0 <= k <= 100
 */

public class Solution {
    public static void main(String[] args) {

    }
    List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int len = n * m;
        k %= len;

        int idx = 0;
        int[] arr= new int[len];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                arr[idx++] = grid[i][j];
            }
        }
        int[] shifted = new int[len];

        for(int i = 0; i < len; i++){
            shifted[(i + k) % len] = arr[i];
        }

        List<List<Integer>> ans = new ArrayList<>();
        idx = 0;
        for(int i = 0; i < n; i++){
            ArrayList<Integer> list = new ArrayList<>();

            for(int j = 0; j < m; j++){
                list.add(shifted[idx++]);
            }
            ans.add(list);
        }

        return ans;
    }
}
