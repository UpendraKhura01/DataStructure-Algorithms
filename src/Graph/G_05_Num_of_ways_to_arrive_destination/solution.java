package Graph.G_05_Num_of_ways_to_arrive_destination;

import java.util.*;

public class solution {
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                {0, 1, 2},
                {1, 2, 3},
                {0, 3, 5},
                {1, 3, 3},
                {2, 3, 4}
        };
        System.out.println("ans is "+ countPaths(V, edges));
    }

    static int countPaths(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<Pair>> AdjL = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            AdjL.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int node = edge[0];
            int neighbour = edge[1];
            int w = edge[2];
            AdjL.get(node).add(new Pair(neighbour, w));
            AdjL.get(neighbour).add(new Pair(node, w));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                Comparator.comparingInt((Pair p) -> p.dist)
                        .thenComparingInt(p -> p.node)
        );

        int[] Dist = new int[V];
        Arrays.fill(Dist, Integer.MAX_VALUE);
        Dist[0] = 0;

        int[] pathN = new int[V];
        pathN[0] = 1;

        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {
            int node = pq.peek().node;
            int dist = pq.peek().dist;
            pq.remove();
            if (dist > Dist[node]) continue;

            for (int i = 0; i < AdjL.get(node).size(); i++) {
                int neighbour_dist = AdjL.get(node).get(i).dist;
                int neighbour = AdjL.get(node).get(i).node;

                int new_dist = dist + neighbour_dist;

                if (new_dist == Dist[neighbour]){
                    pathN[neighbour] += pathN[node];
                }

                else if (new_dist < Dist[neighbour]){
                    Dist[neighbour] = new_dist;
                    pq.add(new Pair(neighbour, new_dist));
                    pathN[neighbour] = pathN[node];
                }

            }

        }

        return pathN[V-1];


    }

    //record Pair<K, V>(K key, V value) {};
    static class Pair {
        int node;
        int dist;

        public Pair(int v, int w) {
            node = v;
            dist = w;
        }
    }

}
