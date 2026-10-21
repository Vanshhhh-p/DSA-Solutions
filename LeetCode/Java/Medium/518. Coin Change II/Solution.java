class Solution {
    static int solve (int amount, int[] coins, int index) {
        // base case
        if (amount == 0) {
            return 1;
        }

        if (amount < 0 ) return 0;
        if (index >= coins.length) return 0;

        // include - exclude pattern 
        // include 
        // if coin value -> 5 , and amount value -> 40
        // if i include coin 5
        // remaining amount -> 35
        int includeAns = solve (amount - coins[index] , coins , index);

        // if i not include 5 than amoount will not change and the index must incerement
        int excludeAns = solve (amount, coins, index+1);

        int finalAns = includeAns + excludeAns;
        return finalAns;
    }
    public int change(int amount ,int coins[]) {
        int index = 0;
        int ans = solve (amount , coins , index);
        return ans;
    }
}