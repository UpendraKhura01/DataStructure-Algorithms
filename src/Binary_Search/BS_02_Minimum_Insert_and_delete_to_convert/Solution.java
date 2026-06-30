package Binary_Search.BS_02_Minimum_Insert_and_delete_to_convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
/*
        Minimum Insert and Delete to Convert
        Difficulty: Hard
        Given two arrays a[] and b[] of size n and m respectively, find the minimum number of insertions and deletions on the array a[],
        required to make both the arrays identical.

        Note: Array b[] is sorted and all its elements are distinct, operations can be performed at any index not necessarily at the end.

        Examples :

        Input: a[] = [1, 2, 5, 3, 1], b[] = [1, 3, 5]
        Output: 4
        Explanation:
        Delete 2 from a: a[] = [1, 5, 3, 1]
        Insert 3 after 1: a[] = [1, 3, 5, 3, 1]
        Delete the last two elements: a[] = [1, 3, 5]
        Total operations = 1 + 1 + 2 = 4.
        Input: a[] = [1, 4], b[] = [1, 4]
        Output : 0
        Explanation: Both the Arrays are already identical.
         Constraints:
        1 ≤ n, m ≤ 10^5
        1 ≤ a[i], b[i] ≤ 10^5
 */

public class Solution {
    public static void main(String[] args) {

    }
    int n;
    int m;
    /******************************************************Optimal_way******************************************************/
    public int minInsAndDel(int[] a, int[] b) {
        // code here

        n = a.length;
        m = b.length;

        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < m; i++) {
            mp.put(b[i], i);

        }
        ArrayList<Integer> index = new ArrayList<>();

        for (int x : a) {
            if (mp.containsKey(x)) {
                index.add(mp.get(x));
            }
        }
        int l = lis(index);
        return (n - l) + (m - l);

    }
    // LIS
    private int lis(ArrayList<Integer> arr) {

        if (arr.size() == 0)
            return 0;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr.get(0));

        for (int i = 1; i < arr.size(); i++) {

            int x = arr.get(i);

            if (x > list.get(list.size() - 1)) {
                list.add(x);
            } else {

                int idx = Collections.binarySearch(list, x);

                if (idx < 0) {
                    idx = -(idx + 1);
                }

                list.set(idx, x);
            }
        }

        return list.size();
    }

// 	// lowerbound code
// 	private int lowerbound(int ele, ArrayList<Integer> arr) {
// 		int l = 0;
// 		int r = arr.size() - 1;
// 		while (l < r) {
// 			int mid = l + (r - l)/ 2;
// 			if (arr.get(mid) >= ele) {
// 				r = mid;
// 			}
// 			else {
// 				l = mid + 1;
// 			}
// 		}
// 		return l;
// 	}
    /******************************************Dynamic_Programming approach O(n^2)******************************************/
    int[] a;
    int[] b;

    public int DP(int[] a, int[] b) {
        // code here
        this.a = a;
        this.b = b;
        n = a.length;
        m = b.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }
        int max = lcs(0, 0, dp);
        return (n - max) + (m - max);
    }
    private int lcs(int i, int j, int[][] dp) {
        if (i == n || j == m) {
            return 0;

        }
        if(dp[i][j] != -1) return dp[i][j];
        if (a[i] == b[j]) {
            return dp[i][j] = 1 + lcs(i + 1, j + 1,dp);
        }
        int max;
        int f = lcs(i + 1, j,dp);
        int s = lcs(i, j + 1, dp);
        max = Math.max(f, s);

        return dp[i][j] = max;
    }
}
