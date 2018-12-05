package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardUITest {

    public BoardUITest(){}

    @Test
    public void checkWhiteFirstToPlay(){
        Board board = new Board();
        assertEquals(Side.WHITE, board.getNextToPlay());
    }

}


