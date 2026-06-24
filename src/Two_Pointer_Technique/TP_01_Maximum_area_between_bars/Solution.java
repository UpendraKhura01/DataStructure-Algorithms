package Two_Pointer_Technique.TP_01_Maximum_area_between_bars;

import java.util.List;

/*
        Maximum Area Between Bars
        Difficulty: Medium
        Given an integer array height[], where height[i] represents the height of the ith bar arranged in a row,
        find the maximum rectangular area that can be formed by selecting any two bars.
        The area is calculated based on the original positions of the selected bars.

        Examples :

        Input: height[] = [2, 5, 4, 3, 7]
        Output: 10
        Explanation:

        The maximum rectangular area is formed by selecting the bars of heights 5 and 7.
        There are 2 bars between them, so the area is: min(5, 7) × 2 = 10
        Input: height[] = [1, 3, 4]
        Output: 1
        Explanation: Selecting bars 1 and 4 gives one bar between them, so the area is: min(1, 4) × 1 = 1
        Constraints:
        1 ≤ height.size() ≤ 10^5
        1 ≤ height[i] ≤ 10^4
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxArea(List<Integer> height) {
        // code here
        int ans = 0;
        int l = 0;
        int r = height.size() - 1;

        while (l < r) {
            int dist = r - l - 1;
            int cur_area = Math.min(height.get(l), height.get(r)) * dist;
            ans = Math.max(ans, cur_area);

            if (height.get(l) < height.get(r))
                l++;
            else
                r--;
        }

        return ans;
    }
}
