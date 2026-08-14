package arrays.A_39_Subset_Sum_on_Generated_Sequence;

/*
        Subset Sum on Generated Sequence

        Difficulty: Medium
        There are n children standing in a queue, each assigned a number arr[i].
        The teacher writes s on a paper and gives it to the first child.

        Each child writes the sum of all numbers already on the paper and arr[i], then passes it to the next child.

        Return true if x can be formed by adding some of the numbers written on the paper: else return false.

        Input: arr[] = [1, 2, 4, 2], s = 1, x = 7
        Output: true
        Explanation: The first student gets 1 on paper and writes 2. The second student gets [1, 2] and writes 5.
        The third student gets [1, 2, 5] and writes 12. The final sequence of numbers on the paper is 1, 2, 5, 12, 22.
        Using 2 and 5 we can form 7.

        Input: arr[] = [51, 88], s = 100, x = 500
        Output: false
        Explanation: The final sequence of numbers on the paper is 100, 151, 339. Using these numbers we cannot form 500.
        Constraints:

        1 ≤ arr.size() ≤ 10^5
        1 ≤ arr[i] ≤ 10^9
        1 ≤ s ≤ 10^9
        0 ≤ x ≤ 10^9

 */
public class Solution {
    public static void main(String[] args) {

    }
    boolean isPossible(int[] arr, int s, int x) {
        // code here
        if(x == 0) return true;
        int n = arr.length;
        long[] seq = new long[n + 1];

        seq[0] = s;
        long prevsum = s;
        for(int i = 0; i < n; i++){
            long cur = prevsum + arr[i];
            seq[i + 1] = cur;
            prevsum += cur;

        }

        long remain = x;
        for(int i = n; i >= 0; i--){
            if(seq[i] > remain){
                continue;
            }
            remain -= seq[i];
            if(remain == 0){
                return true;
            }
        }
        return false;
    }
}
