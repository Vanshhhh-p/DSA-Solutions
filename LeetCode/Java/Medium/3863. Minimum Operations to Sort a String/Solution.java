
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;

        // No proper substring of length 2 exists
        if (n == 2) return -1;

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, s.charAt(i));
            max = (char) Math.max(max, s.charAt(i));
        }

        // One operation is enough
        if (first == min || last == max) {
            return 1;
        }

        // Three operations are needed when both endpoints
        // are the unique maximum and unique minimum
        boolean firstIsUniqueMax = true;
        boolean lastIsUniqueMin = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) >= first) {
                firstIsUniqueMax = false;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) <= last) {
                lastIsUniqueMin = false;
            }
        }

        if (firstIsUniqueMax && lastIsUniqueMin) {
            return 3;
        }

        return 2;
    }
}
