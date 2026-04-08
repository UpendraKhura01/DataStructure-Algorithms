package String.MIrror_letter_frerq;

public class Solution {
    public static void main(String[] args) {
        String s = "byby";
        System.out.println(mirrorFrequency(s));

    }
    static int mirrorFrequency(String s) {
        int n = s.length();

        int[] freqC = new int[26];
        int[] freqN = new int[10];

        for (int i = 0; i < n; i++){
            char c = s.charAt(i);

            if ((int)c >= 'a'){
                freqC[c - 'a']++;
            }
            else freqN[c - '0']++;
        }

        int ans = 0;
        for (int i = 0; i < 13; i++) {
            ans += Math.abs(freqC[i] - freqC[25-i]);
        }

        for (int i = 0; i < 5; i++) {
            ans += Math.abs(freqN[i] - freqN[9 - i]);
        }
        return ans;

    }
}
