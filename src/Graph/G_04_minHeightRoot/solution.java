package Graph.G_04_minHeightRoot;

import java.util.*;

public class solution {
    public static void main(String[] args) {
        int[][] edges = {
                {0, 2},
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int V = 5;
        ArrayList<Integer> ans = minHeightRoot(V, edges);
        System.out.println("ans is : " + ans.get(0) + " " + ans.get(1));
    }

//    static ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
//        ArrayList<Integer> result = new ArrayList<>();
//
//        if (V == 1) {
//            result.add(0);
//            return result;
//        }
//
//        // Step 1: Build adjacency list
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//        for (int i = 0; i < V; i++) {
//            adj.add(new ArrayList<>());
//        }
//
//        int[] degree = new int[V];
//
//        for (int[] e : edges) {
//            int u = e[0];
//            int v = e[1];
//
//            adj.get(u).add(v);
//            adj.get(v).add(u);
//
//            degree[u]++;
//            degree[v]++;
//        }
//
//        // Step 2: Add all leaf nodes (degree = 1)
//        Queue<Integer> q = new LinkedList<>();
//        for (int i = 0; i < V; i++) {
//            if (degree[i] == 1) {
//                q.offer(i);
//            }
//        }
//
//        // Step 3: Trim leaves level by level
//        int remainingNodes = V;
//
//        while (remainingNodes > 2) {
//            int size = q.size();
//            remainingNodes -= size;
//
//            for (int i = 0; i < size; i++) {
//                int node = q.poll();
//
//                for (int neighbor : adj.get(node)) {
//                    degree[neighbor]--;
//
//                    if (degree[neighbor] == 1) {
//                        q.offer(neighbor);
//                    }
//                }
//            }
//        }
//
//        // Step 4: Remaining nodes are answers
//        while (!q.isEmpty()) {
//            result.add(q.poll());
//        }
//
//        return result;
//    }
    static ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        List<HashSet<Integer>> AdjL = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            AdjL.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            AdjL.get(u).add(v);
            AdjL.get(v).add(u);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (AdjL.get(i).size() == 1) q.add(i);
        }
        int total = V;
        while (total > 2) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                int next = AdjL.get(cur).iterator().next();
                AdjL.get(next).remove(cur);

                if (AdjL.get(next).size() == 1) q.add(next);
            }
            total -= size;
        }
        return new ArrayList<>(q);
    }

}
