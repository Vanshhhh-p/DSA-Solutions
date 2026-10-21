class Solution {
    static boolean findEmptyCell (char[][] board, int[] emptyCell) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // store empty cell ki row
                    emptyCell[0] = i;
                    // store empty cell ki col ka index
                    emptyCell[1] = j;
                    return true;
                }
            }
        }
        return false;
    }
    static boolean isSafeToPlace (char[][] board, char charValue , int rowIndex, int colIndex) {
        // rules :
        // check for horizontal or same row
        // row ka index sub cells k liye same rahega and column ka index vary karege from 0-9
        for (int col = 0; col < 9; col++) {
            if (board[rowIndex][col] == charValue) return false;
        }
        // check for vertical or same column
        // colIndex sab celll k liye same rahega , row ka index vary karega zero to 9
        for (int row = 0; row < 9; row++) {
            if (board[row][colIndex] == charValue) return false;
        }
        // check for current 3x3 sub box
        int startingRow = rowIndex - rowIndex % 3;
        int startingCol = colIndex - colIndex % 3;
        // travel over that 3x3 sub matrix
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                int actualRow = startingRow + i;
                int actualCol = startingCol + j;
                if (board[actualRow][actualCol] == charValue) return false;
            }
        }

        // iska mtlb safe to place h 
        return true;
    }
    static boolean solveSudokuHelper (char[][] board) {
        // base case
        // main tbh manuga ki meri puzzle solved hai , jab saare empty space fill hogye honge 
        // when there is no empty space inside the board , then the problem is solved 
        int[] emptyCell = new int[2];

        if (!findEmptyCell(board, emptyCell)) {
            return true;
        }

        // if i found an empty cell
        int rowIndex = emptyCell[0];
        int colIndex = emptyCell[1];

        for (int i = 1; i <= 9; i++) {
            char charValue = (char)(i + '0');
            if (isSafeToPlace(board, charValue , rowIndex , colIndex)) {
                // place kardo
                board[rowIndex][colIndex] = charValue;
                // recursive call
                if (solveSudokuHelper(board) == true) return true;

                // agar recursion solve nahi kr payega , or wapas ayega
                // current value ko undo karo or backtracking wala step karo
                board[rowIndex][colIndex] = '.';
            }
        }

        // no possible combination found
        return false;
    }
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
}