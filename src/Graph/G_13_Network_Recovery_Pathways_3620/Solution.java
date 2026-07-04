package Graph.G_13_Network_Recovery_Pathways_3620;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
/*
        3620. Network Recovery Pathways

        Hard

        You are given a directed acyclic graph of n nodes numbered from 0 to n−1.
        This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi] indicates
        a one‑way communication from node ui to node vi with a recovery cost of costi.

        Some nodes may be offline. You are given a boolean array online where online[i] = true means nodei is online.
        Nodes 0 and n-1 are always online.

        A path from 0to n−1 is valid if:

        All intermediate nodes on the path are online.
        The total recovery cost of all edges on the path does not exceed k.
        For each valid path, define its score as the minimum edge‑cost along that path.

        Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists, return -1.



        Example 1:

        Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10

        Output: 3

        Explanation:



        The graph has two possible routes from node 0 to node 3:

        Path 0 → 1 → 3

        Total cost = 5 + 10 = 15, which exceeds k (15 > 10), so this path is invalid.

        Path 0 → 2 → 3

        Total cost = 3 + 4 = 7 <= k, so this path is valid.

        The minimum edge‐cost along this path is min(3, 4) = 3.

        There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.

        Example 2:

        Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12

        Output: 6

        Explanation:



        Node 3 is offline, so any path passing through 3 is invalid.

        Consider the remaining routes from 0 to 4:

        Path 0 → 1 → 4

        Total cost = 7 + 5 = 12 <= k, so this path is valid.

        The minimum edge‐cost along this path is min(7, 5) = 5.

        Path 0 → 2 → 3 → 4

        Node 3 is offline, so this path is invalid regardless of cost.

        Path 0 → 2 → 4

        Total cost = 6 + 6 = 12 <= k, so this path is valid.

        The minimum edge‐cost along this path is min(6, 6) = 6.

        Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.



        Constraints:

        n == online.length
        2 <= n <= 5 * 104
        0 <= m == edges.length <= min(105, n * (n - 1) / 2)
        edges[i] = [ui, vi, costi]
        0 <= ui, vi < n
        ui != vi
        0 <= costi <= 109
        0 <= k <= 5 * 1013
        online[i] is either true or false, and both online[0] and online[n − 1] are true.
        The given graph is a directed acyclic graph.
 */

public class Solution {
    public static void main(String[] args) {

    }
    int n;
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        n  = online.length;
        int m = edges.length;
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        int low = n;
        int high = 0;
        //adj creation
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int cost = e[2];
            if(!online[u] || !online[v]){
                continue;
            }
            adj.get(u).add(new int[]{v, cost});
            low = Math.min(low, cost);
            high = Math.max(high, cost);
        }

        int ans = -1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(check(mid,adj, k)){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean check(int mid, ArrayList<ArrayList<int[]>> adj, long k){
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        long[] best = new long[n];
        Arrays.fill(best, Long.MAX_VALUE);
        best[0] = 0L;
        pq.add(new long[]{0, 0});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int node = (int)cur[0];
            long cost = cur[1];
            if(cost > k){
                return false;
            }
            if(node == n - 1){
                return true;
            }
            if(cost > best[node]){
                continue;
            }
            for(int[] e : adj.get(node)){
                int newnode = e[0];
                int newcost = e[1];
                if(newcost < mid){
                    continue;
                }
                if(cost + newcost < best[newnode]){
                    best[newnode] = cost + newcost;
                    pq.add(new long[]{newnode, best[newnode]});
                }
            }


        }
        return false;
    }
}
