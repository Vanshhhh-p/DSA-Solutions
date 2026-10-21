class Solution {
    static boolean isSafeToPlace (int rowIndex, int colIndex , int n , char[][] board) {
        // check left horizontal
        int row = rowIndex;
        int col = colIndex;

        while (col >= 0) {
            if (board[row][col] == 'Q') return false;

            // row index me koi change nahi krna h 
            // col index ki value zero tak travel kregi 
            col--;
        }

        // check left upper diagonal
        row = rowIndex;
        col = colIndex;

        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row -= 1;
            col -= 1;
        }

        // left lower diagonal
        row = rowIndex;
        col = colIndex;

        while (row < n && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row += 1;
            col -= 1;
        }
        return true;
    }
    static void solve (char[][] board , int n , int colIndex , List<List<String>> ans) {
        // base case
        if (colIndex >= n) {
            // iska matlb -> board pr merko ek valid arrangement milgyi h
            // iss valid arrangement ko ans me store 
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            ans.add(temp);
            return;
        }

        // current column k hr cell me jaake ya fir current column k hr row pr jaaker 
        // Queen place krunga nas rest recursion ko dedunga solve krne k liye
        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            if (isSafeToPlace(rowIndex , colIndex, n,board)) {
                // place queen 
                board[rowIndex][colIndex] = 'Q'; 
                // Recursion
                solve (board, n , colIndex +1, ans);
                // Backtraking
                board[rowIndex][colIndex] = '.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i],'.');
        }

        int colIndex = 0;
        List<List<String>> ans = new ArrayList<>();

        solve(board, n, colIndex, ans);

        return ans; 
    }
}