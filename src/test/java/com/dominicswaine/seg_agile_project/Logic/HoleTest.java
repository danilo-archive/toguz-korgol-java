package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import com.dominicswaine.seg_agile_project.Board.HoleUI;

/**
 * Hole class tests
 * @author Dominic Swaine
 * @version 11/12/2018
 */
public class HoleTest {

    public HoleTest(){}

    /**
     * Test to check whether each Hole is assigned to the correct player
     */
    @Test
    public void checkPlayerSideAssignment(){
        for(int i = 0; i<9; i++){
            Hole hole = new Hole(i);
            assertEquals(Side.WHITE, hole.getOriginalOwner());
        }
        for(int i = 9; i<18; i++){
            Hole hole = new Hole(i);
            assertEquals(Side.BLACK, hole.getOriginalOwner());
        }
    }

    /**
     * Test to check whether Korgools are successfully added to the Hole
     */
    @Test
    public void checkAddKorgool(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
            assertEquals(i, hole.getNumberOfKoorgools());
        }
        assertEquals(numberToAdd, hole.getKoorgools().size());
    }

    /**
     * Test to check whether an ArrayList of Korgools can be successfully added to the Hole
     */
    @Test
    public void checkAddKorgools(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        ArrayList<Korgool> listOfKorgools = new ArrayList<>();
        for(int i = 0; i<numberToAdd; i++) listOfKorgools.add(new Korgool());
        hole.addKorgools(listOfKorgools);
        assertEquals(numberToAdd, hole.getKoorgools().size());
    }

    /**
     * Test to check whether getNumberOfKorgools(...) returns the number of Korgools contained by a Hole
     */
    @Test
    public void checkGetNumberOfKoorgools(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
        }
        assertEquals(numberToAdd, hole.getNumberOfKoorgools());
    }

    /**
     * Test to check each Hole is originally not a Tuz, but can be made into a Tuz
     */
    @Test
    public void checkTuz(){
        for(int i = 0; i<9; i++){
            Hole hole = new Hole(i);
            hole.setGui(new HoleUI(i+1));
            assertEquals(Side.WHITE, hole.getOwner());
            hole.markAsTuz();
            assertEquals(Side.BLACK, hole.getOwner());
        }
        for(int i = 9; i<18; i++){
            Hole hole = new Hole(i);
            hole.setGui(new HoleUI(i-8));
            assertEquals(Side.BLACK, hole.getOwner());
            hole.markAsTuz();
            assertEquals(Side.WHITE, hole.getOwner());
        }
    }

    /**
     * Test to check whether emptyHole(...) removes all the Korgools contained by a hole
     */
    @Test
    public void checkEmptyHole(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
        }
        hole.emptyHole();
        assertEquals(0, hole.getKoorgools().size());
    }

    /**
     * Test to check whether emptyBarOne(...) removes all bar one Korgools contained by a hole
     */
    @Test
    public void checkEmptyBarOne(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
        }
        hole.emptyBarOne();
        assertEquals(1, hole.getKoorgools().size());
    }

}
