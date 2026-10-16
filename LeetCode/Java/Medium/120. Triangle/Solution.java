class Solution {
    static int solve(List<List<Integer>> triangle ,int rowIndex ,int colIndex) {
        // base case
        if (rowIndex == triangle.size() - 1) {
            // jis value pr khde ho us path me include kr dena dna wapis jao
            return triangle.get(rowIndex).get(colIndex);
        }

        // 1 case hum solve krenge baki sabh recursion sambhale lega
        int downAns = solve(triangle , rowIndex + 1 , colIndex);
        int diagonalAns = solve(triangle , rowIndex + 1, colIndex + 1);

        int finalAns = triangle.get(rowIndex).get(colIndex) + Math.min(downAns , diagonalAns);

        return finalAns;
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowIndex = 0;
        int colIndex = 0;
        int ans = solve(triangle , rowIndex, colIndex);
        return ans;
    }
}