class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each element
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        HashSet<Integer> set = new HashSet<>();

        // Check whether frequencies are unique
        for (int freq : map.values()) {
            if (!set.add(freq)) {
                return false;
            }
        }

        return true;
    }
}