package Graph.G_02_Longest_Cycle_Length_in_a_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class solution {
    public static void main(String[] args) {
        int V = 7;
        int[][] edges = {
                {0, 5},
                {1, 0},
                {2, 4},
                {3, 1},
                {4, 6},
                {5, 6},
                {6, 3}
        };
        System.out.println("ans is:" + longestCycle(V, edges));

    }

    static int longestCycle(int V, int[][] edges) {
        // code here
        ArrayList<Integer> AdjL = new ArrayList<>();
        for (int i = 0; i < V; i++) AdjL.add(-1);

        int[] vis = new int[V];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            AdjL.set(u, v);
        }
        int ans = -1;
        for (int i = 0; i < V; i++) {
            if ((vis[i]) != 1) {

                ans = Math.max(helper(i, AdjL, vis, 0, new HashMap<>()), ans);
            }
        }
        return ans;

    }

    static int helper(int node, ArrayList<Integer> Adjl, int[] vis, int dist, Map<Integer, Integer> mp) {
        if (node == -1) return -1;
        if (mp.containsKey(node)) {
            dist = dist - mp.get(node);
            return dist;
        }
        if (vis[node] == 1) return -1;

        vis[node] = 1;
        mp.put(node, dist);

        return helper(Adjl.get(node), Adjl, vis, dist + 1, mp);

    }
}
