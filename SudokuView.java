import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SudokuView {
    public SudokuView(Sudoku s, String title, int width, int height){
        SwingUtilities.invokeLater(() -> createWindow(s, title, width, height));
    }

    /**
     * Private helper method, to confine all Swing-related work to
     * Swing's Event Dispatch Thread (EDT).
     */
    private void createWindow(Sudoku s, String title, int width, int height){

    }




    JFrame frame = new JFrame();
}
