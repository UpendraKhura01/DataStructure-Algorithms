package Math.Binary_String_With_Equal_Sum_of_Two_halves;

public class Solution {

    public static void main(String[] args) {

    }
/****************************************************** Normal Way O(N) *****************************************************/
    long M = 1000000007;
    public int computeValue(int n) {
        // code here
        long[] fact = new long[2 * n + 1];
        long[] inv = new long[2 * n + 1];

        fact[0] = 1;
        for(int i = 1; i<= 2 * n; i++){
            fact[i] = (fact[i - 1] * i) % M;
        }
        inv[2 * n] = power(fact[2 * n], M - 2);
        for(int i = 2 * n - 1;  i >= 0; i--){
            inv[i] = (inv[i + 1] * (i + 1)) % M;
        }

        long ans = 0;
        for(int r = 0; r <= n; r++){
            long c = ncr(n, r, fact, inv);
            ans = (ans + (c * c) % M) % M;
        }
        return (int)ans;

    }
    private long ncr(int n, int r, long[] fact, long[] inv){
        if (r < 0 || r > n) return 0;

        long res = fact[n];
        res = (res * inv[r]) % M;
        res = (res * inv[n - r]) % M;
        return res;
    }
/***************************************************** Optimised way O(1)*************************************************/
    int computeValue2(int n) {
        // code here
        long[] fact = new long[2 * n + 1];


        fact[0] = 1;
        for(int i = 1; i<= 2 * n; i++){
            fact[i] = (fact[i - 1] * i) % M;
        }


        long top = fact[2 * n];
        long temp = fact[n];
        temp = (temp * temp) % M;

        long inv_down = power(temp, M - 2);


        long ans = (top * inv_down) % M;

        return (int)ans;

    }

    private long power(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % M;
            }
            a = (a * a) % M;
            b>>= 1;
        }
        return res;
    }
}
