class Solution {
    static void solve (int[] coins , int amount, int index , List<List<Integer>> ans ,List<Integer> output) {
        // base case
               if (amount == 0) {
                   // target 0 ka mltb meri output wali list me ek answer bn chuka h 
                   // then store this ans into ans wali list
                   ans.add(new ArrayList(output));
                   return;
               }

               if (index >= coins.length || amount < 0) return;

               // include - exclude pattern
               // include 
               output.add(coins[index]);
               solve(coins, amount - coins[index] , index , ans , output);
               // backtracking wala step
               output.remove(output.size() - 1);
               // exclude
               solve(coins , amount , index + 1, ans , output);
    }
    public int change(int amount ,int coins[]) {
        Arrays.sort(coins);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int index = 0;

        solve (coins , amount , index ,ans, output);
        return ans.size();
    }
}