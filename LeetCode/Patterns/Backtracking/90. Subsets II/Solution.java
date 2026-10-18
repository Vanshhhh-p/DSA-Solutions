class Solution {
    static void solve(int[] nums , int index , List<Integer> output , List<List<Integer>> ans) {
        // base case 
        if (index >= nums.length) {
            // subsequence ready hai -> ouput wali list m 
            // is subsequence ko store krna hai , ans me
            ans.add(new ArrayList<>(output));
            return;
        }   

        // include - exclude pattern 
        int currentValue = nums[index];
        // include 
        output.add(currentValue);
        solve(nums , index +1 , output, ans);
        // backtracking step
        output.remove(output.size() - 1);

        // while excluding, ignore the same elements an move on to the new elements
        // yeh exclude wala logic ek assumption pr based hai
        while (index + 1 < nums.length && nums[index] == nums[index +1]) index++;


        // exclude
        solve(nums , index+1 , output , ans);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        int index = 0;
        solve (nums, index , output, ans);
        return ans;
    }
}