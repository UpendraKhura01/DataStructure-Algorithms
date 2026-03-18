package Miscellaneous.LC_1622_Fancy_Sequence;

import java.util.ArrayList;

public class Fancy_Sequence {
    public static void main(String[] args) {
        Fancy obj = new Fancy();
        obj.append(2);
        obj.addAll(3);
        obj.append(7);
        obj.multAll(2);
        int idx = obj.getIndex(0);
        System.out.println(idx);
        obj.addAll(3);
        obj.append(10);
        obj.multAll(2);
        idx = obj.getIndex(0);
        System.out.println(idx);
        idx = obj.getIndex(1);
        System.out.println(idx);
        idx = obj.getIndex(2);
        System.out.println(idx);
    }

    static class Fancy {
        ArrayList<Long> list;
        long MOD = 1000000007;
        long add = 0;
        long mul = 1;

        public Fancy() {
            list = new ArrayList<>();
        }

        public void append(int val) {
            list.add(((val - add + MOD) % MOD) * modInverse(mul) % MOD);
        }

        public void addAll(int inc) {
            add = (add + inc) % MOD;

        }

        public void multAll(int m) {
            add = (add * m) % MOD;
            mul = (mul * m) % MOD;

        }

        public int getIndex(int idx) {
            if (idx >= list.size()) return -1;

            return (int) ((list.get(idx) * mul % MOD + add) % MOD);

        }
          long modInverse(long x) {
            return power(x, MOD - 2);
        }

         long power(long x, long y) {
            long res = 1;
            x %= MOD;
            while (y > 0) {
                if ((y & 1) == 1) res = (res * x) % MOD;
                x = (x * x) % MOD;
                y >>= 1;
            }
            return res;
        }
    }
}
