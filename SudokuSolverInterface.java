public interface SudokuSolverInterface{
    
    // Work in progress

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved. 
     */
    void setBoard(int[][] board);
    
    /**
     * Get the sudoku board
     */
    int[][] getBoard();

    /**
     * Solve soduko
     * @return true if solution could be found
     */
    boolean solve();

    /**
     * Check if digit is legal on the current board
     * @param row
     * @param col
     * @param nbr
     * @return true if legal
     */
    boolean legal(int row, int col, int nbr);

    /**
     * Get number on board
     * @param row
     * @param col
     * @return number on board
     */
    int get(int row, int col);

    /**
     * Set number on board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param row
     * @param col
     * @param nbr
     */
    void set(int row, int col, int nbr);

    /**
     * Clear the board
     */
    void clear();

}