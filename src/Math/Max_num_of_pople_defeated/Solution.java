package Math.Max_num_of_pople_defeated;
/*
        Maximum Number of People Defeated
        Difficulty: Medium
        There are infinitely many people standing in a row, indexed from 1. The strength of the person at index i is i².

        Given a strength p, determine the maximum number of people that can be defeated.
        A person with strength x can be defeated only if p ≥ x, after which the strength p decreases by x.

        Examples :

        Input: p = 14
        Output: 3
        Explanation: The strengths of the first few people are 1, 4, 9, 16, .... Defeating the first three people consumes 1 + 4 + 9 = 14 strength, leaving 0.
        Therefore, the maximum number of people that can be defeated is 3.
        Input: p = 10
        Output: 2
        Explanation: After defeating people with strengths 1 and 4, the remaining strength is 5, which is less than the next required strength 9.
        Constraints:
        1 ≤ p ≤ 3*10^8`
 */

public class Solution {
    public static void main(String[] args) {

    }

    /************************************************Optimal solution O(logn)************************************************/
    int maxPeopleDefeated(int p) {
        int ans = 0;
        long l = 1;
        long r = 10000;

        while (l <= r) {

            long mid = l + (r - l) / 2;
            long sum = (mid * (mid + 1) * (2 * mid + 1)) / 6;

            if (sum <= p) {
                ans = (int) mid;
                l = mid + 1;
            } else r = mid - 1;
        }

        return ans;
    }

    /**
     * Normal solution O(n)
     **/
    int maxPeopleDefeated1(int p) {
        // code here
        int count = 0;
        for (int i = 1; i < 100000; i++) {

            long cur = ((long) i * i);
            if (p >= cur) {
                p -= (int) cur;
                count++;
            } else break;
        }

        return count;
    }
}
