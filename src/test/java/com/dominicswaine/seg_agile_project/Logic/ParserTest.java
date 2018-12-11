package com.dominicswaine.seg_agile_project.Logic;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;


public class ParserTest {

    @Test
    public void testSaveCustomGame1(){
        Parser.saveCustomGame("src/test/resources/test_files/sr.sav","4","2",
                    new int[]{0,9,9,9,9,9,9,9,9,9},new int[]{0,9,9,9,9,9,9,9,9,9});

        try {
            String content = FileUtils.readFileToString(new File("src/test/resources/test_files/sr.sav"));
            assertEquals(content,"4|2|[0, 9, 9, 9, 9, 9, 9, 9, 9, 9]|[0, 9, 9, 9, 9, 9, 9, 9, 9, 9]");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveCustomGame2(){
        Parser.saveCustomGame("src/test/resources/test_files/sr2.sav","1","2",
                new int[]{0,9,2,9,8,9,5,9,2,9},new int[]{0,18,9,4,0,9,9,6,9,9});

        try {
            String content = FileUtils.readFileToString(new File("src/test/resources/test_files/sr2.sav"));
            assertEquals(content,"1|2|[0, 9, 2, 9, 8, 9, 5, 9, 2, 9]|[0, 18, 9, 4, 0, 9, 9, 6, 9, 9]");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveCustomGame3(){
        Parser.saveCustomGame("src/test/resources/test_files/sr3.sav","0","4",
                new int[]{0,9,2,9,8,0,5,9,1,21},new int[]{0,18,9,4,0,9,8,7,12});

        try {
            String content = FileUtils.readFileToString(new File("src/test/resources/test_files/sr3.sav"));
            assertEquals(content,"0|4|[0, 9, 2, 9, 8, 0, 5, 9, 1, 21]|[0, 18, 9, 4, 0, 9, 8, 7, 12]");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
