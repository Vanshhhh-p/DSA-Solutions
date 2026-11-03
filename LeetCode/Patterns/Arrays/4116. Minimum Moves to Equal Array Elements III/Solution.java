class Solution {
    public int minMoves(int[] nums) {
        int maxElement = nums[0];
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxElement) {
                maxElement = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            count += maxElement - nums[i];
        }

        return count;
    }
}