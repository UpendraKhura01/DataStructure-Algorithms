package Graph.G_16_Longest_Path_in_a_Directed_Acyclic_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
        Longest Path in a Directed Acyclic Graph

        Difficulty: Hard
        Given a weighted Directed Acyclic Graph (DAG) with V vertices numbered from 0 to V - 1,
        represented by edges[][], where edges[i] = [u, v, w] denotes a directed edge from u to v with weight w, and a source vertex src.

        Return the distance array, where the value at index i represents the longest distance from s to vertex i.
        If a vertex is unreachable from s, store INT_MIN for that vertex. The driver code will automatically display INT_MIN as INF.
        Examples :

        Input: V = 4, src = 0, edges[][] = [[0, 1, 1], [0, 2, 1], [1, 2, 5], [3, 1, 2], [3, 2, -1]]
        Output: [0, 1, 6, INF]
        Explanation: The longest distance of vertex 1 from 0 is 1, vertex 2 is 6 and vertex 3 is unreachable so INF.

        Input: V = 5, src = 1, edges[][] = [[0, 1, 1], [0, 2, 2], [1, 4, 4], [3, 2, -1], [4, 2, 3], [4, 3, 6]]
        Output: [INF, 0, 9, 10, 4]
        Explanation: The vertex 0 is not reachable from vertex 1 so its distance is INF, for 2 it is 9, for 3 it is 10, and for 4 it is 4.

        Constraints:
        1 ≤ V ≤ 10^4
        0 ≤ src ≤ V-1
        1 ≤ edges.size() ≤ V * (V - 1) / 2
        0 ≤ edges[i][0], edges[i][1] < V
        -100 ≤ edges[i][2] ≤ 100
 */

public class Solution {
    public static void main(String[] args) {

    }
    int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {
        // code here

        List<List<int[]>> adjl = adjl = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjl.add(new ArrayList<>());

        }

        for (ArrayList<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);

            int[] cur = new int[] {v, w};
            adjl.get(u).add(cur);
        }
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MIN_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        pq.add(new int[] {src, 0});
        dist[src] = 0;

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int e = cur[0];
            int w = cur[1];

            for (int[] i : adjl.get(e)) {
                int ne = i[0];
                int nw = i[1];
                if (w + nw > dist[ne]) {
                    dist[ne] = w + nw;
                    pq.add(new int[] {ne, w + nw});
                }
            }
        }

        return dist;

    }
}
