package String.S_16_Max_Digit_Sum_Number_in_1_to_N.Solution;

/*
        Max Digit Sum Number in 1 to n

        Difficulty: Easy
        Given a number n, find a number in the range from 1 to n such that its digit sum is maximum.
        If there are multiple such numbers, return the largest of them.

        Examples:

        Input: n = 48
        Output: 48
        Explanation: There are two numbers with maximum digit sum = 12. The numbers are 48 and 39. Since 48 > 39, so 48 is the answer.
        Input: n = 90
        Output: 89
        Explanation: 89 gives us the largest digit sum in the range from 1 to n. Hence the answer is 89.
        Constraints:

        1 ≤ n ≤ 10^9
 */
public class Solution {
    public static void main(String[] args) {

    }

    public static int findMax(int n) {
        // code here
        int ans = n;
        int maxSum = 0;

        String s = String.valueOf(n);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            maxSum += s.charAt(i) - '0';
        }

        int curSum = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            curSum += c - '0';

            if (c == '0') {
                continue;
            }

            StringBuilder sb = new StringBuilder(s);

            sb.setCharAt(i, (char) (c - 1));
            for (int j = i + 1; j < len; j++) {
                sb.setCharAt(j, '9');
            }

            int num = Integer.parseInt(sb.toString());

            int newSum = curSum - 1 + ((len - 1 - i) * 9);

            if (newSum > maxSum || (newSum == maxSum && num > ans)) {
                maxSum = newSum;
                ans = num;
            }

        }
        return ans;
    }
}


