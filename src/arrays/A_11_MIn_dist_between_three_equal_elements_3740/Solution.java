package arrays.A_11_MIn_dist_between_three_equal_elements_3740;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3};
        System.out.println(minimumDistanceBruteforce(nums));
    }
    static int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++){
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (int i : mp.keySet()){
            if (mp.get(i).size() >= 3){
                ArrayList<Integer> list = mp.get(i);

                for(int k = 2; k < list.size(); k++) {
                    int dist = 2 * (list.get(k) - list.get(k - 2));
                    ans = Math.min(ans, dist);
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
    static int minimumDistanceBruteforce(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++){
            for (int j = i + 1; j < n - 1; j++) {
                if(nums[i] != nums[j]) continue;
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] == nums[j] && nums[j] == nums[k]){
                        int dist = 2 * (k - i);
                        ans = Math.min(ans, dist);
                        break;
                    }
                }

            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}
