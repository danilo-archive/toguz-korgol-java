package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HolesKorgoolIndexingTest {
    ArrayList<HoleUI> holes = new GameWindow().getHolesBottomRow();


    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {-1, -1}, {100, 99}, {1, 0}, {10, 9}, {0, -1}
        });
    }

    private int input, output;

    public HolesKorgoolIndexingTest(int input, int output){
        this.input = input;
        this.output = output;
    }

    @Test
    public void testIndexIsValidWhenAddingNKorgools(){
        holes.get(0).addKorgols(input);
        assertEquals(output, holes.get(0).getLastKorgolInd());
    }


}
