class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            int[] prev = dp.clone();
            int[] active = new int[budget + 1];

            Arrays.fill(active, NEG);

            for (int b = price; b <= budget; b++) {

                active[b] = prev[b - price] + 1 + free[i];
                if (b >= 2 * price && active[b - price] != NEG) {
                    active[b] = Math.max(
                        active[b],
                        active[b - price] + 1
                    );
                }
            }

            for (int b = 0; b <= budget; b++) {
                dp[b] = Math.max(prev[b], active[b]);
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b]);
        }

        return ans;
    }
}
