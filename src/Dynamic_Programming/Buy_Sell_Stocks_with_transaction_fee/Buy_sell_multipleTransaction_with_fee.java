package Dynamic_Programming.Buy_Sell_Stocks_with_transaction_fee;

import java.util.Arrays;

public class Buy_sell_multipleTransaction_with_fee {
    public static void main(String[] args) {
        int[] arr = {6, 1, 7, 2, 8, 4};
        int k = 2;
        System.out.println("ans is "+ maxProfit(arr, k));

    }
    static int maxProfit(int[] arr, int k) {
        // Code here
        int n = arr.length;
        int[][] dp = new int[n][2];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(arr, k, 0, 1, n, dp);

    }
    static int helper(int[] arr, int k, int idx, int canBuy, int n, int[][] dp){
        if(idx == n) return 0;

        if (dp[idx][canBuy] != -1) return dp[idx][canBuy];
        int pick;
        int not_pick;
        if (canBuy == 1){
            pick = -arr[idx] - k + helper(arr, k, idx + 1, 0, n, dp);
            not_pick = helper(arr, k, idx + 1, 1, n, dp);

        }
        else {
            pick = arr[idx] + helper(arr, k, idx + 1, 1, n, dp);
            not_pick = helper(arr, k, idx + 1, 0, n, dp);

        }
        dp[idx][canBuy] = Math.max(pick,not_pick);
        return dp[idx][canBuy];
    }

    //Using Tabulation
        static int Tabulation_maxProfit(int[] prices, int fee) {
            int cur = -prices[0]; // bought stock
            int profit = 0;          // no stock

            for (int i = 1; i < prices.length; i++) {

                profit = Math.max(profit, cur + prices[i] - fee); // sell
                cur = Math.max(cur, profit - prices[i]);       // buy
              }

            return profit;
        }

}
