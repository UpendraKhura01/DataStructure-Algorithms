package Grid.GD_02_Min_operations_to_make_uni_value_grid_2033;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

    }

    static int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        int[] flat = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flat[idx++] = grid[i][j];
            }
        }

        Arrays.sort(flat);
        int remain = flat[0] % x;

        for (int i : flat) {
            if (i % x != remain)
                return -1;
        }

        int nm = n * m;
        int median = flat[(nm / 2)];

        int ans = 0;
        for (int i = 0; i < nm; i++) {
            int temp = Math.abs(median - flat[i]) / x;
            ans += temp;
        }


        return ans;
    }
}
