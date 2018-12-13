package com.dominicswaine.seg_agile_project.Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import com.dominicswaine.seg_agile_project.Board.KazanUI;

/**
 * Kazan class tests
 * @author Ayberk Demirkol, Dominic Swaine
 * @version 11/12/2018
 */
public class KazanTest {

    public KazanTest(){}

    /**
     * Test to check whether Korgools are successfully added to the Kazan
     */
    @Test
    public void checkAddKorgool(){
        Kazan kazan = new Kazan(1);
        kazan.setGui(new KazanUI());
        final int numberToAdd = 82;
        for(int i = 1; i<=numberToAdd; i++){
            kazan.addKorgool(new Korgool());
            assertEquals(i, kazan.getKoorgools().size());
        }
        assertEquals(numberToAdd, kazan.getNumberOfKoorgools());
    }

    /**
     * Test to check whether an ArrayList of Korgools can be successfully added to the Kazan
     */
    @Test
    public void checkAddKorgools(){
        Kazan kazan = new Kazan(1);
        kazan.setGui(new KazanUI());
        final int numberToAdd = 82;
        ArrayList<Korgool> listOfKorgools = new ArrayList<>();
        for(int i = 0; i<numberToAdd; i++) listOfKorgools.add(new Korgool());
        kazan.addKorgools(listOfKorgools);
        assertEquals(numberToAdd, kazan.getKoorgools().size());
    }

    /**
     * Test to check whether getNumberOfKorgools(...) returns the number of Korgools contained by a Kazan
     */
    @Test
    public void checkGetNumberOfKoorgools(){
        Kazan kazan = new Kazan(1);
        kazan.setGui(new KazanUI());
        final int numberToAdd = 82;
        for(int i = 1; i<=numberToAdd; i++){
            kazan.addKorgool(new Korgool());
        }
        assertEquals(numberToAdd, kazan.getKoorgools().size());
    }

    /**
     * Test to check whether each Kazan is assigned to the correct player
     */
    @Test
    public void checkPlayerSideAssignment(){
        Kazan kazan_zero = new Kazan(0);
        assertEquals(Side.WHITE, kazan_zero.getOwner());
        Kazan kazan_one = new Kazan(1);
        assertEquals(Side.BLACK, kazan_one.getOwner());
    }

}
