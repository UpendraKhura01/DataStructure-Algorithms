package Graph.G_15_Shortest_Path_In_1_2_Graphs;

import java.util.*;
/*
        Shortest Path in 1-2 Graph

        Difficulty: Hard=
        Given a weighted undirected graph with V vertices numbered from 0 to V - 1, represented by an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi. (wi can only be 1 or 2), and two vertices src and dest, find the shortest distance from src to dest.

        The shortest distance is defined as the minimum total weight required to reach dest starting from src.

        Return the shortest distance from src to dest. If dest is not reachable from src, return -1.

        Examples:

        Example 1
        Input: V = 4, edges[][] = [[0, 1, 1], [0, 2, 2], [2, 3, 1], [1, 2, 1], [1, 3, 2]], src = 0, dest = 3
                 (0)
                /   \
             1 /     \ 2
              /   1   \
            (1)-------(2)
              \       /
             2 \     / 1
                \   /
                 (3)

        Output: 3
        Explanation: One of the shortest paths from vertex 0 to vertex 3 is 0 -> 1 -> 3 with a total weight of 1 + 2 = 3.
        Another shortest path is 0 -> 2 -> 3 with a total weight of 2 + 1 = 3.
        Hence, the shortest distance from 0 to 3 is 3.
------------------------------------------------------------------------------------------------------------------------
        Example 2
        Input: V = 5, edges[][] = [[0, 1, 1], [0, 2, 2], [1, 2, 1], [3, 4, 2]], src = 1, dest = 3

               (0)             (3)
              /   \             |
           1 /     \ 2          | 2
            /   1   \           |
          (1)-------(2)        (4)
         Output: -1
        Explanation: There is no path from vertex 1 to vertex 3, so the answer is -1.
------------------------------------------------------------------------------------------------------------------------
        Example 3
        Input: V = 5, edges[][] = [[1, 0, 1], [0, 3, 2], [1, 3, 1], [1, 2, 2], [2, 3, 2], [3, 4, 1], [2, 4, 1]], src = 1, dest = 4
                 (1)
                / | \
             1 /  |  \ 2
              /   |   \
            (0)  1|    (2)
              \   |   / |
             2 \  |  /2 | 1
                \ | /   |
                 (3)---(4)
                     1
        Output: 2
        Explanation: The shortest path from vertex 1 to vertex 4 is 1 -> 3 -> 4 with a total weight of 1 + 1 = 2.
        Hence, the shortest distance from 1 to 4 is 2.
------------------------------------------------------------------------------------------------------------------------
        Constraints:
        2 ≤ V ≤ 10^5
        1 ≤ edges.size() ≤ min(2*105,  V*(V-1)/2)
        0 ≤ edges[i][0], edges[i][1] ≤ V-1
        edges[i][0] != edges[i][1]
        1 ≤ edges[i][1] ≤ 2
 */

class state {

    int v;
    int w;

    state(int v, int w) {

        this.v = v;
        this.w = w;
    }
}

public class Solution {

    public static void main(String[] args) {

    }

    /****************************************************Using Queue BFS****************************************************/
    int shortestPath(int V, int src, int dest, int[][] edges) {
        // code here

        if (src == dest) return 0;
        int n = edges.length;
        ArrayList<ArrayList<int[]>> adjl = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjl.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adjl.get(u).add(new int[]{v, w});
            adjl.get(v).add(new int[]{u, w});
        }


        int[] best = new int[V];

        Arrays.fill(best, Integer.MAX_VALUE);

        best[src] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int[] e : adjl.get(node)) {
                int newnode = e[0];
                int w = e[1];
                if (best[node] + w < best[newnode]) {
                    best[newnode] = best[node] + w;
                    q.add(newnode);
                }
            }
        }


        return (best[dest] == Integer.MAX_VALUE) ? -1 : best[dest];
    }

    /***************************************Using Priority Queue Dijkstra Algorithm***************************************/
    int Dijkstra(int V, int src, int dest, int[][] edges) {
        // code here

        if (src == dest) return 0;
        int n = edges.length;
        ArrayList<ArrayList<int[]>> adjl = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjl.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adjl.get(u).add(new int[]{v, w});
            adjl.get(v).add(new int[]{u, w});
        }

        PriorityQueue<state> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.add(new state(src, 0));

        int[] best = new int[V];
        Arrays.fill(best, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {

            state cur = pq.poll();
            int node = cur.v;
            int weight = cur.w;
            if (node == dest) {
                return weight;
            }

            for (int[] edge : adjl.get(node)) {
                int newnode = edge[0];
                int newweight = edge[1] + weight;

                if (newweight >= best[newnode]) {
                    continue;
                }
                best[newnode] = newweight;
                pq.add(new state(newnode, newweight));

            }
        }


        return -1;
    }
}
