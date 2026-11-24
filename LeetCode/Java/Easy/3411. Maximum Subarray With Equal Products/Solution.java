class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();

            for (int j = i; j < n; j++) {
                int x = nums[j];
                ArrayList<Integer> factors = new ArrayList<>();

                // Find distinct prime factors
                for (int p = 2; p <= x / p; p++) {
                    if (x % p == 0) {
                        factors.add(p);

                        while (x % p == 0) {
                            x /= p;
                        }
                    }
                }

                if (x > 1) {
                    factors.add(x);
                }

                // Check for common prime factors
                boolean valid = true;

                for (int p : factors) {
                    if (set.contains(p)) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) {
                    // Any subarray of length 2 is valid
                    if (j == i + 1) {
                        ans = Math.max(ans, 2);
                    }
                    break;
                }

                set.addAll(factors);
                ans = Math.max(ans, j - i + 1);
            }
        }

        return ans;
    }
}
