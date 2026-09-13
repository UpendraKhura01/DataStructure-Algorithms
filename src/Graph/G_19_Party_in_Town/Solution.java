package Graph.G_19_Party_in_Town;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/*
        Party in Town
        Solved
        Difficulty: Medium
        Geek Town has n houses numbered from 1 to n, choose a house to host a party such that its distance from its
        farthest house is as small as possible. Return this minimum possible distance.
        The houses are connected by n − 1 bidirectional roads, forming a tree.
        The connections are given as an adjacency list adj, where adj[i] contains all houses directly connected to house i + 1.
        Examples:

        Input: adj[][] = [[2], [1, 4, 3], [2], [2]]

        Output: 1
        Explanation: Party should take place at house number 2. Maximum distance from house number 2 is 1.
        Input: adj[][] = [[2], [1, 3], [4, 2], [3]]

        Output: 2
        Explanation: Party should take place at house number 2 or 3. The minimum distance is 2.
        Constraints:

        1 ≤ n ≤ 10^5
        1 ≤ adj[i][j]
        adj.size() = n
 */
public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<ArrayList<Integer>> adj;
    int n;
    public int partyHouse(ArrayList<ArrayList<Integer>> adj) {
        // code here
        this.adj = adj;
        n = adj.size();
        if(n <= 1){
            return 0;
        }

        int[] cur = bfs(1);
        cur = bfs(cur[0]);

        int d = cur[1];

        return (d + 1)/2;

    }
    private int[] bfs(int node) {

        int maxNode = node;
        int maxd = 0;

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        dist[node] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);

        while (!q.isEmpty()) {

            int cur = q.poll();

            if (dist[cur] > maxd) {
                maxNode = cur;
                maxd = dist[cur];
            }

            for (int nei : adj.get(cur - 1)) {
                if (dist[nei] == -1) {
                    dist[nei] = dist[cur] + 1;
                    q.offer(nei);
                }

            }
        }

        return new int[] {maxNode, maxd};

    }

}
