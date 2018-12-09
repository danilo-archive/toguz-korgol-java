package com.dominicswaine.seg_agile_project.Logic;
import org.junit.Test;


public class ParserTest {

    @Test
    public void testTest(){
        Parser.saveCustomGame("sr.sav","1","1",
                new int[]{0,9,9,9,9,9,9,9,9,9},new int[]{0,9,9,9,9,9,9,9,9,9});
    }
}
