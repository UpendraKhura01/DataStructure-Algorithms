package String.S_19_Roll_characters_of_A_String;

/*
        Roll Characters of String

        Difficulty: Medium
        Given a string s consisting of lowercase English letters and an integer array roll[],
        perform a sequence of roll operations on the string.

        For each value roll[i], increment each of the first roll[i] characters of the string by 1 in the English alphabet.
        After 'z', the character wraps around to 'a'.

        Return the final string after applying all roll operations.

        Note: A roll operation affects the current state of the string, including all previous modifications.

        Examples:

        Input: s = "bca", roll[] = [1, 2, 3]
        Output: eeb
        Explanation: Initially, s = "bca"
        roll[0] = 1: Roll the first 1 character. "bca" -> "cca"
        roll[1] = 2: Roll the first 2 characters. "cca"-> "dda"
        roll[2] = 3: Roll the first 3 characters. "dda" -> "eeb"
        After applying all the roll operations, the final string is "eeb".
        Input: s = "zcza", roll[] = [1, 1, 3, 4]
        Output: debb
        Explanation: Initially, s = "zcza".
        Roll first 1 character: "zcza" -> "acza"
        Roll first 1 character again: "acza" -> "bcza"
        Roll first 3 characters: "bcza" -> "cdaa"
        Roll first 4 characters: "cdaa" -> "debb"
        Therefore, the final string is "debb".
        Constraints:

        1 ≤ s.size(), roll.size() ≤ 10^5
        1 ≤ roll[i] ≤ s.size()
        s consists only of lowercase English alphabets
 */
public class Solution {
    public static void main(String[] args) {

    }
    String findRollOut(String s, int[] roll) {
        // code here
        int n = s.length();
        StringBuilder ans = new StringBuilder();

        int[] sum = new int[n];

        for(int i = 0; i < roll.length; i++){
            int pos = roll[i] - 1;
            sum[pos]++;
        }
        for(int i = n - 2; i >= 0; i--){
            sum[i] = sum[i] + sum[i + 1];
        }


        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            int increase = sum[i];

            char newC = (char)('a' + (c - 'a' + increase) % 26);
            ans.append(newC);
        }

        return ans.toString();
    }
}
