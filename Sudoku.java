
public class Sudoku implements SudokuSolverInterface{
    private int[][] board;
    private boolean[][] booleanBoard;

    /* Constructor */
    public Sudoku(){
        board = new int[9][9];
        booleanBoard = new boolean[9][9];
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
     */
    @Override
    public void setBoard(int[][] board) {
        this.board = board;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != 0){
                    booleanBoard[i][j] = true;
                }
            }
        }
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

        return solveRecursive(1, 1);
    }

    private boolean solveRecursive(int r, int c){
        return false;
    }

    /**
     * Check if digit is legal on the current board
     * @param row
     * @param col
     * @param nbr
     * @return true if legal
     */
    @Override
    public boolean legal(int row, int col, int nbr) {

        for(int c = 0; c < 9; c++){ // checka tal för row
            if(board[row][c] == nbr){
                return false;
            } 
        }
        for(int r = 0; r < 9; r++){ // checka tal för col
            if(board[r][col] == nbr){
                return false;
            }
        }

        int r = row - row % 3; // för att hitta area av 3x3
        int c = col - col % 3;
        for(int i = r; i < r + 3; i++){
            for(int j = c; j < c + 3; j++){
                if(board[i][j] == nbr){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int get(int row, int col) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void set(int row, int col, int nbr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

}
