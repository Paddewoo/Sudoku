package game;

import java.awt.*;
import javax.swing.*;

public class sudokuView {
    private Sudoku sudoku;
    private JTextField[][] FieldMatrix;
    JFrame frame;

    public sudokuView(Sudoku sudoku, String title, int width, int height) {
        this.sudoku = sudoku;
        FieldMatrix = new JTextField[9][9];
        SwingUtilities.invokeLater(() -> createBoard(title, width, height));
    }

    
    /** 
     * Creates a grafical user interface board with the specified title, width and height.
     * 
     * @param title of the JFrame
     * @param width of the JFrame and the graphical board
     * @param height of the JFrame and the graphical board
     */
    public void createBoard(String title, int width, int height) {

        // Create JFrame
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates panels with boxes and buttons
        JPanel boxPanel = createGridLayout();
        JPanel btnPanel = createButtons();

        // add panels to frame
        frame.add(boxPanel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
    }

    // skapar the 3x3 gridLayout
    private JPanel createGridLayout() {
        JPanel layout = new JPanel(new GridLayout(3, 3, 1, 1));
        int gridX = 0;
        int gridY = 0;

        for (int i = 0; i < 9; i++) {
            if (gridX == 3) {
                gridY++;
                gridX = 0;
            }
            JPanel gridPanel = createGrid(i % 2 == 1, gridX, gridY); // Alternate grid colors
            layout.add(gridPanel);
            gridX++;
        }

        return layout;
    }

    // skapar själva grid
    private JPanel createGrid(boolean setColour, int gridX, int gridY) {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        int gx = gridX * 3;
        int gy = gridY * 3;

        // Creates the boxes and adds them to a matrix
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                // Creates TextField
                JTextField textField = new JTextField();

                // Set Dimensions and colour
                textField.setPreferredSize(new Dimension(30, 30));
                textField.setHorizontalAlignment(JTextField.CENTER);
                if (setColour)
                    textField.setBackground(Color.LIGHT_GRAY);

                // Stuff;
                panel.add(textField);
                FieldMatrix[gx + x][gy + y] = textField;
            }
        }

        return panel;
    }

    /**
     * Creates buttons for solving and clearing the Sudoku board.
     *
     * @return The panel containing the buttons.
     */
	private JPanel createButtons() {
		JPanel panel = new JPanel();

		// Button Solve, add stuff
		JButton Solve = new JButton("Solve");
		Solve.addActionListener(e -> {
			if (singleDigitCheck()) {
				sudoku.setBoard(readBoard());
				if (sudoku.solve()) {
					int[][] temp = sudoku.getBoard();
					for (int r = 0; r < 9; r++) {
						for (int c = 0; c < 9; c++) {
							FieldMatrix[r][c].setText(Integer.toString(temp[r][c]));
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame, "The board cannot be solved");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Only enter numbers from 1-9");
			}

		});


        // Button Clear, add stuff
        JButton Clear = new JButton("Clear");
        Clear.addActionListener(e -> {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (!FieldMatrix[r][c].getText().equals("")) {
                        FieldMatrix[r][c].setText("");
                    }
                }
            }
            sudoku.clear();
        });

        panel.add(Solve);
        panel.add(Clear);

        return panel;

    }

    /**
     * Checks if the Sudoku board contains valid single-digit entries (1-9).
     *
     * @return True if all entries are valid single digits, false otherwise.
     */
	private boolean singleDigitCheck() {
		
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				String text = FieldMatrix[r][c].getText().trim();

	            // Check if the text is not empty
	            if (!text.isEmpty()) {
	                // Check if the text is a single digit from 1 to 9
	                try {
	                    int n = Integer.parseInt(text);
	                    if (n < 1 || n > 9) {
	                        return false;
	                    }
	                } catch (NumberFormatException e) {
	                    // If it's not a valid integer
	                    return false;
	                }
	            }
			}

		}

		return true;
	}


    private int[][] readBoard() {
        int[][] board = new int[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!FieldMatrix[r][c].getText().equals("")) {
                    board[r][c] = Integer.valueOf(FieldMatrix[r][c].getText());
                }
            }
        }

        return board;
    }
}
