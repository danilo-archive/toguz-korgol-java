package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    public BoardTest(){}

    @Test
    public void checkWhiteFirstToPlay(){
        Board board = new Board();
        assertEquals(Side.WHITE, board.getNextToPlay());
    }

    @Test
    public void checkSetUp(){
        Board board = new Board();
        assertEquals(18,board.getHoles().length);
        assertEquals(2,board.getKazans().length);
        assertEquals(Side.WHITE,board.getKazanByIndex(0).getOwner());
        assertEquals(Side.BLACK,board.getKazanByIndex(1).getOwner());
        for(int i = 0; i < 9; i++){
            assertEquals(Side.WHITE,board.getHoleByIndex(i).getOwner());
        }
        for(int i = 9; i < 18; i++){
            assertEquals(Side.BLACK,board.getHoleByIndex(i).getOwner());
        }
    }

    @Test
    public void checkAvailableMoves(){
        Game game = new Game();
        assertEquals(9,game.getGameBoard().availableMoves(Side.WHITE).size());
        assertEquals(9,game.getGameBoard().availableMoves(Side.BLACK).size());
    }

}


