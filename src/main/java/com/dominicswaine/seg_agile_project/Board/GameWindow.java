package com.dominicswaine.seg_agile_project.Board;

import com.dominicswaine.seg_agile_project.Logic.Parser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *  This object creates the window where board and scoreboard are
 *  shown.
 *  It is the window in which players can interact and play the game.
 *
 * @author Danilo Del Busso
 * @version 12-12-2018
 */
public class GameWindow {

    private ArrayList <HoleUI> holesTopRow;
    private ArrayList <HoleUI> holesBottomRow;
    private ArrayList <KazanUI> kazans;

    private ScoreboardUI scoreboard;
    private JFrame gameWindow;

    /**
     * A window containing the game board and the scoreboard.
     * All holes and kazans are empty
     */
    public GameWindow() {

        //initialising data structures
        holesTopRow = new ArrayList < > ();
        holesBottomRow = new ArrayList < > ();
        kazans = new ArrayList < > ();

        gameWindow = new JFrame("Toguz Korgool");
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = gameWindow.getContentPane();
        pane.setLayout(new BorderLayout());

        ContainerOfEverything board = createNewBoard();

        pane.add(board, BorderLayout.WEST);

        scoreboard = new ScoreboardUI(kazans, gameWindow);

        pane.add(scoreboard, BorderLayout.EAST);

        gameWindow.pack();
        gameWindow.setSize(new Dimension(1275, 720));
        gameWindow.setVisible(true);
        gameWindow.setLocationRelativeTo(null);

    }


    /**
     * Create and draw starting board with 9 korgols per hole and empty kazans
     * @return the board panel
     */
    private ContainerOfEverything createNewBoard() {

        String backgroundPath = System.getProperty("user.dir") + "/src/main/resources/woodbg.jpg";

        ContainerOfEverything board = new ContainerOfEverything(backgroundPath);
        JPanel topRow = new JPanel(new GridLayout(0, 9));
        JPanel bottomRow = new JPanel(new GridLayout(0, 9));
        JPanel kazanRow = new JPanel(new BorderLayout());



        //create holes
        for (int i = 0; i < 9; ++i) {
            //populate top row with holes
            HoleUI h = new HoleUI(8 - i + 1);
            h.adjustLooks();
            holesTopRow.add(h);
            topRow.add(h);

            //populate bottom row with holes
            h = new HoleUI(i + 1);
            h.adjustLooks();
            holesBottomRow.add(h);
            bottomRow.add(h);
        }

        //create kazans
        KazanUI k = new KazanUI();
        kazans.add(k);
        k.adjustLooks();
        kazanRow.add(k, BorderLayout.EAST);

        k = new KazanUI();
        kazans.add(k);
        k.adjustLooks();
        kazanRow.add(k, BorderLayout.WEST);


        topRow.setOpaque(false);
        kazanRow.setOpaque(false);
        bottomRow.setOpaque(false);



        //add elements to board
        board.add(topRow, BorderLayout.NORTH);
        board.add(kazanRow, BorderLayout.CENTER);
        board.add(bottomRow, BorderLayout.SOUTH);

        board.setVisible(true);

        return board;
    }


    /**
     * Return the holes on the bottom row
     * @return the holes on the bottom row
     */
    public ArrayList <HoleUI> getHolesBottomRow() {
        return holesBottomRow;
    }

    /**
     * Return the holes on the top row
     * @return the holes on the top row
     */
    public ArrayList <HoleUI> getHolesTopRow() {
        return holesTopRow;
    }

    /**
     * Return the kazans
     * @return the kazans
     */
    public ArrayList<KazanUI> getKazans(){ return kazans;}

    public ScoreboardUI getScoreboard() {
        return scoreboard;
    }

    /**
     * Return the frame containing the game window
     * @return the frame containing the game window
     */
    public JFrame getGameWindow() {
        return gameWindow;
    }
}
