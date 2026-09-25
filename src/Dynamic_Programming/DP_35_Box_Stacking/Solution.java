package Dynamic_Programming.DP_35_Box_Stacking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
        Box Stacking

        Difficulty: Hard
        Given three arrays height[], width[], and length[] of size n, where height[i], width[i], and length[i]
        represent the dimensions of the ith box, find the maximum possible height of a stack formed using these boxes.

        A box can be rotated so that any of its dimensions becomes its height.
         A box can be placed on top of another only if both dimensions of its base are strictly smaller than
         those of the box below.
        Multiple instances of the same box can be used.
        Examples:

        Input: height[] = [4, 1, 4, 10], width[] = [6, 2, 5, 12], length[] = [7, 3, 6, 32]
        Output: 60
        Explanation: One possible arrangement of the boxes from bottom to top is shown below. Note that there can
        be multiple instances of a box type.

        Hence, the total height of this stack is 10 + 32 + 4 + 4 + 6 + 1 + 3 = 60. No other combination of boxes
        produces a height greater than this.
        Input: height[] = [1, 4, 3], width[] = [2, 5, 4], length[] = [3, 6, 1]
        Output: 15
        Explanation: One possible arrangement of the boxes from bottom to top is shown below:

        Hence, the total height of this stack is 4 + 6 + 1 + 1 + 3 = 15 No other combination of boxes produces a height
        greater than this.
        Constraints:

        1 ≤ height.size(), width.size(), length.size() ≤ 100
        1 ≤ height[i], width[i], length[i] ≤ 10^6
 */
public class Solution {
    public static void main(String[] args) {

    }
    List<int[]> all;
    int[] dp;
    int n;
    public int maxHeight(int[] height, int[] width, int[] length) {
        // Code here
        n = height.length;

        all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int h = height[i];
            int w = width[i];
            int l = length[i];

            all.add(new int[] {Math.max(w, h), Math.min(w, h), l});
            all.add(new int[] {Math.max(h, l), Math.min(h, l), w});
            all.add(new int[] {Math.max(w, l), Math.min(w, l), h});

        }
        dp = new int[all.size()];
        Arrays.fill(dp, -1);

        Collections.sort(all, (a, b) -> {
            return Integer.compare(b[0] * b[1], a[0] * a[1]);
        });

        int total = all.size();
        int ans = 0;
        for (int i = 0; i < total; i++) {
            int[] cur = all.get(i);
            dp[i] = cur[2];
            ans = Math.max(ans, dp[i]);
        }


        for (int i = 0; i < total; i++) {
            int[] cur = all.get(i);

            for (int j = 0; j < i; j++) {
                int[] prev = all.get(j);

                if (cur[0] < prev[0] && cur[1] < prev[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + cur[2]);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;

    }
    // 	private int solve(int i) {
    // 		if (dp[i] != -1) {
    // 			return dp[i];
    // 		}
    // 		int[] cur = all.get(i);
    // 		int maxHeight = cur[2];

    // 		for (int j = 0; j < all.size(); j++) {
    // 			int[] box = all.get(j);
    // 			if (box[0] < cur[0] && box[1] < cur[1]) {
    // 				maxHeight = Math.max(maxHeight, cur[2] + solve(j));
    // 			}
    // 		}

    // 		return dp[i] = maxHeight;
    // 	}
}
