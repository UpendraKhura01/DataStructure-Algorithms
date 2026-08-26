package Graph.G_17_Negative_Weight_Cycle;

/*
        Negative Weight Cycle
        Difficulty: Medium
        Given a weighted directed graph containing V vertices numbered from 0 to V - 1 and
        a list of E directed edges edges[][], determine whether the graph contains a negative weight cycle or not.

        Each edge is represented as: [u, v, w], where there is a directed edge from vertex u to vertex v having the given weight w.

        Note: A negative-weight cycle is a cycle in a graph whose edges sum to a negative value.

        Examples:

        Input: V = 4, E = 4, edges[][] = [[0, 3, 6], [1, 0, 4], [1, 2, 6], [3, 1, 2]]

        Output: false
        Explanation: Cycle 1 -> 0 -> 3 -> 1 has total weight 6 + 4 + 2 = 12, which is positive, so no negative weight cycle exists.

        Input: V = 4, E = 4, edges[][] = [[1, 0, 4], [3, 1, -2], [1, 2, -6], [2, 3, 5]]

        Output: true
        Explanation: There is a cycle 1 -> 2 -> 3 -> 1 with total weight -3, which is negative, so a negative weight cycle exists.

         Constraints:
        1 ≤ V ≤ 10^3
        0 ≤ E ≤ 10^5
        0 ≤ u, v < V
        -106 ≤ w ≤ 10^6


 */
public class Solution {
    public static void main(String[] args) {

    }
    boolean isNegativeWeightCycle(int V, int[][] edges) {
        // code here

        int[] dist = new int[V];
        for(int i = 0; i <= V; i++){

            for(int[] e : edges){
                int u = e[0];
                int v = e[1];
                int w = e[2];
                if(dist[v] > dist[u] + w){
                    dist[v] = dist[u] + w;
                    if(i == v){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
