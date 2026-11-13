class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 1;

        while ((1L << LOG) <= n) {
            LOG++;
        }

        // Find next greater element
        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? n : stack[top];
            stack[++top] = i;
        }

        // Binary lifting tables
        int[][] jump = new int[LOG][n + 1];
        long[][] sum = new long[LOG][n + 1];

        // IMPORTANT: Initialize sentinel positions
        for (int j = 0; j < LOG; j++) {
            jump[j][n] = n;
        }

        for (int i = 0; i < n; i++) {
            jump[0][i] = next[i];
            sum[0][i] = (long) nums[i] * (next[i] - i);
        }

        // Build binary lifting
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = jump[j - 1][i];

                jump[j][i] = jump[j - 1][mid];

                sum[j][i] = sum[j - 1][i]
                          + sum[j - 1][mid];
            }
        }

        // Prefix sum
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long ans = 0;

        // Binary search for each ending index
        for (int r = 0; r < n; r++) {
            int low = 0;
            int high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                long required = getCost(
                    mid, r, nums, jump, sum
                ) - (prefix[r + 1] - prefix[mid]);

                if (required <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += r - low + 1;
        }

        return ans;
    }

    private long getCost(
        int l, int r,
        int[] nums,
        int[][] jump,
        long[][] sum
    ) {
        int LOG = jump.length;
        int cur = l;
        long total = 0;

        for (int j = LOG - 1; j >= 0; j--) {
            int next = jump[j][cur];

            if (next <= r) {
                total += sum[j][cur];
                cur = next;
            }
        }

        total += (long) nums[cur] * (r - cur + 1);

        return total;
    }
}
