package main.java.com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * @author Danilo Del Busso
 * @version 14-11-2018
 */
public class Hole extends KorgolsContainer {


    int tuz;   ///tuz value (0 = not assigned to any player, 1 = assigned to 1st player, 2= assigned to 2nd player)

    /**
     * The hole is the main korgol container of the game
     */
    Hole() {
        setIcon(new ImageIcon(
                    Objects.requireNonNull(
                            getClass().getClassLoader().
                                    getResource(
                                            "main/resources/frame.png")
                    )
                )
        );

        setLayout(new GridLayout(8,5));

        this.tuz = 0;  //tuz is not assigned to any player
    }


    /**
     * Assigns this hole as tuz to the designed player
     * @param player which player the tuz has to b eassigned to (0 = not assigned to any player, 1 = assigned to 1st player, 2= assigned to 2nd player)
      * @return true if the tuz has been assigned successfully
     */
    public boolean assignTuz(int player){
        if(player > -1 && player < 3){
            tuz = player;
            return true;
        }

        return false;
    }

    @Override
    public int getMaxKorgolsPossible() {
        return 21;
    }


    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.getHSBColor(0,0,0));
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(10, 15, 150, 330 ,10,10);
    }*/

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180,360);
    }

}
