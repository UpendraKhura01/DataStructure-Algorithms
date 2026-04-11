package arrays.A_13_Total_num_of_time_digit_appears;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1,34,7};
        int digit = 9;
        System.out.println(countDigitOccurrences(arr, digit));

    }
    static int countDigitOccurrences(int[] nums, int digit) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {

            ans += countdigit(nums[i], digit);
        }

        return ans;
    }
    static int countdigit(int num, int digit){
        int count = 0;
        while(num > 0){
            int d = num % 10;
            if (d == digit) count++;
            num = num / 10;
        }

        return count;
    }
}
