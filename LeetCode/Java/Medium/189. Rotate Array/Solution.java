class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        if (n == 0) return;

        k = k % n;

        while (k > 0) {
            int lastElement = nums[n - 1];

            for (int i = n - 1; i >= 1; i--) {
                nums[i] = nums[i - 1];
            }

            nums[0] = lastElement;
            k--;
        }
    }
}