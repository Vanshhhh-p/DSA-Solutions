
import java.util.*;

class Solution {

    static void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    static int solve(int[] nums, boolean reversed) {
        int n = nums.length;
        ArrayList<Integer> v = new ArrayList<>();

        // Double the array to check circular sequences
        for (int x : nums) {
            v.add(x);
        }
        for (int x : nums) {
            v.add(x);
        }

        int ans = Integer.MAX_VALUE;
        int count = 1;

        // Check increasing consecutive sequence
        for (int i = 1; i < v.size(); i++) {
            if (v.get(i) == v.get(i - 1) + 1) {
                count++;
            } else {
                count = 1;
            }

            if (count >= n) {
                int start = i - n + 1;
                int rotations = (n - start) % n;

                ans = Math.min(ans, rotations + (reversed ? 1 : 0));
            }
        }

        // Check decreasing consecutive sequence in original array
        if (!reversed) {
            count = 1;

            for (int i = 1; i < v.size(); i++) {
                if (v.get(i) == v.get(i - 1) - 1) {
                    count++;
                } else {
                    count = 1;
                }

                if (count >= n) {
                    int start = i - n + 1;
                    int rotations = (n - start) % n;

                    ans = Math.min(ans, rotations + 1);
                }
            }
        }

        return ans;
    }

    public int minOperations(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int ans = solve(nums, false);

        // Reverse the array and check again
        reverse(nums);

        int reversedAns = solve(nums, true);
        ans = Math.min(ans, reversedAns);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
