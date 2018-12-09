package com.dominicswaine.seg_agile_project.Board;
import org.junit.Test;
import javax.swing.*;
import com.athaydes.automaton.Swinger;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CustomGameTest {

    @Test
    public void testIfTuzBecomes2() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        JLabel tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "2");
        assertEquals(tuzLabel.getText(), "Your Tuz: 2");
        //The opponent's tuz should not change to 2
        assertNotEquals(customGame.getTuz(false), "2");

    }

    @Test
    public void testASequenceOfTuzSelections() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        JLabel tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "2");
        assertEquals(tuzLabel.getText(), "Your Tuz: 2");
        swinger.pause(400);
        swinger.clickOn("name:5");
        swinger.pause(400);
        playerTuz = customGame.getTuz(true);
        tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "5");
        assertEquals(tuzLabel.getText(), "Your Tuz: 5");
        swinger.pause(400);
        swinger.clickOn("name:7");
        swinger.pause(400);
        playerTuz = customGame.getTuz(true);
        tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "7");
        assertEquals(tuzLabel.getText(), "Your Tuz: 7");
        swinger.pause(400);

    }

    @Test
    public void testPlayerAndOpponentCantChooseSameValue() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        customGame.changeUser();
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        swinger.clickOn("text:OK");
        swinger.pause(400);
        String opponentTuz = customGame.getTuz(false);
        assertNotEquals(playerTuz, opponentTuz);
        assertEquals(playerTuz, "2");
        assertEquals(opponentTuz, "0");

    }


    @Test
    public void testThatHole9CantBeTuz() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(800);
        swinger.clickOn("name:9");
        swinger.pause(800);
        swinger.clickOn("text:OK");
        swinger.pause(800);
        String playerTuz = customGame.getTuz(true);
        assertNotEquals(playerTuz, "9");
        assertEquals(playerTuz, "0");

    }

    @Test
    public void testThatChoosingHole9AfterAValidSelectionDoesNothing() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        swinger.clickOn("name:9");
        swinger.pause(400);
        swinger.clickOn("text:OK");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        assertEquals(playerTuz, "2");

    }

    @Test
    public void testTuzCanceller() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2"); //First select a value, 2
        swinger.pause(400);
        swinger.clickOn("name:0"); //Then cancel this value
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        JLabel tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "0");
        assertEquals(tuzLabel.getText(), "Your Tuz: None");

    }

    @Test
    public void testBothPlayerAndOpponentCanClickCancel() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:2");
        swinger.pause(400);
        swinger.clickOn("name:0"); //Then cancel this value
        swinger.pause(400);
        customGame.changeUser();
        swinger.pause(400);
        swinger.clickOn("name:5");
        swinger.pause(400);
        swinger.clickOn("name:0"); //Then cancel this value
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        String opponentTuz = customGame.getTuz(false);
        assertEquals(playerTuz, "0");
        assertEquals(opponentTuz, "0");

    }

    @Test
    public void testIfPlayerChangesToOpponent() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        swinger.clickOn("name:dropdown");
        swinger.pause(400);
        swinger.clickOn("text:Opponent (Black Side)");
        swinger.pause(400);
        boolean isPlayer = customGame.getIsPlayer();
        assertEquals(isPlayer, false);

    }

    @Test
    public void testIfInputtedKorgoolValueGetsStored() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();
        JSpinner spinner = mapOfSpinners.get("0");
        swinger.clickOn(spinner);
        swinger.pause(400);
        spinner.setValue(50);
        swinger.pause(400);
        swinger.pause(400);
        int[] playerValues = customGame.getValues(true);
        assertEquals(playerValues[0], 50);

    }

    @Test
    public void testASequenceOfKorgoolSelections() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();

        mapOfSpinners.forEach((k, v) -> {

            JSpinner spinner = mapOfSpinners.get(k);
            swinger.clickOn(spinner);
            swinger.pause(400);
            spinner.setValue(10);
            swinger.pause(400);

        });

        int[] playerValues = customGame.getValues(true);

        int sum = 0;
        for (int i = 0; i<playerValues.length; i++) {

         sum = sum + playerValues[i];

        }

        assertEquals(sum, 100);

    }

    @Test
    public void testASequenceOfKorgoolsPlayerAndOpponent() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();

        mapOfSpinners.forEach((k, v) -> {

            JSpinner spinner = mapOfSpinners.get(k);
            swinger.clickOn(spinner);
            swinger.pause(400);
            spinner.setValue(10);
            swinger.pause(400);

        });

        int[] playerValues = customGame.getValues(true);
        swinger.pause(400);

        customGame.changeUser();

        mapOfSpinners.forEach((k, v) -> {

            JSpinner spinner = mapOfSpinners.get(k);
            swinger.clickOn(spinner);
            swinger.pause(400);
            spinner.setValue(5);
            swinger.pause(400);

        });

        int[] opponentValues = customGame.getValues(false);
        int sum = customGame.sumOfPlayerAndOpponentValues();

        assertEquals(sum, 150);

    }

    @Test
    public void testTotalKorgoolsLessThan162() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();
        JSpinner spinner = mapOfSpinners.get("0");
        swinger.clickOn(spinner);
        swinger.pause(400);
        spinner.setValue(161);
        swinger.pause(400);
        swinger.clickOn("name:Start");
        assertNull(customGame.getGame());
        swinger.pause(400);
        swinger.clickOn("text:OK");
        swinger.pause(400);

    }

    @Test
    public void testTotalKorgoolsGreaterThan162() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();
        JSpinner spinner = mapOfSpinners.get("0");
        swinger.clickOn(spinner);
        swinger.pause(400);
        spinner.setValue(163);
        swinger.pause(400);
        swinger.clickOn("name:Start");
        assertNull(customGame.getGame());
        swinger.pause(400);
        swinger.clickOn("text:OK");
        swinger.pause(400);

    }

    @Test
    public void testTotalKorgoolsEqualTo162() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();
        JSpinner spinner = mapOfSpinners.get("0");
        swinger.clickOn(spinner);
        swinger.pause(400);
        spinner.setValue(162);
        swinger.pause(400);
        swinger.clickOn("name:Start");
        swinger.pause(400);
        assertNotNull(customGame.getGame());
        swinger.pause(400);

    }

    @Test
    public void testTotalKorgoolsDisplayedIsCorrect() {

        CustomGame customGame = new CustomGame();
        Swinger swinger = Swinger.getUserWith(customGame.getFrame());
        swinger.pause(400);
        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();
        JSpinner spinner = mapOfSpinners.get("8");
        swinger.clickOn(spinner);
        swinger.pause(400);
        spinner.setValue(45);
        swinger.pause(400);
        JLabel totalLabel = (JLabel)swinger.getAt("name:totalKorgools");
        assertEquals(totalLabel.getText(), "Total Korgools: 45");
        swinger.pause(400);

    }

}



