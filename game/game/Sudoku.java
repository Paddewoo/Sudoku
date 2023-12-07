package game;

public class Sudoku implements SudokuSolver{
    private int[][] board;

    /* Constructor */
    public Sudoku(){
        board = new int[9][9];
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
     * @return 
     */
    @Override
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Get the sudoku board
     */
    @Override
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * Solve soduko
     * @return true if solution could be found
     */
    @Override
    public boolean solve() {
        if (hasDuplicates()) {
            return false;
        }
        return solveRecursive();

    }

    private boolean solveRecursive(){
        // Find the first empty cell on the board
        int[] emptyCell = findEmptyCell();

        // If there are no empty cells, the puzzle is solved
        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        // Try filling the empty cell with numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isLegal(row, col, num)) {
                // Try placing the number in the cell
                board[row][col] = num;

                // Recursively attempt to solve the remaining puzzle
                if (solveRecursive()) {
                    return true; // Puzzle is solved
                }

                // If the current placement does not lead to a solution, backtrack
                board[row][col] = 0;
            }
        }

        // No valid number can be placed in the current cell
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
     * Check if digit is legal on the current board
     * @param row
     * @param col
     * @param nbr
     * @return true if legal
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
     * Check for duplicates in rows, columns, and grids
     *
     * @return true if duplicates found
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
     * Get number on board
     * @param row
     * @param col
     * @return number on board
     */
    @Override
    public int get(int row, int col) {
        return board[row][col];
    }

    /**
     * Set number on board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param row
     * @param col
     * @param nbr
     */
    @Override
    public void set(int row, int col, int nbr) {
        board[row][col] = nbr;

    }

    /**
     * Clear the board
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