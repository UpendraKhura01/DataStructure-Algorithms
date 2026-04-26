package Stack.Opposite_Sign_Pair_Reduction;

import java.util.ArrayList;
import java.util.Stack;
/*
    Opposite Sign Pair Reduction
    Difficulty: Medium
    Given an array arr[] , return the final array by repeatedly apply the following operation from left to right until no
    more valid operations can be performed.

    If two adjacent elements have opposite signs:

    If their absolute values are different, remove both elements and insert the one with the greater absolute value, preserving its sign.
    If their absolute values are equal, remove both elements without inserting any new element.
    Examples :

    Input: arr[] = [10, -5, -8, 2, -5]
    Output: [10]
    Explanation:
    At Index 0 : Element 10 has positive sign.
    At Index 1 : -5  has lesser absolute value than 10. Replace both of them with 10.
    At Index 2 : -8  has lesser absolute value than 10. Replace both of them with 10.
    At Index 3 : 2 has positive sign. So it will be in the array.
    At Index 4 : -5  has greater absolute value than 2. Replace both of them with 5.
    Now -5  has lesser absolute value than 10. Replace both of them with 10.


    Input: arr[] = [5, -5, -2, -10]
    Output: [-2, -10]
    Explanation: 1st and 2nd element gets discarded because both elements have same values but opposite sign. 3rd and 4th
    elements have same sign. So, both will be in the array.
    Input: arr[] = [-20, 1, 20]
    Output: []
    Explanation: 1st and 2nd elements are removed, and -20 is inserted since it has the larger absolute value. Then, the
     remaining elements [-20, 20] are removed (equal absolute values, opposite signs).

    Constraints:
    1 ≤ arr.size() ≤ 105
    -10000 ≤ arr[i] ≤ 10000
    arr[i] != 0
 */

public class Solution {
    public static void main(String[] args) {
        int[] arr = {10, -5, -8, 2, -5};
        ArrayList<Integer> ans = reducePairs(arr);
        for(int i : ans){
            System.out.println(i);
        }

    }
    static ArrayList<Integer> reducePairs(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int ele1 = arr[i];
            boolean flag = true;
            while (!stack.isEmpty() && !samesign(ele1, stack.peek())) {
                int top = stack.peek();

                if (Math.abs(top) == Math.abs(ele1)) {
                    stack.pop();
                    flag = false;
                    break;
                } else if (Math.abs(top) > Math.abs(ele1)) {
                    flag = false;
                    break;
                } else {
                    stack.pop();
                }
            }
            if (flag) {
                stack.push(ele1);
            }

        }
        return new ArrayList<>(stack);

    }
    static boolean samesign(int a, int b){
        return a >= 0 && b >= 0 || a < 0 && b < 0;
    }
}
