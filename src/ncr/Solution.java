package ncr;

public class Solution {
    long M = 1000000007;
    long ncr(int n, int r){
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

        if (r < 0 || r > n) return 0;

        long res = fact[n];
        res = (res * inv[r]) % M;
        res = (res * inv[n - r]) % M;
        return res;
    }
    long power(long a, long b) {
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
