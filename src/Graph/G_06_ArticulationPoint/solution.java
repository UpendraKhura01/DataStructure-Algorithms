package Graph.G_06_ArticulationPoint;

import java.util.ArrayList;

public class solution {
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0, 1},
                         {1, 4},
                         {4, 3},
                         {4, 2},
                         {2, 3}
                        };
        ArrayList<Integer> ans = articulationPoints(V, edges);
        System.out.println("ans:");
        for (int i : ans){
            System.out.println(i);
        }

    }
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // code here

        ArrayList<ArrayList<Integer>> adj_l = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj_l.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj_l.get(u).add(v);
            adj_l.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        boolean[] isArt = new boolean[V]; //check if the Vertex is Articulation point or not

        int[] l_time = new int[V];//Lowest time count arr

        int[] f_time = new int[V];//Discovery time count arr

        int[] time = {0}; //counting the time

        for (int i = 0; i < V; i++){
            if (!vis[i]) helper(i, -1, vis, isArt, l_time, f_time, adj_l, time);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++){
            if (isArt[i]) ans.add(i);
        }
        if (ans.isEmpty()) ans.add(-1);
        return ans;
    }

    static void helper(int node, int parent, boolean[] vis, boolean[] isArt,
                      int[] l_time, int[] f_time,
                      ArrayList<ArrayList<Integer>> adjL, int[] time){

        time[0]++;
        l_time[node] = f_time[node] = time[0];

        vis[node] = true;

        int child = 0; //counting the child to check if the root node can be the Articulation point or not

        for(int i : adjL.get(node)){

            if (!vis[i]){

                child++;//new child found so increase the child count

                helper(i, node, vis, isArt, l_time, f_time, adjL, time);

                //updating the low time to that of the child after DFS
                l_time[node] = Math.min(l_time[node], l_time[i]);

                //if after DFS the child is still visited after the node, then the node is an Articulation point
                if (f_time[node] <= l_time[i] && parent != -1){
                    isArt[node] = true;
                }
            }

            else if (i != parent){
                l_time[node] = Math.min(l_time[node], f_time[i]);
                /*
                here node = current node
                "i" is the already visited node which is not the parent
                so it is a back edge
                we should update out low time with discovery time of the "i" node

                 */
            }
        }

        if (parent == -1 && child > 1) isArt[node] = true;

    }
}
