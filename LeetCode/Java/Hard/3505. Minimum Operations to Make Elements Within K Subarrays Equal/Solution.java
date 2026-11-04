import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new long[n + 1];
            sum = new long[n + 1];
        }

        void update(int i, int c, long val) {
            for (; i <= n; i += i & -i) {
                count[i] += c;
                sum[i] += val;
            }
        }

        long queryCount(int i) {
            long ans = 0;
            for (; i > 0; i -= i & -i) {
                ans += count[i];
            }
            return ans;
        }

        long querySum(int i) {
            long ans = 0;
            for (; i > 0; i -= i & -i) {
                ans += sum[i];
            }
            return ans;
        }

        int kth(int k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= (int) count[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        Fenwick ft = new Fenwick(m);

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
        }

        long[] cost = new long[n - x + 1];

        long totalSum = 0;

        // First window
        for (int i = 0; i < x; i++) {
            ft.update(id[i], 1, nums[i]);
            totalSum += nums[i];
        }

        // Calculate cost of every window
        for (int i = 0; i <= n - x; i++) {

            int medianIndex = ft.kth((x + 1) / 2);
            long median = sorted[medianIndex - 1];

            long leftCount = ft.queryCount(medianIndex);
            long leftSum = ft.querySum(medianIndex);

            long rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            cost[i] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (i < n - x) {
                ft.update(id[i], -1, -nums[i]);
                ft.update(id[i + x], 1, nums[i + x]);

                totalSum += (long) nums[i + x] - nums[i];
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Skip current element
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}