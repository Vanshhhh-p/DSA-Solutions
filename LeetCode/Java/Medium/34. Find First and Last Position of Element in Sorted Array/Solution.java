class Solution {
    public int[] searchRange(int[] nums, int target) {
        List<Integer> list = new ArrayList<>(2);
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (list.isEmpty()) {
                    list.add(i);
                    list.add(i);
                }
                else 
                    list.set(1, i);
            }
        }

        if (list.isEmpty()) {
            list.add(-1);
            list.add(-1);
        }
        // Integer[] result = list.toArray(new Integer[0]);
        return list.stream().mapToInt(i -> i).toArray();
    }
}