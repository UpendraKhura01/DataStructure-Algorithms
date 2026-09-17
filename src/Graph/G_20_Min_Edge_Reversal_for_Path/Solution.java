package Graph.G_20_Min_Edge_Reversal_for_Path;

import java.util.*;

/*
        Min Edge Reversals for Path

        Difficulty: Medium
        Given a directed graph with n vertices numbered from 1 to n.
        The graph is represented using a 2D array edges[][] of size m, where each entry edges[i] = [u, v] denotes
        a directed edge from vertex u to vertex v. You are also given a source vertex src and a destination vertex dst.

        Find the minimum number of edges that need to be reversed so that there exists at least one path from src to dst.

        If it is not possible to create a path from src to dst, return -1.

        Examples:

        Input: n = 3, edges[][] = [[1, 2], [3, 2]], src = 1, dst = 3

        Output: 1
        Explanation: Reverse the edge 3 -> 2.
        Input: n = 4, edges[][] = [[1, 2], [2, 3], [3, 4]], src = 1, dst = 4

        Output: 0
        Explanation: One path already exists between 1 to 4 i.e. 1 -> 2 -> 3 -> 4.
        Constraints:
        1 ≤ n, m ≤ 10^5
        1 ≤ edges[i][0], edges[i][1] ≤ n
        1 ≤ src, dst ≤ n
 */
public class Solution {
    public static void main(String[] args) {

    }
    int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {
        // code here
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adj.get(u).add(new int[]{v, 0});
            adj.get(v).add(new int[]{u, 1});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> q = new ArrayDeque<>();
        q.add(src);
        dist[src] = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            if(node == dst){
                return dist[node];

            }

            for(int[] e : adj.get(node)){
                int newNode = e[0];
                int dir = e[1];

                int newDist = dir + dist[node];
                if(newDist < dist[newNode]){
                    dist[newNode] = newDist;

                    if(dir == 0){
                        q.offerFirst(newNode);
                    }
                    else{
                        q.offerLast(newNode);
                    }
                }
            }
        }

        return -1;
    }
}
