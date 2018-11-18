package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Danilo Del Busso
 * @version 14-11-2018
 */
public class GameWindow {

    private static final int HOLES_PER_PLAYER = 9;
    private static final int STARTING_KORGOLS = 9;
    private static final Color COLOR_KORGOLS = Color.black;

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

            JPanel board = createNewBoard();

            pane.add(board, BorderLayout.WEST);

            //Scoreboard goes here

            gameWindow.pack();
            gameWindow.setSize(new Dimension(1920, 1080));
            gameWindow.setVisible(true);





        }


    /**
     * Create and draw starting board with 9 korgols per hole and empty kazans
     * @return the board panel
     */
    private JPanel createNewBoard() {

            JPanel board = new JPanel(new BorderLayout());
            JPanel topRow = new JPanel(new GridLayout(0,HOLES_PER_PLAYER));

            JPanel kazanRow = new JPanel(new BorderLayout());
            JPanel bottomRow = new JPanel(new GridLayout(0,HOLES_PER_PLAYER));




            for (int i = 0; i < HOLES_PER_PLAYER ; ++i){
                //populate top row with holes
                Hole h = new Hole(8-i+1);
                h.addKorgols(STARTING_KORGOLS, COLOR_KORGOLS);
                h.adjustLooks(Color.white);
                holesTopRow.add(h);
                topRow.add(h);

                //populate bottom row with holes
                h = new Hole(i+1);
                h.addKorgols(STARTING_KORGOLS, COLOR_KORGOLS);
                h.adjustLooks(Color.white);
                holesBottomRow.add(h);
                bottomRow.add(h);
            }

            Kazan k = new Kazan();
            kazans.add(k);
            k.addKorgols(108, Color.black);
            k.adjustLooks(Color.white);
            kazanRow.add(k, BorderLayout.EAST);

            k = new Kazan();
            kazans.add(k);
            k.addKorgols(0, Color.black);
            k.adjustLooks(Color.white);
            kazanRow.add(k,BorderLayout.WEST);


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
