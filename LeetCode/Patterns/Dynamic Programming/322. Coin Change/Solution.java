class Solution {
    static int solve (int[] coins , int amount) {
        if (amount == 0) return 0;

        if (amount < 0) return Integer.MAX_VALUE;

        int mini = Integer.MAX_VALUE;

        for (int coin : coins) {
            int recursiveAns = solve(coins , amount - coin);
            if (recursiveAns != Integer.MAX_VALUE) {
                int totalCoinUsed = recursiveAns + 1;
                mini = Math.min(mini , totalCoinUsed);
            }
        }

        return mini;
    }
    public int coinChange(int coins[], int sum) {
        int ans = solve(coins , sum);
        if (ans == Integer.MAX_VALUE)
            return -1;
        else 
            return ans;
    }
}