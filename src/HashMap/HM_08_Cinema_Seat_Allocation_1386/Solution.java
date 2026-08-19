package HashMap.HM_08_Cinema_Seat_Allocation_1386;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*
        1386. Cinema Seat Allocation
        Medium

        A cinema has n rows of seats, numbered from 1 to n. Each row has 10 seats, numbered from 1 to 10.

        You are given a 2D integer array reservedSeats, where reservedSeats[i] = [rowi, seati] means,
        that seat seati in row rowi is already reserved.

        A four-person group must be assigned to four seats in the same row. The group can be seated in one of the following seat blocks:

        seats 2, 3, 4, 5
        seats 4, 5, 6, 7
        seats 6, 7, 8, 9
        A block can be used only if none of its seats are reserved. Each seat can be assigned to at most one group.

        Return an integer denoting the maximum number of four-person groups that can be assigned.



        Example 1:

        Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
        Output: 4
        Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.
        Example 2:

        Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
        Output: 2
        Example 3:

        Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
        Output: 4


        Constraints:

        1 <= n <= 10^9
        1 <= reservedSeats.length <= min(10 * n, 10^4)
        reservedSeats[i] == [rowi, seati]
        1 <= rowi <= n
        1 <= seati <= 10
        All reservedSeats[i] are distinct.

 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Set<Integer>> mp = new HashMap<>();
        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];
            mp.computeIfAbsent(row, k -> new HashSet<>()).add(seat);
        }
        long ans = n * 2L;

        for (int row : mp.keySet()) {
            int cur = 0;
            Set<Integer> seats = mp.get(row);

            boolean left = !seats.contains(2) &&
                    !seats.contains(3) &&
                    !seats.contains(4) &&
                    !seats.contains(5);

            boolean middle = !seats.contains(4) &&
                    !seats.contains(5) &&
                    !seats.contains(6) &&
                    !seats.contains(7);

            boolean right = !seats.contains(6) &&
                    !seats.contains(7) &&
                    !seats.contains(8) &&
                    !seats.contains(9);

            if (left && right) {
                continue;
            } else if (left || middle || right) {
                ans -= 1;
            }
            else{
                ans -= 2;
            }
        }

        return (int) ans;
    }
}
