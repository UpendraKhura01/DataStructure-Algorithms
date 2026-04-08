package arrays.A_06_Segregate_0_1;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
        segregate0and1_swap(arr);

    }

    static void segregate0and1(int[] arr) {
        // code here
        int n = arr.length;
        int count_0 = 0;
        for (int i : arr) {
            if (i == 0) count_0++;
        }
        for (int i = 0; i < n; i++) {
            if (i < count_0) arr[i] = 0;
            else arr[i] = 1;
        }
    }

    static void segregate0and1_swap(int[] arr) {
        // code here
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        while (low < high){
            while (low < high && arr[low] == 0) low++;
            while (low < high && arr[high] == 1) high--;

            if (low < high) {
                arr[low++] = 0;
                arr[high--] = 1;
            }
        }
        for (int i : arr) System.out.println(i);

    }
}
