package Range_Query.RQ_05_Longest_SubString_of_One_Repeating_Character_2213;

/*
        2213. Longest Substring of One Repeating Character

        Hard

        You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and
        a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.

        The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].

        Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of
        only one repeating character after the ith query is performed.


        Example 1:

        Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
        Output: [3,3,4]
        Explanation:
        - 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
        - 2nd query updates s = "bbbccc".
          The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
        - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
        Thus, we return [3,3,4].

        Example 2:

        Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
        Output: [2,3]
        Explanation:
        - 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
        - 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
        Thus, we return [2,3].


        Constraints:

        1 <= s.length <= 10^5
        s consists of lowercase English letters.
        k == queryCharacters.length == queryIndices.length
        1 <= k <= 10^5
        queryCharacters consists of lowercase English letters.
        0 <= queryIndices[i] < s.length
 */
public class Solution {
    public static void main(String[] args) {

    }
    class Node {
        int maxlen;
        char l;
        char r;
        int pre;
        int suf;

        Node(int maxlen, char l, char r, int pre, int suf) {
            this.maxlen = maxlen;
            this.l = l;
            this.r = r;
            this.pre = pre;
            this.suf = suf;
        }
    }

    Node[] segtree;

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            segtree[node] = new Node(1, s.charAt(l), s.charAt(r), 1, 1);
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node + 1, l, mid, s);
        build(2 * node + 2, mid + 1, r, s);

        segtree[node] = merge(segtree[2 * node + 1], segtree[2 * node + 2], mid - l + 1, r - mid);

    }

    private Node merge(Node left, Node right, int leftlength, int rightlength) {
        Node res = new Node(0, left.l, right.r, 0 , 0);

        res.pre = left.pre;
        if (left.pre == leftlength && left.r == right.l) {
            res.pre = leftlength + right.pre;
        }

        res.suf = right.suf;
        if (right.suf == rightlength && left.r == right.l) {
            res.suf = rightlength + left.suf;
        }

        res.maxlen = Math.max(left.maxlen, right.maxlen);
        if (left.r == right.l) {
            res.maxlen = Math.max(res.maxlen, left.suf + right.pre);
        }
        return res;
    }

    private void update(int node, int l, int r, char c, int idx) {
        if (l == r) {
            segtree[node] = new Node(1, c, c, 1, 1);
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node + 1, l, mid, c, idx);
        } else {
            update(2 * node + 2, mid + 1, r, c, idx);
        }
        segtree[node] = merge(segtree[2 * node + 1], segtree[2 * node + 2], mid - l + 1, r - mid);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        segtree = new Node[4 * n];
        build(0, 0, n - 1, s);
        int[] ans = new int[queryIndices.length];

        for(int i = 0; i < queryIndices.length; i++){
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            update(0, 0, n - 1, c, idx);
            ans[i] = segtree[0].maxlen;
        }

        return ans;
    }
}
