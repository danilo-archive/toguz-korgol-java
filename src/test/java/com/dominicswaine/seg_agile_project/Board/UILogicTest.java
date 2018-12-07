package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;


import static org.junit.Assert.*;


public class UILogicTest {

    @Test
    public void rightNumberOfWhiteKorgoolAtStart(){
        GameWindow gw = new GameWindow();
        ArrayList<HoleUI> holes = gw.getHolesBottomRow();

       for(HoleUI h : holes){
        int whiteKorgools = 0;

        for(KorgoolUI k : h.getKorgols()){
            if(k.isWhite())
                ++whiteKorgools;
        }

        int ind;

        if(h.getLastKorgolInd() >= h.getMaxKorgolsPossible())
            ind = h.getMaxKorgolsPossible()-1;
        else if(h.getLastKorgolInd() != -1)
            ind = h.getLastKorgolInd();
        else
            ind = 0;
        assertEquals(h.getMaxKorgolsPossible(), ind+whiteKorgools);
       }
    }

    @Test
    public void rightNumberOfWhiteKorgoolWhenAdding(){
        GameWindow gw = new GameWindow();
        ArrayList<HoleUI> holes = gw.getHolesBottomRow();

        int[] korgoolsToAdd = {-2, -100, 300, 1 , 0 ,22, 100, -12,-4564}; //edge cases and some normal values


        for(int i : korgoolsToAdd){
            for(HoleUI h : holes){
                h.addKorgols(i);
                int whiteKorgools = 0;

                for(KorgoolUI k : h.getKorgols()){
                    if(k.isWhite())
                        ++whiteKorgools;
                }
                int visibleKorgools;

                if(h.getLastKorgolInd() >= h.getMaxKorgolsPossible())
                    visibleKorgools = h.getMaxKorgolsPossible();
                else if(h.getLastKorgolInd() == -1 || h.getLastKorgolInd() == 0)
                    visibleKorgools = 0;
                else
                    visibleKorgools = h.getLastKorgolInd()+1;

                assertEquals(h.getMaxKorgolsPossible(), visibleKorgools+whiteKorgools);

                h.removeKorgols(i);
                System.out.println(i);

                int ind = h.getLastKorgolInd();

                if(ind == 0 || ind == -1)
                    ind = 0;
                assertEquals(0,ind);

            }
        }
    }




}
