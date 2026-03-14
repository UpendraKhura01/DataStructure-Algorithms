package String;

import java.util.ArrayList;
import java.util.Scanner;

public class S_01_generate_all_ip_address {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the string:");
        String s=sc.nextLine();
        ArrayList<String> list =generateIp(s);
        System.out.println("ans/n");
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }

    }
    static ArrayList<String> generateIp(String s) {
        // code here

        ArrayList<String> list = new ArrayList<>();
        if(s.length()>12) return new ArrayList<>();
        String cur="";
        helper(s,0,0,cur,list);

        return list;

    }
    static void helper(String S,int index,int part,String cur,ArrayList<String> list){
        int n=S.length();
        if(index==S.length() && part==4){
            list.add(cur.substring(0,cur.length()-1));
            return;
        }
        if(index==S.length() || part==4){
            return;
        }

        for(int i=1;i<=3;i++){
            if(index+i <=n && valid(S.substring(index,index+i))){
                helper(S, index + i, part + 1, cur + S.substring(index, index + i) + ".", list);
            }
        }
    }
    static boolean valid(String cur) {
        if (cur.charAt(0) == '0' && cur.length()>1) return false;
        else {
            int t = Integer.parseInt(cur);
            return t <= 255;
        }
    }


}
