class Solution {
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int[] output = new int[nums.length];

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = count[nums[i]] - 1;
            output[idx] = nums[i];
            count[nums[i]]--;

        }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = output[i];
            }
    }
}