package com.dominicswaine.seg_agile_project.Board;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import com.athaydes.automaton.Swinger;
import java.util.HashMap;
import com.athaydes.automaton.Speed;
import static org.junit.Assert.*;

/**
 * This is the test class for class CustomGame.
 *
 * @author David Mahgerefteh
 * @version 12/12/2018
 */
public class CustomGameTest {

    private CustomGame customGame;
    private Swinger swinger;
    private String playerTuz;
    private JLabel tuzLabel;

    /**
     * This method is executed before each test in this class.
     */
    @Before
    public void executedBeforeEachTest() {

        customGame = new CustomGame();
        swinger = Swinger.getUserWith(customGame.getFrame());
        Swinger.setDEFAULT(Speed.VERY_FAST);
        swinger.pause(400);

    }

    /**
     * This method tests if the tuz successfully becomes 2 after selecting it.
     */
    @Test
    public void testIfTuzBecomes2() {

        testHelper1();
        //The opponent's tuz should not change to 2
        assertNotEquals(customGame.getTuz(false), "2");

    }

    /**
     * This method tests if a sequence of tuz selections works.
     */
    @Test
    public void testASequenceOfTuzSelections() {

        testHelper1();
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

    /**
     * This is a helper method for testIfTuzBecomes2() and testASequenceOfTuzSelections().
     */
    private void testHelper1() {

        swinger.clickOn("name:2");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        JLabel tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "2");
        assertEquals(tuzLabel.getText(), "Your Tuz: 2");

    }

    /**
     * This tests that the player and opponent cannot choose the same tuz value.
     */
    @Test
    public void testPlayerAndOpponentCantChooseSameValue() {

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

    /**
     * This tests that hole 9 cannot be a tuz.
     */
    @Test
    public void testThatHole9CantBeTuz() {

        swinger.clickOn("name:9");
        swinger.pause(800);
        swinger.clickOn("text:OK");
        swinger.pause(800);
        String playerTuz = customGame.getTuz(true);
        assertNotEquals(playerTuz, "9");
        assertEquals(playerTuz, "0");

    }

    /**
     * This tests that choosing hole 9 after a valid selection does not change the current tuz value.
     */
    @Test
    public void testThatChoosingHole9AfterAValidSelectionDoesNothing() {

        swinger.clickOn("name:2");
        swinger.pause(400);
        swinger.clickOn("name:9");
        swinger.pause(400);
        swinger.clickOn("text:OK");
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        assertEquals(playerTuz, "2");

    }

    /**
     * This tests that the tuz canceller works.
     */
    @Test
    public void testTuzCanceller() {

        swinger.clickOn("name:2"); //First select a value, 2
        swinger.pause(400);
        swinger.clickOn("name:0"); //Then cancel this value
        swinger.pause(400);
        String playerTuz = customGame.getTuz(true);
        JLabel tuzLabel = (JLabel)swinger.getAt("name:playerTuzLabel");
        assertEquals(playerTuz, "0");
        assertEquals(tuzLabel.getText(), "Your Tuz: None");

    }

    /**
     * This tests that both the player and opponent can click cancel.
     */
    @Test
    public void testBothPlayerAndOpponentCanClickCancel() {

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

    /**
     * This tests if the player can successfully switch to the opponent.
     */
    @Test
    public void testIfPlayerChangesToOpponent() {

        swinger.clickOn("name:dropdown");
        swinger.pause(400);
        swinger.clickOn("text:Opponent (Black Side)");
        swinger.pause(400);
        boolean isPlayer = customGame.getIsPlayer();
        assertEquals(isPlayer, false);

    }

    /**
     * This tests if the inputted korgool value can be successfully stored.
     */
    @Test
    public void testIfInputtedKorgoolValueGetsStored() {

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

    /**
     * This tests if a sequence of inputted korgool values can be successfully stored.
     */
    @Test
    public void testASequenceOfKorgoolSelections() {

        HashMap<String, JSpinner> mapOfSpinners = testHelper2();

        int[] playerValues = customGame.getValues(true);

        int sum = 0;
        for (int i = 0; i<playerValues.length; i++) {

            sum = sum + playerValues[i];

        }

        assertEquals(sum, 100);

    }

    /**
     * This tests if a sequence of inputted korgool values across both the player and
     * opponent can be successfully stored.
     */
    @Test
    public void testASequenceOfKorgoolsPlayerAndOpponent() {

        HashMap<String, JSpinner> mapOfSpinners = testHelper2();

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

    /**
     * This is a helper method for testASequenceOfKorgoolSelections() and testASequenceOfKorgoolsPlayerAndOpponent().
     */
    private HashMap<String, JSpinner> testHelper2() {

        HashMap<String, JSpinner> mapOfSpinners = customGame.getMapOfSpinners();

        mapOfSpinners.forEach((k, v) -> {

            JSpinner spinner = mapOfSpinners.get(k);
            swinger.clickOn(spinner);
            swinger.pause(400);
            spinner.setValue(10);
            swinger.pause(400);

        });

        return mapOfSpinners;

    }

    /**
     * This method tests whether or not you are able to start a game with less than
     * 162 korgools.
     */
    @Test
    public void testTotalKorgoolsLessThan162() {

        swinger.pause(800);
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

    /**
     * This method tests whether or not you are able to start a game with more than
     * 162 korgools.
     */
    @Test
    public void testTotalKorgoolsGreaterThan162() {

        swinger.pause(800);
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

    /**
     * This method tests whether or not you are able to start a game with exactly
     * 162 korgools.
     */
    @Test
    public void testTotalKorgoolsEqualTo162() {

        swinger.pause(800);
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

    /**
     * This method tests if the total number of korgools displayed
     * is correct.
     */
    @Test
    public void testTotalKorgoolsDisplayedIsCorrect() {

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


