package com.dominicswaine.seg_agile_project.Board;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import com.athaydes.automaton.Speed;
import static org.junit.Assert.*;

/**
 * This is the test class for class MainMenu.
 *
 * @author David Mahgerefteh
 * @version 12/12/2018
 */
public class MainMenuTest {

    private MainMenu mainMenu;
    private Swinger swinger;

    /**
     * This method is executed before each test in this class.
     */
    @Before
    public void executedBeforeEachTest() {

        mainMenu = new MainMenu();
        Swinger.forSwingWindow().pause(500);
        swinger = Swinger.getUserWith(mainMenu.getFrame());
        Swinger.setDEFAULT(Speed.VERY_FAST);
        swinger.pause(1500);

    }

    /**
     * This method tests if the start game button works.
     */
    @Test
    public void testStartGameButton() {

        swinger.clickOn("name:startGameButton");
        swinger.pause(800);
        assertNotNull(mainMenu.getGame());

    }

    /**
     * This tests if the custom game button works.
     */
    @Test
    public void testCustomGameButton() {

        swinger.clickOn("name:customGameButton");
        swinger.pause(800);
        assertNotNull(mainMenu.getCustomGame());

    }

    /**
     * This tests if the help button works.
     */
    @Test
    public void testHelpButton() {

        swinger.clickOn("name:helpButton");
        swinger.pause(800);
        swinger.clickOn("text:OK"); //If it can successfully click on ok, the test will not fail, meaning help pane was shown
        swinger.pause(800);

    }

}
