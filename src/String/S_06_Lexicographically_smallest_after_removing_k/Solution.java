package String.S_06_Lexicographically_smallest_after_removing_k;

/*
        Lexicographically smallest after removing k
        Difficulty: Medium
        Given a string s consisting of n lowercase characters.
        Return the lexicographically smallest string after removing exactly k characters from the string.
        But you have to correct the value of k, i.e., if the length of the string is a power of 2, reduce k by half,
         else multiply k by 2. You can remove any k characters.

        Note: If it is not possible to remove k (the value of k after correction) characters or if the resulting string is empty return -1.

        Examples:

        Input: s = "fooland", k = 2
        Output: "and"
        Explanation: As the size of the string = 7 which is not a power of 2, hence k = 4.
        After removing 4 characters from the given string, the lexicographically smallest string is "and".
        Input: s = "code", k = 4
        Output: "cd"
        Explanation: As the length of the string = 4, which is 2 to the power 2, hence k = 2.
        Hence, lexicographically smallest string after removal of 2 characters is "cd".
        Constraints:
        1 ≤ n ≤ 10^5
        1 ≤ k ≤ 10^5
 */
public class Solution {
    public static void main(String[] args) {

    }
    String lexicographicallySmallest(String s, int k) {
        // code here
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        if((n & (n - 1)) == 0) k /= 2;
        else k *= 2;

        if (k >= n) {
            return "-1";
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            while (!sb.isEmpty() && c < sb.charAt(sb.length() - 1) && k > 0) {

                sb.deleteCharAt(sb.length() - 1);
                k--;

            }

            sb.append(c);
        }

        while(k > 0 && !sb.isEmpty()){

            sb.deleteCharAt(sb.length() - 1);

            k--;
            if(k > 0 && sb.isEmpty()) return "-1";
        }
        return sb.isEmpty() ? "-1" : sb.toString();
    }
}
