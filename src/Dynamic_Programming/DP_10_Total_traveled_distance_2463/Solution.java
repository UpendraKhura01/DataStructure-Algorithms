package Dynamic_Programming.DP_10_Total_traveled_distance_2463;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
2463. Minimum Total Distance Traveled
Hard

There are some robots and factories on the X-axis. You are given an integer array robot
 where robot[i] is the position of the ith robot.
 You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that,
  positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.

The positions of each robot are unique. The positions of each factory are also unique.
Note that a robot can be in the same position as a factory initially.

All the robots are initially broken; they keep moving in one direction. The direction could be the negative or
the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit,
 the factory repairs the robot,and it stops moving.

At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance
 traveled by all the robots.

Return the minimum total distance traveled by all the robots. The test cases are generated such that,
 all the robots can be repaired.

Note that

All robots move at the same speed.
If two robots move in the same direction, they will never collide.
If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
If the robot moved from a position x to a position y, the distance it moved is |y - x|.


Example 1:

Input: robot = [0,4,6], factory = [[2,2],[6,2]]
Output: 4
Explanation: As shown in the figure:
- The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
- The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
- The third robot at position 6 will be repaired at the second factory. It does not need to move.
The limit of the first factory is 2, and it fixed 2 robots.
The limit of the second factory is 2, and it fixed 1 robot.
The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.


Example 2:

Input: robot = [1,-1], factory = [[-2,1],[2,1]]
Output: 2
Explanation: As shown in the figure:
- The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
- The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
The limit of the first factory is 1, and it fixed 1 robot.
The limit of the second factory is 1, and it fixed 1 robot.
The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
 */


public class Solution {
    public static void main(String[] args) {
        int[][] factory = {
                {6, 2},
                {2, 2}
        };
        List<Integer> robot = new ArrayList<>();
        robot.add(0);
        robot.add(4);
        robot.add(6);
        System.out.println(minimumTotalDistance(robot, factory));
    }
    static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int m = factory.length;
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<Integer> fullfactory = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int pos = factory[i][0];
            int limit = factory[i][1];

            //expanding the factories
            while (limit > 0) {
                fullfactory.add(pos);
                limit--;
            }
        }

        int s = fullfactory.size();
        long[][] dp = new long[n + 1][s + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= s; j++) {
                dp[i][j] = -1;
            }
        }
        return helper(0, 0, robot, fullfactory, n, s, dp);

    }

    //Memoization with expanded factories
    static long helper(int i, int j, List<Integer> robot,
                       ArrayList<Integer> factories, int n, int s, long[][] dp) {

        if (i >= n)
            return 0;
        if (j >= s)
            return Long.MAX_VALUE / 2;

        if (dp[i][j] != -1)
            return dp[i][j];

        long take = Math.abs(factories.get(j) - robot.get(i))
                + helper(i + 1, j + 1, robot, factories, n, s, dp);
        long skip = helper(i, j + 1, robot, factories, n, s, dp);

        return dp[i][j] = Math.min(take, skip);
    }


    //Space Optimized Memoization
    static long threeParameters(int i, int j, List<Integer> robot, int n, int[][] factory,int m, long[][] dp){
        if(i == n)
            return 0;
        if(j == m)
            return Long.MAX_VALUE / 2;

        if (dp[i][j] != -1) return dp[i][j];
        long skip = threeParameters(i, j + 1, robot, n, factory, m, dp);

        long take = Long.MAX_VALUE / 2;
        long limit = factory[j][1];
        long cost = 0;
        for (int k = 0; k < limit && i + k < n; k++) {
            long fpos = factory[j][0];
            long rpos = robot.get(i + k);
            cost += Math.abs(rpos - fpos);
            long next = (threeParameters(i + k + 1, j + 1, robot, n, factory, m, dp));
            take = Math.min(take, cost + next);
        }

        return dp[i][j] = Math.min(take, skip);
    }

    //Tabulation
    static long Tabulation(List<Integer> robot, int[][] factory){
        int n = robot.size();
        int m = factory.length;
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        long[][] dp = new long[n + 1][m + 1];

        //base cases
        for (int i = 0; i < m; i++){
            dp[n][i] = 0;
        }
        for (int i = 0; i < n; i++){
            dp[i][m] = Long.MAX_VALUE / 2;
        }

        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = m - 1; j >= 0; j--) {
                long skip = dp[i][j + 1];

                long take = Long.MAX_VALUE / 2;
                long limit = factory[j][1];
                long cost = 0;
                for (int k = 0; k < limit && i + k < n; k++) {
                    long fpos = factory[j][0];
                    long rpos = robot.get(i + k);
                    cost += Math.abs(rpos - fpos);
                    long next = (dp[i + k + 1][j + 1]);
                    take = Math.min(take, cost + next);

                }
                dp[i][j] = Math.min(take, skip);
            }
        }

        return dp[0][0];
    }

    //Space Optimized Tabulation
    static long Tabulation2(List<Integer> robot, int[][] factory){

            int n = robot.size();
            int m = factory.length;
            Collections.sort(robot);
            Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

            long[] curr = new long[n + 1];
            long[] next = new long[n + 1];

            // base case
            Arrays.fill(next, (long) 1e18);
            next[n] = 0;

            // recursive case simulation
            for (int j = m - 1; j >= 0; j--) {
                for (int i = n - 1; i >= 0; i--) {
                    long skip = next[i];
                    long take = Long.MAX_VALUE / 2;
                    long limit = factory[j][1];
                    long cost = 0;
                    for (int k = 0; k < limit && i + k < n; k++) {
                        long fpos = factory[j][0];
                        long rpos = robot.get(i + k);
                        cost += Math.abs(rpos - fpos);
                        long nxt = next[i + k + 1];
                        take = Math.min(take, cost + nxt);
                    }

                    curr[i] = Math.min(take, skip);
                }

                long[] temp = curr;
                curr = next;
                next = temp;
            }

            return next[0];
        }
    }















