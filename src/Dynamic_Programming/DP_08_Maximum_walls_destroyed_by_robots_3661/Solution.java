package Dynamic_Programming.DP_08_Maximum_walls_destroyed_by_robots_3661;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        int[] robots = {4}, distance = {3}, walls = {1, 1};
        System.out.println("max wall is :" + maxWalls(robots, distance, walls));

    }

    static int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        ArrayList<Pair> robotdist = new ArrayList<>();
        ArrayList<Pair> range = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            robotdist.add(new Pair(robots[i], distance[i]));
        }
        robotdist.sort(Comparator.comparingInt(Pair::first));

        Arrays.sort(walls);
        walls = Arrays.stream(walls).distinct().toArray();
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        for (int i = 0; i < n; i++) {
            int pos = robotdist.get(i).first();
            int dist = robotdist.get(i).second();

            int leftLimit = (i == 0) ? 1 : robotdist.get(i - 1).first() + 1; // finding the immediate left robot

            int rightLimit = (i == n - 1) ? (int) 1e9 : robotdist.get(i + 1).first() - 1; //finding the immediate right robot

            int Left = Integer.max(leftLimit, pos - dist); // finding the actual left range

            int Right = Integer.min(rightLimit, pos + dist); // finding the actual right range

            range.add(new Pair(Left, Right));
        }

        return helper(robotdist, range, walls, 1, 0, dp);

    }

    static int helper(ArrayList<Pair> robodist, ArrayList<Pair> range, int[] walls, int dir, int idx, int[][] dp) {
        // dir = true means fire shot towards right

        if (idx == robodist.size()) return 0;
        if (dp[idx][dir] != -1) return dp[idx][dir];
        int left_start = range.get(idx).first();

        if (dir == 1 && idx > 0) {
            left_start = Math.max(left_start, range.get(idx - 1).second() + 1);
        }
        int left_walls = countWalls(walls, left_start, robodist.get(idx).first()) +
                helper(robodist, range, walls, 0, idx + 1, dp);

        int right_walls = countWalls(walls, robodist.get(idx).first(), range.get(idx).second()) +
                helper(robodist, range, walls, 1, idx + 1, dp);

        return dp[idx][dir] = Math.max(left_walls, right_walls);
    }

    static int countWalls(int[] walls, int left, int right) {
        int l = lowerBound(walls, left);
        int r = upperBound(walls, right);
        return r - l;
    }

    static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    static int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public record Pair(int first, int second) {
    }

}
