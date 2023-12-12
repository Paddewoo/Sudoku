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
        board = null;
    }

    @Test
    void testConstructor(){
        for(int r = 0; r < board.length; r++){
            for(int c  = 0; c < board[0].length; c++){
                assertEquals(0, sudoku.get(r, c));
            }
        }
    }

    @Test
    void testSetBoard(){
        board[3][1] = 5;
        board[7][5] = 7;
        board[6][8] = 1;
        board[4][6] = 2;

        sudoku.setBoard(board);

        for(int r = 0; r < board.length; r++){
            for(int c  = 0; c < board[0].length; c++){
                assertEquals(board[r][c], sudoku.get(r, c));
            }
        }
    }

    @Test
    void testGetBoard(){
        board[3][1] = 5;
        board[7][5] = 7;
        board[0][1] = 3;
        board[7][8] = 1;

        sudoku.setBoard(board);

        for(int r = 0; r < board.length; r++){
            for(int c  = 0; c < board[0].length; c++){
                assertEquals(board[r][c], sudoku.get(r, c));
            }
        }
    }

    @Test
    void testSolve(){
        assertTrue(sudoku.solve()); // test empty

        sudoku.set(0, 0, 1); // solvable
        assertTrue(sudoku.solve());

        sudoku.set(0, 1, 1); // unsolvable
        assertFalse(sudoku.solve());
    }

    @Test
    void testFig1(){

        // board = {
        //     {0, 0, 8, 0, 0, 9, 0, 6, 2},
        //     {0, 0, 0, 0, 0, 0, 0, 0, 5},
        //     {1, 0, 2, 5, 0, 0, 0, 0, 0},

        // };

        board[0][2] = 8;
        board[0][5] = 9;
        board[0][7] = 6;
        board[0][8] = 2;

        board[1][8] = 5;

        board[2][0] = 1;
        board[2][2] = 2;
        board[2][3] = 5;

        board[3][3] = 2;
        board[3][4] = 1;
        board[3][7] = 9;

        board[4][1] = 5;
        board[4][6] = 6;

        board[5][0] = 6;
        board[5][7] = 2;
        board[5][8] = 8;

        board[6][0] = 4;
        board[6][1] = 1;
        board[6][3] = 6;
        board[6][5] = 8;

        board[7][0] = 8;
        board[7][1] = 6;
        board[7][4] = 3;
        board[7][6] = 1;

        board[8][6] = 4;

        sudoku.setBoard(board);

        assertTrue(sudoku.solve());
    }

    @Test
    void testIsLegal(){
        assertTrue(sudoku.isLegal(0, 0, 1));
        sudoku.set(0, 0, 1);
        
        assertFalse(sudoku.isLegal(0, 1, 1)); // same rad
        assertFalse(sudoku.isLegal(2, 0, 1)); // same col
        assertFalse(sudoku.isLegal(2, 2, 1)); // same box

        assertTrue(sudoku.solve());
    }

    @Test
    void testGet(){
        board[0][0] = 5;
        board[2][6] = 6;
        sudoku.setBoard(board);

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                assertEquals(board[r][c], sudoku.get(r, c));
            }
        }
    }

    @Test
    void testSet(){
        board[0][0] = 9;
        board[3][4] = 4;
        board[5][0] = 9;

        sudoku.setBoard(board);

        Sudoku sudoku2 = new Sudoku();

        sudoku2.set(0, 0, 9);
        sudoku2.set(3, 4, 4);
        sudoku2.set(5, 0, 9);

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                assertEquals(sudoku2.get(r, c), sudoku.get(r, c));
            }
        }
    }

    @Test
    void testClear(){
        sudoku.set(0, 0, 9);
        sudoku.set(3, 4, 4);
        sudoku.set(5, 0, 9);

        assertTrue(sudoku.get(0, 0) == 9);
        assertTrue(sudoku.get(3, 4) == 4);
        assertTrue(sudoku.get(5, 0) == 9);

        sudoku.clear();

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                assertEquals(sudoku.get(r, c), 0);
            }
        }

    }
    
}

