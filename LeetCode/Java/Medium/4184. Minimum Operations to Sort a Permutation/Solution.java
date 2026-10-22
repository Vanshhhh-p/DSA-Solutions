
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Case 1: nums is a rotation of increasing order
        int start = nums[0];
        boolean increasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (start + i) % n) {
                increasing = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            // Rotate left directly, or use two reversals
            ans = Math.min((n - start) % n, start + 2);
        }

        // Case 2: nums is a rotation of decreasing order
        int shift = n - 1 - nums[0];
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != n - 1 - ((i + shift) % n)) {
                decreasing = false;
                break;
            }
        }

        if (decreasing) {
            // Reverse and rotate, choosing the cheaper direction
            int rotations = (n - shift) % n;
            ans = Math.min(ans, 1 + Math.min(shift, rotations));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
