package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import java.util.ArrayList;


import static org.junit.Assert.*;

/**
 * This class testes the logic behind indexing of korgools in the ui
 * and if the game closes when the game is wonor lost
 *
 * @author Danilo Del Busso
 * @version 12-12-2018
 */
public class UILogicTest {

    /**
     * Test if the gamewindow has the right number
     * of korgools when started
     */
    @Test
    public void rightNumberOfWhiteKorgoolAtStart() {
        GameWindow gw = new GameWindow();
        ArrayList < HoleUI > holes = gw.getHolesBottomRow();

        for (HoleUI h: holes) {
            int whiteKorgools = 0;

            for (KorgoolUI k: h.getKorgols()) {
                if (k.isWhite())
                    ++whiteKorgools;
            }

            int ind;

            if (h.getLastKorgolInd() >= h.getMaxKorgolsPossible())
                ind = h.getMaxKorgolsPossible() - 1;
            else if (h.getLastKorgolInd() != -1)
                ind = h.getLastKorgolInd();
            else
                ind = 0;
            assertEquals(h.getMaxKorgolsPossible(), ind + whiteKorgools);
        }
    }


    /**
     * Testing if the right number of korgools are flagged and shown
     * as non visible (white) when adding korgools to holes
     */
    @Test
    public void rightNumberOfWhiteKorgoolWhenAdding() {
        GameWindow gw = new GameWindow();
        ArrayList < HoleUI > holes = gw.getHolesBottomRow();

        int[] korgoolsToAdd = {
                -2,
                -100,
                300,
                -1,
                1,
                0,
                22,
                100,
                -12,
                -4564
        }; //edge cases and some normal values


        for (int i: korgoolsToAdd) {
            for (HoleUI h: holes) {

                int originalWhiteKorgools = 0;

                for (KorgoolUI k: h.getKorgols()) {
                    if (k.isWhite())
                        originalWhiteKorgools++;
                }
                h.addKorgols(i);

                int whiteKorgools = 0;

                for (KorgoolUI k: h.getKorgols()) {
                    if (k.isWhite())
                        whiteKorgools++;
                }
                if (i <= 0) {
                    assertEquals(originalWhiteKorgools, whiteKorgools);
                } else if (i + h.getMaxKorgolsPossible() - originalWhiteKorgools < h.getMaxKorgolsPossible()) {
                    assertEquals(originalWhiteKorgools - i, whiteKorgools);
                } else {
                    assertEquals(0, whiteKorgools);
                }

            }

        }
    }


    /**
     * Testing if the right number of korgools are flagged and shown
     * as non visible (white) when removing korgools from holes
     */
    @Test
    public void rightNumberOfWhiteKorgoolWhenRemoving() {
        GameWindow gw = new GameWindow();
        ArrayList < HoleUI > holes = gw.getHolesBottomRow();

        int[] korgoolsToRemove = {
                -2,
                -100,
                300,
                -1,
                1,
                0,
                22,
                100,
                -12,
                -4564
        }; //edge cases and some normal values


        for (int i: korgoolsToRemove) {
            for (HoleUI h: holes) {

                int originalWhiteKorgools = 0;

                for (KorgoolUI k: h.getKorgols()) {
                    if (k.isWhite())
                        originalWhiteKorgools++;
                }
                h.removeKorgols(i);

                int whiteKorgools = 0;

                for (KorgoolUI k: h.getKorgols()) {
                    if (k.isWhite())
                        whiteKorgools++;
                }
                if (i <= 0) {
                    assertEquals(originalWhiteKorgools, whiteKorgools);
                } else if (i + originalWhiteKorgools >= h.getMaxKorgolsPossible()) {
                    assertEquals(h.getMaxKorgolsPossible(), whiteKorgools);
                }

            }

        }
    }


    /**
     * Test if adding the correct amount of korgools (82) to a kazan
     * will win the game
     */
    @Test
    public void testIfRightNumberOfKorgoolsInKazanWinsGame(){
        GameWindow gw1 = new GameWindow();
        gw1.getKazans().get(0).addKorgols(82);
        boolean bool1 = gw1.getScoreboard().update();
        assertTrue(bool1);

    }

    /**
     * Test if adding the correct amount of korgools (82) to a kazan
     * will lose the game
     */
    @Test
    public void testIfRightNumberOfKorgoolsInKazanLosesGame(){
        GameWindow gw2 = new GameWindow();
        gw2.getKazans().get(1).addKorgols(82);
        boolean bool2 = gw2.getScoreboard().update();
        assertTrue(bool2);
    }

    /**
     * Test if adding the correct amount of korgools (81) to a kazan
     * will tie the game
     */
    @Test
    public void testIfRightNumberOfKorgoolsInKazanTiesGame(){
        //CHECK TIE
        GameWindow gw3 = new GameWindow();
        gw3.getKazans().get(0).addKorgols(81);
        gw3.getKazans().get(1).addKorgols(81);
        boolean bool3 = gw3.getScoreboard().update();
        assertTrue(bool3);

        //CHECK TIE WITH ONLY ONE KAZAN
        GameWindow gw4 = new GameWindow();
        gw4.getKazans().get(0).addKorgols(81);
        boolean bool4 = gw4.getScoreboard().update();
        assertTrue(!bool4);

        //CHECK TIE WITH ONLY ONE KAZAN
        GameWindow gw5 = new GameWindow();
        gw5.getKazans().get(1).addKorgols(81);
        boolean bool5 = gw5.getScoreboard().update();
        assertTrue(!bool5);

    }


    /**
     * Test if adding the correct amount of korgools to a kazan
     * won't win the game
     */
    @Test
    public void testIfRightNumberOfKorgoolsInKazanDoesntWinGame(){
      //CHECK WRONG NUMBER DOESNT WIN
        int[] arr = {-10,100,125, 1, 0 -1};
        for(int i = 0; i<arr.length;++i){
            GameWindow gw = new GameWindow();
            gw.getKazans().get(1).addKorgols(arr[i]);
            boolean bool = gw.getScoreboard().update();
            if(arr[i]>=82)
                assertTrue(bool);
            else
                assertTrue(!bool);
        }

    }



}