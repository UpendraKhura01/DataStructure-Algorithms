package Range_Query.Range_Minimum_Query;
/*
        Range Minimum Query
        Difficulty: Medium
        Given an array A[ ] and its size N your task is to complete two functions  a constructST  function,
         which builds the segment tree  and a function RMQ which finds range minimum query in a range [a,b] of the given array.

        Input:
        The task is to complete two functions constructST and RMQ.
        The constructST function builds the segment tree and takes two arguments the array A[ ] and the size of the array N.
        It returns a pointer to the first element of the segment tree array.
        The RMQ function takes 4 arguments the first being the segment tree st constructed,
        second being the size N and then third and forth arguments are the range of query a and b.
        The function RMQ returns the min of the elements in the array from index range a and b.
        There are multiple test cases. For each test case, this method will be called individually.

        Output:
        The function RMQ should return the min element in the array from range a to b.

        Example:

        Input (To be used only for expected output)
        1
        4
        1 2 3 4
        2
        0 2 2 3
        Output
        1 3
        Explanation
        1. For query 1 ie 0 2 the element in this range are 1 2 3
           and the min element is 1.
        2. For query 2 ie 2 3 the element in this range are 3 4
           and the min element is 3.
        Constraints:
        1<=T<=100
        1<=N<=10^3+1

        1<=A[i]<=10^9
        1<=Q(no of queries)<=10000
        0<=a<=b
 */
public class Solution {
    public static void main(String[] args) {

    }
    class GfG {
        static int st[];

        public static int[] constructST(int arr[], int n) {
            // Add your code here
            st = new int[4 * n];

            build(st, 0 , n - 1, 0, arr);
            return st;
        }
        private static void build(int[] segtree, int left, int right, int cur, int[] arr) {
            if (left == right) {
                segtree[cur] = arr[left];
                return;

            }
            int mid = left + (right - left) / 2;

            build(segtree, left, mid, 2 * cur + 1, arr); // left
            build(segtree, mid + 1, right, 2 * cur + 2, arr); // right
            segtree[cur] = Math.min(segtree[2 * cur + 1], segtree[2 * cur + 2]);

        }

        /* The functions return s the
        min element in the range
        from l and r */
        public static int RMQ(int st[], int n, int l, int r) {
            // Add your code here

            return query(l, r, 0, n - 1, 0);
        }
        private static int query(int start, int end, int left, int right, int cur)
        {
            if (left >= start && right <= end) {
                return st[cur];
            }
            if (left > end || right < start) {
                return Integer.MAX_VALUE;
            }
            int mid = left + (right - left) / 2;

            return Math.min(query(start, end, left, mid, 2 * cur + 1),
                    query(start, end, mid + 1, right, 2 * cur + 2));
        }
    }
}
