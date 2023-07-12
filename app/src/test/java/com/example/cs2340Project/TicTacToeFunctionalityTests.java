package com.example.cs2340Project;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeFunctionalityTests {

    TicTacToeFunctionality game;
    TicTacToeComputerMovement comp;
    @Before
    public void initialize() {
        game = new TicTacToeFunctionality();
        comp = new TicTacToeComputerMovement(game.getPlayerPiece());
    }

    //1
    @Test
    public void testTicTacToeInitialization() {
        int[][] expected = new int[3][3];
        assertArrayEquals(game.getBoard(), expected);
    }

    //2
    @Test
    public void testSetPlayerPiece() {
        game.setPlayerPiece(1);
        assertEquals(game.getPlayerPiece(), 1);
        game.setPlayerPiece(2);
        assertEquals(game.getPlayerPiece(), 2);
    }

    //3
    @Test
    public void testGetBoard() {
        int[][] expected = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        assertArrayEquals(expected, game.getBoard());

        game.setPlayerPiece(1);
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(2, null);
        game.updateTurn();
        game.placePiece(3, null);
        game.updateTurn();
        expected = new int[][]{{1,1,1},{0,0,0},{0,0,0}};
        assertArrayEquals(expected, game.getBoard());

        game = new TicTacToeFunctionality();
        game.setPlayerPiece(2);
        game.updateTurn();
        game.placePiece(4, null);
        game.updateTurn();
        game.placePiece(5, null);
        game.updateTurn();
        game.placePiece(6, null);
        game.updateTurn();
        expected = new int[][]{{0,0,0},{2,2,2},{0,0,0}};
        assertArrayEquals(expected, game.getBoard());

        game = new TicTacToeFunctionality();
        game.setPlayerPiece(1);
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(5, null);
        game.updateTurn();
        game.placePiece(9, null);
        game.updateTurn();
        expected = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
        assertArrayEquals(expected, game.getBoard());
    }

    //4
    @Test
    public void testGetRow() {
        assertEquals(game.getRow(1), 0);
        assertEquals(game.getRow(2), 0);
        assertEquals(game.getRow(3), 0);
        assertEquals(game.getRow(4), 1);
        assertEquals(game.getRow(5), 1);
        assertEquals(game.getRow(6), 1);
        assertEquals(game.getRow(7), 2);
        assertEquals(game.getRow(8), 2);
        assertEquals(game.getRow(9), 2);
    }

    //5
    @Test
    public void testGetCol() {
        assertEquals(game.getCol(1), 0);
        assertEquals(game.getCol(4), 0);
        assertEquals(game.getCol(7), 0);
        assertEquals(game.getCol(2), 1);
        assertEquals(game.getCol(5), 1);
        assertEquals(game.getCol(8), 1);
        assertEquals(game.getCol(3), 2);
        assertEquals(game.getCol(6), 2);
        assertEquals(game.getCol(9), 2);
    }

    //6
    @Test
    public void testCanPlacePiece() {
        assertEquals(true, game.canPlacePiece(1,null));
        assertEquals(true, game.canPlacePiece(2,null));
        assertEquals(true, game.canPlacePiece(3,null));
        assertEquals(true, game.canPlacePiece(4,null));
        assertEquals(true, game.canPlacePiece(5,null));
        assertEquals(true, game.canPlacePiece(6,null));
        assertEquals(true, game.canPlacePiece(7,null));
        assertEquals(true, game.canPlacePiece(8,null));
        assertEquals(true, game.canPlacePiece(9,null));
    }

    //7
    @Test
    public void testPlacePiece() {
        game.setPlayerPiece(1);
        assertEquals(1, game.getWhosTurn());
        game.placePiece(1, null);
        assertEquals(1, game.getBoard()[0][0]);
        assertEquals(2, game.getWhosTurn());
    }

    //8
    @Test
    public void testUpdateTurn() {
        assertEquals(1, game.getWhosTurn());
        game.updateTurn();
        assertEquals(2, game.getWhosTurn());
        game.updateTurn();
        assertEquals(1, game.getWhosTurn());
    }

    //9
    @Test
    public void checkTestForWinner() {
        game.setPlayerPiece(1);
        assertEquals(0, game.checkForWinner());
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(2, null);
        game.updateTurn();
        game.placePiece(3, null);
        game.updateTurn();
        assertEquals(1, game.checkForWinner());
        game = new TicTacToeFunctionality();
        game.setPlayerPiece(2);
        assertEquals(0, game.checkForWinner());
        game.updateTurn();
        game.placePiece(4, null);
        game.updateTurn();
        game.placePiece(5, null);
        game.updateTurn();
        game.placePiece(6, null);
        game.updateTurn();
        assertEquals(2, game.checkForWinner());
        game = new TicTacToeFunctionality();
        game.setPlayerPiece(1);
        assertEquals(0, game.checkForWinner());
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(5, null);
        game.updateTurn();
        game.placePiece(9, null);
        game.updateTurn();
        assertEquals(1, game.checkForWinner());
    }

    //10
    @Test
    public void testGetPiece() {
        game.setPlayerPiece(1);
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(2, null);
        game.updateTurn();
        game.placePiece(3, null);
        assertEquals(1, game.getPiece(1));
        assertEquals(1, game.getPiece(2));
        assertEquals(1, game.getPiece(3));
        assertEquals(0, game.getPiece(4));
        assertEquals(0, game.getPiece(5));
        assertEquals(0, game.getPiece(6));
        assertEquals(0, game.getPiece(7));
        assertEquals(0, game.getPiece(8));
        assertEquals(0, game.getPiece(9));
    }

    //11
    @Test
    public void testCheckAlmostWin() {
        game.setPlayerPiece(1);
        game.placePiece(1, null);
        game.updateTurn();
        game.placePiece(3, null);
        assertEquals(2, comp.checkAlmostWin(game.getBoard(), 1));
    }
}
