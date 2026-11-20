class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            // Increase x to x + 1
            dp.put(x + 1,
                Math.max(dp.getOrDefault(x + 1, 0),
                         dp.getOrDefault(x, 0) + 1));

            // Keep x unchanged
            dp.put(x,
                Math.max(dp.getOrDefault(x, 0),
                         dp.getOrDefault(x - 1, 0) + 1));

            ans = Math.max(ans, dp.get(x));
            ans = Math.max(ans, dp.get(x + 1));
        }

        return ans;
    }
}
