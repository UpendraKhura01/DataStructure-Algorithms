package Recursion.R_06_N_digit_num_with_increasing_digits;

import java.util.ArrayList;

/*
        N-Digit Numbers with Increasing Digits
        Difficulty: Medium
        Given an integer n, return all the n digit numbers in increasing order,
        such that their digits are in strictly increasing order(from left to right).

        Examples :

        Input: n = 1
        Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        Explanation: Single digit numbers are considered to be strictly increasing order.
        Input: n = 2
        Output: [12, 13, 14, 15, 16, 17, 18, 19, 23....79, 89]
        Explanation: For n = 2, the correct sequence is 12 13 14 15 16 17 18 19 23 and so on up to 89.
        Input: n = 15
        Output: []
        Explanation: No such number exist.
        Constraints:
        1 ≤ n ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    static ArrayList<Integer> ans;
    public static ArrayList<Integer> increasingNumbers(int n) {
        // code here
        ans = new ArrayList<>();
        if (n == 1) ans.add(0);
        int[][] dp = new int[n][9];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= 9; j++){

            }
        }
        for(int i = 1; i <= 9; i++){
            solve(i, i, n);
        }
        return ans;
    }
    private static void solve(int num, int prev, int n){
        if(String.valueOf(num).length() == n){

            ans.add(num);
            return;
        }
        if(prev == 9) return;

        for(int i = prev + 1; i <= 9; i++){
            solve(num * 10 + i, i, n);

        }
    }
}
