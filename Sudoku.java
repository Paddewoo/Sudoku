
public class Sudoku implements SudokuSolverInterface{
    private int[][] board;
        private int[][] temp;
    //private boolean[][] booleanBoard;

    /* Constructor */
    public Sudoku(){
        board = new int[9][9];
                temp = new int[9][9];

        //booleanBoard = new boolean[9][9];
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
     */
    @Override
    public void setBoard(int[][] board) {
        this.board = board;

        // for(int i = 0; i < 9; i++){
        //     for(int j = 0; j < 9; j++){
        //         if(board[i][j] != 0){
        //             booleanBoard[i][j] = true;
        //         }
        //     }
        // }
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
        if(solveRecursive(0, 0)){
            board = temp.clone();
            return true;
        }
        
        return false;

    }

    private boolean solveRecursive(int row, int col){

        // betyder alla i raden har fyllts i
        if(row == 9){ 
            return true;
        }

        //om alla i col 9 ifyllda, gå till nästa rad
        if(col == 9){
            return solveRecursive(row + 1, 0);
        }

        // ignorera celler som redan är ifyllda
        if(board[row][col] != 0){
            temp[row][col] = board[row][col];
            return solveRecursive(row, col + 1);
        }

        // försök lägga in ett tal 1 - 9
        for(int n = 1; n <= 9; n++){
            if(legal(row, col, n)){
                temp[row][col] = n;      // lägg in digit

                if(solveRecursive(row, col + 1)){
                    return true;
                }

                temp[row][col] = 0;
            }
        }
        
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
            if(temp[row][c] == nbr){
                return false;
            } 
        }
        for(int r = 0; r < 9; r++){ // checka tal för col
            if(temp[r][col] == nbr){
                return false;
            }
        }

        int r = row - row % 3; // för att hitta area av 3x3
        int c = col - col % 3;
        for(int i = r; i < r + 3; i++){
            for(int j = c; j < c + 3; j++){
                if(temp[i][j] == nbr){
                    return false;
                }
            }
        }
        return true;
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
        //booleanBoard[row][col] = (nbr != 0);
    }

    /**
     * Clear the board
     */
    @Override
    public void clear() {
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                board[r][c] = 0;
                //booleanBoard[r][c] = false;
            }
        }
    }

}
