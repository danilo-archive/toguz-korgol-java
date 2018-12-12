package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import java.util.ArrayList;


import static org.junit.Assert.*;


public class UILogicTest {

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

    @Test
    public void testIfRightNumberOfKorgoolsInKazanClosesGame(){
        GameWindow gw1 = new GameWindow();
        gw1.getKazans().get(0).addKorgols(82);
        boolean bool1 = gw1.getScoreboard().update();
        assertTrue(bool1);

        GameWindow gw2 = new GameWindow();
        gw2.getKazans().get(1).addKorgols(82);
        boolean bool2 = gw2.getScoreboard().update();
        assertTrue(bool2);
    }


}