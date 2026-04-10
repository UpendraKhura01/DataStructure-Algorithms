package arrays.A_09_Sorted_Subsequence_0f_size_3;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {12, 11, 10, 5, 6, 2, 30};
        //int[] arr = {4, 3, 2, 1};
        ArrayList<Integer> ans = find3Numbers(arr);
        for (int i : ans) System.out.println(i);
    }
    static ArrayList<Integer> find3Numbers(int[] arr) {
        // code here
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        int[] smallest = new int[n];
        int[] biggest = new int[n];
        Arrays.fill(smallest, -1);
        Arrays.fill(biggest, -1);

        //finding biggest
        int maxIndex = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[maxIndex]) {
                maxIndex = i;
            } else {
                biggest[i] = maxIndex;
            }
        }
        //finding smallest
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] <= arr[minIndex]) {
                minIndex = i;
            } else {
                smallest[i] = minIndex;
            }
        }
        //finding triplets pair
        for (int i = 0; i < n; i++) {
            if (biggest[i] != -1 && smallest[i] != -1){
                ans.add(arr[smallest[i]]);
                ans.add(arr[i]);
                ans.add(arr[biggest[i]]);
                break;
            }
        }

        return ans;

    }
    static ArrayList<Integer> find3NumbersBetter(int[] arr) {
        int n = arr.length;

        int first = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        int potential_first = Integer.MAX_VALUE;
        for (int i = 0; i < n ; i++){
            if (arr[i] <= potential_first) potential_first = arr[i];
            else if(arr[i] < mid){
                first = potential_first;
                mid = arr[i];
            }
            else{
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(first);
                ans.add(mid);
                ans.add(arr[i]);
                return ans;
            }
        }
        return new ArrayList<Integer>();

    }
}
