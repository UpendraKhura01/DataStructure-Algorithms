package String.S_18_Longest_Matching_in_Dictionary_With_Removals;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

    }
    ArrayList<ArrayList<Integer>> list;
    public String findLongestWord(String s, List<String> d) {
        // code here
        int n = s.length();
        list = new ArrayList<>();

        for(int i = 0; i < 26; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            list.get(c - 'a').add(i);
        }
        int maxLen = 0;
        String ans = "";
        for(String word : d){
            if(isValid(s, word)){
                if(word.length() > maxLen){
                    ans = word;
                    maxLen = word.length();
                }
                else if(word.length() == maxLen){
                    if(word.compareTo(ans) < 0){
                        ans = word;
                    }
                }

            }


        }

        return ans;

    }
    boolean isValid(String s, String word){
        int prev = -1;

        for(char c : word.toCharArray()){

            int cur = -1;
            ArrayList<Integer> pos = list.get(c - 'a');

            for(int i = 0; i < pos.size(); i++){

                int p = pos.get(i);

                if(p > prev){
                    cur = p;
                    break;
                }
            }
            if(cur == -1){
                return false;
            }

            prev = cur;

        }

        return true;
    }
}
