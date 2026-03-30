package Graph.G_07_Minimum_cost_to_connect_all_CoOrdinates;

import java.util.*;

public class solution {
    public static void main(String[] args) {
        int[][] houses = {
                {0, 7},
                {0, 9},
                {20, 7},
                {30, 7},
                {40, 70}
        };
        System.out.println("ans is "+ minCost(houses));

    }

    static int minCost(int[][] houses) {
        // code here
        int n = houses.length;
        ArrayList<ArrayList<pair>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++) {
                int x1 = houses[i][0];
                int y1 = houses[i][1];
                int x2 = houses[j][0];
                int y2 = houses[j][1];
                int d = dist(x1, x2, y1, y2);
                list.get(i).add(new pair(d,j));
                list.get(j).add(new pair(d,i));
            }
        }
        PriorityQueue<pair> pq = new PriorityQueue<>(
                (a, b)-> a.first - b.first
        );
        boolean[] vis = new boolean[n];
        int ans = 0;
        pq.add(new pair(0, 0));
        while (!pq.isEmpty()){
            pair pair = pq.poll();
            int dist = pair.first;
            int node = pair.second;
            if (vis[node]) continue;
            vis[node] = true;
            ans += dist;
            for (pair neighbour : list.get(node)){
                int nei = neighbour.second;
                int d = neighbour.first;
                if (!vis[nei]){
                    pq.add(new pair(d, nei));
                }

            }

        }
        return ans;

    }

    static int dist(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class pair {
        int first;
        int second;

        pair(int dist, int node) {
            this.first = dist;
            this.second = node;
        }
    }
}
