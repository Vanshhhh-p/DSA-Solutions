class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k |= nums[i];
                k |= i;
            }
        }

        return k == 0 ? 0 : findMaxK(nums);
    }

    private int findMaxK(int[] nums) {
        int k = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                k &= nums[i];
            }
        }

        return k;
    }
}