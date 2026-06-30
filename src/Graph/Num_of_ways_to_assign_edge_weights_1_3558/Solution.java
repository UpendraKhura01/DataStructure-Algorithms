package Graph.Num_of_ways_to_assign_edge_weights_1_3558;

import java.util.ArrayList;
/*
        3558. Number of Ways to Assign Edge Weights I

        Medium

        There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1.
        The tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that
        there is an edge between nodes ui and vi.

        Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.

        The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.

        Select any one node x at the maximum depth. Return the number of ways to assign edge weights in the path from
        node 1 to x such that its total cost is odd.

        Since the answer may be large, return it modulo 109 + 7.

        Note: Ignore all edges not in the path from node 1 to x.



        Example 1:



        Input: edges = [[1,2]]

        Output: 1

        Explanation:

        The path from Node 1 to Node 2 consists of one edge (1 → 2).
        Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
        Example 2:



        Input: edges = [[1,2],[1,3],[3,4],[3,5]]

        Output: 2

        Explanation:

        The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.
        For example, the path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4).
        Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.


        Constraints:

        2 <= n <= 10^5
        edges.length == n - 1
        edges[i] == [ui, vi]
        1 <= ui, vi <= n
        edges represents a valid tree.
 */

public class Solution {
    public static void main(String[] args) {

    }
    int M = 1000000000 + 7;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) {
            adj.add(new ArrayList());
        }
        for (int[] a : edges) {
            int u = a[0];
            int v = a[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int max_depth = maxdepth(adj, 0, 1, -1);
        int ans = exponenient(2 , max_depth - 1);
        return ans;
    }

    private int maxdepth(ArrayList<ArrayList<Integer>> adj, int depth, int node, int parent) {
        int max = depth;

        for (int i : adj.get(node)) {
            if (i == parent)
                continue;
            max = Math.max(max, maxdepth(adj, depth + 1, i, node));

        }
        return max;
    }
    private int exponenient(long base, int power){
        long ans = 1;
        while(power > 0){
            if((power & 1) == 1){
                ans = (base * ans) % M;
            }
            base = (base * base) % M;
            power >>= 1;
        }
        return (int)ans;
    }
}
