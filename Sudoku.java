public class Sudoku implements SudokuSolverInterface{
    private int[][] matrix;

    /* Constructor */
    public Sudoku(){
        matrix = new int[9][9];
    }

    /* Sets the value of the board */
    public void setValue(){

    }

    /* Get the value of the board */
    public int getValue(){

        return 0;
    }

    @Override
    public void setBoard(int[][] board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBoard'");
    }

    @Override
    public int[][] getBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
    }

    @Override
    public boolean solve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'solve'");
    }

    @Override
    public boolean legal(int row, int col, int nbr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'legal'");
    }

}
