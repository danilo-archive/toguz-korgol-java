package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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


}
