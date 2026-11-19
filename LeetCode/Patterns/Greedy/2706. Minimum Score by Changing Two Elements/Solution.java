class Solution {
    public int minimizeSum(int[] nums) {

        int n = nums.length;

        if (n <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        // Case 1: Change two largest elements
        ans = Math.min(ans, nums[n - 3] - nums[0]);

        // Case 2: Change smallest and largest
        ans = Math.min(ans, nums[n - 2] - nums[1]);

        // Case 3: Change two smallest elements
        ans = Math.min(ans, nums[n - 1] - nums[2]);

        return ans;
    }
}
