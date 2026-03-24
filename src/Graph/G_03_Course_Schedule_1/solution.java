package Graph.G_03_Course_Schedule_1;

import java.util.ArrayList;

public class solution {
    public static void main(String[] args) {
        int[][] arr = {{2, 0}, {2, 1}, {3, 2}};
        int n = 4;
        System.out.println(canFinish(n, arr));

    }
    static boolean canFinish(int n, int[][] prerequisites) {
        // code here
        int[] vis = new int[n];
        int[] cur = new int[n];
        ArrayList<ArrayList<Integer>> AdjL = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            AdjL.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            AdjL.get(v).add(u);
        }
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                if (helper(i, vis, cur, AdjL)) {
                    return false; // cycle found
                }
            }
        }
        return true;
    }
    //1 visited
    //0 not visited
    static boolean helper(int node, int[] vis, int[] cur, ArrayList<ArrayList<Integer>> Adjl) {
        vis[node] = 1;
        cur[node] = 1;

        for (int it : Adjl.get(node)) {
            if (vis[it] == 0) {
                    if (helper(it, vis, cur, Adjl) == true) return true;

            }
            //node has been visited and also in same path then it is a cycle
            else if (cur[it] == 1) return true;
        }
            cur[node] = 0;
            return false;

    }
}
