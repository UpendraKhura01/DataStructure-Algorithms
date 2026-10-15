package Bit_magic.BM_04_Maximum_Without_Three_Consecutive_set_Bits;

/*
        Maximum Without 3 Consecutive Set Bits

        Difficulty: Medium
        Given a non-negative integer n, you can only change a set bit (1) to an unset bit (0).
        Find the maximum possible value of n such that its binary representation does not contain three consecutive set bits
        (111) after performing zero or more allowed operations.

        Examples:

        Input: n = 2
        Output: 2
        Explanation: The binary representation of 2 is 10, which does not contain three consecutive set bits. Therefore,
        no change is required.
        Input: n = 7
        Output: 6
        Explanation: The binary representation of 7 is 111, which contains three consecutive set bits.
        Changing the rightmost set bit to 0 gives: 111 -> 110. Thus, the maximum possible value is 6.
        Constraints:

        0 ≤ n ≤ 10^9

 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxValue(int num) {
        // code here
        int consecutive = 0;
        int posFromLeft = Integer.numberOfTrailingZeros(Integer.highestOneBit(num));

        for (int i = posFromLeft; i >= 0 ; i--) {
            if (((num>> i) & 1) != 0) {

                consecutive++;

                if (consecutive == 3) {
                    num = num ^ (1 << i);
                    consecutive = 0;
                }
            }
            else {
                consecutive = 0;
            }

        }

        return num;
    }
}
