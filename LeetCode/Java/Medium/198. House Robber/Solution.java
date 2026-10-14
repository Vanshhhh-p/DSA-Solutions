class Solution {

    static int solve(int[] arr, int index, int[] dp) {

        // Base case
        if (index >= arr.length) {
            return 0;
        }

        // Already calculated
        if (dp[index] != -1) {
            return dp[index];
        }

        // Include current house
        int includeAns = arr[index] + solve(arr, index + 2, dp);

        // Exclude current house
        int excludeAns = solve(arr, index + 1, dp);

        // Store answer
        dp[index] = Math.max(includeAns, excludeAns);

        return dp[index];
    }

    public int rob(int arr[]) {

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);

        return solve(arr, 0, dp);
    }
}