class Solution {
    public int missingNumber(int[] nums) {
        int xorSum = 0;

        // xor with nums element
        for (int i : nums) {
            xorSum = xorSum ^ i;
        }

        // xor with range
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            xorSum = xorSum ^ i;
        }

        return xorSum;
    }
}