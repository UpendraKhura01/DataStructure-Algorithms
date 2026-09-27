package Graph.G_18_Longest_Colored_Path;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        String s = "RBB";
        int[][] edges = {
                {1, 2}, {1, 3}
        };
        Solution solution = new Solution();
        solution.longestPath("s", new int[][]{});

    }

        List<List<Integer>> adjl = new ArrayList<>();
        int n;
        String s;
        boolean[] vis;
        int ans = 0;

        public int longestPath(String s, int[][] edges) {
            // code here
            n = s.length();
            this.s = s;

            vis = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                adjl.add(new ArrayList<>());
            }

            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                adjl.get(u).add(v);
                adjl.get(v).add(u);
            }
            solve(1, -1);
            return ans;
        }
        private int[] solve(int node, int parent) {
            //return len , col

            int max1 = 0;
            int max2 = 0;
            char c = s.charAt(node - 1);
            int col = c - 'A';

            vis[node] = true;
            for (int newnode : adjl.get(node)) {
                if (newnode == node) {
                    continue;
                }
                if (vis[newnode]) {
                    continue;
                }
                int[] temp = solve(newnode, node);
                int len = temp[0];
                int curcol = temp[1];

                if (curcol == col) {
                    //same col check

                    if (len > max1) {
                        max2 = max1;
                        max1 = len;
                    }
                    else if (len > max2 && len < max1) {
                        max2 = len;
                    }

                    ans = Math.max(ans, max1 + max2 + 1);
                }
            }
            vis[node] = false;

            return new int[]{max1 + 1, col};
        }
    }



