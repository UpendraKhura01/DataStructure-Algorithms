package Grid.GD_15_Min_Steps_By_Knight;

import java.util.ArrayDeque;
import java.util.Queue;

/*
        Min Steps by Knight

        Difficulty: Medium
        Given a square chessboard of size n × n, the initial position knightPos and target position targetPos of a Knight are given. Find the minimum number of moves required for the Knight to reach targetPos.

        A Knight moves in an L-shape, covering 2 cells in one direction and 1 cell perpendicular to it. From (x, y), it can move to: (x ± 2, y ± 1) and (x ± 1, y ± 2)

        This gives at most 8 possible moves:


        Note: The positions are given using 1-based indexing.

        Examples:

        Input: n = 3, knightPos[] = [3, 3], targetPos[]= [1, 2]
        Output: 1
        Explanation: Knight takes 1 step to reach from (3, 3) to (1 ,2).
        Input: n = 6, knightPos[] = [1, 3], targetPos[] = [5, 1]
        Output: 2
        Explanation: In above diagram Knight takes 2 step to reach from (1, 3) to (5, 0): (1, 3) -> (3, 2) -> (5, 1)

        Constraints:

        n ≤ 1000
        2 ≤ knightPos.size(), targetPos.size() ≤ 2
        1 ≤ knightPos[i], targetPos[i] ≤ n
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
    int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};

    public int minStepToReachTarget(int knightPos[], int targetPos[], int n) {
        // code here
        int tr = targetPos[0];
        int tc = targetPos[1];

        int r = knightPos[0];
        int c = knightPos[1];

        if (r == tr && c == tc) {
            return 0;
        }

        boolean[][] vis = new boolean[n + 1][n + 1];

        Queue<int[]> q = new ArrayDeque<>();

        vis[r][c] = true;
        q.offer(new int[] {r, c, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int w = cur[2];

            if (cr == tr && cc == tc) {
                return w;
            }

            for (int i = 0; i < 8; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 1 || nr > n || nc < 1 || nc > n) {
                    continue;
                }

                if(vis[nr][nc]){
                    continue;
                }
                vis[nr][nc] = true;
                q.offer(new int[]{nr, nc, w + 1});
            }

        }
        return -1;
    }
}
