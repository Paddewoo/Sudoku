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
    void checkEmpty(){
        for(int r = 0; r < 9; r++){
            for(int c  = 0; c < 9; c++){
                //AssertTrue(0, sudoku.get(r, c));
            }
        }
    }
}

