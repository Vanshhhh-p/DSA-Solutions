import java.util.Arrays;

class Solution {
    static int m, n;
    static int[][] t = new int[1001][1001];

    static int solve(String s1, String s2, int i, int j) {
        if (i >= m || j >= n) {
            return 0;
        }

        if (t[i][j] != -1) return t[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return t[i][j] = 1 + solve(s1, s2, i + 1, j + 1);
        }

        return t[i][j] = Math.max(
            solve(s1, s2, i + 1, j),
            solve(s1, s2, i, j + 1)
        );
    }

    public int longestCommonSubsequence(String text1, String text2) {
        m = text1.length();
        n = text2.length();

        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        return solve(text1, text2, 0, 0);
    }
}
