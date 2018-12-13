package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Board class tests
 * @author Ayberk Demirkol, Dominic Swaine
 * @version 12/12/2018
 */
public class BoardTest {
    
    /**
     * Construct a BoardTest object
     */
    public BoardTest(){}

    /**
     * Test to check the the white-side play goes first
     */
    @Test
    public void checkWhiteFirstToPlay(){
        Board board = new Board();
        assertEquals(Side.WHITE, board.getNextToPlay());
    }

    /**
     * Test to check the correct number of Holes and Kazans are created
     * and that each hole is assigned to the correct player
     */
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

    /**
     * Test to check whether availableMoves(...) returns the correct list of legal moves for a given player side
     */
    @Test
    public void checkAvailableMoves(){
        Game defaultGame = new Game();
        int[] opponentData = {0,0,0,1,2,3,4,0, 5,0};
        int[] playerData =  {0,1,2,0,3,0,4,0,0,0};
        Game customGameNoTuz = new Game("0","0",playerData,opponentData);
        Game customGameWithTuz = new Game("4","0",playerData,opponentData);
        assertEquals(9,defaultGame.getGameBoard().availableMoves(Side.WHITE).size());
        assertEquals(9,defaultGame.getGameBoard().availableMoves(Side.BLACK).size());
        assertEquals(4,customGameNoTuz.getGameBoard().availableMoves(Side.WHITE).size());
        assertEquals(5,customGameNoTuz.getGameBoard().availableMoves(Side.BLACK).size());
        assertEquals(5,customGameWithTuz.getGameBoard().availableMoves(Side.WHITE).size());
        assertEquals(4,customGameWithTuz.getGameBoard().availableMoves(Side.BLACK).size());
    }

    /**
     * Test to check whether redistribute(...) correctly distributes the Koorgols contained by a Hole when it is selected
     */
    @Test
    public void checkRedistribute(){
        Game defaultGame = new Game();
        defaultGame.getGameBoard().redistribute(4);
        assertEquals(1,defaultGame.getGameBoard().getHoleByIndex(4).getNumberOfKoorgools());
        for(int i = 5; i < 12;i++){
            assertEquals(10,defaultGame.getGameBoard().getHoleByIndex(i).getNumberOfKoorgools());
        }
        assertEquals(0,defaultGame.getGameBoard().getHoleByIndex(12).getNumberOfKoorgools());
    }

    /**
     * Test to check whether challengeMove(...) returns the most beneficial onward move
     */
    @Test
    public void checkChallengeMove(){
        Game defaultGame = new Game();
        defaultGame.getGameBoard().challengeMove();
        assertEquals(1,defaultGame.getGameBoard().getHoleByIndex(1).getNumberOfKoorgools());
        for(int i = 2; i < 9;i++){
            assertEquals(10,defaultGame.getGameBoard().getHoleByIndex(i).getNumberOfKoorgools());
        }
        assertEquals(0,defaultGame.getGameBoard().getHoleByIndex(9).getNumberOfKoorgools());
    }

    /**
     * Test to check whether getPlayerTuz(...) returns the index of the Tuz hole for a given player side
     */
    @Test
    public void checkGetPlayerTuz(){
        int[] player = {10,5,5,5,5,5,5,5,5,5};
        int[] opponent = {10,5,5,5,5,5,5,5,5,5};
        Game customGame = new Game("6","4",player,opponent);
        assertEquals(14, customGame.getGameBoard().getPlayerTuz(Side.WHITE));
        assertEquals(3, customGame.getGameBoard().getPlayerTuz(Side.BLACK));
    }

}


