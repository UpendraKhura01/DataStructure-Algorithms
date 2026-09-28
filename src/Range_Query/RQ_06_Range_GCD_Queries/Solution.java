package Range_Query.RQ_06_Range_GCD_Queries;

import java.util.ArrayList;

/*
        Range GCD Queries

        Difficulty: Medium
        Given an integer array arr[] and a 2D array queries[][] containing q queries,
        where each query is one of the following two types:

        Type 1: [0, l, r] -> Return the GCD of all elements in the range [l, r] (both inclusive).
        Type 2: [1, index, value] -> Update arr[index] to value.
        Return an array containing the answers to all Type 1 queries in the order they appear in queries[][].

        Note: Use 0-based indexing.

        Examples:

        Input: arr[] = [2, 3, 4, 6, 8, 16], q = 3, queries[][] = [[0, 0, 2], [1, 3, 8], [0, 2, 5]]
        Output: [1, 4]
        Explanation: Initially, arr[] = [2, 3, 4, 6, 8, 16].
        Query [0, 0, 2]: Find the GCD of the subarray arr[0...2] = [2, 3, 4]. The GCD is 1.
        Query [1, 3, 8]: Update arr[3] from 6 to 8. The array becomes [2, 3, 4, 8, 8, 16].
        Query [0, 2, 5]: Find the GCD of the subarray arr[2...5] = [4, 8, 8, 16]. The GCD is 4.
        Therefore, the answers to all Type 0 queries are [1, 4].


        Input: arr[] = [12, 18, 24, 30, 36], q = 4, queries[][] = [[0, 1, 3], [1, 2, 15], [0, 0, 2], [0, 2, 4]]
        Output: [6, 3, 3]
        Explanation: Initially, arr[] = [12, 18, 24, 30, 36].
        Query [0, 1, 3]: Find the GCD of the subarray arr[1...3] = [18, 24, 30]. The GCD is 6.
        Query [1, 2, 15]: Update arr[2] from 24 to 15. The array becomes [12, 18, 15, 30, 36].
        Query [0, 0, 2]: Find the GCD of the subarray arr[0...2] = [12, 18, 15]. The GCD is 3.
        Query [0, 2, 4]: Find the GCD of the subarray arr[2...4] = [15, 30, 36]. The GCD is 3.
        Therefore, the answers to all Type 0 queries are [6, 3, 3].

        Constraints:
        1 ≤ arr.size() ≤ 105
        1 ≤ q ≤ 105
        0 ≤ l, r, index ≤ arr.size()-1
        1 ≤ arr[i], value ≤ 105
 */
public class Solution {
    public static void main(String[] args) {

    }
    int[] segTree;
    int n;
    int[] arr;
    public ArrayList<Integer> processQueries(int[] arr, int[][] queries) {
        // code here
        n = arr.length;
        segTree = new int[4 * n];
        this.arr = arr;
        ArrayList<Integer> ans = new ArrayList<>();

        buildTree(0, 0, n - 1);

        for(int[] q : queries){
            int type = q[0];
            if(type == 0){
                int left = q[1];
                int right = q[2];

                int val = query(0, left, right, 0, n - 1);
                ans.add(val);
            }
            else{
                int idx = q[1];
                int val = q[2];
                update(0, 0, n - 1, idx, val);
            }
        }
        return ans;
    }

    void buildTree(int node, int left, int right) {
        if (left == right) {
            segTree[node] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;

        buildTree(2 * node + 1, left, mid);
        buildTree(2 * node + 2, mid + 1, right);
        segTree[node] = GCD(segTree[2 * node + 1], segTree[2 * node + 2]);
    }
    int query(int node, int ql, int qr, int l, int r){
        if(l >= ql && r <= qr){
            return segTree[node];
        }
        if(r < ql || l > qr){
            return 0;
        }
        int mid = l + (r - l)/2;
        int left = query(2 * node + 1, ql, qr, l, mid);

        int right = query(2 * node + 2, ql, qr, mid + 1, r);


        return GCD(left, right);
    }
    void update(int node, int l, int r, int idx, int val){
        if(l == r){
            segTree[node] = val;
            return;
        }
        int mid = l + (r - l)/2;
        if(idx <= mid){
            update(2 * node + 1, l, mid, idx, val);
        }
        else{
            update(2 *node + 2, mid + 1, r, idx, val);
        }
        segTree[node] = GCD(segTree[2 * node + 1], segTree[2 * node + 2]);
    }

    int GCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
