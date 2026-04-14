package arrays.A_14_Next_smallest_pallindrome_in_integer_arr;
/*

Next Smallest Palindrome
Difficulty: Hard
Given a number, in the form of an array num[] containing digits from 1 to 9(inclusive).
Find the next smallest palindrome strictly larger than the given number.

Examples:

Input: num[] = [9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2]
Output: [9, 4, 1, 8, 8, 0, 8, 8, 1, 4, 9]
Explanation: Next smallest palindrome is 9 4 1 8 8 0 8 8 1 4 9.
Input: num[] = [2, 3, 5, 4, 5]
Output: [2, 3, 6, 3, 2]
Explanation: Next smallest palindrome is 2 3 6 3 2.
Constraints:
1 ≤ n ≤ 105
1 ≤ num[i] ≤ 9

 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = {9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2};
        int[] ans = nextPalindrome(nums);
        for(int i : ans){
            System.out.print(i + " ");
        }

    }
    static int[] nextPalindrome(int[] num) {
        // code here
        int n = num.length;
        if (all9(num)){
            int[] arr = new int[n + 1];
            arr[0] = arr[n] = 1;
            return arr;
        }

        int mid = n / 2;
        int i = n / 2 - 1;
        int j = (n % 2 == 0) ? mid : mid + 1;
        int[] ans = num;

        while (i >= 0 && num[i] == num[j]){
            i--;
            j++;
        }
        boolean check = false;
        if (i < 0 || num[i] < num[j]) check = true; // either it is already a palindrome or need increment

        i = n / 2 - 1;
        j = (n % 2 == 0) ? mid : mid + 1;

        while (i >= 0) {
            ans[j] = ans[i];
            i--;
            j++;
        }

        if (check){
            int carry = 1;
            i = mid - 1;

            // This portion checks the answer for odd length where one middle element stays and no copy required
            if (n % 2 != 0){
                ans[mid] = ans[mid] + carry;
                carry = ans[mid] / 10;
                ans[mid] %= 10;
                j = mid + 1;
            }
            else j = mid;

            while (i >= 0){
                ans[i] = ans[i] + carry;
                carry = ans[i] / 10;
                ans[i] %= 10;

                ans[j] = ans[i];
                i--;
                j++;
            }
        }
        return ans;
    }
    static boolean all9(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 9) return false;
        }
        return true;
    }


}
