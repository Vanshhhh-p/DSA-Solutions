
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // Initially, all transitions are invalid
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(right[i], -1);
            java.util.Arrays.fill(down[i], -1);
        }

        // Precompute mirror transitions
        // Process from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // Try moving right
                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        right[i][j] = i * n + (j + 1);
                    } else {
                        right[i][j] = down[i][j + 1];
                    }
                }

                // Try moving down
                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        down[i][j] = (i + 1) * n + j;
                    } else {
                        down[i][j] = right[i + 1][j];
                    }
                }
            }
        }

        // DP to count valid paths
        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                long ways = dp[i][j];

                // Move right
                int r = right[i][j];

                if (r != -1) {
                    int x = r / n;
                    int y = r % n;

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }

                // Move down
                int d = down[i][j];

                if (d != -1) {
                    int x = d / n;
                    int y = d % n;

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
