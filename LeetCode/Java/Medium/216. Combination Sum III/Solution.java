class Solution {
    static void solve(int[] candidates , int target , int index , List<List<Integer>> ans, List<Integer> output, int count, int k) {
        // base case
        if (count > k) return;
        if (count == k && target == 0) {
            // target 0 ka mltb meri output wali list me ek answer bn chuka h 
            // then store this ans into ans wali list
            ans.add(new ArrayList(output));
            return;
        }

        if (index >= candidates.length) return;
        if (target < 0) return;

        // include - exclude pattern
        // include 
        output.add(candidates[index]);
        solve(candidates, target - candidates[index] , index+1 , ans , output, count+1,k);
        // backtracking wala step
        output.remove(output.size() - 1);

        // To generate the unique combinations
        while (index + 1 < candidates.length && candidates[index] == candidates[index+1]) index++;

        // exclude
        solve(candidates , target , index + 1, ans , output, count,k);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int index = 0;
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        int target = n;
        int count = 0;
        solve(candidates , target , index , ans , output, count,k);
        return ans;
    }
}