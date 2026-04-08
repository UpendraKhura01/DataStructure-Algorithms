package Recursion.R_01_Gray_Code;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int n = 3;
        ArrayList<String> ans = graycode(n);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }

    }
    static ArrayList<String> graycode(int n) {
        // code here

        return helper(n);

    }
    static ArrayList<String> helper(int n){
        if (n == 1){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("0");
            temp.add("1");
            return temp;
        }

        ArrayList<String> cur = helper(n - 1);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++){
            ans.add("0"+ cur.get(i));
        }
        for (int i = cur.size() - 1; i >= 0; i--){
            ans.add("1"+ cur.get(i));
        }
        return ans;
    }
}
