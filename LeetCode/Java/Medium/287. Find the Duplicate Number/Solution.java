class Solution {
    public int findDuplicate(int[] nums) {
        HashMap<Integer , Integer> freq = new HashMap<>();

        // Frequency Store
        for (int num : nums) {
            freq.put(num , freq.getOrDefault(num , 0) + 1);
        }

        for (int i : nums) {
            if (freq.get(i) > 1) return i;
        }

        return -1;
    }
}