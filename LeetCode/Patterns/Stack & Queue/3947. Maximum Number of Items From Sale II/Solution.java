class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Count frequency of each factor
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate gain for each factor
        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            int count = 0;

            for (int j = f; j <= n; j += f) {
                count += freq[j];
            }

            gain[f] = count - 1;
        }

        // Step 3: Store useful boosted purchases
        List<int[]> list = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 &&
                price <= 2L * minPrice) {

                list.add(new int[]{price, gain[factor]});
            }
        }

        // Step 4: Sort by price
        list.sort((a, b) -> Integer.compare(a[0], b[0]));

        long ans = 0;
        long remaining = budget;

        // Step 5: Buy boosted copies
        for (int[] deal : list) {

            int price = deal[0];
            int count = deal[1];

            long buy = Math.min(count, remaining / price);

            ans += buy * 2;
            remaining -= buy * price;
        }

        // Step 6: Buy normal copies using remaining budget
        ans += remaining / minPrice;

        return (int) ans;
    }
}
