package Graph.G_01_Rotten_Oranges;


import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
      int[][] mat = {{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
//      int[][] mat = {{2, 1, 0, 2, 1}, {0, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
//        int[][] mat = {{0 , 1, 2}, {0, 1, 2}, { 2, 1, 1}};

        System.out.println(Rotten_oranges(mat));
    }
    // 0 1 2
    // 0 1 2
    // 2 1 1

    static int Rotten_oranges(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        return LevelOrder(mat, n, m);
    }

    static int LevelOrder(int[][] mat,int n,int m){
        Queue<pair> q = new LinkedList<>();

        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    q.add(new pair(i, j));
                    vis[i][j] = true;
                }
            }
        }
        int[] row = { 0, 1, 0, -1};
        int[] col = { 1, 0, -1, 0};
        int ans = 0;

        while (!q.isEmpty()) {

            int s = q.size();
            boolean rot = false;
            for (int a = 0; a < s; a++) {

                pair pair = q.poll();
                int r = pair.row;
                int c = pair.col;

                for (int i = 0; i < 4; i++) {
                    int new_r = r + row[i];
                    int new_c = c + col[i];
                    if (new_r >= 0 && new_r < n && new_c >=0 && new_c < m){
                        if (mat[new_r][new_c] == 1 && !vis[new_r][new_c]){
                            vis[new_r][new_c] = true;
                            rot = true;
                            q.add(new pair(new_r,new_c));
                        }
                    }
                }
            }
            if (rot) ans++;
        }
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 && !vis[i][j]) {
                    return -1;
                }
            }
        }

        return ans;
    }

    static class pair{
        int row , col;
        pair(int r, int c){
            row = r;
            col = c;
        }
    }

}
