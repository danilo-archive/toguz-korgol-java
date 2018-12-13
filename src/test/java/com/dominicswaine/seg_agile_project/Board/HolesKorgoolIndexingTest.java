package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test to determine if an index is valid when adding Korgools
 * @author Danilo Del Busso
 * @version 11/12/2018
 */
@RunWith(Parameterized.class)
public class HolesKorgoolIndexingTest {

    ArrayList<HoleUI> holes = new GameWindow().getHolesBottomRow();

    /**
     * Function to return a Collection of indices
     * return an Collection of indices
     */
    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {-1, -1}, {100, 99}, {1, 0}, {10, 9}, {0, -1}
        });
    }

    private int input, output;

    /**
     * Construct a HolesKorgoolIndexingTest object
     */
    public HolesKorgoolIndexingTest(int input, int output){
        this.input = input;
        this.output = output;
    }

    /**
     * Test to determine if an index is valid when adding Korgools
     */
    @Test
    public void testIndexIsValidWhenAddingNKorgools(){
        holes.get(0).addKorgols(input);
        assertEquals(output, holes.get(0).getLastKorgolInd());
    }

}
