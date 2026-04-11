package arrays.A_12_Min_operations_to_transform_arr_into_alternating_prime;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {8, 12};

        // Original approach
        System.out.println("Normal: " + minOperations(arr));

        // Sieve optimized approach
        System.out.println("Sieve: " + minOperationsSieve(arr));
    }

    // ================= ORIGINAL APPROACH =================
    static int minOperations(int[] nums) {

        int ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0){
                if (!isprime(nums[i])) {
                    int a = nextPrime(nums[i]);
                    ans += (a - nums[i]);
                }
            }
            else{
                if (isprime(nums[i])) {
                    int next = nums[i] + 1;
                    while (isprime(next)) next++;
                    ans += next - nums[i];
                }
            }
        }
        return ans;
    }

    static int nextPrime(int n){
        int t = n;
        while (!isprime(t)) t++;
        return t;
    }

    static boolean isprime(int n){
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    // ================= SIEVE OPTIMIZED APPROACH =================
    static int minOperationsSieve(int[] nums) {

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int LIMIT = max + 100;

        boolean[] isPrime = sieve(LIMIT);
        int[] nextPrime = buildNextPrime(isPrime);
        int[] nextNonPrime = buildNextNonPrime(isPrime);

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) { // need PRIME
                int target = nextPrime[nums[i]];
                ans += (target - nums[i]);
            } else { // need NON-PRIME
                int target = nextNonPrime[nums[i]];
                ans += (target - nums[i]);
            }
        }

        return ans;
    }

    // Sieve
    static boolean[] sieve(int max) {
        boolean[] isPrime = new boolean[max + 1];

        for (int i = 2; i <= max; i++) isPrime[i] = true;

        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    // Next Prime Lookup
    static int[] buildNextPrime(boolean[] isPrime) {
        int n = isPrime.length;
        int[] nextPrime = new int[n];

        int next = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (isPrime[i]) next = i;
            nextPrime[i] = next;
        }
        return nextPrime;
    }

    // Next Non-Prime Lookup
    static int[] buildNextNonPrime(boolean[] isPrime) {
        int n = isPrime.length;
        int[] nextNonPrime = new int[n];

        int next = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (!isPrime[i]) next = i;
            nextNonPrime[i] = next;
        }
        return nextNonPrime;
    }
}