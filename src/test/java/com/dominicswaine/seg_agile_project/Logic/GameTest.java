package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameTest {

    public GameTest(){}

    @Test
    public void checkDefaultGame(){
        Game game1 = new Game();
        assertEquals(18,game1.getGameBoard().getHoles().length);
        assertEquals(2,game1.getGameBoard().getKazans().length);
        assertEquals(Side.WHITE, game1.getPlayerSide());

        for(int i = 0; i < 18; i++){
            assertEquals(9,game1.getGameBoard().getHoleByIndex(i).getNumberOfKoorgools());
        }
    }

    @Test
    public void checkCustomGameNoTuzes(){
        int[] opponentData = {20,10,11,12,13,14,15,16,17,18};
        int[] playerData =  {10,1,2,3,4,5,6,7,8,9};
        Game game1 = new Game("0","0",playerData,opponentData);
        assertEquals(10,game1.getGameBoard().getKazanByIndex(0).getNumberOfKoorgools());
        assertEquals(20,game1.getGameBoard().getKazanByIndex(1).getNumberOfKoorgools());
        assertFalse(game1.getPlayerSide().hasTuz());
        assertFalse(Side.BLACK.hasTuz());
        for(int i = 0; i < 18; i++){
            assertEquals(i+1,game1.getGameBoard().getHoleByIndex(i).getNumberOfKoorgools());
        }
    }

    @Test
    public void checkCustomGameWithTuzes(){
        int[] opponentData = {20,10,11,12,13,14,15,16,17,18};
        int[] playerData =  {10,1,2,3,4,5,6,7,8,9};
        Game game1 = new Game("4","7",playerData,opponentData);
        assertEquals(true,game1.getPlayerSide().hasTuz());
        assertEquals(true,Side.BLACK.hasTuz());
        assertEquals(true,game1.getGameBoard().getHoleByIndex(12).isTuz());
        assertEquals(true,game1.getGameBoard().getHoleByIndex(6).isTuz());
        assertEquals(Side.WHITE,game1.getGameBoard().getHoleByIndex(12).getOwner());
        assertEquals(Side.BLACK,game1.getGameBoard().getHoleByIndex(6).getOwner());
    }





}
