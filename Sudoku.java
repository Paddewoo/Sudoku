
import javax.swing.JFrame;

public class Sudoku implements SudokuSolverInterface{
    private int[][] grid;

    /* Constructor */
    public Sudoku(){
        grid = new int[9][9];
        
        for(int r = 0; r < 9; r++){         // startar med alla noll
            for(int c = 0; c < 9; c++){
                grid[r][c] = 0;
            }
        }
    }

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
     */
    @Override
    public void setBoard(int[][] board) {
        grid = board;
    }

    /**
     * Get the sudoku board
     */
    @Override
    public int[][] getBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
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
            if(grid[row][c] == nbr){
                return false;
            } 
        }
        for(int r = 0; r < 9; r++){
            if(grid[r][col] == nbr){
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;
        for(int i = r; i < r + 3; i++){
            for(int j = c; j < c + 3; j++){
                if(grid[i][j] == nbr){
                    return false;
                }
            }
        }

        return true;
    }

}
