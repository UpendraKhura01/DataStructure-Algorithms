package arrays.A_25_check_product_pair;

import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {

    }
    static boolean isProduct(int[] arr, long target) {
        HashSet<Long> set = new HashSet<>();

        for (int x : arr) {
            long num = x;

            if (target == 0) {
                if (num == 0 && set.size() > 0) return true;
                if (num != 0 && set.contains(0L)) return true;
            } else {
                if (num != 0 && target % num == 0) {
                    long need = target / num;
                    if (set.contains(need)) return true;
                }
            }

            set.add(num);
        }

        return false;
    }
}
