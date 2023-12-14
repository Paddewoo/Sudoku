package game;

public class Sudoku implements SudokuSolver{
    private int[][] board;

    /**
     * Constructs a new Sudoku game with an empty board.
     */
    public Sudoku(){
        board = new int[9][9];
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved. 
     * 
     * @param board a board to copy values from
     * @throws IllegalArgumentException if board is invalid, e.g. not 9x9
     */
    @Override
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Gets the current state of the Sudoku board.
     * 
     * @return a copy of the 9x9 array representing the Sudoku board
     */
    @Override
    public int[][] getBoard() {
        int[][] temp = new int[9][9];
        temp = this.board.clone();
        return temp;
    }

    /**
     * Attempts to solve the Sudoku puzzle.
     * 
     * @return true if a solution is found, false otherwise
     */
    @Override
    public boolean solve() {
        if (hasDuplicates()) {
            return false;
        }
        return solveRecursive(0, 0);

    }

    private boolean solveRecursive(int row, int col) {
        // If we have reached the end of the board, the puzzle is solved
        if (row == 9) {
            return true;
        }

        // Calculate the next row and column values
        int nextRow = 0;
        if (col == 8) {
            nextRow = row + 1;
        } else {
            nextRow = row;
        }
        int nextCol = (col + 1) % 9;

        // If the current cell is not empty, move to the next cell
        if (board[row][col] != 0) {
            return solveRecursive(nextRow, nextCol);
        }

        // Try filling the empty cell with numbers 1 to 9
        for (int nbr = 1; nbr <= 9; nbr++) {
            if (isLegal(row, col, nbr)) {
                // Try placing the number in the cell
                board[row][col] = nbr;

                // Recursively attempt to solve the remaining puzzle
                if (solveRecursive(nextRow, nextCol)) {
                    return true; // Puzzle is solved
                }

                // If the current placement does not lead to a solution, backtrack
                board[row][col] = 0;
            }
        }

        // No valid number was found for the current cell
        return false;
    }


    private int[] findEmptyCell() {
        // Find the first empty cell on the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // No empty cell found
    }

    /**
     * Checks if placing a digit is valid at the specified position on the current Sudoku board.
     * 
     * @param row the row index where the digit is to be placed
     * @param col the column index where the digit is to be placed
     * @param num the digit to be checked for validity (1-9)
     * @return true if placing the digit at the given position is legal, false otherwise
     */
    @Override
    public boolean isLegal(int row, int col, int num) {
        // Check if 'num' can be placed in the given cell
        return !usedInRow(row, num) && !usedInCol(col, num) && !usedInBox(row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int row, int num) {
        // Check if 'num' is already used in the row
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(int col, int num) {
        // Check if 'num' is already used in the column
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int startRow, int startCol, int num) {
        // Check if 'num' is already used in the 3x3 box
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[startRow + row][startCol + col] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Checks for duplicate values in rows, columns, and 3x3 grids of the Sudoku board.
     *
     * @return true if duplicates are found, indicating an invalid board state
     */
    private boolean hasDuplicates() {
        return hasDuplicatesInRows() || hasDuplicatesInColumns() || hasDuplicatesInGrids();
    }

    private boolean hasDuplicatesInRows() {
        for (int row = 0; row < 9; row++) {
            if (hasDuplicatesInArray(board[row])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicatesInColumns() {
        for (int col = 0; col < 9; col++) {
            int[] column = new int[9];
            for (int row = 0; row < 9; row++) {
                column[row] = board[row][col];
            }
            if (hasDuplicatesInArray(column)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicatesInGrids() {
        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                int[] grid = new int[9];
                int index = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        grid[index++] = board[startRow + row][startCol + col];
                    }
                }
                if (hasDuplicatesInArray(grid)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDuplicatesInArray(int[] array) {
        boolean[] seen = new boolean[10]; // Assuming numbers are 1-9
        for (int num : array) {
            if (num != 0) {
                if (seen[num]) {
                    return true; // Duplicate found
                } else {
                    seen[num] = true;
                }
            }
        }
        return false;
    }
  
    /**
     * Gets the number at the specified row and column on the Sudoku board.
     * 
     * @param row the row index
     * @param col the column index
     * @return the number at the specified position on the board
     */
    @Override
    public int get(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets a number on the Sudoku board with numbers 1-9 as fixed values, and 0 representing unsolved cells.
     * 
     * @param row the row index
     * @param col the column index
     * @param nbr the number to set at the specified position on the board
     */
    @Override
    public void set(int row, int col, int nbr) {
        board[row][col] = nbr;

    }

    /**
     * Clears the Sudoku board by setting all cells to 0.
     */
    @Override
    public void clear() {
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                board[r][c] = 0;

            }
        }
    }

}