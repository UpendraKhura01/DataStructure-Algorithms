package Graph.G_09_Minimum_multiplication_to_reach_end;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
        Minimum Multiplications to reach End
        Difficulty: Medium
        Given two integers, start and end, along with an array of integers arr[]. In one operation, you can multiply the current value by any element from arr[], and then take the result modulo 1000 to obtain a new value.

        Find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

        Examples :

        Input: arr[] = [2, 5, 7], start = 3, end = 30
        Output: 2
        Explanation:
        Step 1: 3*2 = 6 % 1000 = 6
        Step 2: 6*5 = 30 % 1000 = 30

        Input: arr[] = [3, 4, 65], start = 7, end = 175
        Output: 4
        Explanation:
        Step 1: 7 * 3 = 21 % 1000 = 21
        Step 2: 21 * 3 = 63 % 1000 = 63
        Step 3: 63 * 65 = 4095 % 1000 = 95
        Step 4: 95 * 65 = 6175 % 1000 = 175

        Input: arr[] = [2, 4], start = 3, end = 5
        Output: -1
        Explanation: Starting from 3 and multiplying by 2 or 4 always produces even numbers after the first step. Since 5 is odd, it can never be reached.
        Constraints:
        1  ≤ arr.size()  ≤ 103
        1  ≤ arr[i]  ≤ 103
        0  ≤ start, end  < 103
 */

public class Solution {
    public static void main(String[] args) {

    }
/*******************************************************One_Way*******************************************************/
    int minSteps(int[] arr, int start, int end) {
        if (start == end)
            return 0;

        int n = arr.length;
        int[] vis = new int[1000];
        Arrays.fill(vis, -1);
        int mod = 1000;
        Queue<pair> q = new LinkedList<>();

        q.add(new pair(start, 0));

        int idx = 0;
        vis[start] = 1;
        while (!q.isEmpty()) {
            pair p = q.poll();

            for (int i = 0; i < n; i++){

                int next = (p.num * arr[i]) % mod;
                if(vis[next] != -1) continue;

                if(next == end)
                    return p.dist + 1;
                vis[next] = 1;
                q.add(new pair(next, p.dist + 1));
            }

        }

        return - 1;
    }

    class pair {
        int num;
        int dist;
        pair(int n, int d) {
            num = n;
            dist = d;
        }
    }
/******************************************************Second_way*******************************************************/
int minSteps2(int[] arr, int start, int end) {
    if (start == end) return 0;

    int mod = 1000;
    int[] dist = new int[mod];
    Arrays.fill(dist, -1);

    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    dist[start] = 0;

    while (!q.isEmpty()) {
        int curr = q.poll();

        for (int x : arr) {
            int next = (curr * x) % mod;

            if (dist[next] == -1) {
                dist[next] = dist[curr] + 1;

                if (next == end) return dist[next];

                q.add(next);
            }
        }
    }

    return -1;
}
}
