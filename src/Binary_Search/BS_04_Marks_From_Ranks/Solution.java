package Binary_Search.BS_04_Marks_From_Ranks;

import java.util.ArrayList;
/*
        Marks from Ranks

        Difficulty: Medium
        Consider an input where all marks obtained are divided into intervals of consecutive numbers represented as l[] and r[]
        where l[i] and r[i] represent the starting and ending marks (inclusive) of the i-th interval.

        The intervals are sorted in increasing order and do not overlap.
        The rank of a mark is defined by its position among all valid marks in increasing order, with the smallest mark assigned rank 1,
        the next smallest rank 2, and so on.
        Given an array rank[]. for each value in rank[], find the corresponding mark and return as an array.

        Examples:

        Input: l[] = [1, 6, 14], r[] = [3, 9, 15], rank[] = [2, 5, 8]
        Output: [2, 7, 14]
        Explanation: The valid marks are 1, 2, 3, 6, 7, 8, 9, 14, 15. Their corresponding ranks are 1 to 9 as there are 9 distinct marks.
        Therefore, rank 2 corresponds to mark 2, rank 5 corresponds to mark 7, and rank 8 corresponds to mark 14.
        Input: l[] = [5, 10], r[] = [7, 12], rank[] = [1, 4, 6]
        Output: [5, 10, 12]
        Explanation: The valid marks are 5, 6, 7, 10, 11, 12. Their corresponding ranks are 1 to 6 in increasing order.
        Hence, rank 1 corresponds to mark 5, rank 4 corresponds to mark 10, and rank 6 corresponds to mark 12.


        Constraints:

        1 ≤ l.size(), l[i], r.size(), r[i], rank.size(), rank[i] ≤ 10^5

 */

public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<ranks> list;
    int n;
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        // code here
        n = l.length;

        list = new ArrayList<>();

        int cur_rank = 1;

        for (int i = 0; i < n; i++) {

            int left = l[i];
            int right = r[i];

            int diff = right - left;

            list.add(new ranks(left, cur_rank));
            int new_rank = cur_rank + diff;

            list.add(new ranks(right, new_rank));
            cur_rank = new_rank + 1;
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < rank.length; i++) {
            int num = find(rank[i]);
            ans.add(num);
        }
        return ans;
    }
    private int find(int rank) {
        int left = 0;
        int right = 2 * n - 1;
        int best = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            ranks cur = list.get(mid);

            if (cur.rank() <= rank) {
                left = mid + 1;
                best = mid;
            }
            else {
                right = mid - 1;
            }
        }

        int base = list.get(best).num();

        return base + (rank - list.get(best).rank());

    }
    private record ranks(int num, int rank){};
}
