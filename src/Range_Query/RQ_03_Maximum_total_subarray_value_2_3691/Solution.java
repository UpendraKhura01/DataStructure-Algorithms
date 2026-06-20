package Range_Query.RQ_03_Maximum_total_subarray_value_2_3691;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {

    }
    long maxTotalValue(int[] nums, int k) {
        long ans = 0;
        int n = nums.length;
        segmenttree maxtree = new segmenttree(n, false, nums);
        segmenttree mintree = new segmenttree(n, true, nums);

        PriorityQueue<state> pq =  new PriorityQueue<>((a, b) -> Long.compare(b.value(), a.value()));
        for(int i = 0;  i < n; i++){
            long val = maxtree.findvalue(i, n - 1, mintree, maxtree);
            pq.add(new state(val, i, n -1));
        }
        while(k > 0 && !pq.isEmpty()){
            state s = pq.poll();
            ans += s.value();
            if(s.l() < s.r()){
                long val = maxtree.findvalue(s.l(), s.r() - 1, mintree, maxtree);
                pq.add(new state(val, s.l(), s.r() -1));
            }
            k--;
        }

        return ans;

    }
    public record state(long value, int l, int r) {}
    class segmenttree{
        int[] segtree;
        int n;
        boolean flag;
        segmenttree(int n, boolean flag, int[] arr){
            segtree = new int[4 * n];
            this.flag = flag;
            this.n = n;
            build(0, 0, n -1, arr);

        }

        private void build(int cur, int l, int r, int[] arr){
            if(l == r){
                segtree[cur] = arr[l];
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * cur + 1, l, mid, arr);
            build(2 * cur + 2, mid + 1, r, arr);
            if(flag == true){
                segtree[cur] = Math.min(segtree[2 * cur + 1], segtree[2 * cur + 2]);
            }
            else
                segtree[cur] = Math.max(segtree[2 * cur + 1], segtree[2 * cur + 2]);
        }
        private long query(int ql, int qr, int cur, int l, int r){
            if(l >= ql && r <= qr){
                return segtree[cur];
            }
            if(l > qr || r < ql){
                return (flag == true) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            int mid = l + (r - l) / 2;
            long left = query(ql, qr, 2 * cur + 1, l, mid);
            long right = query(ql, qr, 2 * cur + 2, mid + 1, r);
            if(flag == true){
                return Math.min(left, right);
            }

            return Math.max(left, right);
        }
        long findvalue(int l, int r, segmenttree mintree, segmenttree maxtree){
            long max = maxtree.query(l, r, 0, 0, n - 1);
            long min = mintree.query(l, r, 0, 0, n - 1);
            return max - min;
        }
    }
}
