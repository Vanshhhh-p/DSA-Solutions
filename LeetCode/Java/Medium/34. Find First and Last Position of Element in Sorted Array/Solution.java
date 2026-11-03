import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        List<Integer> list = new ArrayList<>(2);
        
        // Find the first occurrence and the last occurrence using binary search
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        
        list.add(first);
        list.add(last);
        
        // Convert the List to a primitive int[] array
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int bound = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                bound = mid; // Record the potential answer
                
                if (isFirst) {
                    // Keep looking to the left for an earlier occurrence
                    right = mid - 1;
                } else {
                    // Keep looking to the right for a later occurrence
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return bound;
    }
}
