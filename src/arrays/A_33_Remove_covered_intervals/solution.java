package arrays.A_33_Remove_covered_intervals;

import java.util.Arrays;
/*
        1288. Remove Covered Intervals

        Medium

        Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
        remove all intervals that are covered by another interval in the list.

        The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
        Return the number of remaining intervals.

        Example 1:

        Input: intervals = [[1,4],[3,6],[2,8]]
        Output: 2
        Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
        Example 2:

        Input: intervals = [[1,4],[2,3]]
        Output: 1


        Constraints:

        1 <= intervals.length <= 1000
        intervals[i].length == 2
        0 <= li < ri <= 10^5
        All the given intervals are unique.
 */
public class solution {
    public static void main(String[] args) {

    }
    int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(
                intervals,
                (a, b) -> {
                    if (a[0] == b[0]) {
                        return b[1] - a[1];
                    }
                    return a[0] - b[0];
                }
        );
        int count = 0;
        int maxe = 0;
        for (int i = 0; i < n; i++) {
            if (intervals[i][1] > maxe) {
                maxe = intervals[i][1];
                count++;
            }
        }
        return count;
    }
}
