package Two_Pointer_Technique.TP_03_Max_Sum_path_in_sorted_arrays;

/*
        Max Sum Path in Two Arrays
        Difficulty: Medium
        Given two sorted arrays of distinct integers in increasing order a[] and b[], which may have some common elements,
        find the maximum sum of a path from the beginning of any array to the end of any array. You may switch from one array to the other only at common elements.

        Note:  When switching, count the common element only once.

        Examples :

        Input: a[] = [2, 3, 7, 10, 12], b[] = [1, 5, 7, 8]
        Output: 35
        Explanation: The path will be (1 + 5 + 7 + 10 + 12) = 35, where 1 and 5 come from arr2 and then 7 is common so,
        we switch to arr1 and add 10 and 12.
        Input: a[] = [1, 2, 3], b[] = [3, 4, 5]
        Output: 15
        Explanation: The path will be (1 + 2 + 3 + 4 + 5) = 15.
        Constraints:
        1 ≤ a.size(), b.size() ≤ 10^4
        1 ≤ a[i], b[i] ≤ 10^S5
 */
public class Solution {
    public static void main(String[] args) {

    }
    int maxPathSum(int[] a, int[] b) {
        // code here
        int n = a.length;
        int m = b.length;
        int i = 0;
        int j = 0;
        int suma = 0;
        int sumb = 0;
        int sum = 0;

        while (i < n && j < m) {
            if (a[i] == b[j]) {
                sum += Math.max(suma + a[i], sumb + b[j]);
                suma = sumb = 0;
                j++;
                i++;

            }
            else if (a[i] > b[j]) {
                sumb += b[j];
                j++;
            }
            else {
                suma += a[i];
                i++;
            }
        }
        while (i < n) {
            suma += a[i];
            i++;
        }
        while (j < m) {
            sumb += b[j];
            j++;
        }
        sum += Math.max(suma, sumb);
        return sum;
    }
}
