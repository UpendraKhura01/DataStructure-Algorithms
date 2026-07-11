package Math.Ways_to_Express_as_Sum_of_Consecutives;
/*
        Ways to Express as Sum of Consecutives
        Difficulty: Medium
        Given a number n, find the number of ways to represent this number as a sum of 2 or more consecutive natural numbers.

        Examples:

        Input: n = 10
        Output: 1
        Explanation: There is only one way, 10 = 1+2+3+4.
        Input: n = 15
        Output: 3
        Explanation: There are 3 ways, (15 = 1+2+3+4+5), (15 = 4+5+6) and (15 = 7+8).
        Constraints:
        1 ≤ n ≤ 10^8
 */
public class Solution {
    public static void main(String[] args) {

    }
    int getCount(int n) {
        int count = 0;
        for(int i = 2; i * (i - 1) / 2 < n;  i++){
            int check = n - (i * (i - 1))/2;
            if(check % i == 0 && check >= 1){
                count++;
            }
        }

        return count;
    }
}
