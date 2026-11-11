
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Option 1: Buy separately
        long option1 = (long) cost1 * need1 + (long) cost2 * need2;

        // Option 2: Buy only combined items
        long option2 = (long) costBoth * Math.max(need1, need2);

        // Option 3: Buy combined items for common requirements
        // and individual items for remaining requirements
        long common = Math.min(need1, need2);

        long option3 = (long) costBoth * common
                     + (long) cost1 * (need1 - common)
                     + (long) cost2 * (need2 - common);

        return Math.min(option1, Math.min(option2, option3));
    }
}
