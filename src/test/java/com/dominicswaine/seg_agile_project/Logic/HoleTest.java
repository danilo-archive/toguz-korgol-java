package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import com.dominicswaine.seg_agile_project.Board.HoleUI;

public class HoleTest {

    public HoleTest(){}

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

    @Test
    public void checkAddKorgool(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
            assertEquals(i, hole.getNumberOfKoorgools());
        }
        assertEquals(numberToAdd, hole.getNumberOfKoorgools());
    }

    @Test
    public void checkAddKorgools(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        ArrayList<Korgool> listOfKorgools = new ArrayList<>();
        for(int i = 0; i<numberToAdd; i++) listOfKorgools.add(new Korgool());
        hole.addKorgools(listOfKorgools);
        assertEquals(numberToAdd, hole.getNumberOfKoorgools());
    }

    @Test
    public void checkGetNumberOfKoorgools(){
        Hole hole = new Hole(1);
        hole.setGui(new HoleUI(2));
        final int numberToAdd = 20;
        for(int i = 1; i<=numberToAdd; i++){
            hole.addKorgool(new Korgool());
        }
        assertEquals(numberToAdd, hole.getKoorgools().size());
    }

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
