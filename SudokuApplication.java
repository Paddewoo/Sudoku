
public class SudokuApplication {
    public static void main(String[] args) {
        int[][] board = new int[9][9]; 
        Sudoku s = new Sudoku();
        SudokuView view = new SudokuView(s, "Sudoku", 500, 500);
        //s.setBoard(board);
        
    }
}
