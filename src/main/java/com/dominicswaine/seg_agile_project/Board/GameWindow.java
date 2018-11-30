package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *  This object creates the window where board and scoreboard are
 *  shown.
 *  It is the window in which players can interact and play the game
 *
 * @author Danilo Del Busso
 * @version 26-11-2018
 */
public class GameWindow {

    private static final int HOLES_PER_PLAYER = 9;
    private static final int STARTING_KORGOLS = 9;

    private ArrayList<Hole> holesTopRow;
    private ArrayList<Hole> holesBottomRow;
    private ArrayList<Kazan> kazans;



    public static void main(String[] args) {
                new GameWindow();
        }  //todo : remove this

        public GameWindow(){

            //initialising data structures
            holesTopRow = new ArrayList<>();
            holesBottomRow = new ArrayList<>();
            kazans = new ArrayList<>();

            JFrame gameWindow = new JFrame("Toguz Korgool");
            gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Container pane = gameWindow.getContentPane();
            pane.setLayout(new BorderLayout());

            Board board = createNewBoard();

            pane.add(board, BorderLayout.WEST);

            //todo: Scoreboard goes here

            gameWindow.pack();
            gameWindow.setSize(new Dimension(1280, 720));
            gameWindow.setVisible(true);
        }


    /**
     * Create and draw starting board with 9 korgols per hole and empty kazans
     * @return the board panel
     */
    private Board createNewBoard() {

            String backgroundPath = System.getProperty("user.dir") + "/src/main/resources/woodbg.jpg";

            Board board = new Board(backgroundPath);
            JPanel topRow = new JPanel(new GridLayout(0,HOLES_PER_PLAYER));
            JPanel bottomRow = new JPanel(new GridLayout(0,HOLES_PER_PLAYER));
            JPanel kazanRow = new JPanel(new BorderLayout());



            //create holes
            for (int i = 0; i < HOLES_PER_PLAYER ; ++i){
                //populate top row with holes
                Hole h = new Hole(8-i+1);
                h.addKorgols(STARTING_KORGOLS);
                h.adjustLooks();
                holesTopRow.add(h);
                topRow.add(h);

                //populate bottom row with holes
                h = new Hole(i+1);
                h.addKorgols(STARTING_KORGOLS);
                h.adjustLooks();
                holesBottomRow.add(h);
                bottomRow.add(h);
            }

            //create kazans
            Kazan k = new Kazan();
            kazans.add(k);
            k.addKorgols(70);
            k.adjustLooks();
            kazanRow.add(k, BorderLayout.EAST);

            k = new Kazan();
            kazans.add(k);
            k.addKorgols(0);
            k.adjustLooks();
            kazanRow.add(k,BorderLayout.WEST);


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
    public ArrayList<Hole> getHolesBottomRow() {
        return holesBottomRow;
    }

    /**
     * Return the holes on the top row
     * @return the holes on the top row
     */
    public ArrayList<Hole> getHolesTopRow() {
        return holesTopRow;
    }
}
