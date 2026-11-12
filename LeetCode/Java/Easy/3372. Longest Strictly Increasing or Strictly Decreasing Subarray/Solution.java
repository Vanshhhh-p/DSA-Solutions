class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int increasing = 1;
        int decreasing = 1;
        int longest = 1;

        for (int i = 1; i < nums.length; i++) {
                if (nums[i-1] < nums[i]) increasing++;
                else increasing = 1;

                if (nums[i-1] > nums[i]) decreasing++;
                else decreasing = 1;
                longest =  Math.max(increasing, decreasing);
        }
        return longest;
    }
}