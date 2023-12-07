package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import game.Sudoku;

public class SudokuTester {
    Sudoku sudoku;
    int[][] board;

    @BeforeEach
    void setUp(){
        sudoku = new Sudoku();
        board = new int[9][9];
    }

    @AfterEach
    void tearDown(){
        sudoku.clear();
    }

    @Test
    void TestConstructor(){
        for(int r = 0; r < 9; r++){
            for(int c  = 0; c < 9; c++){
                assertEquals(0, sudoku.get(r, c));
            }
        }
    }

    @Test
    void TestsetBoard(){
        board[3][1] = 5;
        board[7][5] = 7;
        board[6][8] = 1;
        board[4][6] = 2;
        sudoku.setBoard(board);

        for(int r = 0; r < 9; r++){
            for(int c  = 0; c < 9; c++){
                assertEquals(board[r][c], sudoku.get(r, c));
            }
        }
    }

    @Test
    void TestgetBoard(){
        board[3][1] = 5;
        board[7][5] = 7;
        board[0][1] = 3;
        board[7][8] = 1;

        sudoku.setBoard(board);

        for(int r = 0; r < 9; r++){
            for(int c  = 0; c < 9; c++){
                assertEquals(board[r][c], sudoku.get(r, c));
            }
        }
    }


    
}

