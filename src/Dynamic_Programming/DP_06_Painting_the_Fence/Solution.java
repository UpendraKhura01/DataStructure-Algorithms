package Dynamic_Programming.DP_06_Painting_the_Fence;

public class Solution {
    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        System.out.println(countWays(n, k));

    }
    static int countWays(int n, int k) {
        // code here.
        if(n == 1) return k;

        int same = k;
        int diff = k * (k - 1);

        for(int i = 3; i <= n; i++){
            int newsame = diff;
            int newdiff = (same + diff) * (k - 1);

            same = newsame;
            diff = newdiff;
        }

        return same + diff;
    }
}
