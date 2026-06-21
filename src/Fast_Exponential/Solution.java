package Fast_Exponential;

public class Solution {
    private long power(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a;
            }
            a = a * a;
            b>>= 1;
        }
        return res;
    }
}
