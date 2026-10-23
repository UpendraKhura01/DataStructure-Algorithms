package Dynamic_Programming.DP_37_Good_Indices_that_Escapes_The_Array;

/*
        Count Indices That Escape Array

        Difficulty: Medium
        Given an array arr[], where arr[i] represents the exact number of steps to move from index i.
        An index is considered good if repeatedly following the jumps eventually takes you outside the array.
        Otherwise, if the jumps result in an infinite loop, the index is considered bad.

        A positive value indicates a move to the right
        A negative value indicates a move to the left.
        A 0 value means no move and considered bad.
        Return the number of good indices.

        Examples:

        Input: [2, 3, -1, 2, -2, 4, 1]
        Output: 3
        Explanation: Indices 3, 5, and 6 are good because their jump sequences eventually leave the array.
        Indices 1, 2, and 4 form a cycle, while index 0 eventually enters this cycle through index 2. Hence,
        the total number of good indices is 3.

        Input: [1, 0, -3, 0, -5, 0]
        Output: 2
        Explanation: Indices 2 and 4 are good because their jumps take them outside the array. Indices 1, 3, and 5 are
        bad because their jumps are zero. Index 0 is also bad because it jumps to index 1, which is a dead end.
        Hence, the total number of good indices is 2.

        Constraints:

        1 ≤ arr.size() ≤ 10^5
        -105 ≤ arr[i] ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] vis;
    int n;
    int[] arr;
    public int goodIndices(int[] arr) {
        // code here
        n = arr.length;
        this.arr = arr;
        vis = new int[n];

        for(int i = 0; i< n; i++){
            dfs(i);
        }
        int ans = 0;

        for(int i : vis){
            if(i == 2){
                ans++;
            }
        }
        return ans;
    }
    int dfs(int cur){
        if(cur < 0 || cur >= n){
            return 2;
        }

        if(vis[cur] == 2 || vis[cur] == -1){
            return vis[cur];
        }
        if(vis[cur] == 1){
            return -1;
        }

        vis[cur] = 1;
        int next = cur + arr[cur];
        int res = dfs(next);

        return vis[cur] = res;
    }
}
