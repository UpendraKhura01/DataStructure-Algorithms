package arrays.A_07_Intersection_of_two_sorted_arr;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 2, 4, 4};
        int[] b = {2, 4};
        ArrayList<Integer> arr = intersection(a, b);
        for (int i : arr){
            System.out.println(i);
        }

    }
    static ArrayList<Integer> intersection(int[] a, int[] b) {
        // code here
        int n = a.length;
        int m = b.length;
        ArrayList<Integer> arr = new ArrayList<>();

        int first = 0, second = 0;
        while (first < n && second < m){

            if (a[first] == b[second]){
                if (arr.isEmpty() || arr.get(arr.size() - 1) != a[first]) {
                    arr.add(a[first]);
                }
                first++;
                second++;
            } else if (a[first] < b[second]) {
                first++;
            }
            else second++;
        }

        return arr;

    }
}
