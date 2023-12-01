
import javax.swing.JFrame;

public class Sudoku implements SudokuSolverInterface{
    private int[][] board;

    /* Constructor */
    public Sudoku(){
        board = new int[9][9];
        
        for(int r = 0; r < 9; r++){         // startar med alla noll
            for(int c = 0; c < 9; c++){
                board[r][c] = 0;
            }
        }
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
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
        return board;
    }

    /**
     * Solve soduko
     * @return true if solution could be found
     */
    @Override
    public boolean solve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'solve'");
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

}
