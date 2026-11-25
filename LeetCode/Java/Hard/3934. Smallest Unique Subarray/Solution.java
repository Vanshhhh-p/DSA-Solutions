class Solution {
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;
    static final long BASE = 911382323L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long pow1 = 1, pow2 = 1;

        // BASE^n
        for (int i = 0; i < n; i++) {
            pow1 = (pow1 * BASE) % MOD1;
            pow2 = (pow2 * BASE) % MOD2;
        }

        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(nums, mid, pow1, pow2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean check(int[] nums, int len,
                          long pow1, long pow2) {

        long p1 = 1, p2 = 1;

        // Calculate BASE^len
        for (int i = 0; i < len; i++) {
            p1 = (p1 * BASE) % MOD1;
            p2 = (p2 * BASE) % MOD2;
        }

        HashMap<Long, Integer> map = new HashMap<>();

        long h1 = 0, h2 = 0;

        for (int i = 0; i < nums.length; i++) {

            h1 = (h1 * BASE + nums[i]) % MOD1;
            h2 = (h2 * BASE + nums[i]) % MOD2;

            // Remove element outside the window
            if (i >= len) {
                h1 = (h1 - nums[i - len] * p1 % MOD1 + MOD1) % MOD1;
                h2 = (h2 - nums[i - len] * p2 % MOD2 + MOD2) % MOD2;
            }

            if (i >= len - 1) {
                long key = (h1 << 32) | h2;

                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        // Is any subarray unique?
        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
